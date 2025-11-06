package com.dagawon.web.common.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
/**
 * String 변수의 값이 null 일 경우 String 빈문자열("")값으로 직렬화하는 커스텀 Serializer
 *
 * @author leh
 * @version 1.0
 * @since 2025.03.14
 */
public class NullToEmptyStringSerializer extends JsonSerializer<String> {
    @Override
    public void serialize(String value, JsonGenerator gen,
                          SerializerProvider provider) throws IOException {
        gen.writeString(value == null ? "" : value);
    }
}