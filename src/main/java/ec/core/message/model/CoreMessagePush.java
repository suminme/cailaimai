package ec.core.message.model;

import java.io.Serializable;

import framework.service.rds.annotation.Column;
import framework.service.rds.annotation.Id;
import framework.service.rds.annotation.Table;

/**
 * @author SUMIN
 * @version 0.1
 * @since 2016-05-25
 */
@SuppressWarnings("serial")
@Table(name = "core_message_push")
public class CoreMessagePush implements Serializable {
	
	/**
	 * 
	 */
	public static final int TYPE_WEB = 0;
	
	/**
	 * 
	 */
	public static final int TYPE_EMAIL = 1;
	
	/**
	 * 
	 */
	public static final int TYPE_MOBILE = 2;

	/**
	 * ID
	 */
	@Id
	private Long id;

	/**
	 * 推送类别
	 */
	@Column
	private int type;

	/**
	 * 接收人ID
	 */
	@Column
	private Long toUserId;

	/**
	 * 消息ID
	 */
	@Column
	private Long messageId;

	/**
	 * 创建时间
	 */
	@Column
	private String createTime;

	/**
	 * 接收时间
	 */
	@Column
	private String receiveTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Long getToUserId() {
		return toUserId;
	}

	public void setToUserId(Long toUserId) {
		this.toUserId = toUserId;
	}

	public Long getMessageId() {
		return messageId;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(String receiveTime) {
		this.receiveTime = receiveTime;
	}
}