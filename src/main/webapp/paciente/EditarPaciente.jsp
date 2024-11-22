<%@ page contentType="text/html;charset=UTF-8" language="java"
 import="java.util.List"
 import= "modelos.Paciente"
  import= "java.util.Date"
 
  %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Editar Datos del paciente</title>
<link rel="stylesheet" type="text/css" href="./css/EditarPaciente.css">
</head>
<% 
// Obtener la acción del parámetro
String tipoUsuario = (String) session.getAttribute("tipoUsuario");
  Paciente  paciente = (Paciente) request.getAttribute("paciente");

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
 <h2>Desde aquí puedes actualizar tus datos personales</h2>
   <main>


    <div class="form-container">
        <form id="formularioEditarPaciente" action="PacienteServlet" method="post">
            <fieldset>
                <legend>DATOS DEL PACIENTE</legend>
         <div class="form-group">
        <input type="hidden" name="action" value="actualizarPaciente">
        </div>
          <div class="form-group">
         <input type="hidden" name="id"value="<%= paciente.getId() %>" />
         </div>
         
           <div class="form-group">
        <label for="username">Nombre de usuario:</label>
        <input type="text" id="username" name="username" value="<%= paciente.getUsername() %>" required><br>
           <span id="errorUsername"></span>
</div>
  <div class="form-group">
        <label for="password">Contraseña:</label>
        <input type="password" id="password" name="password" value="<%= paciente.getPassword() %>" required><br>
         <span id="errorPassword"></span>
</div>
  <div class="form-group">
        <label for="dni">DNI:</label>
        <input type="text" id="dni" name="dni" value="<%= paciente.getDni() %>"  required><br>
         <span id="errorDni"></span>
</div>
  <div class="form-group">
        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" name="nombre" value="<%= paciente.getNombre() %>" required><br>
         <span id="errorNombre"></span>
</div>
  <div class="form-group">
        <label for="apellido1">Primer Apellido:</label>
        <input type="text" id="apellido1" name="apellido1"  value="<%= paciente.getApellido1() %>" required><br>
             <span id="errorApellido1"></span>
</div>
  <div class="form-group">
        <label for="apellido2">Segundo Apellido:</label>
        <input type="text" id="apellido2" name="apellido2"   value="<%= paciente.getApellido2() %>" required><br>
             <span id="errorApellido2"></span>
</div>
  <div class="form-group">
        <label for="email">Correo Electrónico:</label>
        <input type="email" id="email" name="email"  value="<%= paciente.getEmail() %>"  required><br>
        <span id="errorEmail"></span>
</div>
  <div class="form-group">
        <label for="telefono">Teléfono:</label>
        <input type="tel" id="telefono" name="telefono" value="<%= paciente.getTelefono() %>"  required><br>
        <span id="errorTelefono"></span>
</div>
  <div class="form-group">
        <label for="fechaNacimiento">Fecha de Nacimiento:</label>
        <input type="date" id="fechaNacimiento" name="fechaNacimiento" value="<%=paciente.getFechaNacimiento() %>"  required><br>
        <span id="errorFechaNacimiento"></span>
                <span id="errorMenorEdad"></span>
</div>
  <div class="form-group">
        <input type="submit" id="enviar" value="actualizar datos">
        </div>
        
            <div class="divRegresar">
        <a href="ConsultarCitaServlet?action=irSolicitarCita&id=<%= paciente.getId() %>">Regresar</a>
    </div>
        </fieldset>
    </form>

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
   <script src="./paciente/ValidacionFormularioEditarPaciente.js"></script>
</html>