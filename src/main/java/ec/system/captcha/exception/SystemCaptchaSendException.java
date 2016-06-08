package ec.system.captcha.exception;

/**
 * @author SUMIN
 * @version 0.1
 * @since 2016-05-23
 */
@SuppressWarnings("serial")
public class SystemCaptchaSendException extends RuntimeException {

	/**
	 * 
	 */
	public SystemCaptchaSendException(Exception e) {
		super(e);
	}

	/**
	 * 
	 */
	public SystemCaptchaSendException(String message) {
		super(message);
	}
}
