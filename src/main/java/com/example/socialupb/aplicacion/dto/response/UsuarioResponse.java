package com.example.socialupb.aplicacion.dto.response;

import com.example.socialupb.infraestructura.entity.UsuarioEntity;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UsuarioResponse {
    private Long id;
    private String nombre;
    private String email;
    private String imagenUrl;

    public UsuarioResponse(UsuarioEntity usuarioEntity) {
        this.id = usuarioEntity.getId();
        this.nombre = usuarioEntity.getNombre();
        this.email = usuarioEntity.getEmail();
        this.imagenUrl = usuarioEntity.getImagenUrl();
    }
}
