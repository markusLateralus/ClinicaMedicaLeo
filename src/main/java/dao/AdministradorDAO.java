package dao;




import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import modelos.Medico;
import modelos.Paciente;


public class AdministradorDAO {
  
	private DataSource dataSource;

    public AdministradorDAO() throws NamingException {
    	   Context initContext = new InitialContext();
           Context envContext  = (Context)initContext.lookup("java:/comp/env");
           dataSource = (DataSource)envContext.lookup("jdbc/clinicaMedica");
//           System.out.println("abriendo base de datos, ADMINISTRADOR");
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


public boolean verificarAccesoMedico(int idAdministrador, int idMedico) {
    String sql = "SELECT COUNT(*) FROM administradores_medicos WHERE id_administrador = ? AND id_medico = ?";

    try (Connection conn = dataSource.getConnection()) {
     
PreparedStatement stmt = conn.prepareStatement(sql);
         
        stmt.setInt(1, idAdministrador);
        stmt.setInt(2, idMedico);

        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) > 0;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}
public boolean verificarAccesoPaciente(int idAdministrador, int idPaciente) {
    String sql = "SELECT COUNT(*) FROM administradores_pacientes WHERE id_administrador = ? AND id_paciente = ?";

    try (Connection conn = dataSource.getConnection()) {
     
PreparedStatement stmt = conn.prepareStatement(sql);
         
        stmt.setInt(1, idAdministrador);
        stmt.setInt(2, idPaciente);

        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) > 0;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
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
public boolean actualizarMedico(int idAdministrador,Medico medico) throws SQLException {
    if (!verificarAccesoMedico(idAdministrador, medico.getId())) {
        System.out.println("El administrador no tiene permiso para editar los datos del medico.");
        return false;
    }
	//SÍ TIENE PERMISOS...MODIFICAMOS

    try (Connection conn = dataSource.getConnection()) {

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
        System.out.println("actualizando correctamente medico: " + medico.getNombre() + " " + medico.getApellido1() + " " + medico.getApellido2());

    }
    return false;
}


// Método para asignar un paciente a un médico
public boolean asignarPermisosMedico(int idAdministrador, int idMedico) throws SQLException {
    System.out.println("id admin " + idAdministrador + " id medico " + idMedico);
    String query = "INSERT INTO administradores_medicos (id_administrador, id_medico) VALUES (?, ?)";
    try (Connection conn = dataSource.getConnection()) {
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, idAdministrador);
        stmt.setInt(2, idMedico);
        return stmt.executeUpdate() > 0;
    } catch (SQLException e) {
        
    
if (e.getErrorCode() == 1062) {  // Código de error para entrada duplicada
            System.out.println("El adminitrador ya tiene permiso para editar este medico.");
            return false;
        } else {
            throw e;
        }
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
public boolean asignarPermisosPaciente(int idAdministrador, int idPaciente) throws SQLException {
    System.out.println("id admin " + idAdministrador + " id paciente " + idPaciente);
    String query = "INSERT INTO administradores_pacientes (id_administrador, id_paciente) VALUES (?, ?)";
    try (Connection conn = dataSource.getConnection()) {
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, idAdministrador);
        stmt.setInt(2, idPaciente);
        return stmt.executeUpdate() > 0;
    } catch (SQLException e) {
        
    
if (e.getErrorCode() == 1062) {  // Código de error para entrada duplicada
            System.out.println("El adminitrador ya tiene permiso para editar este paciente.");
            return false;
        } else {
            throw e;
        }
    }
}
public boolean actualizarPaciente(int idAdministrador,Paciente paciente) throws SQLException {
    if (!verificarAccesoPaciente(idAdministrador, paciente.getId())) {
        System.out.println("El administrador no tiene permiso para editar los datos del paciente.");
        return false;
    }
	//SÍ TIENE PERMISOS...MODIFICAMOS

    try (Connection conn = dataSource.getConnection()) {

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
    return false;
}
public int insertarPaciente(Paciente paciente) throws SQLException {
	
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
    
    
    Paciente  pacienteConsultado= null;
    try (Connection conn = dataSource.getConnection()) {
        String sql = "SELECT * FROM pacientes ORDER BY id DESC LIMIT 1";
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
        	pacienteConsultado = new Paciente();
        	pacienteConsultado.setId(rs.getInt("id"));
        	pacienteConsultado.setUsername(rs.getString("username"));
        	pacienteConsultado.setPassword(rs.getString("password"));
        	pacienteConsultado.setDni(rs.getString("dni"));
        	pacienteConsultado.setNombre(rs.getString("nombre"));
        	pacienteConsultado.setApellido1(rs.getString("apellido1"));
        	pacienteConsultado.setApellido2(rs.getString("apellido2"));
        	pacienteConsultado.setEmail(rs.getString("email"));
        	pacienteConsultado.setTelefono(rs.getString("telefono"));
        	pacienteConsultado.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
        }
    }
    System.out.println("BASES DE DATOS PACIENTE ID " + pacienteConsultado.getId());
    return pacienteConsultado.getId();
    
    
    
    
    
}
public boolean eliminarMedico(int idAdministrador,Medico medicoEliminado) throws SQLException {
	 if (!verificarAccesoMedico(idAdministrador, medicoEliminado.getId())) {
	        System.out.println("El médico no tiene permiso para editar los datos del paciente.");
	        return false;
	    }
	 
	    try (Connection conn = dataSource.getConnection()) {
	 String query="DELETE FROM administradores_medicos WHERE id_administrador = ? AND id_medico = ?";
     PreparedStatement statement = conn.prepareStatement(query);
     statement.setInt(1, idAdministrador);
     statement.setInt(2, medicoEliminado.getId());
     statement.executeUpdate();
	    }
     
    try (Connection conn = dataSource.getConnection()) {
        String sql = "DELETE FROM medicos WHERE id = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, medicoEliminado.getId());
        statement.executeUpdate();
        System.out.println("eliminado correctamente medico con id: " + medicoEliminado.getId());

    }
    return false;
}
public boolean eliminarPaciente(int idAdministrador,Paciente pacienteEliminado) throws SQLException {
	 if (!verificarAccesoPaciente(idAdministrador, pacienteEliminado.getId())) {
	        System.out.println("El administrador no tiene permiso para editar los datos del paciente.");
	        return false;
	    }
	 
	    try (Connection conn = dataSource.getConnection()) {
	 String query="DELETE FROM administradores_pacientes WHERE id_administrador = ? AND id_paciente = ?";
    PreparedStatement statement = conn.prepareStatement(query);
    statement.setInt(1, idAdministrador);
    statement.setInt(2, pacienteEliminado.getId());
    statement.executeUpdate();
	    }
    
   try (Connection conn = dataSource.getConnection()) {
       String sql = "DELETE FROM pacientes WHERE id = ?";
       PreparedStatement statement = conn.prepareStatement(sql);
       statement.setInt(1, pacienteEliminado.getId());
       statement.executeUpdate();
       System.out.println("eliminado correctamente paciente con id: " + pacienteEliminado.getId());

   }
   return false;
}
	
}
