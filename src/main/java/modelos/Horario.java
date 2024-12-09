package modelos;

public class Horario {

	   private int id;
	    private int medicoId;
	    private int pacienteId;
	    private String dia;
	    private String hora;
	    private String estado;
		public Horario(int id, int medicoId, String dia, String hora, String estado) {
			super();
			this.id = id;
			this.medicoId = medicoId;
			this.dia = dia;
			this.hora = hora;
			this.estado = estado;
		}
		public Horario() {
			// TODO Auto-generated constructor stub
		}
		public void setId(int id) {
			this.id = id;
		}
		public void setMedicoId(int medicoId) {
			this.medicoId = medicoId;
		}
		public void setPacienteId(int pacienteIdd) {
			this.pacienteId = pacienteIdd;
		}
		public void setDia(String dia) {
			this.dia = dia;
		}
		public void setHora(String hora) {
			this.hora = hora;
		}
		public void setEstado(String estado) {
			this.estado = estado;
		}
		public int getId() {
			return id;
		}
		public int getMedicoId() {
			return medicoId;
		}
		public int getPacienteId() {
			return pacienteId;
		}
		public String getDia() {
			return dia;
		}
		public String getHora() {
			return hora;
		}
		public String getEstado() {
			return estado;
		}
	    
	    
}
