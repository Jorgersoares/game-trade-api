package com.gametrade.api.application.controllers;

import com.gametrade.api.model.Usuario;
import com.gametrade.api.model.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping("/users")
    public ResponseEntity<List<Usuario>> listUser() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Usuario> getUser(@PathVariable long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return new ResponseEntity<>(usuario.orElse(null), HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<Usuario> addUser(@Valid @RequestBody Usuario user) {
        usuarioRepository.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

}
