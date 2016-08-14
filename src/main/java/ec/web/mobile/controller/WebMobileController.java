package ec.web.mobile.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ec.core.cart.model.CoreCart;
import ec.core.cart.service.CoreCartService;
import ec.core.goods.model.CoreGoods;
import ec.core.goods.model.CoreGoodsMeta;
import ec.core.goods.service.CoreGoodsService;
import ec.core.mall.model.CoreMall;
import ec.core.mall.model.CoreMallType;
import ec.core.mall.service.CoreMallService;
import ec.core.order.model.CoreOrder;
import ec.core.order.model.CoreOrderGoods;
import ec.core.order.service.CoreOrderService;
import ec.core.user.model.CoreUser;
import ec.core.user.model.CoreUserAddress;
import ec.core.user.model.CoreUserInvoice;
import ec.core.user.service.CoreUserService;
import ec.system.captcha.service.SystemCaptchaService;
import ec.web.mall.annotation.WebCart;
import ec.web.mall.annotation.WebMallData;
import ec.web.mall.service.WebService;
import framework.data.json.JSON;
import framework.data.web.annotation.Login;
import framework.data.web.util.WebUtil;

/**
 * @author SUMIN
 * @version 0.1
 * @since 2016-07-06
 */
@Controller
@RequestMapping("/m")
public class WebMobileController {

	/**
	 * 首页
	 */
	@WebCart
	@WebMallData
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("suggestGoodsList", this.getCoreGoodsService().getSuggestGoodsListByType(null, 8));
		return "web/mobile/index";
	}

	/**
	 * 登陆
	 */
	@RequestMapping(value = "/signin/", method = RequestMethod.GET)
	public String toSignin(String r, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (null == r || "".equals(r)) {
			r = WebUtil.getContextPath(request) + "/m/";
		}
		request.setAttribute("r", r);
		return "web/mobile/signin";
	}

	/**
	 * 注册
	 */
	@RequestMapping(value = "/signup/", method = RequestMethod.GET)
	public String toSignup(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "web/mobile/signup";
	}

	/**
	 * 忘记密码
	 */
	@RequestMapping(value = "/forget/", method = RequestMethod.GET)
	public String toFoget(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "web/mobile/forget";
	}

	/**
	 * 注销
	 */
	@RequestMapping(value = "/signout/", method = RequestMethod.GET)
	public String signout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		this.getWebService().cleanToken(request, response);
		return "redirect:/m/";
	}

	/**
	 * 个人中心
	 */
	@WebCart
	@Login(mobile = true)
	@RequestMapping(value = "/personal/", method = RequestMethod.GET)
	public String personal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "web/mobile/personal/index";
	}

	/**
	 * 个人中心-付款方式
	 */
	@Login(mobile = true)
	@RequestMapping(value = "/personal/payment/", method = RequestMethod.GET)
	public String personalPayment(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "web/mobile/personal/payment";
	}

	/**
	 * 个人中心-收货地址
	 */
	@Login(mobile = true)
	@RequestMapping(value = "/personal/address/", method = RequestMethod.GET)
	public String personalAddress(HttpServletRequest request, HttpServletResponse response) throws Exception {
		CoreUser signinUser = this.getWebService().getSigninUser(request);
		List<CoreUserAddress> datas = this.getCoreUserService().getAddressListByUser(signinUser.getId());
		request.setAttribute("datas", datas);
		return "web/mobile/personal/address";
	}

	/**
	 * 个人中心-收货地址
	 */
	@Login(mobile = true)
	@RequestMapping(value = "/personal/address/add/", method = RequestMethod.GET)
	public String toPersonalAddressAdd(Long id, String r, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("r", r);
		CoreUser signinUser = this.getWebService().getSigninUser(request);
		if (null != id) {
			CoreUserAddress data = this.getCoreUserService().getAddressByUserAndId(signinUser.getId(), id);
			request.setAttribute("data", data);
		}
		return "web/mobile/personal/address_add";
	}

	/**
	 * 个人中心-收货地址
	 */
	@Login(mobile = true)
	@RequestMapping(value = "/personal/address/add/", method = RequestMethod.POST)
	public JSON personalAddressAdd(@ModelAttribute CoreUserAddress data, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CoreUser signinUser = this.getWebService().getSigninUser(request);
		if (null == data.getId()) {
			this.getCoreUserService().saveAddress(signinUser.getId(), data);
		} else {
			this.getCoreUserService().updateAddress(signinUser.getId(), data);
		}
		return JSON.getJson(true);
	}

	/**
	 * 个人中心-收货地址
	 */
	@Login(mobile = true)
	@RequestMapping(value = "/personal/address/delete/", method = RequestMethod.POST)
	public JSON personalAddressDelete(@RequestParam("id") Long id, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CoreUser signinUser = this.getWebService().getSigninUser(request);
		this.getCoreUserService().deleteAddressByUserAndId(signinUser.getId(), id);
		return JSON.getJson(true);
	}

	/**
	 * 个人中心-开票信息
	 */
	@Login(mobile = true)
	@RequestMapping(value = "/personal/invoice/", method = RequestMethod.GET)
	public String personalInvoice(HttpServletRequest request, HttpServletResponse response) throws Exception {
		CoreUser signinUser = this.getWebService().getSigninUser(request);
		List<CoreUserInvoice> datas = this.getCoreUserService().getInvoiceListByUser(signinUser.getId());
		request.setAttribute("datas", datas);
		return "web/mobile/personal/invoice";
	}

	/**
	 * 个人中心-开票信息
	 */
	@Login(mobile = true)
	@RequestMapping(value = "/personal/invoice/add/", method = RequestMethod.GET)
	public String toPersonalInvoiceAdd(Long id, String r, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("r", r);
		CoreUser signinUser = this.getWebService().getSigninUser(request);
		if (null != id) {
			CoreUserInvoice data = this.getCoreUserService().getInvoiceByUserAndId(signinUser.getId(), id);
			request.setAttribute("data", data);
		}
		return "web/mobile/personal/invoice_add";
	}

	/**
	 * 个人中心-开票信息
	 */
	@Login(mobile = true)
	@RequestMapping(value = "/personal/invoice/add/", method = RequestMethod.POST)
	public JSON personalInvoiceAdd(@ModelAttribute CoreUserInvoice data, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CoreUser signinUser = this.getWebService().getSigninUser(request);
		if (null == data.getId()) {
			this.getCoreUserService().saveInvoice(signinUser.getId(), data);
		} else {
			this.getCoreUserService().updateInvoice(signinUser.getId(), data);
		}
		return JSON.getJson(true);
	}

	/**
	 * 个人中心-开票信息
	 */
	@Login(mobile = true)
	@RequestMapping(value = "/personal/invoice/delete/", method = RequestMethod.POST)
	public JSON personalInvoiceDelete(@RequestParam("id") Long id, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CoreUser signinUser = this.getWebService().getSigninUser(request);
		this.getCoreUserService().deleteInvoiceByUserAndId(signinUser.getId(), id);
		return JSON.getJson(true);
	}

	/**
	 * 订单列表
	 */
	@Login(mobile = true)
	@RequestMapping(value = "/order/list/", method = RequestMethod.GET)
	public String orderList(Integer status, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CoreUser signinUser = this.getWebService().getSigninUser(request);
		List<CoreOrder> orderList = this.getCoreOrderService().getUserOrderListByStatus(signinUser.getId(), status);
		request.setAttribute("status", status);
		request.setAttribute("orderList", orderList);
		return "web/mobile/order/list";
	}

	/**
	 * 订单确认
	 */
	@Login(mobile = true)
	@RequestMapping(value = "/order/confirm/")
	public String orderConfirm(@RequestParam("goodsId") Long[] goodsId, @RequestParam("amount") Float[] amount,
			Long[] cartId, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CoreUser signinUser = this.getWebService().getSigninUser(request);
		Map<CoreOrder, List<CoreOrderGoods>> orderMap = this.getCoreOrderService().divideOrder(goodsId, amount);
		request.setAttribute("cartIds", cartId);
		request.setAttribute("orderMap", orderMap);
		request.setAttribute("addressList", this.getCoreUserService().getAddressListByUser(signinUser.getId()));
		request.setAttribute("invoiceList", this.getCoreUserService().getInvoiceListByUser(signinUser.getId()));
		request.setAttribute("materialList", this.getCoreUserService().getMaterialListByUser(signinUser.getId()));
		return "web/mobile/order/confirm";
	}

	/**
	 * 订单提交
	 */
	@RequestMapping(value = "/order/submit/", method = RequestMethod.POST)
	public String orderSubmit(@RequestParam("goodsId") Long[] goodsId, @RequestParam("amount") Float[] amount,
			@RequestParam("addressId") Long addressId, @RequestParam("invoiceId") Long invoiceId,
			@RequestParam("materialId") Long materialId, String about, Long[] cartId, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CoreUser signinUser = this.getWebService().getSigninUser(request);
		String mainCode = this.getCoreOrderService().createOrder(signinUser, goodsId, amount, addressId, invoiceId,
				materialId, about, cartId);
		return "redirect:/m/order/list/?mainCode=" + mainCode;
	}

	/**
	 * 商城主页
	 */
	@WebCart
	@WebMallData
	@RequestMapping(value = "/mall/{mallSign}/", method = RequestMethod.GET)
	public String mall(@PathVariable String mallSign, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CoreMall mall = this.getCoreMallService().getMallBySign(mallSign);
		request.setAttribute("mall", mall);
		List<CoreGoods> goodsList = this.getCoreGoodsService().getGoodsListByMall(mall.getId());
		request.setAttribute("goodsList", goodsList);
		List<CoreMallType> mallGoodsTypes = this.getCoreGoodsService().getTypeListByMallWithGoodsCount(mall.getId());
		request.setAttribute("mallGoodsTypes", mallGoodsTypes);
		return "web/mobile/mall/type";
	}

	/**
	 * 商城搜索
	 */
	@WebCart
	@WebMallData
	@RequestMapping(value = "/mall/search/", method = RequestMethod.GET)
	public String mallSearch(@RequestParam("keyword") String keyword, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<CoreGoods> goodsList = this.getCoreGoodsService().getGoodsListBySearch(keyword);
		request.setAttribute("goodsList", goodsList);
		request.setAttribute("keyword", keyword);
		return "web/mobile/mall/search";
	}

	/**
	 * 商品明细
	 */
	@WebCart
	@WebMallData
	@RequestMapping(value = "/mall/{goodsId}.html", method = RequestMethod.GET)
	public String goodsDetail(@PathVariable Long goodsId, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CoreGoods goods = this.getCoreGoodsService().getGoodsById(goodsId);
		List<CoreGoodsMeta> metas = this.getCoreGoodsService().getGoodsMetaListByGoods(goods.getId());
		if (null != metas) {
			Map<Long, String[]> metaMap = new HashMap<Long, String[]>();
			for (CoreGoodsMeta meta : metas) {
				Long key = meta.getTypeMetaId();
				String[] s = metaMap.get(key);
				if (null == s) {
					s = new String[] { meta.getTypeMeta(), meta.getTypeMetaValue() };
				} else {
					String v = s[1];
					v = v + "," + meta.getTypeMetaValue();
					s = new String[] { meta.getTypeMeta(), v };
				}
				metaMap.put(key, s);
			}
			request.setAttribute("metas", metaMap.values());
		}
		request.setAttribute("goods", goods);
		this.getAndSetDetailByType(goods.getTypeId(), request);
		request.setAttribute("imgs", this.getCoreGoodsService().getGoodsImgListByGoods(goods.getId()));
		return "web/mobile/mall/goods_detail";
	}

	/**
	 * 根据类别获取页面信息
	 */
	private CoreMallType getAndSetDetailByType(Long typeId, HttpServletRequest request) {
		CoreMallType type = this.getCoreMallService().getTypeById(typeId);
		request.setAttribute("type", type);
		CoreMall mall = this.getCoreMallService().getMallById(type.getMallId());
		request.setAttribute("mall", mall);
		List<CoreGoods> suggestGoodsList = this.getCoreGoodsService().getSuggestGoodsListByType(null, 8);
		request.setAttribute("suggestGoodsList", suggestGoodsList);
		List<CoreMallType> mallGoodsTypes = this.getCoreGoodsService()
				.getTypeListByMallWithGoodsCount(type.getMallId());
		request.setAttribute("mallGoodsTypes", mallGoodsTypes);
		return type;
	}

	/**
	 * 新手上路
	 */
	@WebCart
	@RequestMapping(value = "/new/", method = RequestMethod.GET)
	public String toNew(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<CoreMall> mallList = this.getCoreMallService().getMallList();
		request.setAttribute("mallList", mallList);
		return "web/mobile/new/index";
	}

	/**
	 * 购物车
	 */
	@Login(mobile = true)
	@RequestMapping(value = "/mall/cart/", method = RequestMethod.GET)
	public String toCart(HttpServletRequest request, HttpServletResponse response) throws Exception {
		CoreUser signinUser = this.getWebService().getSigninUser(request);
		CoreCart cart = this.getCoreCartService().getCartByUserWithGoods(signinUser.getId());
		request.setAttribute("cart", cart);
		return "web/mobile/mall/cart";
	}

	/**
	 * 添加到购物车
	 */
	@RequestMapping(value = "/mall/cart/add/", method = RequestMethod.POST)
	public JSON addToCart(@RequestParam("goodsId") long goodsId, Float amount, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CoreUser signinUser = this.getWebService().getSigninUser(request);
		this.getCoreCartService().addGoodsToUserCart(signinUser.getId(), goodsId, amount);
		CoreCart cart = this.getCoreCartService().getCartByUser(signinUser.getId());
		return JSON.getJson(cart);
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
	private CoreOrderService coreOrderService;

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
	private SystemCaptchaService systemCaptchaService;

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

	public SystemCaptchaService getSystemCaptchaService() {
		return systemCaptchaService;
	}

	public void setSystemCaptchaService(SystemCaptchaService systemCaptchaService) {
		this.systemCaptchaService = systemCaptchaService;
	}

	public CoreOrderService getCoreOrderService() {
		return coreOrderService;
	}

	public void setCoreOrderService(CoreOrderService coreOrderService) {
		this.coreOrderService = coreOrderService;
	}
}