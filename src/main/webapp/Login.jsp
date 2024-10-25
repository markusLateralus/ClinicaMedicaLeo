<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
         <%@ page import="java.util.*,modelos.Administrador,modelos.Rol" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  <link rel="stylesheet" type="text/css" href="./css/Login.css">
    

</head>
<body>

    <h1>CLÍNICA LEO</h1>
    
    <h3>LOGIN</h3>

    <form id="loginForm" action="LoginServlet" method="post">
        <div class="form-group">
            <label for="username">Username *</label>
            <input type="text" id="username" name="username" autofocus>
           
        </div>

        <div class="form-group">
            <label for="password">Contraseña *</label>
            <input type="password" id="password" name="password">
            </div>

<div>
    <!-- Mostrar mensaje de error aquí si está presente -->
        <span id="login-error" class="error-message">
            <%= request.getAttribute("errorMessage") != null ? request.getAttribute("errorMessage") : "" %>
        </span>
	</div>
	
	<div class="form-group">
	 <label for="tipo_usuario">Tipo de Usuario *</label>
    <select name="tipoUsuario" id="tipoUsuario" >
        <option value="PACIENTE">Paciente</option>
        <option value="MEDICO">Médico</option>
         <option value="ADMINISTRADOR">Administrador</option>
    </select>
	</div>
	    <div>
      <!-- Mostrar mensaje de error si existe -->
    <% String error = (String) request.getAttribute("error"); %>
    <% if (error != null) { %>
        <div class="error-message"><%= error %></div>
    <% } %>
    
    </div>
	<div class="form-group">
        <button type="submit">Iniciar Sesión</button>

    </div>

<div class="form-group">
        <span>¿No te has registrado?  <a href="PacienteServlet?action=irCrearPaciente">Regístrate</a> </span>
        </div>
    </form>
    



</body>

</html>