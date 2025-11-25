package com.example.socialupb.aplicacion.service;

import com.example.socialupb.aplicacion.dto.UsuarioNuevo;
import com.example.socialupb.infraestructura.entity.UsuarioEntity;

import java.util.List;

public interface UsuarioService {
    UsuarioEntity findById(Long id);
    void save(UsuarioNuevo usuarioDto);
    void editar(Long id, UsuarioNuevo usuarioDto);
    void delete(Long id);
    List<UsuarioEntity> listar();
}
