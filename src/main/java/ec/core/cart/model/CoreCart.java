package ec.core.cart.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import ec.core.mall.model.CoreMall;

/**
 * @author SUMIN
 * @version 0.1
 * @since 2016-05-16
 */
@SuppressWarnings("serial")
public class CoreCart implements Serializable {

	/**
	 * 购物车商品总数
	 */
	private int count;

	/**
	 * 购物车商品总额
	 */
	private float money;

	/**
	 * 购物车商品列表
	 */
	private List<CoreCartGoods> cartGoodsList;

	/**
	 * 购物车商品列表分组
	 */
	private Map<CoreMall, List<CoreCartGoods>> goodsMap;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public float getMoney() {
		return money;
	}

	public void setMoney(float money) {
		this.money = money;
	}

	public List<CoreCartGoods> getCartGoodsList() {
		return cartGoodsList;
	}

	public void setCartGoodsList(List<CoreCartGoods> cartGoodsList) {
		this.cartGoodsList = cartGoodsList;
	}

	public Map<CoreMall, List<CoreCartGoods>> getGoodsMap() {
		return goodsMap;
	}

	public void setGoodsMap(Map<CoreMall, List<CoreCartGoods>> goodsMap) {
		this.goodsMap = goodsMap;
	}
}