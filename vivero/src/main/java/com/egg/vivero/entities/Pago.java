package com.egg.vivero.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pago {
    private int idPago;
    private int idCliente;
    private String formaPago;
    private int idTransaccion;
    private Date fechaPago;
    private double total;
}
