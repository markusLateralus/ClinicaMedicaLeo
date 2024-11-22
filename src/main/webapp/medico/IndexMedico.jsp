<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="modelos.Medico" %>
<%@ page import="modelos.Horario" %>
<%@ page import="dao.MedicoDAO" %>
<%@ page import="modelos.Administrador" %>
<%@ page import="dao.AdministradorDAO" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>Indiex del medico</title>
    <link rel="stylesheet" type="text/css" href="css/indexMedico.css">
    <style>
    .disponible { background-color: green; color: white; font-size:20px;}
    .ocupado { background-color: red; color: white; font-size:20px;}
</style>
</head>
<%
Medico medico=(Medico) request.getAttribute("medico");
String tipoUsuario = (String) session.getAttribute("tipoUsuario");
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

<main>
<%


String medicoId=(String) request.getParameter("id");

// Controlar acceso a la lista de pacientes
if (tipoUsuario == null) {
    // Si el usuario no está logado, redirigir al login
    response.sendRedirect("Login.jsp");
    return;
}
    // Si es Médico o Administrador, puede ver la lista
	 %>
	
	 
	 
<%if ( tipoUsuario.equals("MEDICO")){ %>	 
	 

<h2>Horario de la semana</h2>
<table border="1">
    <tr>
        <th>Hora</th>
        <th>Lunes</th>
        <th>Martes</th>
        <th>Miércoles</th>
        <th>Jueves</th>
        <th>Viernes</th>
    </tr>
    <% 
    ArrayList<Horario> horarios= (ArrayList<Horario>) request.getAttribute("horarios");
        for (int hora = 9; hora < 14; hora++) { 
    %>
        <tr>
            <td><%= hora %>:00 - <%= (hora + 1) %>:00</td>
            <% 
                String[] dias = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes"};
                for (String dia : dias) { 
                    boolean esDisponible = false;
                    int horarioId = 1;
                  
                    
                    
                    // Verificar disponibilidad y obtener el ID de horario
                    for (Horario horario : horarios) {%>
                 
                          <% 
                        if (horario.getDia().equals(dia) && horario.getHora().equals(hora + ":00:00")) {
                
                            esDisponible = "disponible".equals(horario.getEstado());
                            horarioId = horario.getId();
                            
                            %>
                              	  
                    <!--  	  <%=dia + " " + horario.getHora()  +  " , " + horario.getHora().equals(hora + ":00:00")%>-->
                            <% 
                            break;
                        }else{
                        	esDisponible="ocupado".equals(horario.getEstado());
                        	horarioId=horario.getId();
                        	%>
                    <!--       		  <%=dia + " " + horario.getHora()  +  " , " + horario.getHora().equals(hora + ":00:00")%>-->
                        	<% 
                        }
                    }
            %>

        
                <td>
                    <% if (esDisponible) { %>
                        <form action="RealizarReservaServlet" method="post">
                            <input type="hidden" name="action" value="citaMedica">
                            <input type="hidden" name="id" value="<%= horarioId %>">
                               <input type="hidden" name="idMedico" value="<%= medico.getId() %>">
                            <div class="disponible" >Libre</div>
                        </form>
                    <% } else {
                    	
                    	%>
                        <div class="ocupado">ocupado</div>
                       
                    <% } %>
                </td>
            <% 
                } // Fin del for de días
            %>
        </tr>
    <% 
        }} // Fin del for de horas
    %>
</table>
      

</main>
<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
	<footer class="footer">
		<div class="footer-container">
			<div class="footer-left">
				<a href="#contacto">Contacto</a> <a href="#aviso-legal">Aviso
					Legal</a> <a href="#politicas-privacidad">Políticas de Privacidad</a>
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
</html>
