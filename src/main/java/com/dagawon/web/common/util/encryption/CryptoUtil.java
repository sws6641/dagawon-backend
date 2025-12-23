package com.dagawon.web.common.util.encryption;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * AES256 기반 양방향 암호화 유틸
 * - DB에 저장할 때는 encrypt()
 * - DB에서 읽어올 때는 decrypt()
 */
@Component
public class CryptoUtil {

    // AES 256bit key (32byte)
    @Value("${app.crypto.secret-key}")
    private String secretKey;

    // AES 128bit IV (16byte)
    @Value("${app.crypto.iv}")
    private String iv;


    /**
     * 문자열을 AES256으로 암호화 (양방향)
     * @param str 평문
     * @return 암호문 (Base64)
     */
    public String encrypt(String str) {
        if (str == null) return null;

        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(), "AES");
            IvParameterSpec ivParam = new IvParameterSpec(iv.getBytes());

            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParam);
            return Base64.getEncoder().encodeToString(
                    cipher.doFinal(str.getBytes("UTF-8"))
            );
        } catch (Exception e) {
            throw new RuntimeException("AES 암호화 실패", e);
        }
    }

    /**
     * 암호화된 문자열을 복호화
     * @param str 암호문(Base64)
     * @return 평문
     */
    public String decrypt(String str) {
        if (str == null) return null;

        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "AES");
            IvParameterSpec ivParam = new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8));

            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParam);
            return new String(
                    cipher.doFinal(Base64.getDecoder().decode(str)),
                    "UTF-8"
            );
        } catch (Exception e) {
            throw new RuntimeException("AES 복호화 실패", e);
        }
    }
}


