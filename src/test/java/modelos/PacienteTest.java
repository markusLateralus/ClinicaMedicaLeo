package modelos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class PacienteTest {
    private Paciente paciente;

    @BeforeAll
    public void setUp() {
        paciente = new Paciente();
    }

    //1
    @Test
    public void testSetYGetId() {
        paciente.setId(1);
        assertEquals(1, paciente.getId());
    }

    //2
    @Test
    public void testSetYGetUsername() { 
    	String username = "medico123";
    	paciente.setUsername(username);
    	assertEquals(username, paciente.getUsername());
    	} 
    //3
    @Test
    public void testSetYGetPassword() {
    	String password = "password123";
    	paciente.setPassword(password); 
    	assertEquals(password, paciente.getPassword());
    	} 
    //4
    @Test 
    public void testSetYGetDni() { 
    	String dni = "12345678A";
    	paciente.setDni(dni); 
    	assertEquals(dni, paciente.getDni());
    	}
    
    //6
    @Test
    public void testSetYGetApellido1() {
        String apellido1 = "Pérez";
        paciente.setNombre(apellido1);
        assertEquals(apellido1, paciente.getApellido1());
    }
    
    //7
    @Test
    public void testSetYGetApellido2() {
        String apellido2 = "Fernandez";
        paciente.setNombre(apellido2);
        assertEquals(apellido2, paciente.getApellido2());
    }
    
//8
    @Test
    public void testSetYGetEmail() {
    	String email = "admin@example.com"; 
    	paciente.setEmail(email);
    	assertEquals(email, paciente.getEmail());
    	} 
    //9
    @Test 
    public void testSetYGetTelefono() { 
    	String telefono = "600123456";
    	paciente.setTelefono(telefono);
    	assertEquals(telefono, paciente.getTelefono()); 
    	} 
    
    //10
    @Test 
    public void testSetYGetFechaNacimiento() { 
    	Date fechaNacimiento = new Date();
    	paciente.setFechaNacimiento(fechaNacimiento); 
    	assertEquals(fechaNacimiento, paciente.getFechaNacimiento());
    	}




}

