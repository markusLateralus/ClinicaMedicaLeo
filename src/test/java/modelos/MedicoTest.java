package modelos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class MedicoTest {
    private Medico medico;

    @BeforeAll
    public void setUp() {
        medico = new Medico();
    }

    //1
    @Test
    public void testSetYGetId() {
        medico.setId(1);
        assertEquals(1, medico.getId());
    }

    //2
    @Test
    public void testSetYGetUsername() { 
    	String username = "medico123";
    	medico.setUsername(username);
    	assertEquals(username, medico.getUsername());
    	} 
    //3
    @Test
    public void testSetYGetPassword() {
    	String password = "password123";
    	medico.setPassword(password); 
    	assertEquals(password, medico.getPassword());
    	} 
    //4
    @Test 
    public void testSetYGetDni() { 
    	String dni = "12345678A";
    	medico.setDni(dni); 
    	assertEquals(dni, medico.getDni());
    	}
    
    //6
    @Test
    public void testSetYGetApellido1() {
        String apellido1 = "Mejicano";
        medico.setNombre(apellido1);
        assertEquals(apellido1, medico.getApellido1());
    }
    
    //7
    @Test
    public void testSetYGetApellido2() {
        String apellido2 = "Rubiales";
        medico.setNombre(apellido2);
        assertEquals(apellido2, medico.getApellido2());
    }
    
//8
    @Test
    public void testSetYGetEmail() {
    	String email = "medico@example.com"; 
    	medico.setEmail(email);
    	assertEquals(email, medico.getEmail());
    	} 
    //9
    @Test 
    public void testSetYGetTelefono() { 
    	String telefono = "600123456";
    	medico.setTelefono(telefono);
    	assertEquals(telefono, medico.getTelefono()); 
    	} 
    
    //10
    @Test 
    public void testSetYGetFechaNacimiento() { 
    	Date fechaNacimiento = new Date();
    	medico.setFechaNacimiento(fechaNacimiento); 
    	assertEquals(fechaNacimiento, medico.getFechaNacimiento());
    	}

    //11
    @Test 
    public void testSetYGetEspecialidad() { 
    	String especialidad = "psiquiatria";
    	medico.setEspecialidad(especialidad); 
    	assertEquals(especialidad, medico.getEspecialidad());
    	}



}

