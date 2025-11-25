package com.example.socialupb.infraestructura.repository;

import com.example.socialupb.infraestructura.entity.ComentarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentarioRepository extends JpaRepository<ComentarioEntity, Long> {
}
