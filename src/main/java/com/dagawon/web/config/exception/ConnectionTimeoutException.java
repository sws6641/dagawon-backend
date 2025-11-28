package com.dagawon.web.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * API 서버와의 연결 시도 중 시간 초과가 발생한 경우 발생하는 예외입니다.
 *
 * @author 박원준
 * @version 1.0
 * @since 2025.04.08
 */
@ResponseStatus(HttpStatus.GATEWAY_TIMEOUT)
public class ConnectionTimeoutException extends RuntimeException {
    public ConnectionTimeoutException(String message) {
        super(message);
    }

    public ConnectionTimeoutException(String message, Throwable cause) {
        super(message, cause);
    }
}