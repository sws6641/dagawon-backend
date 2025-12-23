package com.dagawon.web.common.converter;

import com.dagawon.web.common.util.encryption.HashUtil;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


/**
 * 단방향 해시 Converter
 * - 저장될 때만 해시 적용
 * - 조회 시 그대로 값 유지 (해시값을 raw로 반환)
 * - 복호화 불가
 */
@Converter
@Component
@RequiredArgsConstructor
public class HashConverter implements AttributeConverter<String, String> {

    @Override
    public String convertToDatabaseColumn(String attribute) {
        return attribute == null ? null : HashUtil.sha256(attribute);
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        // 해시된 값 그대로 반환 (단방향)
        return dbData;
    }
}
