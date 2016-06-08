package ec.core.order.model;

import java.io.Serializable;
import java.util.List;

import ec.core.mall.model.CoreMall;
import ec.core.user.model.CoreUser;
import ec.core.user.model.CoreUserAddress;
import ec.core.user.model.CoreUserInvoice;
import ec.core.user.model.CoreUserMaterial;
import framework.service.rds.annotation.Column;
import framework.service.rds.annotation.Id;
import framework.service.rds.annotation.Table;

/**
 * @author SUMIN
 * @version 0.1
 * @since 2016-05-30
 */
@SuppressWarnings("serial")
@Table(name = "core_order")
public class CoreOrder implements Serializable {

	/**
	 * 已取消订单
	 */
	public static final int STATUS_CANEL = -1;

	/**
	 * 待付款订单
	 */
	public static final int STATUS_WAITPAY = 1;

	/**
	 * 已付款订单
	 */
	public static final int STATUS_HAVEPAY = 2;

	/**
	 * 已出库订单
	 */
	public static final int STATUS_DELIVERY = 3;

	/**
	 * 已完成订单
	 */
	public static final int STATUS_COMPELETE = 4;

	/**
	 * ID
	 */
	@Id
	private Long id;

	/**
	 * 订单状态
	 */
	@Column
	private int status;

	/**
	 * 订单对应商城ID
	 */
	@Column
	private Long mallId;

	/**
	 * 订单对应商城
	 */
	private String mallName;

	/**
	 * 订单对应商城
	 */
	private CoreMall mall;

	/**
	 * 订单编号
	 */
	@Column
	private String code;

	/**
	 * 主订单编号
	 */
	@Column
	private String mainCode;

	/**
	 * 所属用户ID
	 */
	@Column
	private Long userId;

	/**
	 * 所属用户
	 */
	private String userName;

	/**
	 * 实付金额
	 */
	@Column
	private float pay;

	/**
	 * 订单总额
	 */
	@Column
	private float total;

	/**
	 * 商品金额
	 */
	@Column
	private float money;

	/**
	 * 运费
	 */
	@Column
	private float freight;

	/**
	 * 收货地址
	 */
	@Column
	private Long addressId;

	/**
	 * 发票信息
	 */
	@Column
	private Long invoiceId;

	/**
	 * 素材信息
	 */
	@Column
	private Long materialId;
	
	/**
	 * 备注
	 */
	@Column
	private String about;

	/**
	 * 创建时间
	 */
	@Column
	private String createTime;

	/**
	 * 出库时间
	 */
	@Column
	private String deliveryTime;

	/**
	 * 创建人信息
	 */
	private CoreUser user;

	/**
	 * 送货信息
	 */
	private CoreUserAddress address;

	/**
	 * 开票信息
	 */
	private CoreUserInvoice invoice;

	/**
	 * 素材信息
	 */
	private CoreUserMaterial material;
	
	/**
	 * 订单商品列表
	 */
	private List<CoreOrderGoods> orderGoodsList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMainCode() {
		return mainCode;
	}

	public void setMainCode(String mainCode) {
		this.mainCode = mainCode;
	}

	public Long getMallId() {
		return mallId;
	}

	public void setMallId(Long mallId) {
		this.mallId = mallId;
	}

	public String getMallName() {
		return mallName;
	}

	public void setMallName(String mallName) {
		this.mallName = mallName;
	}

	public CoreMall getMall() {
		return mall;
	}

	public void setMall(CoreMall mall) {
		this.mall = mall;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public float getPay() {
		return pay;
	}

	public void setPay(float pay) {
		this.pay = pay;
	}

	public float getMoney() {
		return money;
	}

	public void setMoney(float money) {
		this.money = money;
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public Long getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(Long invoiceId) {
		this.invoiceId = invoiceId;
	}

	public Long getMaterialId() {
		return materialId;
	}

	public void setMaterialId(Long materialId) {
		this.materialId = materialId;
	}

	public float getFreight() {
		return freight;
	}

	public void setFreight(float freight) {
		this.freight = freight;
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

	public String getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public CoreUser getUser() {
		return user;
	}

	public void setUser(CoreUser user) {
		this.user = user;
	}

	public CoreUserAddress getAddress() {
		return address;
	}

	public void setAddress(CoreUserAddress address) {
		this.address = address;
	}

	public CoreUserInvoice getInvoice() {
		return invoice;
	}

	public void setInvoice(CoreUserInvoice invoice) {
		this.invoice = invoice;
	}

	public CoreUserMaterial getMaterial() {
		return material;
	}

	public void setMaterial(CoreUserMaterial material) {
		this.material = material;
	}

	public List<CoreOrderGoods> getOrderGoodsList() {
		return orderGoodsList;
	}

	public void setOrderGoodsList(List<CoreOrderGoods> orderGoodsList) {
		this.orderGoodsList = orderGoodsList;
	}
}