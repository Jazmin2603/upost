package com.example.socialupb.aplicacion.dto.response;

import com.example.socialupb.infraestructura.entity.PostEntity;
import com.example.socialupb.infraestructura.entity.UsuarioEntity;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class PostResponse {
    private Long id;
    private String mensaje;
    private LocalDateTime fecha;
    private Long idusuario;
    private String nombreUsuario;

    public PostResponse(PostEntity postEntity, UsuarioEntity usuarioEntity) {
        this.id = postEntity.getId();
        this.mensaje = postEntity.getMensaje();
        this.fecha = postEntity.getFecha();
        this.idusuario = usuarioEntity.getId();
        this.nombreUsuario = usuarioEntity.getNombre();
    }
}
