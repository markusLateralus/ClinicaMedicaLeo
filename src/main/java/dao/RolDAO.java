package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import modelos.Rol;

public class RolDAO {

    private DataSource dataSource;

    public RolDAO() throws NamingException {
 	   Context initContext = new InitialContext();
       Context envContext  = (Context)initContext.lookup("java:/comp/env");
       dataSource = (DataSource)envContext.lookup("jdbc/clinicaMedica");
       System.out.println("abriendo base de datos roles");
    }

    // Método para obtener un rol por su ID
    public Rol getRolById(int id) throws SQLException {
        String sql = "SELECT * FROM roles WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToRol(rs);
                } else {
                    return null; // o lanzar una excepción si el rol no se encuentra
                }
            }
        }
    }

    // Método para obtener todos los roles
    public List<Rol> getAllRoles() throws SQLException {
        String sql = "SELECT * FROM roles";
        List<Rol> roles = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                roles.add(mapResultSetToRol(rs));
            }
        }
        return roles;
    }

    // Método para mapear el ResultSet a un objeto Rol
    private Rol mapResultSetToRol(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String nombre = rs.getString("nombre_rol");
        return new Rol(id, nombre);
    }
}

