package servlets;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelos.Horario;
import modelos.Medico;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import dao.MedicoDAO;

@WebServlet("/MedicoServlet")
public class MedicoServlet extends HttpServlet{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MedicoDAO medicoDAO;

    public void init() {
       
        try {
			medicoDAO = new MedicoDAO();
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
    	}
    }
    private void irIndexMedico(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("id medico " + request.getParameter("id"));
    	int medicoId = Integer.parseInt(request.getParameter("id"));
        
        MedicoDAO medicoDAO=null;
        Medico medico=null;
		try {
			medicoDAO = new MedicoDAO();
			  List<Horario> horarios = medicoDAO.obtenerHorariosPorMedico(medicoId);
			  medico=medicoDAO.getMedicoById(medicoId);
			  
//			  for(Horario h:horarios) {
//				  System.out.println("horarios" + h.getDia() + ", " + h.getHora() + ", " + h.getEstado() + ", id " + h.g);
//			  }
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
        medicoActualizado.setEmail(request.getParameter("email"));
        medicoActualizado.setTelefono(request.getParameter("telefono"));
        medicoActualizado.setFechaNacimiento(Date.valueOf(request.getParameter("fechaNacimiento")));
        medicoActualizado.setEspecialidad(request.getParameter("especialidad"));
		System.out.println("id " + medicoActualizado.getId());
		System.out.println("username " + medicoActualizado.getUsername());
		System.out.println("pass " + medicoActualizado.getPassword());
		System.out.println("dni " + medicoActualizado.getDni());
		System.out.println("nombre " + medicoActualizado.getNombre());
		System.out.println("apellido1 " + medicoActualizado.getApellido1());
		System.out.println("ape2 " + medicoActualizado.getApellido2());
		System.out.println("email " + medicoActualizado.getEmail());
		System.out.println("telefon" + medicoActualizado.getTelefono());
		System.out.println("fecha " + medicoActualizado.getFechaNacimiento());

        try {
            medicoDAO.actualizarMedico(medicoActualizado); // Actualiza el médico
            response.sendRedirect("MedicoServlet?action=irEditarMedico&id="+id); // Redirige a la lista de médicos
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    
    
    
}
