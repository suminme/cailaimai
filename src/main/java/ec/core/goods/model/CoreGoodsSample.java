package ec.core.goods.model;

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
@Table(name = "core_goods_sample")
public class CoreGoodsSample implements Serializable {

	/**
	 * 
	 */
	public final static int STATUS_APPLY = 0;

	/**
	 * 
	 */
	public final static int STATUS_APPROVE = 1;

	/**
	 * 
	 */
	public final static int STATUS_REFUSE = -1;

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
	private Long userId;

	/**
	 * 
	 */
	@Column
	private Long goodsId;

	/**
	 * 
	 */
	@Column
	private String address;

	/**
	 * 
	 */
	@Column
	private Long addressId;

	/**
	 * 
	 */
	@Column
	private String about;

	/**
	 * 
	 */
	@Column
	private String reason;

	/**
	 * 
	 */
	@Column
	private String createTime;

	/**
	 * 
	 */
	private String goodsName;

	/**
	 * 
	 */
	private String userMobile;

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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}
}
