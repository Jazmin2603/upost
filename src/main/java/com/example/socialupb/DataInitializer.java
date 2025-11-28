package com.example.socialupb;

import com.example.socialupb.infraestructura.entity.UsuarioEntity;
import com.example.socialupb.infraestructura.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final UsuarioRepository UsuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        init();
    }

    private void init() {
        if (UsuarioRepository.count() == 0) {
            UsuarioEntity root = UsuarioRepository.save(UsuarioEntity.builder()
                    .nombre("root")
                    .email("root@root")
                    .password(passwordEncoder.encode("empanada"))
                    .build());
            this.UsuarioRepository.save(root);
        }
    }

}