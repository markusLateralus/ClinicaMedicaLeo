package servlets;

import dao.MedicoDAO;
import dao.PacienteDAO;
import modelos.Medico;
import modelos.Paciente;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/PacienteServlet")
public class PacienteServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PacienteDAO pacienteDAO;
	private MedicoDAO medicoDAO;

    public void init() throws ServletException {
    	
        try {
        	 System.out.println("abriendo pacienteServlet");
            pacienteDAO = new PacienteDAO();
            medicoDAO=new MedicoDAO();
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("listarPacientes".equals(action)) {
        	listarPacientes(request, response);
        }  else if ("irCrearPaciente".equals(action)) {
        	irCrearPaciente(request, response);
        }  else if ("irEditarPaciente".equals(action)) {
        	irEditarPaciente(request, response);
        } else if ("eliminarPaciente".equals(action)) {
        	eliminarPaciente(request, response);
        } else if ("verPaciente".equals(action)) {
        	verPaciente(request, response);
        }else if ("listarMedicos".equals(action)) {
        	listarMedicos(request, response);
        }
    }

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
    private void verPaciente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 int id = Integer.parseInt(request.getParameter("id"));
    	Paciente paciente=null;
		try {
			paciente = pacienteDAO.getPacienteById(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        request.setAttribute("paciente", paciente);
    	request.getRequestDispatcher("./paciente/VerPaciente.jsp").forward(request, response);
		
	}
    
    private void irCrearPaciente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 request.getRequestDispatcher("./paciente/InsertarPaciente.jsp").forward(request, response);
		
	}
    
    private void listarPacientes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Paciente> listaPacientes = pacienteDAO.getAllPacientes();
            request.setAttribute("listaPacientes", listaPacientes);
            RequestDispatcher dispatcher = request.getRequestDispatcher("./paciente/ListarPacientes.jsp");
         
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void irEditarPaciente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            Paciente paciente = pacienteDAO.getPacienteById(id);
            request.setAttribute("paciente", paciente);
            RequestDispatcher dispatcher = request.getRequestDispatcher("./paciente/EditarPaciente.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("crearPaciente".equals(action)) {
        	crearPaciente(request, response);
        } else if ("actualizarPaciente".equals(action)) {
       
            actualizarPaciente(request, response);
        }
    }
    
    



    private void eliminarPaciente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            pacienteDAO.eliminarPaciente(id);
            response.sendRedirect("PacienteServlet?action=listarPacientes");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void crearPaciente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Paciente nuevoPaciente = new Paciente();
        nuevoPaciente.setUsername(request.getParameter("username"));
        nuevoPaciente.setPassword(request.getParameter("password"));
        nuevoPaciente.setDni(request.getParameter("dni"));
        nuevoPaciente.setNombre(request.getParameter("nombre"));
        nuevoPaciente.setApellido1(request.getParameter("apellido1"));
        nuevoPaciente.setApellido2(request.getParameter("apellido2"));
        nuevoPaciente.setEmail(request.getParameter("email"));
        nuevoPaciente.setTelefono(request.getParameter("telefono"));
        nuevoPaciente.setFechaNacimiento(Date.valueOf(request.getParameter("fechaNacimiento")));

        try {
            pacienteDAO.insertarPaciente(nuevoPaciente);
            response.sendRedirect("PacienteServlet?action=listarPacientes");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void actualizarPaciente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int id = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("PASSWORDD " + password);
        String dni = request.getParameter("dni");
        String nombre = request.getParameter("nombre");
        String apellido1 = request.getParameter("apellido1");
        String apellido2 = request.getParameter("apellido2");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        java.util.Date fechaNacimiento = null;
			fechaNacimiento =  Date.valueOf(request.getParameter("fechaNacimiento"));
       
        	Paciente pacienteActualizado;
			try {
				pacienteActualizado = pacienteDAO.getPacienteById(id);
				pacienteActualizado.setId(id);
				pacienteActualizado.setUsername(username);
				pacienteActualizado.setPassword(password);
				pacienteActualizado.setDni(dni);
				pacienteActualizado.setNombre(nombre);
				pacienteActualizado.setApellido1(apellido1);
				pacienteActualizado.setApellido2(apellido2);
				pacienteActualizado.setEmail(email);
				pacienteActualizado.setTelefono(telefono);
				pacienteActualizado.setFechaNacimiento(fechaNacimiento);
//				System.out.println("id " + pacienteActualizado.getId());
//				System.out.println("username " + pacienteActualizado.getUsername());
//				System.out.println("pass " + pacienteActualizado.getPassword());
//				System.out.println("dni " + pacienteActualizado.getDni());
//				System.out.println("nombre " + pacienteActualizado.getNombre());
//				System.out.println("apellido1 " + pacienteActualizado.getApellido1());
//				System.out.println("ape2 " + pacienteActualizado.getApellido2());
//				System.out.println("email " + pacienteActualizado.getEmail());
//				System.out.println("telefon" + pacienteActualizado.getTelefono());
//				System.out.println("fecha " + pacienteActualizado.getFechaNacimiento());
						
				
				   pacienteDAO.actualizarPaciente(pacienteActualizado);
		            response.sendRedirect("PacienteServlet?action=listarPacientes");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
         
        
    } 
    
    
    
    
    
    
    
    
}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /*
    private void listarPacientes(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        ArrayList<Paciente> pacientes = (ArrayList<Paciente>) pacienteDAO.getAllPacientes();
        request.setAttribute("pacientes", pacientes);
        request.getRequestDispatcher("./paciente/pacientes.jsp?action=list").forward(request, response);
    }
    

    private void verPaciente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        Paciente paciente = pacienteDAO.getPacienteById(id);
        request.setAttribute("paciente", paciente);
        request.getRequestDispatcher("./paciente/VerPaciente.jsp").forward(request, response);
    }
    private void irInsertarPaciente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

        request.getRequestDispatcher("./paciente/InsertarPaciente.jsp").forward(request, response);
    }

    private void insertarPaciente(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
    	String id = request.getParameter("id");  
    	String nombre = request.getParameter("nombre");
        String apellido1 = request.getParameter("apellido1");
        String apellido2 = request.getParameter("apellido2");
//        System.out.println(nombre +" "+ apellido1 + " "+ apellido2);
        Paciente nuevoPaciente = new Paciente(nombre, apellido1, apellido2);
        pacienteDAO.insertarPaciente(nuevoPaciente);
        response.sendRedirect("Pacientes");
    }

    private void editarPaciente(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Paciente paciente = pacienteDAO.getPacienteById(id);
//        System.out.println("editando " + paciente.getId() + " " + paciente.getNombre());
        request.setAttribute("paciente", paciente);
        request.getRequestDispatcher("./paciente/EditarPaciente.jsp").forward(request, response);
    }

    private void actualizarPaciente(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        String apellido1 = request.getParameter("apellido1");
        String apellido2 = request.getParameter("apellido2");

        Paciente paciente = pacienteDAO.getPacienteById(id);
        paciente.setNombre(nombre);
        paciente.setApellido1(apellido1);
        paciente.setApellido2(apellido2);
//        System.out.println("actualizar " + paciente.getId() + " " + paciente.getNombre());
        pacienteDAO.actualizarPaciente(paciente);
        response.sendRedirect("Pacientes");
    }
    private void eliminarPaciente(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Paciente paciente = pacienteDAO.getPacienteById(id);
        pacienteDAO.eliminarPaciente(id);
        response.sendRedirect("Pacientes");
}
}

*/
