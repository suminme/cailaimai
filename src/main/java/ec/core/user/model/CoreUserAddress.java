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
@Table(name = "core_user_address")
public class CoreUserAddress implements Serializable {

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
	private String name;

	/**
	 * 
	 */
	@Column
	private String phone;

	/**
	 * 
	 */
	@Column
	private String province;

	/**
	 * 
	 */
	@Column
	private Long provinceId;

	/**
	 * 
	 */
	@Column
	private String city;

	/**
	 * 
	 */
	@Column
	private Long cityId;
	
	/**
	 * 
	 */
	@Column
	private String area;
	
	/**
	 * 
	 */
	@Column
	private Long areaId;

	/**
	 * 
	 */
	@Column
	private String detail;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public Long getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Long getAreaId() {
		return areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
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