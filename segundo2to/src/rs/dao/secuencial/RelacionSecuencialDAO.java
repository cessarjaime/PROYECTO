package rs.dao.secuencial;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.TreeMap;

import rs.dao.RelacionDAO;
import rs.dao.UsuarioDAO;
import rs.modelo.Relacion;
import rs.modelo.Usuario;

public class RelacionSecuencialDAO implements RelacionDAO {

	private List<Relacion> list;
	private String name;
	TreeMap<String, Usuario> usuarios;

	public RelacionSecuencialDAO() {
		
		ResourceBundle rb = ResourceBundle.getBundle("secuencial");
		name = rb.getString("relaciones");
	    usuarios=cargaUsuarios();
		list = readFromFile(name);
	}

	private List<Relacion> readFromFile(String file) {
		List<Relacion> list = new ArrayList<>();
		Scanner inFile = null;
		try {
			inFile = new Scanner(new File(file));
			inFile.useDelimiter("\\s*,\\s*");
			while (inFile.hasNext()) {
				Relacion r = new Relacion();
				
				r.setUsuario1(usuarios.get(inFile.next()));
				r.setUsuario2(usuarios.get(inFile.next()));
				r.setInteraccion(inFile.nextInt());
				r.setLikes(inFile.nextInt());
				r.setFechaAmistad(LocalDate.of(inFile.nextInt(), inFile.nextInt(), inFile.nextInt()));
				list.add(r);
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

	private void writeToFile(List<Relacion> list, String file) {
		Formatter outFile = null;
	
		try {
			outFile = new Formatter(file);
			for (Relacion r : list) {

				outFile.format("%s,%s,%d,%d,%d,%d,%d,\n", r.getUsuario1().getId(), r.getUsuario2().getId(), r.getInteraccion(),
				r.getLikes(),r.getFechaAmistad().getYear(),r.getFechaAmistad().getMonthValue(),r.getFechaAmistad().getDayOfMonth());
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
	public List<Relacion> buscarTodos() {
		return list;
	}

	@Override
	public void insertar(Relacion relacion) {
		list.add(relacion);
		writeToFile(list, name);
	}

	@Override
	public void actualizar(Relacion relacion) {
		int pos = list.indexOf(relacion);
		list.set(pos, relacion);
		writeToFile(list, name);
	}

	@Override
	public void borrar(Relacion relacion) {
		list.remove(relacion);
		writeToFile(list, name);
	}
	private TreeMap<String, Usuario> cargaUsuarios() {
		TreeMap<String, Usuario> usuariosM =new TreeMap<String, Usuario>();
		UsuarioDAO usuariosDAO=new UsuarioSecuencialDAO();
		List<Usuario> us=usuariosDAO.buscarTodos();
		for(Usuario u:us)
			usuariosM.put(u.getId(), u);
		return usuariosM;
		
	}

}
