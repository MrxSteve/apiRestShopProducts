package com.backend.utils;

import com.backend.models.entities.RolEntity;
import com.backend.models.entities.UsuarioEntity;
import com.backend.models.repositories.RolRepository;
import com.backend.models.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer {
    @Value("${ADMIN_USER}")
    private String adminUsername;

    @Value("${ADMIN_PASSWORD}")
    private String adminPass;

//    @Bean
//    CommandLineRunner initData(RolRepository rolRepository, UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
//        return args -> {
//            // Crear roles
//            RolEntity adminRole = new RolEntity();
//            adminRole.setNombre("ROLE_ADMIN");
//            rolRepository.save(adminRole);
//
//            RolEntity userRole = new RolEntity();
//            userRole.setNombre("ROLE_USER");
//            rolRepository.save(userRole);
//
//            // Crear usuario admin
//            UsuarioEntity admin = new UsuarioEntity();
//            admin.setUsername(adminUsername);
//            admin.setPassword(passwordEncoder.encode(adminPass));
//            admin.setRoles(List.of(adminRole));
//            usuarioRepository.save(admin);
//
//            /* Crear usuario normal
//            * UsuarioEntity user = new UsuarioEntity();
//            * user.setUsername("user");
//            * user.setPassword(passwordEncoder.encode("user"));
//            * user.setRoles(List.of(userRole));
//            * usuarioRepository.save(user);
//            */
//        };
//    }
}
