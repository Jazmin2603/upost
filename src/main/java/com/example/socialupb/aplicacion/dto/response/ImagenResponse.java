package com.example.socialupb.aplicacion.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ImagenResponse {
    private Long id;
    private String urlImagen;

    public ImagenResponse(ImagenResponse imagenResponse) {
        this.id = imagenResponse.getId();
        this.urlImagen = imagenResponse.getUrlImagen();
    }

}
