package com.gametrade.api.service;

import com.gametrade.api.model.Usuario;
import com.gametrade.api.model.dtos.LoginRequest;
import com.gametrade.api.model.repository.UsuarioRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public Usuario login(LoginRequest loginForm) throws Exception {
        Usuario user = usuarioRepository.findByEmail(loginForm.getEmail());
        if(user != null){
            if(BCrypt.checkpw(loginForm.getPassword(), user.getPassword())){
                return user;
            }
        }
        throw new Exception("Email ou senha inválida");
    }
}
