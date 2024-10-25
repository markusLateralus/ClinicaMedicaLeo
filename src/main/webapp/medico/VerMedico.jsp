<%@ page contentType="text/html;charset=UTF-8" language="java"
 import="java.util.List"
 import= "modelos.Medico"
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
String especialidad="";
Date fechaNacimiento=null;

// Si la acción es "edit", recuperar los datos del paciente del request (los debes haber pasado desde el servlet)
if ("verMedico".equals(action)) {
	Medico medico = (Medico) request.getAttribute("medico");
    if (medico != null) {
		id=medico.getId();
		username=medico.getUsername();
		password=medico.getPassword();
		dni=medico.getDni();
        nombre = medico.getNombre();
        apellido1 = medico.getApellido1();
        apellido2 = medico.getApellido2();
        especialidad=medico.getEspecialidad();
        email=medico.getEmail();
        telefono=medico.getTelefono();
        fechaNacimiento=medico.getFechaNacimiento();
        
    }
}

%>
    <h1>HOLA <%=nombre %></h1>
<form id="formularioInsertaMedico" action="MedicoServlet" method="post">
        <!-- Campo oculto para definir la acción en el Servlet -->
        <input type="hidden" name="action" value="update">
               <input type="hidden" name="id"value="<%= id %>" />
        <label for="username">Nombre de usuario:</label>
        <input type="text" id="username" name="username" value="<%= username %>" readonly><br>

        <label for="password">Contraseña:</label>
        <input type="text" id="password" name="password" value="<%= password %>" readonly><br>

        <label for="dni">DNI:</label>
        <input type="text" id="dni" name="dni" value="<%= dni %>"  readonly><br>

        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" name="nombre" value="<%= nombre %>" readonly><br>

        <label for="apellido1">Primer Apellido:</label>
        <input type="text" id="apellido1" name="apellido1"  value="<%= apellido1 %>" readonly><br>

        <label for="apellido2">Segundo Apellido:</label>
        <input type="text" id="apellido2" name="apellido2"   value="<%= apellido2 %>" readonly><br>
        
          <label for="especialidad">Especialidad:</label>
        <input type="text" id="especialidad" name="especialidad"   value="<%= especialidad %>" readonly><br>

        <label for="email">Correo Electrónico:</label>
        <input type="email" id="email" name="email"  value="<%= email %>"  readonly><br>

        <label for="telefono">Teléfono:</label>
        <input type="tel" id="telefono" name="telefono" value="<%= telefono %>"  readonly><br>

        <label for="fechaNacimiento">Fecha de Nacimiento:</label>
        <input type="date" id="fechaNacimiento" name="fechaNacimiento" value="<%=fechaNacimiento %>"  readonly><br>
   </form>
 <a href="MedicoServlet?action=listarMedicos">regresar</a>


</body>

</html>