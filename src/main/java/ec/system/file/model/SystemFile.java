package ec.system.file.model;

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
@Table(name = "system_file")
public class SystemFile implements Serializable {

	/**
	 * 
	 */
	@Id
	private Long id;

	/**
	 * 
	 */
	private String host;

	/**
	 * 
	 */
	@Column
	private String type;

	/**
	 * 
	 */
	@Column
	private String typeId;

	/**
	 * 
	 */
	@Column
	private String bucketKey;

	/**
	 * 
	 */
	@Column
	private String fileName;

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

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getBucketKey() {
		return bucketKey;
	}

	public void setBucketKey(String bucketKey) {
		this.bucketKey = bucketKey;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}