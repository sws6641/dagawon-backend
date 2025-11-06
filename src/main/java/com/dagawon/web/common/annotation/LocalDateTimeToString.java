package com.dagawon.web.common.annotation;

import com.dagawon.web.common.serializer.LocalDateTimeStringSerializer;
import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * LocalDateTime -> String으로 변환하는 어노테이션
 * 데이터가 null 이면 빈문자열("")로 변경
 *
 * 사용 예시:
 * {@code
 *      // 기본 패턴 사용 (yyyy-MM-dd HH:mm:ss)
 *      @LocalDateTimeToString
 *      private LocalDateTime defaultDateTime;
 * 
 *      // 커스텀 패턴 사용
 *      @LocalDateTimeToString(pattern = "yyyy/MM/dd HH:mm")
 *      private LocalDateTime customDateTime;
 * }
 *
 * @author leh
 * @version 1.0
 * @since 2025.03.14
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
@JacksonAnnotationsInside
@JsonFormat(shape = JsonFormat.Shape.STRING)
@JsonSerialize(nullsUsing = LocalDateTimeStringSerializer.class, using = LocalDateTimeStringSerializer.class)
public @interface LocalDateTimeToString {
    /**
     * 날짜/시간을 문자열로 변환할 때 사용할 패턴을 지정
     * DateTimeFormatter에서 지원하는 모든 패턴 사용 가능
     *
     * @return 날짜/시간 패턴 문자열
     */
    String pattern() default "yyyy-MM-dd HH:mm:ss";
}
