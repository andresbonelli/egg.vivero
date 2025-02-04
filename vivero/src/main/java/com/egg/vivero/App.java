package com.egg.vivero;

import com.egg.vivero.entities.Cliente;
import com.egg.vivero.entities.Producto;
import com.egg.vivero.persistence.ClienteDAO;
import com.egg.vivero.persistence.DAO;
import com.egg.vivero.persistence.ProductoDAO;

import java.sql.*;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws Exception {

        ProductoDAO productoDAO = new ProductoDAO();
        ClienteDAO clienteDAO = new ClienteDAO();
        Cliente cliente19 = clienteDAO.buscarClientePorCodigo(19);
        System.out.println(cliente19.toString());
        // Cliente newCliente = new Cliente();
        /*newCliente.setCodigoCliente(9999);
        newCliente.setNombreCliente("Cliente 9999");
        newCliente.setNombreContacto("Nombre 9999");
        newCliente.setApellidoContacto("Apellido 9999");
        newCliente.setTelefono("999999999");
        newCliente.setFax("999999999");
        newCliente.setCiudad("Ciudad 9999");
        newCliente.setRegion("Region 9999");
        newCliente.setPais("Pais 9999");
        newCliente.setCodigoPostal("9999");
        newCliente.setIdEmpleado(1);
        newCliente.setLimiteCredito(9999.99);
        clienteDAO.guardarCliente(newCliente);
        clienteDAO.buscarClientePorCodigo(9999);
        clienteDAO.eliminarClientePorCodigo(9999);*/
    }
}