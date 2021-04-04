package com.gametrade.api.application.controllers;

import com.gametrade.api.exception.AppException;
import com.gametrade.api.model.Usuario;
import com.gametrade.api.presentation.dtos.LoginRequest;
import com.gametrade.api.application.service.AuthService;
import com.gametrade.api.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
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
