package ec.web.mall.controller;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ec.core.cart.service.CoreCartService;
import ec.core.goods.service.CoreGoodsService;
import ec.core.mall.service.CoreMallService;
import ec.core.order.model.CoreOrder;
import ec.core.order.model.CoreOrderGoods;
import ec.core.order.service.CoreOrderService;
import ec.core.user.model.CoreUser;
import ec.core.user.model.CoreUserAddress;
import ec.core.user.model.CoreUserAddressData;
import ec.core.user.model.CoreUserInvoice;
import ec.core.user.model.CoreUserMaterial;
import ec.core.user.service.CoreUserService;
import ec.system.file.model.SystemFile;
import ec.system.file.service.SystemFileService;
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
@RequestMapping("/mall/order")
public class WebMallOrderController {

	/**
	 * 订单确认
	 */
	@WebCart
	@WebMallData
	@RequestMapping(value = "/confirm/", method = RequestMethod.POST)
	public String confirm(@RequestParam("goodsId") Long[] goodsId, @RequestParam("amount") Float[] amount,
			Long[] cartId, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CoreUser signinUser = this.getWebService().getSigninUser(request);
		Map<CoreOrder, List<CoreOrderGoods>> orderMap = this.getCoreOrderService().divideOrder(goodsId, amount);
		request.setAttribute("cartIds", cartId);
		request.setAttribute("orderMap", orderMap);
		request.setAttribute("addressList", this.getCoreUserService().getAddressListByUser(signinUser.getId()));
		request.setAttribute("invoiceList", this.getCoreUserService().getInvoiceListByUser(signinUser.getId()));
		request.setAttribute("materialList", this.getCoreUserService().getMaterialListByUser(signinUser.getId()));
		return "web/mall/order";
	}

	/**
	 * 订单提交
	 */
	@WebCart
	@WebMallData
	@RequestMapping(value = "/submit/", method = RequestMethod.POST)
	public String submit(@RequestParam("goodsId") Long[] goodsId, @RequestParam("amount") Float[] amount,
			@RequestParam("addressId") Long addressId, @RequestParam("invoiceId") Long invoiceId,
			@RequestParam("materialId") Long materialId, String about, Long[] cartId, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CoreUser signinUser = this.getWebService().getSigninUser(request);
		String mainCode = this.getCoreOrderService().createOrder(signinUser, goodsId, amount, addressId, invoiceId,
				materialId, about, cartId);
		return "redirect:/mall/order/success/?mainCode=" + mainCode;
	}

	/**
	 * 订单提交成功
	 */
	@WebCart
	@WebMallData
	@RequestMapping(value = "/success/", method = RequestMethod.GET)
	public String success(@RequestParam("mainCode") String mainCode, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CoreUser signinUser = this.getWebService().getSigninUser(request);
		List<CoreOrder> orderList = this.getCoreOrderService().getUserOrderListByMainCode(signinUser.getId(), mainCode);
		request.setAttribute("orderList", orderList);
		return "web/mall/order_success";
	}

	/**
	 * 添加素材
	 */
	@RequestMapping(value = "/material/add/", method = RequestMethod.GET)
	public String materialAdd(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "web/mall/material_add";
	}

	/**
	 * 添加素材
	 */
	@RequestMapping(value = "/material/add/", method = RequestMethod.POST)
	public String materialAdd(@ModelAttribute CoreUserMaterial material, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CoreUser signinUser = this.getWebService().getSigninUser(request);
		File uploadFile = this.getSystemFileService().getFileFromRequest(request, 0);
		SystemFile fileModel = this.getSystemFileService().fileUpload("material", String.valueOf(signinUser.getId()),
				uploadFile.getName(), uploadFile);
		material.setFileId(fileModel.getId());
		this.getCoreUserService().saveMaterial(signinUser.getId(), material);
		request.setAttribute("material", material);
		return "web/mall/material_add";
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
	 * 添加收货地址
	 */
	@RequestMapping(value = "/address/add/", method = RequestMethod.GET)
	public String addressAdd(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "web/mall/address_add";
	}

	/**
	 * 添加收货地址
	 */
	@RequestMapping(value = "/address/add/", method = RequestMethod.POST)
	public JSON addressAdd(@ModelAttribute CoreUserAddress data, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CoreUser signinUser = this.getWebService().getSigninUser(request);
		this.getCoreUserService().saveAddress(signinUser.getId(), data);
		return JSON.getJson(data);
	}

	/**
	 * 添加发票信息
	 */
	@RequestMapping(value = "/invoice/add/", method = RequestMethod.GET)
	public String invoiceAdd(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "web/mall/invoice_add";
	}

	/**
	 * 添加发票信息
	 */
	@RequestMapping(value = "/invoice/add/", method = RequestMethod.POST)
	public JSON invoiceAdd(@ModelAttribute CoreUserInvoice data, HttpServletRequest request,
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
	 * 
	 */
	@Resource
	private WebService webService;

	/**
	 * 
	 */
	@Resource
	private CoreUserService coreUserService;

	/**
	 * 
	 */
	@Resource
	private CoreCartService coreCartService;

	/**
	 * 
	 */
	@Resource
	private CoreMallService coreMallService;

	/**
	 * 
	 */
	@Resource
	private CoreOrderService coreOrderService;

	/**
	 * 
	 */
	@Resource
	private CoreGoodsService coreGoodsService;

	/**
	 * 
	 */
	@Resource
	private SystemFileService systemFileService;

	public WebService getWebService() {
		return webService;
	}

	public void setWebService(WebService webService) {
		this.webService = webService;
	}

	public CoreCartService getCoreCartService() {
		return coreCartService;
	}

	public void setCoreCartService(CoreCartService coreCartService) {
		this.coreCartService = coreCartService;
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

	public CoreOrderService getCoreOrderService() {
		return coreOrderService;
	}

	public void setCoreOrderService(CoreOrderService coreOrderService) {
		this.coreOrderService = coreOrderService;
	}

	public CoreGoodsService getCoreGoodsService() {
		return coreGoodsService;
	}

	public void setCoreGoodsService(CoreGoodsService coreGoodsService) {
		this.coreGoodsService = coreGoodsService;
	}

	public SystemFileService getSystemFileService() {
		return systemFileService;
	}

	public void setSystemFileService(SystemFileService systemFileService) {
		this.systemFileService = systemFileService;
	}
}