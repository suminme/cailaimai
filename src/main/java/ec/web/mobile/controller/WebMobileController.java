package ec.web.mobile.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ec.core.goods.service.CoreGoodsService;
import ec.core.mall.service.CoreMallService;
import ec.core.user.model.CoreUser;
import ec.core.user.model.CoreUserAddress;
import ec.core.user.model.CoreUserInvoice;
import ec.core.user.service.CoreUserService;
import ec.system.captcha.service.SystemCaptchaService;
import ec.web.mall.annotation.WebCart;
import ec.web.mall.annotation.WebMallData;
import ec.web.mall.service.WebService;
import framework.data.web.annotation.Login;
import framework.data.web.util.WebUtil;

/**
 * @author SUMIN
 * @version 0.1
 * @since 2016-07-06
 */
@WebCart
@Controller
@RequestMapping("/m")
public class WebMobileController {

	/**
	 * 首页
	 */
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
	 * 购物车
	 */
	@Login(mobile = true)
	@RequestMapping(value = "/cart/", method = RequestMethod.GET)
	public String toCart(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "web/mobile/cart";
	}

	/**
	 * 个人中心
	 */
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
}