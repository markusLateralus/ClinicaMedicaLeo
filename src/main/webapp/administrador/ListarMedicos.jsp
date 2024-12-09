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
    <title>Lista de Medicos</title>
    <link rel="stylesheet" type="text/css" href="css/ListarMedicos.css">
        <link rel="stylesheet" type="text/css" href="css/EliminarMedicoA.css">
</head>
<%
Administrador administrador = (Administrador) request.getAttribute("administrador");
int idAdministrador=administrador.getId();
String tipoUsuario = (String) session.getAttribute("tipoUsuario");

%>
<body>
<header>
    <div class="divLogo">
    <a href="AdministradorServlet?action=irIndexAdministrador&id=<%=administrador.getId()%>">
        <img src="imagenes/ClinicaLeo2.png" alt="Logo de Clínica LEO" class="logo">
        </a>
    </div>
    <div class="divH1">
        <h1>Clínica LEO</h1>
    </div>
</header>

<nav>
    <ul class="menu">
    <li>
      <a href="#">Medicos</a>
            <ul class="submenu">
        <li><a href="AdministradorServlet?action=listarMedicos&id=<%=administrador.getId()%>">Consultar Médicos</a></li>
             <li><a href="AdministradorServlet?action=irCrearMedico&id=<%=administrador.getId()%>">Alta médico</a></li>
         </ul>
         </li>
           <li>
            <a href="#">Pacientes</a>
            <ul class="submenu">
                <li><a href="AdministradorServlet?action=listarPacientes&id=<%=administrador.getId()%>">Consultar Pacientes</a></li>
                     <li><a href="AdministradorServlet?action=irCrearPaciente&id=<%=administrador.getId()%>">Alta paciente</a></li>
            </ul>
        </li>
        <!-- Submenú de Datos personales -->
        <li>
            <a href="#">Datos Personales</a>
            <ul class="submenu">
                <li><a href="AdministradorServlet?action=verAdministrador&idAdministrador=<%=administrador.getId()%>">Consultarlos</a></li>
                <li><a href="AdministradorServlet?action=irEditarAdministrador&idAdministrador=<%=administrador.getId()%>">Editarlos</a></li>
            </ul>
        </li>
        <li><a href="LogoutServlet">Cerrar Sesión</a></li>
    </ul>
    
    <div class="divTipoUsuario">
  	  <h4><%= administrador.getNombre() %></h4>
         <h4><%= "Tipo Usuario: " + tipoUsuario %></h4>
      </div>
    
</nav>



<main>
<%
// Obtener el tipo de usuario desde la sesión


// Controlar acceso a la lista de pacientes
if (tipoUsuario == null) {
    // Si el usuario no está logado, redirigir al login
    response.sendRedirect("Login.jsp");
    return;
} 
    
	 %>
    <h2>Lista de Medicos</h2>
    <table border="1">
        <tr>
            <th>Nombre</th>
            <th>Primer Apellido</th>
            <th>Segundo Apellido</th>
            <th>Acciones</th>
        </tr>
        <%
            //PacienteDAO pacienteDAO = new PacienteDAO(DatabaseConnection.getConnection());

            ArrayList<Medico> medicos= (ArrayList<Medico>) request.getAttribute("listaMedicos");
           if(medicos==null){%>
        	   <h3>no hay medicos </h3>
           <%}
           else{
            for (Medico medico : medicos) {
        %>
        <tr>
            <td><%= medico.getNombre() %></td>
    		 <td><%= medico.getApellido1() %></td>
    		  <td><%= medico.getApellido2() %></td>
            <td>
              <a href="AdministradorServlet?action=verMedico&idMedico=<%= medico.getId() %>&idAdministrador=<%=administrador.getId() %>">Ver</a>
                <a href="AdministradorServlet?action=irEditarMedico&idMedico=<%= medico.getId() %>&idAdministrador=<%=administrador.getId()%>">Editar</a>
                   <form action="AdministradorServlet" method="post" class="formularioReserva">
                            <input type="hidden" name="action" value="eliminarMedico">
                            <input type="hidden" name="idMedico" value="<%= medico.getId() %>">
                             <input type="hidden" name="idAdministrador" value="<%= administrador.getId() %>">
                 <a class="disponible reservarBtn" href="#" onclick="abrirModal('<%= medico.getId() %>', '<%= administrador.getId() %>')">Eliminar</a>
                
                        </form>
        
            </td>
        </tr>

      <% }} %>
    </table>
  
        
</main>        
                <!-- Modal de Confirmación eliminación médico -->
<div class="modal" id="confirmModal">
    <div class="modal-content">
        <h3>Confirmar Eliminación</h3>
        <p>¿Estás seguro de que deseas eliminar al médico?</p>
        <button id="continuarReserva" class="confirm-button"  onclick="confirmarEliminacion()">Continuar</button>
        <button id="cancelarReserva" class="cancel-button" onclick="cerrarModal()">Cancelar</button>
    </div>
</div>


  

  <br><br><br><br><br><br><br><br><br><br><br><br><br>><br><br><br><br><br><br><br>
	<footer class="footer">
		<div class="footer-container">
			<div class="footer-left">
								<a href="AdministradorServlet?action=irAvisoLegal&id=<%=administrador.getId()%>">Aviso Legal</a>
				 <a href="AdministradorServlet?action=irPoliticaPrivacidad&id=<%=administrador.getId()%>">Políticas de Privacidad</a>
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
   <script src="./js/EliminarMedicoA.js"></script>
</html>
