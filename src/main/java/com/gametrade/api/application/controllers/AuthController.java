package com.gametrade.api.application.controllers;

import com.gametrade.api.exception.AppException;
import com.gametrade.api.model.Usuario;
import com.gametrade.api.presentation.dtos.LoginRequest;
import com.gametrade.api.application.service.AuthService;
import com.gametrade.api.application.service.UserService;
import com.gametrade.api.presentation.dtos.UserResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @Autowired
    UserService userService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@Valid @RequestBody LoginRequest loginForm) throws AppException {
        Usuario user = authService.login(loginForm);
        return new ResponseEntity<>(modelMapper.map(user, UserResponse.class), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> addUser(@Valid @RequestBody Usuario user) throws AppException {
        Usuario user$ = userService.createUser(user);
        return new ResponseEntity<>(modelMapper.map(user$, UserResponse.class), HttpStatus.CREATED);
    }
}
