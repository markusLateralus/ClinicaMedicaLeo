/**
 * 
 */
var campoUserName=document.getElementById("username");
var campoPassword=document.getElementById("password");
var campoDni=document.getElementById("dni");
var campoNombre=document.getElementById("nombre");
var campoApellido1=document.getElementById("apellido1");
var campoApellido2=document.getElementById("apellido2");
var campoEmail=document.getElementById("email");
var campoTelefono=document.getElementById("telefono");
var campoFechaNacimiento=document.getElementById("fechaNacimiento");
var selectEspecialidad=document.getElementById("especialidad");

var errorUserName=document.getElementById("errorUsername");
var errorPassword=document.getElementById("errorPassword");
var errorDni=document.getElementById("errorDni");
var errorNombre=document.getElementById("errorNombre");
var errorApellido1=document.getElementById("errorApellido1");
var errorApellido2=document.getElementById("errorApellido2");
var errorEmail=document.getElementById("errorEmail");
var errorTelefono=document.getElementById("errorTelefono");
var errorFechaNacimiento=document.getElementById("errorFechaNacimiento");
var errorEspecialidad=document.getElementById("errorEspecialidad");
var errorMenorEdad=document.getElementById("errorMenorEdad");

var botonEnviar=document.getElementById("enviar");
function validarUserName(){
    //validamos campo Nombre
        var esValido=false;
        var userName=campoUserName.value.trim();
        var totalVocales=userName.match(/[aeiouáéíóúü]/gi);
        if(userName ==null ||  userName == "" || userName.length<4 || userName.length>10 ||totalVocales<1 ){
      //agregamos una css al campo para ponerlo rojo
            campoUserName.className="camposConError";
           let mensajeError="Mínimo 4 letras, máximo 10 y una vocal obligatoria";
           //agregamos un texto en el parrafo con el mensaje de error
           errorUserName.innerHTML=mensajeError;
           return esValido;
        }else{
			userName=userName.toLowerCase();
					campoUserName.value=userName;
            campoUserName.classList.remove('camposConError');
            errorUserName.innerHTML="";
            esValido=true;
            return esValido;
        }

}

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

function validarDni(){
var dni=campoDni.value;
var numero;
var letr;
var letra;
var mensajeError;
var expresion_regular_dni = /^\d{8}[a-zA-Z]$/;
var esValido=false;

if(expresion_regular_dni.test (dni) == true){
   numero = dni.substr(0,dni.length-1);
   letr = dni.substr(dni.length-1,1);
   numero = numero % 23;
   letra='TRWAGMYFPDXBNJZSQVHLCKET';
   letra=letra.substring(numero,numero+1);
  if (letra!=letr.toUpperCase()) {
    // alert('Dni erroneo, la letra del NIF no se corresponde');
    campoDni.className="camposConError";
     mensajeError="La letra del NIF no se corresponde";
    errorDni.innerHTML=mensajeError;
    return esValido;
   }else{
    // alert('Dni correcto');
    campoDni.classList.remove('camposConError');
    errorDni.innerHTML="";
    esValido=true;
    return esValido;
   }
}else{
  // alert('Dni erroneo, formato no válido');
  campoDni.className="camposConError";
   mensajeError="Formato no valido";
  errorDni.innerHTML=mensajeError;
  return esValido;
 }
}

function validarNombre(){
    //validamos campo Nombre
        var esValido=false;
        var nombre=campoNombre.value.trim();
        var totalVocales=nombre.match(/[aeiouáéíóúü]/gi);
        if(nombre ==null ||  nombre == "" || nombre.length<4 || nombre.length>25 ||totalVocales<1 ){
      //agregamos una css al campo para ponerlo rojo
            campoNombre.className="camposConError";
           let mensajeError="Mínimo 4 letras, máximo 25 y una vocal obligatoria";
           //agregamos un texto en el parrafo con el mensaje de error
           errorNombre.innerHTML=mensajeError;
           return esValido;
        }else{
			nombre = nombre.charAt(0).toUpperCase() + nombre.slice(1).toLowerCase();
							    // Actualizamos el campo con el valor formateado
			campoNombre.value = nombre;
            campoNombre.classList.remove('camposConError');
            errorNombre.innerHTML="";
            esValido=true;
            return esValido;
        }

}





function validarApellido1(){
	//validamos campo Nombre
	    var esValido=false;
	    var apellido1=campoApellido1.value.trim();
	    var totalVocales=apellido1.match(/[aeiouáéíóúü]/gi);
	    if(apellido1 ==null ||  apellido1 == "" || apellido1.length<4 || apellido1.length>20 ||totalVocales<1 ){
	  //agregamos una css al campo para ponerlo rojo
	        campoApellido1.className="camposConError";
	       let mensajeError="Mínimo 4 letras, máximo 20 y una vocal obligatoria";
	       //agregamos un texto en el parrafo con el mensaje de error
	       errorApellido1.innerHTML=mensajeError;
	       return esValido;
	    }else{
			apellido1 = apellido1.charAt(0).toUpperCase() + apellido1.slice(1).toLowerCase();
									// Actualizamos el campo con el valor formateado
									 campoApellido1.value = apellido1;
	        campoApellido1.classList.remove('camposConError');
	        errorApellido1.innerHTML="";
	        esValido=true;
	        return esValido;
	    }

}
function validarApellido2(){
	//validamos campo Nombre
	    var esValido=false;
	    var apellido2=campoApellido2.value.trim();
	    var totalVocales=apellido2.match(/[aeiouáéíóúü]/gi);
	    if(apellido2 ==null ||  apellido2 == "" || apellido2.length<4 || apellido2.length>20 ||totalVocales<1 ){
	  //agregamos una css al campo para ponerlo rojo
	        campoApellido2.className="camposConError";
	       let mensajeError="Mínimo 4 letras, máximo 20 y una vocal obligatoria";
	       //agregamos un texto en el parrafo con el mensaje de error
	       errorApellido2.innerHTML=mensajeError;
	       return esValido;
	    }else{
			apellido2 = apellido2.charAt(0).toUpperCase() + apellido2.slice(1).toLowerCase();
							// Actualizamos el campo con el valor formateado
					campoApellido2.value = apellido2
	        campoApellido2.classList.remove('camposConError');
	        errorApellido2.innerHTML="";
	        esValido=true;
	        return esValido;
	    }

}


 function validarEmail(){
    let email=campoEmail.value.trim();
    let patron= /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/
    let esValido=false;
    if(patron.test(email)){  //utilizamos el metodo test() para esta validacion
       // console.log("correcto")
       campoEmail.classList.remove('camposConError');
       errorEmail.innerHTML="";
       esValido=true;
       return esValido;
      }else{
		email=email.toLowerCase();
			campoEmail.value=email;
       campoEmail.className="camposConError";
       mensajeError="Formato no válido";
       errorEmail.innerHTML=mensajeError;
       return esValido;
      }
 }


 function validarTelefono(){
   let telefono=campoTelefono.value;
   let patron = /^[6|7|9]{1}\d{8}$/; //debe empezar por 6o 7 o 9 y un total de 9 digitos
   var esValido=false;
   if(telefono.match(patron)){
   //  console.log("correcto")
      campoTelefono.classList.remove('camposConError');
      errorTelefono.innerHTML="";
      esValido=true;
      return esValido;
     }else{
      campoTelefono.className="camposConError";
      mensajeError="Debe comenzar por 6, 7 ó 9";
      errorTelefono.innerHTML=mensajeError;
      return esValido;
     }
  } 
7






function validarFechaNacimiento(){
var fechaNacimiento=campoFechaNacimiento.value;

if(fechaNacimiento!=""){
    mensajeError="";
    errorMenorEdad.innerHTML=mensajeError;
    errorMenorEdad.classList.remove('camposConError');


var fecha=new Date(fechaNacimiento);
var dia=fecha.getDate();
var mes=fecha.getMonth()+1;
var anio=fecha.getFullYear();
var fechaHoy=new Date();
let edad;
//reseteamos las horas de las dos fechas para que se puedan comparar no tenerlas en cuenta cuando comparemos fechas
fechaHoy.setHours(0,0,0,0);
fecha.setHours(0,0,0,0);

if(fechaHoy<=fecha){
    campoFechaNacimiento.className="camposConError";
    mensajeError="No se pueden registrar menores de 16 anos";
    errorFechaNacimiento.innerHTML=mensajeError;
   
    
}else{
    campoFechaNacimiento.classList.remove('camposConError');
    errorFechaNacimiento.innerHTML="";
    edad=getEdad(fecha);
    if(edad<16){
        //console.log("no se admiten alumnos menores de 16 años");
        mensajeError="No se pueden registrar menores de 16 anos";
        errorMenorEdad.innerHTML=mensajeError;
        campoFechaNacimiento.className="camposConError";
        return edad;
    }else{
        //console.log("correcto")
        mensajeError="";
        errorMenorEdad.innerHTML=mensajeError;
        errorMenorEdad.classList.remove('camposConError');
        return edad;
    }
}
}else{
    mensajeError="No tiene fecha ";
	campoFechaNacimiento.className="camposConError";
    errorFechaNacimiento.innerHTML=mensajeError;

    //errorMenorEdad.className="camposConError";
}
}
function getEdad(dateString) {
    let hoy = new Date()
    let fechaNacimiento = new Date(dateString)
    let edad = hoy.getFullYear() - fechaNacimiento.getFullYear()
    let diferenciaMeses = hoy.getMonth() - fechaNacimiento.getMonth()
    if (
      diferenciaMeses < 0 ||
      (diferenciaMeses === 0 && hoy.getDate() < fechaNacimiento.getDate())
    ) {
      edad--
    }
    return edad
  }
  
  function validarEspecialidad(){
      //validamos campo Nombre
          var esValido=false;
          var nombre=selectEspecialidad.value;

          if(nombre ==null ||  nombre == ""){
        //agregamos una css al campo para ponerlo rojo
              selectEspecialidad.className="camposConError";
             let mensajeError="Seleccione una especialidad";
             //agregamos un texto en el parrafo con el mensaje de error
             errorEspecialidad.innerHTML=mensajeError;
             return esValido;
          }else{
              selectEspecialidad.classList.remove('camposConError');
              errorEspecialidad.innerHTML="";
              esValido=true;
              return esValido;
          }

  }  
  
  
  
  
function escribirMensaje(event) {   
   event.preventDefault();  //con esto hacemos que la ACCCION del formulario se autoejecute
   let validoUserName=validarUserName();
   let validoPassword=validarPassword();
   let validoDni=validarDni();
   let validoNombre=validarNombre();
   let validoApellido1=validarApellido1();
   let validoApellido2=validarApellido2();
   let validoEmail=validarEmail();
   let validoTelefono=validarTelefono();
   let validoFechaNacimiento=validarFechaNacimiento();
	let validoEspecialidad=validarEspecialidad();
   

 if(validoUserName && validoPassword && validoDni && validoNombre && validoApellido1 && validoApellido2 &&
	   validoEmail && validoTelefono && validoEmail && validoEspecialidad && validoFechaNacimiento>=16){
   document.getElementById("formularioInsertarMedico").submit();
   }
}

  botonEnviar.addEventListener("click", escribirMensaje, false);    
