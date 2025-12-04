package com.example.socialupb.infraestructura.controllers;

import com.example.socialupb.aplicacion.dto.request.PostNuevo;
import com.example.socialupb.aplicacion.dto.request.UsuarioNuevo;
import com.example.socialupb.aplicacion.dto.response.ComentarioResponse;
import com.example.socialupb.aplicacion.dto.response.PostResponse;
import com.example.socialupb.aplicacion.dto.response.UsuarioResponse;
import com.example.socialupb.aplicacion.service.PostService;
import com.example.socialupb.dominio.exception.OperationException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/v1/posts")
public class PostController {
    private final PostService postService;

    @PostMapping()
    public ResponseEntity<?> registrar(@RequestBody PostNuevo dto) {
        try {
            postService.save(dto);
            return ResponseEntity.ok("Registro OK");
        }catch (OperationException oe) {
            log.error(oe.getMessage());
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(Map.of("mensaje", oe.getMessage()));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().body("Error al adicionar post");
        }
    }

    @GetMapping()
    public ResponseEntity<?> listarPosts(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "sortBy", defaultValue = "id") String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "DESC") Sort.Direction sortDir
    ) {
        try {
            Pageable pageable = PageRequest.of(page, size, Sort.by(sortDir, sortBy));
            return ResponseEntity.ok(postService.listarTodos(pageable));
        } catch (OperationException oe) {
            log.error(oe.getMessage());
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(Map.of("mensaje", oe.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }


    @DeleteMapping()
    public ResponseEntity<?> eliminarPost(@RequestParam("idPost") Long idPost) {
        try {
            postService.delete(idPost);
            return ResponseEntity.ok("Post eliminado");
        }catch (OperationException oe) {
            log.error(oe.getMessage());
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(Map.of("mensaje", oe.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
