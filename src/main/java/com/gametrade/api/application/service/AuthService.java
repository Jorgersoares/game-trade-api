package com.gametrade.api.application.service;

import com.gametrade.api.exception.AppException;
import com.gametrade.api.model.Usuario;
import com.gametrade.api.presentation.dtos.LoginRequest;
import com.gametrade.api.infra.persistence.repository.UsuarioRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public Usuario login(LoginRequest loginForm) throws AppException {
        Usuario user = usuarioRepository.findByEmail(loginForm.getEmail());

        if ( user != null && BCrypt.checkpw(loginForm.getPassword(), user.getPassword()) ){
            return user;
        }

        throw new AppException(HttpStatus.BAD_REQUEST,"Email ou senha inválida", HttpStatus.BAD_REQUEST.value());
    }
}
