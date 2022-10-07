package modelo;

import java.time.LocalDate;
import java.util.Objects;

public class Relacion {

	private Usuario usuario1;
	private Usuario usuario2;
	private int interaccion;
	private int likes;
	private LocalDate fechaAmistad;

	public Relacion(Usuario usuario1, Usuario usuario2, int interaccion, int likes, LocalDate fechaAmistad) {
		super();
		this.usuario1 = usuario1;
		this.usuario2 = usuario2;
		this.interaccion = interaccion;
		this.likes = likes;
		this.fechaAmistad = fechaAmistad;
	}

	public Usuario getUsuario1() {
		return usuario1;
	}

	public void setUsuario1(Usuario usuario1) {
		this.usuario1 = usuario1;
	}

	public Usuario getUsuario2() {
		return usuario2;
	}

	public void setUsuario2(Usuario usuario2) {
		this.usuario2 = usuario2;
	}

	public int getInteraccion() {
		return interaccion;
	}

	public void setInteraccion(int interaccion) {
		this.interaccion = interaccion;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public int getTiempoAmistad() {
		int anios = LocalDate.now().getYear() - fechaAmistad.getYear();
		int meses = LocalDate.now().getMonthValue() - fechaAmistad.getMonthValue();
		int dias = LocalDate.now().getDayOfMonth() - fechaAmistad.getDayOfMonth();
		if (meses < 0 || (meses == 0 && dias < 0)) {
			anios--;
		}
		return anios;
	}

	@Override
	public String toString() {
		return "Relacion [" + usuario1 + " " + usuario2 + " " + interaccion + " " + likes + " " + getTiempoAmistad()
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(usuario1, usuario2);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Relacion other = (Relacion) obj;
		if ((Objects.equals(usuario1, other.usuario1) && Objects.equals(usuario2, other.usuario2)))
			return true;
		return Objects.equals(usuario1, other.usuario2) && Objects.equals(usuario2, other.usuario1);
	}

}
