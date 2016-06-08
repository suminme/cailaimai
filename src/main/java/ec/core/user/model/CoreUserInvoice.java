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
@Table(name = "core_user_invoice")
public class CoreUserInvoice implements Serializable {

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
	private String tax;

	/**
	 * 
	 */
	@Column
	private String bank;

	/**
	 * 
	 */
	@Column
	private String phone;

	/**
	 * 
	 */
	@Column
	private String about;

	/**
	 * 
	 */
	@Column
	private String company;

	/**
	 * 
	 */
	@Column
	private String account;

	/**
	 * 
	 */
	@Column
	private String address;

	/**
	 * 
	 */
	@Column
	private boolean valid;

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

	public String getTax() {
		return tax;
	}

	public void setTax(String tax) {
		this.tax = tax;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}