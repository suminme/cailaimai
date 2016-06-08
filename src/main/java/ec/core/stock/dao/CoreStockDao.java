package ec.core.stock.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import ec.core.goods.model.CoreGoods;
import ec.core.stock.model.CoreStock;
import ec.core.user.model.CoreUser;
import framework.data.date.DateUtil;
import framework.service.rds.RdsService;

/**
 * @author SUMIN
 * @version 0.1
 * @since 2016-05-25
 */
@Component
public class CoreStockDao {

	/**
	 * 获取库存列表
	 */
	public List<CoreStock> getStockList(String supplier, String goodsCode, String fromDate, String toDate) {
		String sql = "select a.*,b.code as goodsCode,b.id as goodsId,c.name as creater from {TABLE} a left join "
				+ this.getRdsService().getTableName(CoreGoods.class) + " b on a.goodsCode=b.code left join "
				+ this.getRdsService().getTableName(CoreUser.class) + " c on a.createrId=c.id where b.status=? ";
		List<Object> obs = new ArrayList<Object>();
		obs.add(CoreGoods.STATUS_NORMAL);
		if (null != supplier && !"".equals(supplier)) {
			sql = sql + " and a.supplier=?";
			obs.add(supplier);
		}
		if (null != goodsCode && !"".equals(goodsCode)) {
			sql = sql + " and a.goodsCode=?";
			obs.add(goodsCode);
		}
		if (null != fromDate && !"".equals(fromDate)) {
			sql = sql + " and a.createTime>=?";
			obs.add(fromDate);
		}
		if (null != toDate && !"".equals(toDate)) {
			sql = sql + " and a.createTime<=?";
			obs.add(toDate);
		}
		sql = sql + " order by a.id desc";
		List<CoreStock> datas = this.getRdsService().gets(CoreStock.class, sql, obs.toArray());
		return datas;
	}

	/**
	 * 获取待维护的商品列表
	 */
	public List<CoreGoods> getMaintenanceGoodsList() {
		String sql = "select a.id,a.name,a.code,ifnull(b.stock,0) stock from {TABLE} a left join (select sum(num) as stock,goodsCode from "
				+ this.getRdsService().getTableName(CoreStock.class)
				+ " where valid=? group by goodsCode) b on a.code=b.goodsCode where a.status=? order by id desc";
		List<CoreGoods> datas = this.getRdsService().gets(CoreGoods.class, sql,
				new Object[] { CoreGoods.STATUS_NORMAL, true });
		return datas;
	}

	/**
	 * 添加一条库存记录
	 */
	public void addStock(CoreStock stock) {
		stock.setCreateTime(DateUtil.getDateTimeString());
		this.getRdsService().save(stock);
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