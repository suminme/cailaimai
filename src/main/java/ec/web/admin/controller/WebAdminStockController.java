package ec.web.admin.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ec.core.goods.model.CoreGoods;
import ec.core.stock.model.CoreStock;
import ec.core.stock.service.CoreStockService;
import ec.core.user.model.CoreUser;
import ec.web.mall.service.WebService;
import framework.data.json.JSON;

/**
 * @author SUMIN
 * @version 0.1
 * @since 2016-05-23
 */
@Controller
@RequestMapping("/admin/stock")
public class WebAdminStockController {

	/**
	 * 库存列表
	 */
	@RequestMapping(value = "/list/", method = RequestMethod.GET)
	public String list(String goodsCode, HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<CoreStock> datas = this.getCoreStockService().getStockList(null, goodsCode, null, null);
		request.setAttribute("datas", datas);
		return "web/admin/stock/list";
	}

	/**
	 * 库存维护
	 */
	@RequestMapping(value = "/maintenance/", method = RequestMethod.GET)
	public String toMaintenance(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<CoreGoods> goodsList = this.getCoreStockService().getMaintenanceGoodsList();
		request.setAttribute("goodsList", goodsList);
		return "web/admin/stock/form";
	}

	/**
	 * 库存维护
	 */
	@RequestMapping(value = "/maintenance/", method = RequestMethod.POST)
	public JSON maintenance(@ModelAttribute CoreStock stock, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CoreUser user = this.getWebService().getSigninUser(request);
		this.getCoreStockService().addStock(user.getId(), stock);
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
	private CoreStockService coreStockService;

	public WebService getWebService() {
		return webService;
	}

	public void setWebService(WebService webService) {
		this.webService = webService;
	}

	public CoreStockService getCoreStockService() {
		return coreStockService;
	}

	public void setCoreStockService(CoreStockService coreStockService) {
		this.coreStockService = coreStockService;
	}
}