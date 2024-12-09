



let idPaciente, idAdministrador;
function abrirModal(pacienteId, administradorId) {

	idPaciente = pacienteId;
	   idAdministrador = administradorId;
	   
    document.getElementById("confirmModal").style.display = "flex";
}

function cerrarModal() {
    document.getElementById("confirmModal").style.display = "none";
}


function confirmarEliminacion() {
    // Redirige al servlet con los parámetros de eliminación
    window.location.href = `AdministradorServlet?action=eliminarPaciente&idPaciente=${idPaciente}&idAdministrador=${idAdministrador}`;
}
	