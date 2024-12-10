package modelos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class HorarioTest {
    private Horario horario;

    @BeforeAll
    public void setUp() {
        horario = new Horario();
    }

    @Test
    public void testSetYGetId() {
        horario.setId(1);
        assertEquals(1, horario.getId());
    }

    @Test
    public void testSetYGetMedicoId() {
        horario.setMedicoId(101);
        assertEquals(101, horario.getMedicoId());
    }

    @Test
    public void testSetYGetPacienteId() {
        horario.setPacienteId(202);
        assertEquals(202, horario.getPacienteId());
    }

    @Test
    public void testSetYGetDia() {
        String dia = "Lunes";
        horario.setDia(dia);
        assertEquals(dia, horario.getDia());
    }

    @Test
    public void testSetYGetHora() {
        String hora = "10:00";
        horario.setHora(hora);
        assertEquals(hora, horario.getHora());
    }

    @Test
    public void testSetYGetEstado() {
        String estado = "activo";
        horario.setEstado(estado);
        assertEquals(estado, horario.getEstado());
    }


}

