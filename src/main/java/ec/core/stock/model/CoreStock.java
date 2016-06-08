package ec.core.stock.model;

import java.io.Serializable;
import framework.service.rds.annotation.Column;
import framework.service.rds.annotation.Id;
import framework.service.rds.annotation.Table;

/**
 * @author SUMIN
 * @version 0.1
 * @since 2016-05-25
 */
@SuppressWarnings("serial")
@Table(name = "core_stock")
public class CoreStock implements Serializable {

	/**
	 * ID
	 */
	@Id
	private Long id;

	/**
	 * 是否有效
	 */
	@Column
	private boolean valid;

	/**
	 * 商品ID
	 */
	private Long goodsId;

	/**
	 * 商品编号
	 */
	@Column
	private String goodsCode;

	/**
	 * 订单编号
	 */
	@Column
	private String orderCode;

	/**
	 * 操作数量
	 */
	@Column
	private float num;

	/**
	 * 供应商
	 */
	@Column
	private String supplier;

	/**
	 * 描述
	 */
	@Column
	private String description;

	/**
	 * 创建人名称
	 */
	private String creater;

	/**
	 * 创建人ID
	 */
	@Column
	private Long createrId;

	/**
	 * 创建时间
	 */
	@Column
	private String createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public float getNum() {
		return num;
	}

	public void setNum(float num) {
		this.num = num;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public Long getCreaterId() {
		return createrId;
	}

	public void setCreaterId(Long createrId) {
		this.createrId = createrId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}
}