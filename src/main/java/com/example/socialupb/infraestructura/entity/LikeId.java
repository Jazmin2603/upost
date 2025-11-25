package com.example.socialupb.infraestructura.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LikeId {
    @Column(name = "id_post", nullable = false)
    private Long idPost;

    @Column(name = "id_usuario", nullable = false)
    private Long idUsuario;
}
