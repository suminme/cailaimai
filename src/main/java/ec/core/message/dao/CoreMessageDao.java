package ec.core.message.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import ec.core.message.model.CoreMessage;
import ec.core.message.model.CoreMessagePush;
import ec.core.message.model.CoreMessageTemplet;
import ec.core.user.model.CoreUser;
import framework.data.date.DateUtil;
import framework.service.rds.RdsService;

/**
 * @author SUMIN
 * @version 0.1
 * @since 2016-05-26
 */
@Component
public class CoreMessageDao {

	/**
	 * 根据ID获取用户消息
	 */
	public CoreMessage getUserMessageById(Long messageId, Long userId) {
		String sql = "select a.*,c.name as creater from {TABLE} a left join "
				+ this.getRdsService().getTableName(CoreMessagePush.class) + " b on a.id=b.messageId left join "
				+ this.getRdsService().getTableName(CoreUser.class)
				+ " c on a.createrId=c.id where a.id=? and b.toUserId=?";
		CoreMessage data = this.getRdsService().get(CoreMessage.class, sql, new Object[] { messageId, userId });
		return data;
	}

	/**
	 * 将某推送消息至为已读
	 */
	public void markMessageAsRead(int type, Long messageId, Long userId) {
		String sql = "update {TABLE} set receiveTime=? where type=? and messageId=? and toUserId=?";
		this.getRdsService().update(CoreMessagePush.class, sql,
				new Object[] { DateUtil.getDateTimeString(), type, messageId, userId });
	}

	/**
	 * 获取用户Web消息列表
	 */
	public List<CoreMessage> getUserWebMessageList(Long userId, Boolean unread) {
		String sql = "select a.*,c.name as creater from {TABLE} a left join "
				+ this.getRdsService().getTableName(CoreMessagePush.class) + " b on a.id=b.messageId left join "
				+ this.getRdsService().getTableName(CoreUser.class)
				+ " c on a.createrId=c.id where b.type=? and b.toUserId=?";
		if (null != unread) {
			if (unread) {
				sql = sql + " and b.receiveTime is null";
			} else {
				sql = sql + " and b.receiveTime is not null";
			}
		}
		sql = sql + " order by a.id desc";
		List<CoreMessage> datas = this.getRdsService().gets(CoreMessage.class, sql,
				new Object[] { CoreMessagePush.TYPE_WEB, userId });
		return datas;
	}

	/**
	 * 添加一条消息
	 */
	public void saveMessage(String templetSign, Long[] toUserIds, CoreMessage message) {
		if (null == templetSign) {
			templetSign = "default";
		}
		CoreMessageTemplet templet = this.getMessageTempletBySign(templetSign);
		message.setTempletId(templet.getId());
		message.setCreateTime(DateUtil.getDateTimeString());
		this.getRdsService().save(message);
		for (Long toUserId : toUserIds) {
			if (templet.isWeb()) {
				this.saveMessagePush(CoreMessagePush.TYPE_WEB, message.getId(), toUserId);
			}
			if (templet.isEmail()) {
				this.saveMessagePush(CoreMessagePush.TYPE_EMAIL, message.getId(), toUserId);
			}
			if (templet.isMobile()) {
				this.saveMessagePush(CoreMessagePush.TYPE_MOBILE, message.getId(), toUserId);
			}
		}
	}

	/**
	 * 根据Sign查模板
	 */
	public CoreMessageTemplet getMessageTempletBySign(String sign) {
		String sql = "select * from {TABLE} where sign=?";
		CoreMessageTemplet data = this.getRdsService().get(CoreMessageTemplet.class, sql, new Object[] { sign });
		return data;
	}

	/**
	 * 保存推送记录
	 */
	private void saveMessagePush(int type, Long messageId, Long toUserId) {
		CoreMessagePush push = new CoreMessagePush();
		push.setType(type);
		push.setToUserId(toUserId);
		push.setMessageId(messageId);
		push.setCreateTime(DateUtil.getDateTimeString());
		this.getRdsService().save(push);
	}

	/**
	 * 
	 */
	@Resource
	private RdsService rdsService;

	public RdsService getRdsService() {
		return rdsService;
	}

	public void setRdsService(RdsService rdsService) {
		this.rdsService = rdsService;
	}
}