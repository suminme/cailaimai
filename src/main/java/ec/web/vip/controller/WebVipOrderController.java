package ec.web.vip.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ec.core.order.model.CoreOrder;
import ec.core.order.service.CoreOrderService;
import ec.core.user.model.CoreUser;
import ec.web.mall.annotation.WebCart;
import ec.web.mall.annotation.WebMallData;
import ec.web.mall.service.WebService;

/**
 * @author SUMIN
 * @version 0.1
 * @since 2016-05-23
 */
@Controller
@RequestMapping("/vip/order")
public class WebVipOrderController {

	/**
	 * 订单列表
	 */
	@WebCart
	@WebMallData
	@RequestMapping(value = "/list/", method = RequestMethod.GET)
	public String main(Integer status, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CoreUser signinUser = this.getWebService().getSigninUser(request);
		List<CoreOrder> orderList = this.getCoreOrderService().getUserOrderListByStatus(signinUser.getId(), status);
		request.setAttribute("status", status);
		request.setAttribute("orderList", orderList);
		return "web/vip/order/list";
	}

	/**
	 * 订单明细
	 */
	@WebCart
	@WebMallData
	@RequestMapping(value = "/detail/", method = RequestMethod.GET)
	public String detail(@RequestParam("code") String code, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CoreUser signinUser = this.getWebService().getSigninUser(request);
		CoreOrder order = this.getCoreOrderService().getUserOrderByCode(signinUser.getId(), code);
		request.setAttribute("order", order);
		return "web/vip/order/detail";
	}

	/**
	 * 订单打印
	 */
	@WebMallData
	@RequestMapping(value = "/print/", method = RequestMethod.GET)
	public String print(@RequestParam("code") String code, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CoreUser signinUser = this.getWebService().getSigninUser(request);
		CoreOrder order = this.getCoreOrderService().getUserOrderByCode(signinUser.getId(), code);
		request.setAttribute("order", order);
		return "web/vip/order/print";
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