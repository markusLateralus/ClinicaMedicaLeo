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
if ("irEditarMedico".equals(action)) {
	Medico medico = (Medico) request.getAttribute("medico");
    if (medico != null) {
		id=medico.getId();
		username=medico.getUsername();
		password=medico.getPassword();
		dni=medico.getDni();
        nombre = medico.getNombre();
        apellido1 = medico.getApellido1();
        apellido2 = medico.getApellido2();
        email=medico.getEmail();
        telefono=medico.getTelefono();
        fechaNacimiento=medico.getFechaNacimiento();
        especialidad=medico.getEspecialidad();
    }
}

%>
 <h1>Editar Medico <%=nombre %></h1>
<form id="formularioInsertarMedico" action="MedicoServlet" method="post">
        <!-- Campo oculto para definir la acción en el Servlet -->
        <input type="hidden" name="action" value="actualizarMedico">
               <input type="hidden" name="id"value="<%= id %>" />
        <label for="username">Nombre de usuario:</label>
        <input type="text" id="username" name="username" value="<%= username %>" required><br>

        <label for="password">Contraseña:</label>
        <input type="password" id="password" name="password" value="<%= password %>" required><br>

        <label for="dni">DNI:</label>
        <input type="text" id="dni" name="dni" value="<%= dni %>"  required><br>

        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" name="nombre" value="<%= nombre %>" required><br>

        <label for="apellido1">Primer Apellido:</label>
        <input type="text" id="apellido1" name="apellido1"  value="<%= apellido1 %>" required><br>

        <label for="apellido2">Segundo Apellido:</label>
        <input type="text" id="apellido2" name="apellido2"   value="<%= apellido2 %>" required><br>

        <label for="email">Correo Electrónico:</label>
        <input type="email" id="email" name="email"  value="<%= email %>"  required><br>

        <label for="telefono">Teléfono:</label>
        <input type="tel" id="telefono" name="telefono" value="<%= telefono %>"  required><br>

        <label for="fechaNacimiento">Fecha de Nacimiento:</label>
        <input type="date" id="fechaNacimiento" name="fechaNacimiento" value="<%=fechaNacimiento %>"  required><br>
        
                <label for="especialidad">Especialidad</label>
        <input type="text" id="especialidad" name="especialidad"   value="<%= especialidad %>" required readonly><br>

        <input type="submit" value="actualizar datos">
    </form>

    <!-- Enlace para regresar a la lista de pacientes -->
    <form  action="MedicoServlet" method="get">
    <a href="MedicoServlet?action=listarMedicos" class="button">Volver a la Lista</a>
</form>















</body>

</html>