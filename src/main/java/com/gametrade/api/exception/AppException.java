package com.gametrade.api.exception;

import org.springframework.http.HttpStatus;

public class AppException extends RuntimeException {

    private final HttpStatus status;
    private int statusCode;

    public AppException(HttpStatus status, String message, int statusCode) {
        super(message);
        this.status = status;
        this.statusCode = statusCode;
    }

    public HttpStatus getStatus() {
        return this.status;
    }

    public int getStatusCode() {
        return this.statusCode;
    }
}