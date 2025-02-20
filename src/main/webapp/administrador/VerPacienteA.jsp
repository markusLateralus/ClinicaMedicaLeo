<%@ page contentType="text/html;charset=UTF-8" language="java"
 import="java.util.List"
 import= "modelos.Paciente"
  import= "modelos.Administrador"
 import= "java.util.Date"
  %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ver datos Paciente</title>
<link rel="stylesheet" type="text/css" href="./css/VerPacienteA.css">

</head>
<body>
  
<% 
// Obtener la acción del parámetro

String tipoUsuario = (String) session.getAttribute("tipoUsuario");
Administrador administrador = (Administrador) request.getAttribute("administrador");
	Paciente paciente = (Paciente) request.getAttribute("paciente");
%>

  
</head>
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
    <div class="form-container">
        <form class="formularioInsertarPaciente">
            <fieldset>
                <legend>DATOS USUARIO</legend>
                <label for="username">Nombre de usuario:</label>
                <input type="text" id="username" name="username" value="<%= paciente.getUsername() %>" readonly class="readonly-field">
                
                <label for="password">Contraseña:</label>
                <input type="text" id="password" name="password" value="<%= paciente.getPassword() %>" readonly class="readonly-field">
            </fieldset>
        </form>
    </div>
    
    <div class="form-separator"></div>

    <div class="form-container">
        <form class="formularioInsertarPaciente">
            <fieldset>
                <legend>DATOS DEL PACIENTE</legend>
                <label for="nombre">Nombre:</label>
                <input type="text" id="nombre" name="nombre" value="<%= paciente.getNombre() %>" readonly class="readonly-field">
                
                <label for="apellido1">Primer Apellido:</label>
                <input type="text" id="apellido1" name="apellido1" value="<%= paciente.getApellido1() %>" readonly class="readonly-field">
                
                <label for="apellido2">Segundo Apellido:</label>
                <input type="text" id="apellido2" name="apellido2" value="<%= paciente.getApellido2() %>" readonly class="readonly-field">
                
                <label for="dni">DNI:</label>
                <input type="text" id="dni" name="dni" value="<%= paciente.getDni() %>" readonly class="readonly-field">
                
                <label for="email">Correo Electrónico:</label>
                <input type="email" id="email" name="email" value="<%= paciente.getEmail() %>" readonly class="readonly-field">
                
                <label for="telefono">Teléfono:</label>
                <input type="tel" id="telefono" name="telefono" value="<%= paciente.getTelefono() %>" readonly class="readonly-field">
                
                <label for="fechaNacimiento">Fecha de Nacimiento:</label>
                <input type="date" id="fechaNacimiento" name="fechaNacimiento" value="<%= new java.text.SimpleDateFormat("yyyy-MM-dd").format(paciente.getFechaNacimiento()) %>" readonly class="readonly-field">
            </fieldset>
        </form>
    </div>

           <form  action="AdministradorServlet" method="get" >
                    <div class="form-group">
        <input type="hidden" name="action" value="listarPacientes">
            </div>
              <div class="form-group">
         <input type="hidden" name="id"value="<%= administrador.getId() %>" />
         </div>
            <div class="divRegresar">
                	<button  type="submit" id="enviar" class="divRegresar">volver</button>
                	</div>
                </form>
</main>

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

</html>