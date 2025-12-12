package com.example.socialupb.aplicacion.serviceImpl;

import com.example.socialupb.aplicacion.dto.request.ComentarioNuevo;
import com.example.socialupb.aplicacion.dto.request.PostNuevo;
import com.example.socialupb.aplicacion.dto.response.ComentarioResponse;
import com.example.socialupb.aplicacion.dto.response.PostResponse;
import com.example.socialupb.aplicacion.service.ComentarioService;
import com.example.socialupb.aplicacion.service.PostService;
import com.example.socialupb.aplicacion.service.UsuarioService;
import com.example.socialupb.dominio.exception.OperationException;
import com.example.socialupb.infraestructura.entity.ComentarioEntity;
import com.example.socialupb.infraestructura.entity.PostEntity;
import com.example.socialupb.infraestructura.entity.UsuarioEntity;
import com.example.socialupb.infraestructura.repository.ComentarioRepository;
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
public class ComentarioServiceImpl implements ComentarioService {
    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    @Lazy
    private UsuarioService usuarioService;

    @Autowired
    @Lazy
    private PostService postService;

    @Override
    public ComentarioEntity findById(Long id) {
        return comentarioRepository.findById(id)
                .orElseThrow(() -> new OperationException("Comentario no encontrado: " + id));
    }
    @Override
    public Page<ComentarioResponse> listarPorPost(Long idPost, Pageable pageable) {
        return comentarioRepository.listarPorPost(idPost,pageable);
    }

    @Override
    public void save(ComentarioNuevo comentarioDto) {
        UsuarioEntity usuario = usuarioService.findById(comentarioDto.getIdusuario());
        PostEntity post = postService.findById(comentarioDto.getIdPost());

        comentarioRepository.save(ComentarioEntity.builder()
                .mensaje(comentarioDto.getMensaje())
                .fecha(LocalDateTime.now())
                .usuarioEntity(usuario)
                .postEntity(post)
                .build());
    }
    @Override
    @Transactional
    public void delete(Long id) {
        ComentarioEntity comentario = findById(id);
        comentarioRepository.delete(comentario);
    }

    @Override
    @Transactional
    public void borrarPorPost(Long idPost) {
        comentarioRepository.eliminarComentariosPorPost(idPost);
    }

}
