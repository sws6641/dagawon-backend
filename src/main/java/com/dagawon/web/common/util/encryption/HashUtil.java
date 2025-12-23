package com.dagawon.web.common.util.encryption;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

/**
 * 단방향 해시 유틸
 * - 복호화 불가
 * - 비밀번호, 민감 데이터 검증용
 */
// HashUtil.java 개선 제안 (Salt 추가)
public class HashUtil {
    public static String sha256(String value) {
        if (value == null) return null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            // TODO: 실제 서비스에서는 사용자별 고유 Salt를 추가하는 것을 권장합니다.
            // md.update(salt.getBytes());
            byte[] hash = md.digest(value.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hash);
        } catch (Exception e) {
            throw new RuntimeException("SHA256 해시 실패", e);
        }
    }
}
