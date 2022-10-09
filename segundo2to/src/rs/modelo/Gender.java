package rs.modelo;

public enum Gender {
	M("mujer"), H("hombre"), NB ("no binario");

	private String genero;

	Gender(String genero) {
		this.genero = genero;
	}

	public String getGenero() {
		return genero;
	}
}
