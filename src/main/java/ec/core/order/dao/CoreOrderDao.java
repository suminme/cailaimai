package ec.core.order.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import ec.core.goods.model.CoreGoods;
import ec.core.goods.model.CoreGoodsImg;
import ec.core.mall.model.CoreMall;
import ec.core.order.model.CoreOrder;
import ec.core.order.model.CoreOrderCode;
import ec.core.order.model.CoreOrderGoods;
import ec.core.order.model.CoreOrderPay;
import ec.core.order.model.CoreOrderStatus;
import ec.core.user.model.CoreUser;
import ec.system.file.model.SystemFile;
import framework.data.date.DateUtil;
import framework.service.rds.RdsService;

/**
 * @author SUMIN
 * @version 0.1
 * @since 2016-05-30
 */
@Component
public class CoreOrderDao {

	/**
	 * 根据状态获取订单列表
	 */
	public List<CoreOrder> getOrderListByStatus(Integer status) {
		String sql = "select a.*,b.name as mallName,c.name as userName from {TABLE} a left join "
				+ this.getRdsService().getTableName(CoreMall.class) + " b on a.mallId=b.id left join "
				+ this.getRdsService().getTableName(CoreUser.class) + " c on a.userId=c.id where 1=1 ";
		List<Object> obs = new ArrayList<Object>();
		if (null != status) {
			sql = sql + " and a.status=?";
			obs.add(status);
		}
		sql = sql + " order by id desc";
		List<CoreOrder> datas = this.getRdsService().gets(CoreOrder.class, sql, obs.toArray());
		return datas;
	}

	/**
	 * 根据订单号获取订单信息
	 */
	public CoreOrder getOrderByCode(String code) {
		String sql = "select a.*,b.name as mallName from {TABLE} a left join "
				+ this.getRdsService().getTableName(CoreMall.class) + " b on a.mallId=b.id where a.code=?";
		CoreOrder data = this.getRdsService().get(CoreOrder.class, sql, new Object[] { code });
		return data;
	}

	/**
	 * 根据订单号取用户订单信息
	 */
	public CoreOrder getUserOrderByCode(Long userId, String code) {
		String sql = "select a.*,b.name as mallName from {TABLE} a left join "
				+ this.getRdsService().getTableName(CoreMall.class)
				+ " b on a.mallId=b.id where a.userId=? and a.code=?";
		CoreOrder data = this.getRdsService().get(CoreOrder.class, sql, new Object[] { userId, code });
		return data;
	}

	/**
	 * 根据主订单号取用户订单列表
	 */
	public List<CoreOrder> getUserOrderListByMainCode(Long userId, String mainCode) {
		String sql = "select a.*,b.name as mallName from {TABLE} a left join "
				+ this.getRdsService().getTableName(CoreMall.class)
				+ " b on a.mallId=b.id where a.userId=? and a.mainCode=?";
		List<CoreOrder> datas = this.getRdsService().gets(CoreOrder.class, sql, new Object[] { userId, mainCode });
		return datas;
	}

	/**
	 * 根据状态获取用户订单列表
	 */
	public List<CoreOrder> getUserOrderListByStatus(Long userId, Integer status) {
		String sql = "select a.*,b.name as mallName from {TABLE} a left join "
				+ this.getRdsService().getTableName(CoreMall.class) + " b on a.mallId=b.id where a.userId=?";
		List<Object> obs = new ArrayList<Object>();
		obs.add(userId);
		if (null != status) {
			sql = sql + " and a.status=?";
			obs.add(status);
		}
		sql = sql + " order by id desc";
		List<CoreOrder> datas = this.getRdsService().gets(CoreOrder.class, sql, obs.toArray());
		return datas;
	}

	/**
	 * 取订单商品数据
	 */
	public List<CoreOrderGoods> getOrderGoodsList(List<Long> orderIds) {
		String sql = "select a.*,b.code as goodsCode,b.name as goodsName,c.bucketKey as goodsImagePath from {TABLE} a left join "
				+ this.getRdsService().getTableName(CoreGoods.class)
				+ " b on a.goodsId=b.id left join (select a.*,b.bucketKey from "
				+ this.getRdsService().getTableName(CoreGoodsImg.class) + " a left join "
				+ this.getRdsService().getTableName(SystemFile.class)
				+ " b on a.fileId=b.id where a.main=?) c on a.goodsId=c.goodsId where a.orderId in (";
		for (Long orderId : orderIds) {
			sql = sql + orderId + ",";
		}
		sql = sql.substring(0, sql.length() - 1) + ") order by a.id desc";
		List<CoreOrderGoods> datas = this.getRdsService().gets(CoreOrderGoods.class, sql, new Object[] { true });
		return datas;
	}

	/**
	 * 保存订单
	 */
	public void saveOrder(CoreOrder order) {
		order.setCreateTime(DateUtil.getDateTimeString());
		this.getRdsService().save(order);
		for (CoreOrderGoods orderGoods : order.getOrderGoodsList()) {
			orderGoods.setOrderId(order.getId());
			this.getRdsService().save(orderGoods);
		}
	}

	/**
	 * 添加状态修改记录
	 */
	public void addOrderStatus(Long orderId, int status, int fromStatus, Long operatorId) {
		CoreOrderStatus orderStatus = new CoreOrderStatus();
		orderStatus.setOperatorId(operatorId);
		orderStatus.setOrderId(orderId);
		orderStatus.setOperateTime(DateUtil.getDateTimeString());
		orderStatus.setStatus(status);
		orderStatus.setFromStatus(fromStatus);
		this.getRdsService().save(orderStatus);
	}

	/**
	 * 添加支付金额记录
	 */
	public void addOrderPay(Long orderId, float pay, float fromPay, Long operatorId) {
		CoreOrderPay orderPay = new CoreOrderPay();
		orderPay.setOperatorId(operatorId);
		orderPay.setOrderId(orderId);
		orderPay.setOperateTime(DateUtil.getDateTimeString());
		orderPay.setPay(pay);
		orderPay.setFromPay(fromPay);
		this.getRdsService().save(orderPay);
	}

	/**
	 * 更新订单
	 */
	public void updateOrder(CoreOrder order) {
		this.getRdsService().save(order);
	}

	/**
	 * 获取最大的一个商品编号
	 */
	public int getMaxCode() {
		String date = DateUtil.getDateString();
		String sql = "select * from {TABLE} where createDate=?";
		CoreOrderCode c = this.getRdsService().get(CoreOrderCode.class, sql, new Object[] { date });
		if (null == c) {
			c = new CoreOrderCode();
		}
		c.setNum(c.getNum() + 1);
		c.setCreateDate(date);
		this.getRdsService().save(c);
		return c.getNum();
	}

	/**
	 * 
	 */
	@Resource
	private RdsService rdsService;

	public RdsService getRdsService() {
		return rdsService;
	}

	public void setRdsService(RdsService rdsService) {
		this.rdsService = rdsService;
	}
}