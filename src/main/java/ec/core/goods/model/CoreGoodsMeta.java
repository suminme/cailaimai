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
@Table(name = "core_goods_meta")
public class CoreGoodsMeta implements Serializable {

	/**
	 * 
	 */
	@Id
	private Long id;

	/**
	 * 
	 */
	@Column
	private Long goodsId;

	/**
	 * 
	 */
	@Column
	private Long typeMetaId;

	/**
	 * 
	 */
	@Column
	private Long typeMetaValueId;

	/**
	 * 
	 */
	private String typeMeta;

	/**
	 * 
	 */
	private String typeMetaValue;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public Long getTypeMetaId() {
		return typeMetaId;
	}

	public void setTypeMetaId(Long typeMetaId) {
		this.typeMetaId = typeMetaId;
	}

	public Long getTypeMetaValueId() {
		return typeMetaValueId;
	}

	public void setTypeMetaValueId(Long typeMetaValueId) {
		this.typeMetaValueId = typeMetaValueId;
	}

	public String getTypeMeta() {
		return typeMeta;
	}

	public void setTypeMeta(String typeMeta) {
		this.typeMeta = typeMeta;
	}

	public String getTypeMetaValue() {
		return typeMetaValue;
	}

	public void setTypeMetaValue(String typeMetaValue) {
		this.typeMetaValue = typeMetaValue;
	}
}