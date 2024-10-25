package servlets;

import java.io.IOException;

import javax.naming.NamingException;

import dao.AdministradorDAO;
import dao.HorarioDAO;
import dao.MedicoDAO;
import dao.PacienteDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/RealizarReservaServlet")
public class RealizarReservaServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private PacienteDAO pacienteDAO;
    private MedicoDAO medicoDAO;
    private HorarioDAO horarioDAO;
    @Override
    public void init() throws ServletException {
        // Aquí debes obtener la conexión a la base de datos
    	try {
		
			pacienteDAO=new PacienteDAO();
			medicoDAO=new MedicoDAO();
			horarioDAO=new HorarioDAO();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    
    
    
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String action = request.getParameter("action");
    	if ("irSolicitarCita".equals(action)) {
     
    		
    	}
    }
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        	String action = request.getParameter("action");
        	if ("citaMedica".equals(action)) {
            	this.citaMedica(request, response);
        	}
        }
    
    
    
        
        public void citaMedica(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    System.out.println("hora de reserva " + request.getParameter("id"));
            int horarioId = Integer.parseInt(request.getParameter("id"));
            int idMedico = Integer.parseInt(request.getParameter("idMedico"));
            // Lógica para actualizar el estado en la base de datos
            HorarioDAO horarioDAO=null;
    		try {
    			horarioDAO = new HorarioDAO();
    		} catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
            boolean exito = horarioDAO.reservarHorario(horarioId);

            if (exito) {
//                response.sendRedirect("SoliciarCitaServlet?&id=" + request.getParameter("idMedico"));
//                response.sendRedirect("ConsultarCitaServlet?action=irSolicitarCita&id="+paciente.getId() );  // Redirigir a la página de inicio de médicos
               response.sendRedirect("ConsultarCitaServlet?action=verHorarioDelMedico&id="+idMedico);
            } else {
                response.sendRedirect("error.jsp");
            }
        }
        
        
        
}
