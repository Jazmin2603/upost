package com.example.socialupb.aplicacion.service;

import com.example.socialupb.aplicacion.dto.request.UsuarioNuevo;
import com.example.socialupb.aplicacion.dto.response.UsuarioResponse;
import com.example.socialupb.infraestructura.entity.UsuarioEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface UsuarioService {
    UsuarioEntity findById(Long id);
    void save(UsuarioNuevo usuarioDto);
    void editar(Long id, UsuarioNuevo usuarioDto);
    void delete(Long id);
    Page<UsuarioResponse> listar(Pageable pageable);
    UsuarioEntity verificar(String email, String password);
    void actualizarFotoPerfil(Long id, MultipartFile image);
}
