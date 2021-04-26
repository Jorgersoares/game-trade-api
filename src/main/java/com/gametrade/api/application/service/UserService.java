package com.gametrade.api.application.service;

import com.gametrade.api.exception.AppException;
import com.gametrade.api.model.Usuario;
import com.gametrade.api.infra.persistence.repository.UsuarioRepository;
import com.gametrade.api.presentation.dtos.EditUserRequest;
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

    public Usuario updateUsuario(Long id, EditUserRequest editUserRequest) throws AppException {
        Usuario user = this.getUsuario(id);

        if (!editUserRequest.getEmail().equals(user.getEmail())) {
            Usuario userEmail = usuarioRepository.findByEmail(editUserRequest.getEmail());
            if (userEmail != null) {
                throw new AppException(HttpStatus.CONFLICT, "Usuário já cadastrado com esse email.", HttpStatus.CONFLICT.value());
            }
        }
        user.setEmail(editUserRequest.getEmail());
        user.setFirstName(editUserRequest.getFirstName());
        user.setLastName(editUserRequest.getLastName());

        return usuarioRepository.save(user);
    }
}
