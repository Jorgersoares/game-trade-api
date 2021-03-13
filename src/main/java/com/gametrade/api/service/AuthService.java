package com.gametrade.api.service;


import com.gametrade.api.exception.AppException;
import com.gametrade.api.model.Usuario;
import com.gametrade.api.model.dtos.LoginRequest;
import com.gametrade.api.model.repository.UsuarioRepository;
import com.gametrade.api.security.util.JWTUtil;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AuthService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    public HashMap<String, String> auth(String email, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            HashMap<String, String> response = new HashMap<>();
            response.put("token", JWTUtil.generateToken(email));
            return response;

        } catch (BadCredentialsException e) {
            throw new AppException(HttpStatus.BAD_REQUEST,"Email ou senha inválida", HttpStatus.BAD_REQUEST.value());
        }
    }
}
