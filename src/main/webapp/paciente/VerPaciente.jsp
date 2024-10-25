<%@ page contentType="text/html;charset=UTF-8" language="java"
 import="java.util.List"
 import= "modelos.Paciente"
 import= "java.util.Date"
  %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  
<% 
// Obtener la acción del parámetro
String action = request.getParameter("action");

// Inicializar variables para el paciente
int id=0;
String username="";
String password="";
String dni="";
String nombre = "";
String apellido1 = "";
String apellido2 = "";
String email="";
String telefono="";
Date fechaNacimiento=null;

// Si la acción es "edit", recuperar los datos del paciente del request (los debes haber pasado desde el servlet)
if ("verPaciente".equals(action)) {
    Paciente paciente = (Paciente) request.getAttribute("paciente");
    if (paciente != null) {
		id=paciente.getId();
		username=paciente.getUsername();
		password=paciente.getPassword();
		dni=paciente.getDni();
        nombre = paciente.getNombre();
        apellido1 = paciente.getApellido1();
        apellido2 = paciente.getApellido2();
        email=paciente.getEmail();
        telefono=paciente.getTelefono();
        fechaNacimiento=paciente.getFechaNacimiento();
    }
}

%>
 <h1>HOLA <%=nombre %></h1>
<form id="formularioInsertarPaciente" action="PacienteServlet" method="post">
        <!-- Campo oculto para definir la acción en el Servlet -->
        <input type="hidden" name="action" value="update">
               <input type="hidden" name="id"value="<%= id %>" />
        <label for="username">Nombre de usuario:</label>
        <input type="text" id="username" name="username" value="<%= username %>" readonly><br>

        <label for="password">Contraseña:</label>
        <input type="password" id="password" name="password" value="<%= password %>" readonly><br>

        <label for="dni">DNI:</label>
        <input type="text" id="dni" name="dni" value="<%= dni %>"  readonly><br>

        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" name="nombre" value="<%= nombre %>" readonly><br>

        <label for="apellido1">Primer Apellido:</label>
        <input type="text" id="apellido1" name="apellido1"  value="<%= apellido1 %>" readonly><br>

        <label for="apellido2">Segundo Apellido:</label>
        <input type="text" id="apellido2" name="apellido2"   value="<%= apellido2 %>" readonly><br>

        <label for="email">Correo Electrónico:</label>
        <input type="email" id="email" name="email"  value="<%= email %>"  readonly><br>

        <label for="telefono">Teléfono:</label>
        <input type="tel" id="telefono" name="telefono" value="<%= telefono %>"  readonly><br>

        <label for="fechaNacimiento">Fecha de Nacimiento:</label>
        <input type="date" id="fechaNacimiento" name="fechaNacimiento" value="<%=fechaNacimiento %>"  readonly><br>
   
 <a href="PacienteServlet?action=listarPacientes">regresar</a>


</body>

</html>