package com.gametrade.api.exception;


import com.gametrade.api.model.dtos.ApiErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler({ AppException.class })
    protected ResponseEntity<ApiErrorResponse> handleApiException(AppException ex) {
        ApiErrorResponse errorResponse = new ApiErrorResponse(ex.getStatus(), ex.getMessage(), ex.getStatusCode());
        return new ResponseEntity<>(errorResponse, ex.getStatus());
    }
}
