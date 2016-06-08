package ec.core.cart.model;

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
@Table(name = "core_cart_goods")
public class CoreCartGoods implements Serializable {

	/**
	 * ID
	 */
	@Id
	private Long id;

	/**
	 * 所属用户ID
	 */
	@Column
	private Long userId;

	/**
	 * 商品ID
	 */
	@Column
	private Long goodsId;

	/**
	 * 数量
	 */
	@Column
	private float amount;

	/**
	 * 创建时间
	 */
	@Column
	private String createTime;

	/**
	 * 商品信息
	 */
	private CoreGoods goods;

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

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public CoreGoods getGoods() {
		return goods;
	}

	public void setGoods(CoreGoods goods) {
		this.goods = goods;
	}
}