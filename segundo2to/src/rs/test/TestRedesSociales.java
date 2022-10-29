package rs.test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.TreeMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import net.datastructures.Graph;
import rs.modelo.Relacion;
import rs.modelo.Usuario;
import rs.negocio.Calculo;
import rs.negocio.RedSocial;

public class TestRedesSociales {
	
	private Calculo calculo;
	private RedSocial redSocial;
	private Usuario u1;
	private Usuario u2;
	private Usuario u3;
	private Usuario u4;
	private Usuario u5;
	private Usuario u6;
	private Usuario u7;
	private TreeMap<String, Usuario> usuarios;

	@Before
	public void setUp() throws Exception {
		
    //El text se prueba con el archivo secuencial se cambia en factory.properties
		redSocial=new RedSocial();
		calculo = new Calculo(redSocial.getUsuarios(),redSocial.getRelaciones());
		usuarios=new TreeMap<String, Usuario>();
		for(Usuario u:calculo.getUsuarios())
			usuarios.put(u.getId(), u);

		u1 = usuarios.get("juan");
		u2 = usuarios.get("marcos");
		u3 = usuarios.get("lucas");
		u4 = usuarios.get("maria");
		u5 = usuarios.get("ana");
		u6 = usuarios.get("luis");
		u7 = usuarios.get("micaela");

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void usuariosCargados() {
		TreeMap<String, Usuario> cargados = usuarios;
		assertTrue(cargados.containsValue(u1) && u1.getId().equals("juan"));
		assertTrue(cargados.containsValue(u2) && u2.getId().equals("marcos"));
		assertTrue(cargados.containsValue(u3) && u3.getId().equals("lucas"));
		assertTrue(cargados.containsValue(u4) && u4.getId().equals("maria"));
		assertTrue(cargados.containsValue(u5) && u5.getId().equals("ana"));
		assertTrue(cargados.containsValue(u6) && u6.getId().equals("luis"));
		assertTrue(cargados.containsValue(u7) && u7.getId().equals("micaela"));
	}

	@Test
	public void testAmigosDe() {
		List<Usuario> resultadoObtenido = calculo.amigosDe(u1);
		assertTrue(resultadoObtenido.contains(u2));
		assertEquals(resultadoObtenido.size(), 3, 0.01);
	}

	@Test
	public void testTiempoDeAmistad() {
		assertEquals(calculo.tiempoDeAmistad(u1, u2).getTiempoAmistad(), 3, 0.01);
	}

	@Test
	public void testOrdenMasInfluyentes() {
		List<Usuario> resultadoObtenido = calculo.masInfluyentes();
		assertTrue(resultadoObtenido.get(0).equals(u6));
		assertEquals(resultadoObtenido.size(), 7, 0.01);
	}

	@Test
	public void testUsuarioQueMasInteractuanEntreSi() {
		List<Usuario> resultadoObtenido = calculo.usuariosQueMasInteractuan();
		Usuario vicio = resultadoObtenido.get(0);
		int resultado = calculo.totalInteraccionesUsuarios(vicio);
		assertTrue(vicio.equals(u1));
		assertEquals(resultado, 17, 0.01);
		assertTrue(resultadoObtenido.get(1).equals(u6));
		assertTrue(resultadoObtenido.get(6).equals(u7));
	}

	@Test
	public void testUsuarioQueMasInteractuaEnRedes() {
		Usuario resultado = calculo.usuarioQueMasIteractuaEnRedes();
		assertTrue(resultado.equals(u1));
	}

	@Test
	public void testGradoPromedio() {
		double arcos = calculo.getRedSocial().numEdges() * 2;
		double vertices = calculo.getRedSocial().numVertices();
		assertEquals(calculo.gradoPromedio(), arcos / vertices, 0.1);
	}

	@Test
	public void testCaminoMasNuevo() {
		List<Usuario> resultadoObtenido = calculo.caminoMasNuevo(u1.getId(), u2.getId());
		assertEquals(resultadoObtenido.size(), 2, 0.01);
		assertTrue(resultadoObtenido.contains(u1));
		assertTrue(resultadoObtenido.contains(u2));
	}

	@Test
	public void testSugerenciaDeAmistad() {
		List<Usuario> resultadoObtenido = calculo.sugerenciaNuevaAmistad(u1);
		assertEquals(resultadoObtenido.size(), 1, 0.01);
		assertTrue(resultadoObtenido.contains(u4));

	}

	@Test
	public void testDeGrafo() {
		Graph<Usuario, Relacion> resultadObtenido = calculo.getRedSocial();
		assertTrue(calculo.getRedSocial().numEdges() == resultadObtenido.numEdges()
				&& calculo.getRedSocial().numVertices() == resultadObtenido.numVertices()
				&& calculo.getRedSocial().equals(resultadObtenido));

	}
}
