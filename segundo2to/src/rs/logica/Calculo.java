package rs.logica;

import java.util.ArrayList;
import java.util.List;

import net.datastructures.AdjacencyMapGraph;
import net.datastructures.Edge;
import net.datastructures.Entry;
import net.datastructures.Graph;
import net.datastructures.GraphAlgorithms;
import net.datastructures.PositionalList;
import net.datastructures.TreeMap;
import net.datastructures.Vertex;
import rs.modelo.Relacion;
import rs.modelo.Usuario;

public class Calculo {

	private Graph<Usuario, Relacion> redSocial;
	private TreeMap<String, Vertex<Usuario>> vertices;
	private List<Relacion> arcos;

	public Calculo(TreeMap<String, Usuario> usuarios, List<Relacion> relaciones) {

		redSocial = new AdjacencyMapGraph<>(false);

		// Cargar usuarios
		vertices = new TreeMap<String, Vertex<Usuario>>();
		for (Entry<String, Usuario> usr : usuarios.entrySet())
			vertices.put(usr.getKey(), redSocial.insertVertex(usr.getValue()));

		// Cargar relaciones
		arcos = relaciones;
		for (Relacion rel : arcos)
			redSocial.insertEdge(vertices.get(rel.getUsuario1().getId()), vertices.get(rel.getUsuario2().getId()), rel);

	}

	/** Devuelve el grado promedio */
	public double gradoPromedio() {
		if (redSocial.numEdges() == 0)
			return redSocial.numEdges();

		double a = redSocial.numEdges() * 2;
		double v = redSocial.numVertices();
		return a / v;
	}

	public List<Usuario> masInfluyentes() {

		List<Usuario> influyentes = new ArrayList<Usuario>(redSocial.numVertices());
		List<Usuario> l;
		TreeMap<Integer, List<Usuario>> mapInf = new TreeMap<>();

		for (Entry<String, Vertex<Usuario>> vert : vertices.entrySet()) {
			if ((l = mapInf.get(redSocial.outDegree(vert.getValue()))) != null)
				l.add(vert.getValue().getElement());
			else {
				l = new ArrayList<>();
				l.add(0, vert.getValue().getElement());
				mapInf.put(redSocial.outDegree(vert.getValue()), l);
			}
		}

		for (List<Usuario> list : mapInf.values())
			for (Usuario usuario : list)
				influyentes.add(0, usuario);

		return influyentes;
	}

	public List<Usuario> caminoMasNuevo(String id, String id1) throws IllegalArgumentException {
		Graph<Usuario, Integer> g = new AdjacencyMapGraph<>(false);

		for (Entry<String, Vertex<Usuario>> usr : vertices.entrySet())
			vertices.put(usr.getKey(), g.insertVertex(usr.getValue().getElement()));

		for (Relacion rel : arcos)
			g.insertEdge(vertices.get(rel.getUsuario1().getId()), vertices.get(rel.getUsuario2().getId()),
					rel.getTiempoAmistad());

		if (vertices.get(id) == null || vertices.get(id1) == null)
			throw new IllegalArgumentException();

		PositionalList<Vertex<Usuario>> c = GraphAlgorithms.shortestPathList(g, vertices.get(id), vertices.get(id1));

		List<Usuario> camino = new ArrayList<>(c.size());
		for (Vertex<Usuario> v : c)
			camino.add(v.getElement());

		return camino;

	}

	public int tiempoDeAmistad(Usuario u1, Usuario u2) {
		Edge<Relacion> arcoAmistad = redSocial.getEdge(vertices.get(u1.getId()), vertices.get(u2.getId()));
		Relacion amistad = arcoAmistad.getElement();
		return amistad.getTiempoAmistad();
	}

	public List<Usuario> amigosDe(Usuario u) {
		List<Usuario> amigos = new ArrayList<Usuario>();
		Vertex<Usuario> v = vertices.get(u.getId());
		for (Edge<Relacion> r : redSocial.incomingEdges(v)) {
			if (u.equals(r.getElement().getUsuario1())) {
				amigos.add(r.getElement().getUsuario2());
			} else {
				amigos.add(r.getElement().getUsuario1());
			}
		}
		return amigos;
	}

	public int cantidadAmigos(Usuario u) {
		Vertex<Usuario> v = vertices.get(u.getId());
		return redSocial.inDegree(v);
	}

	public Usuario masInfluyente() {
		Usuario influyente = vertices.firstEntry().getValue().getElement();
		for (Vertex<Usuario> v : vertices.values()) {
			if (this.cantidadAmigos(influyente) < this.cantidadAmigos(v.getElement()))
				influyente = v.getElement();
		}
		return influyente;
	}

	public void agregarUsuario(Usuario usuario) {
		vertices.put(usuario.getId(), redSocial.insertVertex(usuario));
	}

	public void agregarRelacion(Relacion relacion) {
		redSocial.insertEdge(vertices.get(relacion.getUsuario1().getId()), vertices.get(relacion.getUsuario2().getId()),
				relacion);
		arcos.add(relacion);
	}

	public Usuario buscarVertice(String id) {
		if (vertices.get(id)== null)
			return null;
		return vertices.get(id).getElement();
	}

	public List<Usuario> getUsuarios() {
		List<Usuario> usuarios = new ArrayList<>();
		for (Entry<String, Vertex<Usuario>> vert : vertices.entrySet())
			usuarios.add(vert.getValue().getElement());

		return usuarios;
	}

	public List<Relacion> getArcos() {
         return arcos;
	}

}
