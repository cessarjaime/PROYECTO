package rs.dao.secuencial;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;
import java.util.Scanner;

import rs.dao.UsuarioDAO;
import rs.modelo.Gender;
import rs.modelo.Usuario;

public class UsuarioSecuencialDAO implements UsuarioDAO {

	private List<Usuario> list;
	private String name;

	public UsuarioSecuencialDAO() {
		
		ResourceBundle rb = ResourceBundle.getBundle("secuencial");
		name = rb.getString("usuarios");
		list = readFromFile(name);
	}

	private List<Usuario> readFromFile(String file) {
		List<Usuario> list = new ArrayList<>();
		Scanner inFile = null;
		try {
			inFile = new Scanner(new File(file));
			inFile.useDelimiter("\\s*,\\s*");
			while (inFile.hasNext()) {
				Usuario u = new Usuario();
			
				u.setId(inFile.next());			
				u.setNombre(inFile.next());
				u.setGenero(Gender.valueOf(inFile.next()));
				u.setCiudad(inFile.next());	
				u.setFechaNacimiento(
						LocalDate.of(inFile.nextInt(), inFile.nextInt(), inFile.nextInt()));
				u.setEstadoCivil(inFile.next());
				u.setNivelAcademico(inFile.next());
				list.add(u);
			}
		} catch (FileNotFoundException fileNotFoundException) {
			System.err.println("Error opening file.");
			fileNotFoundException.printStackTrace();
		} catch (NoSuchElementException noSuchElementException) {
			System.err.println("Error in file record structure");
			noSuchElementException.printStackTrace();
		} catch (IllegalStateException illegalStateException) {
			System.err.println("Error reading from file.");
			illegalStateException.printStackTrace();
		} finally {
			if (inFile != null)
				inFile.close();
		}
		return list;
	}

	private void writeToFile(List<Usuario> list, String file) {
		Formatter outFile = null;
		try {
			outFile = new Formatter(file);
			for (Usuario u : list) {
				outFile.format("%s,%s,%s,%s,%d,%d,%d,%s,%s,\n", u.getId(), u.getNombre(), u.getGenero().toString(),u.getCiudad(),
						u.getFechaNacimiento().getYear(), u.getFechaNacimiento().getMonthValue(), u.getFechaNacimiento().getDayOfMonth(),
						u.getEstadoCivil(),u.getNivelAcademico());
			}
		} catch (FileNotFoundException fileNotFoundException) {
			System.err.println("Error creating file.");
		} catch (FormatterClosedException formatterClosedException) {
			System.err.println("Error writing to file.");
		} finally {
			if (outFile != null)
				outFile.close();
		}
	}

	@Override
	public List<Usuario> buscarTodos() {
		return list;
	}

	@Override
	public void insertar(Usuario usuario) {
		list.add(usuario);
		writeToFile(list, name);
	}

	@Override
	public void actualizar(Usuario usuario) {
		int pos = list.indexOf(usuario);
		list.set(pos, usuario);
		writeToFile(list, name);
	}

	@Override
	public void borrar(Usuario usuario) {
		list.remove(usuario);
		writeToFile(list, name);
	}

}
