package rs.dao;

import java.util.List;
import rs.modelo.Usuario;

public interface UsuarioDAO {
	void insertar(Usuario usuario);

	void actualizar(Usuario usuario);

	void borrar(Usuario usuario);

	List<Usuario> buscarTodos();
}
