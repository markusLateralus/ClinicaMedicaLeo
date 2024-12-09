<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="modelos.Medico" %>
<%@ page import="modelos.Paciente" %>
<%@ page import="java.util.Date" %>
<%@ page import="dao.MedicoDAO" %>
<%@ page import="modelos.Administrador" %>
<%@ page import="dao.AdministradorDAO" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>Lista de Medicos para pedir cita</title>
    <link rel="stylesheet" type="text/css" href="css/ListadoMedicosDisponibles.css">
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
       <li><a href="RealizarReservaServlet?action=mostrarCitasPaciente&idPaciente=<%=paciente.getId()  %>" >Consultar Citas</a></li>
     
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
                    <a href="ConsultarCitaServlet?action=verHorarioDelMedico&id=<%= medico.getId() %>&idPaciente=<%=paciente.getId()%>">VER HORARIO</a>
                </td>
            </tr>
            <% } } %>
        </table>
    

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

</html>
