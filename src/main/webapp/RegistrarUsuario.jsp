<%@ page contentType="text/html;charset=UTF-8" language="java"
 import="java.util.List"
 import= "modelos.Usuario" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="./css/RegistrarUsuario.css">

</head>
<body>

<%String action = request.getParameter("action");

if ("insert".equals(action) || action==null) {
   

 %>
    <div class="form-container">
        <h2>Registro de Usuario</h2>

        <form id="formularioInsertarUsuario" action="UsuarioServlet" method="post">
         <input type="hidden" name="action" value="insert" />
            <!-- Username -->
            <label for="username">Nombre de Usuario</label>
            <input type="text" id="username" name="username" required  placeholder="Ingresa tu usuario">
            <span class="error" id="usernameError"></span>

            <!-- Password -->
            <label for="password">Contraseña</label>
            <input type="password" id="password" name="password" required autocomplete="off" placeholder="Ingresa tu contraseña">
            <span class="error" id="passwordError"></span>
 			<!-- dni -->
            <label for="dni">Dni</label>
            <input type="text" id="dni" name="dni" required placeholder="Ingresa tu dni">
            <span class="error" id="dniError"></span>
			 <!-- Nombre -->
            <label for="nombre">Nombre</label>
            <input type="text" id="nombre" name="nombre" required placeholder="Ingresa tu nombre">
            <span class="error" id="nombreError"></span>
           

            <!-- Apellido -->
            <label for="apellido1">Apellido1</label>
            <input type="text" id="apellido1" name="apellido1" required placeholder="Ingresa tu apellido">
            <span class="error" id="apellido1Error"></span>
                      <!-- Apellido -->
            <label for="apellido2">Apellido2</label>
            <input type="text" id="apellido2" name="apellido2" required placeholder="Ingresa tu apellido">
            <span class="error" id="apellido2Error"></span>

            <!-- Email -->
            <label for="email">Correo Electrónico</label>
            <input type="email" id="email" name="email" required autocomplete="off" placeholder="Ingresa tu email">
            <span class="error" id="emailError"></span>

            <!-- Teléfono -->
            <label for="telefono">Teléfono</label>
            <input type="text" id="telefono" name="telefono" required placeholder="Ingresa tu número de teléfono">
            <span class="error" id="telefonoError"></span>

            <!-- Fecha de nacimiento -->
            <label for="fechaNacimiento">Fecha de Nacimiento</label>
            <input type="date" id="fechaNacimiento" name="fechaNacimiento" required>
            <span class="error" id="fechaNacimientoError"></span>

            <!-- Roles -->
            <label for="roles">Rol</label>
            <select id="roles" name="roles" onchange="toggleEspecialidad()">
                <option value="">Seleccione un rol</option>
                <option value="1">Paciente</option>
                <option value="2">Médico</option>
                <!-- No incluir la opción Administrador directamente en el formulario -->
            </select>
             <span class="error" id="rolesError"></span>
               
               <!-- especialidad -->
               <div id="especialidadesDiv">
            <label for="especialidades" id="labelEspecialidad">Especialidad</label>
            <select id="especialidades" name="especialidades" disabled>
                <option value="">Seleccione una especialidad</option>
                <option value="1">Cardiólogo</option>
                <option value="2">Oftalmólogo</option>
                <!-- No incluir la opción Administrador directamente en el formulario -->
            </select>
            <span class="error" id="especialidadesError"></span>
</div>
            <br><br>
            <input type="submit" value="Registrar" class="submit-btn">
        </form>
              <span>Ya estoy registrado!!  <a href="Login.jsp">Logarme </a></span>
    </div>

    <!-- Enlace al archivo de validación JavaScript -->
    <script src="./js/ValidacionFormularioRegistrarUsuario.js"></script>
    
    <%} %>
</body>
</html>