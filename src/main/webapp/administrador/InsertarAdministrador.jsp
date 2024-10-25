<%@ page contentType="text/html;charset=UTF-8" language="java"
 import="java.util.List"
 import= "modelos.Administrador" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  <link rel="stylesheet" href="./css/InsertarPaciente.css"> <!-- Enlace al archivo CSS -->
</head>
<body>
    <h1>Nuevo admnistrador</h1>
<%String action = request.getParameter("action");

if ("irCrearAdministrador".equals(action) || action==null) {
   

 %>
<form id="formularioInsertarAdministrador" action="AdministradorServlet" method="post">
        <!-- Campo oculto para definir la acción en el Servlet -->
        <input type="hidden" name="action" value="crearAdministrador">
        
        <label for="username">Nombre de usuario:</label>
        <input type="text" id="username" name="username" required><br>

        <label for="password">Contraseña:</label>
        <input type="password" id="password" name="password" required><br>

        <label for="dni">DNI:</label>
        <input type="text" id="dni" name="dni" required><br>

        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" name="nombre" required><br>

        <label for="apellido1">Primer Apellido:</label>
        <input type="text" id="apellido1" name="apellido1" required><br>

        <label for="apellido2">Segundo Apellido:</label>
        <input type="text" id="apellido2" name="apellido2" required><br>

        <label for="email">Correo Electrónico:</label>
        <input type="email" id="email" name="email" required><br>

        <label for="telefono">Teléfono:</label>
        <input type="tel" id="telefono" name="telefono" required><br>

        <label for="fechaNacimiento">Fecha de Nacimiento:</label>
        <input type="date" id="fechaNacimiento" name="fechaNacimiento" required><br>

        <input type="submit" value="Registrar Administrador">
    </form>
 <a href="AdministradorServlet?action=listarAdministradores">regresar</a>
    <!-- Enlace para regresar a la lista de pacientes -->
    <a href="LoginServlet" class="button">logarse</a>

    <script src="./js/ValidacionFormularioInsertarPaciente.js"></script> <!-- Enlace al archivo JavaScript -->
<%} %>
 
</body>
</html>