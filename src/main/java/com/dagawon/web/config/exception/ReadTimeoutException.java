package com.dagawon.web.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * API 응답을 읽는 도중 시간 초과가 발생한 경우 발생하는 예외입니다.
 *
 * @see HttpStatus#REQUEST_TIMEOUT
 * @author 박원준
 * @version 1.0
 * @since 2025.04.08
 */
@ResponseStatus(HttpStatus.REQUEST_TIMEOUT)
public class ReadTimeoutException extends RuntimeException {
    public ReadTimeoutException(String message) {
        super(message);
    }

    public ReadTimeoutException(String message, Throwable cause) {
        super(message, cause);
    }
}