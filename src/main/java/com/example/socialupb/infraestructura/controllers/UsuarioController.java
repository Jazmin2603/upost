package com.example.socialupb.infraestructura.controllers;

import com.example.socialupb.aplicacion.dto.request.UsuarioNuevo;
import com.example.socialupb.aplicacion.dto.response.UsuarioResponse;
import com.example.socialupb.aplicacion.service.UsuarioService;
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
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;
    @PostMapping()
    public ResponseEntity<?> registrar(@RequestBody UsuarioNuevo dto) {
        try {
            usuarioService.save(dto);
            return ResponseEntity.ok("Registro OK");
        }catch (OperationException oe) {
            log.error(oe.getMessage());
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(Map.of("mensaje", oe.getMessage()));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().body("Error al registrar usuario");
        }
    }

    @GetMapping()
    public ResponseEntity<?> listarUsuarios(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "sortBy", defaultValue = "id") String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "DESC") Sort.Direction sortDir
    ) {
        try {
            Pageable pageable = PageRequest.of(page, size, Sort.by(sortDir, sortBy));
            return ResponseEntity.ok(usuarioService.listar(pageable));
        } catch (OperationException oe) {
            log.error(oe.getMessage());
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(Map.of("mensaje", oe.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<?> buscarUnUsuario(@PathVariable Long idUsuario) {
        try {
            return ResponseEntity.ok(new UsuarioResponse(usuarioService.findById(idUsuario)));
        }catch (OperationException oe) {
            log.error(oe.getMessage());
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(Map.of("mensaje", oe.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{idUsuario}")
    public ResponseEntity<?> editarUsuario(@PathVariable Long idUsuario, @RequestBody UsuarioNuevo dto) {
        try {
            usuarioService.editar(idUsuario, dto);
            return ResponseEntity.ok("Editado OK");
        }catch (OperationException oe) {
            log.error(oe.getMessage());
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(Map.of("mensaje", oe.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }


    @DeleteMapping()
    public ResponseEntity<?> eliminarUsuario(@RequestParam("idUsuario") Long idUsuario) {
        try {
            usuarioService.delete(idUsuario);
            return ResponseEntity.ok("Usuario eliminado");
        }catch (OperationException oe) {
            log.error(oe.getMessage());
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(Map.of("mensaje", oe.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
