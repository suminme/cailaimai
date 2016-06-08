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
@Table(name = "core_message_templet")
public class CoreMessageTemplet implements Serializable {

	/**
	 * ID
	 */
	@Id
	private Long id;

	/**
	 * 模板标识
	 */
	@Column
	private String sign;

	/**
	 * Web
	 */
	@Column
	private boolean web;

	/**
	 * Email
	 */
	@Column
	private boolean email;

	/**
	 * Mobile
	 */
	@Column
	private boolean mobile;

	/**
	 * 短信模板ID
	 */
	@Column
	private String smsTpl;

	/**
	 * 模板内容
	 */
	@Column
	private String content;

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

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public boolean isWeb() {
		return web;
	}

	public void setWeb(boolean web) {
		this.web = web;
	}

	public boolean isEmail() {
		return email;
	}

	public void setEmail(boolean email) {
		this.email = email;
	}

	public boolean isMobile() {
		return mobile;
	}

	public void setMobile(boolean mobile) {
		this.mobile = mobile;
	}

	public String getSmsTpl() {
		return smsTpl;
	}

	public void setSmsTpl(String smsTpl) {
		this.smsTpl = smsTpl;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}