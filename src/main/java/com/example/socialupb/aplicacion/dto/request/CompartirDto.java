package com.example.socialupb.aplicacion.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CompartirDto {
    private String emailUsuario;
    private String password;
    private String mensaje;

}
