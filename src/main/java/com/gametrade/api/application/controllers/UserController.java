package com.gametrade.api.application.controllers;

import com.gametrade.api.model.Usuario;
import com.gametrade.api.infra.persistence.repository.UsuarioRepository;
import com.gametrade.api.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<Usuario>> listUser() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Usuario> getUser(@PathVariable long id) {
        Usuario user = userService.getUsuario(id);
        return new ResponseEntity<>(user, HttpStatus.OK);

    }
}
