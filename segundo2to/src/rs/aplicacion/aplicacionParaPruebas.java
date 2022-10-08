package aplicacion;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import datastructure.TreeMap;
import datos.CargarParametros;
import datos.Dato;
import interfaz.Interfaz;
import logica.Calculo;
import modelo.Relacion;
import modelo.Usuario;

public class AplicacionParaPruebas {

	public static void main(String[] args) {
		// Cargar parametros
		try {
			CargarParametros.parametros();
		} catch (IOException e) {
			System.err.print("Error al cargar parametros");
			System.exit(-1);
		}

		// Cargar datos
		TreeMap<String, Usuario> usuarios = null;

		List<Relacion> relaciones = null;
		try {
			usuarios = Dato.cargarUsuarios(CargarParametros.getArchivoUsuario());

			relaciones = Dato.cargarRelaciones(CargarParametros.getArchivoRelacion(), usuarios);

		} catch (FileNotFoundException e) {
			System.err.print("Error al cargar archivos de datos");
			System.exit(-1);
		}

		Calculo calculo = new Calculo(usuarios, relaciones);
		
		Usuario u1 = usuarios.get("R3234");
		Usuario u2 = usuarios.get("B3434");
		
		System.out.println("usuario 1: " + u1.toString());
		System.out.println("usuario 2: " + u2.toString());
		System.out.println("cantidad amigos u1: " + calculo.cantidadAmigos(u1));
		System.out.println("cantidad amigos u2: " + calculo.cantidadAmigos(u2));
		System.out.println("anios amistad u1 y u2: " + calculo.tiempoDeAmistad(u1, u2) + " anios");
		System.out.println("escribir amigos de u1: " + calculo.amigosDe(u1));
		System.out.println("");
		System.out.println("escribir amigos de u2: " + calculo.amigosDe(u2));
		System.out.println("escribir usuario más influyente: " + calculo.masInfluyente().toString());
		
		//Interfaz interfaz = new Interfaz(usuarios);

		
		
		//mostrarInterfaz(calculo, interfaz);

	}

	private static void mostrarInterfaz(Calculo calculo, Interfaz interfaz) {

		switch (interfaz.menu()) {
		case 1:
			interfaz.mostrarGradoPromedio(calculo.gradoPromedio());
			break;
		case 2:
			interfaz.mostrarMasInfluyentes(calculo.masInfluyentes());
			break;
		case 3:
			interfaz.getUsuarios();
			List<String> ids = interfaz.elegirUsuarios();
			try {
				interfaz.mostrarCaminoMasNuevo(calculo.caminoMasNuevo(ids.get(0), ids.get(1)));
			} catch (IllegalArgumentException e) {
				System.out.println("Error al cargar los nombres");
			}
			break;
		}

		if (interfaz.continuar() == 1)
			mostrarInterfaz(calculo, interfaz);

	}

}
