package com.dagawon.web.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;



/**
 * API 서버의 응답 대기 중 시간 초과가 발생한 경우 발생하는 예외입니다.
 *
 * @see HttpStatus#REQUEST_TIMEOUT
 * @author 박원준
 * @version 1.0
 * @since 2025.04.08
 */
@ResponseStatus(HttpStatus.REQUEST_TIMEOUT)
public class ResponseTimeoutException extends RuntimeException {
    public ResponseTimeoutException(String message) {
        super(message);
    }

    public ResponseTimeoutException(String message, Throwable cause) {
        super(message, cause);
    }
}