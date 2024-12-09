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
    		 else if ("mostrarNotificacionesPaciente".equals(action)) {
                 mostrarNotificacionesPacientes(request, response);
//                 mostrarCitasPacientes(request,response);
                 
             } else if ("mostrarNotificacionesMedico".equals(action)) {
                 mostrarNotificacionesMedico(request, response);
             } 
        	 else if ("mostrarCitasPaciente".equals(action)) {
                 mostrarCitasPacientes(request, response);
//                 mostrarCitasPacientes(request,response);
                 
             } else if ("mostrarCitasMedico".equals(action)) {
                 mostrarCitasMedico(request, response);
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
          
            Horario horario=new Horario();
            horario.setId(horarioId);
            horario.setDia(dia);
            horario.setHora(hora);
            horario.setMedicoId(idMedico);
            horario.setPacienteId(idPaciente);
            horario.setEstado("ocupado");
            HorarioDAO horarioDAO=null;
    		try {
    			horarioDAO = new HorarioDAO();
    			
    		
    		} catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
            boolean exito = horarioDAO.reservarHorario(horario);
            System.out.println("EXITO  " + exito);
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
                notificacionPaciente.setMensajePaciente("Se ha realizado la reserva para el día " + dia + " a las " + hora + " con el Dr. " + medico.getNombre() + " especialista en " + medico.getEspecialidad());
                notificacionPaciente.setMensajeMedico("Se ha realizado la reserva para el día " + dia + " a las " + hora + " con el paciente " + paciente.getNombre() + " " + paciente.getApellido1());

                notificacionPaciente.setEstado("activo");
                try {
					notificacionDAO.insertarNotificacion(notificacionPaciente);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                
    			
				List<Horario> horarios=null;
				 horarios = horarioDAO.obtenerHorariosPorPaciente(idPaciente);
			
	request.setAttribute("horarios", horarios);
                
    request.setAttribute("medico", medico);
                request.setAttribute("paciente", paciente);
                request.setAttribute("notificacionPaciente", notificacionPaciente);
                request.setAttribute("horario", horario);
              response.sendRedirect("RealizarReservaServlet?action=mostrarNotificacionesPaciente&idPaciente="+idPaciente);
             


            } else {
//                response.sendRedirect("error.jsp");
            }
        }
        
        


        
        public void mostrarNotificacionesPacientes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            int idPaciente = Integer.parseInt(request.getParameter("idPaciente"));
//            int idHorario = Integer.parseInt(request.getParameter("horarioId"));
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
						
						List<Horario> horarios=null;
						 horarios = horarioDAO.obtenerHorariosPorPaciente(idPaciente);
					
			request.setAttribute("horarios", horarios);
            request.setAttribute("notificaciones", notificaciones);
            request.setAttribute("paciente", paciente);
            request.getRequestDispatcher("./paciente/IndexPaciente.jsp").forward(request, response);
        }
        
        
          
        public void mostrarNotificacionesMedico(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            int idMedico = Integer.parseInt(request.getParameter("idMedico"));
//          int idPaciente = Integer.parseInt(request.getParameter("idPaciente"));
//            int idPaciente = (int) session.getAttribute("idPaciente");
            List<Notificacion> notificaciones=null;
			notificaciones = notificacionDAO.obtenerNotificacionesActivasParaMedicos(idMedico);
			   Medico medico=null;
						try {
							medico = medicoDAO.getMedicoById(idMedico);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						////////////MARCOS ESTAS AKI SOLUCNINAOD ESTO
						List<Horario> horarios=null;
						 horarios = horarioDAO.obtenerHorariosPorMedico(idMedico);
            request.setAttribute("notificaciones", notificaciones);
            request.setAttribute("horarios", horarios);
            request.setAttribute("medico", medico);
          request.getRequestDispatcher("./medico/IndexMedico.jsp").forward(request, response);
//            response.sendRedirect("MedicoServlet?action=irIndexMedico&idMedico="+idMedico);

        }
        
        
        public void mostrarCitasPacientes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            int idPaciente = Integer.parseInt(request.getParameter("idPaciente"));
//            int idHorario = Integer.parseInt(request.getParameter("horarioId"));
            List<Notificacion> notificaciones=null;
            int idMedico = 0;
			try {
				notificaciones = notificacionDAO.obtenerNotificacionesActivasParaPacientes(idPaciente);
				idMedico=notificaciones.get(0).getIdMedico();
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
					Medico medico=null;
					
					try {
						medico=medicoDAO.getMedicoById(idMedico);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						
						
						
						
						List<Horario> horarios=null;
						 horarios = horarioDAO.obtenerHorariosPorPaciente(idPaciente);
					
			request.setAttribute("horarios", horarios);
	          request.setAttribute("medico", medico);
            request.setAttribute("paciente", paciente);
          request.getRequestDispatcher("./paciente/CitasPaciente.jsp").forward(request, response);
//            response.sendRedirect("RealizarReservaServlet?action=mostrarCitasPaciente&idPaciente="+idPaciente);

        }   
 
        public void mostrarCitasMedico(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            int idMedico = Integer.parseInt(request.getParameter("idMedico"));
//          int idPaciente = Integer.parseInt(request.getParameter("idPaciente"));
//            int idPaciente = (int) session.getAttribute("idPaciente");
            List<Notificacion> notificaciones=null;
			notificaciones = notificacionDAO.obtenerNotificacionesActivasParaMedicos(idMedico);
			   Medico medico=null;
						try {
							medico = medicoDAO.getMedicoById(idMedico);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				int idPaciente;
				
				
				Paciente paciente=null;
				try {
					idPaciente=notificaciones.get(0).getIdPaciente();
					paciente=pacienteDAO.getPacienteById(idPaciente);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}catch(IndexOutOfBoundsException i) {
					i.printStackTrace();
				}
						
						
						////////////MARCOS ESTAS AKI SOLUCNINAOD ESTO
						List<Horario> horarios=null;
						 horarios = horarioDAO.obtenerHorariosPorMedico(idMedico);
            request.setAttribute("notificaciones", notificaciones);
            request.setAttribute("horarios", horarios);
            request.setAttribute("medico", medico);
            request.setAttribute("paciente", paciente);
            request.getRequestDispatcher("./medico/CitasMedico.jsp").forward(request, response);
        }
        
        
        
        
}
