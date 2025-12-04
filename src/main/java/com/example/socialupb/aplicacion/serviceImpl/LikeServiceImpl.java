package com.example.socialupb.aplicacion.serviceImpl;

import com.example.socialupb.aplicacion.dto.request.ComentarioNuevo;
import com.example.socialupb.aplicacion.dto.request.LikeNuevo;
import com.example.socialupb.aplicacion.service.LikeService;
import com.example.socialupb.aplicacion.service.PostService;
import com.example.socialupb.aplicacion.service.UsuarioService;
import com.example.socialupb.dominio.exception.OperationException;
import com.example.socialupb.infraestructura.entity.*;
import com.example.socialupb.infraestructura.repository.LikeRepository;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Service
public class LikeServiceImpl implements LikeService {
    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    @Lazy
    private UsuarioService usuarioService;

    @Autowired
    @Lazy
    private PostService postService;

    @Override
    public LikeEntity findById(Long idUsuario, Long idPost) {
        LikeId likeId = new LikeId(idPost, idUsuario);
        return likeRepository.findById(likeId)
                .orElseThrow(() -> new OperationException("Like no registrado: " + idUsuario + " " + idPost));
    }

    @Override
    public void save(LikeNuevo likeDto) {
        LikeId likeId = new LikeId(likeDto.getIdPost(), likeDto.getIdusuario());

        Optional<LikeEntity> like = likeRepository.findById(likeId);
        if (like.isPresent()) {
            throw new OperationException("Like ya registrado");
        }

        UsuarioEntity usuario = usuarioService.findById(likeDto.getIdusuario());
        PostEntity post = postService.findById(likeDto.getIdPost());


        likeRepository.save(LikeEntity.builder()
                .id(likeId)
                .postEntity(post)
                .usuarioEntity(usuario)
                .build());
    }

    @Override
    @Transactional
    public void delete(Long idUsuario, Long idPost) {
        LikeEntity like = findById(idUsuario, idPost);
        likeRepository.delete(like);
    }
}
