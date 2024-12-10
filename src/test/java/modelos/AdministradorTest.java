package modelos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class AdministradorTest {
    private Administrador administrador;

    @BeforeAll
    public void setUp() {
        administrador = new Administrador();
    }

    //1
    @Test
    public void testSetYGetId() {
        administrador.setId(1);
        assertEquals(1, administrador.getId());
    }

    //2
    @Test
    public void testSetYGetUsername() { 
    	String username = "admin123";
    	administrador.setUsername(username);
    	assertEquals(username, administrador.getUsername());
    	} 
    //3
    @Test
    public void testSetYGetPassword() {
    	String password = "password123";
    	administrador.setPassword(password); 
    	assertEquals(password, administrador.getPassword());
    	} 
    //4
    @Test 
    public void testSetYGetDni() { 
    	String dni = "12345678A";
    	administrador.setDni(dni); 
    	assertEquals(dni, administrador.getDni());
    	}
    
    //6
    @Test
    public void testSetYGetApellido1() {
        String apellido1 = "Suarez";
        administrador.setNombre(apellido1);
        assertEquals(apellido1, administrador.getApellido1());
    }
    
    //7
    @Test
    public void testSetYGetApellido2() {
        String apellido2 = "Monasterio";
        administrador.setNombre(apellido2);
        assertEquals(apellido2, administrador.getApellido2());
    }
    
//8
    @Test
    public void testSetYGetEmail() {
    	String email = "admin@example.com"; 
    	administrador.setEmail(email);
    	assertEquals(email, administrador.getEmail());
    	} 
    //9
    @Test 
    public void testSetYGetTelefono() { 
    	String telefono = "600123456";
    	administrador.setTelefono(telefono);
    	assertEquals(telefono, administrador.getTelefono()); 
    	} 
    
    //10
    @Test 
    public void testSetYGetFechaNacimiento() { 
    	Date fechaNacimiento = new Date();
    	administrador.setFechaNacimiento(fechaNacimiento); 
    	assertEquals(fechaNacimiento, administrador.getFechaNacimiento());
    	}




}

