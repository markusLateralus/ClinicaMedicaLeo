<%@ page contentType="text/html;charset=UTF-8" language="java"
 import="java.util.List"
 import= "modelos.Paciente"
 import= "java.util.Date"
  %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ver datos Paciente</title>
<link rel="stylesheet" type="text/css" href="./css/VerPaciente.css">

</head>
<body>
  
<% 
String tipoUsuario = (String) session.getAttribute("tipoUsuario");
    Paciente paciente = (Paciente) request.getAttribute("paciente");
%>

  
</head>
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
<br><br>

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

          <form  action="PacienteServlet" method="get" >
                    <div class="form-group">
        <input type="hidden" name="action" value="irIndexPaciente">
            </div>
              <div class="form-group">
         <input type="hidden" name="id"value="<%= paciente.getId() %>" />
         </div>
            <div class="divRegresar">
                	<button  type="submit" id="enviar" class="divRegresar">volver</button>
                	</div>
                </form>
</main>

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