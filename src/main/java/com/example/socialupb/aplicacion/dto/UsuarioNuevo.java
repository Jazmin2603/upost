package com.example.socialupb.aplicacion.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UsuarioNuevo {
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no puede tener más de 100 caracteres")
    @JsonProperty(required = true)
    private String nombre;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "Debe ingresar un email válido")
    @Size(max = 100, message = "El email no puede tener más de 100 caracteres")
    @JsonProperty(required = true)
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    @JsonProperty(required = true)
    private String password;
}
