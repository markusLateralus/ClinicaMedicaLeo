<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ page import="modelos.Paciente" %>
          <%@ page import="modelos.Administrador" %>
               <%@ page import="modelos.Medico" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Aviso Legal</title>
<link rel="stylesheet" type="text/css" href="./css/Contacto.css">
</head>
<%
//Obtener la acción del parámetro
String tipoUsuario = (String) session.getAttribute("tipoUsuario");
Paciente paciente= (Paciente) request.getAttribute("paciente");
Administrador administrador= (Administrador) request.getAttribute("administrador");
Medico medico= (Medico) request.getAttribute("medico");
// Controlar acceso a la lista de pacientes

	 %>
<body>
<header> 
<div class="divLogo">
			<% if(paciente != null){ %>
			<a href="PacienteServlet?action=irIndexPaciente&id=<%=paciente.getId()%>">
			 <img src="imagenes/ClinicaLeo2.png" alt="Logo de Clínica LEO" class="logo">
			</a> 
			<%} else if(medico != null){ %>
			<a href="MedicoServlet?action=irIndexMedico&id=<%=medico.getId()%>">
			 <img src="imagenes/ClinicaLeo2.png" alt="Logo de Clínica LEO" class="logo">
			</a> 
			<%}else if(administrador != null){ %>
			<a href="AdministradorServlet?action=irIndexAdministrador&id=<%=administrador.getId()%>">
			 <img src="imagenes/ClinicaLeo2.png" alt="Logo de Clínica LEO" class="logo">
			</a> 
			<%}
			
			
			else{ %>
				<a href="LoginServlet">
				
    <img src="imagenes/ClinicaLeo2.png" alt="Logo de Clínica LEO" class="logo">
    </a>
				<%} %>

</div>
<div class="divH1">
	<h1>Clínica LEO</h1>
</div>
</header>
<% if(administrador !=null){ %>

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
         <h4><%= "Tipo Usuario: " %></h4>
      </div>
</nav>
<%}else if (medico !=null){ %>
<nav>
    <ul class="menu">
        <li><a href="MedicoServlet?action=irIndexMedico&id=<%=medico.getId()%>">Consultar horario semanal</a></li>
        <!-- Submenú de Datos personales -->
                       <li><a href="RealizarReservaServlet?action=mostrarCitasMedico&idMedico=<%=medico.getId()  %>" >Consultar Citas</a></li>
        
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


<%} else if (paciente !=null){%>

<nav>
    <ul class="menu">
        <li><a href="ConsultarCitaServlet?action=irSolicitarCita&id=<%=paciente.getId()  %>" >Consultar especialistas</a></li>
        <!-- Submenú de Datos personales -->
       <li><a href="RealizarReservaServlet?action=mostrarCitasPaciente&idPaciente=<%=paciente.getId()  %>" >Consultar citas</a></li>
       
        <!-- Submenú de Datos personales -->
        <li>
            <a href="#">Datos Personales</a>
            <ul class="submenu">
                <li><a href="PacienteServlet?action=verPaciente&id=<%= paciente.getId() %>">Consultarlos</a></li>
                <li><a href="PacienteServlet?action=irEditarPaciente&id=<%= paciente.getId() %>">Editarlos</a></li>
            </ul>
        </li>
        <li><a href="LogoutServlet">Cerrar Sesión</a></li>
    </ul>
            <div class="divTipoUsuario">
  	  <h4><%= paciente.getNombre() %></h4>
         <h4><%= "Tipo Usuario: " + tipoUsuario %></h4>
      </div>
</nav>


<%} else{%>

<nav></nav>
<%} %>


<main>
	<section>
		<h2>Aviso Legal</h2>
		<p>
			Este Aviso Legal regula las condiciones generales de acceso y uso del sitio web de Clínica LEO, accesible en la URL 
			<a href="index.jsp">www.clinicaLEO.com</a>, que la empresa pone a disposición de los usuarios de Internet.
		</p>
		<h3>Información General</h3>
		<p>
			<strong>Nombre de la empresa:</strong> Clínica LEO S.L.<br>
			<strong>Domicilio:</strong> Calle de la Salud 123, Madrid, España<br>
			<strong>CIF:</strong> B12345678<br>
			<strong>Teléfono:</strong> +34 600 123 456<br>
			<strong>Email:</strong> info@clinicaLEO.com<br>
		</p>
		<h3>Condiciones de Uso</h3>
		<p>
			El acceso y uso de este sitio web implican la aceptación de los términos y condiciones aquí detallados. Los usuarios se comprometen a 
			utilizar la web y su contenido de forma adecuada y conforme a la legislación vigente.
		</p>
		<h3>Propiedad Intelectual</h3>
		<p>
			El contenido de esta página web, incluyendo textos, imágenes, logotipos, y diseño gráfico, es propiedad de Clínica LEO y está protegido 
			por la legislación sobre propiedad intelectual. Está prohibida su reproducción, distribución o modificación sin autorización expresa.
		</p>
		<h3>Exclusión de Responsabilidad</h3>
		<p>
			Clínica LEO no será responsable de los daños y perjuicios que puedan derivarse del uso inadecuado de esta web, ni de posibles errores 
			o inexactitudes en los contenidos publicados.
		</p>
	</section>
</main>
<footer class="footer">
	<div class="footer-container">
		<div class="footer-left">
		<% if(paciente != null){ %>
			<a href="PacienteServlet?action=irContacto&id=<%=paciente.getId()%>">Contacto</a> 
			<a href="PacienteServlet?action=irAvisoLegal&id=<%=paciente.getId()%>">Aviso Legal</a>
				 <a href="PacienteServlet?action=irPoliticaPrivacidad&id=<%=paciente.getId()%>">Políticas de Privacidad</a>
			<% }else if(medico != null){ %> 
			<a href="MedicoServlet?action=irAvisoLegal&id=<%=medico.getId()%>">Aviso Legal</a>
				 <a href="MedicoServlet?action=irPoliticaPrivacidad&id=<%=medico.getId()%>">Políticas de Privacidad</a>
				<% }else if(administrador != null){ %> 
			<a href="AdministradorServlet?action=irAvisoLegal&id=<%=administrador.getId()%>">Aviso Legal</a>
				 <a href="AdministradorServlet?action=irPoliticaPrivacidad&id=<%=administrador.getId()%>">Políticas de Privacidad</a>
			

			<%} else{ %>
				<a href="ContactoServlet?action=irContactoPersonaNoRegistrada">Contacto</a> 
					<a href="AvisoLegal.jsp">Aviso Legal</a>
				 <a href="PoliticaPrivacidad.jsp">Políticas de Privacidad</a>
				<%} %>
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
</html>
