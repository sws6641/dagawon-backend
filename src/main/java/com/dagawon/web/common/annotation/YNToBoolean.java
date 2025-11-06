package com.dagawon.web.common.annotation;

import com.dagawon.web.common.serializer.YNBooleanSerializer;
import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * String -> boolean으로 변환하는 어노테이션
 * "Y" 이면 true
 *  null 또는 "Y"가 아니면 false
 *
 * @author leh
 * @version 1.0
 * @since 2025.03.14
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@JacksonAnnotationsInside
@JsonSerialize(nullsUsing = YNBooleanSerializer.class, using = YNBooleanSerializer.class)
public @interface YNToBoolean {
}
