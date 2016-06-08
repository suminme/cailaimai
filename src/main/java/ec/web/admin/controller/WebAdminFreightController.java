package ec.web.admin.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ec.core.mall.model.CoreMall;
import ec.core.mall.service.CoreMallService;
import ec.web.mall.service.WebService;
import framework.data.json.JSON;

/**
 * @author SUMIN
 * @version 0.1
 * @since 2016-05-23
 */
@Controller
@RequestMapping("/admin/freight")
public class WebAdminFreightController {

	/**
	 * 运费模板列表
	 */
	@RequestMapping(value = "/list/", method = RequestMethod.GET)
	public String list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<CoreMall> datas = this.getCoreMallService().getMallList();
		request.setAttribute("datas", datas);
		return "web/admin/freight/list";
	}

	/**
	 * 更新运费模板
	 */
	@RequestMapping(value = "/update/", method = RequestMethod.GET)
	public String update(@RequestParam("id") Long id, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CoreMall data = this.getCoreMallService().getMallById(id);
		request.setAttribute("data", data);
		return "web/admin/freight/form";
	}

	/**
	 * 运费模板提交
	 */
	@RequestMapping(value = "/update/", method = RequestMethod.POST)
	public JSON add(@ModelAttribute CoreMall mall, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		this.getCoreMallService().updateMallFreight(mall);
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
}