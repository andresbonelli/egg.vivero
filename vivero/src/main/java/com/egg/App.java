package com.egg;

import java.sql.*;

public class App {
    public static void main(String[] args) throws Exception {
        Connection connection = getConnection();
        showClients(connection);
        buscarClientePorCodigo(connection, 9);
        buscarClientesPorEmpleado(connection, 5);
        getProductListByGama(connection);
        getProductosNoComprados(connection);
        getPedidosPorProducto(connection,1);
        getPedidosPorFechas(connection, "2009-01-01", "2010-01-01");
        getPedidosPorCliente(connection, 7);
        closeConnection(connection);
    }

    private static Connection getConnection() {
        String host = "127.0.0.1"; // localhost
        String port = "3306"; // por defecto es el puerto que utiliza
        String name = "root"; // usuario de la base de datos
        String password = ""; // password de la base de datos
        String database = "vivero"; // nombre de la base de datos recien creada, en este caso vivero.
        // Esto especifica una zona horaria, no es obligatorio de utilizar, pero en
        // algunas zonas genera conflictos de conexión si no existiera
        String zona = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String url = "jdbc:mysql://" + host + ":" + port + "/" + database + zona;
        // esto indica la ruta de conexion, que es la combinacion de
        // host,usuario,puerto, nombre de la base de datos a la cual queremos
        // conectarnos y la zona horaria (si se precisara).

        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, name, password);
            System.out.println("Conexión exitosa a la base de datos.");
        } catch (ClassNotFoundException e) {
            System.out.println("Error al cargar el conector JDBC: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
        return connection;
    }

    private static void showClients(Connection connection) {
        String sql = "SELECT id_cliente, nombre_contacto, apellido_contacto, telefono FROM cliente";
        System.out.println("\nlista de clientes: ");
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Integer id = rs.getInt("id_cliente");
                String nombre = rs.getString("nombre_contacto");
                String apellido = rs.getString("apellido_contacto");
                String telefono = rs.getString("telefono");
                System.out.println(id + " - " + nombre + " " + apellido + " -  " + telefono);
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
    }

    private static void buscarClientePorCodigo(Connection connection, Integer clientCode) {
        String sql = "SELECT * FROM cliente WHERE codigo_cliente = ?";
        System.out.printf("\nlista de clientes por codigo %d: \n",clientCode);
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, clientCode);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt("id_cliente");
                String nombreCliente = rs.getString("nombre_cliente");
                System.out.println(id + " - " + nombreCliente);
            }
            // Cerrar ResultSet y Statement dentro del bloque try-catch-finally
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
    }

     private static void buscarClientesPorEmpleado(Connection connection, Integer codigoEmpleado) {
         String sql = """
                         SELECT c.id_cliente, c.nombre_cliente, e.nombre FROM cliente c
                         JOIN empleado e ON e.id_empleado = c.id_empleado
                         WHERE c.id_empleado = ?
                     """;
         System.out.printf("\nLista de clientes del empleado con ID %d: \n", codigoEmpleado);
         try {
             PreparedStatement ps = connection.prepareStatement(sql);
             ps.setInt(1, codigoEmpleado);
             ResultSet rs = ps.executeQuery();
             while (rs.next()) {
                 Integer id = rs.getInt("id_cliente");
                 String nombreCliente = rs.getString("nombre_cliente");
                 String nombreEmpleado = rs.getString("nombre");
                 System.out.println(id + " - " + nombreCliente + " - " + nombreEmpleado);
             }
             // Cerrar ResultSet y Statement dentro del bloque try-catch-finally
             rs.close();
             ps.close();
         } catch (SQLException e) {
             System.out.println("Error en la consulta: " + e.getMessage());
         }
     }

     public static void getProductListByGama(Connection connection) {
        String sql = """
                        SELECT SUM(p.id_producto) as cantidad, g.gama as nombre_gama
                        FROM producto p
                        JOIN gama_producto g ON g.id_gama = p.id_gama
                        GROUP BY nombre_gama;
                     """;
        System.out.println("\nLista de productos por gama: \n");
        try(Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Integer cantidad = rs.getInt("cantidad");
                String gama = rs.getString("nombre_gama");
                System.out.println("Cantidad "+cantidad + " - " + "Gama "+gama);
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
     }

     private static void getProductosNoComprados(Connection connection) {
        String sql = """
                        SELECT p.nombre as nombre
                        FROM producto p
                        WHERE NOT EXISTS (
                        SELECT 1 FROM detalle_pedido d WHERE d.id_producto = p.id_producto
                        );
                     """;
         try(Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
             System.out.println("\nProductos No comprados: ");
             while (rs.next()) {
                 String nombre = rs.getString("nombre");
                 System.out.println(nombre);
             }
         } catch (SQLException e) {
             System.out.println("Error en la consulta: " + e.getMessage());
         }
     }

     private static void getPedidosPorProducto(Connection connection, Integer idProducto) {
        String sql = """
                        SELECT p.fecha_pedido, p.estado, p.comentarios
                        FROM pedido p
                        JOIN detalle_pedido d ON d.id_pedido = p.id_pedido
                        WHERE d.id_producto = ?;
                     """;
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idProducto);
            ResultSet rs = ps.executeQuery();
            System.out.println("\nPedidos por producto: ");
            while (rs.next()) {
                String fecha = rs.getString("fecha_pedido");
                String estado = rs.getString("estado");
                String comentarios = rs.getString("comentarios");
                System.out.println(fecha + " - " + estado + " - " + comentarios);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
     }

     private static void getPedidosPorFechas(Connection connection, String fechaInicio, String fechaFin) {
        String sql = """
                        SELECT p.fecha_pedido, p.estado, p.comentarios
                        FROM pedido p
                        WHERE p.fecha_pedido BETWEEN ? AND ?
                        ORDER BY p.fecha_pedido DESC
                        LIMIT 100;
                     """;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, fechaInicio);
            ps.setString(2, fechaFin);
            ResultSet rs = ps.executeQuery();
            System.out.printf("\nPedidos realizados entre %s y %s: \n",fechaInicio,fechaFin);
            while (rs.next()) {
                String fecha = rs.getString("fecha_pedido");
                String estado = rs.getString("estado");
                String comentarios = rs.getString("comentarios");
                System.out.println(fecha + " - " + estado + " - " + comentarios);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
     }

     private static void getPedidosPorCliente(Connection connection, Integer idCliente) {
        String sql2 = """
                        SELECT nombre_cliente FROM cliente WHERE id_cliente = ?
                     """;
        String sql = """
                        SELECT p.fecha_pedido, p.estado, p.comentarios
                        FROM pedido p
                        JOIN cliente c ON c.id_cliente = p.id_cliente
                        WHERE c.id_cliente = ?
                        ORDER BY p.fecha_pedido DESC
                        LIMIT 100;
                     """;
        try {
            PreparedStatement ps2 = connection.prepareStatement(sql2);
            ps2.setInt(1, idCliente);
            ResultSet rs2 = ps2.executeQuery();
            while (rs2.next()) {
                String nombre = rs2.getString("nombre_cliente");
                System.out.println("\nPedidos realizados por "+nombre+": ");
            }
            rs2.close();
            ps2.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idCliente);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String fecha = rs.getString("fecha_pedido");
                String estado = rs.getString("estado");
                String comentarios = rs.getString("comentarios");
                System.out.println(fecha + " - " + estado + " - " + comentarios);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
     }

    private static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("La conexión a la base de datos fue cerrada de manera exitosa");
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión:" + e.getMessage());
            }
        }
    }
}