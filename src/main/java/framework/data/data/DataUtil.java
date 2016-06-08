package framework.data.data;

import java.security.MessageDigest;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author SUMIN
 * @version 0.1
 * @since 2016-04-18
 */
public class DataUtil {

	/**
	 * AES KEY IN SYSTEM
	 */
	public static String AES_KEY = "aes";

	/**
	 * Base64加密
	 */
	public String base64Encode(String in) {
		return Base64Encoder.encode(in);
	}

	/**
	 * Base64解密
	 */
	public String base64Decode(String in) {
		return Base64Decoder.decode(in);
	}

	/**
	 * AES加密
	 */
	public static String aesEncrypt(String in) {
		String key = System.getProperty(AES_KEY);
		if (null == key) {
			return null;
		}
		return aesEncrypt(in, key);
	}

	/**
	 * AES加密
	 */
	public static String aesEncrypt(String in, String key) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128, new SecureRandom(key.getBytes()));
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));
			byte[] bs = cipher.doFinal(in.getBytes("UTF-8"));
			return parseByte2HexStr(bs);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * AES解密
	 */
	public static String aesDecrypt(String in) {
		String key = System.getProperty(AES_KEY);
		if (null == key) {
			return null;
		}
		return aesDecrypt(in, key);
	}

	/**
	 * AES解密
	 */
	public static String aesDecrypt(String in, String key) {
		try {
			byte[] bs = parseHexStr2Byte(in);
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128, new SecureRandom(key.getBytes()));
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));
			byte[] decryptBytes = cipher.doFinal(bs);
			return new String(decryptBytes);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * MD5加密
	 */
	public static String md5(String in) {
		byte[] btInput = in.getBytes();
		MessageDigest mdInst = null;
		try {
			mdInst = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			return null;
		}
		mdInst.update(btInput);
		byte[] md = mdInst.digest();
		char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		char[] resultCharArray = new char[md.length * 2];
		int index = 0;
		for (byte b : md) {
			resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];
			resultCharArray[index++] = hexDigits[b & 0xf];
		}
		return new String(resultCharArray);
	}

	/**
	 * 是否是数字
	 */
	public static boolean isNumber(String value) {
		try {
			Long.parseLong(value);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 */
	public static String parseMessageWithParameters(String message, Object... parameters) {
		if (null == message) {
			return null;
		}
		if (null == parameters || parameters.length == 0) {
			return message;
		}
		for (int n = 0; n < parameters.length; n++) {
			message = message.replace("{"+(n+1)+"}", parameters[n].toString());
		}
		return message;
	}

	/**
	 * 2进制转16进制
	 */
	private static String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

	/**
	 * 16进制转2进制
	 */
	private static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}
}