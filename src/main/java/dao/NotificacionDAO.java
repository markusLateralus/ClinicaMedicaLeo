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
import modelos.Rol;

public class NotificacionDAO {

    private DataSource dataSource;

    public NotificacionDAO() throws NamingException {
 	   Context initContext = new InitialContext();
       Context envContext  = (Context)initContext.lookup("java:/comp/env");
       dataSource = (DataSource)envContext.lookup("jdbc/clinicaMedica");
      
    }

    
    public void insertarNotificacion(Notificacion notificacion) throws SQLException {
        String sql = "INSERT INTO notificaciones (id_paciente, id_medico, mensaje, estado) VALUES (?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1,notificacion.getIdPaciente());
            stmt.setInt(2,notificacion.getIdMedico());
            stmt.setString(3, notificacion.getMensaje());
            stmt.setString(4, notificacion.getEstado());
            stmt.executeUpdate();
            System.out.println("insertado correctamente en la tabla Notificacion:"+ notificacion.getMensaje());
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
                notificacion.setMensaje(rs.getString("mensaje"));
                notificacion.setEstado(rs.getString("estado"));
                notificacion.setFechaCreacion(rs.getTimestamp("fecha_creacion"));
                notificaciones.add(notificacion);
            }
        }
        return notificaciones;
    }

    public void marcarNotificacionComoInactiva(int idNotificacion)  {
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
                notificacion.setIdMedico(rs.getInt("id_medio"));
                notificacion.setMensaje(rs.getString("mensaje"));
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
}

