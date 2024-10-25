<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="modelos.Administrador" %>
<%@ page import="dao.PacienteDAO" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>Lista de Administradores</title>
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
} else if (tipoUsuario.equals("ADMINISTRADOR")) {
    // Si es Médico o Administrador, puede ver la lista
	 %>
    <h1>Lista de Administradores</h1>
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

            ArrayList<Administrador> administradores = (ArrayList<Administrador>) request.getAttribute("listaAdministradores");
           if(administradores==null || administradores.size()==0){%>
        	   <h3>no hay administradores </h3>
           <%}
           else{
            for (Administrador administrador : administradores) {
        %>
        <tr>
            <td><%= administrador.getId() %></td>
            <td><%= administrador.getUsername() %></td>
              <td><%= administrador.getPassword() %></td>
            <td><%= administrador.getNombre() %></td>
            <td><%= administrador.getEmail() %></td>
            <td><%= administrador.getTelefono() %></td>
              <td><%= administrador.getFechaNacimiento() %></td>
            <td>
              <a href="AdministradorServlet?action=verAdministrador&id=<%= administrador.getId() %>">VER</a>
                <a href="AdministradorServlet?action=irEditarAdministrador&id=<%= administrador.getId() %>">Editar</a>
                <a href="AdministradorServlet?action=eliminarAdministrador&id=<%= administrador.getId() %>">Eliminar</a>
            </td>
        </tr>
      
    </table>
        <a href="AdministradorServlet?action=irCrearAdministrador">nuevo administrador</a>
                <a href="LoginServlet">logarse</a>
        

<a href="LogoutServlet">cerrar sesión</a>
    <form  action="MedicoServlet" method="get">
    <a href="MedicoServlet?action=listarMedicos" class="button">lista medicos</a>
    </form>
      <a href="PacienteServlet?action=listarPacientes" class="button">lista pacientes</a>
      
        <% }}} %>
</body>
</html>
