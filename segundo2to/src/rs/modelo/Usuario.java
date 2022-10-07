package modelo;

import java.time.LocalDate;
import java.util.Objects;

public class Usuario {

	private String id;
	private String nombre;
	private LocalDate fechaNacimiento;
	private Gender genero;
	private String ciudad;
	private String estadoCivil;
	private String nivelAcademico;

	public Usuario(String id, String nombre, Gender genero, String ciudad, LocalDate fechaNacimiento,
			String estadoCivil, String nivelAcademico) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.genero = genero;
		this.ciudad = ciudad;
		this.fechaNacimiento = fechaNacimiento;
		this.estadoCivil = estadoCivil;
		this.nivelAcademico = nivelAcademico;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getEdad() {
		int anios = LocalDate.now().getYear() - fechaNacimiento.getYear();
		int meses = LocalDate.now().getMonthValue() - fechaNacimiento.getMonthValue();
		int dias = LocalDate.now().getDayOfMonth() - fechaNacimiento.getDayOfMonth();
		if (meses < 0 || (meses == 0 && dias < 0)) {
			anios--;
		}
		return anios;
	}

	public enum genero {
		hombre, mujer;
	}

	public Gender getGenero() {
		return genero;
	}

	public void setGenero(Gender genero) {
		this.genero = genero;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(id, other.id);
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getNivelAcademico() {
		return nivelAcademico;
	}

	public void setNivelAcademico(String nivelAcademico) {
		this.nivelAcademico = nivelAcademico;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", fechaNacimiento=" + fechaNacimiento + ", edad="
				+ getEdad() + ", genero=" + genero.getGenero() + ", ciudad=" + ciudad + ", estadoCivil=" + estadoCivil
				+ ", nivelAcademico=" + nivelAcademico + "]";
	}

}
