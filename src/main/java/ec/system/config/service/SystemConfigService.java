package ec.system.config.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ec.system.config.exception.SystemConfigNotExistException;
import ec.system.config.exception.SystemConfigParseException;
import ec.system.config.model.SystemConfig;
import framework.service.rds.RdsService;
import net.sf.json.JSONObject;

/**
 * @author SUMIN
 * @version 0.1
 * @since 2016-05-23
 */
@Service
@Transactional
public class SystemConfigService {

	/**
	 * 获取配置
	 */
	public String getConfigValue(String type, String name) {
		String sql = "select value from {TABLE} where type=? and name=?";
		SystemConfig config = this.getRdsService().get(SystemConfig.class, sql, new Object[] { type, name });
		if (null == config) {
			throw new SystemConfigNotExistException();
		}
		return config.getValue();
	}

	/**
	 * 获取Int型配置
	 */
	public int getConfigValueInt(String type, String name) {
		String value = this.getConfigValue(type, name);
		int result = 0;
		try {
			result = Integer.parseInt(value);
		} catch (Exception e) {
			throw new SystemConfigParseException(value);
		}
		return result;
	}

	/**
	 * 获取JSONObject型配置
	 */
	public JSONObject getConfigValueJsonObject(String type, String name) {
		String value = this.getConfigValue(type, name);
		JSONObject result = null;
		try {
			result = JSONObject.fromObject(value);
		} catch (Exception e) {
			throw new SystemConfigParseException(value);
		}
		return result;
	}

	/**
	 * 获取某类配置
	 */
	public Map<String, String> getConfigMap(String type) {
		String sql = "select name,value from {TABLE} where type=?";
		Map<String, String> map = new HashMap<String, String>();
		List<SystemConfig> list = this.getRdsService().gets(SystemConfig.class, sql, new Object[] { type });
		if (null == list) {
			return map;
		}
		for (SystemConfig config : list) {
			map.put(config.getName(), config.getValue().trim());
		}
		return map;
	}

	/**
	 * 获取所有配置
	 */
	public Map<String, Map<String, String>> getConfigMap() {
		String sql = "select type,name,value from {TABLE}";
		List<SystemConfig> list = this.getRdsService().gets(SystemConfig.class, sql, null);
		Map<String, Map<String, String>> maps = new HashMap<String, Map<String, String>>();
		if (null == list) {
			return maps;
		}
		for (SystemConfig config : list) {
			Map<String, String> map = (Map<String, String>) maps.get(config.getType());
			if (null == map) {
				map = new HashMap<String, String>();
			}
			map.put(config.getName(), config.getValue().trim());
			maps.put(config.getType(), map);
		}
		return maps;
	}

	/**
	 * 系统配置文件
	 */
	private static Properties properties = null;

	/**
	 * 初始化配置文件
	 */
	static {
		try {
			properties = new Properties();
			properties.load(
					Thread.currentThread().getContextClassLoader().getResourceAsStream("/config/system.properties"));
		} catch (Exception e) {
			Logger.getLogger(SystemConfigService.class).error("properties load error", e);
		}
	}

	/**
	 * 获取系统配置文件配置
	 */
	public String getProperty(String name) {
		Object value = properties.get(name);
		if (null == value) {
			return null;
		}
		return value.toString().trim();
	}

	/**
	 * 
	 */
	@Resource
	private RdsService rdsService;

	public RdsService getRdsService() {
		return rdsService;
	}

	public void setRdsService(RdsService rdsService) {
		this.rdsService = rdsService;
	}
}