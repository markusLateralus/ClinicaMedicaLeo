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
        String tipoUsuario = request.getParameter("tipoUsuario");
        try {
        if(tipoUsuario.equals("") || tipoUsuario==null) {
//        	System.out.println("tipo " + tipoUsuario);
    	request.getRequestDispatcher("Login.jsp").forward(request, response);
        }
        }catch(Exception e) {
//        	System.out.println("sin tipo de usuario " + tipoUsuario);
        	request.getRequestDispatcher("Login.jsp").forward(request, response);
        }
        
    }

  
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String tipoUsuario = request.getParameter("tipoUsuario");
        RequestDispatcher dispatcher = null;

        // Variables para almacenar el usuario encontrado
        Paciente paciente = null;
        Medico medico = null;
        Administrador administrador = null;

        boolean credencialesIncorrectas = false;

        // Intenta loguear como PACIENTE
        if ("PACIENTE".equals(tipoUsuario)) {
            paciente = pacienteDAO.logarse(username, password);
            if (paciente != null) {
                if (!password.equals(paciente.getPassword())) {
                    request.setAttribute("errorPassword", "La contraseña no coincide.");
                } else if (!username.equals(paciente.getUsername())) {
                    request.setAttribute("errorUsername", "El nombre de usuario no coincide.");
                } else {
                    // Usuario y contraseña correctos
                    request.getSession().setAttribute("tipoUsuario", tipoUsuario);
                    response.sendRedirect("PacienteServlet?action=irIndexPaciente&id=" + paciente.getId());
                    return;
                }
            } else {
                credencialesIncorrectas = true;
            }
        }

        // Intenta loguear como MEDICO
        if ("MEDICO".equals(tipoUsuario)) {
            medico = medicoDAO.logarse(username, password);
            if (medico != null) {
                if (!password.equals(medico.getPassword())) {
                    request.setAttribute("errorPassword", "La contraseña no coincide.");
                } else if (!username.equals(medico.getUsername())) {
                    request.setAttribute("errorUsername", "El nombre de usuario no coincide.");
                } else {
                    // Usuario y contraseña correctos
                    int medicoId = medico.getId();
                    request.getSession().setAttribute("tipoUsuario", tipoUsuario);
                    request.getSession().setAttribute("medicoId", medicoId);
                    response.sendRedirect("MedicoServlet?action=irIndexMedico&id=" + medicoId);
                    return;
                }
            } else {
                credencialesIncorrectas = true;
            }
        }

        // Intenta loguear como ADMINISTRADOR
        if ("ADMINISTRADOR".equals(tipoUsuario)) {
            administrador = administradorDAO.logarse(username, password);
            if (administrador != null) {
                if (!password.equals(administrador.getPassword())) {
                    request.setAttribute("errorPassword", "La contraseña no coincide.");
                } else if (!username.equals(administrador.getUsername())) {
                    request.setAttribute("errorUsername", "El nombre de usuario no coincide.");
                } else {
                    // Usuario y contraseña correctos
                    int idAdministrador = administrador.getId();
                    request.getSession().setAttribute("tipoUsuario", tipoUsuario);
                    request.getSession().setAttribute("idAdministrador", idAdministrador);
                    response.sendRedirect("AdministradorServlet?action=irIndexAdministrador&id=" + idAdministrador);
                    return;
                }
            } else {
                credencialesIncorrectas = true;
            }
        }

        // Si las credenciales son correctas pero el tipo de usuario es incorrecto
        if (credencialesIncorrectas) {
            request.setAttribute("errorCredenciales", "Tipo de usuario incorrecto. Inténtelo nuevamente.");
        } else {
            // Si no se encontró ningún usuario
            request.setAttribute("error", "Credenciales incorrectas. Inténtelo nuevamente.");
        }

        // Redirigir al formulario de login con el error
        dispatcher = request.getRequestDispatcher("Login.jsp");
        dispatcher.forward(request, response);
    }

}

