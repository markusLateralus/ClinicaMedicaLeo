package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import modelos.Paciente;

import modelos.Administrador;
import modelos.Horario;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class PacienteDAO {

    private DataSource dataSource;

    public PacienteDAO() throws Exception {
        Context initContext = new InitialContext();
        Context envContext  = (Context)initContext.lookup("java:/comp/env");
        dataSource = (DataSource)envContext.lookup("jdbc/clinicaMedica");
//        System.out.println("abriendo base de datos paciente");
    }
    
    
   

        public Paciente logarse(String username, String password)  {
            Paciente paciente = null;

            String sql = "SELECT * FROM pacientes WHERE username = ? AND password = ?";
            try (Connection conn = dataSource.getConnection()){
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                paciente = new Paciente();
                paciente.setId(rs.getInt("id"));
                paciente.setUsername(rs.getString("username"));
                paciente.setPassword(rs.getString("password"));
                paciente.setDni(rs.getString("dni"));
                paciente.setNombre(rs.getString("nombre"));
                paciente.setApellido1(rs.getString("apellido1"));
                paciente.setApellido2(rs.getString("apellido2"));
                paciente.setEmail(rs.getString("email"));
                paciente.setTelefono(rs.getString("telefono"));
                paciente.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
            }
            }
            catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//            System.out.println("login correcto");
            return paciente; // Si no se encuentra paciente, devolverá null
        }
    

    

    public List<Paciente> getAllPacientes() throws SQLException {
        List<Paciente> pacientes = new ArrayList<>();
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * FROM pacientes";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Paciente paciente = new Paciente();
                paciente.setId(rs.getInt("id"));
                paciente.setUsername(rs.getString("username"));
                paciente.setPassword(rs.getString("password"));
                paciente.setDni(rs.getString("dni"));
                paciente.setNombre(rs.getString("nombre"));
                paciente.setApellido1(rs.getString("apellido1"));
                paciente.setApellido2(rs.getString("apellido2"));
                paciente.setEmail(rs.getString("email"));
                paciente.setTelefono(rs.getString("telefono"));
                paciente.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                pacientes.add(paciente);
//                System.out.println(paciente.getNombre());
            }
        }
        return pacientes;
    }

    public void insertarPaciente(Paciente paciente) throws SQLException {
    	
        try (Connection conn = dataSource.getConnection()) {
            String query = "INSERT INTO pacientes (username, password, dni, nombre, apellido1, apellido2, email, telefono, fecha_nacimiento) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, paciente.getUsername());
            stmt.setString(2, paciente.getPassword());
            stmt.setString(3, paciente.getDni());
            stmt.setString(4, paciente.getNombre());
            stmt.setString(5, paciente.getApellido1());
            stmt.setString(6, paciente.getApellido2());
            stmt.setString(7, paciente.getEmail());
            stmt.setString(8, paciente.getTelefono());
            stmt.setDate(9, new java.sql.Date(paciente.getFechaNacimiento().getTime()));
         
            stmt.executeUpdate();
            System.out.println("insertado correctamente paciente: " + paciente.getNombre() + " " + paciente.getApellido1() + " " + paciente.getApellido2());

        }
    }

    public void actualizarPaciente(Paciente paciente) throws SQLException {

        try (Connection conn = dataSource.getConnection()) {
//            String query = "INSERT INTO pacientes (username, password, dni, nombre, apellido1, apellido2, email, telefono, fecha_nacimiento) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
//        	  conn.setAutoCommit(false);
            String sql = "UPDATE pacientes SET username = ?, password = ?, dni = ?, nombre = ?, apellido1 = ?, apellido2 = ?, email = ?, telefono = ?, fecha_nacimiento = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);       
            stmt.setString(1, paciente.getUsername());
            stmt.setString(2, paciente.getPassword());
            stmt.setString(3, paciente.getDni());
            stmt.setString(4, paciente.getNombre());
            stmt.setString(5, paciente.getApellido1());
            stmt.setString(6, paciente.getApellido2());
            stmt.setString(7, paciente.getEmail());
            stmt.setString(8, paciente.getTelefono());
            stmt.setDate(9, new java.sql.Date(paciente.getFechaNacimiento().getTime()));
            stmt.setInt(10, paciente.getId());
            stmt.executeUpdate();
            System.out.println("actualizando correctamente paciente: " + paciente.getNombre() + " " + paciente.getApellido1() + " " + paciente.getApellido2());

        }
        
    }

    public void eliminarPaciente(int id) throws SQLException {
    
        try (Connection conn = dataSource.getConnection()) {
            String sql = "DELETE FROM pacientes WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("eliminado correctamente paciente con id: " + id);

        }
    }

    public Paciente getPacienteById(int id) throws SQLException {
        Paciente paciente = null;
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * FROM pacientes WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                paciente = new Paciente();
                paciente.setId(rs.getInt("id"));
                paciente.setUsername(rs.getString("username"));
                paciente.setPassword(rs.getString("password"));
                paciente.setDni(rs.getString("dni"));
                paciente.setNombre(rs.getString("nombre"));
                paciente.setApellido1(rs.getString("apellido1"));
                paciente.setApellido2(rs.getString("apellido2"));
                paciente.setEmail(rs.getString("email"));
                paciente.setTelefono(rs.getString("telefono"));
                paciente.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
            }
        }
        return paciente;
    }
    
    public List<Horario> obtenerHorariosPorMedico(int medicoId) {
        List<Horario> horarios = new ArrayList<>();
        String query = "SELECT * FROM horarios WHERE medico_id = ?";
        try (Connection conn = dataSource.getConnection()){
             PreparedStatement stmt = conn.prepareStatement(query); 
            stmt.setInt(1, medicoId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Horario horario = new Horario();
                    horario.setId(rs.getInt("id"));
                    horario.setMedicoId(rs.getInt("medico_id"));
                    horario.setDia(rs.getString("dia"));
                    horario.setHora(rs.getString("hora"));
                    horario.setEstado(rs.getString("estado"));
                    horarios.add(horario);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return horarios;
    }
}

