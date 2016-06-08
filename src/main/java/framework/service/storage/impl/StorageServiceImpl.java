package framework.service.storage.impl;

import java.io.File;
import java.util.Date;

/**
 * @author SUMIN
 * @version 0.1
 * @since 2016-05-09
 */
public class StorageServiceImpl {

	/**
	 * 获取缓存目录
	 */
	public File getTempDir() {
		String tempPath = System.getProperty("java.io.tmpdir", "tmp");
		File dir = new File(tempPath + "/" + new Date().getTime() + "/");
		if (!dir.exists()) {
			dir.mkdirs();
		}
		return dir;
	}

	/**
	 * 
	 */
	private String host;

	/**
	 * 
	 */
	private String bucket;

	/**
	 * 
	 */
	private String endpoint;

	/**
	 * 
	 */
	private String accessKey;

	/**
	 * 
	 */
	private String accessSecret;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getBucket() {
		return bucket;
	}

	public void setBucket(String bucket) {
		this.bucket = bucket;
	}

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public String getAccessKey() {
		return accessKey;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	public String getAccessSecret() {
		return accessSecret;
	}

	public void setAccessSecret(String accessSecret) {
		this.accessSecret = accessSecret;
	}
}
