package com.dagawon.web.common.serializer;

import com.dagawon.web.common.annotation.DateFormatString;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * String 타입의 날짜를 지정된 포맷으로 변환하는 Serializer
 * DateFormatString 어노테이션과 함께 사용되며, 지정된 패턴에 따라 날짜 문자열을 변환
 *
 * @author leh
 * @version 1.0
 * @since 2025.03.14
 */
public class DateFormatStringSerializer extends JsonSerializer<String> implements ContextualSerializer {
    private String inputPattern;
    private String outputPattern;

    /**
     * 기본 생성자
     * 기본 입력 패턴: "yyyyMMdd"
     * 기본 출력 패턴: "yyyy-MM-dd"
     */
    public DateFormatStringSerializer() {
        this.inputPattern = "yyyyMMdd";
        this.outputPattern = "yyyy-MM-dd";
    }

    /**
     * 지정된 패턴으로 초기화하는 생성자
     *
     * @param inputPattern 입력 날짜 패턴 (예: "yyyyMMdd")
     * @param outputPattern 출력 날짜 패턴 (예: "yyyy-MM-dd")
     */
    public DateFormatStringSerializer(String inputPattern, String outputPattern) {
        this.inputPattern = inputPattern;
        this.outputPattern = outputPattern;
    }

    /**
     * String 타입의 날짜를 지정된 포맷으로 변환
     * null이나 빈 문자열은 빈문자열("")로 직렬화
     *
     * @param value String 타입의 날짜 값
     * @param gen JSON 생성을 위한 JsonGenerator
     * @param provider serialization 제공자
     * @throws IOException JSON 생성 중 발생할 수 있는 예외
     */
    @Override
    public void serialize(String value, JsonGenerator gen,
                          SerializerProvider provider) throws IOException {
        if (value == null || value.trim().isEmpty()) {
            gen.writeString("");
            return;
        }

        try {
            // LocalDate를 사용하여 날짜만 파싱
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern(inputPattern);
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern(outputPattern);

            LocalDate date = LocalDate.parse(value, inputFormatter);
            gen.writeString(date.format(outputFormatter));
        } catch (DateTimeParseException e) {
            // 날짜 파싱에 실패하면 원본 값을 그대로 반환하고 로그 출력
            System.out.println("Date parsing failed for value: " + value + " with pattern: " + inputPattern);
            gen.writeString(value);
        }
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) {
        if (property != null) {
            DateFormatString annotation = property.getAnnotation(DateFormatString.class);
            if (annotation != null) {
                return new DateFormatStringSerializer(annotation.inputPattern(), annotation.outputPattern());
            }
        }
        return this;
    }
}