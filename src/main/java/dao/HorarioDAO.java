package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import modelos.Horario;
import modelos.Medico;

public class HorarioDAO {
	
	
    private DataSource dataSource;

    public HorarioDAO() throws Exception {
        Context initContext = new InitialContext();
        Context envContext  = (Context)initContext.lookup("java:/comp/env");
        dataSource = (DataSource)envContext.lookup("jdbc/clinicaMedica");
        System.out.println("abriendo horarios");
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
        System.out.println("BASES DE DATOS MEDICO ID " + horarioConsultado.getId());
        return horarioConsultado;
    }
}