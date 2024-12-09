package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import modelos.Horario;
import modelos.Medico;
import modelos.Notificacion;

public class HorarioDAO {
	
	
    private DataSource dataSource;

    public HorarioDAO() throws Exception {
        Context initContext = new InitialContext();
        Context envContext  = (Context)initContext.lookup("java:/comp/env");
        dataSource = (DataSource)envContext.lookup("jdbc/clinicaMedica");
//        System.out.println("abriendo horarios");
    }
    
	
	
	
    // Método para reservar un horario
    public boolean reservarHorario(int horarioId) {
        String query = "UPDATE horarios SET estado = 'ocupado' WHERE id = ?";
        	System.out.println("reservar horario " + horarioId);
        try (Connection conn = dataSource.getConnection()){
             PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, horarioId);
            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    // Método para reservar un horario
    public boolean reservarHorario(Horario horario) {
//        String query = "UPDATE horarios SET medico_id = ? , dia = ? , hora = ?, estado =?, paciente_id =? WHERE id = ?";
        String query = "UPDATE horarios SET medico_id = ?, dia = ?, hora = ?, estado = ?, paciente_id = ? WHERE id = ?";

        try (Connection conn = dataSource.getConnection()){
             PreparedStatement stmt = conn.prepareStatement(query);
             stmt.setInt(1, horario.getMedicoId());
             stmt.setString(2, horario.getDia());
             stmt.setString(3, horario.getHora());
             stmt.setString(4, horario.getEstado());
             stmt.setInt(5, horario.getPacienteId());
            stmt.setInt(6, horario.getId());
            int filasAfectadas = stmt.executeUpdate();
            System.out.println("actualizando correctamente horario: " + horario.getId() + " dia " + horario.getDia() + " hora " + horario.getHora() + " estado " + horario.getEstado()
            + " id medico " + horario.getMedicoId() + " idPaciente " + horario.getPacienteId());

            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
//        stmt.setInt(1, horario.getMedicoId());
//        stmt.setString(2, horario.getDia());
//        stmt.setString(3, horario.getHora());
//        stmt.setString(4, horario.getEstado());
//        stmt.setInt(5, horario.getPacienteId());
//       stmt.setInt(6, horario.getId());
//       stmt.executeUpdate();

    }
    
    
    
    public Horario getHorarioById(int id) {
    	Horario horarioConsultado=null;
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * FROM horarios ORDER BY id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
            	horarioConsultado = new Horario();
            	horarioConsultado.setId(rs.getInt("id"));
            	horarioConsultado.setMedicoId(rs.getInt("medico_id"));
            	horarioConsultado.setDia(rs.getString("dia"));
            	horarioConsultado.setHora(rs.getString("hora"));
            	horarioConsultado.setEstado(rs.getString("estado"));

            }
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//        System.out.println("BASES DE DATOS MEDICO ID " + horarioConsultado.getId());
        return horarioConsultado;
    }
    
    public boolean cancelarCita(int idHorario, int idMedico, int idPaciente) throws SQLException {

//    	System.out.println("cancelar cita id " + idHorario + " id medico " +idMedico + " id paci " + idPaciente);
//    	String query = "UPDATE horarios SET estado = 'disponible' WHERE id = ? AND paciente_id = ? AND medico_id = ?";
    	String query = "UPDATE horarios SET estado = 'disponible' , paciente_id= null WHERE id = ? AND paciente_id = ? AND medico_id = ?";

    	try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idHorario);
            stmt.setInt(2, idPaciente);
            stmt.setInt(3, idMedico);
            int rowsAffected = stmt.executeUpdate();
            System.out.println("Se ha cancelado la cita correctamente");
            return rowsAffected > 0;
          
        }
    }




	public List<Horario> obtenerHorariosPorPaciente(int idPaciente) {
	       List<Horario> horarios = new ArrayList<>();
	        String sql = "SELECT * FROM horarios WHERE paciente_id = ? AND estado = 'ocupado' ORDER BY id DESC";
	        try (Connection conn = dataSource.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setInt(1, idPaciente);
	            ResultSet rs = stmt.executeQuery();
	            while (rs.next()) {
	            	Horario horarioConsultado = new Horario();
	            	horarioConsultado.setId(rs.getInt("id"));
	            	horarioConsultado.setMedicoId(rs.getInt("medico_id"));
	            	horarioConsultado.setPacienteId(rs.getInt("paciente_id"));
	            	horarioConsultado.setDia(rs.getString("dia"));
	            	horarioConsultado.setHora(rs.getString("hora"));
	            	horarioConsultado.setEstado(rs.getString("estado"));
	                horarios.add(horarioConsultado);
	       
	            }
	        } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        return horarios;
	    }


	public List<Horario> obtenerHorariosPorMedico(int idMedico) {
  

	       List<Horario> horarios = new ArrayList<>();
	        String sql = "SELECT * FROM horarios WHERE medico_id = ? AND estado = 'ocupado' ORDER BY id DESC";
	        try (Connection conn = dataSource.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setInt(1, idMedico);
	            ResultSet rs = stmt.executeQuery();
	            while (rs.next()) {
	            	Horario horarioConsultado = new Horario();
	            	horarioConsultado.setId(rs.getInt("id"));
	            	horarioConsultado.setMedicoId(rs.getInt("medico_id"));
	            	horarioConsultado.setPacienteId(rs.getInt("paciente_id"));
	            	horarioConsultado.setDia(rs.getString("dia"));
	            	horarioConsultado.setHora(rs.getString("hora"));
	            	horarioConsultado.setEstado(rs.getString("estado"));
	                horarios.add(horarioConsultado);
	              	System.out.println("obtener horario por medico id medico " +horarioConsultado.getDia() + " HORA "+ horarioConsultado.getHora() + " estado " + horarioConsultado.getEstado());
	            }
	        } catch (SQLException e) {
				// TODO Auto-generated catch block 
				e.printStackTrace();
			}
	        return horarios;
	    }

//	public List<Horario> obtenerCitasActivasParaPacientes(int idMedico) {
//	        List<Horario> horarios = new ArrayList<>();
//	        String sql = "SELECT * FROM horarios WHERE id_medico = ? AND estado = 'activo' ORDER BY fecha_creacion DESC";
//	        try (Connection conn = dataSource.getConnection();
//	             PreparedStatement stmt = conn.prepareStatement(sql)) {
//	            stmt.setInt(1, idMedico);
//	            ResultSet rs = stmt.executeQuery();
//	            while (rs.next()) {
//	            	Horario horario = new Horario();
//	                horario.setId(rs.getInt("id"));
//	                horario.setIdPaciente(rs.getInt("id_paciente"));
//	                horario.setIdMedico(rs.getInt("id_medico"));
//	                horario.setMensajePaciente(rs.getString("mensajePaciente"));
//	                horario.setMensajeMedico(rs.getString("mensajeMedico"));
//	                horario.setEstado(rs.getString("estado"));
//	                horario.setFechaCreacion(rs.getTimestamp("fecha_creacion"));
//	                horarios.add(horario);
//	            }
//	        }
//	        return horarios;
//	    }

    
    
}