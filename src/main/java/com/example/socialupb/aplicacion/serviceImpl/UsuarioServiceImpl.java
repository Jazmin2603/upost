package com.example.socialupb.aplicacion.serviceImpl;

import com.example.socialupb.aplicacion.dto.UsuarioNuevo;
import com.example.socialupb.aplicacion.service.UsuarioService;
import com.example.socialupb.dominio.exception.OperationException;
import com.example.socialupb.infraestructura.entity.UsuarioEntity;
import com.example.socialupb.infraestructura.repository.UsuarioRepository;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UsuarioEntity findById(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new OperationException("Usuario no encontrado: " + id));
    }
    @Override
    public List<UsuarioEntity> listar() {
        return usuarioRepository.findAll();
    }

    @Override
    public void save(UsuarioNuevo usuarioDto) {
        UsuarioEntity usuarioEntity = UsuarioEntity.builder()
                .nombre(usuarioDto.getNombre())
                .email(usuarioDto.getEmail())
                .password(passwordEncoder.encode(usuarioDto.getPassword()))
                .build();

        usuarioRepository.save(usuarioEntity);
    }
    @Override
    @Transactional
    public void delete(Long id) {
        UsuarioEntity usuarioEntity = usuarioRepository.findById(id)
                .orElseThrow(() -> new OperationException("Usuario no encontrado: " + id));

        usuarioRepository.delete(usuarioEntity);
    }

    @Override
    @Transactional
    public void editar(Long id, UsuarioNuevo usuarioDto) {
        UsuarioEntity usuarioEntity = usuarioRepository.findById(id)
                .orElseThrow(() -> new OperationException("Usuario no encontrado: " + id));

        usuarioEntity.setNombre(usuarioDto.getNombre());
        usuarioEntity.setEmail(usuarioDto.getEmail());

        if (usuarioDto.getPassword() != null && !usuarioDto.getPassword().isBlank()) {
            usuarioEntity.setPassword(passwordEncoder.encode(usuarioDto.getPassword()));
        }

        usuarioRepository.save(usuarioEntity);
    }
}
