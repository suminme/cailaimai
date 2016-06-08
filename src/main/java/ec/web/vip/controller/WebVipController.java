package ec.web.vip.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ec.web.mall.annotation.WebCart;
import ec.web.mall.annotation.WebMallData;

/**
 * @author SUMIN
 * @version 0.1
 * @since 2016-05-23
 */
@Controller
@RequestMapping("/vip")
public class WebVipController {

	/**
	 * 首页
	 */
	@WebCart
	@WebMallData
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String main(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "forward:/vip/order/list/";
	}
}