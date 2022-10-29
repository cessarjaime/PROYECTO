package rs.controlador;

import org.apache.log4j.Logger;

import rs.conexion.AConnection;
import rs.gui.DesktopFrame;
import rs.gui.UsuariosListResult;
import rs.gui.RelacionesForm;
import rs.gui.RelacionesList;
import rs.gui.UsuariosForm;
import rs.gui.UsuariosList;
import rs.gui.UsuariosFormConsultas;
import rs.negocio.Calculo;
import rs.negocio.RedSocial;

public class Aplicacion {
	
	final static Logger logger = Logger.getLogger(Aplicacion.class);
	
    private RedSocial redSocial;
	private Coordinador coordinador;
	private Calculo calculo;
	private DesktopFrame desktopFrame;
	private UsuariosForm usuariosForm;
	private UsuariosList usuariosList;
	private RelacionesForm relacionesForm;
	private RelacionesList relacionesList;
	private UsuariosListResult usuariosListResult;
	private UsuariosFormConsultas usuariosFormConsultas;

	public static void main(String[] args) {
		Aplicacion miAplicacion = new Aplicacion();
		miAplicacion.iniciar();

	}

	private void iniciar() {
		
		redSocial=new RedSocial();
		calculo = new Calculo(redSocial.getUsuarios(),redSocial.getRelaciones());
		coordinador = new Coordinador();
		desktopFrame = new DesktopFrame();
		usuariosForm = new UsuariosForm();
		usuariosList = new UsuariosList();
		relacionesForm = new RelacionesForm();
		relacionesList = new RelacionesList();
		usuariosListResult = new UsuariosListResult();
		usuariosFormConsultas = new UsuariosFormConsultas();

		desktopFrame.setCoordinador(coordinador);
		usuariosForm.setCoordinador(coordinador);
		usuariosList.setCoordinador(coordinador);
		relacionesForm.setCoordinador(coordinador);
		relacionesList.setCoordinador(coordinador);
		usuariosListResult.setCoordinador(coordinador);
		usuariosFormConsultas.setCoordinador(coordinador);
		
        coordinador.setRedSocial(redSocial);
		coordinador.setCalculo(calculo);
		coordinador.setDesktopFrame(desktopFrame);
		coordinador.setUsuariosForm(usuariosForm);
		coordinador.setUsuariosList(usuariosList);
		coordinador.setRelacionesForm(relacionesForm);
		coordinador.setRelacionesList(relacionesList);
		coordinador.setUsuariosListResult(usuariosListResult);
		coordinador.setUsuariosFormConsultas(usuariosFormConsultas);
		
		logger.debug("Iniciando Aplicacion");
		
		desktopFrame.setVisible(true);
		
		

	}

}
