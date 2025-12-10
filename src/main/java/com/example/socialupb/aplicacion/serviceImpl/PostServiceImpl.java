package com.example.socialupb.aplicacion.serviceImpl;

import com.example.socialupb.aplicacion.dto.request.PostNuevo;
import com.example.socialupb.aplicacion.dto.request.UsuarioNuevo;
import com.example.socialupb.aplicacion.dto.response.PostResponse;
import com.example.socialupb.aplicacion.service.ComentarioService;
import com.example.socialupb.aplicacion.service.PostService;
import com.example.socialupb.aplicacion.service.UsuarioService;
import com.example.socialupb.dominio.exception.OperationException;
import com.example.socialupb.dominio.modelo.Usuario;
import com.example.socialupb.infraestructura.entity.PostEntity;
import com.example.socialupb.infraestructura.entity.UsuarioEntity;
import com.example.socialupb.infraestructura.repository.PostRepository;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    @Lazy
    private UsuarioService usuarioService;

    @Autowired
    @Lazy
    private ComentarioService comentarioService;

    @Override
    public PostEntity findById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new OperationException("Post no encontrado: " + id));
    }
    @Override
    public Page<PostResponse> listarTodos(Pageable pageable) {
        return postRepository.listarPorFecha(pageable);
    }

    @Override
    public void save(PostNuevo postDto) {
        UsuarioEntity usuario = usuarioService.findById(postDto.getIdusuario());

        postRepository.save(PostEntity.builder()
                .mensaje(postDto.getMensaje())
                .fecha(LocalDateTime.now())
                .usuarioEntity(usuario)
                .build());
    }
    @Override
    @Transactional
    public void delete(Long id) {
        PostEntity post = findById(id);
        comentarioService.borrarPorPost(id);
        postRepository.delete(post);
    }

}
