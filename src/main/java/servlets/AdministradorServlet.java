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
//        System.out.println("id Admin " + request.getParameter("id"));
    	int idAdministrador = Integer.parseInt(request.getParameter("id"));
        
//        MedicoDAO medicoDAO=null;
        AdministradorDAO administradorDAO=null;
        Administrador administrador=null;
		try {
			medicoDAO = new MedicoDAO();
			administradorDAO=new AdministradorDAO();
			 administrador=administradorDAO.getAdministradorById(idAdministrador);
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
     	int idAdministrador = Integer.parseInt(request.getParameter("id"));
        AdministradorDAO administradorDAO=null;
     	Administrador administrador=null;
    
    	try {
            List<Paciente> listaPacientes = pacienteDAO.getAllPacientes();
        	administradorDAO=new AdministradorDAO();
        	administrador=administradorDAO.getAdministradorById(idAdministrador);
            request.setAttribute("listaPacientes", listaPacientes);
            request.setAttribute("administrador", administrador);
            RequestDispatcher dispatcher = request.getRequestDispatcher("./administrador/ListarPacientes.jsp");
         
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            throw new ServletException(e);
        } catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

   
    private void listarMedicos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//    	 System.out.println("Listar medicos id  " + request.getParameter("idAdmininstrador"));
    	int idAdministrador = Integer.parseInt(request.getParameter("id"));
        AdministradorDAO administradorDAO=null;
     	Administrador administrador=null;
    	
    	try {
            List<Medico> listaMedicos = medicoDAO.getAllMedicos(); // Obtener todos los médicos
         	administradorDAO=new AdministradorDAO();
        	administrador=administradorDAO.getAdministradorById(idAdministrador);
     
            request.setAttribute("administrador", administrador);
            request.setAttribute("listaMedicos", listaMedicos);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("./administrador/ListarMedicos.jsp"); // Redirige a la lista de médicos
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            throw new ServletException(e);
        } catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
    	int idAdministrador = Integer.parseInt(request.getParameter("id"));
//      System.out.println("id Admin IR CREAR MEDICO " + idAdministrador);
   
   	Administrador administrador=null;
  	try {
  		administradorDAO=new AdministradorDAO();
			administrador=administradorDAO.getAdministradorById(idAdministrador);
		} catch (SQLException | NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  	  request.setAttribute("administrador",administrador);
    	 request.getRequestDispatcher("./administrador/InsertarPacienteA.jsp").forward(request, response);
		
	}
    private void irCrearMedico(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int idAdministrador = Integer.parseInt(request.getParameter("id"));
//        System.out.println("id Admin IR CREAR MEDICO " + idAdministrador);
     
     	Administrador administrador=null;
    	try {
    		administradorDAO=new AdministradorDAO();
			administrador=administradorDAO.getAdministradorById(idAdministrador);
		} catch (SQLException | NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	  request.setAttribute("administrador",administrador);
      	 request.getRequestDispatcher("./administrador/InsertarMedicoA.jsp").forward(request, response);
   		
   	}
    private void irCrearAdministrador(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
      	 request.getRequestDispatcher("./administrador/InsertarAdministrador.jsp").forward(request, response);
   		
   	}
    
    
    
    private void verPaciente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 int idPaciente = Integer.parseInt(request.getParameter("idPaciente"));
         int idAdministrador = Integer.parseInt(request.getParameter("idAdministrador"));

    	 Paciente paciente=null;
		try {
			paciente = pacienteDAO.getPacienteById(idPaciente);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 Administrador administrador=null;
	        try {
				administrador=administradorDAO.getAdministradorById(idAdministrador);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        request.setAttribute("paciente", paciente);
        request.setAttribute("administrador", administrador);
    	request.getRequestDispatcher("./administrador/VerPacienteA.jsp").forward(request, response);
		
	}
    private void verMedico(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 int idAdministrador = Integer.parseInt(request.getParameter("idAdministrador"));
    	int idMedico = Integer.parseInt(request.getParameter("idMedico"));
   	Medico medico=null;
		try {
			medico = medicoDAO.getMedicoById(idMedico);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 Administrador administrador=null;
	        try {
				administrador=administradorDAO.getAdministradorById(idAdministrador);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
       request.setAttribute("medico", medico);
       request.setAttribute("administrador", administrador);
   	request.getRequestDispatcher("./administrador/VerMedicoA.jsp").forward(request, response);
		
	}
    private void verAdministrador(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   
    	int id = Integer.parseInt(request.getParameter("idAdministrador"));
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
        int idAdministrador = Integer.parseInt(request.getParameter("idAdministrador"));
        int idPaciente = Integer.parseInt(request.getParameter("idPaciente"));
        Paciente paciente =null;
        try {
             paciente = pacienteDAO.getPacienteById(idPaciente);
          
        
        } catch (SQLException e) {
            throw new ServletException(e);
        }
        
        Administrador administrador=null;
        try {
			administrador=administradorDAO.getAdministradorById(idAdministrador);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        request.setAttribute("paciente", paciente);
        request.setAttribute("administrador", administrador);
        RequestDispatcher dispatcher = request.getRequestDispatcher("./administrador/EditarPacienteA.jsp");
        dispatcher.forward(request, response);
    }
    private void irEditarMedico(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idAdministrador = Integer.parseInt(request.getParameter("idAdministrador"));
        int idMedico = Integer.parseInt(request.getParameter("idMedico"));
        Medico medico =null;
        try {
             medico = medicoDAO.getMedicoById(idMedico);
          
        
        } catch (SQLException e) {
            throw new ServletException(e);
        }
        
        Administrador administrador=null;
        try {
			administrador=administradorDAO.getAdministradorById(idAdministrador);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        request.setAttribute("medico", medico);
        request.setAttribute("administrador", administrador);
        RequestDispatcher dispatcher = request.getRequestDispatcher("./administrador/EditarMedicoA.jsp");
        dispatcher.forward(request, response);
    }
    private void irEditarAdministrador(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("idAdministrador"));
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
        System.out.println("DENTRO ELIMINAR Paciente");
    	int idPaciente = Integer.parseInt(request.getParameter("idPaciente"));
        int idAdministrador = Integer.parseInt(request.getParameter("idAdministrador"));
        Administrador administrador=null;
       Paciente pacienteEliminado=null;
        try {
        	pacienteEliminado=pacienteDAO.getPacienteById(idPaciente);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        try {
        	administradorDAO.asignarPermisosPaciente(idAdministrador, idPaciente);
			administradorDAO.eliminarPaciente(idAdministrador, pacienteEliminado);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


            response.sendRedirect("AdministradorServlet?action=listarPacientes&id="+idAdministrador);
    }
    
    private void eliminarMedico(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("DENTRO ELIMINAR MEDICO");
    	int idMedico = Integer.parseInt(request.getParameter("idMedico"));
        int idAdministrador = Integer.parseInt(request.getParameter("idAdministrador"));
        Administrador administrador=null;
        Medico medicoEliminado=null;
        try {
			medicoEliminado=medicoDAO.getMedicoById(idMedico);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        try {
        	administradorDAO.asignarPermisosMedico(idAdministrador, idMedico);
			administradorDAO.eliminarMedico(idAdministrador, medicoEliminado);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


            response.sendRedirect("AdministradorServlet?action=listarMedicos&id="+idAdministrador);
        
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
        else if ("crearMedicoA".equals(action)) {
        	crearMedicoA(request, response);
        }
        else if ("crearPacienteA".equals(action)) {
        	crearPacienteA(request, response);
        }
        else if ("crearAdministrador".equals(action)) {
        	crearAdministrador(request, response);
        }
  
        else if ("actualizarMedico".equals(action)) {
            
            actualizarMedico(request, response);
        }
        else if ("actualizarPacienteA".equals(action)) {
            
            actualizarPacienteA(request, response);
        }
        else if ("actualizarMedicoA".equals(action)) {
            
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

    private void crearMedicoA(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	
    	int idAdministrador = Integer.parseInt(request.getParameter("idAdministrador"));
      	Administrador administrador=null;
      	 try {
			administrador=administradorDAO.getAdministradorById(idAdministrador);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

        Medico medicoAsociado=null;
     int idMedico;
        try {
           idMedico=administradorDAO.insertarMedico(medicoNuevo);
 
        } catch (SQLException e) {
            throw new ServletException(e);
        }
        
        try {
			medicoAsociado=medicoDAO.getMedicoById(idMedico);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        System.out.println("MEDICO ASOCIADO id " + medicoAsociado.getId() + " nombre " + medicoAsociado.getNombre() );
        try {
			administradorDAO.asignarPermisosMedico(administrador.getId(), medicoAsociado.getId());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         request.setAttribute("administrador", administrador);
         response.sendRedirect("AdministradorServlet?action=listarMedicos&id="+administrador.getId());
    }

    private void crearPacienteA(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	
    	int idAdministrador = Integer.parseInt(request.getParameter("idAdministrador"));
      	Administrador administrador=null;
      	 try {
			administrador=administradorDAO.getAdministradorById(idAdministrador);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Paciente pacienteNuevo = new Paciente();
        pacienteNuevo.setUsername(request.getParameter("username"));
        pacienteNuevo.setPassword(request.getParameter("password"));
        pacienteNuevo.setDni(request.getParameter("dni"));
        pacienteNuevo.setNombre(request.getParameter("nombre"));
        pacienteNuevo.setApellido1(request.getParameter("apellido1"));
        pacienteNuevo.setApellido2(request.getParameter("apellido2"));
        pacienteNuevo.setEmail(request.getParameter("email"));
        pacienteNuevo.setTelefono(request.getParameter("telefono"));
        pacienteNuevo.setFechaNacimiento(Date.valueOf(request.getParameter("fechaNacimiento")));

        Paciente pacienteAsociado=null;
     int idPaciente;
        try {
           idPaciente=administradorDAO.insertarPaciente(pacienteNuevo);
 
        } catch (SQLException e) {
            throw new ServletException(e);
        }
        
        try {
			pacienteAsociado=pacienteDAO.getPacienteById(idPaciente);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        System.out.println("Paciente ASOCIADO id " + pacienteAsociado.getId() + " nombre " + pacienteAsociado.getNombre() );
        try {
			administradorDAO.asignarPermisosPaciente(administrador.getId(), pacienteAsociado.getId());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         request.setAttribute("administrador", administrador);
         response.sendRedirect("AdministradorServlet?action=listarPacientes&id="+administrador.getId());
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

    
    
    
    
    
    private void actualizarPacienteA(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int idAdministrador = Integer.parseInt(request.getParameter("idAdministrador"));
    	int idPaciente = Integer.parseInt(request.getParameter("idPaciente"));
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
       
			Administrador administrador=null;
			try {
				administrador=administradorDAO.getAdministradorById(idAdministrador);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 	Paciente pacienteActualizado=null;
			try {
//				administrador=administradorDAO.getAdministradorById(idAdministrador);
				pacienteActualizado = pacienteDAO.getPacienteById(idPaciente);
				pacienteActualizado.setId(idPaciente);
				pacienteActualizado.setUsername(username);
				pacienteActualizado.setPassword(password);
				pacienteActualizado.setDni(dni);
				pacienteActualizado.setNombre(nombre);
				pacienteActualizado.setApellido1(apellido1);
				pacienteActualizado.setApellido2(apellido2);
				pacienteActualizado.setEmail(email);
				pacienteActualizado.setTelefono(telefono);
				pacienteActualizado.setFechaNacimiento(fechaNacimiento);
				

						
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
         
			
			try {
						administradorDAO.asignarPermisosPaciente(administrador.getId(),pacienteActualizado.getId());
				boolean resultado=administradorDAO.actualizarPaciente(administrador.getId(), pacienteActualizado);
				  response.sendRedirect("AdministradorServlet?action=listarPacientes&id="+administrador.getId());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	          
    } 
    
    
    private void actualizarMedico(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int idAdministrador = Integer.parseInt(request.getParameter("idAdministrador"));
    	int idMedico = Integer.parseInt(request.getParameter("idMedico"));
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
       
       
			Administrador administrador=null;
			try {
				administrador=administradorDAO.getAdministradorById(idAdministrador);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 	Medico medicoActualizado=null;
			try {
//				administrador=administradorDAO.getAdministradorById(idAdministrador);
				medicoActualizado = medicoDAO.getMedicoById(idMedico);
				medicoActualizado.setId(idMedico);
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

						
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
         
			
			try {
						administradorDAO.asignarPermisosMedico(administrador.getId(),medicoActualizado.getId());
				boolean resultado=administradorDAO.actualizarMedico(administrador.getId(), medicoActualizado);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	            response.sendRedirect("AdministradorServlet?action=listarMedicos&id="+administrador.getId());
    } 
    
    
    
    
    
    
    private void actualizarAdministrador(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	int id = Integer.parseInt(request.getParameter("idAdministrador"));
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
		            response.sendRedirect("AdministradorServlet?action=irIndexAdministrador&id="+administradorActualizado.getId());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
         
        
    } 
    
    
    
    
    
    
    
    
    
    
    
    

}
