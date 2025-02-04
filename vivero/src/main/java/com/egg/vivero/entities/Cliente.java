package com.egg.vivero.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cliente {
    private int idCliente;
    private int codigoCliente;
    private String nombreCliente;
    private String nombreContacto;
    private String apellidoContacto;
    private String telefono;
    private String fax;
    private String ciudad;
    private String region;
    private String pais;
    private String codigoPostal;
    private int idEmpleado;
    private Double limiteCredito;
}
