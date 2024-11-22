<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="modelos.Medico" %>
<%@ page import="modelos.Paciente" %>
<%@ page import="modelos.Horario" %>
<%@ page import="dao.MedicoDAO" %>
<%@ page import="modelos.Administrador" %>
<%@ page import="dao.AdministradorDAO" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>Horario del Médico</title>
    <link rel="stylesheet" type="text/css" href="css/HorarioDelMedico.css">
    <style>
        .disponible { background-color: green; color: white; }
        .ocupado { background-color: red; color: white; }
        

    </style>
</head>
<% 
// Obtener la acción del parámetro
String tipoUsuario = (String) session.getAttribute("tipoUsuario");
// Inicializar variables para el medico
Paciente   paciente = (Paciente) request.getAttribute("paciente");
int idPaciente=paciente.getId();
Medico    medico = (Medico) request.getAttribute("medico");

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
        <li><a href="ConsultarCitaServlet?action=irSolicitarCita&id=<%=paciente.getId()  %>">Consultar especialistas</a></li>
                       <li><a href="RealizarReservaServlet?action=mostrarNotificaciones&idPaciente=<%=paciente.getId()  %>" >Consultar notificaciones</a></li>
       
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

<%

String medicoId = request.getParameter("id");
String horaDelHorario="";
String diaDelHorario="";

%>
    <h2>Horario del Médico <%=medico.getNombre() %></h2>
    <table border="1">
        <tr>
            <th>Hora</th>
            <th>Lunes</th>
            <th>Martes</th>
            <th>Miércoles</th>
            <th>Jueves</th>
            <th>Viernes</th>
        </tr>
        <%
        ArrayList<Horario> horarios = (ArrayList<Horario>) request.getAttribute("horarios");
        for (int hora = 9; hora < 14; hora++) {
        %>
            <tr>
                <td><%= hora %>:00 - <%= (hora + 1) %>:00</td>
                <% 
                String[] dias = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes"};
                for (String dia : dias) { 
                    boolean esDisponible = false;
                    int horarioId = 0;
  

                    for (Horario horario : horarios) {
                        if (horario.getDia().equals(dia) && horario.getHora().equals(hora + ":00:00") ||
                            horario.getDia().equals(dia) && horario.getHora().equals("0"+hora + ":00:00")) {
                            esDisponible = "disponible".equals(horario.getEstado());
                            horarioId = horario.getId();
                            horaDelHorario=horario.getHora();
                            diaDelHorario=horario.getDia();
                            break;
                        }
                    }
                %>

                <td>
                    <% if (esDisponible) { %>
                        <form action="RealizarReservaServlet" method="post" class="formularioReserva">
                            <input type="hidden" name="action" value="citaMedica">
                            <input type="hidden" name="id" value="<%= horarioId %>">
                             <input type="hidden" name="hora" value="<%= horaDelHorario %>">
                             <input type="hidden" name="dia" value="<%= diaDelHorario %>">
                            <input type="hidden" name="idMedico" value="<%= medicoId %>">
                            <input type="hidden" name="idPaciente" value="<%= idPaciente %>">
                            <button type="button" class="disponible reservarBtn">Reservar</button>
                        </form>
                    <% } else { %>
                        <div class="ocupado">Ocupado</div>
                    <% } %>
                </td>
                <% 
                } // Fin del for de días 
                %>
            </tr>
        <% 
        } // Fin del for de horas 
        %>
    </table>

</main>
<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<br><br><br><br><br><br>

<!-- Modal de Confirmación -->
<div class="modal" id="confirmModal">
    <div class="modal-content">
        <h3>Confirmar Reserva</h3>
        <p>¿Estás seguro de que deseas realizar esta reserva?</p>
        <button id="continuarReserva" class="confirm-button">Continuar</button>
        <button id="cancelarReserva" class="cancel-button">Cancelar</button>
    </div>
</div>

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
   <script src="./solicitarCita/HorarioDelMedico.js"></script>
</html>

