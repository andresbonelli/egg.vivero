package com.egg.vivero.persistence;

import com.egg.vivero.entities.Producto;

import java.util.ArrayList;
import java.util.List;

public class ProductoDAO extends DAO{
    public Producto buscarProductoPorId(int id) throws Exception {
        consultarDataBase("SELECT * FROM producto WHERE id_producto = " + id);
        Producto producto = new Producto();
        if (resultSet.next()) {
            producto.setIdProducto(resultSet.getInt("id_producto"));
            producto.setCodigoProducto(resultSet.getString("codigo_producto"));
            producto.setNombre(resultSet.getString("nombre"));
            producto.setIdGama(resultSet.getInt("id_gama"));
            producto.setDimensiones(resultSet.getString("dimensiones"));
            producto.setProveedor(resultSet.getString("proveedor"));
            producto.setDescripcion(resultSet.getString("descripcion"));
            producto.setCantidadEnStock(resultSet.getInt("cantidad_en_stock"));
            producto.setPrecioVenta(resultSet.getDouble("precio_venta"));
            producto.setPrecioProveedor(resultSet.getDouble("precio_proveedor"));
        }
        desconectarDataBase();
        return producto;
    }

    public void guardarProducto(Producto producto) throws Exception {
        insertarModificarEliminarDataBase("INSERT INTO producto (codigo_producto, nombre, id_gama, dimensiones, proveedor, descripcion, cantidad_en_stock, precio_venta, precio_proveedor) VALUES ('" + producto.getCodigoProducto() + "', '" + producto.getNombre() + "', " + producto.getIdGama() + ", '" + producto.getDimensiones() + "', '" + producto.getProveedor() + "', '" + producto.getDescripcion() + "', " + producto.getCantidadEnStock() + ", " + producto.getPrecioVenta() + ", " + producto.getPrecioProveedor() + ")");
        desconectarDataBase();
    }

    public List<Producto> listarProductos() throws Exception {
        consultarDataBase("SELECT * FROM producto");
        List<Producto> productos = new ArrayList<>();
        while (resultSet.next()) {
            productos.add(new Producto(
                    resultSet.getInt("id_producto"),
                    resultSet.getString("codigo_producto"),
                    resultSet.getString("nombre"),
                    resultSet.getInt("id_gama"),
                    resultSet.getString("dimensiones"),
                    resultSet.getString("proveedor"),
                    resultSet.getString("descripcion"),
                    resultSet.getInt("cantidad_en_stock"),
                    resultSet.getDouble("precio_venta"),
                    resultSet.getDouble("precio_proveedor")
            ));
        }
        desconectarDataBase();
        return productos;
    }

    public void eliminarProductoPorCodigo(String codigoProducto) throws Exception {
        insertarModificarEliminarDataBase(
                String.format(
                        "DELETE FROM producto WHERE codigo_producto = '%s'",
                        codigoProducto)
        );
    }

}

