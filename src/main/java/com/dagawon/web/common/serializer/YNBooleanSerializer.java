package com.dagawon.web.common.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
/**
 * Y/N 문자열을 boolean 값으로 직렬화하는 커스텀 Serializer
 *
 * @author leh
 * @version 1.0
 * @since 2025.03.14
 */
public class YNBooleanSerializer extends JsonSerializer<String> {

    /**
     * String 값을 boolean으로 직렬화
     *
     * @param value Y/N 값을 가진 String
     * @param gen JSON 생성을 위한 JsonGenerator
     * @param provider serialization 제공자
     * @throws IOException JSON 생성 중 발생할 수 있는 예외
     */
    @Override
    public void serialize(String value, JsonGenerator gen,
                          SerializerProvider provider) throws IOException {
        gen.writeBoolean(value != null && "Y".equals(value));
    }
}
