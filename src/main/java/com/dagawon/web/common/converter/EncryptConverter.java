package com.dagawon.web.common.converter;

import com.dagawon.web.common.util.encryption.CryptoUtil;
import com.dagawon.web.common.util.SpringContext;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.extern.slf4j.Slf4j;

/**
 * JPA AttributeConverter
 * - DB 저장 → AES 암호화
 * - DB 조회 → AES 복호화
 *
 * 주의:
 *   - Converter는 JPA가 new 로 직접 생성하기 때문에
 *     Spring @Autowired, @Value, @Component 등이 절대 작동하지 않는다.
 *   - CryptoUtil은 SpringContext.getBean() 으로 가져와야 한다.
 */
@Converter(autoApply = false)
@Slf4j // 로그 추가
public class EncryptConverter implements AttributeConverter<String, String> {

    private CryptoUtil getCryptoUtil() {
        try {
            return SpringContext.getBean(CryptoUtil.class);
        } catch (Exception e) {
            log.error("CryptoUtil 빈을 가져오는데 실패했습니다.");
            return null;
        }
    }

    @Override
    public String convertToDatabaseColumn(String attribute) {
        if (attribute == null || attribute.isEmpty()) return attribute;

        CryptoUtil util = getCryptoUtil();
        if (util == null) return attribute; // 빈이 없으면 평문 저장 (위험하지만 에러 방지)

        return util.encrypt(attribute);
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) return dbData;

        CryptoUtil util = getCryptoUtil();
        if (util == null) return dbData;

        try {
            return util.decrypt(dbData);
        } catch (Exception e) {
            // 복호화 실패 시 로그를 찍어 원인 파악 (예: 기존 평문 데이터 등)
            log.error("복호화 실패 (데이터: {}): {}", dbData, e.getMessage());
            return dbData; // 에러 대신 원본 데이터 반환하여 앱 중단 방지
        }
    }
}