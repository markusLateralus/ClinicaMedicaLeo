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
    <link rel="stylesheet" type="text/css" href="css/HorarioDelMedico.css">
    <style>
    .disponible { background-color: green; color: white; font-size:20px;}
    .ocupado { background-color: red; color: white; font-size:20px;}
</style>
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
    <ul class="menu">
        <li><a href="MedicoServlet?action=listarMedicos">Lista de Médicos</a></li>
        <!-- Submenú de Datos personales -->
        <li>
            <a href="#">Datos Personales</a>
            <ul class="submenu">
                <li><a href="PacienteServlet?action=consultarDatos">Consultarlos</a></li>
                <li><a href="PacienteServlet?action=editarDatos">Editarlos</a></li>
            </ul>
        </li>
        <li><a href="LogoutServlet">Cerrar Sesión</a></li>
    </ul>
</nav>
<br><br><br><br><br>
<main>
<%
// Obtener el tipo de usuario desde la sesión
String tipoUsuario = (String) session.getAttribute("tipoUsuario");

String medicoId=(String) request.getParameter("id");

// Controlar acceso a la lista de pacientes
if (tipoUsuario == null) {
    // Si el usuario no está logado, redirigir al login
    response.sendRedirect("Login.jsp");
    return;
}
    // Si es Médico o Administrador, puede ver la lista
	 %>
	  <%= tipoUsuario %>
	 
	 
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
                               <input type="hidden" name="idMedico" value="<%= medicoId %>">
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
<footer>
    <div class="social-icons">
        <a href="https://www.facebook.com" target="_blank"><img src="images/facebook-icon.png" alt="Facebook"></a>
        <a href="https://www.instagram.com" target="_blank"><img src="images/instagram-icon.png" alt="Instagram"></a>
        <a href="https://www.youtube.com" target="_blank"><img src="imagenes/youtubeES.png" alt="YouTube"></a>
    </div>
</footer>
  

</body>
</html>
