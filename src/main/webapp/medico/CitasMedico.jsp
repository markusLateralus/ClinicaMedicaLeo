<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="modelos.Medico" %>
<%@ page import="modelos.Notificacion" %>
<%@ page import="modelos.Paciente" %>
<%@ page import="modelos.Horario" %>
<%@ page import="java.util.Date" %>
<%@ page import="dao.MedicoDAO" %>
<%@ page import="modelos.Administrador" %>
<%@ page import="dao.AdministradorDAO" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>Notificaciones Medico</title>
    <link rel="stylesheet" type="text/css" href="css/CitasMedico.css">
</head>
<% 
// Obtener la acción del parámetro
String tipoUsuario = (String) session.getAttribute("tipoUsuario");
    Medico medico= (Medico) request.getAttribute("medico");

    Paciente paciente= (Paciente) request.getAttribute("paciente");
%>
<body>
<header>
<div class="divLogo">
<a href="MedicoServlet?action=irIndexMedico&id=<%=medico.getId()%>">
    <img src="imagenes/ClinicaLeo2.png" alt="Logo de Clínica LEO" class="logo">
    </a>
</div>
<div class="divH1">
    <h1>Clínica LEO</h1>
</div>
</header>

<nav>
    <ul class="menu">
        <li><a href="MedicoServlet?action=irIndexMedico&id=<%=medico.getId()%>">Consultar horario semanal</a></li>
                       <li><a href="RealizarReservaServlet?action=mostrarCitasMedico&idMedico=<%=medico.getId()  %>" >Consultar Citas</a></li>
       
        <!-- Submenú de Datos personales -->
        <li>
            <a href="#">Datos Personales</a>
            <ul class="submenu">
                <li><a href="MedicoServlet?action=verMedico&id=<%=medico.getId()%>">Consultarlos</a></li>
                <li><a href="MedicoServlet?action=irEditarMedico&id=<%=medico.getId()%>">Editarlos</a></li>
            </ul>
        </li>
        <li><a href="LogoutServlet">Cerrar Sesión</a></li>
    </ul>
        <div class="divTipoUsuario">
  	  <h4><%= medico.getNombre() %></h4>
         <h4><%= "Tipo Usuario: " + tipoUsuario %></h4>
      </div>
</nav>
<br>

<main>
 <div class="expliacionPagina">
<p>Desde aquí puedes consultar tus citas y cancelarlas</p>
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
                <th>Paciente</th>
                 <th>Cancela tu cita</th>
   
            </tr>
        </thead>
        <tbody>
            <% if (horarios != null && !horarios.isEmpty()) { %>
                <% for (Horario horario : horarios) { %>
                    <tr>
                        <td><%= horario.getDia() %></td>
                        <td><%= horario.getHora() %></td>
                        <td><%= paciente.getNombre() %> <%=paciente.getApellido1() %></td>
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
<div class="modal" id="confirmModal2">
    <div class="modal-content">
        <h3>Cancelar Cita</h3>
         <% if (horarios != null && !horarios.isEmpty()) { %>
        <p>¿Estás seguro de que deseas cancelar la cita médica con el paciente <%=paciente.getNombre() + " " +paciente.getApellido1()%>?</p>
        <%} %>
        <form id="cancelarCitaForm" action="MedicoServlet" method="post">
            <input type="hidden" name="action" value="cancelarCitaMedico">
            <input type="hidden" name="idHorario" id="modalHorarioId">
            <input type="hidden" name="idMedico" id="modalMedicoId">
            <input type="hidden" name="idPaciente" id="modalPacienteId">
            <button type="submit" class="confirm-button">Confirmar</button>
            <button type="button" class="cancel-button" id="cancelarModal">Cancelar</button>
        </form>
    </div>
</div>
<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<br><br><br><br><br><br><br><br><br>
	<footer class="footer">
		<div class="footer-container">
			<div class="footer-left">
					<a href="MedicoServlet?action=irAvisoLegal&id=<%=medico.getId()%>">Aviso Legal</a>
				 <a href="MedicoServlet?action=irPoliticaPrivacidad&id=<%=medico.getId()%>">Políticas de Privacidad</a>
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
 <script src="./js/notificacionesMedico.js"></script>
</html>
