package ec.web.vip.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ec.core.user.exception.CoreUserValidateException;
import ec.core.user.model.CoreUser;
import ec.core.user.model.CoreUserAddress;
import ec.core.user.model.CoreUserAddressData;
import ec.core.user.model.CoreUserInvoice;
import ec.core.user.service.CoreUserService;
import ec.web.mall.annotation.WebCart;
import ec.web.mall.annotation.WebMallData;
import ec.web.mall.service.WebService;
import framework.data.json.JSON;

/**
 * @author SUMIN
 * @version 0.1
 * @since 2016-05-23
 */
@Controller
@RequestMapping("/vip/user")
public class WebVipUserController {

	/**
	 * 我的资料
	 */
	@WebCart
	@WebMallData
	@RequestMapping(value = "/profile/", method = RequestMethod.GET)
	public String toProfile(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "web/vip/user/profile";
	}

	/**
	 * 我的资料
	 */
	@RequestMapping(value = "/profile/", method = RequestMethod.POST)
	public JSON profile(@ModelAttribute CoreUser formUser, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CoreUser signinUser = this.getWebService().getSigninUser(request);
		this.getCoreUserService().updateUserProfile(signinUser.getId(), formUser);
		return JSON.getJson(true);
	}

	/**
	 * 账户安全
	 */
	@WebCart
	@WebMallData
	@RequestMapping(value = "/security/", method = RequestMethod.GET)
	public String toSecurity(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "web/vip/user/security";
	}

	/**
	 * 账户安全
	 */
	@RequestMapping(value = "/security/", method = RequestMethod.POST)
	public JSON security(@RequestParam("oldPassword") String oldPassword, @RequestParam("password") String password,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		CoreUser signinUser = this.getWebService().getSigninUser(request);
		try {
			this.getCoreUserService().updateUserSecurity(signinUser.getId(), oldPassword, password,
					this.getWebService().getToken(request));
		} catch (CoreUserValidateException e) {
			return JSON.getJson(1, "原密码不正确");
		}
		return JSON.getJson(true);
	}

	/**
	 * 地址信息列表
	 */
	@WebCart
	@WebMallData
	@RequestMapping(value = "/address/list/", method = RequestMethod.GET)
	public String toAddressList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		CoreUser signinUser = this.getWebService().getSigninUser(request);
		List<CoreUserAddress> datas = this.getCoreUserService().getAddressListByUser(signinUser.getId());
		request.setAttribute("datas", datas);
		return "web/vip/user/address/list";
	}

	/**
	 * 地址信息添加
	 */
	@WebCart
	@WebMallData
	@RequestMapping(value = "/address/add/", method = RequestMethod.GET)
	public String toAddressAdd(boolean dialog, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (dialog) {
			return "web/vip/user/address/form_dialog";
		}
		return "web/vip/user/address/form";
	}

	/**
	 * 更新地址信息
	 */
	@WebCart
	@WebMallData
	@RequestMapping(value = "/address/update/", method = RequestMethod.GET)
	public String toAddressUpdate(@RequestParam("id") Long id, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CoreUser signinUser = this.getWebService().getSigninUser(request);
		CoreUserAddress data = this.getCoreUserService().getAddressByUserAndId(signinUser.getId(), id);
		request.setAttribute("data", data);
		return "web/vip/user/address/form";
	}

	/**
	 * 提交地址信息
	 */
	@RequestMapping(value = "/address/submit/", method = RequestMethod.POST)
	public JSON addressSubmit(@ModelAttribute CoreUserAddress data, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CoreUser signinUser = this.getWebService().getSigninUser(request);
		if (null == data.getId()) {
			this.getCoreUserService().saveAddress(signinUser.getId(), data);
		} else {
			this.getCoreUserService().updateAddress(signinUser.getId(), data);
		}
		return JSON.getJson(data);
	}

	/**
	 * 删除地址信息
	 */
	@RequestMapping(value = "/address/delete/", method = RequestMethod.GET)
	public JSON addressDelete(@RequestParam("id") Long id, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CoreUser signinUser = this.getWebService().getSigninUser(request);
		this.getCoreUserService().deleteAddressByUserAndId(signinUser.getId(), id);
		return JSON.getJson(true);
	}

	/**
	 * 收货地址数据
	 */
	@RequestMapping(value = "/address/data/", method = RequestMethod.GET)
	public JSON addressData(@RequestParam("pid") Long pid, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<CoreUserAddressData> data = this.getCoreUserService().getAddressData(pid);
		return JSON.getJson(data);
	}

	/**
	 * 发票信息列表
	 */
	@WebCart
	@WebMallData
	@RequestMapping(value = "/invoice/list/", method = RequestMethod.GET)
	public String toInvoiceList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		CoreUser signinUser = this.getWebService().getSigninUser(request);
		List<CoreUserInvoice> datas = this.getCoreUserService().getInvoiceListByUser(signinUser.getId());
		request.setAttribute("datas", datas);
		return "web/vip/user/invoice/list";
	}

	/**
	 * 发票信息添加
	 */
	@WebCart
	@WebMallData
	@RequestMapping(value = "/invoice/add/", method = RequestMethod.GET)
	public String toInvoiceAdd(boolean dialog, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (dialog) {
			return "web/vip/user/invoice/form_dialog";
		}
		return "web/vip/user/invoice/form";
	}

	/**
	 * 更新发票信息
	 */
	@WebCart
	@WebMallData
	@RequestMapping(value = "/invoice/update/", method = RequestMethod.GET)
	public String toInvoiceUpdate(@RequestParam("id") Long id, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CoreUser signinUser = this.getWebService().getSigninUser(request);
		CoreUserInvoice data = this.getCoreUserService().getInvoiceByUserAndId(signinUser.getId(), id);
		request.setAttribute("data", data);
		return "web/vip/user/invoice/form";
	}

	/**
	 * 提交发票信息
	 */
	@RequestMapping(value = "/invoice/submit/", method = RequestMethod.POST)
	public JSON invoiceSubmit(@ModelAttribute CoreUserInvoice data, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CoreUser signinUser = this.getWebService().getSigninUser(request);
		if (null == data.getId()) {
			this.getCoreUserService().saveInvoice(signinUser.getId(), data);
		} else {
			this.getCoreUserService().updateInvoice(signinUser.getId(), data);
		}
		return JSON.getJson(data);
	}

	/**
	 * 删除发票信息
	 */
	@RequestMapping(value = "/invoice/delete/", method = RequestMethod.GET)
	public JSON invoiceDelete(@RequestParam("id") Long id, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CoreUser signinUser = this.getWebService().getSigninUser(request);
		this.getCoreUserService().deleteInvoiceByUserAndId(signinUser.getId(), id);
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
	private CoreUserService coreUserService;

	public WebService getWebService() {
		return webService;
	}

	public void setWebService(WebService webService) {
		this.webService = webService;
	}

	public CoreUserService getCoreUserService() {
		return coreUserService;
	}

	public void setCoreUserService(CoreUserService coreUserService) {
		this.coreUserService = coreUserService;
	}
}