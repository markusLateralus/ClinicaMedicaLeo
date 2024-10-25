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
    <title>Indiex del administrador</title>
    <link rel="stylesheet" type="text/css" href="css/IndexAdministrador.css">
    <style>
    .disponible { background-color: green; color: white; font-size:20px;}
    .ocupado { background-color: red; color: white; font-size:20px;}
</style>
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
    <li>
      <a href="#">Medicos</a>
            <ul class="submenu">
        <li><a href="MedicoServlet?action=listarMedicos">Lista Médicos</a></li>
         <li><a href="MedicoServlet?action=irCrearMedico">Alta médico</a></li>
         </ul>
         </li>
           <li>
            <a href="#">Pacientes</a>
            <ul class="submenu">
                <li><a href="PacienteServlet?action=listarPacientes">Lista Pacientes</a></li>
                <li><a href="PacienteServlet?action=irCrearPaciente">Alta paciente</a></li>
            </ul>
        </li>
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

<%
// Obtener el tipo de usuario desde la sesión
String tipoUsuario = (String) session.getAttribute("tipoUsuario");

int id=0;
String username="";
String password="";
String dni="";
String nombre = "";
String apellido1 = "";
String apellido2 = "";
String email="";
String telefono="";
Administrador administrador = (Administrador) request.getAttribute("administrador");
if (administrador != null) {
	id=administrador.getId();
	username=administrador.getUsername();
	password=administrador.getPassword();
	dni=administrador.getDni();
    nombre = administrador.getNombre();
    apellido1 = administrador.getApellido1();
    apellido2 = administrador.getApellido2();
    email=administrador.getEmail();
    telefono=administrador.getTelefono();
}

// Controlar acceso a la lista de pacientes
if (tipoUsuario == null) {
    // Si el usuario no está logado, redirigir al login
    response.sendRedirect("Login.jsp");
    return;
}
    // Si es Médico o Administrador, puede ver la lista
	 %>
	 	 <h4>	tipo usuario  <%= tipoUsuario %></h4>
	 <h4>	BIENVENIDO  <%= nombre %></h4>
<br><br><br><br><br>
<main>	 

<%if ( tipoUsuario.equals("ADMINISTRADOR")){ %>	 
	 

<h2></h2>

<%} %>
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
