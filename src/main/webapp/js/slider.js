/**
 * 
 */

let currentIndex = 0;
const slides = document.querySelectorAll(".slide");
const totalSlides = slides.length;

// Mover las diapositivas
function moveSlide(direction) {
    currentIndex = (currentIndex + direction + totalSlides) % totalSlides;
    updateSlidePosition();
}

// Actualizar posición del slider
function updateSlidePosition() {
    const slider = document.querySelector(".slides");
    slider.style.transform = `translateX(-${currentIndex * 100}%)`;
}

// Cambio automático cada 5 segundos
setInterval(() => {
    moveSlide(1);
}, 5000);
