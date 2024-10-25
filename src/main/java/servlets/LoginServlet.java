package servlets;



import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelos.Medico;
import modelos.Paciente;
import modelos.Administrador;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;

import dao.MedicoDAO;
import dao.PacienteDAO;
import dao.AdministradorDAO;

// El servlet se mapea a la URL /LoginServlet
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AdministradorDAO administradorDAO;
    private PacienteDAO pacienteDAO;
    private MedicoDAO medicoDAO;

    public void init() {
        try {
			administradorDAO = new AdministradorDAO();
			pacienteDAO=new PacienteDAO();
			medicoDAO= new MedicoDAO();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // Asegúrate de tener tu DAO bien inicializado
 catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 request.getRequestDispatcher("Login.jsp").forward(request, response);
    	
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recoger el username y password del formulario de login
//    	System.out.println("dentro");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String tipoUsuario = request.getParameter("tipoUsuario");
        RequestDispatcher dispatcher=null;
        // Variables para almacenar el paciente o médico encontrado
        Paciente paciente = null;
        Medico medico = null;
        Administrador administrador=null;
        // Intenta loguear como paciente
        if ("PACIENTE".equals(tipoUsuario)) {
        	System.out.println("tipo  "   + tipoUsuario);
            paciente = pacienteDAO.logarse(username, password);
            if (paciente != null) {
                // Usuario y tipo de usuario coinciden
                request.getSession().setAttribute("tipoUsuario", tipoUsuario);
                response.sendRedirect("ConsultarCitaServlet?action=irSolicitarCita&id="+paciente.getId() );  // Redirigir a la página de inicio de médicos
     
                return;
            }
        }

        // Intenta loguear como médico
        if ("MEDICO".equals(tipoUsuario)) {
            medico = medicoDAO.logarse(username, password);
            if (medico != null) {
                // Usuario y tipo de usuario coincide
            	int medicoId=medico.getId();
                request.getSession().setAttribute("tipoUsuario", tipoUsuario);
                request.getSession().setAttribute("medicoId", medicoId);
//                response.sendRedirect("ConsultarCitaServlet?action=verHorarioDelMedico&id="+medicoId);
                response.sendRedirect("ConsultarCitaServlet?action=irIndexMedico&id="+medicoId );  // Redirigir a la página de inicio de médicos
                return;
            }
        }
        
        
        // Intenta loguear como administrador
        if ("ADMINISTRADOR".equals(tipoUsuario)) {
        	administrador = administradorDAO.logarse(username, password);
            if (administrador != null) {
                // Usuario y tipo de usuario coinciden
                request.getSession().setAttribute("tipoUsuario", tipoUsuario);
//                response.sendRedirect("AdministradorServlet?action=verAdministrador&id="+administrador.getId() );  // Redirigir a la página de inicio de médicos
                response.sendRedirect("AdministradorServlet?action=irIndexAdministrador&id="+administrador.getId() );  // Redirigir a la página de inicio de médicos

                return;
            }
        }

        
        

        // Si no encontró el usuario con el tipo de usuario seleccionado, verificamos el otro tipo
        // Verificar si existe como paciente pero seleccionaron "MEDICO"
        if ("MEDICO".equals(tipoUsuario)) {
            paciente = pacienteDAO.logarse(username, password);
            if (paciente != null) {
                // Usuario existe como paciente, pero seleccionó el tipo incorrecto
                request.setAttribute("error", "Credenciales o tipo de usuario incorrecto, Inéntelo de  nuevo.");
            }
        }

        // Verificar si existe como médico pero seleccionaron "PACIENTE"
        if ("PACIENTE".equals(tipoUsuario)) {
            medico = medicoDAO.logarse(username, password);
            if (medico != null) {
                // Usuario existe como médico, pero seleccionó el tipo incorrecto
                request.setAttribute("error", "Credenciales o tipo de usuario incorrecto, Inéntelo de  nuevo.");
            }
        }
        
        // Verificar si existe como médico pero seleccionaron "PACIENTE"
        if ("ADMINISTRADOR".equals(tipoUsuario)) {
            administrador = administradorDAO.logarse(username, password);
            if (administrador != null) {
                // Usuario existe como médico, pero seleccionó el tipo incorrecto
                request.setAttribute("error", "Credenciales o tipo de usuario incorrecto, Inéntelo de  nuevo.");
            }
        }
        
        

        // Si no se encontró el usuario en absoluto (ni como médico ni como paciente ni como administrador)
        if (paciente == null && medico == null && administrador==null) {
            request.setAttribute("error", "Credenciales incorrectas. Inténtalo de nuevo.");
        }

        // Volver al formulario de login con el error
        request.getRequestDispatcher("Login.jsp").forward(request, response);
    }
}

