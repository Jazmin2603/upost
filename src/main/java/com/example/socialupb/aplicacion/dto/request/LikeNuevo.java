package com.example.socialupb.aplicacion.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class LikeNuevo {
    private Long idusuario;
    private Long idPost;
}
