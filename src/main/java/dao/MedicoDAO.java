package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import modelos.Horario;
import modelos.Medico;
import modelos.Paciente;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MedicoDAO {

    private DataSource dataSource;

    public MedicoDAO() throws Exception {
        Context initContext = new InitialContext();
        Context envContext  = (Context)initContext.lookup("java:/comp/env");
        dataSource = (DataSource)envContext.lookup("jdbc/clinicaMedica");
//        System.out.println("abriendo base de datos MEDICO");
    }
    
    
    public Medico logarse(String username, String password) {
    	Medico medico = null;

  
        String sql = "SELECT * FROM medicos WHERE username = ? AND password = ?";
        try (Connection conn = dataSource.getConnection()){
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, username);
        stmt.setString(2, password);

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            medico = new Medico();
            medico.setId(rs.getInt("id"));
            medico.setUsername(rs.getString("username"));
            medico.setPassword(rs.getString("password"));
            medico.setDni(rs.getString("dni"));
            medico.setNombre(rs.getString("nombre"));
            medico.setApellido1(rs.getString("apellido1"));
            medico.setApellido2(rs.getString("apellido2"));
            medico.setEspecialidad(rs.getString("especialidad"));
            medico.setEmail(rs.getString("email"));
            medico.setTelefono(rs.getString("telefono"));
            medico.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
            
        }
        }catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
//        System.out.println("login correcto");
        return medico; // Si no se encuentra paciente, devolverá null
    }


    public List<Medico> getAllMedicos() throws SQLException {
        List<Medico> medicos = new ArrayList<>();
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * FROM medicos";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
            	Medico medico = new Medico();
                medico.setId(rs.getInt("id"));
                medico.setUsername(rs.getString("username"));
                medico.setPassword(rs.getString("password"));
                medico.setDni(rs.getString("dni"));
                medico.setNombre(rs.getString("nombre"));
                medico.setApellido1(rs.getString("apellido1"));
                medico.setApellido2(rs.getString("apellido2"));
                medico.setEspecialidad(rs.getString("especialidad"));
                medico.setEmail(rs.getString("email"));
                medico.setTelefono(rs.getString("telefono"));
                medico.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                medicos.add(medico);
//                System.out.println(paciente.getNombre());
            }
        }
        return medicos;
    }

    public int insertarMedico(Medico medico) throws SQLException {
    	   int medicoId = -1;
    	    Connection conn = null;
    	    PreparedStatement stmt = null;

    	    try {
    	        conn = dataSource.getConnection();
    	        conn.setAutoCommit(false); // Desactivamos el autocommit para manejar la transacción.

    	        // Insertar el médico
    	        String insertMedicoQuery = "INSERT INTO medicos (username, password, dni, nombre, apellido1, apellido2, especialidad, email, telefono, fecha_nacimiento) " +
    	                                   "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    	        stmt = conn.prepareStatement(insertMedicoQuery, Statement.RETURN_GENERATED_KEYS);
    	        stmt.setString(1, medico.getUsername());
    	        stmt.setString(2, medico.getPassword());
    	        stmt.setString(3, medico.getDni());
    	        stmt.setString(4, medico.getNombre());
    	        stmt.setString(5, medico.getApellido1());
    	        stmt.setString(6, medico.getApellido2());
    	        stmt.setString(7, medico.getEspecialidad());
    	        stmt.setString(8, medico.getEmail());
    	        stmt.setString(9, medico.getTelefono());
    	        stmt.setDate(10, new java.sql.Date(medico.getFechaNacimiento().getTime()));

    	        int rowsAffected = stmt.executeUpdate();
    	        if (rowsAffected == 0) {
    	            throw new SQLException("No se pudo insertar el médico.");
    	        }

    	        // Obtener el ID del médico recién insertado
    	        try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
    	            if (generatedKeys.next()) {
    	                medicoId = generatedKeys.getInt(1);
    	            } else {
    	                throw new SQLException("No se pudo obtener el ID del nuevo médico.");
    	            }
    	        }

    	        // Insertar los horarios
    	        String insertHorariosQuery = 
    	            "INSERT INTO horarios (medico_id, dia, hora, estado) VALUES " +
    	            "(?, 'Lunes', '09:00:00', 'disponible'), " +
    	            "(?, 'Lunes', '10:00:00', 'disponible'), " +
    	            "(?, 'Lunes', '11:00:00', 'disponible'), " +
    	            "(?, 'Lunes', '12:00:00', 'disponible'), " +
    	            "(?, 'Lunes', '13:00:00', 'disponible'), " +
    	            "(?, 'Martes', '09:00:00', 'disponible'), " +
    	            "(?, 'Martes', '10:00:00', 'disponible'), " +
    	            "(?, 'Martes', '11:00:00', 'disponible'), " +
    	            "(?, 'Martes', '12:00:00', 'disponible'), " +
    	            "(?, 'Martes', '13:00:00', 'disponible'), " +
    	            "(?, 'Miércoles', '09:00:00', 'disponible'), " +
    	            "(?, 'Miércoles', '10:00:00', 'disponible'), " +
    	            "(?, 'Miércoles', '11:00:00', 'disponible'), " +
    	            "(?, 'Miércoles', '12:00:00', 'disponible'), " +
    	            "(?, 'Miércoles', '13:00:00', 'disponible'), " +
    	            "(?, 'Jueves', '09:00:00', 'disponible'), " +
    	            "(?, 'Jueves', '10:00:00', 'disponible'), " +
    	            "(?, 'Jueves', '11:00:00', 'disponible'), " +
    	            "(?, 'Jueves', '12:00:00', 'disponible'), " +
    	            "(?, 'Jueves', '13:00:00', 'disponible'), " +
    	            "(?, 'Viernes', '09:00:00', 'disponible'), " +
    	            "(?, 'Viernes', '10:00:00', 'disponible'), " +
    	            "(?, 'Viernes', '11:00:00', 'disponible'), " +
    	            "(?, 'Viernes', '12:00:00', 'disponible'), " +
    	            "(?, 'Viernes', '13:00:00', 'disponible')";

    	        try (PreparedStatement horariosStmt = conn.prepareStatement(insertHorariosQuery)) {
    	            for (int i = 1; i <= 25; i++) {
    	                horariosStmt.setInt(i, medicoId);
    	            }
    	            horariosStmt.executeUpdate();
    	        }

    	        conn.commit(); // Confirmamos la transacción
    	    } catch (SQLException e) {
    	        if (conn != null) {
    	            try {
						conn.rollback();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} // Revertimos la transacción en caso de error
    	        }
    	        throw e;
    	    } finally {
    	        if (stmt != null) stmt.close();
    	        if (conn != null) conn.close();
    	    }

    	    return medicoId; // Devolvemos el ID del médico recién creado
    	}

    

    public void actualizarMedico(Medico medico) throws SQLException {

        try (Connection conn = dataSource.getConnection()) {
//            String query = "INSERT INTO pacientes (username, password, dni, nombre, apellido1, apellido2, email, telefono, fecha_nacimiento) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            String sql = "UPDATE medicos SET username = ?, password = ?, dni = ?, nombre = ?, apellido1 = ?, apellido2 = ?, especialidad = ?,email = ?, telefono = ?, fecha_nacimiento = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, medico.getUsername());
            stmt.setString(2, medico.getPassword());
            stmt.setString(3, medico.getDni());
            stmt.setString(4, medico.getNombre());
            stmt.setString(5, medico.getApellido1());
            stmt.setString(6, medico.getApellido2());
            stmt.setString(7, medico.getEspecialidad());
            stmt.setString(8, medico.getEmail());
            stmt.setString(9, medico.getTelefono());
            stmt.setDate(10, new java.sql.Date(medico.getFechaNacimiento().getTime()));
            stmt.setInt(11, medico.getId());
            stmt.executeUpdate();
//            System.out.println("actualizando correctamente medico: " + medico.getNombre() + " " + medico.getApellido1() + " " + medico.getApellido2());

        }
    }

    public void eliminarMedico(int id) throws SQLException {
    
        try (Connection conn = dataSource.getConnection()) {
            String sql = "DELETE FROM medicos WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("eliminado correctamente medico con id: " + id);

        }
    }

    public Medico getMedicoById(int id) throws SQLException {
    	Medico medico = null;
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * FROM medicos WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                medico = new Medico();
                medico.setId(rs.getInt("id"));
                medico.setUsername(rs.getString("username"));
                medico.setPassword(rs.getString("password"));
                medico.setDni(rs.getString("dni"));
                medico.setNombre(rs.getString("nombre"));
                medico.setApellido1(rs.getString("apellido1"));
                medico.setApellido2(rs.getString("apellido2"));
                medico.setEspecialidad(rs.getString("especialidad"));
                medico.setEmail(rs.getString("email"));
                medico.setTelefono(rs.getString("telefono"));
                medico.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
            }
        }
        return medico;
    }
  
    // Método para obtener un objeto Medico a partir de sus propiedades
    public Medico obtenerMedico(int id,String username,String password,String dni, String nombre,String apellido1, String apellido2,String especialidad, String email, String telefono, Date fechaNacimiento) {
      
    	Medico medico = null;
    	String query = "SELECT * FROM medicos WHERE id = ?  AND username = ? AND password = ? AND dni = ? AND nombre = ? "
        		+ "AND apellido1 = ? AND apellido2 = ? AND email = ? AND especialidad = ? AND telefono = ? AND fecha_nacimiento = ?";
        try (Connection conn = dataSource.getConnection()) {
        	  PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.setString(2, username);
            stmt.setString(3, password);
            stmt.setString(4, dni);
            stmt.setString(5, nombre);
            stmt.setString(6, apellido1);
            stmt.setString(7, apellido2);
            stmt.setString(8, especialidad);
            stmt.setString(9, email);
            stmt.setString(10, telefono);
            stmt.setDate(11,  new java.sql.Date(fechaNacimiento.getTime()));

       ResultSet rs = stmt.executeQuery(); 
                
              
if (rs.next()) {
	medico = new Medico();
                        medico.setId(rs.getInt("id"));
                        medico.setUsername( rs.getString("username"));
                        medico.setPassword( rs.getString("password"));
                        medico.setDni(rs.getString("dni"));
                        medico.setNombre(rs.getString("nombre"));
                        medico.setApellido1(rs.getString("apellido1"));
                        medico.setApellido2(rs.getString("apellido2"));
                        medico.setEspecialidad(rs.getString("especialidad"));
                        medico.setTelefono(rs.getString("telefono"));
                        medico.setEmail(rs.getString("email"));
                        medico.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                    
                    
            }
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
            return medico;
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

