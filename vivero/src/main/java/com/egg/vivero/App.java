package com.egg.vivero;

import com.egg.vivero.entities.Cliente;
import com.egg.vivero.persistence.ClienteDAO;
import com.egg.vivero.persistence.ProductoDAO;
import com.egg.vivero.service.ClienteService;

public class App {
    public static void main(String[] args) throws Exception {

        ClienteService clienteService = new ClienteService();
        Cliente newCliente = new Cliente();
        newCliente.setCodigoCliente(9999);
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

        clienteService.crearCliente(newCliente);
        Cliente clienteRecienGuardado = clienteService.buscarClientePorCodigo(9999);
        System.out.println(clienteRecienGuardado);
        clienteService.eliminarClientePorCodigo(9999);

    }
}