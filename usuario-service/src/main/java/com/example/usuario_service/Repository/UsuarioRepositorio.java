package com.example.usuario_service.Repository;

import com.example.usuario_service.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
    Usuario findByCorreo(String email);
    Usuario findByRut(String rut);
}
