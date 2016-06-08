package ec.core.order.model;

import java.io.Serializable;
import framework.service.rds.annotation.Column;
import framework.service.rds.annotation.Id;
import framework.service.rds.annotation.Table;

/**
 * @author SUMIN
 * @version 0.1
 * @since 2016-05-30
 */
@SuppressWarnings("serial")
@Table(name = "core_order_status")
public class CoreOrderStatus implements Serializable {

	/**
	 * ID
	 */
	@Id
	private Long id;

	/**
	 * 订单修改后状态
	 */
	@Column
	private int status;

	/**
	 * 订单原状态
	 */
	@Column
	private int fromStatus;

	/**
	 * 订单ID
	 */
	@Column
	private Long orderId;

	/**
	 * 操作人ID
	 */
	@Column
	private Long operatorId;

	/**
	 * 操作人名称
	 */
	private Long operatorName;

	/**
	 * 操作时间
	 */
	@Column
	private String operateTime;

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

	public int getFromStatus() {
		return fromStatus;
	}

	public void setFromStatus(int fromStatus) {
		this.fromStatus = fromStatus;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Long operatorId) {
		this.operatorId = operatorId;
	}

	public Long getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(Long operatorName) {
		this.operatorName = operatorName;
	}

	public String getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}
}