package com.k2futrue.commons.util;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author West
 * @date create in 2020/3/11
 */
public class AesUtils {

    /**
     * 密钥算法
     */
    private static final String ALGORITHM = "AES";
    /**
     * 加解密算法/工作模式/填充方式
     */
    private static final String ALGORITHM_MODE_PADDING = "AES/ECB/PKCS7Padding";

    // 增加bc 库
    static {
        if (Security.getProvider("BC") == null) {
            Security.addProvider(new BouncyCastleProvider());
        }
    }

    /**
     * 解密
     * @param bytes 密码
     * @param key key 密钥
     * @return
     * @throws Exception
     */
    public static String decrypt(byte[] bytes, String key) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM_MODE_PADDING, "BC");
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getBytes(), ALGORITHM));
        return new String(cipher.doFinal(bytes));
    }


    /**
     * AES加密
     *
     * @param data 原数据
     * @param key 密钥
     * @return
     * @throws Exception
     */
    public static byte[] encrypt(String data,String key) throws Exception {
        // 创建密码器
        Cipher cipher = Cipher.getInstance(ALGORITHM_MODE_PADDING, "BC");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), ALGORITHM);
        // 初始化
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        return cipher.doFinal(data.getBytes());
    }

}
