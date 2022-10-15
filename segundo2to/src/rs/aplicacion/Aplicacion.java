package aplicacion;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.TreeMap;


import datos.CargarParametros;
import datos.Dato;
import gui.DesktopFrame;
import gui.MetodosList;
import gui.RelacionesForm;
import gui.RelacionesList;
import gui.UsuariosForm;
import gui.UsuariosList;
import gui.UsuariosMetodoForm;
import logica.Calculo;
import modelo.Relacion;
import modelo.Usuario;

public class Aplicacion {

	private Coordinador coordinador;
    private Calculo calculo;
	private Dato dato;
	private DesktopFrame desktopFrame;
	private UsuariosForm usuariosForm;
	private UsuariosList usuariosList;
	private RelacionesForm relacionesForm;
	private RelacionesList relacionesList;
	private MetodosList metodosList;
	private UsuariosMetodoForm usuariosMetodoForm;

	public static void main(String[] args) {
		Aplicacion miAplicacion = new Aplicacion();
		miAplicacion.iniciar();

	}

	private void iniciar() {
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

		calculo = new Calculo(usuarios, relaciones);

		coordinador = new Coordinador();
		dato = new Dato(CargarParametros.getArchivoUsuario(), CargarParametros.getArchivoRelacion());
		desktopFrame = new DesktopFrame();
		usuariosForm = new UsuariosForm();
		usuariosList = new UsuariosList();
		relacionesForm = new RelacionesForm();
		relacionesList = new RelacionesList();
		metodosList= new MetodosList ();
		usuariosMetodoForm = new UsuariosMetodoForm();
		
		desktopFrame.setCoordinador(coordinador);
		usuariosForm.setCoordinador(coordinador);
		usuariosList.setCoordinador(coordinador);
		relacionesForm.setCoordinador(coordinador);
		relacionesList.setCoordinador(coordinador);
		metodosList.setCoordinador(coordinador);
		usuariosMetodoForm.setCoordinador(coordinador);
		
		
		coordinador.setCalculo(calculo);
		coordinador.setDato(dato);
		coordinador.setDesktopFrame(desktopFrame);
		coordinador.setUsuariosForm(usuariosForm);
		coordinador.setUsuariosList(usuariosList);
		coordinador.setRelacionesForm(relacionesForm);
		coordinador.setRelacionesList(relacionesList);
		coordinador.setMetodosList(metodosList);
		coordinador.setUsuariosMetodoForm(usuariosMetodoForm);
		
		desktopFrame.setVisible(true);

	}

}
