package rs.util;

import rs.dao.RelacionDAO;
import rs.dao.UsuarioDAO;
import rs.dao.aleatorio.RelacionAleatorioDAO;
import rs.dao.aleatorio.UsuarioAleatorioDAO;
import rs.dao.secuencial.RelacionSecuencialDAO;
import rs.dao.secuencial.UsuarioSecuencialDAO;
import rs.modelo.Relacion;
import rs.modelo.Usuario;
import rs.negocio.RedSocial;
import rs.negocio.Subject;

public class CargarDatos {

	public static void main(String[] args) {
		Subject subject=new Subject();
		RedSocial redSocial=new RedSocial(subject);	
		RelacionDAO relacionDAO;
		UsuarioDAO usuarioDAO;
		String cargaArchivo="secuencial";
		if(cargaArchivo.equalsIgnoreCase("secuencial")) {
			relacionDAO=new RelacionSecuencialDAO();
			usuarioDAO =new UsuarioSecuencialDAO();
		}else {
			 relacionDAO = new RelacionAleatorioDAO();
			 usuarioDAO= new UsuarioAleatorioDAO();
		}
		
		for(Usuario u:redSocial.getUsuarios())
			usuarioDAO.insertar(u);
		
		for(Relacion r:redSocial.getRelaciones())
			relacionDAO.insertar(r);
	}	

}
