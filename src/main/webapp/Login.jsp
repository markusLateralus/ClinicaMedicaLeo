<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="./css/Login.css">
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

    <main>

        <h2>LOGIN</h2>
        <form id="loginForm" action="LoginServlet" method="post">
            <div class="form-group">
                <label for="username">Username *</label>
                <input type="text" id="username" name="username" placeholder="mi userName"  pattern="^(?=.*[aeiouáéíóúàèìòùäëïöü]).{2,10}$" 
          title="Introduce un nombre de usuario válida, por ejemplo: mika" required><br>
        <span id="errorUsername"></span>
                <span id="errorUsername"></span>
                      <% 
            String errorUsername = (String) request.getAttribute("errorUsername"); 
            if (errorUsername != null) {
        %>
        <div class="error-message">
            <p><%= errorUsername %></p>
        </div>
        <% } %>
            </div>

            <div class="form-group">
                <label for="password">Contraseña *</label>
                <input type="text" id="password" name="password" minlength="4" maxlength="20" placeholder="mi contraseña"  pattern="(?=.*[a-zA-Z0-9!@#\$%\^&\*\(\)_\+\-=\[\] \{\};':\,.<>\/\?¡¿ñÑáéíóúÁÉÍÓÚüÜ]).{4,20}" 
          title="Introduce una contraseña válida, por ejemplo: m12Rs" required><br>
                <span id="errorPassword"></span>
                         <!-- Mensaje de error (si existe) -->
        <% 
            String errorPassword = (String) request.getAttribute("errorPassword"); 
            if (errorPassword != null) {
        %>
        <div class="error-message">
            <p><%= errorPassword %></p>
        </div>
        <% } %>
 
                
            </div>

            <div class="form-group">
                <label for="tipoUsuario">Tipo de Usuario *</label>
                <select name="tipoUsuario" id="tipoUsuario" required>
                    <option value="PACIENTE">Paciente</option>
                    <option value="MEDICO">Médico</option>
                    <option value="ADMINISTRADOR">Administrador</option>
                </select>
                <span id="errorTipoUsuario"></span>
                  <% 
            String errorCredenciales = (String) request.getAttribute("errorCredenciales"); 
            if (errorCredenciales != null) {
        %>
        <div class="error-message">
            <p><%= errorCredenciales %></p>
        </div>
        <% } %>
            </div>

  

            <div class="form-group">
                <button type="button" id="submitButton">Iniciar Sesión</button>
            </div>

            <div class="form-group">
                <span>¿No te has registrado? <a href="PacienteServlet?action=irCrearPaciente">Regístrate</a></span>
            </div>
        </form>
    </main>

    <footer class="footer">
        <div class="footer-container">
            <div class="footer-left">
                <a href="ContactoServlet?action=irContactoPersonaNoRegistrada">Contacto</a> 
                <a href="AvisoLegal.jsp">Aviso Legal</a>
                <a href="PoliticaPrivacidad.jsp">Políticas de Privacidad</a>
            </div>
            <div class="footer-right">
                <a href="https://www.facebook.com" target="_blank"><img src="imagenes/facebook.png" alt="Facebook"></a>
                <a href="https://www.instagram.com" target="_blank"><img src="imagenes/instagram.png" alt="Instagram"></a>
                <a href="https://www.youtube.com" target="_blank"><img src="imagenes/youtube.png" alt="YouTube"></a>
            </div>
        </div>
        <p class="footer-author">Autor: Marcos Antonio Arrornes Alcañiz &copy; 2024</p>
    </footer>

    <script src="./js/ValidacionFormularioLogin.js"></script>
</body>
</html>
    