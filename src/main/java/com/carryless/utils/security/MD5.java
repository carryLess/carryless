package com.carryless.utils.security;

import java.security.MessageDigest;

/**
 * MD5加密工具类
 * @version 1.0
 * @author  carryLess
 */
public class MD5 {

    private MD5(){}
    /**
     * 对字符串进行MD5加密
     * @param str 需要加密的字符串
     * @return 加密后的字符串
     */
    public final static String getStringMD5(String str) {
        byte[] strTemp = str.getBytes();
        return MD5.getByteArrayMD5(strTemp);
    }
    /**
     * 对byte数组进行MD5加密
     * @param source
     * @return
     */
    public final static String getByteArrayMD5(byte[] source) {
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f' };
        try {
            // 使用MD5创建MessageDigest对象
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(source);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte b = md[i];
                str[k++] = hexDigits[b >> 4 & 0xf];
                str[k++] = hexDigits[b & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }

}
