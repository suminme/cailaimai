package ec.core.order.service;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ec.core.cart.service.CoreCartService;
import ec.core.goods.model.CoreGoods;
import ec.core.goods.service.CoreGoodsService;
import ec.core.mall.model.CoreMall;
import ec.core.mall.service.CoreMallService;
import ec.core.message.service.CoreMessageService;
import ec.core.order.dao.CoreOrderDao;
import ec.core.order.model.CoreOrder;
import ec.core.order.model.CoreOrderGoods;
import ec.core.stock.service.CoreStockService;
import ec.core.user.model.CoreUser;
import ec.core.user.model.CoreUserAddress;
import ec.core.user.model.CoreUserInvoice;
import ec.core.user.model.CoreUserMaterial;
import ec.core.user.service.CoreUserService;
import ec.system.config.service.SystemConfigService;
import framework.data.date.DateUtil;
import net.sf.json.JSONObject;

/**
 * @author SUMIN
 * @version 0.1
 * @since 2016-05-31
 */
@Service
@Transactional
public class CoreOrderService {

	/**
	 * 根据状态获取订单列表
	 */
	public List<CoreOrder> getOrderListByStatus(Integer status) {
		List<CoreOrder> datas = this.getCoreOrderDao().getOrderListByStatus(status);
		return datas;
	}

	/**
	 * 根据订单号获取订单信息
	 */
	public CoreOrder getOrderByCode(String code) {
		CoreOrder order = this.getCoreOrderDao().getOrderByCode(code);
		this.getAndSetOrderUser(order);
		this.getAndSetOrderAddress(order);
		this.getAndSetOrderInvoice(order);
		this.getAndSetOrderMaterial(order);
		this.getAndSetOrderGoodsList(order);
		return order;
	}

	/**
	 * 根据订单号取用户订单信息
	 */
	public CoreOrder getUserOrderByCode(Long userId, String code) {
		CoreOrder order = this.getCoreOrderDao().getUserOrderByCode(userId, code);
		this.getAndSetOrderMaterial(order);
		this.getAndSetOrderAddress(order);
		this.getAndSetOrderInvoice(order);
		this.getAndSetOrderGoodsList(order);
		return order;
	}

	/**
	 * 根据主订单号取用户订单列表
	 */
	public List<CoreOrder> getUserOrderListByMainCode(Long userId, String mainCode) {
		List<CoreOrder> datas = this.getCoreOrderDao().getUserOrderListByMainCode(userId, mainCode);
		return datas;
	}

	/**
	 * 根据状态获取用户订单列表
	 */
	public List<CoreOrder> getUserOrderListByStatus(Long userId, Integer status) {
		List<CoreOrder> datas = this.getCoreOrderDao().getUserOrderListByStatus(userId, status);
		this.getAndSetOrderGoodsList(datas);
		return datas;
	}

	/**
	 * 拆分订单
	 */
	public Map<CoreOrder, List<CoreOrderGoods>> divideOrder(Long[] goodsIds, Float[] amounts) {
		List<CoreMall> mallList = this.getCoreMallService().getMallList();
		Map<CoreMall, CoreOrder> om = new HashMap<CoreMall, CoreOrder>();
		Map<CoreOrder, List<CoreOrderGoods>> goodsMap = new HashMap<CoreOrder, List<CoreOrderGoods>>();
		for (int n = 0; n < goodsIds.length; n++) {
			Long goodsId = goodsIds[n];
			Float amount = amounts[n];
			CoreGoods goods = this.getCoreGoodsService().getGoodsById(goodsId);
			if (null == goods) {
				continue;
			}
			CoreMall mall = null;
			for (CoreMall m : mallList) {
				if (m.getId().equals(goods.getMallId())) {
					mall = m;
					break;
				}
			}
			if (null == mall) {
				continue;
			}
			if (amount > goods.getStock()) {
				continue;
			}
			CoreOrder order = om.get(mall);
			if (null == order) {
				order = new CoreOrder();
			}
			order.setMall(mall);
			order.setMallId(mall.getId());
			List<CoreOrderGoods> list = goodsMap.get(order);
			if (null == list) {
				list = new ArrayList<CoreOrderGoods>();
			}
			CoreOrderGoods orderGoods = new CoreOrderGoods();
			orderGoods.setGoods(goods);
			orderGoods.setAmount(amount);
			orderGoods.setGoodsId(goodsId);
			orderGoods.setGoodsCode(goods.getCode());
			orderGoods.setPrice(goods.getPrice());
			list.add(orderGoods);
			order.setMoney(order.getMoney() + (goods.getPrice() * amount));
			order.setOrderGoodsList(list);
			om.put(mall, order);
			goodsMap.put(order, list);
		}
		for (CoreMall m : om.keySet()) {
			CoreOrder order = om.get(m);
			if (order.getMoney() < m.getFreightFree()) {
				order.setFreight(m.getFreight());
			}
		}
		return goodsMap;
	}

	/**
	 * 创建订单
	 */
	public String createOrder(CoreUser user, Long[] goodsIds, Float[] amounts, Long addressId, Long invoiceId,
			Long materialId, String about, Long[] cartIds) {
		Map<CoreOrder, List<CoreOrderGoods>> orderMap = this.divideOrder(goodsIds, amounts);
		String mainCode = this.createOrderCode(null);
		for (CoreOrder order : orderMap.keySet()) {
			order.setAbout(about);
			order.setUserId(user.getId());
			order.setInvoiceId(invoiceId);
			order.setAddressId(addressId);
			order.setMaterialId(materialId);
			this.getAndSetOrderCode(mainCode, order);
			order.setStatus(CoreOrder.STATUS_WAITPAY);
			this.getAndSetOrderTotal(order);
			this.getCoreOrderDao().saveOrder(order);
			this.sendOrderMessage(user, order);
		}
		if (null != cartIds && cartIds.length > 0) {
			this.getCoreCartService().deleteUserCart(user.getId(), cartIds);
		}
		return mainCode;
	}

	/**
	 * 修改订单状态
	 */
	public void updateOrderStatus(String code, int status, CoreUser operatorUser) {
		JSONObject s = this.getSystemConfigService().getConfigValueJsonObject("order", "status");
		CoreOrder order = this.getCoreOrderDao().getOrderByCode(code);
		int old = order.getStatus();
		if (status == CoreOrder.STATUS_DELIVERY) {
			order.setDeliveryTime(DateUtil.getDateTimeString());
		}
		if (status == CoreOrder.STATUS_DELIVERY) {
			this.getAndSetOrderGoodsList(order);
			for (CoreOrderGoods orderGoods : order.getOrderGoodsList()) {
				this.getCoreStockService().removeStock(code, orderGoods.getGoodsCode(), orderGoods.getAmount());
			}
		}
		order.setStatus(status);
		this.getCoreOrderDao().updateOrder(order);
		this.getCoreOrderDao().addOrderStatus(order.getId(), status, old, operatorUser.getId());
		this.getCoreMessageService().sendMessage(new Long[] { order.getUserId() }, "您的订单[" + code + "]状态已从"
				+ s.getString(String.valueOf(old)) + "修改为" + s.getString(String.valueOf(status)));
	}

	/**
	 * 修改订单金额
	 */
	public void updateOrderPay(String code, float pay, CoreUser operatorUser) {
		CoreOrder order = this.getCoreOrderDao().getOrderByCode(code);
		float old = order.getPay();
		order.setPay(pay);
		this.getCoreOrderDao().updateOrder(order);
		this.getCoreOrderDao().addOrderPay(order.getId(), pay, old, operatorUser.getId());
		this.getCoreMessageService().sendMessage(new Long[] { order.getUserId() },
				"您的订单[" + code + "]金额已从" + old + "元修改为" + pay + "元");
	}

	/**
	 * 取订单用户信息
	 */
	private void getAndSetOrderUser(CoreOrder order) {
		CoreUser user = this.getCoreUserService().getUserById(order.getUserId());
		order.setUser(user);
	}

	/**
	 * 取订单素材信息
	 */
	private void getAndSetOrderMaterial(CoreOrder order) {
		CoreUserMaterial material = null;
		if (order.getMaterialId() <= 0) {

		} else {
			material = this.getCoreUserService().getMaterialByUserAndId(order.getUserId(), order.getMaterialId());
		}
		order.setMaterial(material);
	}

	/**
	 * 取订单开票信息
	 */
	private void getAndSetOrderInvoice(CoreOrder order) {
		CoreUserInvoice invoice = null;
		if (order.getInvoiceId() <= 0) {

		} else {
			invoice = this.getCoreUserService().getInvoiceByUserAndId(order.getUserId(), order.getInvoiceId());
		}
		order.setInvoice(invoice);
	}

	/**
	 * 取订单送货信息
	 */
	private void getAndSetOrderAddress(CoreOrder order) {
		CoreUserAddress address = null;
		if (order.getAddressId() <= 0) {

		} else {
			address = this.getCoreUserService().getAddressByUserAndId(order.getUserId(), order.getAddressId());
		}
		order.setAddress(address);
	}

	/**
	 * 取订单商品数据
	 */
	private void getAndSetOrderGoodsList(CoreOrder order) {
		List<CoreOrder> orderList = new ArrayList<CoreOrder>();
		orderList.add(order);
		this.getAndSetOrderGoodsList(orderList);
	}

	/**
	 * 取订单商品数据
	 */
	private void getAndSetOrderGoodsList(List<CoreOrder> orderList) {
		if (null == orderList || orderList.size() == 0) {
			return;
		}
		List<Long> ids = new ArrayList<Long>();
		for (CoreOrder order : orderList) {
			ids.add(order.getId());
		}
		List<CoreOrderGoods> orderGoodsList = this.getCoreOrderDao().getOrderGoodsList(ids);
		for (CoreOrder order : orderList) {
			List<CoreOrderGoods> list = order.getOrderGoodsList();
			if (null == list) {
				list = new ArrayList<CoreOrderGoods>();
			}
			for (CoreOrderGoods orderGoods : orderGoodsList) {
				if (orderGoods.getOrderId().equals(order.getId())) {
					list.add(orderGoods);
				}
			}
			order.setOrderGoodsList(list);
		}
	}

	/**
	 * 设置订单号
	 */
	private void getAndSetOrderCode(String mainCode, CoreOrder order) {
		order.setMainCode(mainCode);
		order.setCode(this.createOrderCode(order.getMallId()));
	}

	/**
	 * 计算订单总金额
	 */
	private void getAndSetOrderTotal(CoreOrder order) {
		if (order.getAddressId() <= 0) {
			order.setFreight(0);
		}
		order.setTotal(order.getMoney() + order.getFreight());
		order.setPay(order.getTotal());
	}

	/**
	 * 生成订单编号
	 */
	private String createOrderCode(Long mallId) {
		String mall = "";
		if (null != mallId) {
			mall = new DecimalFormat("00").format(mallId.intValue());
		}
		String date = new SimpleDateFormat("yyMMdd").format(new Date());
		String c = new DecimalFormat("00000").format(this.getCoreOrderDao().getMaxCode());
		String code = mall + date + c;
		return code;
	}

	/**
	 * 发送订单消息
	 */
	private void sendOrderMessage(CoreUser user, CoreOrder order) {
		this.getCoreMessageService().sendMessageToAdmin(
				"用户:" + user.getMobile() + " 提交了订单:" + order.getCode() + ", 订单总额:" + order.getTotal() + "元",
				user.getId());
	}

	/**
	 * 
	 */
	@Resource
	private CoreOrderDao coreOrderDao;

	/**
	 * 
	 */
	@Resource
	private CoreCartService coreCartService;

	/**
	 * 
	 */
	@Resource
	private CoreMallService coreMallService;

	/**
	 * 
	 */
	@Resource
	private CoreUserService coreUserService;

	/**
	 * 
	 */
	@Resource
	private CoreGoodsService coreGoodsService;

	/**
	 * 
	 */
	@Resource
	private CoreStockService coreStockService;

	/**
	 * 
	 */
	@Resource
	private CoreMessageService coreMessageService;

	/**
	 * 
	 */
	@Resource
	private SystemConfigService systemConfigService;

	public CoreOrderDao getCoreOrderDao() {
		return coreOrderDao;
	}

	public void setCoreOrderDao(CoreOrderDao coreOrderDao) {
		this.coreOrderDao = coreOrderDao;
	}

	public CoreCartService getCoreCartService() {
		return coreCartService;
	}

	public void setCoreCartService(CoreCartService coreCartService) {
		this.coreCartService = coreCartService;
	}

	public CoreMallService getCoreMallService() {
		return coreMallService;
	}

	public void setCoreMallService(CoreMallService coreMallService) {
		this.coreMallService = coreMallService;
	}

	public CoreUserService getCoreUserService() {
		return coreUserService;
	}

	public void setCoreUserService(CoreUserService coreUserService) {
		this.coreUserService = coreUserService;
	}

	public CoreGoodsService getCoreGoodsService() {
		return coreGoodsService;
	}

	public void setCoreGoodsService(CoreGoodsService coreGoodsService) {
		this.coreGoodsService = coreGoodsService;
	}

	public CoreStockService getCoreStockService() {
		return coreStockService;
	}

	public void setCoreStockService(CoreStockService coreStockService) {
		this.coreStockService = coreStockService;
	}

	public CoreMessageService getCoreMessageService() {
		return coreMessageService;
	}

	public void setCoreMessageService(CoreMessageService coreMessageService) {
		this.coreMessageService = coreMessageService;
	}

	public SystemConfigService getSystemConfigService() {
		return systemConfigService;
	}

	public void setSystemConfigService(SystemConfigService systemConfigService) {
		this.systemConfigService = systemConfigService;
	}
}