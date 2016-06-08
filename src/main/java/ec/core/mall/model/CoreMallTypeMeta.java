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
@Table(name = "core_mall_type_meta")
public class CoreMallTypeMeta implements Serializable {

	/**
	 * 
	 */
	@Id
	private Long id;

	/**
	 * 
	 */
	@Column
	private Long typeId;

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
	private boolean required;

	/**
	 * 
	 */
	@Column
	private boolean multiple;

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

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public boolean isMultiple() {
		return multiple;
	}

	public void setMultiple(boolean multiple) {
		this.multiple = multiple;
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