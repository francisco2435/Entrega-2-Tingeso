package com.example.descuentoClienFrec_service.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    private Long id;
    public String rut;
    public String nombre;
    public String correo;
    public String telefono;
    public String rol;
    public String contrasenia;
    public LocalDate fechaNacimiento;
}
