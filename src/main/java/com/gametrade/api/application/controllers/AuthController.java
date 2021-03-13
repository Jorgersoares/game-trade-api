package com.gametrade.api.application.controllers;

import com.gametrade.api.exception.AppException;
import com.gametrade.api.model.Usuario;
import com.gametrade.api.model.dtos.LoginRequest;
import com.gametrade.api.service.AuthService;
import com.gametrade.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@Valid @RequestBody LoginRequest loginForm) throws AppException {
        return new ResponseEntity<>(authService.login(loginForm), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Usuario> addUser(@Valid @RequestBody Usuario user) throws AppException {
        Usuario user$ = userService.createUser(user);
        return new ResponseEntity<>(user$, HttpStatus.CREATED);
    }
}
