package ec.core.stock.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ec.core.goods.model.CoreGoods;
import ec.core.stock.dao.CoreStockDao;
import ec.core.stock.model.CoreStock;

/**
 * @author SUMIN
 * @version 0.1
 * @since 2016-05-25
 */
@Service
@Transactional
public class CoreStockService {

	/**
	 * 获取库存列表
	 */
	public List<CoreStock> getStockList(String supplier, String goodsCode, String fromDate, String toDate) {
		List<CoreStock> datas = this.getCoreStockDao().getStockList(supplier, goodsCode, fromDate, toDate);
		return datas;
	}

	/**
	 * 获取待维护的商品列表
	 */
	public List<CoreGoods> getMaintenanceGoodsList() {
		List<CoreGoods> datas = this.getCoreStockDao().getMaintenanceGoodsList();
		return datas;
	}

	/**
	 * 添加一条库存记录
	 */
	public void addStock(Long createrId, CoreStock stock) {
		stock.setValid(true);
		stock.setCreaterId(createrId);
		this.getCoreStockDao().addStock(stock);
	}

	/**
	 * 购买商品扣除库存
	 */
	public void removeStock(String orderCode, String goodsCode, float num) {
		CoreStock stock = new CoreStock();
		stock.setCreaterId(-1l);
		stock.setGoodsCode(goodsCode);
		stock.setNum(-num);
		stock.setValid(true);
		stock.setOrderCode(orderCode);
		stock.setDescription("商城系统自动扣除库存,订单编号:" + orderCode);
		this.getCoreStockDao().addStock(stock);
	}

	/**
	 * 
	 */
	@Resource
	private CoreStockDao coreStockDao;

	private CoreStockDao getCoreStockDao() {
		return coreStockDao;
	}

	public void setCoreStockDao(CoreStockDao coreStockDao) {
		this.coreStockDao = coreStockDao;
	}
}