package com.egg.vivero.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Empleado {
    private int idEmpleado;
    private int codigoEmpleado;
    private String nombre;
    private String apellido;
    private String extension;
    private String email;
    private int idOficina;
    private int idJefe;
}
