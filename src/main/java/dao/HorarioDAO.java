package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

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
}