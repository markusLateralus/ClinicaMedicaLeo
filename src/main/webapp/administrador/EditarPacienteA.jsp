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
<title>Editar datos paciente</title>
<link rel="stylesheet" type="text/css" href="./css/EditarPacienteA.css">
</head>
<% 
// Obtener la acción del parámetro
Administrador administrador = (Administrador) request.getAttribute("administrador");
	Paciente paciente=(Paciente)request.getAttribute("paciente");
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

<br><br>
>
 <h2>Desde aquí puedes actualizar los datos personales del paciente</h2>
   <main>
<form id="formularioEditarPaciente" action="AdministradorServlet" method="post">
   <fieldset>
                <legend>DATOS DEL PACIENTE</legend>
            <div class="form-group">
        <input type="hidden" name="action" value="actualizarPacienteA">
            </div>
              <div class="form-group">
         <input type="hidden" name="idAdministrador"value="<%= administrador.getId() %>" />
         </div>
        
              <div class="form-group">
         <input type="hidden" name="idPaciente"value="<%= paciente.getId() %>" />
         </div>
         
           <div class="form-group">
        <label for="username">Nombre de usuario:</label>
        <input type="text" id="username" name="username" minlength="4" maxlength="10" placeholder="mi userName"  pattern="^(?=.*[aeiouáéíóúàèìòùäëïöü]).{2,10}$" 
          title="Introduce un nombre de usuario válida, por ejemplo: mika" value="<%= paciente.getUsername() %>" required><br>
           <span id="errorUsername"></span>
</div>
  <div class="form-group">
        <label for="password">Contraseña:</label>
        <input type="text" id="password" name="password" minlength="4" maxlength="20" placeholder="mi contraseña"  pattern="(?=.*[a-zA-Z0-9!@#\$%\^&\*\(\)_\+\-=\[\] \{\};':\,.<>\/\?¡¿ñÑáéíóúÁÉÍÓÚüÜ]).{4,20}" 
          title="Introduce una contraseña válida, por ejemplo: m12Rs" value="<%= paciente.getPassword() %>" required><br>
         <span id="errorPassword"></span>
</div>
  <div class="form-group">
        <label for="dni">DNI:</label>
        <input type="text" id="dni" name="dni" minlength="9" maxlength="9" placeholder="mi dni"  pattern="^\d{8}[A-Z]$" 
          title="Introduce un dni de usuario válida, por ejemplo: 44778899Y" value="<%= paciente.getDni() %>"  required><br>
         <span id="errorDni"></span>
</div>
  <div class="form-group">
        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" name="nombre" minlength="4" maxlength="25"  placeholder="mi nombre"  pattern="^(?=.*[aeiouáéíóúàèìòùäëïöü]).{2,10}$" 
          title="Introduce un nombre válida, por ejemplo: Manuel" value="<%= paciente.getNombre() %>" required><br>
         <span id="errorNombre"></span>
</div>
  <div class="form-group">
        <label for="apellido1">Primer Apellido:</label>
        <input type="text" id="apellido1" name="apellido1" minlength="4" maxlength="20"  placeholder="mi primer apellido"  pattern="^(?=.*[aeiouáéíóúàèìòùäëïöü]).{2,10}$" 
          title="Introduce un primer apellido válida, por ejemplo: Miranda" value="<%= paciente.getApellido1() %>" required><br>
             <span id="errorApellido1"></span>
</div>
  <div class="form-group">
        <label for="apellido2">Segundo Apellido:</label>
        <input type="text" id="apellido2" name="apellido2" minlength="4" maxlength="20" placeholder="segundo apellido"  pattern="^(?=.*[aeiouáéíóúàèìòùäëïöü]).{2,10}$" 
          title="Introduce un segundo apellido válida, por ejemplo: Quijote"  value="<%= paciente.getApellido2() %>" required><br>
             <span id="errorApellido2"></span>
</div>
  <div class="form-group">
        <label for="email">Correo Electrónico:</label>
        <input type="email" id="email" name="email"  minlength="5" placeholder="manolete@gmail.com" 
        pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}"  title="Introduce un email válida, por ejemplo: lucas@gmail.com"  value="<%= paciente.getEmail() %>"  required><br>
        <span id="errorEmail"></span>
</div>
  <div class="form-group">
        <label for="telefono">Teléfono:</label>
        <input type="tel" id="telefono" name="telefono" maxlength="9" placeholder="666666666"   pattern="^[679]\d{8}$" 
          title="Introduce un teléfono válida, por ejemplo: 678678678" value="<%= paciente.getTelefono() %>"  required><br>
        <span id="errorTelefono"></span>
</div>
  <div class="form-group">
        <label for="fechaNacimiento">Fecha de Nacimiento:</label>
        <input type="date" id="fechaNacimiento" name="fechaNacimiento"  min="1914-01-01" max="2008-01-01"  
          title="Introduce una fecha de nacimiento  válida, por ejemplo: 01/01/2000" value="<%=paciente.getFechaNacimiento() %>"  required><br>
        <span id="errorFechaNacimiento"></span>
                <span id="errorMenorEdad"></span>
  </div>

    
   <div class="form-group">
    <button  type="submit" id="enviar" class="disponible reservarBtn">actualizar</button>
        </div>
        </fieldset>
    </form>



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
                
                   <!-- Modal de Confirmación -->
<div class="modal" id="confirmModal">
    <div class="modal-content">
        <h3>Editar datos persolanes</h3>
        <p>¿Estás seguro de que deseas realizar los cambios del paciente <%=paciente.getNombre() + " " + paciente.getApellido1() %>?</p>
        <button id="continuarReserva" class="confirm-button">Continuar</button>
        <button id="cancelarReserva" class="cancel-button">Cancelar</button>
    </div>
</div>
        

   

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
  <script src="./js/ValidacionFormularioEditarPacienteA.js"></script>

</html>