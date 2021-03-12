package com.gametrade.api.application.controllers;

import com.gametrade.api.exception.AppException;
import com.gametrade.api.model.Usuario;
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
    public ResponseEntity<Usuario> login(@Valid @RequestBody LoginRequest loginForm) throws AppException {
        return new ResponseEntity<Usuario>(authService.login(loginForm), HttpStatus.OK);
    }
}
