package com.fmi110.descrypt.util;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;

/**
 * 用于解密前端表单加密后的数据
 */
public class DesCryptUtil {
    private static String KEY       = "vienna2018";
    private static String CODE_TYPE = "UTF-8";

    /**
     * DES加密
     *
     * @param datasource
     * @return
     */
    public static String encode(String key, String datasource) {
        try {
            SecureRandom random = new SecureRandom();
            DESKeySpec   desKey = new DESKeySpec(key.getBytes(CODE_TYPE));
            //创建一个密匙工厂，然后用它把DESKeySpec转换成
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey        securekey  = keyFactory.generateSecret(desKey);
            //Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance("DES");
            //用密匙初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
            //现在，获取数据并加密
            byte[] temp = Base64.encodeBase64(cipher.doFinal(datasource.getBytes()));
            return new String(temp, "UTF-8");
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * DES加密
     *
     * @param datasource
     * @return
     */
    public static String encode(String datasource) {
        return encode(KEY, datasource);
    }


    /**
     * DES解密
     *
     * @return
     */
    public static String decode(String src) throws Exception {
        return decode(KEY, src);
    }

    public static String decode(String key ,String src) throws Exception {
        // DES算法要求有一个可信任的随机数源
        SecureRandom random = new SecureRandom();
        // 创建一个DESKeySpec对象
        DESKeySpec desKey = new DESKeySpec(key.getBytes(CODE_TYPE));
        // 创建一个密匙工厂
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        // 将DESKeySpec对象转换成SecretKey对象
        SecretKey securekey = keyFactory.generateSecret(desKey);
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance("DES");
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, random);
        // 真正开始解密操作
        return new String(cipher.doFinal(Base64.decodeBase64(src)), "UTF-8");
    }

    public String getKEY() {
        return KEY;
    }

    public void setKEY(String KEY) {
        this.KEY = KEY;
    }


//    public static void main(String[] args) throws Exception {
//        String   key   = "vienna2018";
//        String   text  = "VzUsAfL0q0I=";
////        System.out.println(DesCrypt.decode(text));
//    }
}
