package com.example.socialupb.aplicacion.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class PostNuevo {
    private Long id;
    private String mensaje;
    private Long idusuario;
}
