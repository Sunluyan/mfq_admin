package com.mfq.admin.web.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Md5 {

	private static final Logger logger = LoggerFactory.getLogger(Md5.class);
    final static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
    private static final String UTF8 = "utf-8";
    /**
     * Encodes a string
     * 
     * @param str String to encode
     * @return Encoded String
     */
    public static String crypt(String str) {
        return crypt(str, "UTF-8");
    }

    /**
     * encode a string with given encoding
     * 
     * @param str String to encode
     * @param charSet encoding
     * @return the encode String
     */
    public static String crypt(String str, String charSet) {
        if (str == null || str.length() == 0 || charSet == null) {
            throw new IllegalArgumentException("String or charset to encript cannot be null or zero length");
        }
        try {
            return crypt(str.getBytes(charSet));
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e);
        }
    }


    /**
     * encode bytes
     * 
     * @param source source bytes
     * @return the encode String
     */
    public static String crypt(byte[] source) {
        String s = null;

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(source);
            byte tmp[] = md.digest();
            char str[] = new char[16 * 2];
            int k = 0;
            for (int i = 0; i < 16; i++) {
                byte byte0 = tmp[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];

                str[k++] = hexDigits[byte0 & 0xf];
            }
            s = new String(str);

        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
        return s;
    }
    
	/**
	 * MD5数字签名
	 * 
	 * @param src
	 * @return
	 * @throws Exception
	 */
	public static String md5Digest(String src) {
		try {
			// 定义数字签名方法, 可用：MD5, SHA-1
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] b = md.digest(src.getBytes(UTF8));
			return byte2HexStr(b);
		} catch (Exception e) {
			logger.error("md5Digest error");
		}
		return null;
	}
	
    /**
     * 字节数组转化为大写16进制字符串
     *
     * @param b
     * @return
     */
    private static String byte2HexStr(byte[] b) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < b.length; i++) {
            String s = Integer.toHexString(b[i] & 0xFF);
            if (s.length() == 1) {
                sb.append("0");
            }
            sb.append(s.toUpperCase());
        }
        return sb.toString();
    }
}