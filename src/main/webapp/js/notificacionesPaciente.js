document.addEventListener("DOMContentLoaded", () => {
    const modal = document.getElementById("confirmModal2");
    const cancelarCitaForm = document.getElementById("cancelarCitaForm");
    const modalHorarioId = document.getElementById("modalHorarioId");
    const modalMedicoId = document.getElementById("modalMedicoId");
    const modalPacienteId = document.getElementById("modalPacienteId");

    // Mostrar modal al pulsar "Cancelar"
    document.querySelectorAll(".cancelarBtn").forEach((button) => {
        button.addEventListener("click", (e) => {
            modal.style.display = "flex";
            modalHorarioId.value = button.getAttribute("data-id");
            modalMedicoId.value = button.getAttribute("data-id-medico");
            modalPacienteId.value = button.getAttribute("data-id-paciente");
        });
    });

    // Cerrar el modal sin cancelar
    document.getElementById("cancelarModal").addEventListener("click", () => {
        modal.style.display = "none";
    });

    // Cerrar modal al hacer clic fuera de él
    window.addEventListener("click", (e) => {
        if (e.target === modal) {
            modal.style.display = "none";
        }
    });
});