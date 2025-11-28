package com.example.socialupb.aplicacion.dto.request;

import lombok.*;

import java.time.LocalDateTime;

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
