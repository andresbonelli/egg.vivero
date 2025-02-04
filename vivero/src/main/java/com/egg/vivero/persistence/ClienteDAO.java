package com.egg.vivero.persistence;

import com.egg.vivero.entities.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteDAO extends DAO{

    public void guardarCliente(Cliente cliente) throws Exception {
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente no puede ser nulo");
        }
        String sql = String.format("""
                INSERT INTO cliente (
                        codigo_cliente,
                        nombre_cliente,
                        nombre_contacto,
                        apellido_contacto,
                        telefono,
                        fax,
                        ciudad,
                        region,
                        pais,
                        codigo_postal,
                        id_empleado,
                        limite_credito
                        ) VALUES ('%d','%s','%s','%s','%s','%s','%s','%s','%s','%s','%d','%f')""",
                cliente.getCodigoCliente(),
                cliente.getNombreCliente(),
                cliente.getNombreContacto(),
                cliente.getApellidoContacto(),
                cliente.getTelefono(),
                cliente.getFax(),
                cliente.getCiudad(),
                cliente.getRegion(),
                cliente.getPais(),
                cliente.getCodigoPostal(),
                cliente.getIdEmpleado(),
                cliente.getLimiteCredito()
        );
        insertarModificarEliminarDataBase(sql);
    }

    public List<Cliente> listarClientes() throws Exception {
        String sql = "SELECT id_cliente, nombre_contacto, apellido_contacto FROM cliente";
        consultarDataBase(sql);
        List<Cliente> clientes = new ArrayList<>();
        while (resultSet.next()) {
            System.out.print(resultSet.getInt("id_cliente")+" - ");
            System.out.print(resultSet.getString("nombre_contacto")+" - ");
            System.out.print(resultSet.getString("apellido_contacto"));
            System.out.println();
            /*Cliente cliente = new Cliente();
            cliente.setIdCliente(resultSet.getInt("id_cliente"));
            cliente.setNombreContacto(resultSet.getString("nombre_contacto"));
            cliente.setApellidoContacto(resultSet.getString("apellido_contacto"));
            clientes.add(cliente);*/
        }
        return clientes;
    }

    public Cliente buscarClientePorCodigo(int codigo) throws Exception {
        String sql = String.format("SELECT * FROM cliente WHERE codigo_cliente = '%d'", codigo);
        consultarDataBase(sql);
        Cliente cliente = new Cliente();
        while (resultSet.next()) {
            cliente.setIdCliente(resultSet.getInt("id_cliente"));
            cliente.setCodigoCliente(resultSet.getInt("codigo_cliente"));
            cliente.setNombreCliente(resultSet.getString("nombre_cliente"));
            cliente.setNombreContacto(resultSet.getString("nombre_contacto"));
            cliente.setApellidoContacto(resultSet.getString("apellido_contacto"));
            cliente.setTelefono(resultSet.getString("telefono"));
            cliente.setFax(resultSet.getString("fax"));
            cliente.setCiudad(resultSet.getString("ciudad"));
            cliente.setRegion(resultSet.getString("region"));
            cliente.setPais(resultSet.getString("pais"));
            cliente.setCodigoPostal(resultSet.getString("codigo_postal"));
            cliente.setIdEmpleado(resultSet.getInt("id_empleado"));
            cliente.setLimiteCredito(resultSet.getDouble("limite_credito"));
        }
        return cliente;
    }

    public void eliminarClientePorCodigo(int codigo) throws Exception {
        String sql = String.format("DELETE FROM cliente WHERE codigo_cliente = '%d'", codigo);
        insertarModificarEliminarDataBase(sql);
        System.out.println("Cliente eliminado");
    }
}
