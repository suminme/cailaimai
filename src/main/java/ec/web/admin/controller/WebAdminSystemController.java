package ec.web.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author SUMIN
 * @version 0.1
 * @since 2016-05-23
 */
@Controller
@RequestMapping("/admin/system")
public class WebAdminSystemController {

	/**
	 * 首页
	 */
	@RequestMapping(value = "/config/", method = RequestMethod.GET)
	public String main(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "web/admin/system/config";
	}
}