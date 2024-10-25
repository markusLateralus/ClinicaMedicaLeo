package servlets;

import dao.RolDAO;
import dao.AdministradorDAO;
import dao.MedicoDAO;
import dao.PacienteDAO;
import modelos.Paciente;
import modelos.Rol;
import modelos.Administrador;
import modelos.Horario;
import modelos.Medico;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.naming.NamingException;

@WebServlet("/AdministradorServlet")
public class AdministradorServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private PacienteDAO pacienteDAO;
	private MedicoDAO medicoDAO;
	private AdministradorDAO administradorDAO;

    public void init() throws ServletException {
    	
        try {
        	administradorDAO= new AdministradorDAO();
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
        }else if ("listarMedicos".equals(action)) {
        	listarMedicos(request, response);
        }
        else if ("listarAdministradores".equals(action)) {
        	listarAdministradores(request, response);
        } 
        else if ("irCrearPaciente".equals(action)) {
        	irCrearPaciente(request, response);
        }  else if ("irCrearMedico".equals(action)) {
        	irCrearMedico(request, response);
        } 
        else if ("irCrearAdministrador".equals(action)) {
        	irCrearAdministrador(request, response);
        } 
        else if ("irEditarPaciente".equals(action)) {
        	irEditarPaciente(request, response);
        }  else if ("irEditarMedico".equals(action)) {
        	irEditarMedico(request, response);
        } else if ("irEditarAdministrador".equals(action)) {
        	irEditarAdministrador(request, response);
        }
        
        else if ("eliminarPaciente".equals(action)) {
        	eliminarPaciente(request, response);
        }    else if ("eliminarMedico".equals(action)) {
        	eliminarMedico(request, response);
        }   else if ("eliminarAdministrador".equals(action)) {
        	eliminarAdministrador(request, response);
        } else if ("verPaciente".equals(action)) {
        	verPaciente(request, response);
        } else if ("verMedico".equals(action)) {
        	verMedico(request, response);
        }
        
        else if ("verAdministrador".equals(action)) {
        	
        	verAdministrador(request, response);
        }   else if ("irIndexAdministrador".equals(action)) {
        	
        	irIndexAdministrador(request, response);
        }
    }
    
    private void irIndexAdministrador(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("id medico " + request.getParameter("id"));
    	int medicoId = Integer.parseInt(request.getParameter("id"));
        
//        MedicoDAO medicoDAO=null;
        AdministradorDAO administradorDAO=null;
        Administrador administrador=null;
		try {
			medicoDAO = new MedicoDAO();
			administradorDAO=new AdministradorDAO();
			 administrador=administradorDAO.getAdministradorById(medicoId);
//			  for(Horario h:horarios) {
//				  System.out.println("horarios" + h.getDia() + ", " + h.getHora() + ", " + h.getEstado() + ", id " + h.g);
//			  }
		      
				request.setAttribute("administrador",administrador);
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        RequestDispatcher dispatcher = request.getRequestDispatcher("./administrador/IndexAdministrador.jsp");
        dispatcher.forward(request, response);// TODO Auto-generated method stub
//   	 request.getRequestDispatcher("./administrador/InsertarAdministrador.jsp").forward(request, response);
		
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
    private void listarAdministradores(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Administrador> listaAdministradores = administradorDAO.getAllAdministradores(); // Obtener todos los médicos
            request.setAttribute("listaAdministradores", listaAdministradores);
            RequestDispatcher dispatcher = request.getRequestDispatcher("./administrador/ListarAdministradores.jsp"); // Redirige a la lista de médicos
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
    
    private void irCrearPaciente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   	 request.getRequestDispatcher("./paciente/InsertarPaciente.jsp").forward(request, response);
		
	}
    private void irCrearMedico(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      	 request.getRequestDispatcher("./medico/InsertarMedico.jsp").forward(request, response);
   		
   	}
    private void irCrearAdministrador(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      	 request.getRequestDispatcher("./administrador/InsertarAdministrador.jsp").forward(request, response);
   		
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
    private void verAdministrador(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   
    	int id = Integer.parseInt(request.getParameter("id"));
   	Administrador administrador=null;
		try {
			administrador = administradorDAO.getAdministradorById(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       request.setAttribute("administrador", administrador);
   	request.getRequestDispatcher("./administrador/VerAdministrador.jsp").forward(request, response);
		
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
    private void irEditarAdministrador(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            Administrador administrador = administradorDAO.getAdministradorById(id);
            request.setAttribute("administrador", administrador);
            RequestDispatcher dispatcher = request.getRequestDispatcher("./administrador/EditarAdministrador.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
    
    
    private void eliminarPaciente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            pacienteDAO.eliminarPaciente(id);
            response.sendRedirect("PacienteServlet?action=list");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
    
    private void eliminarMedico(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
           medicoDAO.eliminarMedico(id);
            response.sendRedirect("MedicoServlet?action=listarMedicos");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
    
    private void eliminarAdministrador(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            administradorDAO.eliminarAdministrador(id);
            response.sendRedirect("AdministradorServlet?action=listarAdministradores");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
    
    ////////////////////////////////////////////////////
    ///////////////////////////////
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("crearPaciente".equals(action)) {
        	crearPaciente(request, response);
        } 
        else if ("crearMedico".equals(action)) {
        	crearMedico(request, response);
        }
        else if ("crearAdministrador".equals(action)) {
        	crearAdministrador(request, response);
        }
        else if ("actualizarPaciente".equals(action)) {
       
            actualizarPaciente(request, response);
        }
        else if ("actualizarMedico".equals(action)) {
            
            actualizarMedico(request, response);
        }
        else if ("actualizarAdministrador".equals(action)) {
            
            actualizarAdministrador(request, response);
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

    private void crearMedico(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Medico medicoNuevo = new Medico();
        medicoNuevo.setUsername(request.getParameter("username"));
        medicoNuevo.setPassword(request.getParameter("password"));
        medicoNuevo.setDni(request.getParameter("dni"));
        medicoNuevo.setNombre(request.getParameter("nombre"));
        medicoNuevo.setApellido1(request.getParameter("apellido1"));
        medicoNuevo.setApellido2(request.getParameter("apellido2"));
        medicoNuevo.setEmail(request.getParameter("email"));
        medicoNuevo.setTelefono(request.getParameter("telefono"));
        medicoNuevo.setEspecialidad(request.getParameter("especialidad"));
        medicoNuevo.setFechaNacimiento(Date.valueOf(request.getParameter("fechaNacimiento")));

        try {
           medicoDAO.insertarMedico(medicoNuevo);
            response.sendRedirect("MedicoServlet?action=listarMedicos");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    
    private void crearAdministrador(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Administrador administradorNuevo = new Administrador();
        administradorNuevo.setUsername(request.getParameter("username"));
        administradorNuevo.setPassword(request.getParameter("password"));
        administradorNuevo.setDni(request.getParameter("dni"));
        administradorNuevo.setNombre(request.getParameter("nombre"));
        administradorNuevo.setApellido1(request.getParameter("apellido1"));
        administradorNuevo.setApellido2(request.getParameter("apellido2"));
        administradorNuevo.setEmail(request.getParameter("email"));
        administradorNuevo.setTelefono(request.getParameter("telefono"));
        administradorNuevo.setFechaNacimiento(Date.valueOf(request.getParameter("fechaNacimiento")));

        try {
            administradorDAO.insertarAdministrador(administradorNuevo);
            response.sendRedirect("AdministradorServlet?action=listarAdministradores");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    
    
    
    
    
    private void actualizarPaciente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int id = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
 
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
    
    
    private void actualizarMedico(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int id = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String dni = request.getParameter("dni");
        String nombre = request.getParameter("nombre");
        String apellido1 = request.getParameter("apellido1");
        String apellido2 = request.getParameter("apellido2");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        String especialidad=request.getParameter("especialidad");
        java.util.Date fechaNacimiento = null;
			fechaNacimiento =  Date.valueOf(request.getParameter("fechaNacimiento"));
       
        	Medico medicoActualizado;
			try {
				medicoActualizado = medicoDAO.getMedicoById(id);
				medicoActualizado.setId(id);
				medicoActualizado.setUsername(username);
				medicoActualizado.setPassword(password);
				medicoActualizado.setDni(dni);
				medicoActualizado.setNombre(nombre);
				medicoActualizado.setApellido1(apellido1);
				medicoActualizado.setApellido2(apellido2);
				medicoActualizado.setEmail(email);
				medicoActualizado.setTelefono(telefono);
				medicoActualizado.setFechaNacimiento(fechaNacimiento);
				medicoActualizado.setEspecialidad(especialidad);

						
				
				medicoDAO.actualizarMedico(medicoActualizado);
		            response.sendRedirect("MedicoServlet?action=listarMedicos");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
         
        
    } 
    
    
    
    
    
    
    private void actualizarAdministrador(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
       
			Administrador administradorActualizado;
			try {
				administradorActualizado = administradorDAO.getAdministradorById(id);
				administradorActualizado.setId(id);
				administradorActualizado.setUsername(username);
				administradorActualizado.setPassword(password);
				administradorActualizado.setDni(dni);
				administradorActualizado.setNombre(nombre);
				administradorActualizado.setApellido1(apellido1);
				administradorActualizado.setApellido2(apellido2);
				administradorActualizado.setEmail(email);
				administradorActualizado.setTelefono(telefono);
				administradorActualizado.setFechaNacimiento(fechaNacimiento);

						
				
					administradorDAO.actualizarAdministrador(administradorActualizado);
		            response.sendRedirect("AdministradorServlet?action=listarAdministradores");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
         
        
    } 
    
    
    
    
    
    
    
    
    
    
    
    

}
