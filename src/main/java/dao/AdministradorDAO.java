package dao;




import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import modelos.Administrador;


public class AdministradorDAO {
  
	private DataSource dataSource;

    public AdministradorDAO() throws NamingException {
    	   Context initContext = new InitialContext();
           Context envContext  = (Context)initContext.lookup("java:/comp/env");
           dataSource = (DataSource)envContext.lookup("jdbc/clinicaMedica");
           System.out.println("abriendo base de datos, ADMINISTRADOR");
    }

    
    /////////////////////////////////////////////7
    
    public Administrador logarse(String username, String password)  {
    	Administrador administrador = null;

        String sql = "SELECT * FROM administradores WHERE username = ? AND password = ?";
        try (Connection conn = dataSource.getConnection()){
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, username);
        stmt.setString(2, password);

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            administrador = new Administrador();
            administrador.setId(rs.getInt("id"));
            administrador.setUsername(rs.getString("username"));
            administrador.setPassword(rs.getString("password"));
            administrador.setDni(rs.getString("dni"));
            administrador.setNombre(rs.getString("nombre"));
            administrador.setApellido1(rs.getString("apellido1"));
            administrador.setApellido2(rs.getString("apellido2"));
            administrador.setEmail(rs.getString("email"));
            administrador.setTelefono(rs.getString("telefono"));
            administrador.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
        }
        }
        catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//        System.out.println("login correcto");
        return administrador; // Si no se encuentra paciente, devolverá null
    }




public List<Administrador> getAllAdministradores() throws SQLException {
    List<Administrador> administradores = new ArrayList<>();
    try (Connection conn = dataSource.getConnection()) {
        String sql = "SELECT * FROM administradores";
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
        	Administrador administrador = new Administrador();
            administrador.setId(rs.getInt("id"));
            administrador.setUsername(rs.getString("username"));
            administrador.setPassword(rs.getString("password"));
            administrador.setDni(rs.getString("dni"));
            administrador.setNombre(rs.getString("nombre"));
            administrador.setApellido1(rs.getString("apellido1"));
            administrador.setApellido2(rs.getString("apellido2"));
            administrador.setEmail(rs.getString("email"));
            administrador.setTelefono(rs.getString("telefono"));
            administrador.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
            administradores.add(administrador);
//            System.out.println(paciente.getNombre());
        }
    }
    return administradores;
}

public void insertarAdministrador(Administrador administrador) throws SQLException {
	
    try (Connection conn = dataSource.getConnection()) {
        String query = "INSERT INTO administradores (username, password, dni, nombre, apellido1, apellido2, email, telefono, fecha_nacimiento) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, administrador.getUsername());
        stmt.setString(2, administrador.getPassword());
        stmt.setString(3, administrador.getDni());
        stmt.setString(4, administrador.getNombre());
        stmt.setString(5, administrador.getApellido1());
        stmt.setString(6, administrador.getApellido2());
        stmt.setString(7, administrador.getEmail());
        stmt.setString(8, administrador.getTelefono());
        stmt.setDate(9, new java.sql.Date(administrador.getFechaNacimiento().getTime()));
     
        stmt.executeUpdate();
        System.out.println("insertado correctamente administrador: " + administrador.getNombre() + " " + administrador.getApellido1() + " " + administrador.getApellido2());

    }
}

public void actualizarAdministrador(Administrador administrador) throws SQLException {

    try (Connection conn = dataSource.getConnection()) {
//        String query = "INSERT INTO pacientes (username, password, dni, nombre, apellido1, apellido2, email, telefono, fecha_nacimiento) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
//    	  conn.setAutoCommit(false);
        String sql = "UPDATE administradores SET username = ?, password = ?, dni = ?, nombre = ?, apellido1 = ?, apellido2 = ?, email = ?, telefono = ?, fecha_nacimiento = ? WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);       
        stmt.setString(1, administrador.getUsername());
        stmt.setString(2, administrador.getPassword());
        stmt.setString(3, administrador.getDni());
        stmt.setString(4, administrador.getNombre());
        stmt.setString(5, administrador.getApellido1());
        stmt.setString(6, administrador.getApellido2());
        stmt.setString(7, administrador.getEmail());
        stmt.setString(8, administrador.getTelefono());
        stmt.setDate(9, new java.sql.Date(administrador.getFechaNacimiento().getTime()));
        stmt.setInt(10, administrador.getId());
        stmt.executeUpdate();
        System.out.println("actualizando correctamente administrador: " + administrador.getNombre() + " " + administrador.getApellido1() + " " + administrador.getApellido2());

    }
    
}

public void eliminarAdministrador(int id) throws SQLException {

    try (Connection conn = dataSource.getConnection()) {
        String sql = "DELETE FROM administradores WHERE id = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();
        System.out.println("eliminado correctamente administrador con id: " + id);

    }
}

public Administrador getAdministradorById(int id) throws SQLException {
	Administrador administrador = null;
    try (Connection conn = dataSource.getConnection()) {
        String sql = "SELECT * FROM administradores WHERE id = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            administrador = new Administrador();
            administrador.setId(rs.getInt("id"));
            administrador.setUsername(rs.getString("username"));
            administrador.setPassword(rs.getString("password"));
            administrador.setDni(rs.getString("dni"));
            administrador.setNombre(rs.getString("nombre"));
            administrador.setApellido1(rs.getString("apellido1"));
            administrador.setApellido2(rs.getString("apellido2"));
            administrador.setEmail(rs.getString("email"));
            administrador.setTelefono(rs.getString("telefono"));
            administrador.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
        }
    }
    return administrador;
}
    
}
