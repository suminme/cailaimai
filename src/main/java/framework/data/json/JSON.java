package framework.data.json;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author SUMIN
 * @version 0.1
 * @since 2016-04-18
 */
@SuppressWarnings("serial")
public class JSON implements Serializable {

	/**
	 * 
	 */
	private Object data;

	/**
	 * 
	 */
	private String message;

	/**
	 * 
	 */
	private boolean success;

	/**
	 * 
	 */
	private int errorCode;

	/**
	 * 
	 */
	@Override
	public String toString() {
		JSONObject jSONObject = JSONObject.fromObject(this);
		if (jSONObject.has("class")) {
			jSONObject.remove("class");
		}
		if (jSONObject.getBoolean("success")) {
			jSONObject.remove("errorCode");
		}
		return jSONObject.toString();
	}

	/**
	 * 
	 */
	public void write(HttpServletResponse response) {
		String output = this.toString();
		response.setContentType("application/json;charset=UTF-8");
		try {
			response.getWriter().write(output);
			response.getWriter().flush();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 
	 */
	public static JSON getJson(Object object) {
		return new JSON(true, 0, null, object);
	}

	/**
	 * 
	 */
	public static JSON getJson(Exception e) {
		return new JSON(false, 0, e.getMessage(), null);
	}

	/**
	 * 
	 */
	public static JSON getJson(int errorCode, String message) {
		return new JSON(false, errorCode, message, null);
	}

	/**
	 * 
	 */
	public static JSON getJson(int errorCode, String message, Object object) {
		return new JSON(false, errorCode, message, object);
	}

	/**
	 * 
	 */
	@SuppressWarnings("rawtypes")
	private JSON(boolean success, int errorCode, String message, Object data) {
		if (null != data) {
			if (data instanceof List) {
				JSONArray jsonArray = new JSONArray();
				for (Object o : (List) data) {
					jsonArray.add(o);
				}
				this.data = jsonArray;
			} else if (!(data instanceof String) && !(data instanceof Number)) {
				this.data = JSONObject.fromObject(data);
			} else {
				this.data = data;
			}
		}
		this.success = success;
		this.message = message;
		this.errorCode = errorCode;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
}