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
       <li><a href="RealizarReservaServlet?action=mostrarCitasPaciente&idPaciente=<%=paciente.getId()  %>" >Consultar citas</a></li>
       
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
 <div class="expliacionPagina">
<p>BIENVENIDO <%= paciente.getNombre() %></p>

<p>Desde aquí puedes consultar tus notificaciones de reserva de cita</p>
</div>
<% 
ArrayList<Notificacion> notificaciones= (ArrayList<Notificacion>) request.getAttribute("notificaciones");
if (notificaciones != null && !notificaciones.isEmpty()) {
%>
<div class="mensajes-container">
    <h3>Notificaciones</h3>
    <ul>
        <% for (int i = 0; i < notificaciones.size(); i++) { %>
            
            <% if (notificaciones.get(i).getMensajePaciente().length()>1){%>
               <li>
                <%=notificaciones.get(i).getMensajePaciente()%>
             
                <a href="NotificacionServlet?action=eliminarNotificacionPaciente&id=<%=notificaciones.get(i).getId() %>&idPaciente=<%=paciente.getId()%>" class="eliminar-notificacion">Desactivar</a>
   </li>
<%} %>

         
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
<br><br>
 <div class="expliacionPagina">

<p>Nuestras instalaciones</p>
</div>
    <!-- Slider -->
    <div class="slider">
        <button class="prev" onclick="moveSlide(-1)">&#10094;</button>
        <div class="slides">
            <img src="imagenes/ClinicaLeo2.png" alt="Logo de Clínica LEO" class="slide">
            <img src="imagenes/salaDeEspera.png" alt="sala de espera" class="slide">
            <img src="imagenes/salaDeEsperaPediatria.png" alt="sala de espera Pediatría" class="slide">
            <img src="imagenes/salaDeEsperaPsiquiatria.png" alt="sala de espera Psiquiatria" class="slide">
             <img src="imagenes/exteriores1.png" alt="exteriores" class="slide">
            <img src="imagenes/aparcamiento.png" alt="aparcamientos" class="slide">
              <img src="imagenes/recepcion.png" alt="recepcion" class="slide">
        </div>
        <button class="next" onclick="moveSlide(1)">&#10095;</button>
    </div>
    
    

    
</main>

<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>

	<footer class="footer">
		<div class="footer-container">
			<div class="footer-left">
					<a href="PacienteServlet?action=irContacto&id=<%=paciente.getId()%>">Contacto</a> 
				<a href="PacienteServlet?action=irAvisoLegal&id=<%=paciente.getId()%>">Aviso Legal</a>
				 <a href="PacienteServlet?action=irPoliticaPrivacidad&id=<%=paciente.getId()%>">Políticas de Privacidad</a>
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
<script src="js/slider.js"></script>
</html>
