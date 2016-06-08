package ec.web.admin.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ec.core.order.model.CoreOrder;
import ec.core.order.service.CoreOrderService;
import ec.core.user.model.CoreUser;
import ec.web.mall.service.WebService;
import framework.data.json.JSON;

/**
 * @author SUMIN
 * @version 0.1
 * @since 2016-05-31
 */
@Controller
@RequestMapping("/admin/order")
public class WebAdminOrderController {

	/**
	 * 订单列表
	 */
	@RequestMapping(value = "/list/", method = RequestMethod.GET)
	public String list(@RequestParam("status") int status, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<CoreOrder> datas = this.getCoreOrderService().getOrderListByStatus(status);
		request.setAttribute("datas", datas);
		return "web/admin/order/list";
	}

	/**
	 * 订单明细
	 */
	@RequestMapping(value = "/detail/", method = RequestMethod.GET)
	public String detail(@RequestParam("code") String code, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CoreOrder order = this.getCoreOrderService().getOrderByCode(code);
		request.setAttribute("order", order);
		return "web/admin/order/detail";
	}

	/**
	 * 修改订单状态
	 */
	@RequestMapping(value = "/status/update/")
	public JSON status(@RequestParam("code") String code, @RequestParam("status") int status,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		CoreUser signinUser = this.getWebService().getSigninUser(request);
		this.getCoreOrderService().updateOrderStatus(code, status, signinUser);
		return JSON.getJson(true);
	}

	/**
	 * 修改订单金额
	 */
	@RequestMapping(value = "/pay/update/")
	public JSON pay(@RequestParam("code") String code, @RequestParam("pay") float pay, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CoreUser signinUser = this.getWebService().getSigninUser(request);
		this.getCoreOrderService().updateOrderPay(code, pay, signinUser);
		return JSON.getJson(true);
	}

	/**
	 * 出库单打印
	 */
	@RequestMapping(value = "/delivery/print/{code}/", method = RequestMethod.GET)
	public String deliveryPrint(@PathVariable String code, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CoreOrder order = this.getCoreOrderService().getOrderByCode(code);
		request.setAttribute("order", order);
		return "web/admin/order/delivery_print";
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
	private CoreOrderService coreOrderService;

	public WebService getWebService() {
		return webService;
	}

	public void setWebService(WebService webService) {
		this.webService = webService;
	}

	public CoreOrderService getCoreOrderService() {
		return coreOrderService;
	}

	public void setCoreOrderService(CoreOrderService coreOrderService) {
		this.coreOrderService = coreOrderService;
	}
}