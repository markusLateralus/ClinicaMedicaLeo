<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="modelos.Medico" %>
<%@ page import="modelos.NotificacionTest" %>
<%@ page import="modelos.Paciente" %>
<%@ page import="modelos.Medico" %>
<%@ page import="modelos.Horario" %>
<%@ page import="java.util.Date" %>
<%@ page import="dao.MedicoDAO" %>
<%@ page import="modelos.Administrador" %>
<%@ page import="dao.AdministradorDAO" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>Citas del Paciente</title>
    <link rel="stylesheet" type="text/css" href="css/CitasPaciente.css">
</head>
<% 
// Obtener la acción del parámetro
String tipoUsuario = (String) session.getAttribute("tipoUsuario");
    Paciente paciente = (Paciente) request.getAttribute("paciente");
    Medico medico = (Medico) request.getAttribute("medico");
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
<p>PUEDES CANCELAR LA CITA SI DESEAS</p>
</div>
<% 
ArrayList<Horario> horarios= (ArrayList<Horario>) request.getAttribute("horarios");

%>
<h2>Citas Reservadas</h2>
    <table>
        <thead>
            <tr>
                <th>Día</th>
                <th>Hora</th>
                <th>Especialista</th>
                <th>Cancelación de cita</th>
                 
   
            </tr>
        </thead>
        <tbody>
            <% if (horarios != null && !horarios.isEmpty()) { %>
                <% for (Horario horario : horarios) { %>
                    <tr>
                        <td><%= horario.getDia() %></td>
                        <td><%= horario.getHora() %></td>
                        <td><%= medico.getEspecialidad() %></td>
                      
                    
    					 <td>
    					   <button 
                                type="button" 
                                class="cancelarBtn" 
                                data-id="<%= horario.getId() %>"
                                data-id-medico="<%= horario.getMedicoId() %>"
                                data-id-paciente="<%= horario.getPacienteId() %>"
                            >
                                Cancelar
                            </button>
    					 </td>
    					 
                    </tr>
                <% } %>
            <% } else { %>
                <tr>
                    <td colspan="4">No tienes citas reservadas.</td>
                </tr>
                
            <% } %>
            
        </tbody>
        
    </table>

</main>
<div class="modal" id="confirmModal2">
    <div class="modal-content">
        <h3>Cancelar Cita</h3>
          <% if (horarios != null && !horarios.isEmpty()) { %>
        <p>¿Estás seguro de que deseas cancelar la cita médica con <%= medico.getNombre() + " " + medico.getApellido1() %>?</p>
        <%} %>
        <form id="cancelarCitaForm" action="PacienteServlet" method="post">
            <input type="hidden" name="action" value="cancelarCitaPaciente">
            <input type="hidden" name="idHorario" id="modalHorarioId">
            <input type="hidden" name="idMedico" id="modalMedicoId">
            <input type="hidden" name="idPaciente" id="modalPacienteId">
            <button type="submit" class="confirm-button">Confirmar</button>
            <button type="button" class="cancel-button" id="cancelarModal">Cancelar</button>
        </form>
    </div>
</div>
<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<br><br><br><br><br><br>

<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<br><br><br><br><br><br><br><br><br>
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
 <script src="./js/notificacionesPaciente.js"></script>
</html>
