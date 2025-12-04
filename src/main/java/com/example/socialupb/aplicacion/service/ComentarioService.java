package com.example.socialupb.aplicacion.service;

import com.example.socialupb.aplicacion.dto.request.ComentarioNuevo;
import com.example.socialupb.aplicacion.dto.request.PostNuevo;
import com.example.socialupb.aplicacion.dto.response.PostResponse;
import com.example.socialupb.infraestructura.entity.ComentarioEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ComentarioService {
    ComentarioEntity findById(Long id);
    Page<PostResponse> listarPorPost(Long idPost, Pageable pageable);
    void save(ComentarioNuevo comentarioDto);
    void delete(Long id);
    void borrarPorPost(Long idPost);
}
