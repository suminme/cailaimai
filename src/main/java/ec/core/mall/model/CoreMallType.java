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
@Table(name = "core_mall_type")
public class CoreMallType implements Serializable {

	/**
	 * 
	 */
	@Id
	private Long id;

	/**
	 * 
	 */
	@Column
	private Long mallId;

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
	private String createTime;

	/**
	 * 
	 */
	@Column
	private Long displayMetaId;

	/**
	 * 
	 */
	private int goodsCount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMallId() {
		return mallId;
	}

	public void setMallId(Long mallId) {
		this.mallId = mallId;
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

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Long getDisplayMetaId() {
		return displayMetaId;
	}

	public void setDisplayMetaId(Long displayMetaId) {
		this.displayMetaId = displayMetaId;
	}

	public int getGoodsCount() {
		return goodsCount;
	}

	public void setGoodsCount(int goodsCount) {
		this.goodsCount = goodsCount;
	}
}