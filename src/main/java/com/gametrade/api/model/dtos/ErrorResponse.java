package com.gametrade.api.model.dtos;

import org.springframework.http.HttpStatus;

public class ErrorResponse {
    public String message;
    public int status;

    public ErrorResponse(String message, int status){
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
