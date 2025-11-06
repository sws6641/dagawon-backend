package com.dagawon.web.common.annotation;

import com.dagawon.web.common.serializer.DateFormatStringSerializer;
import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * String 타입의 날짜를 지정된 포맷으로 변환하는 어노테이션
 * 데이터가 null 이면 빈문자열("")로 변경
 *
 * 사용 예시:
 * {@code
 *     // 기본 패턴 사용 (yyyy-MM-dd)
 *     @DateFormatString
 *     private String defaultDate;
 *
 *     // 커스텀 패턴 사용
 *     @DateFormatString(inputPattern = "yyyyMMdd", outputPattern = "yyyy-MM-dd")
 *     private String customDate;
 * }
 *
 * @author leh
 * @version 1.0
 * @since 2025.03.14
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
@JacksonAnnotationsInside
@JsonSerialize(using = DateFormatStringSerializer.class , nullsUsing = DateFormatStringSerializer.class)
public @interface DateFormatString {
    /**
     * 입력되는 날짜 문자열의 패턴
     * @return 입력 날짜 패턴 문자열
     */
    String inputPattern() default "yyyyMMdd";

    /**
     * 출력할 날짜 문자열의 패턴
     * @return 출력 날짜 패턴 문자열
     */
    String outputPattern() default "yyyy-MM-dd";
}