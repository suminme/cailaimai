package ec.system.captcha.model;

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
@Table(name = "system_captcha")
public class SystemCaptcha implements Serializable {

	/**
	 * 
	 */
	@Id
	private Long id;

	/**
	 * 
	 */
	@Column
	private String code;

	/**
	 * 
	 */
	@Column
	private String target;

	/**
	 * 
	 */
	@Column
	private boolean used;

	/**
	 * 
	 */
	@Column
	private String usedFor;

	/**
	 * 
	 */
	@Column
	private String invokerIp;

	/**
	 * 
	 */
	@Column
	private String invokeTime;

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

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}

	public String getUsedFor() {
		return usedFor;
	}

	public void setUsedFor(String usedFor) {
		this.usedFor = usedFor;
	}

	public String getInvokerIp() {
		return invokerIp;
	}

	public void setInvokerIp(String invokerIp) {
		this.invokerIp = invokerIp;
	}

	public String getInvokeTime() {
		return invokeTime;
	}

	public void setInvokeTime(String invokeTime) {
		this.invokeTime = invokeTime;
	}
}