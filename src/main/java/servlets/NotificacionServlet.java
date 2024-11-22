package servlets;
import dao.NotificacionDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;

@WebServlet("/NotificacionServlet")
public class NotificacionServlet extends HttpServlet {

    private NotificacionDAO notificacionDAO;

    @Override
    public void init() {
        try {
			notificacionDAO = new NotificacionDAO();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if ("eliminar".equals(action)) {
                eliminarNotificacion(request, response);
            } else {
                response.sendRedirect("indexPaciente.jsp");
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void eliminarNotificacion(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int idNotificacion = Integer.parseInt(request.getParameter("id"));
        int idPaciente = Integer.parseInt(request.getParameter("idPaciente"));
        notificacionDAO.marcarNotificacionComoInactiva(idNotificacion);
//        response.sendRedirect("PacienteServlet?action=verNotificaciones");
        request.setAttribute("idPaciente", idPaciente);
        response.sendRedirect("RealizarReservaServlet?action=mostrarNotificaciones&idPaciente="+idPaciente);

    }
}

