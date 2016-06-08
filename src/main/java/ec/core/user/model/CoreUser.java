package ec.core.user.model;

import java.io.Serializable;

import framework.service.rds.annotation.Column;
import framework.service.rds.annotation.Id;
import framework.service.rds.annotation.Table;

/**
 * @author SUMIN
 * @version 0.1
 * @since 2016-05-23
 */
@SuppressWarnings("serial")
@Table(name = "core_user")
public class CoreUser implements Serializable {

	/**
	 * 
	 */
	public static final int STATUS_NORMAL = 1;

	/**
	 * 
	 */
	public static final int STATUS_DELETE = -1;

	/**
	 * 
	 */
	@Id
	private Long id;

	/**
	 * 
	 */
	@Column
	private int status;

	/**
	 * 
	 */
	@Column
	private String name;

	/**
	 * 
	 */
	@Column
	private String email;

	/**
	 * 
	 */
	@Column
	private String mobile;

	/**
	 * 
	 */
	@Column
	private String password;

	/**
	 * 
	 */
	@Column
	private String createTime;

	/**
	 * 
	 */
	@Column
	private int vipRank;

	/**
	 * 
	 */
	@Column
	private int adminRank;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public int getVipRank() {
		return vipRank;
	}

	public void setVipRank(int vipRank) {
		this.vipRank = vipRank;
	}

	public int getAdminRank() {
		return adminRank;
	}

	public void setAdminRank(int adminRank) {
		this.adminRank = adminRank;
	}
}