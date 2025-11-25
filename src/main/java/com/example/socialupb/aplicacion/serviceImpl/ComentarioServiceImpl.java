package com.example.socialupb.aplicacion.serviceImpl;

import com.example.socialupb.aplicacion.service.ComentarioService;
import com.example.socialupb.infraestructura.repository.ComentarioRepository;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
