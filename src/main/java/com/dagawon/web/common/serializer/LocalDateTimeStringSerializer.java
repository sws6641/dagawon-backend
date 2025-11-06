package com.dagawon.web.common.serializer;

import com.dagawon.web.common.annotation.LocalDateTimeToString;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * LocalDateTime 변수의 값이 null 일 경우 String 빈문자열("")값으로 직렬화하는 커스텀 Serializer
 * LocalDateTimeToString 어노테이션과 함께 사용되며, 지정된 패턴에 따라 날짜/시간을 문자열로 변환
 *
 * @author leh
 * @version 1.0
 * @since 2025.03.14
 */
public class LocalDateTimeStringSerializer extends JsonSerializer<LocalDateTime> implements ContextualSerializer {
    /** 날짜/시간을 문자열로 변환하는데 사용할 포맷터 */
    private DateTimeFormatter formatter;

    /**
     * 기본 패턴 "yyyy-MM-dd HH:mm:ss"를 사용하는 포맷터 초기화
     */
    public LocalDateTimeStringSerializer() {
        this.formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 지정된 패턴으로 포맷터를 초기화하는 생성자
     * 
     * @param pattern 날짜/시간 패턴 (예: "yyyy/MM/dd HH:mm")
     */
    public LocalDateTimeStringSerializer(String pattern) {
        this.formatter = DateTimeFormatter.ofPattern(pattern);
    }

    /**
     * LocalDateTime 값을 문자열로 직렬화
     * null 값은 빈문자열("")로 직렬화
     *
     * @param value    LocalDateTime 타입의 값
     * @param gen      JSON 생성을 위한 JsonGenerator
     * @param provider serialization 제공자
     * @throws IOException JSON 생성 중 발생할 수 있는 예외
     */
    @Override
    public void serialize(LocalDateTime value, JsonGenerator gen,
            SerializerProvider provider) throws IOException {
        gen.writeString(value != null ? formatter.format(value) : "");
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) {
        if (property != null) {
            LocalDateTimeToString annotation = property.getAnnotation(LocalDateTimeToString.class);
            if (annotation != null) {
                return new LocalDateTimeStringSerializer(annotation.pattern());
            }
        }
        return this;
    }
}