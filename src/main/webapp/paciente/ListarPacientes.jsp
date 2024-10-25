<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="modelos.Paciente" %>
<%@ page import="dao.PacienteDAO" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>Lista de Pacientes</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<%
// Obtener el tipo de usuario desde la sesión
String tipoUsuario = (String) session.getAttribute("tipoUsuario");

// Controlar acceso a la lista de pacientes
if (tipoUsuario == null) {
    // Si el usuario no está logado, redirigir al login
    response.sendRedirect("Login.jsp");
    return;
} else if (tipoUsuario.equals("MEDICO") || tipoUsuario.equals("ADMINISTRADOR")) {
    // Si es Médico o Administrador, puede ver la lista
	 %>
	
	<h1>Lista de Pacientes</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Username</th>
              <th>PASSWORD</th>
            <th>Nombre</th>
            <th>Email</th>
            <th>Teléfono</th>
            <th>Fecha nacimiento</th>
            <th>Acciones</th>
        </tr>
        <%
            //PacienteDAO pacienteDAO = new PacienteDAO(DatabaseConnection.getConnection());

            ArrayList<Paciente> pacientes = (ArrayList<Paciente>) request.getAttribute("listaPacientes");
           if(pacientes==null){%>
        	   <h3>no hay pacientes </h3>
           <%}
           else{
            for (Paciente paciente : pacientes) {
        %>
        <tr>
            <td><%= paciente.getId() %></td>
            <td><%= paciente.getUsername() %></td>
              <td><%= paciente.getPassword() %></td>
            <td><%= paciente.getNombre() %></td>
            <td><%= paciente.getEmail() %></td>
            <td><%= paciente.getTelefono() %></td>
              <td><%= paciente.getFechaNacimiento() %></td>
            <td>
              <a href="PacienteServlet?action=verPaciente&id=<%= paciente.getId() %>">VER</a>
                <a href="PacienteServlet?action=irEditarPaciente&id=<%= paciente.getId() %>">Editar</a>
                <a href="PacienteServlet?action=eliminarPaciente&id=<%= paciente.getId() %>">Eliminar</a>
            </td>
        </tr>
     
    </table>
        <a href="PacienteServlet?action=irCrearPaciente">nuevo paciente</a>
                <a href="LoginServlet">logarse</a>
        

<a href="LogoutServlet">cerrar sesión</a>
    <form  action="MedicoServlet" method="get">
    <a href="MedicoServlet?action=listarMedicos" class="button">lista medicos</a>
    </form>
    
       <% }}} %>
    
</body>
</html>
