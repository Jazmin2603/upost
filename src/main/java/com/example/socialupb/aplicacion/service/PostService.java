package com.example.socialupb.aplicacion.service;

import com.example.socialupb.aplicacion.dto.request.CompartirDto;
import com.example.socialupb.aplicacion.dto.request.PostNuevo;
import com.example.socialupb.aplicacion.dto.response.PostResponse;
import com.example.socialupb.infraestructura.entity.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface PostService {
    PostEntity findById(Long id);
    Page<PostResponse> listarTodos(Pageable pageable);
    void save(PostNuevo postDto, MultipartFile image);
    void delete(Long id);
    void compartir(CompartirDto compartirDto, MultipartFile image);
}
