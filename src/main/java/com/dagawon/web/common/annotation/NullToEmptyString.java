package com.dagawon.web.common.annotation;

import com.dagawon.web.common.serializer.NullToEmptyStringSerializer;
import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * String 변수 값이 null이면 빈문자열("")으로 변환하는 어노테이션
 *
 * @author leh
 * @version 1.0
 * @since 2025.03.14
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@JacksonAnnotationsInside
@JsonSerialize(nullsUsing = NullToEmptyStringSerializer.class, using = NullToEmptyStringSerializer.class)
public @interface NullToEmptyString {
}
