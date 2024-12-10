package modelos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ConsultaMedicaTest {
    private ConsultaMedica consultaMedica;

    @BeforeAll
    public void setUp() {
        consultaMedica = new ConsultaMedica();
    }

    @Test
    public void testSetYGetId() {
        consultaMedica.setId(1);
        assertEquals(1, consultaMedica.getId());
    }

    @Test
    public void testSetYGetFechaConsulta() {
        Date fecha = new Date();
        consultaMedica.setFechaConsulta(fecha);
        assertEquals(fecha, consultaMedica.getFechaConsulta());
    }

    @Test
    public void testSetYGetHoraInicio() {
        String horaInicio = "10:00";
        consultaMedica.setHoraInicio(horaInicio);
        assertEquals(horaInicio, consultaMedica.getHoraInicio());
    }

    @Test
    public void testSetYGetHoraFin() {
        String horaFin = "11:00";
        consultaMedica.setHoraFin(horaFin);
        assertEquals(horaFin, consultaMedica.getHoraFin());
    }

    @Test
    public void testSetYGetDisponible() {
        consultaMedica.setDisponible(true);
        assertEquals(true, consultaMedica.isDisponible());
    }

    // Continuar con el resto de los atributos de ConsultaMedica...
}

