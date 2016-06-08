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
@Table(name = "core_goods")
public class CoreGoods implements Serializable {

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
	public static final int STATUS_HISTORY = -2;

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
	private Long mallId;

	/**
	 * 
	 */
	@Column
	private Long typeId;

	/**
	 * 
	 */
	@Column
	private String code;

	/**
	 * 
	 */
	@Column
	private float price;

	/**
	 * 
	 */
	private int stock;

	/**
	 * 
	 */
	@Column
	private String name;

	/**
	 * 
	 */
	@Column
	private String unit;

	/**
	 * 
	 */
	@Column
	private String cert;

	/**
	 * 
	 */
	@Column
	private String description;

	/**
	 * 
	 */
	@Column
	private String content;

	/**
	 * 
	 */
	@Column
	private boolean onsale;

	/**
	 * 
	 */
	@Column
	private boolean sample;

	/**
	 * 
	 */
	@Column
	private boolean upload;
	/**
	 * 
	 */
	@Column
	private boolean suggest;

	/**
	 * 
	 */
	@Column
	private String createTime;

	/**
	 * 
	 */
	private float totalSale;

	/**
	 * 
	 */
	private String imagePath;

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

	public Long getMallId() {
		return mallId;
	}

	public void setMallId(Long mallId) {
		this.mallId = mallId;
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getCert() {
		return cert;
	}

	public void setCert(String cert) {
		this.cert = cert;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isOnsale() {
		return onsale;
	}

	public void setOnsale(boolean onsale) {
		this.onsale = onsale;
	}

	public boolean isSample() {
		return sample;
	}

	public void setSample(boolean sample) {
		this.sample = sample;
	}

	public boolean isUpload() {
		return upload;
	}

	public void setUpload(boolean upload) {
		this.upload = upload;
	}

	public boolean isSuggest() {
		return suggest;
	}

	public void setSuggest(boolean suggest) {
		this.suggest = suggest;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public float getTotalSale() {
		return totalSale;
	}

	public void setTotalSale(float totalSale) {
		this.totalSale = totalSale;
	}
}