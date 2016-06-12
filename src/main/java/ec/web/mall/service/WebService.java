package ec.web.mall.service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import ec.core.user.model.CoreUser;
import ec.core.user.model.CoreUserToken;
import ec.core.user.service.CoreUserService;
import framework.data.web.util.WebUtil;
import net.sf.json.JSONObject;

/**
 * @author SUMIN
 * @version 0.1
 * @since 2016-04-27
 */
@Service
public class WebService {

	/**
	 * 
	 */
	public static final String TOKEN_IN_REQUEST = "token";

	/**
	 * 获取登录Token
	 */
	public String getToken(HttpServletRequest request) {
		String token = WebUtil.getCookies(request, TOKEN_IN_REQUEST);
		return token;
	}

	/**
	 * 写入登录信息
	 */
	public JSONObject setToken(CoreUser user, CoreUserToken token, HttpServletRequest request,
			HttpServletResponse response) {
		//WebUtil.setCookies(request, response, TOKEN_IN_REQUEST, token.getToken(), token.getExpiresIn());
		JSONObject json = new JSONObject();
		json.put("name", user.getName());
		json.put("token", token.getToken());
		json.put("path", WebUtil.getContextPathWithSuffix(request));
		json.put("expires_in", token.getExpiresIn());
		return json;
	}

	/**
	 * 移除登陆信息
	 */
	public void cleanToken(HttpServletRequest request, HttpServletResponse response) {
		WebUtil.cleanCookies(request, response, TOKEN_IN_REQUEST);
	}

	/**
	 * 获取登录用户
	 */
	public CoreUser getSigninUser(HttpServletRequest request) {
		CoreUser signinUser = (CoreUser) request.getAttribute("signinUser");
		if (null != signinUser) {
			return signinUser;
		}
		String token = this.getToken(request);
		if (null == token) {
			return null;
		}
		signinUser = this.getCoreUserService().getCoreUserTokenService().getUserByToken(token);
		request.setAttribute("signinUser", signinUser);
		return signinUser;
	}

	/**
	 * 获取完整网址
	 */
	public static String getKey(HttpServletRequest request) throws Exception {
		StringBuffer key = new StringBuffer(request.getRequestURI());
		for (Object k : request.getParameterMap().keySet()) {
			String value = request.getParameter(k.toString());
			if (null != value && !"".equals(value.trim())) {
				key = key.append((key.indexOf("?") != -1) ? "&" : "?").append(k.toString()).append("=").append(value);
			}
		}
		return key.toString();
	}

	/**
	 * 跳转到登陆界面
	 */
	public void redirectToSignin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String r = request.getRequestURL().toString();
		for (Object k : request.getParameterMap().keySet()) {
			String value = request.getParameter(k.toString());
			if (null != value && !"".equals(value.trim())) {
				r = r + ((r.indexOf("?") != -1) ? "&" : "?") + k + "=" + value;
			}
		}
		response.sendRedirect(WebUtil.getContextPath(request) + "/signin/?r=" + WebUtil.encodeURL(r.toString()));
	}

	/**
	 * 
	 */
	@Resource
	private CoreUserService coreUserService;

	public CoreUserService getCoreUserService() {
		return coreUserService;
	}

	public void setCoreUserService(CoreUserService coreUserService) {
		this.coreUserService = coreUserService;
	}
}
