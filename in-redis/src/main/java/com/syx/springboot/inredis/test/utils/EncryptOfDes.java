/*
package com.syx.springboot.inredis.test.utils;

*/
/**
 * @author shaoyx
 * @date 2019/3/28 17:21
 * Des 算法
 **//*

public class EncryptOfDes {



    // 密钥(DES加密和解密过程中，密钥长度都必须是8的倍数)
    private final static String secretKey = "CFC3C3C5C0B6BAD3B5E7D7D3BFC6BCBCCFC3C3C5C0B6BADA";

    public static String encode3DES(String plain){
        String decode = "";
        try {
            decode = EncryptUtils.encode3Des(plain,secretKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decode;
    }
}
*/
