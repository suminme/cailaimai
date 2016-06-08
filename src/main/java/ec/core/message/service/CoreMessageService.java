package ec.core.message.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ec.core.message.dao.CoreMessageDao;
import ec.core.message.model.CoreMessage;
import ec.core.message.model.CoreMessagePush;
import ec.core.user.model.CoreUser;
import ec.core.user.service.CoreUserService;

/**
 * @author SUMIN
 * @version 0.1
 * @since 2016-05-26
 */
@Service
@Transactional
public class CoreMessageService {

	/**
	 * 根据ID获取用户消息
	 */
	public CoreMessage getUserMessageById(Long messageId, Long userId) {
		CoreMessage data = this.getCoreMessageDao().getUserMessageById(messageId, userId);
		return data;
	}

	/**
	 * 获取用户未读Web消息列表
	 */
	public List<CoreMessage> getUserUnreadWebMessageList(Long userId) {
		List<CoreMessage> datas = this.getCoreMessageDao().getUserWebMessageList(userId, true);
		return datas;
	}

	/**
	 * 获取用户Web消息列表
	 */
	public List<CoreMessage> getUserWebMessageList(Long userId) {
		List<CoreMessage> datas = this.getCoreMessageDao().getUserWebMessageList(userId, null);
		return datas;
	}

	/**
	 * 发送消息
	 */
	public void sendMessage(Long[] toUserIds, String content) {
		this.sendMessage(toUserIds, content, -1l);
	}

	/**
	 * 发送消息
	 */
	public void sendMessage(Long[] toUserIds, String content, Long createrId) {
		CoreMessage message = new CoreMessage();
		message.setContent(content);
		message.setCreaterId(createrId);
		this.getCoreMessageDao().saveMessage(null, toUserIds, message);
	}

	/**
	 * 发送消息给管理员
	 */
	public void sendMessageToAdmin(String content, Long createrId) {
		List<CoreUser> adminList = this.getCoreUserService().getAdminUserList();
		Long[] toUserIds = new Long[adminList.size()];
		for (int n = 0; n < adminList.size(); n++) {
			toUserIds[n] = adminList.get(n).getId();
		}
		this.sendMessage(toUserIds, content, createrId);
	}

	/**
	 * 阅读Web消息
	 */
	public CoreMessage readWebMessage(Long messageId, Long userId) {
		CoreMessage data = this.getUserMessageById(messageId, userId);
		this.getCoreMessageDao().markMessageAsRead(CoreMessagePush.TYPE_WEB, messageId, userId);
		return data;
	}

	/**
	 * 
	 */
	@Resource
	private CoreMessageDao coreMessageDao;

	/**
	 * 
	 */
	@Resource
	private CoreUserService coreUserService;

	private CoreMessageDao getCoreMessageDao() {
		return coreMessageDao;
	}

	public CoreUserService getCoreUserService() {
		return coreUserService;
	}

	public void setCoreUserService(CoreUserService coreUserService) {
		this.coreUserService = coreUserService;
	}

	public void setCoreMessageDao(CoreMessageDao coreMessageDao) {
		this.coreMessageDao = coreMessageDao;
	}
}