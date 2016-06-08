package ec.core.order.model;

import java.io.Serializable;

import ec.core.goods.model.CoreGoods;
import framework.service.rds.annotation.Column;
import framework.service.rds.annotation.Id;
import framework.service.rds.annotation.Table;

/**
 * @author SUMIN
 * @version 0.1
 * @since 2016-05-16
 */
@SuppressWarnings("serial")
@Table(name = "core_order_goods")
public class CoreOrderGoods implements Serializable {

	/**
	 * ID
	 */
	@Id
	private Long id;

	/**
	 * 订单ID
	 */
	@Column
	private Long orderId;

	/**
	 * 商品ID
	 */
	@Column
	private Long goodsId;

	/**
	 * 商品编号
	 */
	@Column
	private String goodsCode;

	/**
	 * 商品数量
	 */
	@Column
	private float amount;

	/**
	 * 商品价格
	 */
	@Column
	private float price;

	/**
	 * 商品信息
	 */
	private CoreGoods goods;

	/**
	 * 商品名称
	 */
	private String goodsName;

	/**
	 * 商品名称
	 */
	private String goodsImagePath;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public CoreGoods getGoods() {
		return goods;
	}

	public void setGoods(CoreGoods goods) {
		this.goods = goods;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public String getGoodsImagePath() {
		return goodsImagePath;
	}

	public void setGoodsImagePath(String goodsImagePath) {
		this.goodsImagePath = goodsImagePath;
	}
}