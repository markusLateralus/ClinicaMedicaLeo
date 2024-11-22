<%@ page contentType="text/html;charset=UTF-8" language="java"
 import="java.util.List"
 import= "modelos.Medico"
  import= "java.util.Date"
 
  %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Editar datos médico</title>
<link rel="stylesheet" type="text/css" href="./css/EditarMedico.css">
</head>
<% 
// Obtener la acción del parámetro
String tipoUsuario = (String) session.getAttribute("tipoUsuario");
	Medico medico = (Medico) request.getAttribute("medico");

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

<br><br>
  <h2>Edición datos personales de D. <%=medico.getNombre() %></h2>
   <main>
<form id="formularioEditarMedico" action="MedicoServlet" method="post">
   <fieldset>
                <legend>DATOS DEL MÉDICO</legend>
            <div class="form-group">
        <input type="hidden" name="action" value="actualizarMedico">
          </div>
              <div class="form-group">
         <input type="hidden" name="id"value="<%= medico.getId() %>" />
         </div>
         
           <div class="form-group">
        <label for="username">Nombre de usuario:</label>
        <input type="text" id="username" name="username" value="<%= medico.getUsername() %>" required><br>
           <span id="errorUsername"></span>
</div>
  <div class="form-group">
        <label for="password">Contraseña:</label>
        <input type="password" id="password" name="password" value="<%= medico.getPassword() %>" required><br>
         <span id="errorPassword"></span>
</div>
  <div class="form-group">
        <label for="dni">DNI:</label>
        <input type="text" id="dni" name="dni" value="<%= medico.getDni() %>"  required><br>
         <span id="errorDni"></span>
</div>
  <div class="form-group">
        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" name="nombre" value="<%= medico.getNombre() %>" required><br>
         <span id="errorNombre"></span>
</div>
  <div class="form-group">
        <label for="apellido1">Primer Apellido:</label>
        <input type="text" id="apellido1" name="apellido1"  value="<%= medico.getApellido1() %>" required><br>
             <span id="errorApellido1"></span>
</div>
  <div class="form-group">
        <label for="apellido2">Segundo Apellido:</label>
        <input type="text" id="apellido2" name="apellido2"   value="<%= medico.getApellido2() %>" required><br>
             <span id="errorApellido2"></span>
</div>
  <div class="form-group">
        <label for="email">Correo Electrónico:</label>
        <input type="email" id="email" name="email"  value="<%= medico.getEmail() %>"  required><br>
        <span id="errorEmail"></span>
</div>
  <div class="form-group">
        <label for="telefono">Teléfono:</label>
        <input type="tel" id="telefono" name="telefono" value="<%= medico.getTelefono() %>"  required><br>
        <span id="errorTelefono"></span>
</div>
  <div class="form-group">
        <label for="fechaNacimiento">Fecha de Nacimiento:</label>
        <input type="date" id="fechaNacimiento" name="fechaNacimiento" value="<%=medico.getFechaNacimiento() %>"  required><br>
        <span id="errorFechaNacimiento"></span>
                <span id="errorMenorEdad"></span>
  </div>
             <div class="form-group">
                <label for="especialidad">Especialidad</label>
        <input type="text" id="especialidad" name="especialidad"   value="<%= medico.getEspecialidad() %>" required readonly><br>
			</div>
    
  <div class="form-group">
        <input type="submit" id="enviar" value="actualizar datos">
        </div>
        </fieldset>
    </form>


            <div class="divRegresar">
          <% if(medico==null){ %>
        <a href="MedicoServlet?action=irIndexMedico&id=<%= medico.getId() %>">Regresar</a>
        <%}else{%>
        	<a id="botonRegresar" >Regresar</a>
       <%  }%>
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
  <script src="./medico/ValidacionFormularioEditarMedico.js"></script>
   <script src="./administrador/Redirecciones.js"></script>
</html>