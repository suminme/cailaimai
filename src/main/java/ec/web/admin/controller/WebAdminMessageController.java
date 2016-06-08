package ec.web.admin.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ec.core.message.model.CoreMessage;
import ec.core.message.service.CoreMessageService;
import ec.core.user.model.CoreUser;
import ec.core.user.service.CoreUserService;
import ec.web.mall.service.WebService;
import framework.data.json.JSON;

/**
 * @author SUMIN
 * @version 0.1
 * @since 2016-05-25
 */
@Controller
@RequestMapping("/admin/message")
public class WebAdminMessageController {

	/**
	 * 消息列表
	 */
	@RequestMapping(value = "/list/", method = RequestMethod.GET)
	public String list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		CoreUser user = this.getWebService().getSigninUser(request);
		List<CoreMessage> datas = this.getCoreMessageService().getUserWebMessageList(user.getId());
		request.setAttribute("datas", datas);
		return "web/admin/message/list";
	}

	/**
	 * 未读消息列表
	 */
	@RequestMapping(value = "/unread/", method = RequestMethod.GET)
	public JSON unreadList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		CoreUser user = this.getWebService().getSigninUser(request);
		List<CoreMessage> datas = this.getCoreMessageService().getUserUnreadWebMessageList(user.getId());
		return JSON.getJson(datas);
	}

	/**
	 * 发送消息
	 */
	@RequestMapping(value = "/send/", method = RequestMethod.GET)
	public String toSend(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<CoreUser> userList = this.getCoreUserService().getUserList();
		request.setAttribute("userList", userList);
		return "web/admin/message/send";
	}

	/**
	 * 发送消息
	 */
	@RequestMapping(value = "/send/", method = RequestMethod.POST)
	public JSON send(@RequestParam("toUserId") Long toUserId, @RequestParam("content") String content,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		CoreUser user = this.getWebService().getSigninUser(request);
		Long[] toUserIds = null;
		if (-1 == toUserId.longValue()) {
			List<CoreUser> userList = this.getCoreUserService().getUserList();
			toUserIds = new Long[userList.size()];
			for (int n = 0; n < userList.size(); n++) {
				toUserIds[n] = userList.get(n).getId();
			}
		} else {
			toUserIds = new Long[] { toUserId };
		}
		this.getCoreMessageService().sendMessage(toUserIds, content, user.getId());
		return JSON.getJson(true);
	}

	/**
	 * 阅读消息
	 */
	@RequestMapping(value = "/read/")
	public JSON read(@RequestParam("messageId") Long messageId, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CoreUser user = this.getWebService().getSigninUser(request);
		CoreMessage message = this.getCoreMessageService().readWebMessage(messageId, user.getId());
		return JSON.getJson(message);
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

	/**
	 * 
	 */
	@Resource
	private CoreMessageService coreMessageService;

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

	public CoreMessageService getCoreMessageService() {
		return coreMessageService;
	}

	public void setCoreMessageService(CoreMessageService coreMessageService) {
		this.coreMessageService = coreMessageService;
	}
}