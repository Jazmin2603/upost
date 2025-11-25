package com.example.socialupb.aplicacion.serviceImpl;

import com.example.socialupb.aplicacion.service.PostService;
import com.example.socialupb.infraestructura.repository.PostRepository;
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
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;
}
