package com.example.socialupb.infraestructura.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = "likes")
public class LikeEntity {
    @EmbeddedId
    private LikeId id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idPost")
    @JoinColumn(name = "id_post")
    private PostEntity postEntity;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idUsuario")
    @JoinColumn(name = "id_usuario")
    private UsuarioEntity usuarioEntity;
}
