package com.example.socialupb.infraestructura.repository;

import com.example.socialupb.aplicacion.dto.response.UsuarioResponse;
import com.example.socialupb.infraestructura.entity.UsuarioEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    @Query("SELECT new com.example.socialupb.aplicacion.dto.response.UsuarioResponse (u)" +
            " from UsuarioEntity u")
    Page<UsuarioResponse> listAll(Pageable pageable);
}
