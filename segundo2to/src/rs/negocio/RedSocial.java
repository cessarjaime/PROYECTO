package rs.negocio;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import rs.conexion.AConnection;
import rs.modelo.Relacion;
import rs.modelo.Usuario;
import rs.servicio.RelacionService;
import rs.servicio.RelacionServiceImpl;
import rs.servicio.UsuarioService;
import rs.servicio.UsuarioServiceImpl;

public class RedSocial {
	final static Logger logger = Logger.getLogger(RedSocial.class);
	private UsuarioService usuarioService;
	private RelacionService relacionService;
	List<Usuario> usuarios;
	List<Relacion> relaciones;

	public RedSocial() {
		usuarios = new ArrayList<>();
		usuarioService = new UsuarioServiceImpl();
		usuarios.addAll(usuarioService.buscarTodos());
		relaciones = new ArrayList<>();
		relacionService = new RelacionServiceImpl();
		relaciones.addAll(relacionService.buscarTodos());
	}

	public void agregarUsuario(Usuario usuario) {
		usuarioService.insertar(usuario);
		logger.info("Se agrego el usuario "+ usuario.getId()+" al archivo");
	}

	public void agregarRelacion(Relacion relacion) {
		relacionService.insertar(relacion);
		logger.info("Se agrego la relacion "+ relacion.getUsuario1().getId() 
				+" y "+relacion.getUsuario2().getId()+" al archivo");
	}

	public void modificarUsuario(Usuario usuario) {
		usuarioService.actualizar(usuario);
		logger.info("Se modifico el usuario "+ usuario.getId()+" del archivo");
	}

	public void modificarRelacion(Relacion relacion) {
		relacionService.actualizar(relacion);
		logger.info("Se modifco la relacion "+ relacion.getUsuario1().getId() 
				+" y "+relacion.getUsuario2().getId()+" del archivo");
	}

	public void borrarUsuario(Usuario usuario) {
		usuarioService.borrar(usuario);
		logger.info("Se borro el usuario "+ usuario.getId()+" del archivo");
	}

	public void borrarRelacion(Relacion relacion) {
		relacionService.borrar(relacion);
		logger.info("Se borro la relacion "+ relacion.getUsuario1().getId() 
				+" y "+relacion.getUsuario2().getId()+" del archivo");
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<Relacion> getRelaciones() {
		return relaciones;
	}

	public void setRelaciones(List<Relacion> relaciones) {
		this.relaciones = relaciones;
	}

}
