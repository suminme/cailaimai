package ec.core.mall.model;

import java.io.Serializable;

import framework.service.rds.annotation.Column;
import framework.service.rds.annotation.Id;
import framework.service.rds.annotation.Table;

/**
 * @author SUMIN
 * @version 0.1
 * @since 2016-05-11
 */
@SuppressWarnings("serial")
@Table(name = "core_mall")
public class CoreMall implements Serializable {

	/**
	 * 
	 */
	@Id
	private Long id;

	/**
	 * 
	 */
	@Column
	private String sign;

	/**
	 * 
	 */
	@Column
	private String name;

	/**
	 * 
	 */
	@Column
	private boolean valid;

	/**
	 * 
	 */
	@Column
	private float freight;

	/**
	 * 
	 */
	@Column
	private float freightFree;

	/**
	 * 
	 */
	@Column
	private String freightAbout;

	/**
	 * 
	 */
	@Column
	private String freightDelivery;

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

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public float getFreight() {
		return freight;
	}

	public void setFreight(float freight) {
		this.freight = freight;
	}

	public float getFreightFree() {
		return freightFree;
	}

	public void setFreightFree(float freightFree) {
		this.freightFree = freightFree;
	}

	public String getFreightAbout() {
		return freightAbout;
	}

	public void setFreightAbout(String freightAbout) {
		this.freightAbout = freightAbout;
	}

	public String getFreightDelivery() {
		return freightDelivery;
	}

	public void setFreightDelivery(String freightDelivery) {
		this.freightDelivery = freightDelivery;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}