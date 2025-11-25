package com.example.socialupb.infraestructura.repository;

import com.example.socialupb.infraestructura.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity,Long> {
}
