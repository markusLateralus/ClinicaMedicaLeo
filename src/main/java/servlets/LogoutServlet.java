package servlets;



import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Invalidar la sesión actual
        HttpSession session = request.getSession(false); // Obtener la sesión sin crear una nueva
        if (session != null || session.getAttribute("usuario") == null) {
            session.invalidate(); // Invalidar la sesión actual
        }
        
        // Redirigir al formulario de login
        response.sendRedirect("Login.jsp");
    }
}
