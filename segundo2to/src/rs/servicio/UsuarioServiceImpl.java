package rs.servicio;

import java.util.List;
import rs.conexion.Factory;
import rs.dao.UsuarioDAO;
import rs.modelo.Usuario;

public class UsuarioServiceImpl implements UsuarioService {

	private UsuarioDAO usuarioDAO; 
		
	public UsuarioServiceImpl(){
		usuarioDAO = (UsuarioDAO) Factory.getInstancia("USUARIOS");
	}
	
	@Override
	public void insertar(Usuario usuario) {
		usuarioDAO.insertar(usuario);				
	}

	@Override
	public void actualizar(Usuario usuario) {
		usuarioDAO.actualizar(usuario);						
	}

	@Override
	public void borrar(Usuario usuario) {
		usuarioDAO.borrar(usuario);
		
	}

	@Override
	public List<Usuario> buscarTodos() {
		return usuarioDAO.buscarTodos();
		
	}

}
