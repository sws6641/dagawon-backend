package com.dagawon.web.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 전체 요청 처리 시간이 초과된 경우 발생하는 예외입니다.
 *
 * @see HttpStatus#GATEWAY_TIMEOUT
 * @author 박원준
 * @version 1.0
 * @since 2025.04.08
 */
@ResponseStatus(HttpStatus.GATEWAY_TIMEOUT)
public class RequestTimeoutException extends RuntimeException {

    public RequestTimeoutException(String message) {
        super(message);
    }

    public RequestTimeoutException(String message, Throwable cause) {
        super(message, cause);
    }
}