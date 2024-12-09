<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="modelos.Administrador" %>
    <%@ page import="modelos.Paciente" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Contacto</title>
<link rel="stylesheet" type="text/css" href="./css/Contacto.css">
</head>
<%
//Obtener la acción del parámetro

Paciente paciente= (Paciente) request.getAttribute("paciente");
// Controlar acceso a la lista de pacientes

	 %>

<body>
<header> 
<div class="divLogo">
			<% if(paciente != null){ %>
			<a href="PacienteServlet?action=irIndexPaciente&id=<%=paciente.getId()%>">
			 <img src="imagenes/ClinicaLeo2.png" alt="Logo de Clínica LEO" class="logo">
			</a> 
			<%} else{ %>
				<a href="LoginServlet">
				
    <img src="imagenes/ClinicaLeo2.png" alt="Logo de Clínica LEO" class="logo">
    </a>
				<%} %>

</div>
    <div class="divH1">
        <h1>Clínica LEO</h1>
    </div>
    </header>
<nav>
    <ul class="menu">
    	<% if(paciente != null){ %>
        <li><a href="ConsultarCitaServlet?action=irSolicitarCita&id=<%=paciente.getId()  %>" >Consultar especialistas</a></li>
       	<%} else{ %>
       	
       	<%} %>
       	<% if(paciente != null){ %>
        <!-- Submenú de Datos personales -->
       <li><a href="RealizarReservaServlet?action=mostrarCitasPaciente&idPaciente=<%=paciente.getId()  %>" >Consultar citas</a></li>
   <%} else{ %>
       	
       	<%} %>
       	<!--  bmenú de Datos personales -->
        <li>
        	<% if(paciente != null){ %>
            <a href="#">Datos Personales</a>
            <ul class="submenu">
            
                <li><a href="PacienteServlet?action=verPaciente&id=<%= paciente.getId() %>">Consultarlos</a></li>
                <li><a href="PacienteServlet?action=irEditarPaciente&id=<%= paciente.getId() %>">Editarlos</a></li>
            </ul>
                 	<%} else{ %>
       	
       	<%} %>
        </li>
        	<% if(paciente != null){ %>
        <li><a href="LogoutServlet">Cerrar Sesión</a></li>
             	<%} else{ %>
       	
       	<%} %>
    </ul>
            <div class="divTipoUsuario">
            	<% if(paciente != null){ %>
  	  <h4><%= paciente.getNombre() %></h4>
           	<%} else{ %>
       	
       	<%} %>
      </div>
</nav>
  <main> 
  <section> 
  <h2>Contacto</h2>
   <p>Estamos aquí para ayudarte . Si tienes alguna consulta, no dudes en enviarnos un mensaje a través del siguiente formulario.</p>
    </section>
     <section> 
<% if (paciente != null){ %>
      <form id="formularioContacto" method="post" action="ContactoServlet">
           <fieldset>
        <legend>FORMULARIO DE CONTACTO</legend>
           <div class="form-group">
        <input type="hidden" name="action" value="enviarEmailDelPaciente" />
            </div>
               <div class="form-group">
        <input type="hidden"  name="id" value="<%=paciente.getId() %>"/>
            </div>
            
           <div class="form-group">
       <label for="nombre">Nombre:</label> 
       <input type="text" id="nombre" name="nombre" placeholder="Tu nombre completo" required> 
        <span id="errorNombre"></span>
       </div> 
            <div class="form-group">
       <label for="email">Correo Electrónico:</label> 
       <input type="email" id="email" name="email" placeholder="tuemail@ejemplo.com" required>
       <span id="errorEmail"></span>
        </div> 
             <div class="form-group">
        <label for="telefono">Teléfono:</label>
         <input type="tel" id="telefono" name="telefono" placeholder="Ej: 600123456" pattern="[6|7|9]{1}[0-9]{8}" required> 
        <span id="errorTelefono"></span>
         </div> 
          <div class="form-group"> 
         <label for="mensaje">Mensaje:</label> 
         <textarea id="mensaje" name="mensaje"  placeholder="Escribe tu consulta aquí..." required>
         </textarea> 
          <span id="errorMensaje"></span>
         </div> 
              <div class="form-group">
         <button type="submit" id="enviar">Enviar</button>
     			</div>
     				  </fieldset>
    			
                 <div class="form-group link-container">
            <a href="LoginServlet" class="login-link">Ir a login</a>
        </div>
    		
           </form> 		
     			
     			
     			
         <%} else { %>
             <form id="formularioContactoPersonaNoRegistrada" method="post" action="ContactoServlet">
           <fieldset>
        <legend>FORMULARIO DE CONTACTO</legend>
           <div class="form-group">
        <input type="hidden" name="action" value="enviarEmailPersonaNoRegistrada">
            </div>
            
           <div class="form-group">
       <label for="nombre">Nombre:</label> 
       <input type="text" id="nombre" name="nombre" placeholder="Tu nombre completo" required> 
        <span id="errorNombre"></span>
       </div> 
            <div class="form-group">
       <label for="email">Correo Electrónico:</label> 
       <input type="email" id="email" name="email" placeholder="tuemail@ejemplo.com" required>
       <span id="errorEmail"></span>
        </div> 
             <div class="form-group">
        <label for="telefono">Teléfono:</label>
         <input type="tel" id="telefono" name="telefono" placeholder="Ej: 600123456" pattern="[6|7|9]{1}[0-9]{8}" required> 
        <span id="errorTelefono"></span>
         </div> 
          <div class="form-group"> 
         <label for="mensaje">Mensaje:</label> 
         <textarea id="mensaje" name="mensaje"  placeholder="Escribe tu consulta aquí..." required>
         </textarea> 
          <span id="errorMensaje"></span>
         </div> 
              <div class="form-group">
         <button type="submit" id="enviar">Enviar</button>
     			</div>    
           
          		  </fieldset>
    		
                 <div class="form-group link-container">
            <a href="LoginServlet" class="login-link">Ir a login</a>
        </div>
    			
           </form>  
           
           
           <%} %>
      
    	
           </section>
           
            <section class="datosPersonales">
             <h3>Información de contacto</h3>
              <p><strong>Dirección:</strong> Calle de la Salud 123, Madrid, España</p>
               <p><strong>Teléfono:</strong> +34 600 123 456</p>
                <p><strong>Correo Electrónico:</strong> info@clinicaLEO.com</p>
                 <p><strong>Horario de Atención:</strong> Lunes a Viernes, 9:00 AM - 7:00 PM</p> 
                 </section> 
                 </main>
	<footer class="footer">
		<div class="footer-container">
			<div class="footer-left">
			<% if(paciente != null){ %>
			<a href="PacienteServlet?action=irContacto&id=<%=paciente.getId()%>">Contacto</a> 
			<a href="PacienteServlet?action=irAvisoLegal&id=<%=paciente.getId()%>">Aviso Legal</a>
				 <a href="PacienteServlet?action=irPoliticaPrivacidad&id=<%=paciente.getId()%>">Políticas de Privacidad</a>
			<%} else{ %>
				<a href="ContactoServlet?action=irContactoPersonaNoRegistrada">Contacto</a> 
					<a href="AvisoLegal.jsp">Aviso Legal</a>
				 <a href="PoliticaPrivacidad.jsp">Políticas de Privacidad</a>
				<%} %>
					
			
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
    <script src="./js/ValidacionFormularioContacto.js"></script>
</html>