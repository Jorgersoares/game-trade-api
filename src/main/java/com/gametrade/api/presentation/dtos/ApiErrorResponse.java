package com.gametrade.api.presentation.dtos;

import org.springframework.http.HttpStatus;

public class ApiErrorResponse {

    private final HttpStatus status;
    private final String message;
    private final int statusCode;

    public ApiErrorResponse(HttpStatus status, String message, int statusCode) {
        this.status = status;
        this.message = message;
        this.statusCode = statusCode;
    }

    public HttpStatus getStatus() {
        return this.status;
    }

    public String getMessage() {
        return this.message;
    }


    public int getStatusCode() {
        return statusCode;
    }
}