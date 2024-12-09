/**
 * 
 */

// Variables de los campos
var campoUsername = document.getElementById("username");
var campoPassword = document.getElementById("password");
var campoTipoUsuario = document.getElementById("tipoUsuario");

var errorUsername = document.getElementById("errorUsername");
var errorPassword = document.getElementById("errorPassword");
var errorTipoUsuario = document.getElementById("errorTipoUsuario");
var loginError = document.getElementById("loginError");

var botonEnviar = document.getElementById("submitButton");

// Función para validar el campo Username
function validarUsername() {
    var esValido = false;
    var username = campoUsername.value.trim();

    if (username.length < 4 || username.length > 10) {
        campoUsername.className = "camposConError";
        errorUsername.innerHTML = "El username debe tener entre 4 y 10 caracteres.";
    } else {
        campoUsername.classList.remove("camposConError");
        errorUsername.innerHTML = "";
        esValido = true;
    }
    return esValido;
}

// Función para validar el campo Password
function validarPassword(){
	//validamos campo Nombre
	    var esValido=false;
	    var nombre=campoPassword.value;
	    var totalVocales=nombre.match(/[aeiou]/gi);
	    if(nombre ==null ||  nombre == "" || nombre.length<4 || nombre.length>20 ||totalVocales<1 ){
	  //agregamos una css al campo para ponerlo rojo
	        campoPassword.className="camposConError";
	       let mensajeError="Mínimo 4 letras, máximo 20 y una vocal obligatoria";
	       //agregamos un texto en el parrafo con el mensaje de error
	       errorPassword.innerHTML=mensajeError;
	       return esValido;
	    }else{
	        campoPassword.classList.remove('camposConError');
	        errorPassword.innerHTML="";
	        esValido=true;
	        return esValido;
	    }

}





// Función para validar el campo Tipo de Usuario
function validarTipoUsuario() {
    var esValido = false;
    var tipoUsuario = campoTipoUsuario.value;

    if (tipoUsuario === "") {
        campoTipoUsuario.className = "camposConError";
        errorTipoUsuario.innerHTML = "Debes seleccionar un tipo de usuario.";
    } else {
        campoTipoUsuario.classList.remove("camposConError");
        errorTipoUsuario.innerHTML = "";
        esValido = true;
    }
    return esValido;
}

// Función principal para validar el formulario
function validarFormulario(event) {
    event.preventDefault(); // Evita el envío automático del formulario

    var validoUsername = validarUsername();
    var validoPassword = validarPassword();
    var validoTipoUsuario = validarTipoUsuario();

    if (validoUsername && validoPassword && validoTipoUsuario) {
        document.getElementById("loginForm").submit();
    } else {
        loginError.innerHTML = "Por favor, corrige los errores antes de continuar.";
    }
}

// Asociar la validación al botón de enviar
botonEnviar.addEventListener("click", validarFormulario, false);
