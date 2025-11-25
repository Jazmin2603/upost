package com.example.socialupb.aplicacion.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UsuarioNuevo {
    private String nombre;
    private String email;
    private String password;
}
