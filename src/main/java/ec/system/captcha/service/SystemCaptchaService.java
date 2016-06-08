package ec.system.captcha.service;

import java.io.IOException;
import java.util.Calendar;
import java.util.Random;

import javax.annotation.Resource;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ec.system.captcha.exception.SystemCaptchaFrequencyException;
import ec.system.captcha.exception.SystemCaptchaSendException;
import ec.system.captcha.model.SystemCaptcha;
import ec.system.config.service.SystemConfigService;
import framework.data.date.DateUtil;
import framework.data.web.util.WebUtil;
import framework.service.rds.RdsService;
import net.sf.json.JSONObject;

/**
 * @author SUMIN
 * @version 0.1
 * @since 2016-05-23
 */
@Service
@Transactional
public class SystemCaptchaService {

	/**
	 * 发送短信验证码
	 */
	public String sendSmsCaptcha(String mobile, String usedFor, String ip) throws SystemCaptchaFrequencyException {
		if (!this.canIpSendNewCaptcha(ip)) {
			throw new SystemCaptchaFrequencyException();
		}
		if (!this.canTargetSendNewCaptcha(mobile)) {
			throw new SystemCaptchaFrequencyException();
		}
		String code = this.createRandomCode();
		String tplId = this.getSystemConfigService().getConfigValue("captcha", "tplId");
		this.sendSms(mobile, tplId, new String[] { "code" }, new String[] { code });
		this.saveCaptcha(code, mobile, usedFor, ip);
		return code;
	}

	/**
	 * 判断验证码是否正确并置为已用
	 */
	public boolean checkAndMarkTargetCaptchaCode(String target, String code) {
		String sql = "select * from {TABLE} where target=? order by id desc limit 0,1";
		SystemCaptcha data = this.getRdsService().get(SystemCaptcha.class, sql, new Object[] { target });
		if (null == data || !data.getCode().equals(code) || data.isUsed()) {
			return false;
		}
		Long c = (DateUtil.getDate().getTime() - DateUtil.getDateTime(data.getInvokeTime().substring(0, 19)).getTime())
				/ 1000;
		int invalid = this.getSystemConfigService().getConfigValueInt("captcha", "invalid");
		if (c > invalid) {
			return false;
		}
		sql = "update {TABLE} set used=? where id=?";
		this.getRdsService().update(SystemCaptcha.class, sql, new Object[] { true, data.getId() });
		return true;
	}

	/**
	 * 保存验证码记录
	 */
	private void saveCaptcha(String code, String target, String usedFor, String ip) {
		SystemCaptcha captcha = new SystemCaptcha();
		captcha.setCode(code);
		captcha.setTarget(target);
		captcha.setUsed(false);
		captcha.setUsedFor(usedFor);
		captcha.setInvokerIp(ip);
		captcha.setInvokeTime(DateUtil.getDateTimeString());
		this.getRdsService().save(captcha);
	}

	/**
	 * 判断当前目标是否可以发送新验证码
	 */
	private boolean canTargetSendNewCaptcha(String target) {
		int frequency = this.getSystemConfigService().getConfigValueInt("captcha", "targetFrequency");
		if (frequency <= 0) {
			return true;
		}
		String sql = "select * from {TABLE} where target=? order by id desc limit 0,1";
		SystemCaptcha data = this.getRdsService().get(SystemCaptcha.class, sql, new Object[] { target });
		if (null == data) {
			return true;
		}
		Long c = (DateUtil.getDate().getTime() - DateUtil.getDateTime(data.getInvokeTime().substring(0, 19)).getTime())
				/ 1000;
		if (c.intValue() >= frequency) {
			return true;
		}
		return false;
	}

	/**
	 * 判断当前IP是否可以发送新验证码
	 */
	private boolean canIpSendNewCaptcha(String ip) {
		int frequency = this.getSystemConfigService().getConfigValueInt("captcha", "ipFrequency");
		if (frequency <= 0) {
			return true;
		}
		String sql = "select id from {TABLE} where invokerIp=? and invokeTime between ? and ? order by id desc limit 0,1";
		Calendar next = Calendar.getInstance();
		next.add(Calendar.SECOND, -frequency);
		SystemCaptcha data = this.getRdsService().get(SystemCaptcha.class, sql,
				new Object[] { ip, DateUtil.getDateTimeString(next.getTime()), DateUtil.getDateTimeString() });
		if (null == data) {
			return true;
		}
		return false;
	}

	/**
	 * 生成一个验证码
	 */
	public String createRandomCode() {
		int length = this.getSystemConfigService().getConfigValueInt("captcha", "length");
		return this.createRandomCode(length, true);
	}

	/**
	 * 生成一个随机验证码
	 */
	private String createRandomCode(int length, boolean onlyNumber) {
		String code = "";
		for (int n = 0; n < length; n++) {
			String b = "";
			if (onlyNumber) {
				b = String.valueOf(Math.abs(new Random().nextInt()) % 10);
			}
			code = code + b;
		}
		return code;
	}

	/**
	 * 发送短信
	 */
	public void sendSms(String mobile, String tplId, String[] fields, String[] values) {
		String tplValue = this.getTextFromFieldAndValue(fields, values);
		this.sendSms(mobile, tplId, tplValue);
	}

	/**
	 * 发送短信
	 */
	public void sendSms(String mobile, String tplId, String tplValue) {
		String apiKey = this.getSystemConfigService().getConfigValue("captcha", "apiKey");
		String url = "http://v.juhe.cn/sms/send?mobile=" + mobile + "&tpl_id=" + tplId + "&key=" + apiKey
				+ "&tpl_value=" + (null == tplValue ? "" : tplValue);
		int retry = 3;
		Document response = null;
		while (--retry >= 0) {
			try {
				response = Jsoup.connect(url).ignoreContentType(true).get();
				break;
			} catch (IOException e) {
				continue;
			}
		}
		if (null == response) {
			throw new SystemCaptchaSendException("IOException");
		}
		JSONObject json = null;
		try {
			json = JSONObject.fromObject(response.text());
		} catch (Exception e) {
			throw new SystemCaptchaSendException(response.toString());
		}
		if (json.getInt("error_code") > 0) {
			throw new SystemCaptchaSendException(json.toString());
		}
	}

	/**
	 * 解析短信内容
	 */
	private String getTextFromFieldAndValue(String[] fields, String[] values) {
		String text = "";
		if (null == fields || fields.length == 0) {
			return text;
		}
		for (int n = 0; n < fields.length; n++) {
			text = "#" + fields[n] + "#=" + values[n] + "&";
		}
		text = text.substring(0, text.length() - 1);
		return WebUtil.encodeURL(text);
	}

	/**
	 * 
	 */
	@Resource
	private RdsService rdsService;

	/**
	 * 
	 */
	@Resource
	private SystemConfigService systemConfigService;

	public RdsService getRdsService() {
		return rdsService;
	}

	public void setRdsService(RdsService rdsService) {
		this.rdsService = rdsService;
	}

	public SystemConfigService getSystemConfigService() {
		return systemConfigService;
	}

	public void setSystemConfigService(SystemConfigService systemConfigService) {
		this.systemConfigService = systemConfigService;
	}
}