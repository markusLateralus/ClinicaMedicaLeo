package modelos;

import java.sql.Timestamp;

public class Notificacion {

    private int id;
    private int idPaciente;
    private int idMedico;
    private String mensajePaciente;
    private String mensajeMedico;
    private String estado; // "activo" o "inactivo"
    private Timestamp fechaCreacion;
	public Notificacion(int id, int idPaciente, int idMedico, String mensajePaciente,String mensajeMedico, String estado, Timestamp fechaCreacion) {
		super();
		this.id = id;
		this.idPaciente = idPaciente;
		this.idMedico = idMedico;
		this.mensajePaciente = mensajePaciente;
		this.mensajeMedico=mensajeMedico;
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
	public String getMensajePaciente() {
		return mensajePaciente;
	}
	public void setMensajePaciente(String mensaje) {
		this.mensajePaciente = mensaje;
	}
	public String getMensajeMedico() {
		return mensajeMedico;
	}
	public void setMensajeMedico(String mensaje) {
		this.mensajeMedico = mensaje;
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
