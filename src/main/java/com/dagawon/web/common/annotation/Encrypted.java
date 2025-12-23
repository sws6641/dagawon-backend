package com.dagawon.web.common.annotation;

import java.lang.annotation.*;

/**
 * @Encrypted
 *
 * 양방향(AES) 암호화가 필요한 엔티티 필드에 사용하는 마커 어노테이션.
 *
 * - DB 저장 시 EncryptConverter에 의해 AES 암호화되어 저장되고
 * - DB 조회 시 EncryptConverter에 의해 자동 복호화된다.
 *
 * 주의:
 * - 단방향 해시는 복호화가 불가능하므로 @Encrypted를 사용하지 않는다.
 * - 비밀번호처럼 검증만 필요한 필드는 HashConverter만 적용한다.
 *
 * 사용 예시:
 *     @Encrypted
 *     @Convert(converter = EncryptConverter.class)
 *     private String email;
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Encrypted {
}