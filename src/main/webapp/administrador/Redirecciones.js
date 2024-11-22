/**
 * 
 */

var botonRegresar=document.getElementById("botonRegresar");


function regresar(event) {
	event.preventDefault();  //con esto hacemos que la ACCCION del formulario se autoejecute
    window.history.back();
}
  botonRegresar.addEventListener("click", regresar, false);    
