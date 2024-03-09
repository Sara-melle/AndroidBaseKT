package com.ajwlhzh.common.base.utils;


import com.blankj.utilcode.util.EncodeUtils;

import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.LinkedList;
import java.util.List;

import javax.crypto.Cipher;

public class RSAUtils {

    private static String KEY_RSA_TYPE = "RSA";
    private static String KEY_RSA_TYPE_ALL = "RSA/ECB/PKCS1Padding";
    private static int KEY_SIZE = 1024;//JDK方式RSA加密最大只有1024位
    private static int ENCODE_PART_SIZE = KEY_SIZE / 8;
    public static final String PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAMymRX8RRffkunnthoAnfgNNzucp8OPMJf++MB2ojnsJBp2gwMHOf4FsqQsRsbjkPnYjWSjaAswQD+ulj+pDDX7ia+K8ECXryrdJR39z0eeqFmMoZ399ywud5swOezRl4bffWrSs0SbCFkHiAbCPM0wtDWdW3VhJAuiyBXi3Tw/TAgMBAAECgYAIbqVtvzwfIwkynZXYJp3A7G4lBOEMZyH9r/bIPLoPhGdbC/+akglGP5WWLiwgznlrDA56v240EvQE+p+7wE2IT5xbYETEiVpZiX8Diq/GpG2Z0GpKfNQKPIMU/g+bxeVe4GyXgvmC9PAuCXTqDls8jk79b2TPEzYsf/k4mUKm0QJBAPgLfFVTUGKoraOPqS0J6ridiq8ZGbtK0W0HJxFHl+KnivG7pSfJ94FbObwVC3MflBWa7pSFXZvRvlqacw3YJ6sCQQDTNn+PRKRqjc7B8uVQvKzg49r4BDUdTckyTA0qBKLpUQtrQRXRV6W2z4sS9+jWLey4BgJ4wNmmyPj3eN9Q3PB5AkBILCdgOF6yZjwn1Dw4Y6NNVmGs8xO+oFVxcNxYNcmm5HMURTIX8rc2h1G0PTdYpv6SjsMOqgODvDKz8C67/4IDAkBRkoe7blhNqs0CjOvRvbzK8lVcWkulMKiW21ZuNvIwSBOv7agefMpMi2V8AvES3xPAktmbh2fICXBWK4Gb+miJAkEAiPkt5R5pcqjpvpK9mG40rWGIDNM1vZHlo7ys5ftGvjCzP7+MxuBe1SRm3n7L4GjM3pREanJdJUxflNxu7OOaQQ==";
    public static final String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDMpkV/EUX35Lp57YaAJ34DTc7nKfDjzCX/vjAdqI57CQadoMDBzn+BbKkLEbG45D52I1ko2gLMEA/rpY/qQw1+4mvivBAl68q3SUd/c9HnqhZjKGd/fcsLnebMDns0ZeG331q0rNEmwhZB4gGwjzNMLQ1nVt1YSQLosgV4t08P0wIDAQAB";

    /**
     * RSA解密数据
     * @param sourceBase64RSA
     * @return
     */
    public static String decode(String sourceBase64RSA) {
        byte[] privateBytes = EncodeUtils.base64Decode(PRIVATE_KEY);
        byte[] encodeSource = EncodeUtils.base64Decode(sourceBase64RSA);
        int encodePartLen = encodeSource.length / ENCODE_PART_SIZE;
        List<byte[]> decodeListData = new LinkedList<>();//所有解密数据
        String decodeStrResult = null;
        //私钥解密
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateBytes);
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_RSA_TYPE);
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            Cipher cipher = Cipher.getInstance(KEY_RSA_TYPE_ALL);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            int allDecodeByteLen = 0;//初始化所有被解密数据长度
            for (int i = 0; i < encodePartLen; i++) {
                byte[] tempEncodedData = new byte[ENCODE_PART_SIZE];
                System.arraycopy(encodeSource, i * ENCODE_PART_SIZE, tempEncodedData, 0, ENCODE_PART_SIZE);
                byte[] decodePartData = cipher.doFinal(tempEncodedData);
                decodeListData.add(decodePartData);
                allDecodeByteLen += decodePartData.length;
            }
            byte[] decodeResultBytes = new byte[allDecodeByteLen];
            for (int i = 0, curPosition = 0; i < encodePartLen; i++) {
                byte[] tempSorceBytes = decodeListData.get(i);
                int tempSourceBytesLen = tempSorceBytes.length;
                System.arraycopy(tempSorceBytes, 0, decodeResultBytes, curPosition, tempSourceBytesLen);
                curPosition += tempSourceBytesLen;
            }
            decodeStrResult = new String(decodeResultBytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decodeStrResult;
    }

    /**
     * RSA加密数据，使用公钥加密，接口私钥解密
     *
     * @param sourceStr
     * @return
     */
    public static String encode(String sourceStr) {
        byte[] publicBytes = EncodeUtils.base64Decode(PUBLIC_KEY);
        //公钥加密
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicBytes);
        List<byte[]> alreadyEncodeListData = new LinkedList<>();

        int maxEncodeSize = ENCODE_PART_SIZE - 11;
        String encodeBase64Result = null;
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_RSA_TYPE);
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            Cipher cipher = Cipher.getInstance(KEY_RSA_TYPE_ALL);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] sourceBytes = sourceStr.getBytes(StandardCharsets.UTF_8);
            int sourceLen = sourceBytes.length;
            for (int i = 0; i < sourceLen; i += maxEncodeSize) {
                int curPosition = sourceLen - i;
                int tempLen = curPosition;
                if (curPosition > maxEncodeSize) {
                    tempLen = maxEncodeSize;
                }
                byte[] tempBytes = new byte[tempLen];//待加密分段数据
                System.arraycopy(sourceBytes, i, tempBytes, 0, tempLen);
                byte[] tempAlreadyEncodeData = cipher.doFinal(tempBytes);
                alreadyEncodeListData.add(tempAlreadyEncodeData);
            }
            int partLen = alreadyEncodeListData.size();//加密次数

            int allEncodeLen = partLen * ENCODE_PART_SIZE;
            byte[] encodeData = new byte[allEncodeLen];//存放所有RSA分段加密数据
            for (int i = 0; i < partLen; i++) {
                byte[] tempByteList = alreadyEncodeListData.get(i);
                System.arraycopy(tempByteList, 0, encodeData, i * ENCODE_PART_SIZE, ENCODE_PART_SIZE);
            }
            encodeBase64Result = EncodeUtils.base64Encode2String(encodeData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encodeBase64Result;
    }
}

