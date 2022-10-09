package rs.util;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import rs.modelo.Gender;

public class Validation {

	public static Double isDouble(String str) {
		try {
			return Double.parseDouble(str);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	public static int isInteger(String str) {
		try {
			return Integer.parseInt(str);
		} catch (NumberFormatException e) {
			return -1;
		}
	}

	public static LocalDate isDate(String date) {
		try {
			return LocalDate.parse(date);
		} catch (DateTimeParseException e) {
			return null;
		}
	}

	public static Gender isGenero(String g) {
		try {
			return Gender.valueOf(g);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}
}
