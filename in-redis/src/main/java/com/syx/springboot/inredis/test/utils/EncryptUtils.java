/*
package com.syx.springboot.inredis.test.utils;




import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static java.lang.System.arraycopy;

*/
/**
 * Created by syx on 2018/7/9.
 * 加密算法
 *//*

public class EncryptUtils {


    //region MD5加密
    public static String getMD5(String message){
        String md5 = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");//创建一个算法对象
            byte[] messageByte = message.getBytes("utf-8");
            byte[] mdByte = md.digest(messageByte);
            md5 = bytesToHex(mdByte);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return md5;
    }
    //二进制转十六进制
    public static String bytesToHex(byte[] bytes){
        StringBuffer hexStr = new StringBuffer();
        int num;
        for(int i = 0;i<bytes.length; i++){
            num=bytes[i];
            if(num<0){
                num+=256;
            }if(num<16){
                hexStr.append("0");
            }
            hexStr.append(Integer.toHexString(num));
        }
        return hexStr.toString().toUpperCase();
    }
    //endregion


    //region 3Des加密

    // 密钥(DES加密和解密过程中，密钥长度都必须是8的倍数)
//    private final static String secretKey = "CFC3C3C5C0B6BAD3B5E7D7D3BFC6BCBCCFC3C3C5C0B6BAD3";
//    private final static String IV  = "00000000";//偏移量

    private final static String KEY_ALGORITHM = "DES";//算法名称
    private final static String CIPHER_ALGORITHM  = KEY_ALGORITHM + "/ECB/NoPadding";//算法模式和填充方式

    private final static String KEY_ALGORITHM_3 = "DESede";//算法名称
    private final static String CIPHER_ALGORITHM_3  = KEY_ALGORITHM_3 + "/ECB/NoPadding";//算法模式和填充方式

    // 加解密统一使用的编码方式
    private final static String encoding = "utf-8";


    */
/**
     * Des加密
     *
     * @author shaoyx
     * @date 2019/3/18 14:48
     * @param plainText 明文
     * @return java.lang.String
     **//*

    public static String encodeDes(String plainText, String key) throws Exception{
        //从原始密钥数据创建DESKeySpec对象
        DESKeySpec desKeySpec = new DESKeySpec(Hex.decode(key));
        //创建一个密匙工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM);
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);

        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);//Cipher对象实际完成加密操作
        cipher.init(Cipher.ENCRYPT_MODE,secretKey);//密匙初始化Cipher对象

        //执行加密操作
        byte[] encryptData = cipher.doFinal(Hex.decode(plainText));
        return Hex.encodeToString(encryptData);

    }
    */
/**
     * Des解密
     *
     * @author shaoyx
     * @date 2019/3/18 14:49
     * @param encryptText 密文
     * @return java.lang.String
     **//*

    public static String decodeDes(String encryptText, String key) throws Exception{
        //从原始密钥数据创建DESKeySpec对象
        DESKeySpec desKeySpec = new DESKeySpec(Hex.decode(key));
        //创建一个密匙工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM);
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);

        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE,secretKey);//密匙初始化Cipher对象

        //执行解密操作
        byte[] decryptData = cipher.doFinal(Hex.decode(encryptText));
        return Hex.encodeToString(decryptData);
    }


    */
/**
     * 3次单Des加密
     *
     * @author shaoyx
     * @date 2019/3/19 11:25
     * @param plainText
     * @param key
     * @return java.lang.String
     **//*

    public static String encode3Des(String plainText, String key) throws Exception {


        byte[] key1 = new byte[8];
        byte[] key2 = new byte[8];
        byte[] key3 = new byte[8];
        byte[] key4 = Hex.decode(key);
        arraycopy(key4,0,key1,0,8);
        arraycopy(key4,8,key2,0,8);
        arraycopy(key4,16,key3,0,8);

        String outdata;
        outdata = encodeDes(toUtf8String(plainText), Hex.encodeToString(key1));//加
        outdata = decodeDes(outdata, Hex.encodeToString(key2));//解
        outdata = encodeDes(outdata, Hex.encodeToString(key3));//加

        return outdata;
    }
    */
/**
     * 3次单Des解密
     *
     * @author shaoyx
     * @date 2019/3/19 11:26
     * @param encryptText
     * @param key
     * @return java.lang.String
     **//*

    public static String decode3Des(String encryptText, String key) throws Exception {

        byte[] key1 = new byte[8];
        byte[] key2 = new byte[8];
        byte[] key3 = new byte[8];
        byte[] key4 = Hex.decode(key);
        arraycopy(key4,16,key1,0,8);
        arraycopy(key4,8,key2,0,8);
        arraycopy(key4,0,key3,0,8);

        String outdata;
        outdata = decodeDes(encryptText, Hex.encodeToString(key1));//解
        outdata = encodeDes(outdata, Hex.encodeToString(key2));//加
        outdata = decodeDes(outdata, Hex.encodeToString(key3));//解

        return utf8ToString(outdata);
    }



    */
/**
     * 设置编码为utf8的字节数组 并填充补位
     *
     * @author shaoyx
     * @date 2019/3/19 11:29
     * @param str
     * @return java.lang.String
     **//*

    public static String toUtf8String(String str) throws Exception {

        byte[] bytes = str.getBytes(encoding);
        //补位
        byte[] bytes1 = new byte[(bytes.length+7)/8*8];
        for(byte b : bytes1){
            b = 0x00;
        }
        arraycopy(bytes,0,bytes1,0,bytes.length);
        return StringFormatUtils.toHexString(bytes1);
    }
    */
/**
     * 还原编码
     *
     * @author shaoyx
     * @date 2019/3/19 11:30
     * @param str
     * @return java.lang.String
     **//*

    public static String utf8ToString(String str) throws Exception {
        byte[] bytes = StringFormatUtils.convertHexString(str);
        return new String(bytes,encoding).replace("\0","");
    }
    //endregion

}
*/
