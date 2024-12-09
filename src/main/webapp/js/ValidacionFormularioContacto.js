/**
 * 
 */

var campoNombre=document.getElementById("nombre");
var campoEmail=document.getElementById("email");
var campoTelefono=document.getElementById("telefono");
var campoMensaje=document.getElementById("mensaje");


var errorMensaje=document.getElementById("errorNombre");
var errorEmail=document.getElementById("errorEmail");
var errorTelefono=document.getElementById("errorTelefono");
var errorMensaje=document.getElementById("errorMensaje");

var botonEnviar=document.getElementById("enviar");


function validarNombre(){
    //validamos campo Nombre
        var esValido=false;
        var nombre=campoNombre.value.trim();
        var totalVocales=nombre.match(/[aeiouáéíóúü]/gi);
        if(nombre ==null ||  nombre == "" || nombre.length<2 || nombre.length>25 ||totalVocales<1 ){
      //agregamos una css al campo para ponerlo rojo
            campoNombre.className="camposConError";
           let mensajeError="Mínimo 2 letras, máximo 25 y una vocal obligatoria";
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

  function validarMensaje(){
      //validamos campo Nombre
          var esValido=false;
          var mensaje=campoMensaje.value.trim();

          if(mensaje ==null ||  mensaje == "" ){
        //agregamos una css al campo para ponerlo rojo
              campoMensaje.className="camposConError";
             let mensajeError="el mensaje NO puede ir vacío";
             //agregamos un texto en el parrafo con el mensaje de error
             errorMensaje.innerHTML=mensajeError;
             return esValido;
          }else{
  			mensaje = mensaje.charAt(0).toUpperCase() + mensaje.slice(1).toLowerCase();
  							    // Actualizamos el campo con el valor formateado
  			campoMensaje.value = mensaje;
              campoMensaje.classList.remove('camposConError');
              errorMensaje.innerHTML="";
              esValido=true;
              return esValido;
          }

  }
  
function escribirMensaje(event) {   
   event.preventDefault();  //con esto hacemos que la ACCCION del formulario se autoejecute

   // Identificar cuál formulario y botón se han utilizado
     const botonPresionado = event.target; // Detecta qué botón activó el evento
     const formulario = botonPresionado.closest("form"); // Encuentra el formulario más cercano al botón

   let validoNombre=validarNombre();
   let validoEmail=validarEmail();
   let validoTelefono=validarTelefono();
   let validoMensaje=validarMensaje();


 if( validoNombre &&
	   validoEmail && validoTelefono && validoEmail && validoMensaje ){
	formulario.submit();	
  // document.getElementById("formularioContacto").submit();
   }
}

  botonEnviar.addEventListener("click", escribirMensaje, false);    
