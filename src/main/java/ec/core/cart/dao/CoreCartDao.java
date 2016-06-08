package ec.core.cart.dao;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import ec.core.cart.model.CoreCart;
import ec.core.cart.model.CoreCartGoods;
import ec.core.goods.model.CoreGoods;
import framework.data.date.DateUtil;
import framework.service.rds.RdsService;

/**
 * @author SUMIN
 * @version 0.1
 * @since 2016-05-30
 */
@Component
public class CoreCartDao {

	/**
	 * 将商品添加到用户的购物车
	 */
	public void addGoodsToUserCart(Long userId, Long goodsId, float amount) {
		String sql = "select a.* from {TABLE} a where userId=? and goodsId=?";
		CoreCartGoods cartGoods = this.getRdsService().get(CoreCartGoods.class, sql, new Object[] { userId, goodsId });
		if (null == cartGoods) {
			cartGoods = new CoreCartGoods();
			cartGoods.setUserId(userId);
			cartGoods.setGoodsId(goodsId);
			cartGoods.setCreateTime(DateUtil.getDateTimeString());
		}
		cartGoods.setAmount(cartGoods.getAmount() + amount);
		this.getRdsService().save(cartGoods);
	}

	/**
	 * 获取用户购物车数据
	 */
	public CoreCart getCartByUser(Long userId) {
		String sql = "select count(*) as count,sum(price) as money from "
				+ this.getRdsService().getTableName(CoreCartGoods.class) + " a left join "
				+ this.getRdsService().getTableName(CoreGoods.class)
				+ " b on a.goodsId=b.id where a.userId=? and b.status=? group by a.userId";
		CoreCart cart = this.getRdsService().get(CoreCart.class, sql, new Object[] { userId, CoreGoods.STATUS_NORMAL });
		return cart;
	}

	/**
	 * 获取用户购物车里的商品列表
	 */
	public List<CoreCartGoods> getCartGoodsListByUser(Long userId) {
		String sql = "select * from {TABLE} a where a.userId=?";
		List<CoreCartGoods> datas = this.getRdsService().gets(CoreCartGoods.class, sql, new Object[] { userId });
		return datas;
	}

	/**
	 * 删除用户的购物车的数据
	 */
	public void deleteUserCart(Long userId, Long[] cartIds) {
		String sql = "delete from {TABLE} where userId=? and id in (";
		for (Long cartId : cartIds) {
			sql = sql + cartId + ",";
		}
		sql = sql.substring(0, sql.length() - 1) + ")";
		this.getRdsService().update(CoreCartGoods.class, sql, new Object[] { userId });
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