package com.gametrade.api.application.controllers;

import com.gametrade.api.model.Usuario;
import com.gametrade.api.infra.persistence.repository.UsuarioRepository;
import com.gametrade.api.application.service.UserService;
import com.gametrade.api.presentation.dtos.UserResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    UserService userService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> listUser() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return new ResponseEntity<>(usuarios
                .stream()
                .map(usuario -> modelMapper.map(usuario, UserResponse.class))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable long id) {
        Usuario user = userService.getUsuario(id);
        return new ResponseEntity<>(modelMapper.map(user, UserResponse.class), HttpStatus.OK);

    }
}
