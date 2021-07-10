package com.conehanor.kfcserver.util;

import org.springframework.stereotype.Component;
import sun.security.provider.SHA;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class CustomEncryptor {

    public String SHA256(final String plainText){
        return SHA(plainText, "SHA-256");
    }

    private String SHA(String plainText, String strType){
        if (plainText != null && plainText.length() > 0) {
            try {
                // SHA 加密开始
                // 创建加密对象 并傳入加密類型
                MessageDigest messageDigest = MessageDigest.getInstance(strType);
                // 传入要加密的字符串
                messageDigest.update(plainText.getBytes());
                // 得到 byte 類型结果
                byte byteBuffer[] = messageDigest.digest();

                // 將 byte 轉換爲 string
                StringBuffer strHexString = new StringBuffer();
                // 遍歷 byte buffer
                for (int i = 0; i < byteBuffer.length; i++) {
                    String hex = Integer.toHexString(0xff & byteBuffer[i]);
                    if (hex.length() == 1) {
                        strHexString.append('0');
                    }
                    strHexString.append(hex);
                }
                // 得到返回結果
                plainText = strHexString.toString();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return plainText;
    }
}
