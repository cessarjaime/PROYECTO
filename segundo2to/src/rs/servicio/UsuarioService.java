package rs.servicio;

import java.util.List;
import rs.modelo.Usuario;

public interface UsuarioService {

	void insertar(Usuario usuario);

	void actualizar(Usuario usuario);

	void borrar(Usuario usuario);

	List<Usuario> buscarTodos();

}
