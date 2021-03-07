package com.gametrade.api.application.controllers;

import com.gametrade.api.model.Usuario;
import com.gametrade.api.model.repository.UsuarioRepository;
import com.gametrade.api.service.UserService;
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

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<Usuario>> listUser() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Usuario> getUser(@PathVariable long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        Usuario user = usuario.orElse(null);
        HttpStatus httpStatus = HttpStatus.OK;
        if(user == null){
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(usuario.orElse(null), httpStatus);
    }

    @PostMapping("/users")
    public ResponseEntity<Usuario> addUser(@Valid @RequestBody Usuario user) {
        Usuario user$ = userService.createUser(user);
        return new ResponseEntity<>(user$, HttpStatus.CREATED);
    }

}
