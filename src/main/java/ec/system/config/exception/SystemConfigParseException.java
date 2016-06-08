package ec.system.config.exception;

/**
 * @author SUMIN
 * @version 0.1
 * @since 2016-05-23
 */
@SuppressWarnings("serial")
public class SystemConfigParseException extends RuntimeException {

	/**
	 * 
	 */
	public String value;

	/**
	 * 
	 */
	public SystemConfigParseException(String value) {
		this.value = value;
	}
}
