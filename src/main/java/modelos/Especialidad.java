package modelos;


public class Especialidad {
    public int id;
    public String nombre_especialidad;

    public Especialidad(int id2, String nombre2) {
		this.id=id2;
		this.nombre_especialidad=nombre2;
	}
	public Especialidad() {
		// TODO Auto-generated constructor stub
	}
	// Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombreEspecialiad() { return nombre_especialidad; }
    public void setNombreEspecialidad(String nombre) { this.nombre_especialidad = nombre; }
}
