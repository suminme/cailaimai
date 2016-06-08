package framework.service.storage.impl.aliyun;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;

import framework.service.storage.StorageService;

/**
 * @author SUMIN
 * @version 0.1
 * @since 2016-05-09
 */
public class StorageServiceImpl extends framework.service.storage.impl.StorageServiceImpl implements StorageService {

	/**
	 * 
	 */
	private OSSClient oss;

	/**
	 * 
	 */
	@Override
	public String storageFile(File file, String key) {
		String keyName = this.checkAndCreateKey(key);
		InputStream content = null;
		try {
			content = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		ObjectMetadata meta = new ObjectMetadata();
		meta.setContentLength(file.length());
		this.getOSSClient().putObject(this.getBucket(), keyName, content, meta);
		return key;
	}

	/**
	 * 
	 */
	private String checkAndCreateKey(String key) {
		key = key.replace("\\", "/");
		if (key.startsWith("/")) {
			key = key.substring(1, key.length());
		}
		return key;
	}

	/**
	 * 
	 */
	private OSSClient getOSSClient() {
		if (null == this.oss) {
			this.oss = new OSSClient(this.getEndpoint(), this.getAccessKey(), this.getAccessSecret());
		}
		return this.oss;
	}
}