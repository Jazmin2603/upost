package com.example.socialupb.infraestructura.repository;

import com.example.socialupb.infraestructura.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
}
