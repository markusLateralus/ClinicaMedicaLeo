package modelos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Timestamp;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class NotificacionTest {
    private Notificacion notificacion;

    @BeforeAll
    public void setUp() {
        notificacion = new Notificacion();
    }

    @Test
    public void testSetYGetId() {
        notificacion.setId(1);
        assertEquals(1, notificacion.getId());
    }

    @Test
    public void testSetYGetIdPaciente() {
        notificacion.setIdPaciente(101);
        assertEquals(101, notificacion.getIdPaciente());
    }

    @Test
    public void testSetYGetIdMedico() {
        notificacion.setIdMedico(202);
        assertEquals(202, notificacion.getIdMedico());
    }

    @Test
    public void testSetYGetMensajePaciente() {
        String mensaje = "Mensaje para el paciente";
        notificacion.setMensajePaciente(mensaje);
        assertEquals(mensaje, notificacion.getMensajePaciente());
    }
    
    @Test
    public void testSetYGetMensajeMedico() {
        String mensaje = "Mensaje para el medico";
        notificacion.setMensajeMedico(mensaje);
        assertEquals(mensaje, notificacion.getMensajeMedico());
    }
    
    @Test
    public void testSetYGetEstado() {
        String estado = "actvo";
        notificacion.setEstado(estado);
        assertEquals(estado, notificacion.getEstado());
    }

   
}

