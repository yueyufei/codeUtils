package yyf.common.trans;

import java.math.BigInteger;
import java.security.MessageDigest;

public class Md5 {
	/**
	 * @param str
	 * @param method
	 *            "MD5","SHA"
	 * @return
	 */

	public static String getMD5(String str, String method) {
		try {
			MessageDigest md = MessageDigest.getInstance(method);
			md.update(str.getBytes());
			return new BigInteger(1, md.digest()).toString(16);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) {
		String md5 = getMD5("九月九日", "SHA");
		System.out.println(md5);
	}

}
