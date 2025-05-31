package com.example.usuario_service.Controller;

import com.example.usuario_service.Entity.Usuario;
import com.example.usuario_service.Service.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioControlador {
    @Autowired
    private UsuarioServicio usuarioServicio;

    //Crear nuevo usuario
    @PostMapping("/nuevousuario")
    public ResponseEntity<Usuario> registrarUsuario(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioServicio.registrarUsuario(usuario.nombre, usuario.rut, usuario.correo, usuario.telefono, usuario.rol, usuario.contrasenia, usuario.fechaNacimiento));
    }

    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestParam String correo, @RequestParam String contrasenia) {
        return ResponseEntity.ok(usuarioServicio.LoginUsuario(correo, contrasenia));
    }

    @GetMapping("/buscarPorRut/{rut}")
    public ResponseEntity<Usuario> buscarPorRut(@PathVariable String rut) {
        return ResponseEntity.ok(usuarioServicio.BuscarPorRut(rut));
    }
}
