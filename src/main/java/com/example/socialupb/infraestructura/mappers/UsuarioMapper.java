package com.example.socialupb.infraestructura.mappers;

import com.example.socialupb.dominio.modelo.Usuario;
import com.example.socialupb.infraestructura.entity.UsuarioEntity;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@AllArgsConstructor
public class UsuarioMapper {
    private final PasswordEncoder passwordEncoder;

    public UsuarioEntity toEntity(Usuario usuario) {
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setNombre(usuario.getNombre());
        usuarioEntity.setEmail(usuario.getEmail());
        usuarioEntity.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioEntity;
    }

    public Usuario toModel(UsuarioEntity usuarioEntity) {
        return new Usuario(usuarioEntity.getNombre(),
                usuarioEntity.getEmail(),
                usuarioEntity.getPassword());
    }
}
