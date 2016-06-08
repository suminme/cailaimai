package ec.core.cart.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ec.core.cart.dao.CoreCartDao;
import ec.core.cart.model.CoreCart;
import ec.core.cart.model.CoreCartGoods;
import ec.core.goods.model.CoreGoods;
import ec.core.goods.service.CoreGoodsService;
import ec.core.mall.model.CoreMall;
import ec.core.mall.service.CoreMallService;

/**
 * @author SUMIN
 * @version 0.1
 * @since 2016-05-30
 */
@Service
public class CoreCartService {

	/**
	 * 将商品添加到用户的购物车
	 */
	public void addGoodsToUserCart(Long userId, Long goodsId, Float amount) {
		if (null == amount) {
			amount = 1f;
		}
		this.getCoreCartDao().addGoodsToUserCart(userId, goodsId, amount);
	}

	/**
	 * 删除用户的购物车的商品
	 */
	public void deleteUserCart(Long userId, Long[] cartIds) {
		this.getCoreCartDao().deleteUserCart(userId, cartIds);
	}

	/**
	 * 获取用户购物车数据
	 */
	public CoreCart getCartByUser(Long userId) {
		CoreCart cart = this.getCoreCartDao().getCartByUser(userId);
		return cart;
	}

	/**
	 * 获取用户购物车数据
	 */
	public CoreCart getCartByUserWithGoods(Long userId) {
		CoreCart cart = this.getCartByUser(userId);
		if(null == cart) {
			cart = new CoreCart();
		}
		List<CoreCartGoods> cartGoodsList = this.getCoreCartDao().getCartGoodsListByUser(userId);
		if (null == cartGoodsList) {
			return cart;
		}
		List<CoreMall> mallList = this.getCoreMallService().getMallList();
		Map<CoreMall, List<CoreCartGoods>> goodsMap = new HashMap<CoreMall, List<CoreCartGoods>>();
		for (CoreCartGoods cartGoods : cartGoodsList) {
			CoreGoods goods = this.getCoreGoodsService().getGoodsById(cartGoods.getGoodsId());
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
			cartGoods.setGoods(goods);
			List<CoreCartGoods> list = goodsMap.get(mall);
			if (null == list) {
				list = new ArrayList<CoreCartGoods>();
			}
			list.add(cartGoods);
			goodsMap.put(mall, list);
		}
		cart.setGoodsMap(goodsMap);
		cart.setCartGoodsList(cartGoodsList);
		return cart;
	}

	/**
	 * 
	 */
	@Resource
	private CoreCartDao coreCartDao;

	/**
	 * 
	 */
	@Resource
	private CoreMallService coreMallService;

	/**
	 * 
	 */
	@Resource
	private CoreGoodsService coreGoodsService;

	public CoreCartDao getCoreCartDao() {
		return coreCartDao;
	}

	public void setCoreCartDao(CoreCartDao coreCartDao) {
		this.coreCartDao = coreCartDao;
	}

	public CoreMallService getCoreMallService() {
		return coreMallService;
	}

	public void setCoreMallService(CoreMallService coreMallService) {
		this.coreMallService = coreMallService;
	}

	public CoreGoodsService getCoreGoodsService() {
		return coreGoodsService;
	}

	public void setCoreGoodsService(CoreGoodsService coreGoodsService) {
		this.coreGoodsService = coreGoodsService;
	}
}