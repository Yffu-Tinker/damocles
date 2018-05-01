package cc.tinker.wechat;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Validate {
	
	private static String token = "tinker";

	/**
	 * 验证消息的确来自微信服务器
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @return
	 */
	public static boolean validateSignature(String signature, String timestamp, String nonce) {
		boolean success = false;
		String[] params = new String[] { token, timestamp, nonce };
		Arrays.sort(params);
		String content = params[0].concat(params[1]).concat(params[2]);
		String result = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			byte[] digest = md.digest(content.getBytes());
			result = byteArrayToString(digest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		if (result != null) {
			success = result.equals(signature.toUpperCase());
		}
		return success;
	}

	/**
	 * 将字节数组转换为十六进制字符串
	 * @param bytes
	 * @return
	 */
	private static String byteArrayToString(byte[] bytes) {
		StringBuilder string = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			string.append(byteToString(bytes[i]));
		}
		return string.toString();
	}

	/**
	 * 将字节转换为十六进制字符串
	 * @param b
	 * @return
	 */
	private static String byteToString(byte b) {
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		char[] tempArr = new char[2];
		tempArr[0] = Digit[(b >>> 4) & 0X0F];
		tempArr[1] = Digit[b & 0X0F];
		String string = new String(tempArr);
		return string;
	}

}
