package yyf.common.trans;

import java.util.Base64;

public class Base64Util {
	final static Base64.Encoder encoder = Base64.getEncoder();
    final static Base64.Decoder decoder = Base64.getDecoder();
	 /**
     * 给字符串加密
     * @param text
     * @return
     */
    public static String encode(String text) {
        byte[] textByte = new byte[0];
        try {
            textByte = text.getBytes("UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        String encodedText = encoder.encodeToString(textByte);
        return encodedText;
    }

    /**
     * 将加密后的字符串进行解密
     * @param encodedText
     * @return
     */
    public static String decode(String encodedText) {
        String text = null;
        try {
            text = new String(decoder.decode(encodedText), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return text;
    }

    public static void main(String[] args) {
		String str  ="{\"password\":\"86b17ad56916a8de6c71b17c2606436a\",\"auths\":\"ROLE_POWER_USER\",\"userId\":52,\"username\":\"test1\"}";
		System.out.println(encode(str));
    }
}
