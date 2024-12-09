<%@ page contentType="text/html;charset=UTF-8" language="java"
 import="java.util.List"
 import= "modelos.Paciente" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registrar paciente</title>

    <link rel="stylesheet" type="text/css" href="./css/InsertarPaciente.css"> 

 
</head>
<body>
<header>
<div class="divLogo">
  
    <a href="LoginServlet">
    <img src="imagenes/ClinicaLeo2.png" alt="Logo de Clínica LEO" class="logo">
    </a>
</div>
<div class="divH1">
    <h1>Clínica LEO</h1>
</div>
</header>


<br><br><br><br><br>
<main>
    <h2>Registrar nuevo Paciente</h2>
<%String action = request.getParameter("action");

if ("irCrearPaciente".equals(action) || action==null) {
   

 %>
<form id="formularioInsertarPaciente" action="PacienteServlet" method="post">
        <!-- Campo oculto para definir la acción en el Servlet -->
            <div class="form-group">
        <input type="hidden" name="action" value="crearPaciente">
         </div>
             <div class="form-group">
        <label for="username">Nombre de usuario:</label>
        <input type="text" id="username" name="username" minlength="4" maxlength="10" placeholder="mi userName"  pattern="^(?=.*[aeiouáéíóúàèìòùäëïöü]).{4,10}$" 
          title="Introduce un nombre de usuario válida, por ejemplo: mika" required><br>
        <span id="errorUsername"></span>
 </div>    <div class="form-group">
 
        <label for="password">Contraseña:</label>
        <input type="password" id="password" name="password" minlength="4" maxlength="20" placeholder="mi contraseña"  pattern="(?=.*[a-zA-Z0-9!@#\$%\^&\*\(\)_\+\-=\[\] \{\};':\,.<>\/\?¡¿ñÑáéíóúÁÉÍÓÚüÜ]).{4,20}" 
          title="Introduce una contraseña válida, por ejemplo: m12Rs" required><br>
                <span id="errorPassword"></span>
 </div>
     <div class="form-group">
        <label for="dni">DNI:</label>
        <input type="text" id="dni" name="dni" minlength="9" maxlength="9" placeholder="mi dni"  pattern="^\d{8}[A-Z]$" 
          title="Introduce un dni de usuario válida, por ejemplo: 44778899Y" required><br>
	        <span id="errorDni"></span>
 </div>
 
     <div class="form-group">
        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" name="nombre"  minlength="4" maxlength="25"  placeholder="mi nombre"  pattern="^(?=.*[aeiouáéíóúàèìòùäëïöü]).{2,10}$" 
          title="Introduce un nombre válida, por ejemplo: Manuel" required><br>
 	        <span id="errorNombre"></span>
 </div>
     <div class="form-group">
        <label for="apellido1">Primer Apellido:</label>
        <input type="text" id="apellido1" name="apellido1" minlength="4" maxlength="20"  placeholder="mi primer apellido"  pattern="^(?=.*[aeiouáéíóúàèìòùäëïöü]).{2,10}$" 
          title="Introduce un primer apellido válida, por ejemplo: Miranda" required><br>
                <span id="errorApellido1"></span>
 </div>
     <div class="form-group">
        <label for="apellido2">Segundo Apellido:</label>
        <input type="text" id="apellido2" name="apellido2" minlength="4" maxlength="20" placeholder="segundo apellido"  pattern="^(?=.*[aeiouáéíóúàèìòùäëïöü]).{2,10}$" 
          title="Introduce un segundo apellido válida, por ejemplo: Quijote" required><br>
         <span id="errorApellido2"></span>
 </div>
     <div class="form-group">
        <label for="email">Correo Electrónico:</label>
        <input type="email" id="email" name="email" minlength="5" placeholder="manolete@gmail.com" pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}"  title="Introduce un email válida, por ejemplo: lucas@gmail.com" required><br>
 		        <span id="errorEmail"></span>
 </div>
     <div class="form-group">
        <label for="telefono">Teléfono:</label>
        <input type="tel" id="telefono" name="telefono"  maxlength="9" placeholder="666666666"   pattern="^[679]\d{8}$" 
          title="Introduce un teléfono válida, por ejemplo: 678678678" required><br>
 	        <span id="errorTelefono"></span>
 </div>
     <div class="form-group">
        <label for="fechaNacimiento">Fecha de Nacimiento:</label>
        <input type="date" id="fechaNacimiento" name="fechaNacimiento" placeholder="dd/mm/aaaa"  min="1914-01-01" max="2008-01-01"  
          title="Introduce una fecha de nacimiento válida válida, por ejemplo: 01/01/2000" required><br>
                <span id="errorFechaNacimiento"></span>
                <span id="errorMenorEdad"></span>
 </div>
     <div class="form-group">
        <input type="submit" value="Registrar Paciente" id="enviar">
        </div>
        <div class="form-group">
         <input type="reset" value="limpiar" id="limpiar">
         </div>
    </form>

    <!-- Enlace para regresar a la lista de pacientes -->
    <form  action="PacienteServlet" method="get" class="formulario2">
                 <div class="form-group link-container">
            <a href="LoginServlet" class="login-link">Ir a login</a>
        </div>
</form>
  </main>
<%} %>




<footer class="footer">
    <div class="footer-container">
<div class="footer-left">
            <a href="Contacto.jsp">Contacto</a>
            <a href="#aviso-legal">Aviso Legal</a>       
<a href="#politicas-privacidad">Políticas de Privacidad</a>  
</div>
        <div class="footer-right">
   <a href="https://www.facebook.com" target="_blank"><img src="imagenes/facebook.png" alt="Facebook"></a>
        <a href="https://www.instagram.com" target="_blank"><img src="imagenes/instagram.png" alt="Instagram"></a>
        <a href="https://www.youtube.com" target="_blank"><img src="imagenes/youtube.png" alt="YouTube"></a>
</div>
    </div>

    <p class="footer-author">Autor: Marcos Antonio Arrornes Alcañiz &copy; 2024</p>

</footer>


</body>
   <script src="./js/ValidacionFormularioInsertarPaciente.js"></script>
</html>