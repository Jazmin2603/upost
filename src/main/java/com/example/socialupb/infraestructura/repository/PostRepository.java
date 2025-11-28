package com.example.socialupb.infraestructura.repository;

import com.example.socialupb.aplicacion.dto.response.PostResponse;
import com.example.socialupb.infraestructura.entity.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<PostEntity,Long> {
    @Query("SELECT new com.example.socialupb.aplicacion.dto.response.PostResponse (p,u)" +
            " from PostEntity p INNER JOIN UsuarioEntity u ON p.usuarioEntity.id = u.id " +
            " ORDER BY p.fecha DESC ")
    Page<PostResponse> listarPorFecha(Pageable pageable);
}
