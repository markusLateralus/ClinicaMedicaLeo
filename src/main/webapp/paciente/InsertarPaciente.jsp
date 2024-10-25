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
    <img src="imagenes/ClinicaLeo2.png" alt="Logo de Clínica LEO" class="logo">
</div>
<div class="divH1">
    <h1>Clínica LEO</h1>
</div>
</header>

<nav>

</nav>
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
        <input type="text" id="username" name="username" minlength="2" maxlength="10" placeholder="mi userName"required><br>
        <span id="errorUsername"></span>
 </div>    <div class="form-group">
 
        <label for="password">Contraseña:</label>
        <input type="password" id="password" name="password" minlength="2" maxlength="20" placeholder="mi password"required><br>
                <span id="errorPassword"></span>
 </div>
     <div class="form-group">
        <label for="dni">DNI:</label>
        <input type="text" id="dni" name="dni" minlength="9" maxlength="9" placeholder="12345678Y"required><br>
	        <span id="errorDni"></span>
 </div>
 
     <div class="form-group">
        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" name="nombre"  minlength="2" maxlength="25" placeholder="nombre" required><br>
 	        <span id="errorNombre"></span>
 </div>
     <div class="form-group">
        <label for="apellido1">Primer Apellido:</label>
        <input type="text" id="apellido1" name="apellido1" minlength="2" maxlength="20" placeholder="primer apellido"required><br>
                <span id="errorApellido1"></span>
 </div>
     <div class="form-group">
        <label for="apellido2">Segundo Apellido:</label>
        <input type="text" id="apellido2" name="apellido2" minlength="2" maxlength="20" placeholder="segundo apellido" required><br>
         <span id="errorApellido2"></span>
 </div>
     <div class="form-group">
        <label for="email">Correo Electrónico:</label>
        <input type="email" id="email" name="email" minlength="5" placeholder="manolete@gmail.com"required><br>
 		        <span id="errorEmail"></span>
 </div>
     <div class="form-group">
        <label for="telefono">Teléfono:</label>
        <input type="tel" id="telefono" name="telefono"  maxlength=9" placeholder="666666666"required><br>
 	        <span id="errorTelefono"></span>
 </div>
     <div class="form-group">
        <label for="fechaNacimiento">Fecha de Nacimiento:</label>
        <input type="date" id="fechaNacimiento" name="fechaNacimiento" placeholder="dd/mm/aaaa" required><br>
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
        <div class="form-group">
    <a class="enlaceLogin" href="LoginServlet" > Ir a login </a>
     </div>
</form>
  </main>
<%} %>


<footer>
<div class="divisionOpciones">
<div class="opciones">
<ul>
<li><a href="">Contacto</a>
</ul>
</div>
<div class="opciones">
<ul>
<li><a href="">Aviso Legal</a>
</ul>
</div>
<div class="opciones">
<ul>
<li><a href="">Política de Privacidad</a>
</ul>
</div>
</div>
    <div class="social-icons">
        <a href="https://www.facebook.com" target="_blank"><img src="imagenes/facebook.png" alt="Facebook"></a>
        <a href="https://www.instagram.com" target="_blank"><img src="imagenes/instagram.png" alt="Instagram"></a>
        <a href="https://www.youtube.com" target="_blank"><img src="imagenes/youtubee.png" alt="YouTube"></a>
    </div>
</footer>
</body>
   <script src="./paciente/ValidacionFormularioInsertarPaciente.js"></script>
</html>