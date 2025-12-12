package com.example.socialupb.aplicacion.serviceImpl;

import com.example.socialupb.aplicacion.dto.request.UsuarioNuevo;
import com.example.socialupb.aplicacion.dto.response.UsuarioResponse;
import com.example.socialupb.aplicacion.service.UsuarioService;
import com.example.socialupb.dominio.exception.OperationException;
import com.example.socialupb.dominio.modelo.Usuario;
import com.example.socialupb.infraestructura.entity.UsuarioEntity;
import com.example.socialupb.infraestructura.repository.UsuarioRepository;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

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
    @Autowired
    private CloudinaryService cloudinaryService;

    @Override
    public UsuarioEntity findById(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new OperationException("Usuario no encontrado: " + id));
    }

    @Override
    public UsuarioEntity verificar(String email, String password) {
        UsuarioEntity usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new OperationException("Credenciales no validas"));
        if (!passwordEncoder.matches(password, usuario.getPassword())) {
            throw new OperationException("Credenciales no validas");
        }else  {
            return usuario;
        }
    }

    @Override
    public void actualizarFotoPerfil(Long id, MultipartFile image) {
        UsuarioEntity usuarioEntity = usuarioRepository.findById(id)
                .orElseThrow(() -> new OperationException("Usuario no encontrado: " + id));

        try {

            String imagenUrl = (image != null && !image.isEmpty()) ? saveImage(image) : null;

            usuarioEntity.setImagenUrl(imagenUrl);
            usuarioRepository.save(usuarioEntity);
        } catch (Exception e) {
            throw new OperationException("Error al subir la imagen de perfil: " + e.getMessage());
        }
    }

    @Transactional
    public String saveImage(MultipartFile image) {

        try {
            File tempFile = File.createTempFile("upload-", image.getOriginalFilename());
            image.transferTo(tempFile);

            String imageUrl = cloudinaryService.uploadImage(tempFile);
            log.info("URL de la imagen subida a Cloudinary: " + imageUrl);

            tempFile.delete();

            return imageUrl;

        } catch (IOException e) {
            throw new RuntimeException("Error subiendo archivo a Cloudinary", e);
        }

    }

    @Override
    public Page<UsuarioResponse> listar(Pageable pageable) {
        return usuarioRepository.listAll(pageable);
    }

    @Override
    public void save(UsuarioNuevo usuarioDto) {
        Usuario usuario = new Usuario(usuarioDto.getNombre(),usuarioDto.getEmail(),usuarioDto.getPassword());

        usuarioRepository.save(UsuarioEntity.builder()
                .nombre(usuario.getNombre())
                .email(usuario.getEmail())
                .password(passwordEncoder.encode(usuario.getPassword()))
                .build());
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
