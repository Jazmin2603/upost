package com.example.socialupb.aplicacion.service;

import com.example.socialupb.aplicacion.dto.request.LikeNuevo;
import com.example.socialupb.infraestructura.entity.LikeEntity;

public interface LikeService {
    LikeEntity findById(Long idUsuario, Long idPost);
    void save(LikeNuevo likeDto);
    void delete(Long idUsuario, Long idPost);
}
