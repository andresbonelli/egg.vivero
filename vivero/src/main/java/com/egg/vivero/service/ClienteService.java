package com.egg.vivero.service;

import com.egg.vivero.persistence.ClienteDAO;
import com.egg.vivero.entities.Cliente;
import lombok.RequiredArgsConstructor;

import java.sql.SQLException;

public class ClienteService {

    private final ClienteDAO clienteDAO;

    public ClienteService() {
        this.clienteDAO = new ClienteDAO();
    }

    public void crearCliente(Cliente cliente) throws Exception {
        validarDatosCliente(cliente);
        clienteDAO.guardarCliente(cliente);
    }

    public Cliente buscarClientePorId(int idCliente) throws Exception {
        return clienteDAO.buscarPorId(idCliente);
    }

    public Cliente buscarClientePorCodigo(int codigo) throws Exception {
        return clienteDAO.buscarPorCodigo(codigo);
    }

    public void actualizarCliente(Cliente cliente) throws Exception {
        validarDatosCliente(cliente);
        clienteDAO.actualizar(cliente);
    }

    public void eliminarClientePorCodigo(int codigo) throws Exception {
        clienteDAO.eliminarClientePorCodigo(codigo);
    }

    private void validarDatosCliente(Cliente cliente) throws IllegalArgumentException {
        if (cliente.getNombreCliente() == null || cliente.getNombreCliente().isEmpty()) {
            throw new IllegalArgumentException("El nombre del cliente es requerido.");
        }
    }

}
