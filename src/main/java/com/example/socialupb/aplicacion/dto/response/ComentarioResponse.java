package com.example.socialupb.aplicacion.dto.response;

import com.example.socialupb.infraestructura.entity.ComentarioEntity;
import com.example.socialupb.infraestructura.entity.PostEntity;
import com.example.socialupb.infraestructura.entity.UsuarioEntity;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ComentarioResponse {
    private Long id;
    private String mensaje;
    private LocalDateTime fecha;
    private Long idusuario;
    private String nombreUsuario;

    public ComentarioResponse(ComentarioEntity comentarioEntity, UsuarioEntity usuarioEntity) {
        this.id = comentarioEntity.getId();
        this.mensaje = comentarioEntity.getMensaje();
        this.fecha = comentarioEntity.getFecha();
        this.idusuario = usuarioEntity.getId();
        this.nombreUsuario = usuarioEntity.getNombre();
    }
}
