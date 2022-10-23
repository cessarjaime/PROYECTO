package util;

import java.time.LocalDate;

public class Calendario {

	public Calendario () {		
	}
	
	public static int getTiempo (LocalDate fechaNacimiento) {
		int anios = LocalDate.now().getYear() - fechaNacimiento.getYear();
		int meses = LocalDate.now().getMonthValue() - fechaNacimiento.getMonthValue();
		int dias = LocalDate.now().getDayOfMonth() - fechaNacimiento.getDayOfMonth();
		if (meses < 0 || (meses == 0 && dias < 0)) {
			anios--;
		}
		return anios;
	}	
}
