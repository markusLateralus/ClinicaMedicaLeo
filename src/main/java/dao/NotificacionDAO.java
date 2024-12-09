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

import modelos.Notificacion;


public class NotificacionDAO {

    private DataSource dataSource;

    public NotificacionDAO() throws NamingException {
 	   Context initContext = new InitialContext();
       Context envContext  = (Context)initContext.lookup("java:/comp/env");
       dataSource = (DataSource)envContext.lookup("jdbc/clinicaMedica");
      
    }

    
    public void insertarNotificacion(Notificacion notificacion) throws SQLException {
        System.out.println("insertar NOTIFICIACION DESDE LA BASE DE DATOS!");

        String sql = "INSERT INTO notificaciones (id_paciente, id_medico, mensajePaciente,mensajeMedico, estado) VALUES (?, ?,?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1,notificacion.getIdPaciente());
            stmt.setInt(2,notificacion.getIdMedico());
            stmt.setString(3, notificacion.getMensajePaciente());
            stmt.setString(4, notificacion.getMensajeMedico());
            stmt.setString(5, notificacion.getEstado());
            stmt.executeUpdate();
            System.out.println("insertado correctamente en la tabla Notificacion:"+ notificacion.getMensajePaciente());
        }
    }

 
    public List<Notificacion> obtenerNotificacionesActivasParaPacientes(int idPaciente) throws SQLException {
        List<Notificacion> notificaciones = new ArrayList<>();
        String sql = "SELECT * FROM notificaciones WHERE id_paciente = ? AND estado = 'activo' ORDER BY fecha_creacion DESC";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPaciente);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Notificacion notificacion = new Notificacion();
                notificacion.setId(rs.getInt("id"));
                notificacion.setIdPaciente(rs.getInt("id_paciente"));
                notificacion.setIdMedico(rs.getInt("id_medico"));
                notificacion.setMensajePaciente(rs.getString("mensajePaciente"));
                notificacion.setMensajeMedico(rs.getString("mensajeMedico"));
                notificacion.setEstado(rs.getString("estado"));
                notificacion.setFechaCreacion(rs.getTimestamp("fecha_creacion"));
                notificaciones.add(notificacion);
            }
        }
        return notificaciones;
    }
    public void marcarNotificacionComoInactivaParaPaciente(int idNotificacion)  {
        String sql = "UPDATE notificaciones SET mensajePaciente = '' WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idNotificacion);
            stmt.executeUpdate();
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public void marcarNotificacionComoInactivaParaMedico(int idNotificacion)  {
        String sql = "UPDATE notificaciones SET mensajeMedico = '' WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idNotificacion);
            stmt.executeUpdate();
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void marcarNotificacionComoInactiva2(int idNotificacion)  {
        String sql = "UPDATE notificaciones SET estado = 'inactivo' WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idNotificacion);
            stmt.executeUpdate();
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }


    public List<Notificacion> obtenerNotificacionesActivasParaMedicos(int idMedico){
        List<Notificacion> notificaciones = new ArrayList<>();
        String sql = "SELECT * FROM notificaciones WHERE id_medico = ? AND estado = 'activo' ORDER BY fecha_creacion DESC";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idMedico);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Notificacion notificacion = new Notificacion();
                notificacion.setId(rs.getInt("id"));
                notificacion.setIdPaciente(rs.getInt("id_paciente"));
                notificacion.setIdMedico(rs.getInt("id_medico"));
                notificacion.setMensajePaciente(rs.getString("mensajePaciente"));
                notificacion.setMensajeMedico(rs.getString("mensajeMedico"));
                notificacion.setEstado(rs.getString("estado"));
                notificacion.setFechaCreacion(rs.getTimestamp("fecha_creacion"));
                notificaciones.add(notificacion);
            }
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return notificaciones;
    }


	public boolean eliminarNotificacion(int idMedico) throws SQLException {
    String query = "DELETE FROM notificaciones WHERE id_medico = ?";
    try (Connection conn = dataSource.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setInt(1, idMedico);
        int rowsAffected = stmt.executeUpdate();
        return rowsAffected > 0;
    }
}

}

