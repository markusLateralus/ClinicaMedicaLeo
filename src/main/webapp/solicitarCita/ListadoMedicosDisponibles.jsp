<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="modelos.Medico" %>
<%@ page import="dao.MedicoDAO" %>
<%@ page import="modelos.Administrador" %>
<%@ page import="dao.AdministradorDAO" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>Lista de Medicos para pedir cita</title>
    <link rel="stylesheet" type="text/css" href="css/ListadoMedicosDisponibles.css">
</head>
<body>
<header>
<div class="divLogo">
    <img src="imagenes/ClinicaLeo2.png" alt="Logo de Clínica LEO" class="logo">
</div>
<div class="divH1">
    <h1>Clínica LEO</h1>
</div>
</header>

<nav>
    <ul class="menu">
        <li><a href="MedicoServlet?action=listarMedicos">Lista de Médicos</a></li>
        <!-- Submenú de Datos personales -->
        <li>
            <a href="#">Datos Personales</a>
            <ul class="submenu">
                <li><a href="PacienteServlet?action=consultarDatos">Consultarlos</a></li>
                <li><a href="PacienteServlet?action=editarDatos">Editarlos</a></li>
            </ul>
        </li>
        <li><a href="LogoutServlet">Cerrar Sesión</a></li>
    </ul>
</nav>
<br><br><br><br><br>
<main>
    <%
        // Obtener el tipo de usuario desde la sesión
        String tipoUsuario = (String) session.getAttribute("tipoUsuario");

        // Controlar acceso a la lista de pacientes
        if (tipoUsuario == null) {
            // Si el usuario no está logado, redirigir al login
            response.sendRedirect("Login.jsp");
            return;
        }
    %>

    <% if (tipoUsuario.equals("PACIENTE")) { %>
        <h2>LISTA DE MÉDICOS PARA PEDIR CITA</h2>
        <table border="1">
            <tr>
                <th>Nombre</th>
                <th>Apellido 1</th>
                <th>Apellido 2</th>
                <th>Especialidad</th>
                <th>Acciones</th>
            </tr>
            <%
                ArrayList<Medico> medicos = (ArrayList<Medico>) request.getAttribute("listaMedicos");
                if (medicos == null) {
            %>
                <h3>No hay médicos disponibles</h3>
            <% } else { 
                for (Medico medico : medicos) { %>
            <tr>
                <td><%= medico.getNombre() %></td>
                <td><%= medico.getApellido1() %></td>
                <td><%= medico.getApellido2() %></td>
                <td><%= medico.getEspecialidad() %></td>
                <td>
                    <a href="ConsultarCitaServlet?action=verHorarioDelMedico&id=<%= medico.getId() %>">VER HORARIO</a>
                </td>
            </tr>
            <% } } %>
        </table>
    <% } %>

</main>
<footer>
    <div class="social-icons">
        <a href="https://www.facebook.com" target="_blank"><img src="images/facebook-icon.png" alt="Facebook"></a>
        <a href="https://www.instagram.com" target="_blank"><img src="images/instagram-icon.png" alt="Instagram"></a>
        <a href="https://www.youtube.com" target="_blank"><img src="imagenes/youtubeES.png" alt="YouTube"></a>
    </div>
</footer>
</body>
</html>
