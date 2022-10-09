package rs.aplicacion;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import net.datastructures.TreeMap;
import rs.datos.CargarParametros;
import rs.datos.Dato;
import rs.gui.DesktopFrame;
import rs.gui.RelacionesForm;
import rs.gui.RelacionesList;
import rs.gui.UsuariosForm;
import rs.gui.UsuariosList;
import rs.logica.Calculo;
import rs.modelo.Relacion;
import rs.modelo.Usuario;

public class Aplicacion {

	private Coordinador coordinador;
    private Calculo calculo;
	private Dato dato;
	private DesktopFrame desktopFrame;
	private UsuariosForm usuariosForm;
	private UsuariosList usuariosList;
	private RelacionesForm relacionesForm;
	private RelacionesList relacionesList;

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
		
		desktopFrame.setCoordinador(coordinador);
		usuariosForm.setCoordinador(coordinador);
		usuariosList.setCoordinador(coordinador);
		relacionesForm.setCoordinador(coordinador);
		relacionesList.setCoordinador(coordinador);
		
		coordinador.setCalculo(calculo);
		coordinador.setDato(dato);
		coordinador.setDesktopFrame(desktopFrame);
		coordinador.setUsuariosForm(usuariosForm);
		coordinador.setUsuariosList(usuariosList);
		coordinador.setRelacionesForm(relacionesForm);
		coordinador.setRelacionesList(relacionesList);
		
		desktopFrame.setVisible(true);

	}

}
