<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="modelos.Medico" %>
<%@ page import="dao.MedicoDAO" %>
<%@ page import="modelos.Administrador" %>
<%@ page import="dao.AdministradorDAO" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>Lista de Medicos</title>
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
    <h1>Lista de Medicos</h1>
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

            ArrayList<Medico> medicos= (ArrayList<Medico>) request.getAttribute("listaMedicos");
           if(medicos==null){%>
        	   <h3>no hay medicos </h3>
           <%}
           else{
            for (Medico medico : medicos) {
        %>
        <tr>
            <td><%= medico.getId() %></td>
            <td><%= medico.getUsername() %></td>
              <td><%= medico.getPassword() %></td>
            <td><%= medico.getNombre() %></td>
            <td><%= medico.getEmail() %></td>
            <td><%= medico.getTelefono() %></td>
              <td><%= medico.getFechaNacimiento() %></td>
            <td>
              <a href="MedicoServlet?action=verMedico&id=<%= medico.getId() %>">VER</a>
                <a href="MedicoServlet?action=irEditarMedico&id=<%= medico.getId() %>">Editar</a>
                <a href="MedicoServlet?action=eliminarMedico&id=<%= medico.getId() %>">Eliminar</a>
            </td>
        </tr>
      
    </table>
        <a href="MedicoServlet?action=irCrearMedico">nuevo medico</a>
                <a href="LoginServlet">logarse</a>
        

<a href="LogoutServlet">cerrar sesión</a>
<a href="PacienteServlet?action=listarPacientes">lista pacientes</a>
<a href="MedicoServlet?action=listarMedicos">lista medicos</a>

  <% }}} %>
  <a href="AdministradorServlet?action=listarAdministradores">lista administradores</a>
</body>
</html>
