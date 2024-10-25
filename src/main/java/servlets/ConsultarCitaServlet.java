package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelos.ConsultaMedica;
import modelos.Horario;
import modelos.Medico;
import modelos.Paciente;
import modelos.Administrador;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.naming.NamingException;
import dao.HorarioDAO;
import dao.MedicoDAO;
import dao.PacienteDAO;
import dao.AdministradorDAO;

@WebServlet("/ConsultarCitaServlet")
public class ConsultarCitaServlet extends HttpServlet {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//    private Connection connection;
    private AdministradorDAO administradorDAO;
    private PacienteDAO pacienteDAO;
    private MedicoDAO medicoDAO;
    private HorarioDAO horarioDAO;
    @Override
    public void init() throws ServletException {
        // Aquí debes obtener la conexión a la base de datos
    	try {
			administradorDAO = new AdministradorDAO();
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
        	irSolicitarCita(request, response);
    	}else if ("verHorarioDelMedico".equals(action)) {
    		verHorarioDelMedico(request,response);
    		
    	}else if("irIndexMedico".equals(action)) {
    		this.irIndexMedico(request,response);
    	}
    }
    


	private void irSolicitarCita(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
      	 int id = Integer.parseInt(request.getParameter("id"));
        	Paciente paciente=null;
     		try {
     			paciente = pacienteDAO.getPacienteById(id);
     		} catch (SQLException e) {
     			// TODO Auto-generated catch block
     			e.printStackTrace();
     		}
            request.setAttribute("paciente", paciente);
//        	request.getRequestDispatcher("./paciente/VerPaciente.jsp").forward(request, response);
        try {
            List<Medico> listaMedicos = medicoDAO.getAllMedicos(); // Obtener todos los médicos
            request.setAttribute("listaMedicos", listaMedicos);
            RequestDispatcher dispatcher = request.getRequestDispatcher("./solicitarCita/ListadoMedicosDisponibles.jsp"); // Redirige a la lista de médicos
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void verHorarioDelMedico(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("id medico " + request.getParameter("id"));
    	int medicoId = Integer.parseInt(request.getParameter("id"));
        
        MedicoDAO medicoDAO=null;
		try {
			medicoDAO = new MedicoDAO();
			  List<Horario> horarios = medicoDAO.obtenerHorariosPorMedico(medicoId);
//			  for(Horario h:horarios) {
//				  System.out.println("horarios" + h.getDia() + ", " + h.getHora() + ", " + h.getEstado() + ", id " + h.g);
//			  }
		        request.setAttribute("horarios", horarios);
				request.setAttribute("medicoId", medicoId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        RequestDispatcher dispatcher = request.getRequestDispatcher("./solicitarCita/HorarioDelMedico.jsp");
        dispatcher.forward(request, response);
    
    }
    private void irIndexMedico(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("id medico " + request.getParameter("id"));
    	int medicoId = Integer.parseInt(request.getParameter("id"));
        
        MedicoDAO medicoDAO=null;
		try {
			medicoDAO = new MedicoDAO();
			  List<Horario> horarios = medicoDAO.obtenerHorariosPorMedico(medicoId);
//			  for(Horario h:horarios) {
//				  System.out.println("horarios" + h.getDia() + ", " + h.getHora() + ", " + h.getEstado() + ", id " + h.g);
//			  }
		        request.setAttribute("horarios", horarios);
				request.setAttribute("medicoId", medicoId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        RequestDispatcher dispatcher = request.getRequestDispatcher("IndexMedico.jsp");
        dispatcher.forward(request, response);// TODO Auto-generated method stub
		
	}
    
    
//   
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//    	String action = request.getParameter("action");
//    	if ("realizarReserva".equals(action)) {
//        	realizarReserva(request, response);
//    	}
//    }
    

    
    
    
}

