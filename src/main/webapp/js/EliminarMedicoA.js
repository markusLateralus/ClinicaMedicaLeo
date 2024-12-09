



let idMedico, idAdministrador;
function abrirModal(medicoId, administradorId) {

	idMedico = medicoId;
	   idAdministrador = administradorId;
	   
    document.getElementById("confirmModal").style.display = "flex";
}

function cerrarModal() {
    document.getElementById("confirmModal").style.display = "none";
}


function confirmarEliminacion() {
    // Redirige al servlet con los parámetros de eliminación
    window.location.href = `AdministradorServlet?action=eliminarMedico&idMedico=${idMedico}&idAdministrador=${idAdministrador}`;
}
	