package rs.aplicacion;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;

import net.datastructures.TreeMap;
import rs.datos.CargarParametros;
import rs.datos.Dato;
import rs.interfaz.Interfaz;
import rs.logica.Calculo;
import rs.modelo.Relacion;
import rs.modelo.Usuario;

public class Aplicacion {

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
		Interfaz interfaz = new Interfaz(usuarios);

		mostrarInterfaz(calculo, interfaz);

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
			interfaz.mostrarCaminoMasNuevo(calculo.caminoMasNuevo(ids.get(0),ids.get(1)));
			} catch (IllegalArgumentException e) {
				System.out.println("Error al cargar los nombres");
			}
			break;
			}
		

      if(interfaz.continuar()==1) 	  
    	  mostrarInterfaz(calculo,interfaz);
     
	}
}
