<%@ page contentType="text/html;charset=UTF-8" language="java"
 import="java.util.List"
 import= "modelos.Administrador"
 import= "java.util.Date"
  %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ver datos personales del administrador</title>
<link rel="stylesheet" type="text/css" href="./css/VerAdministrador.css">
</head>
<% 
// Obtener la acción del parámetro
Administrador administrador = (Administrador) request.getAttribute("administrador");
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
         <li><a href="AdministradorServlet?action=irCrearMedico">Alta médico</a></li>
         </ul>
         </li>
           <li>
            <a href="#">Pacientes</a>
            <ul class="submenu">
                <li><a href="AdministradorServlet?action=listarPacientes&id=<%=administrador.getId()%>">Consultar Pacientes</a></li>
                <li><a href="AdministradorServlet?action=irCrearPaciente">Alta paciente</a></li>
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

 <h2>Desde aquí puedes ver tus datos personales</h2>
   <main>

<div class="form-container">
        <form class="formularioInsertarPaciente">
            <fieldset>
                <legend>DATOS USUARIO</legend>
                <label for="username">Nombre de usuario:</label>
                <input type="text" id="username" name="username" value="<%= administrador.getUsername() %>" readonly class="readonly-field">
                
                <label for="password">Contraseña:</label>
                <input type="text" id="password" name="password" value="<%= administrador.getPassword() %>" readonly class="readonly-field">
            </fieldset>
        </form>
    </div>
 
 
     <div class="form-separator"></div>
 
     <div class="form-container">
        <form class="formularioInsertarPaciente">
            <fieldset>
                <legend>DATOS DEL ADMINISTRADOR</legend>
                <label for="nombre">Nombre:</label>
                <input type="text" id="nombre" name="nombre" value="<%= administrador.getNombre() %>" readonly class="readonly-field">
                
                <label for="apellido1">Primer Apellido:</label>
                <input type="text" id="apellido1" name="apellido1" value="<%= administrador.getApellido1() %>" readonly class="readonly-field">
                
                <label for="apellido2">Segundo Apellido:</label>
                <input type="text" id="apellido2" name="apellido2" value="<%= administrador.getApellido2() %>" readonly class="readonly-field">
                
                <label for="dni">DNI:</label>
                <input type="text" id="dni" name="dni" value="<%= administrador.getDni() %>" readonly class="readonly-field">
                
                <label for="email">Correo Electrónico:</label>
                <input type="email" id="email" name="email" value="<%= administrador.getEmail() %>" readonly class="readonly-field">
                
                <label for="telefono">Teléfono:</label>
                <input type="tel" id="telefono" name="telefono" value="<%= administrador.getTelefono() %>" readonly class="readonly-field">
                
                <label for="fechaNacimiento">Fecha de Nacimiento:</label>
                <input type="date" id="fechaNacimiento" name="fechaNacimiento" value="<%= new java.text.SimpleDateFormat("yyyy-MM-dd").format(administrador.getFechaNacimiento()) %>" readonly class="readonly-field">
            </fieldset>
        </form>
    </div>
  


            <div class="divRegresar">
        	<a id="botonRegresar" >Regresar</a>
    </div>
        

   

</main>


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
  <script src="./administrador/ValidacionFormularioEditarAdministrador.js"></script>
   <script src="./administrador/Redirecciones.js"></script>
</html>