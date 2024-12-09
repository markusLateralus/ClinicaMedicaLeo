/**
 * 
 */

var botonRegresarAdmnistrador=document.getElementById("botonRegresarAdministrador");
var botonRegresarMedico=document.getElementById("botonRegresarMedico");
var botonRegresarPaciente=document.getElementById("botonRegresarPaciente");

function regresarIndexAdministrador(event, id) {
	event.preventDefault();  //con esto hacemos que la ACCCION del formulario se autoejecute
	const url = `http://localhost:8080/ClinicaMedica/AdministradorServlet?action=IndexAdministrador&id=${id}`;
	window.location.href=url;
  //  window.history.back();
}

function regresarIndexMedico(event, id) {
	event.preventDefault();  //con esto hacemos que la ACCCION del formulario se autoejecute
	const url = `http://localhost:8080/ClinicaMedica/MedicoServlet?action=irIndexMedico&id=${id}`;
	window.location.href=url;
  //  window.history.back();
}

function regresarIndexPaciente(event, id) {
	event.preventDefault();  //con esto hacemos que la ACCCION del formulario se autoejecute
	const url = `http://localhost:8080/ClinicaMedica/PacienteServlet?action=irIndexPaciente&id=${id}`;
	window.location.href=url;
  //  window.history.back();
}
  botonRegresarAdmnistrador.addEventListener("click", regresarIndexAdministrador, false);    
  botonRegresarMedico.addEventListener("click", regresarIndexMedico, false);  
  botonRegresarPaciente.addEventListener("click", regresarIndexPaciente, false);  