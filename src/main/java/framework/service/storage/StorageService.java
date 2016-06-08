package framework.service.storage;

import java.io.File;

/**
 * @author SUMIN
 * @version 0.1
 * @since 2016-05-09
 */
public interface StorageService {

	/**
	 * 
	 */
	public String getHost();

	/**
	 * 
	 */
	public File getTempDir();

	/**
	 * 
	 */
	public String storageFile(File file, String key);
}