package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import dao.AdministradorDAO;
import dao.HorarioDAO;
import dao.MedicoDAO;
import dao.NotificacionDAO;
import dao.PacienteDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelos.Horario;
import modelos.Medico;
import modelos.Notificacion;
import modelos.Paciente;

@WebServlet("/RealizarReservaServlet")
public class RealizarReservaServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private PacienteDAO pacienteDAO;
    private MedicoDAO medicoDAO;
    private HorarioDAO horarioDAO;
    private NotificacionDAO notificacionDAO;
    @Override
    public void init() throws ServletException {
        // Aquí debes obtener la conexión a la base de datos
    	try {
		
			pacienteDAO=new PacienteDAO();
			medicoDAO=new MedicoDAO();
			horarioDAO=new HorarioDAO();
			notificacionDAO=new NotificacionDAO();
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
    		 else if ("irMostrarNotificaciones".equals(action)) {
//                 irMostrarNotificaciones(request, response);
             } 
    		 else if ("mostrarNotificaciones".equals(action)) {
                 mostrarNotificaciones(request, response);
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
//    System.out.println("hora de reserva " + request.getParameter("id"));
            int horarioId = Integer.parseInt(request.getParameter("id"));
            int idMedico = Integer.parseInt(request.getParameter("idMedico"));
            int idPaciente =Integer.parseInt(request.getParameter("idPaciente"));
            String dia=request.getParameter("dia");
            String hora=request.getParameter("hora");
            // Lógica para actualizar el estado en la base de datos
            HorarioDAO horarioDAO=null;
            Horario horario=null;
    		try {
    			horarioDAO = new HorarioDAO();
    		
    		} catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
            boolean exito = horarioDAO.reservarHorario(horarioId);

            if (exito) {
//            	horario=horarioDAO.getHorarioById(horarioId);
            	   Paciente paciente=null;
				try {
					paciente = pacienteDAO.getPacienteById(idPaciente);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                   Medico medico=null;
				try {
					medico = medicoDAO.getMedicoById(idMedico);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                // Mensajes personalizados
                String mensajePaciente = String.format(
              "Se ha realizado la reserva para el día %s a las %s horas para la consulta del especialista Dr. %s %s",
                   dia, hora, medico.getNombre(), medico.getApellido1()
                );
                String mensajeMedico = String.format(
                    "Se ha realizado la reserva para el día %s a las %s horas para la consulta del paciente %s %s",
                    dia, hora, paciente.getNombre(), paciente.getApellido1()
                );
             // Mensaje para el paciente
                Notificacion notificacionPaciente = new Notificacion();
                notificacionPaciente.setIdPaciente(idPaciente);
                notificacionPaciente.setIdMedico(idMedico);
                notificacionPaciente.setMensaje("Se ha realizado la reserva para el día " + dia + " a las " + hora + " con el Dr. " + medico.getNombre() + " especialista en " + medico.getEspecialidad());
                
                notificacionPaciente.setEstado("activo");
                try {
					notificacionDAO.insertarNotificacion(notificacionPaciente);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                // Mensaje para el paciente
                Notificacion notificacionMedico = new Notificacion();
                notificacionMedico.setIdPaciente(idPaciente);
                notificacionMedico.setIdMedico(idMedico);
                notificacionMedico.setMensaje("Se ha realizado la reserva para el día " + dia + " a las " + hora + " con el paciente " + paciente.getNombre() + " " + paciente.getApellido1());
                
                notificacionMedico.setEstado("activo");
//                try {
//					notificacionDAO.insertarNotificacion(notificacionMedico);
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
 
            	
                
                //////////////////////////7ultimas
//            	response.sendRedirect("ConsultarCitaServlet?action=verHorarioDelMedico&id="+idMedico+"&idPaciente="+idPaciente);
//             	response.sendRedirect("./paciente/IndexPaciente.jsp");
                request.setAttribute("paciente", paciente);
                request.setAttribute("notificacionPaciente", notificacionPaciente);
//             	 request.getRequestDispatcher("./paciente/IndexPaciente.jsp").forward(request, response);
                 response.sendRedirect("RealizarReservaServlet?action=mostrarNotificaciones&idPaciente="+idPaciente);

            } else {
                response.sendRedirect("error.jsp");
            }
        }
        
        


        
        public void mostrarNotificaciones(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            int idPaciente = Integer.parseInt(request.getParameter("idPaciente"));
//            int idPaciente = (int) session.getAttribute("idPaciente");
            List<Notificacion> notificaciones=null;
			try {
				notificaciones = notificacionDAO.obtenerNotificacionesActivasParaPacientes(idPaciente);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			   Paciente paciente=null;
						try {
							paciente = pacienteDAO.getPacienteById(idPaciente);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
            request.setAttribute("notificaciones", notificaciones);
            request.setAttribute("paciente", paciente);
            request.getRequestDispatcher("./paciente/notificacionesPaciente.jsp").forward(request, response);
        }
}
