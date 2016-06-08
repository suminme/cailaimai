package ec.web.admin.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import ec.core.user.model.CoreUser;
import ec.web.mall.service.WebService;

/**
 * @author SUMIN
 * @version 0.1
 * @since 2016-05-23
 */
public class WebAdminInterceptor implements HandlerInterceptor {

	/**
	 * 预拦截
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handlerMethod)
			throws Exception {
		CoreUser signinUser = this.getWebService().getSigninUser(request);
		if (null == signinUser) {
			this.getWebService().redirectToSignin(request, response);
			return false;
		}
		if (signinUser.getAdminRank() <= 0) {
			return false;
		}
		return true;
	}

	/**
	 * 拦截中
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handlerMethod,
			ModelAndView modelAndView) throws Exception {

	}

	/**
	 * 拦截后
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handlerMethod,
			Exception e) throws Exception {

	}

	/**
	 * 
	 */
	@Resource
	private WebService webService;

	public WebService getWebService() {
		return webService;
	}

	public void setWebService(WebService webService) {
		this.webService = webService;
	}
}