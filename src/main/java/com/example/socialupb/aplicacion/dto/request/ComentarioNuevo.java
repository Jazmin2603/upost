package com.example.socialupb.aplicacion.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ComentarioNuevo {
    private Long id;
    private String mensaje;
    private Long idusuario;
    private Long idPost;
}
