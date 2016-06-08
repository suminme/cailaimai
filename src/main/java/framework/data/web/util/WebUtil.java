package framework.data.web.util;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author SUMIN
 * @version 0.1
 * @since 2016-04-18
 */
public class WebUtil {

	/**
	 * URLEncoder
	 */
	public static String encodeURL(String url) {
		String u = null;
		try {
			u = URLEncoder.encode(url, "UTF-8");
		} catch (Exception e) {

		}
		return u;
	}

	/**
	 * 获取Cookie
	 */
	public static String getCookies(HttpServletRequest request, String key) {
		Cookie[] cookies = request.getCookies();
		if (null != cookies && cookies.length > 0) {
			String value = null;
			for (int i = 0; i < cookies.length; i++) {
				Cookie newCookie = cookies[i];
				if (newCookie.getName().equals(key) && newCookie.getValue() != null
						&& !newCookie.getValue().equals("")) {
					value = newCookie.getValue();
					break;
				}
			}
			if (value != null) {
				try {
					value = URLDecoder.decode(value, "UTF-8");
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
				return value;
			}
		}
		return null;
	}

	/**
	 * 写入Cookie
	 */
	public static void setCookies(HttpServletRequest request, HttpServletResponse response, String key, String value,
			int expireIn) {
		setCookies(response, key, value, getContextPathWithSuffix(request), expireIn);
	}

	/**
	 * 写入Cookie
	 */
	public static void setCookies(HttpServletResponse response, String key, String value, String path, int expireIn) {
		try {
			value = URLEncoder.encode(value, "UTF-8");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		Cookie cookie = new Cookie(key, value);
		cookie.setMaxAge(expireIn);
		cookie.setPath(path);
		response.addCookie(cookie);
	}

	/**
	 * 清除Cookie
	 */
	public static void cleanCookies(HttpServletRequest request, HttpServletResponse response, String key) {
		cleanCookies(response, key, getContextPath(request) + "/");
	}

	/**
	 * 清除Cookie
	 */
	public static void cleanCookies(HttpServletResponse response, String key, String path) {
		Cookie cookie = new Cookie(key, null);
		cookie.setPath(path);
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}

	/**
	 * 获取ContextPath
	 */
	public static String getContextPath(HttpServletRequest request) {
		String contextPath = request.getContextPath();
		return contextPath;
	}

	/**
	 * 获取ContextPath
	 */
	public static String getContextPathWithSuffix(HttpServletRequest request) {
		String contextPath = getContextPath(request);
		return contextPath.endsWith("/") ? contextPath : contextPath + "/";
	}

	/**
	 * 获取UserAgent
	 */
	public static String getUserAgent(HttpServletRequest request) {
		String userAgent = request.getHeader("User-Agent");
		if (null == userAgent) {
			userAgent = "";
		}
		return userAgent;
	}

	/**
	 * 获取客户端IP地址
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (null != ip && !"".equals(ip) && !"unknown".equals(ip)) {
			ip = ip.contains(",") ? ip.split(",")[0] : ip;
		} else {
			ip = request.getHeader("X-Real-IP");
		}
		if (null == ip || "".equals(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 获取网站host
	 */
	public static String getHost(HttpServletRequest request) {
		String host = "";
		String contextPath = getContextPath(request);
		String requestUrl = request.getRequestURL().toString();
		Matcher matcher = Pattern.compile("(.*?)//(.*?)" + contextPath + "/(.*?)").matcher(requestUrl);
		if (matcher.matches()) {
			host = matcher.group(1) + "//" + matcher.group(2) + contextPath;
		} else {
			host = contextPath;
		}
		return host;
	}
}
