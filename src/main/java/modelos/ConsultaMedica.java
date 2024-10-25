package modelos;

import java.util.Date;

public class ConsultaMedica {
    private int id;
    private Administrador medico;
    private Administrador paciente;
    private Date fechaConsulta;
    private String horaInicio;
    private String horaFin;
    private boolean disponible;

    public ConsultaMedica(int id, Administrador medico, Administrador paciente, Date fechaConsulta, String horaInicio, String horaFin, boolean disponible) {
        this.id = id;
        this.medico = medico;
        this.paciente = paciente;
        this.fechaConsulta = fechaConsulta;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.disponible = disponible;
    }

    public ConsultaMedica() {
		// TODO Auto-generated constructor stub
	}

	// Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Administrador getMedico() { return medico; }
    public void setMedico(Administrador medico) { this.medico = medico; }

    public Administrador getPaciente() { return paciente; }
    public void setPaciente(Administrador paciente) { this.paciente = paciente; }

    public Date getFechaConsulta() { return fechaConsulta; }
    public void setFechaConsulta(Date fechaConsulta) { this.fechaConsulta = fechaConsulta; }

    public String getHoraInicio() { return horaInicio; }
    public void setHoraInicio(String horaInicio) { this.horaInicio = horaInicio; }

    public String getHoraFin() { return horaFin; }
    public void setHoraFin(String horaFin) { this.horaFin = horaFin; }

    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }
}
