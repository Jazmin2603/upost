package com.example.socialupb.infraestructura.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "usuarios")
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre",length = 100, nullable = false)
    private String nombre;

    @Column(name = "email",length = 100, unique = true, nullable = false)
    private String email;

    @Column(name = "password",columnDefinition = "TEXT", nullable = false)
    private String password;

    @Column(name = "url_imagen", length = 300)
    private String imagenUrl;

}
