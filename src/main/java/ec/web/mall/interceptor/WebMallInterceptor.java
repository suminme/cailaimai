package ec.web.mall.interceptor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import ec.core.cart.model.CoreCart;
import ec.core.cart.service.CoreCartService;
import ec.core.mall.service.CoreMallService;
import ec.core.user.model.CoreUser;
import ec.system.config.service.SystemConfigService;
import ec.web.mall.annotation.WebCart;
import ec.web.mall.annotation.WebMallData;
import ec.web.mall.service.WebService;
import framework.data.json.JSON;
import framework.data.web.annotation.Login;
import framework.data.web.util.WebUtil;
import framework.service.storage.StorageService;

/**
 * @author SUMIN
 * @version 0.1
 * @since 2016-05-23
 */
public class WebMallInterceptor implements HandlerInterceptor {

	/**
	 * 预拦截
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handlerMethod)
			throws Exception {
		this.configEncode(request, response);
		if (!this.hasAnnotation(handlerMethod, JSON.class)) {
			this.configEnvironment(request);
		}
		if (null == this.getWebService().getSigninUser(request) && this.hasAnnotation(handlerMethod, Login.class)) {
			this.getWebService().redirectToSignin(request, response);
			return false;
		}
		return true;
	}

	/**
	 * 拦截前
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handlerMethod,
			ModelAndView modelAndView) throws Exception {
		if (isRestful(handlerMethod)) {
			this.dealRestful(response, handlerMethod, modelAndView);
		}
		this.getAndSetInfo(handlerMethod, request);
	}

	/**
	 * 拦截后
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handlerMethod,
			Exception e) throws Exception {

	}

	/**
	 * 是否是Restful请求
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isRestful(Object handlerMethod) {
		if (null == handlerMethod || !(handlerMethod instanceof HandlerMethod)
				|| null == ((HandlerMethod) handlerMethod).getMethod()) {
			return false;
		}
		Class c = ((HandlerMethod) handlerMethod).getMethod().getReturnType();
		return c == JSON.class ? true : false;
	}

	/**
	 * 处理Restful请求
	 */
	public void dealRestful(HttpServletResponse response, Object handlerMethod, ModelAndView modelAndView) {
		if (null == modelAndView || null == modelAndView.getModelMap()) {
			return;
		}
		for (Object o : modelAndView.getModelMap().values()) {
			if (o instanceof JSON) {
				((JSON) o).write(response);
				modelAndView.clear();
			}
		}
	}

	/**
	 * 设置数据
	 */
	private void getAndSetInfo(Object handlerMethod, HttpServletRequest request) {
		if (this.hasAnnotation(handlerMethod, WebMallData.class)) {
			request.setAttribute("mallList", this.getCoreMallService().getMallList());
			request.setAttribute("typeMap", this.getCoreMallService().getTypeMap());
		}
		if (this.hasAnnotation(handlerMethod, WebMallData.class)) {
			request.setAttribute("typeMetaMap", this.getCoreMallService().getTypeMetaMap());
			request.setAttribute("typeMetaValueMap", this.getCoreMallService().getTypeMetaValueMap());
		}
		if (this.hasAnnotation(handlerMethod, WebCart.class)) {
			this.getAndSetCartInfo(request);
		}
	}

	/**
	 * 设置购物车数据
	 */
	private void getAndSetCartInfo(HttpServletRequest request) {
		CoreUser signinUser = this.getWebService().getSigninUser(request);
		CoreCart cart = null;
		if (null != signinUser) {
			cart = this.getCoreCartService().getCartByUser(signinUser.getId());
		}
		if (null == cart) {
			cart = new CoreCart();
		}
		request.setAttribute("cart", cart);
	}

	/**
	 * 配置字符
	 */
	public void configEncode(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
	}

	/**
	 * 环境配置
	 */
	public void configEnvironment(HttpServletRequest request) {
		Map<String, String> env = new HashMap<String, String>();
		String host = this.getSystemConfigService().getProperty("host");
		if (null == host || "".equals(host)) {
			host = WebUtil.getHost(request);
		}
		if (host.startsWith("http://")) {
			host = host.replace("http://", "//");
		}
		env.put("host", host);
		String staticHost = this.getSystemConfigService().getProperty("static.host");
		if (null == staticHost || "".equals(staticHost)) {
			String staticPath = this.getSystemConfigService().getProperty("static.path");
			staticHost = host + "/" + staticPath;
		}
		String staticVersion = this.getSystemConfigService().getProperty("static.version");
		staticHost = staticHost + "/" + staticVersion;
		env.put("staticHost", staticHost);
		env.put("storageHost", this.getStorageService().getHost());
		request.setAttribute("env", env);
		Map<String, Map<String, String>> config = this.getSystemConfigService().getConfigMap();
		request.setAttribute("config", config);
	}

	/**
	 * 判断是否有某注解
	 */
	@SuppressWarnings("unchecked")
	private <T> boolean hasAnnotation(Object handlerMethod, Class<T> annotations) {
		if (null == handlerMethod || !(handlerMethod instanceof HandlerMethod)
				|| null == ((HandlerMethod) handlerMethod).getMethod()) {
			return false;
		}
		Method method = ((HandlerMethod) handlerMethod).getMethod();
		Annotation t = method.getAnnotation((Class<Annotation>) annotations);
		if (null == t) {
			t = method.getDeclaringClass().getAnnotation((Class<Annotation>) annotations);
		}
		if (null == t) {
			return false;
		}
		return true;
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
	private StorageService storageService;

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
	private SystemConfigService systemConfigService;

	public WebService getWebService() {
		return webService;
	}

	public void setWebService(WebService webService) {
		this.webService = webService;
	}

	public StorageService getStorageService() {
		return storageService;
	}

	public void setStorageService(StorageService storageService) {
		this.storageService = storageService;
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

	public SystemConfigService getSystemConfigService() {
		return systemConfigService;
	}

	public void setSystemConfigService(SystemConfigService systemConfigService) {
		this.systemConfigService = systemConfigService;
	}
}