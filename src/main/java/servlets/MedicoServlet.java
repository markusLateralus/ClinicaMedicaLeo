package servlets;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelos.Administrador;
import modelos.Horario;
import modelos.Medico;
import modelos.Notificacion;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import dao.AdministradorDAO;
import dao.HorarioDAO;
import dao.MedicoDAO;
import dao.NotificacionDAO;

@WebServlet("/MedicoServlet")
public class MedicoServlet extends HttpServlet{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MedicoDAO medicoDAO;
	private HorarioDAO horarioDAO;
	private NotificacionDAO notificacionDAO;

    public void init() {
       
        try {
			medicoDAO = new MedicoDAO();
			horarioDAO=new HorarioDAO();
			notificacionDAO=new NotificacionDAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

         if ("listarMedicos".equals(action)) {
        	listarMedicos(request, response);
        }  else if ("irCrearMedico".equals(action)) {
        	irCrearMedico(request, response);
        }
        
        else if ("irEditarMedico".equals(action)) {
        	irEditarMedico(request, response);
        } else if ("eliminarMedico".equals(action)) {
        	eliminarMedico(request, response);
        } else if ("verMedico".equals(action)) {
        	verMedico(request, response);
        }else if("irIndexMedico".equals(action)) {
    		this.irIndexMedico(request,response);
    	}else if ("eliminarMensaje".equals(action)) {
    		this.eliminarMensaje(request,response);
    	}else if ("irPoliticaPrivacidad".equals(action)) {
    		this.irPoliticaPrivacidad(request,response);
    	}else if ("irAvisoLegal".equals(action)) {
    		this.irAvisoLegal(request,response);
    	}
    }
    
    private void irAvisoLegal(HttpServletRequest request, HttpServletResponse response) {
      	int idMedico = Integer.parseInt(request.getParameter("id"));
        Medico medico=null;
   		try {
//   			medicoDAO = new MedicoDAO();
   			medicoDAO=new MedicoDAO();
   			 medico=medicoDAO.getMedicoById(idMedico);
   			request.setAttribute("medico",medico);
   		} catch (Exception e) {
   			e.printStackTrace();
   		}


       RequestDispatcher dispatcher = request.getRequestDispatcher("AvisoLegal.jsp");
        try {
   		dispatcher.forward(request, response);
   	} catch (ServletException | IOException e) {
   		// TODO Auto-generated catch block
   		e.printStackTrace();
   		System.out.println("ERROR MARCOS "+ e.getMessage() + " DEJAR CLARO " + e.hashCode()  );
   	} 
		
	}

	private void irPoliticaPrivacidad(HttpServletRequest request, HttpServletResponse response) {
      	int idMedico = Integer.parseInt(request.getParameter("id"));
        Medico medico=null;
   		try {
//   			medicoDAO = new MedicoDAO();
   			medicoDAO=new MedicoDAO();
   			 medico=medicoDAO.getMedicoById(idMedico);
   			request.setAttribute("medico",medico);
   		} catch (Exception e) {
   			e.printStackTrace();
   		}


       RequestDispatcher dispatcher = request.getRequestDispatcher("PoliticaPrivacidad.jsp");
        try {
   		dispatcher.forward(request, response);
   	} catch (ServletException | IOException e) {
   		// TODO Auto-generated catch block
   		e.printStackTrace();
   		System.out.println("ERROR MARCOS "+ e.getMessage() + " DEJAR CLARO " + e.hashCode()  );
   	}  
		
	}

	private void cancelarCitaMedico(HttpServletRequest request, HttpServletResponse response) {
        int idHorario = Integer.parseInt(request.getParameter("idHorario"));
       int idMedico = Integer.parseInt(request.getParameter("idMedico"));
        int idPaciente = Integer.parseInt(request.getParameter("idPaciente"));
        

        try {
            boolean citaCancelada = horarioDAO.cancelarCita(idHorario, idMedico, idPaciente);
            if (citaCancelada) {
                // Redirigir con un mensaje de éxito
                request.setAttribute("mensaje", "Cita cancelada correctamente.");
            } else {
                request.setAttribute("mensaje", "Error al cancelar la cita.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensaje", "Ocurrió un error inesperado.");
        }
        

        try {
//			request.getRequestDispatcher("./paciente/notificacionesPaciente.jsp").forward(request, response);
            response.sendRedirect("RealizarReservaServlet?action=mostrarNotificacionesMedico&idMedico="+idMedico);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    

		
	}
    
    private void eliminarMensaje(HttpServletRequest request, HttpServletResponse response) {
        int indice = Integer.parseInt(request.getParameter("indice"));

        HttpSession session = request.getSession();
        List<String> mensajesPaciente = (List<String>) session.getAttribute("mensajesPaciente");

        if (mensajesPaciente != null && indice >= 0 && indice < mensajesPaciente.size()) {
            mensajesPaciente.remove(indice);
            session.setAttribute("mensajesPaciente", mensajesPaciente);
        }

        try {
			response.sendRedirect("./medico/indexMedico.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    private void irIndexMedico(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("id medico " + request.getParameter("id"));
    	int medicoId = Integer.parseInt(request.getParameter("id"));
        
        MedicoDAO medicoDAO=null;
        Medico medico=null;
		try {
			medicoDAO = new MedicoDAO();
			  List<Horario> horarios = medicoDAO.obtenerHorariosPorMedico(medicoId);
			  medico=medicoDAO.getMedicoById(medicoId);
			  List<Notificacion> notificaciones = notificacionDAO.obtenerNotificacionesActivasParaMedicos(medicoId);
		        request.setAttribute("notificaciones", notificaciones);
			  
//			  for(Horario h:horarios) {
//				  System.out.println("horarios" + h.getDia() + ", " + h.getHora() + ", " + h.getEstado() + ", id " + h.g);
//			  }
//			  request.getSession().setAttribute("tipoUsuario", tipoUsuario);
		        request.setAttribute("horarios", horarios);
				request.setAttribute("medico", medico);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        RequestDispatcher dispatcher = request.getRequestDispatcher("./medico/IndexMedico.jsp");
        dispatcher.forward(request, response);// TODO Auto-generated method stub
		
	}
    
    private void verMedico(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   	 int id = Integer.parseInt(request.getParameter("id"));
   	Medico medico=null;
		try {
			medico = medicoDAO.getMedicoById(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       request.setAttribute("medico", medico);
   	request.getRequestDispatcher("./medico/VerMedico.jsp").forward(request, response);
		
	}
    
    private void irCrearMedico(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   	 request.getRequestDispatcher("./medico/InsertarMedico.jsp").forward(request, response);
		
	}
    
    private void irEditarMedico(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
        	Medico medico = medicoDAO.getMedicoById(id);
            request.setAttribute("medico", medico);
            RequestDispatcher dispatcher = request.getRequestDispatcher("./medico/EditarMedico.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("crearMedico".equals(action)) {
        	crearMedico(request, response);
        } else if ("actualizarMedico".equals(action)) {
            actualizarMedico(request, response);
        }else if ("cancelarCitaMedico".equals(action)) {
    		this.cancelarCitaMedico(request,response);
    	}
    }
    
  ////////////////////////////////////////////////////////////////////////////  
    
    private void listarMedicos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Medico> listaMedicos = medicoDAO.getAllMedicos(); // Obtener todos los médicos
            request.setAttribute("listaMedicos", listaMedicos);
            RequestDispatcher dispatcher = request.getRequestDispatcher("./medico/ListarMedicos.jsp"); // Redirige a la lista de médicos
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }


    
    
    private void eliminarMedico(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            medicoDAO.eliminarMedico(id); // Elimina el médico por ID
            response.sendRedirect("MedicoServlet?action=listarMedicos"); // Redirige a la lista de médicos
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
    
    
    private void crearMedico(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Medico nuevoMedico = new Medico();
        nuevoMedico.setUsername(request.getParameter("username"));
        nuevoMedico.setPassword(request.getParameter("password"));
        nuevoMedico.setDni(request.getParameter("dni"));
        nuevoMedico.setNombre(request.getParameter("nombre"));
        nuevoMedico.setApellido1(request.getParameter("apellido1"));
        nuevoMedico.setApellido2(request.getParameter("apellido2"));
        nuevoMedico.setEspecialidad(request.getParameter("especialidad"));
        nuevoMedico.setEmail(request.getParameter("email"));
        nuevoMedico.setTelefono(request.getParameter("telefono"));
        nuevoMedico.setFechaNacimiento(Date.valueOf(request.getParameter("fechaNacimiento")));
  

        try {
            medicoDAO.insertarMedico(nuevoMedico); // Inserta el médico en la base de datos
            response.sendRedirect("MedicoServlet?action=listarMedicos"); // Redirige a la lista de médicos
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    
    private void actualizarMedico(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Medico medicoActualizado = new Medico();
        medicoActualizado.setId(id);
        medicoActualizado.setUsername(request.getParameter("username"));
        medicoActualizado.setPassword(request.getParameter("password"));
        medicoActualizado.setDni(request.getParameter("dni"));
        medicoActualizado.setNombre(request.getParameter("nombre"));
        medicoActualizado.setApellido1(request.getParameter("apellido1"));
        medicoActualizado.setApellido2(request.getParameter("apellido2"));
        medicoActualizado.setEmail(request.getParameter("email").toLowerCase());
        medicoActualizado.setTelefono(request.getParameter("telefono"));
        medicoActualizado.setFechaNacimiento(Date.valueOf(request.getParameter("fechaNacimiento")));
        medicoActualizado.setEspecialidad(request.getParameter("especialidad"));
//		System.out.println("id " + medicoActualizado.getId());
//		System.out.println("username " + medicoActualizado.getUsername());
//		System.out.println("pass " + medicoActualizado.getPassword());
//		System.out.println("dni " + medicoActualizado.getDni());
//		System.out.println("nombre " + medicoActualizado.getNombre());
//		System.out.println("apellido1 " + medicoActualizado.getApellido1());
//		System.out.println("ape2 " + medicoActualizado.getApellido2());
//		System.out.println("email " + medicoActualizado.getEmail());
//		System.out.println("telefon" + medicoActualizado.getTelefono());
//		System.out.println("fecha " + medicoActualizado.getFechaNacimiento());

        try {
            medicoDAO.actualizarMedico(medicoActualizado); // Actualiza el médico
            response.sendRedirect("MedicoServlet?action=irEditarMedico&id="+id); // Redirige a la lista de médicos
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    
    
    
}
