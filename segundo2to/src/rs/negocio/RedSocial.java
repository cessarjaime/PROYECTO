package rs.negocio;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import rs.modelo.Relacion;
import rs.modelo.Usuario;
import rs.servicio.RelacionService;
import rs.servicio.RelacionServiceImpl;
import rs.servicio.UsuarioService;
import rs.servicio.UsuarioServiceImpl;

/**
 * Clase gestion de la red social
 * @author Camacho, Cristian; Jaime, Cesar
 *
 */
public class RedSocial {
	
	final static Logger logger = Logger.getLogger(RedSocial.class);
	private UsuarioService usuarioService;
	private RelacionService relacionService;
	private List<Usuario> usuarios;
	private List<Relacion> relaciones;
	private Subject subject;

	/**
	 * Constructor de red social
	 * @param subject observador
	 */
	public RedSocial(Subject subject) {
		this.subject=subject;
		usuarios = new ArrayList<>();
		usuarioService = new UsuarioServiceImpl();
		usuarios.addAll(usuarioService.buscarTodos());
		relaciones = new ArrayList<>();
		relacionService = new RelacionServiceImpl();
		relaciones.addAll(relacionService.buscarTodos());
	}

	/**
	 * agrega usuario a la red
	 * @param usuario
	 */
	public void agregarUsuario(Usuario usuario) {
		usuarioService.insertar(usuario);
		usuarios.add(usuario);
		subject.refresh();
		logger.info("Se agrego el usuario "+ usuario.getId()+" al archivo");
	}

	/**
	 * agrega relacion a la red
	 * @param relacion
	 */
	public void agregarRelacion(Relacion relacion) {
		relacionService.insertar(relacion);
		relaciones.add(relacion);
		subject.refresh();
		logger.info("Se agrego la relacion "+ relacion.getUsuario1().getId() 
				+" y "+relacion.getUsuario2().getId()+" al archivo");
	}

	/**
	 * modifica usuario de la red
	 * @param usuario
	 */
	public void modificarUsuario(Usuario usuario) {
		usuarioService.actualizar(usuario);
		usuarios.remove(usuario);
		usuarios.add(usuario);
		subject.refresh();
		logger.info("Se modifico el usuario "+ usuario.getId()+" del archivo");
	}

	/**
	 * modifica relacion de la red
	 * @param relacion
	 */
	public void modificarRelacion(Relacion relacion) {
		relacionService.actualizar(relacion);
		relaciones.remove(relacion);
		relaciones.add(relacion);
		subject.refresh();
		logger.info("Se modifco la relacion "+ relacion.getUsuario1().getId() 
				+" y "+relacion.getUsuario2().getId()+" del archivo");
	}

	/**
	 * borra usuario de la red
	 * @param usuario
	 */
	public void borrarUsuario(Usuario usuario) {
		usuarioService.borrar(usuario);
		
	    List<Relacion> relacionesBorrar=new ArrayList<>(relaciones);
		for(Relacion r:relacionesBorrar)
			if(r.getUsuario1().equals(usuario)|| r.getUsuario2().equals(usuario))
				borrarRelacion(r);
		
		usuarios.remove(usuario);	
		subject.refresh();
		
		logger.info("Se borro el usuario "+ usuario.getId()+" del archivo");
	}

	/**
	 * borra relacion de la red
	 * @param relacion
	 */
	public void borrarRelacion(Relacion relacion) {
		relacionService.borrar(relacion);
		relaciones.remove(relacion);
		subject.refresh();
		logger.info("Se borro la relacion "+ relacion.getUsuario1().getId() 
				+" y "+relacion.getUsuario2().getId()+" del archivo");
	}
	
	/**
	 * busca usuario de la red
	 * @param usuario
	 * @return usuario encontrado
	 */
	public Usuario buscarUsuario( Usuario usuario) {
		int pos = usuarios.indexOf(usuario);
		if (pos == -1)
			return null;
		return usuarios.get(pos);
	}
	
	/**
	 * buscar relacion de la red
	 * @param relacion
	 * @return
	 */
	public Relacion buscarRelacion( Relacion relacion) {
		int pos = relaciones.indexOf(relacion);
		if (pos == -1)
			return null;
		return relaciones.get(pos);
	}

	/**
	 * obtiene lista de usuarios
	 * @return lista de usuarios
	 */
	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	/**
	 * establece lista de usuarios
	 * @param lista de usuarios
	 */
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	/**
	 * obtiene lista de relaciones
	 * @return lista de relaciones
	 */
	public List<Relacion> getRelaciones() {
		return relaciones;
	}

	/**
	 * establece lista de relaciones
	 * @param relaciones lista de relaciones
	 */
	public void setRelaciones(List<Relacion> relaciones) {
		this.relaciones = relaciones;
	}

}
