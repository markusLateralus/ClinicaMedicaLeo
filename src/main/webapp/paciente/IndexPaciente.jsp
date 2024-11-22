<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="modelos.Medico" %>
<%@ page import="modelos.Notificacion" %>
<%@ page import="modelos.Paciente" %>
<%@ page import="java.util.Date" %>
<%@ page import="dao.MedicoDAO" %>
<%@ page import="modelos.Administrador" %>
<%@ page import="dao.AdministradorDAO" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>Index Paciente</title>
    <link rel="stylesheet" type="text/css" href="css/IndexPaciente.css">
</head>
<% 
// Obtener la acción del parámetro
String tipoUsuario = (String) session.getAttribute("tipoUsuario");
    Paciente paciente = (Paciente) request.getAttribute("paciente");
%>
<body>
<header>
<div class="divLogo">
<a href="PacienteServlet?action=irIndexPaciente&id=<%=paciente.getId()%>">
    <img src="imagenes/ClinicaLeo2.png" alt="Logo de Clínica LEO" class="logo">
    </a>
</div>
<div class="divH1">
    <h1>Clínica LEO</h1>
</div>
</header>

<nav>
    <ul class="menu">
        <li><a href="ConsultarCitaServlet?action=irSolicitarCita&id=<%=paciente.getId()  %>" >Consultar especialistas</a></li>
        <!-- Submenú de Datos personales -->
   
                <li><a href="RealizarReservaServlet?action=mostrarNotificaciones&idPaciente=<%=paciente.getId()  %>" >Consultar notificaciones</a></li>
        <!-- Submenú de Datos personales -->
        <li>
            <a href="#">Datos Personales</a>
            <ul class="submenu">
                <li><a href="PacienteServlet?action=verPaciente&id=<%= paciente.getId() %>">Consultarlos</a></li>
                <li><a href="PacienteServlet?action=irEditarPaciente&id=<%= paciente.getId() %>">Editarlos</a></li>
            </ul>
        </li>
        <li><a href="LogoutServlet">Cerrar Sesión</a></li>
    </ul>
            <div class="divTipoUsuario">
  	  <h4><%= paciente.getNombre() %></h4>
         <h4><%= "Tipo Usuario: " + tipoUsuario %></h4>
      </div>
</nav>
<br>

<main>
<p>BIENVENIDO <%= paciente.getNombre() %></p>
<p>Cita médica Reservada</p>
<% 
List<Notificacion> mensajesPaciente = (List<Notificacion>) session.getAttribute("mensajesPaciente");
if (mensajesPaciente != null && !mensajesPaciente.isEmpty()) {
%>
<div class="mensajes-container">
    <h3>Notificaciones</h3>
    <ul>
        <% for (int i = 0; i < mensajesPaciente.size(); i++) { %>
            <li>
                <%= mensajesPaciente.get(i).getMensaje() %>
                <button class="btn-eliminar" onclick="eliminarMensaje(<%= i %>)">x</button>
            </li>
        <% } %>
    </ul>
</div>
<%
}
%>

<script>
function eliminarMensaje(indice) {
    // Redirigir al servidor para eliminar el mensaje de la lista
     if (indice !== undefined && indice !== null) {
        window.location.href = `PacienteServlet?action=eliminarMensaje&indice=${indice}`;
    } else {
        alert("El índice no es válido");
    }
}
</script>

</main>

<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<br><br><br><br><br><br><br><br><br>
	<footer class="footer">
		<div class="footer-container">
			<div class="footer-left">
				<a href="#contacto">Contacto</a> <a href="#aviso-legal">Aviso
					Legal</a> <a href="#politicas-privacidad">Políticas de Privacidad</a>
			</div>
			<div class="footer-right">
				<a href="https://www.facebook.com" target="_blank"><img
					src="imagenes/facebook.png" alt="Facebook"></a> <a
					href="https://www.instagram.com" target="_blank"><img
					src="imagenes/instagram.png" alt="Instagram"></a> <a
					href="https://www.youtube.com" target="_blank"><img
					src="imagenes/youtube.png" alt="YouTube"></a>
			</div>
		</div>

		<p class="footer-author">Autor: Marcos Antonio Arrornes Alcañiz
			&copy; 2024</p>

	</footer>
</body>

</html>
