package datos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;
import java.time.LocalDate;
import modelo.Gender;
import modelo.Relacion;
import modelo.Usuario;

public class Dato {
	FileWriter fw;
	PrintWriter escribir;
	File archivoU;
	File archivoR;

	public Dato(String fileName, String fileName1) {
		archivoU = new File(fileName);
		archivoR = new File(fileName1);
	}

	public static TreeMap<String, Usuario> cargarUsuarios(String fileName) throws FileNotFoundException {
		Scanner read;

		TreeMap<String, Usuario> usuarios = new TreeMap<String, Usuario>();

		read = new Scanner(new File(fileName));
		read.useDelimiter("\\s*,\\s*");
		String id, nombre, ciudad, estadoCivil, nivelAcademico, genero;
		int anio, mes, dia;

		while (read.hasNext()) {
			id = read.next();
			nombre = read.next();
			genero = read.next();
			ciudad = read.next();
			anio = read.nextInt();
			mes = read.nextInt();
			dia = read.nextInt();
			estadoCivil = read.next();
			nivelAcademico = read.next();
			usuarios.put(id, new Usuario(id, nombre, Gender.valueOf(genero), ciudad, LocalDate.of(anio, mes, dia),
					estadoCivil, nivelAcademico));
		}
		read.close();

		return usuarios;
	}

	public static List<Relacion> cargarRelaciones(String fileName, TreeMap<String, Usuario> usuarios)
			throws FileNotFoundException {
		Scanner read;
		List<Relacion> relaciones = new ArrayList<Relacion>();

		read = new Scanner(new File(fileName));
		read.useDelimiter("\\s*,\\s*");
		Usuario usr1, usr2;
		int interaccion, likes, anioAmistad, mesAmistad, diaAmistad;

		while (read.hasNext()) {
			usr1 = usuarios.get(read.next());
			usr2 = usuarios.get(read.next());
			interaccion = read.nextInt();
			likes = read.nextInt();
			anioAmistad = read.nextInt();
			mesAmistad = read.nextInt();
			diaAmistad = read.nextInt();
			relaciones.add(0,
					new Relacion(usr1, usr2, interaccion, likes, LocalDate.of(anioAmistad, mesAmistad, diaAmistad)));
		}
		read.close();

		return relaciones;
	}

	public void escribirUsuarios(Usuario usuario) {

		try {
			fw = new FileWriter(archivoU, true);
			escribir = new PrintWriter(fw);
			escribir.print(usuario.getId() + "," + usuario.getNombre() + "," + usuario.getGenero() + ","
					+ usuario.getCiudad() + "," + usuario.getFechaNacimiento().getYear() + ","
					+ usuario.getFechaNacimiento().getMonthValue() + "," + usuario.getFechaNacimiento().getDayOfMonth()
					+ "," + usuario.getEstadoCivil() + "," + usuario.getNivelAcademico() + "," + "\n");
			escribir.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void escribirRelaciones(Relacion relacion) {

		try {
			fw = new FileWriter(archivoR, true);
			escribir = new PrintWriter(fw);
			escribir.print(relacion.getUsuario1().getId() + "," + relacion.getUsuario2().getId() + ","
					+ relacion.getInteraccion() + "," + relacion.getLikes() + "," + relacion.getFechaAmistad().getYear()
					+ "," + relacion.getFechaAmistad().getMonthValue() + ","
					+ relacion.getFechaAmistad().getDayOfMonth() + "," + "\n");
			escribir.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
