/**
 * registrar usuarios 
 */

function validarFormulario () {
    let valid = true;

    // Validación del campo username
    const username = document.getElementById("username").value;
    const usernameError = document.getElementById("usernameError");
    const usernameRegex = /^[a-zA-Z0-9]*[aeiouAEIOU]+[a-zA-Z0-9]*$/;
	const inputUsername=document.getElementById("username");

    if (username.length < 3 || username.length > 10 || !usernameRegex.test(username)) {
        usernameError.textContent = "El nombre de usuario debe tener entre 3 y 10 caracteres y contener al menos una vocal.";
        inputUsername.classList.add("invalid");
		inputUsername.classList.remove("valid");
		valid = false;
    } else {
        usernameError.textContent = "";
		inputUsername.classList.add("valid");
			inputUsername.classList.remove("invalid");
		;
    }

    // Validación del campo password
    const password = document.getElementById("password").value;
    const passwordError = document.getElementById("passwordError");
    const passwordRegex = /^[a-zA-Z0-9]+$/; //caracteres o numeros
    if (password.length < 3 || password.length > 10 || !passwordRegex.test(password)) {
        passwordError.textContent = "La contraseña debe tener entre 3 y 10 caracteres.";
        valid = false;
    } else {
        passwordError.textContent = "";
    }
	
	//validacion DNI
	const dni = document.getElementById("dni").value;
	const dniError=document.getElementById("dniError");
	   const dniRegex = /^[0-9]{8}[A-Z]$/;

	   // Validar el DNI usando la expresión regular
	   if (!dniRegex.test(dni)) {
	       dniError.textContent = "El formato del DNI debe ser 123456789M.";
		   valid = false;
		      } else {
		          dniError.textContent = "";
		      }
	
	
	
	

    // Validación del nombre
    const nombre = document.getElementById("nombre").value;
    const nombreError = document.getElementById("nombreError");
	//const nombreRegex = /^[a-zA-Z\s]*[aeiouAEIOU]+[a-zA-Z\s]*$/;
	const nombreRegex = /^[a-zA-ZáéíóúÁÉÍÓÚñÑ\s]*[aeiouáéíóúAEIOUÁÉÍÓÚñÑ]+[a-zA-ZáéíóúÁÉÍÓÚñÑ\s]*$/;

    if (nombre.length < 4 || nombre.length > 20 || !nombreRegex.test(nombre)) {
        nombreError.textContent = "El nombre debe tener entre 4 y 20 caracteres y contener al menos una vocal.";
        valid = false;
    } else {
        nombreError.textContent = "";
    }

    // Validación del apellido1
    const apellido1 = document.getElementById("apellido1").value;
    const apellido1Error = document.getElementById("apellido1Error");

    if (apellido1.length < 4 || apellido1.length > 20 || !nombreRegex.test(apellido1)) {
        apellido1Error.textContent = "El apellido1 debe tener entre 4 y 20 caracteres y contener al menos una vocal.";
        valid = false;
    } else {
        apellido1Error.textContent = "";
    }
	
	// Validación del apellido2
	const apellido2 = document.getElementById("apellido2").value;
	const apellido2Error = document.getElementById("apellido2Error");

	if (apellido2.length < 4 || apellido2.length > 20 || !nombreRegex.test(apellido2)) {
	    apellido2Error.textContent = "El apellido2 debe tener entre 4 y 20 caracteres y contener al menos una vocal.";
	    valid = false;
	} else {
	    apellido2Error.textContent = "";
	}

    // Validación del correo electrónico
    const email = document.getElementById("email").value;
    const emailError = document.getElementById("emailError");
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/; // Regex para validar emails
    if (!emailRegex.test(email)) {
        emailError.textContent = "Por favor, ingresa un correo electrónico válido.";
        valid = false;
    } else {
        emailError.textContent = "";
    }

    // Validación del teléfono
    const telefono = document.getElementById("telefono").value;
    const telefonoError = document.getElementById("telefonoError");
    const telefonoRegex = /^\d{9}$/;
    if (!telefonoRegex.test(telefono)) {
        telefonoError.textContent = "El teléfono debe contener exactamente 9 dígitos.";
        valid = false;
    } else {
        telefonoError.textContent = "";
    }

    // Validación de la fecha de nacimiento
    const fechaNacimiento = document.getElementById("fechaNacimiento").value;
    const fechaNacimientoError = document.getElementById("fechaNacimientoError");
    const fechaMinima = new Date('1900-01-01');
    const fechaUsuario = new Date(fechaNacimiento);
    if (fechaUsuario < fechaMinima || fechaNacimiento === "") {
        fechaNacimientoError.textContent = "La fecha de nacimiento debe ser posterior al 1 de enero de 1900.";
        valid = false;
    } else {
        fechaNacimientoError.textContent = "";
    }

    // Validación de roles
    const roles = document.getElementById("roles").value;
    const rolesError = document.getElementById("rolesError");
    if (roles === "") {
        rolesError.textContent = "Debes seleccionar un rol.";
        valid = false;
    } else {
        rolesError.textContent = "";
    }
return valid;
};

// Asignar la función de validación al formulario cuando se envía
document.getElementById("formularioInsertarUsuario").onsubmit = validarFormulario;



document.getElementById("formularioInsertarUsuario").addEventListener('submit', function(event) {
    if (!validarFormulario()) {
        event.preventDefault();  // Detiene el envío del formulario
    }
});



function toggleEspecialidad() {
      var rolSelect = document.getElementById('roles');
      var especialidadSelect = document.getElementById('especialidades');
	  var especialidadLabel = document.getElementById('labelEspecialidad');
      if (rolSelect.value == "2") {  // Si el rol es Médico
          especialidadSelect.disabled = false;  // Habilitar el select
          especialidadSelect.classList.remove('not-editable');  // Remover clase de no editable
		  especialidadLabel.classList.remove('not-editable');  // Remover clase de no editable
      } else if (rolSelect.value == "1") {  // Si el rol es Paciente
          especialidadSelect.disabled = true;  // Deshabilitar el select
          especialidadSelect.classList.add('not-editable');  // Añadir clase de no editable
		  especialidadLabel.classList.add('not-editable');  // Añadir clase de no editable
      } else {
          especialidadSelect.disabled = true;  // Si no se selecciona un rol, mantener el select deshabilitado
          especialidadSelect.classList.add('not-editable');
		  especialidadLabel.classList.add('not-editable');  // Añadir clase de no editable
      }
  }



