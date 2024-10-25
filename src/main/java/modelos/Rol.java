package modelos;


public class Rol {
    public int id;
    public String nombre_rol;

    public Rol(int id2, String nombre2) {
		this.id=id2;
		this.nombre_rol=nombre2;
	}
	public Rol() {
		// TODO Auto-generated constructor stub
	}
	// Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombreRol() { return nombre_rol; }
    public void setNombreRol(String nombre) { this.nombre_rol = nombre; }
}
