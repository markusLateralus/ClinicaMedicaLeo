package servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
	import jakarta.servlet.http.HttpServletRequest;
	import jakarta.servlet.http.HttpServletResponse;
import modelos.Horario;
import modelos.Notificacion;
import modelos.Paciente;

import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

import dao.HorarioDAO;
import dao.NotificacionDAO;
import dao.PacienteDAO;
import jakarta.mail.AuthenticationFailedException;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@WebServlet("/ContactoServlet")
public class ContactoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private NotificacionDAO notificacionDAO;
	private PacienteDAO pacienteDAO;
    public void init() throws ServletException {
    	
        try {
//        	 System.out.println("abriendo pacienteServlet");
            pacienteDAO = new PacienteDAO();
            notificacionDAO=new NotificacionDAO();
   
           
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("irContactoPersonaNoRegistrada".equals(action)) {
            irContactoPersonaNoRegistrada(request, response);
        }
    }
    
    private void irContactoPersonaNoRegistrada(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
        RequestDispatcher dispatcher = request.getRequestDispatcher("Contacto.jsp");
        try {
    		dispatcher.forward(request, response);
    	} catch (ServletException | IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    		System.out.println("ERROR MARCOS "+ e.getMessage() + " DEJAR CLARO " + e.hashCode()  );
    	}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("enviarEmailDelPaciente".equals(action)) {
            enviarEmailDelPaciente(request, response);
        }else if("enviarEmailPersonaNoRegistrada".equals(action)){
        	enviarEmailPersonaNoRegistrada(request,response);
        }
        
    }

    
   


	private void enviarEmailPersonaNoRegistrada(HttpServletRequest request, HttpServletResponse response) {
		   String nombre = request.getParameter("nombre");
	        String email = request.getParameter("email");
	        String mensaje = request.getParameter("mensaje");

	        // Validar parámetros
	        if (nombre == null || nombre.isEmpty() ||
	            email == null || email.isEmpty() || !esCorreoValido(email) ||
	            mensaje == null || mensaje.isEmpty()) {

	            try {
					response.getWriter().println("Por favor, completa todos los campos correctamente.");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            return;
	        }

	        // Configuración del correo
	        String asunto = "Contacto Desde Clinica Leo";
	        String host = "smtp.gmail.com";
	        final String user = "marcoslateralus@gmail.com";
	        final String password = "bxmq xjev nlkm cwun"; // Temporal

	        Properties properties = new Properties();
	        properties.put("mail.smtp.auth", "true");
	        properties.put("mail.smtp.ssl.enable", "true");
	        properties.put("mail.smtp.host", host);
	        properties.put("mail.smtp.port", "465"); //587     465

	        // Crear la sesión
	        Session session = Session.getInstance(properties, new Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(user, password);
	            }
	        });

	        try {
	            // Crear el mensaje con formato HTML
	            Message message = new MimeMessage(session);
	            message.setFrom(new InternetAddress(user));
	            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("marcoslateralus@gmail.com"));
	            message.setSubject(asunto);

	            String contenidoHtml = "<h1>Nuevo mensaje de contacto de " + nombre + " </h1>" +
	                                   "<p><b>Email:</b> " + email + "</p>" +
	                                   "<p><b>Mensaje:</b><br>" + mensaje + "</p>";

	            message.setContent(contenidoHtml, "text/html; charset=utf-8");

	            // Enviar el mensaje
	            Transport.send(message);
	            response.getWriter().println("El mensaje ha sido enviado exitosamente.");
                response.sendRedirect("ContactoServlet?action=irContactoPersonaNoRegistrada" );  // Redirigir a la página de inicio de médicos

	        } catch (MessagingException e) {
	            try {
					response.getWriter().println("Error al enviar el correo: " + e.getMessage());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

		
	

	private void enviarEmailDelPaciente(HttpServletRequest request, HttpServletResponse response) throws IOException {
	   	System.out.println("id email " + request.getParameter("id"));
		int idPaciente = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        String email = request.getParameter("email");
        String mensaje = request.getParameter("mensaje");

        // Validar parámetros
        if (nombre == null || nombre.isEmpty() ||
            email == null || email.isEmpty() || !esCorreoValido(email) ||
            mensaje == null || mensaje.isEmpty()) {

            response.getWriter().println("Por favor, completa todos los campos correctamente.");
            return;
        }

        // Configuración del correo
        String asunto = "Contacto Desde Clinica Leo";
        String host = "smtp.gmail.com";
        final String user = "marcoslateralus@gmail.com";
        final String password = "bxmq xjev nlkm cwun"; // Temporal

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465"); //587     465

        // Crear la sesión
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });

        try {
            // Crear el mensaje con formato HTML
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("marcoslateralus@gmail.com"));
            message.setSubject(asunto);

            String contenidoHtml = "<h1>Nuevo mensaje de contacto de " + nombre + " </h1>" +
                                   "<p><b>Email:</b> " + email + "</p>" +
                                   "<p><b>Mensaje:</b><br>" + mensaje + "</p>";

            message.setContent(contenidoHtml, "text/html; charset=utf-8");

            // Enviar el mensaje
            Transport.send(message);
           response.getWriter().println("El mensaje ha sido enviado exitosamente.");

        } catch (MessagingException e) {
            response.getWriter().println("Error al enviar el correo: " + e.getMessage());
        }
        
        
//        PacienteDAO pacienteDAO=null;
        Paciente paciente=null;
		try {
			pacienteDAO = new PacienteDAO();
			  List<Horario> horarios = pacienteDAO.obtenerHorariosPorMedico(idPaciente);
			  paciente=pacienteDAO.getPacienteById(idPaciente);
			  List<Notificacion> notificaciones = notificacionDAO.obtenerNotificacionesActivasParaPacientes(idPaciente);
		        request.setAttribute("notificaciones", notificaciones);
			  
		        request.setAttribute("horarios", horarios);
				request.setAttribute("paciente", paciente);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
	    RequestDispatcher dispatcher = request.getRequestDispatcher("Contacto.jsp");
        try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// TODO Auto-generated method stub
    }

    // Método para validar formato de correo electrónico
    private boolean esCorreoValido(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }
}




	
	

