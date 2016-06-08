package ec.web.mall.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ec.core.cart.model.CoreCart;
import ec.core.cart.service.CoreCartService;
import ec.core.goods.model.CoreGoods;
import ec.core.goods.service.CoreGoodsService;
import ec.core.mall.service.CoreMallService;
import ec.core.user.model.CoreUser;
import ec.web.mall.annotation.WebCart;
import ec.web.mall.annotation.WebMallData;
import ec.web.mall.service.WebService;
import framework.data.json.JSON;
import framework.data.web.annotation.Login;

/**
 * @author SUMIN
 * @version 0.1
 * @since 2016-04-29
 */
@Login
@Controller
@RequestMapping("/mall/cart")
public class WebMallCartController {

	/**
	 * 购物车
	 */
	@WebMallData
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(HttpServletRequest request, HttpServletResponse response) throws Exception {
		CoreUser signinUser = this.getWebService().getSigninUser(request);
		CoreCart cart = this.getCoreCartService().getCartByUserWithGoods(signinUser.getId());
		request.setAttribute("cart", cart);
		return "web/mall/cart";
	}

	/**
	 * 添加到购物车
	 */
	@WebCart
	@WebMallData
	@RequestMapping(value = "/add/", method = RequestMethod.GET)
	public String addToCart(@RequestParam("back") String back, @RequestParam("goodsId") Long goodsId, Float amount,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		CoreUser signinUser = this.getWebService().getSigninUser(request);
		this.getCoreCartService().addGoodsToUserCart(signinUser.getId(), goodsId, amount);
		CoreGoods goods = this.getCoreGoodsService().getGoodsById(goodsId);
		request.setAttribute("back", back);
		request.setAttribute("goods", goods);
		request.setAttribute("amount", amount);
		return "web/mall/cart_success";
	}

	/**
	 * 添加到购物车
	 */
	@RequestMapping(value = "/add/", method = RequestMethod.POST)
	public JSON addToCart(@RequestParam("goodsId") long goodsId, Float amount, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CoreUser signinUser = this.getWebService().getSigninUser(request);
		this.getCoreCartService().addGoodsToUserCart(signinUser.getId(), goodsId, amount);
		CoreCart cart = this.getCoreCartService().getCartByUser(signinUser.getId());
		return JSON.getJson(cart);
	}

	/**
	 * 删除购物车商品
	 */
	@RequestMapping(value = "/delete/", method = RequestMethod.POST)
	public JSON deleteCartGoods(@RequestParam("id") long id, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CoreUser signinUser = this.getWebService().getSigninUser(request);
		this.getCoreCartService().deleteUserCart(signinUser.getId(), new Long[] { id });
		return JSON.getJson(true);
	}

	/**
	 * 
	 */
	@Resource
	private WebService webService;

	/**
	 * 
	 */
	@Resource
	private CoreMallService coreMallService;

	/**
	 * 
	 */
	@Resource
	private CoreCartService coreCartService;

	/**
	 * 
	 */
	@Resource
	private CoreGoodsService coreGoodsService;

	public WebService getWebService() {
		return webService;
	}

	public void setWebService(WebService webService) {
		this.webService = webService;
	}

	public CoreMallService getCoreMallService() {
		return coreMallService;
	}

	public void setCoreMallService(CoreMallService coreMallService) {
		this.coreMallService = coreMallService;
	}

	public CoreCartService getCoreCartService() {
		return coreCartService;
	}

	public void setCoreCartService(CoreCartService coreCartService) {
		this.coreCartService = coreCartService;
	}

	public CoreGoodsService getCoreGoodsService() {
		return coreGoodsService;
	}

	public void setCoreGoodsService(CoreGoodsService coreGoodsService) {
		this.coreGoodsService = coreGoodsService;
	}
}