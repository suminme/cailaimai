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
@Table(name = "core_message")
public class CoreMessage implements Serializable {

	/**
	 * ID
	 */
	@Id
	private Long id;

	/**
	 * 模板ID
	 */
	@Column
	private Long templetId;

	/**
	 * 消息内容
	 */
	@Column
	private String content;

	/**
	 * 创建人
	 */
	private String creater;

	/**
	 * 创建人ID
	 */
	@Column
	private Long createrId;

	/**
	 * 创建时间
	 */
	@Column
	private String createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTempletId() {
		return templetId;
	}

	public void setTempletId(Long templetId) {
		this.templetId = templetId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public Long getCreaterId() {
		return createrId;
	}

	public void setCreaterId(Long createrId) {
		this.createrId = createrId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}