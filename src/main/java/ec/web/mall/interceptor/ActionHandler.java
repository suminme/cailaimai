package ec.web.mall.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import framework.data.json.JSON;

/**
 * @author SUMIN
 * @version 0.1
 * @since 2016-05-23
 */
@Component
public class ActionHandler implements HandlerExceptionResolver {

	/**
	 * 日志记录
	 */
	protected Logger logger = Logger.getLogger(this.getClass());

	/**
	 * 异常处理
	 */
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handlerMethod,
			Exception e) {
		if (!WebMallInterceptor.isRestful(handlerMethod)) {
			request.setAttribute("error", e);
			return null;
		}
		JSON r = JSON.getJson(e);
		r.write(response);
		return new ModelAndView();
	}
}