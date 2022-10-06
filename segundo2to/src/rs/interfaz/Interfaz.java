package rs.interfaz;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import net.datastructures.Entry;
import net.datastructures.TreeMap;
import rs.modelo.Usuario;

public class Interfaz {

	private TreeMap<String, Usuario> usuarios;
	private Scanner input;

	public Interfaz(TreeMap<String, Usuario> usuarios) {
		this.usuarios = usuarios;
		input = new Scanner(System.in);
	}

	public void mostrarGradoPromedio(double gradopromedio) {
		System.out.println("El grado promedio es=" + gradopromedio + "\n");
	}

	public void mostrarMasInfluyentes(List<Usuario> usuarios) {
		System.out.println("Usuarios mas influyentes ");
		for (Usuario u : usuarios)
			System.out.println(u);
		System.out.println();
	}

	public void mostrarCaminoMasNuevo(List<Usuario> list) {
		
		System.out.print("El camino mas nuevo entre "
       +list.get(0).getNombre()+" Y "+list.get(list.size()-1).getNombre()+" es:");
		
		for (Usuario u: list)
			System.out.println(u);	
    }

	public int menu() {

		System.out.println("Eliga una opcion");
		System.out.println("1=El grado promedio");
		System.out.println("2=Los Usuarios mas influyentes");
		System.out.println("3=El camino mas nuevo entre dos usuarios");

		return input.nextInt();
	}

	public void getUsuarios() {

		System.out.println("Lista de usuarios:");
		for (Entry<String, Usuario> u : usuarios.entrySet()) {
			System.out.print("Nombre=" + u.getValue().getNombre());
			System.out.println("    id=" + u.getKey());
		}

	}

	public List<String> elegirUsuarios() {
		input = new Scanner(System.in);
		List<String> l = new ArrayList<>(2);
		System.out.print("Escriba el id del primer usuario:");
		l.add(input.nextLine());
		System.out.print("Escriba el id del segundo usuario:");
		l.add(input.nextLine());

		return l;
	}

	public int continuar() {

		System.out.println("1=Volver al menu");
		System.out.println("2=salir");

		return input.nextInt();
	}

}
