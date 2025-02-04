package com.egg.vivero;

import com.egg.vivero.entities.Producto;
import com.egg.vivero.persistence.DAO;
import com.egg.vivero.persistence.ProductoDAO;

import java.sql.*;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws Exception {

        ProductoDAO productoDAO = new ProductoDAO();
        System.out.println(productoDAO.listarProductos());

    }
}