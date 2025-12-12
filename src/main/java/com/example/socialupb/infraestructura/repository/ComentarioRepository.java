package com.example.socialupb.infraestructura.repository;

import com.example.socialupb.aplicacion.dto.response.ComentarioResponse;
import com.example.socialupb.infraestructura.entity.ComentarioEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ComentarioRepository extends JpaRepository<ComentarioEntity, Long> {

    @Query("SELECT new com.example.socialupb.aplicacion.dto.response.ComentarioResponse (c,u)" +
            " from ComentarioEntity c INNER JOIN UsuarioEntity u ON c.usuarioEntity.id = u.id " +
            " WHERE c.postEntity.id =:pIdPost" +
            " ORDER BY c.fecha ASC ")
    Page<ComentarioResponse> listarPorPost(@Param("pIdPost") Long idPost, Pageable pageable);

    @Modifying
    @Query("DELETE from ComentarioEntity c " +
            " WHERE c.postEntity.id =:pIdPost")
    void eliminarComentariosPorPost(@Param("pIdPost") Long idPost);
}