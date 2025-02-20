package modelos;
import java.util.Date;
import java.util.List;

public class Administrador {
    private int id;
    private String username;
    private String password;
    private String dni;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String email;
    private String telefono;
    private Date fechaNacimiento;

	public Administrador(int id, String username, String password, String dni, String nombre, String apellido1,
			String apellido2, String email, String telefono, Date fechaNacimiento) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.dni = dni;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.email = email;
		this.telefono = telefono;
		this.fechaNacimiento = fechaNacimiento;
	
	}
    
   

	public Administrador() {
		// TODO Auto-generated constructor stub
	}



	// Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni=dni; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido1() { return apellido1; }
    public void setApellido1(String apellido) { this.apellido1 = apellido; }
    public String getApellido2() { return apellido2; }
    public void setApellido2(String apellido) { this.apellido2 = apellido; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public Date getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(Date fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }


}

