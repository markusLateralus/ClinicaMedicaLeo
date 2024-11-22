package modelos;

import java.sql.Timestamp;

public class Notificacion {

    private int id;
    private int idPaciente;
    private int idMedico;
    private String mensaje;
    private String estado; // "activo" o "inactivo"
    private Timestamp fechaCreacion;
	public Notificacion(int id, int idPaciente, int idMedico, String mensaje, String estado, Timestamp fechaCreacion) {
		super();
		this.id = id;
		this.idPaciente = idPaciente;
		this.idMedico = idMedico;
		this.mensaje = mensaje;
		this.estado = estado;
		this.fechaCreacion = fechaCreacion;
	}
	public Notificacion() {
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdPaciente() {
		return idPaciente;
	}
	public void setIdPaciente(int idPaciente) {
		this.idPaciente = idPaciente;
	}
	public int getIdMedico() {
		return idMedico;
	}
	public void setIdMedico(int idMedico) {
		this.idMedico = idMedico;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Timestamp getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
    
    
    
}
