package com.egg.vivero.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Producto {
    private int idProducto;
    private String codigoProducto;
    private String nombre;
    private int idGama;
    private String dimensiones;
    private String proveedor;
    private String descripcion;
    private int cantidadEnStock;
    private double precioVenta;
    private double precioProveedor;

    public Producto(String codigoProducto, String nombre, int idGama, String dimensiones, String proveedor, String descripcion, int cantidadEnStock, double precioVenta, double precioProveedor) {
        this.codigoProducto = codigoProducto;
        this.nombre = nombre;
        this.idGama = idGama;
        this.dimensiones = dimensiones;
        this.proveedor = proveedor;
        this.descripcion = descripcion;
        this.cantidadEnStock = cantidadEnStock;
        this.precioVenta = precioVenta;
        this.precioProveedor = precioProveedor;
    }
}
