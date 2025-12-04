package com.example.socialupb.infraestructura.controllers;

import com.example.socialupb.aplicacion.dto.request.ComentarioNuevo;
import com.example.socialupb.aplicacion.dto.request.LikeNuevo;
import com.example.socialupb.aplicacion.service.LikeService;
import com.example.socialupb.dominio.exception.OperationException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/v1/likes")
public class LikeController {
    private final LikeService likeService;

    @PostMapping()
    public ResponseEntity<?> registrar(@RequestBody LikeNuevo dto) {
        try {
            likeService.save(dto);
            return ResponseEntity.ok("Registro OK");
        }catch (OperationException oe) {
            log.error(oe.getMessage());
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(Map.of("mensaje", oe.getMessage()));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().body("Error al dar Like al Post");
        }
    }

    @DeleteMapping()
    public ResponseEntity<?> eliminarUsuario(@RequestParam("idUsuario") Long idUsuario, @RequestParam("idPost") Long idPost) {
        try {
            likeService.delete(idUsuario, idPost);
            return ResponseEntity.ok("Like eliminado");
        }catch (OperationException oe) {
            log.error(oe.getMessage());
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(Map.of("mensaje", oe.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
