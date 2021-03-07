package com.gametrade.api.application.controllers;

import com.gametrade.api.model.dtos.ErrorResponse;
import com.gametrade.api.model.dtos.LoginRequest;
import com.gametrade.api.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import javax.validation.Valid;

@RestController
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginForm) throws Exception {
        try{
            return new ResponseEntity(authService.login(loginForm), HttpStatus.OK);
        } catch (Exception err){
            return new ResponseEntity(
                    new ErrorResponse(
                            err.getMessage(),HttpStatus.BAD_REQUEST.value()),
                            HttpStatus.BAD_REQUEST
                    );
        }
    }
}
