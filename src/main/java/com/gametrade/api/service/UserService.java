package com.gametrade.api.service;

import com.gametrade.api.model.Usuario;
import com.gametrade.api.model.repository.UsuarioRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public Usuario createUser(Usuario usuario) {
        String hashPassword = BCrypt.hashpw(usuario.getPassword(),BCrypt.gensalt());
        usuario.setPassword(hashPassword);
        return usuarioRepository.save(usuario);
    }
}
