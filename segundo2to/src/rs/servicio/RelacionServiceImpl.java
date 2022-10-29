package rs.servicio;

import java.util.List;
import rs.conexion.Factory;
import rs.dao.RelacionDAO;
import rs.modelo.Relacion;

public class RelacionServiceImpl implements RelacionService {

	private RelacionDAO relacionDAO; 
		
	public RelacionServiceImpl(){
		relacionDAO = (RelacionDAO) Factory.getInstancia("RELACIONES");
	}
	
	@Override
	public void insertar(Relacion relacion) {
		relacionDAO.insertar(relacion);				
	}

	@Override
	public void actualizar(Relacion relacion) {
		relacionDAO.actualizar(relacion);						
	}

	@Override
	public void borrar(Relacion relacion) {
		relacionDAO.borrar(relacion);
		
	}

	@Override
	public List<Relacion> buscarTodos() {
		return relacionDAO.buscarTodos();
		
	}

}
