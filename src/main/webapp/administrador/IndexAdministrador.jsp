<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="modelos.Medico" %>
<%@ page import="modelos.Horario" %>
<%@ page import="dao.MedicoDAO" %>
<%@ page import="modelos.Administrador" %>
<%@ page import="dao.AdministradorDAO" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>Index del administrador</title>
    <link rel="stylesheet" type="text/css" href="css/IndexAdministrador.css">

</head>
<%
// Obtener el tipo de usuario desde la sesión
String tipoUsuario = (String) session.getAttribute("tipoUsuario");
Administrador administrador = (Administrador) request.getAttribute("administrador");
// Controlar acceso a la lista de pacientes
if (tipoUsuario == null) {
    // Si el usuario no está logado, redirigir al login
    response.sendRedirect("Login.jsp");
    return;
}
    // Si es Médico o Administrador, puede ver la lista
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


<br><br>

<main>
<div class="contenedorPrincipal">
<div class="contenedorSuperior">
<p>Bienvenido <%=administrador.getNombre() %></p><br>
<p>Estás en la página principal de la plataforma Clínica LEO</p>
<p>Como <%=tipoUsuario %> podrás tener acceso a todas las funciones principales.</p>
<p>Podrás realizar consultas sobre los pacientes y médicos dados de alta </p>
<p>Podrás editar sus datos personales y darles de baja en la plataforma</p>
<p>Podrás dar de alta nuevos pacientes y médicos en la plataforma</p>
</div>
<div class="contenedorInferior">
<p>Cualquier problema técnico puedes ponerte en contacto con nosotros desde: </p>
<p>Teléfono: 924-666666  &  Email: soporteTecnico@gmail.com</p>

</div>
</div>
	 


</main>
<br><br><br><br>
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
