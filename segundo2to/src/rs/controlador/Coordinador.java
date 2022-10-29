package rs.controlador;

import java.util.List;

import rs.modelo.Relacion;
import rs.modelo.Usuario;
import rs.negocio.Calculo;
import rs.negocio.RedSocial;
import rs.gui.*;

public class Coordinador {
	
    private RedSocial redSocial;
	private Calculo calculo;
	private DesktopFrame desktopFrame;
	private UsuariosForm usuariosForm;
	private UsuariosList usuariosList;
	private RelacionesForm relacionesForm;
	private RelacionesList relacionesList;
	private UsuariosListResult usuariosListResult;
	private UsuariosFormConsultas usuariosFormConsultas;


	public RedSocial getRedSocial() {
		return redSocial;
	}

	public void setRedSocial(RedSocial redSocial) {
		this.redSocial = redSocial;
	}

	public Calculo getCalculo() {
		return calculo;
	}

	public void setCalculo(Calculo calculo) {
		this.calculo = calculo;
	}

	public DesktopFrame getDesktopFrame() {
		return desktopFrame;
	}

	public void setDesktopFrame(DesktopFrame desktopFrame) {
		this.desktopFrame = desktopFrame;
	}

	public UsuariosForm getUsuariosForm() {
		return usuariosForm;
	}

	public void setUsuariosForm(UsuariosForm usuariosForm) {
		this.usuariosForm = usuariosForm;
	}

	public UsuariosList getUsuariosList() {
		return usuariosList;
	}

	public void setUsuariosList(UsuariosList usuariosList) {
		this.usuariosList = usuariosList;
	}

	public RelacionesForm getRelacionesForm() {
		return relacionesForm;
	}

	public void setRelacionesForm(RelacionesForm relacionesForm) {
		this.relacionesForm = relacionesForm;
	}

	public RelacionesList getRelacionesList() {
		return relacionesList;
	}

	public void setRelacionesList(RelacionesList relacionesList) {
		this.relacionesList = relacionesList;
	}

	public UsuariosListResult getUsuariosListResult() {
		return usuariosListResult;
	}

	public void setUsuariosListResult(UsuariosListResult usuariosListResult) {
		this.usuariosListResult = usuariosListResult;
	}

	public UsuariosFormConsultas getUsuariosFormConsultas() {
		return usuariosFormConsultas;
	}

	public void setUsuariosFormConsultas(UsuariosFormConsultas usuariosFormConsultas) {
		this.usuariosFormConsultas = usuariosFormConsultas;
	}

	// Usuarios
	public void verUsuarios() {
		usuariosList.loadTable();
		usuariosList.setVisible(true);
	}

	public void mostrarInsertarUsuario() {
		usuariosForm.accion(Constantes.INSERTAR, null);
		usuariosForm.setVisible(true);
	}

	public void insertarUsuario(Usuario usuario) {
	
		calculo.agregarUsuario(usuario);
		redSocial.agregarUsuario(usuario);
		usuariosForm.setVisible(false);
		usuariosList.addRow(usuario);

	}

	public void mostrarModificarUsuario(Usuario usuario) {
		usuariosForm.accion(Constantes.MODIFICAR, usuario);
		usuariosForm.setVisible(true);
	}

	public void modificarUsuario(Usuario usuario) {
		calculo.modificarVertice(usuario);
		redSocial.modificarUsuario(usuario);
		usuariosList.setAccion(Constantes.MODIFICAR);
		usuariosList.setUsuario(usuario);
		usuariosForm.setVisible(false);
	}

	public void mostrarBorrarUsuario(Usuario usuario) {
		usuariosForm.accion(Constantes.BORRAR, usuario);
		usuariosForm.setVisible(true);
	}

	public void borrarUsuario(Usuario usuario) {
		
		for(Relacion r: calculo.removerVertice(usuario))
		      redSocial.borrarRelacion(r);
		
		redSocial.borrarUsuario(usuario);
		usuariosList.setAccion(Constantes.BORRAR);
		usuariosForm.setVisible(false);
	}

	public void cancelarUsuario() {
		usuariosList.setAccion(Constantes.CANCELAR);
		usuariosForm.setVisible(false);
	}

	public Usuario buscarUsuario(String id) {
		return calculo.buscarVertice(id);
	}

	public List<Usuario> listaUsuarios() {
		return calculo.getUsuarios();
	}

	// relaciones
	public void verRelaciones() {
		relacionesList.loadTable();
		relacionesList.setVisible(true);
	}

	public void mostrarInsertarRelacion() {
		relacionesForm.accion(Constantes.INSERTAR, null);
		relacionesForm.setVisible(true);
	}

	public void insertarRelacion(Relacion relacion) {

		calculo.agregarRelacion(relacion);
		redSocial.agregarRelacion(relacion);
		relacionesForm.setVisible(false);
		relacionesList.addRow(relacion);

	}

	public void mostrarModifcarRelacion(Relacion relacion) {
		relacionesForm.accion(Constantes.MODIFICAR, relacion);
		relacionesForm.setVisible(true);
	}

	public void modifcarRelacion(Relacion relacion) {
		calculo.modificarArco(relacion);
		redSocial.modificarRelacion(relacion);
		relacionesList.setAccion(Constantes.MODIFICAR);
		relacionesList.setRelacion(relacion);
		relacionesForm.setVisible(false);
	}

	public void mostrarBorrarRelacion(Relacion relacion) {
		relacionesForm.accion(Constantes.BORRAR, relacion);
		relacionesForm.setVisible(true);
	}

	public void borrarRelacion(Relacion relacion) {
		calculo.removerArco(relacion);
		redSocial.borrarRelacion(relacion);
		relacionesList.setAccion(Constantes.BORRAR);
		relacionesForm.setVisible(false);
	}

	public void cancelarRelacion() {
		relacionesList.setAccion(Constantes.CANCELAR);
		relacionesForm.setVisible(false);
	}

	public Relacion buscarRelacion(Relacion relacion) {
		return calculo.bucarArco(relacion);
	}

	public List<Relacion> listaRelacion() {
		return calculo.getArcos();
	}

	public boolean verificarRelacion(Relacion relacion) {
		return calculo.getArcos().contains(relacion);
	}

	// UsuariosListResult
	public void losMetodosUsuarioList(Usuario u, String id1, String id2, int opcion) {
		usuariosListResult.loadTable(u, id1, id2, opcion);
		usuariosListResult.setVisible(true);
	}

	// UsuariosFormConsultas
	public void mostrarCaminoUsuarios(int opcion) {
		usuariosFormConsultas.accion(opcion);
		usuariosFormConsultas.setVisible(true);
	}

	public void cancelarCaminoUsuarios() {
		usuariosFormConsultas.setVisible(false);
	}

	// consultas

	public double mostrarGradoPromedio() {
		return calculo.gradoPromedio();
	}

	public List<Usuario> listaInfluyentes() {
		return calculo.masInfluyentes();
	}

	public List<Usuario> listaCaminoMasNuevo(String id1, String id2) {
		return calculo.caminoMasNuevo(id1, id2);
	}

	public Relacion mostrarTiempoAmistad(Usuario u1, Usuario u2) {
		return calculo.tiempoDeAmistad(u1, u2);
	}

	public List<Usuario> listaDeAmigos(Usuario u) {
		return calculo.amigosDe(u);
	}

	public int mostrarCantidadAmigos(Usuario u) {
		return calculo.cantidadAmigos(u);
	}

	public Usuario mostrarMasInfluyente() {
		return calculo.masInfluyente();
	}

	public List<Usuario> usuarioMasdensos() {
		return calculo.usuariosQueMasInteractuan();
	}

	public List<Usuario> mostrarSugerenciasDeAmitad(Usuario u) {
		return calculo.sugerenciaNuevaAmistad(u);
	}

	public Usuario mostrarElQueMasInteractua() {
		return calculo.usuarioQueMasIteractuaEnRedes();
	}

	public int mostrarInteraccionTotal(Usuario usuario) {
		return calculo.totalInteraccionesUsuarios(usuario);
	}

}
