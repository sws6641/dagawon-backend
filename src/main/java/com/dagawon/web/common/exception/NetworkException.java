package com.dagawon.web.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * API 요청 도중 네트워크 오류가 발생한 경우 발생하는 예외입니다.
 *
 * @see HttpStatus#BAD_GATEWAY
 * @author 박원준
 * @version 1.0
 * @since 2025.04.08
 */
@ResponseStatus(HttpStatus.BAD_GATEWAY)
public class NetworkException extends RuntimeException {
    public NetworkException(String message) {
        super(message);
    }

    public NetworkException(String message, Throwable cause) {
        super(message, cause);
    }
}