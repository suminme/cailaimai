package ec.web.admin.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ec.core.user.model.CoreUser;
import ec.core.user.service.CoreUserService;
import ec.web.mall.service.WebService;

/**
 * @author SUMIN
 * @version 0.1
 * @since 2016-05-25
 */
@Controller
@RequestMapping("/admin/user")
public class WebAdminUserController {

	/**
	 * 用户列表
	 */
	@RequestMapping(value = "/list/", method = RequestMethod.GET)
	public String list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<CoreUser> datas = this.getCoreUserService().getUserList();
		request.setAttribute("datas", datas);
		return "web/admin/user/list";
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