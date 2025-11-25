package com.example.socialupb.infraestructura.repository;

import com.example.socialupb.infraestructura.entity.LikeEntity;
import com.example.socialupb.infraestructura.entity.LikeId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<LikeEntity, LikeId> {
}
