package ec.web.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ec.web.mall.annotation.WebMallData;

/**
 * @author SUMIN
 * @version 0.1
 * @since 2016-05-23
 */
@Controller
@RequestMapping("/admin")
public class WebAdminController {

	/**
	 * 首页
	 */
	@WebMallData
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String main(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "web/admin/main";
	}

	/**
	 * 控制台
	 */
	@RequestMapping(value = "/dashboard/", method = RequestMethod.GET)
	public String dashboard(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "web/admin/dashboard";
	}
}