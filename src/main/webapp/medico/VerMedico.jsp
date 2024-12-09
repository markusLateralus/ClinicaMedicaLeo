<%@ page contentType="text/html;charset=UTF-8" language="java"
 import="java.util.List"
 import= "modelos.Medico"
 import= "java.util.Date"
  %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ver datos del Médico</title>
<link rel="stylesheet" type="text/css" href="./css/VerMedico.css">

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
                       <li><a href="RealizarReservaServlet?action=mostrarCitasMedico&idMedico=<%=medico.getId()  %>" >Consultar Citas</a></li>
      
        <li>
            <a href="#">Datos Personales</a>
            <ul class="submenu">
                  <li><a href="MedicoServlet?action=verMedico&id=<%= medico.getId() %>">Consultarlos</a></li>
                <li><a href="MedicoServlet?action=irEditarMedico&id=<%= medico.getId() %>">Editarlos</a></li>
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

 <main>
 
     <div class="form-container">
        <form class="formularioInsertarPaciente">
            <fieldset>
                <legend>DATOS USUARIO</legend>
                <label for="username">Nombre de usuario:</label>
                <input type="text" id="username" name="username" value="<%= medico.getUsername() %>" readonly class="readonly-field">
                
                <label for="password">Contraseña:</label>
                <input type="text" id="password" name="password" value="<%= medico.getPassword() %>" readonly class="readonly-field">
            </fieldset>
        </form>
    </div>
 
 
     <div class="form-separator"></div>
 
     <div class="form-container">
        <form class="formularioInsertarPaciente">
            <fieldset>
                <legend>DATOS DEL MEDICO</legend>
                <label for="nombre">Nombre:</label>
                <input type="text" id="nombre" name="nombre" value="<%= medico.getNombre() %>" readonly class="readonly-field">
                
                <label for="apellido1">Primer Apellido:</label>
                <input type="text" id="apellido1" name="apellido1" value="<%= medico.getApellido1() %>" readonly class="readonly-field">
                
                <label for="apellido2">Segundo Apellido:</label>
                <input type="text" id="apellido2" name="apellido2" value="<%= medico.getApellido2() %>" readonly class="readonly-field">
                
                <label for="dni">DNI:</label>
                <input type="text" id="dni" name="dni" value="<%= medico.getDni() %>" readonly class="readonly-field">
                
                <label for="email">Correo Electrónico:</label>
                <input type="email" id="email" name="email" value="<%= medico.getEmail() %>" readonly class="readonly-field">
                
                <label for="telefono">Teléfono:</label>
                <input type="tel" id="telefono" name="telefono" value="<%= medico.getTelefono() %>" readonly class="readonly-field">
                
                <label for="fechaNacimiento">Fecha de Nacimiento:</label>
                <input type="date" id="fechaNacimiento" name="fechaNacimiento" value="<%= new java.text.SimpleDateFormat("yyyy-MM-dd").format(medico.getFechaNacimiento()) %>" readonly class="readonly-field">
                         <div class="form-group">
                <label for="especialidad">Especialidad</label>
        <input type="text" id="especialidad" name="especialidad"   value="<%= medico.getEspecialidad() %>" readonly class="readonly-field"><br>
			</div>
           
            </fieldset>
        </form>
    </div>

          <form  action="MedicoServlet" method="get" >
                    <div class="form-group">
        <input type="hidden" name="action" value="irIndexMedico">
            </div>
              <div class="form-group">
         <input type="hidden" name="id"value="<%= medico.getId() %>" />
         </div>
            <div class="divRegresar">
                	<button  type="submit" id="enviar" class="divRegresar">volver</button>
                	</div>
                </form>
        
 
 
 </main>
 
 
 


	<footer class="footer">
		<div class="footer-container">
			<div class="footer-left">
				<a href="MedicoServlet?action=irAvisoLegal&id=<%=medico.getId()%>">Aviso Legal</a>
				 <a href="MedicoServlet?action=irPoliticaPrivacidad&id=<%=medico.getId()%>">Políticas de Privacidad</a>
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