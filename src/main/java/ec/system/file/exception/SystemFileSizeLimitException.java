package ec.system.file.exception;

/**
 * @author SUMIN
 * @version 0.1
 * @since 2016-05-23
 */
@SuppressWarnings("serial")
public class SystemFileSizeLimitException extends Exception {

	/**
	 * 
	 */
	public int maxMB;

	/**
	 * 
	 */
	public SystemFileSizeLimitException(int maxMB) {
		this.maxMB = maxMB;
	}
}
