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
                       <li><a href="RealizarReservaServlet?action=mostrarCitasMedico&idMedico=<%=medico.getId()  %>" >Consultar Citas</a></li>
        
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
            <input type="hidden" name="id" value="<%= medico.getId() %>">
        </div>
         
        <div class="form-group">
            <label for="username">Nombre de usuario:</label>
         <input type="text" id="username" name="username"  minlength="4" maxlength="10" placeholder="mi userName"  pattern="^(?=.*[aeiouáéíóúàèìòùäëïöü]).{2,10}$" 
          title="Introduce un nombre de usuario válida, por ejemplo: mika" value="<%= medico.getUsername() %>" required autocomplete="username"><br>
            <span id="errorUsername"></span>
        </div>

        

  
<div class="form-group">
            <label for="password">Contraseña:</label>
            <input type="text" id="password" name="password" minlength="4" maxlength="20" placeholder="mi contraseña"  pattern="(?=.*[a-zA-Z0-9!@#\$%\^&\*\(\)_\+\-=\[\] \{\};':\,.<>\/\?¡¿ñÑáéíóúÁÉÍÓÚüÜ]).{4,20}" 
          title="Introduce una contraseña válida, por ejemplo: m12Rs" value="<%= medico.getPassword() %>" required autocomplete="new-password"><br>
            <span id="errorPassword"></span>
        </div>

        <div class="form-group">
            <label for="dni">DNI:</label>
            <input type="text" id="dni" name="dni" minlength="9" maxlength="9" placeholder="mi dni"  pattern="^\d{8}[A-Z]$" 
          title="Introduce un dni de usuario válida, por ejemplo: 44778899Y" value="<%= medico.getDni() %>" required autocomplete="off"><br>
            <span id="errorDni"></span>
        </div>

        <div class="form-group">
            <label for="nombre">Nombre:</label>
            <input type="text" id="nombre" name="nombre" value="<%= medico.getNombre() %>" required autocomplete="given-name"><br>
            <span id="errorNombre"></span>
        </div>

        <div class="form-group">
            <label for="apellido1">Primer Apellido:</label>
            <input type="text" id="apellido1" name="apellido1"  minlength="4" maxlength="25"  placeholder="mi primer apellido"  pattern="^(?=.*[aeiouáéíóúàèìòùäëïöü]).{2,10}$" 
          title="Introduce un nombre válida, por ejemplo: Manuel"  value="<%= medico.getApellido1() %>" required autocomplete="family-name"><br>
            <span id="errorApellido1"></span>
        </div>

        <div class="form-group">
            <label for="apellido2">Segundo Apellido:</label>
            <input type="text" id="apellido2" name="apellido2"  minlength="4" maxlength="20"  placeholder="mi segundo apellido"  pattern="^(?=.*[aeiouáéíóúàèìòùäëïöü]).{2,10}$" 
          title="Introduce un segundo apellido válida, por ejemplo: Quijote" value="<%= medico.getApellido2() %>" required autocomplete="family-name"><br>
            <span id="errorApellido2"></span>
        
        </div>


        <div class="form-group">
            <label for="email">Correo Electrónico:</label>
            <input type="email" id="email" name="email" minlength="5" placeholder="manolete@gmail.com" pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}"  
            title="Introduce un email válida, por ejemplo: lucas@gmail.com"  value="<%= medico.getEmail() %>" required autocomplete="email"><br>
            <span id="errorEmail"></span>
        </div>

        <div class="form-group">
            <label for="telefono">Teléfono:</label>
            <input type="tel" id="telefono" name="telefono" maxlength="9" placeholder="666666666"   pattern="^[679]\d{8}$" 
          title="Introduce un teléfono válida, por ejemplo: 678678678"  value="<%= medico.getTelefono() %>" required autocomplete="tel"><br>
            <span id="errorTelefono"></span>
        </div>

        <div class="form-group">
            <label for="fechaNacimiento">Fecha de Nacimiento:</label>
            <input type="date" id="fechaNacimiento" name="fechaNacimiento" min="1914-01-01" max="2008-01-01"  
          title="Introduce una fecha de nacimiento válida válida, por ejemplo: 01/01/2000" value="<%= medico.getFechaNacimiento() %>" required autocomplete="bday"><br>
            <span id="errorFechaNacimiento"></span>
            <span id="errorMenorEdad"></span>
        </div>

        <div class="form-group">
            <label for="especialidad">Especialidad</label>
            <input type="text" id="especialidad" name="especialidad" value="<%= medico.getEspecialidad() %>" required readonly autocomplete="off"><br>
        </div>

        <div class="form-group">
             <button  type="submit" id="enviar" class="disponible reservarBtn">actualizar</button>
        </div>
    </fieldset>
</form>

        <form  action="MedicoServlet" method="get" >
                    <div class="form-group">
        <input type="hidden" name="action" value="irIndexMedico">
            </div>
              <div class="form-group">
         <input type="hidden" name="id"value="<%= medico.getId() %>" />
         </div>
            <div class="divRegresar">
                	<button  type="submit" id="regresar" class="divRegresar">volver</button>
                	</div>
                </form>


    <!-- Modal de Confirmación -->
<div class="modal" id="confirmModal">
    <div class="modal-content">
        <h3>Editar datos persolanes</h3>
        <p>¿Estás seguro de que deseas realizar los cambios?</p>
        <button id="continuarReserva" class="confirm-button">Continuar</button>
        <button id="cancelarReserva" class="cancel-button">Cancelar</button>
    </div>
</div>
        

   

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

    <script src="./js/ValidacionFormularioEditarMedico.js"></script>

</html>