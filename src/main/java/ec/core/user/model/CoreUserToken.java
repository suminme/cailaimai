package ec.core.user.model;

import java.io.Serializable;

import framework.service.rds.annotation.Column;
import framework.service.rds.annotation.Id;
import framework.service.rds.annotation.Table;

/**
 * @author sumin
 * @version 0.1
 * @since 2016-01-11
 */
@SuppressWarnings("serial")
@Table(name = "core_user_token")
public class CoreUserToken implements Serializable {

	/**
	 * 
	 */
	@Id
	private Long id;

	/**
	 * 
	 */
	@Column
	private Long userId;

	/**
	 * 
	 */
	@Column
	private String token;

	/**
	 * 
	 */
	@Column
	private int expiresIn;

	/**
	 * 
	 */
	@Column
	private boolean valid;

	/**
	 * 
	 */
	@Column
	private String createrIp;

	/**
	 * 
	 */
	@Column
	private String createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public String getCreaterIp() {
		return createrIp;
	}

	public void setCreaterIp(String createrIp) {
		this.createrIp = createrIp;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}