package com.gametrade.api.service;

import com.gametrade.api.exception.AppException;
import com.gametrade.api.model.Usuario;
import com.gametrade.api.model.dtos.ApiErrorResponse;
import com.gametrade.api.model.repository.UsuarioRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public Usuario createUser(Usuario usuario) throws AppException {
        Usuario user = usuarioRepository.findByEmail(usuario.getEmail());

        if (user == null) {
            String hashPassword = BCrypt.hashpw(usuario.getPassword(), BCrypt.gensalt());
            usuario.setPassword(hashPassword);
            return usuarioRepository.save(usuario);
        }

        throw new AppException(HttpStatus.CONFLICT, "Usuário já cadastrado com esse email.", HttpStatus.CONFLICT.value());
    }

    public Usuario getUsuario(long id) throws AppException {
        Optional<Usuario> user = usuarioRepository.findById(id);

        if (user.isPresent()) {
            return user.orElse(null);
        }

        throw new AppException(HttpStatus.NOT_FOUND, "Nenhum usuário encontrado com esse Id.", HttpStatus.NOT_FOUND.value());
    }
}
