

document.addEventListener("DOMContentLoaded", function() {
    const reservarBtns = document.querySelectorAll(".reservarBtn");
    const confirmModal = document.getElementById("confirmModal");
    const continuarBtn = document.getElementById("continuarReserva");
    const cancelarBtn = document.getElementById("cancelarReserva");
    let formularioActual;

    // Mostrar modal de confirmación
    reservarBtns.forEach(btn => {
        btn.addEventListener("click", function() {
            formularioActual = this.closest(".formularioReserva");
            confirmModal.style.display = "flex";
        });
    });

    // Confirmar y enviar el formulario
    continuarBtn.addEventListener("click", function() {
        confirmModal.style.display = "none";
        formularioActual.submit();
    });

    // Cancelar la reserva y cerrar el modal
    cancelarBtn.addEventListener("click", function() {
        confirmModal.style.display = "none";
    });
});