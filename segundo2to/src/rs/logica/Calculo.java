package logica;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

import datastructure.AdjacencyMapGraph;
import datastructure.Edge;
import datastructure.Graph;
import datastructure.GraphAlgorithms;
import datastructure.PositionalList;
import datastructure.Vertex;
import modelo.Relacion;
import modelo.Usuario;

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
	
	/**Muestra un listado de los amigos de un usuario*/
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
	
	/** tiempo de amistad entre dos usuarios*/
	public int tiempoDeAmistad(Usuario u1, Usuario u2) {
		Edge<Relacion> arcoAmistad = redSocial.getEdge(vertices.get(u1.getId()), vertices.get(u2.getId()));
		if(arcoAmistad==null)
			return 0;
		Relacion amistad = arcoAmistad.getElement();
		return amistad.getTiempoAmistad();
	}
	
	/**Listado de los usuarios en orden del más influyente al menos*/
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
			for (Usuario usuario : list )
				influyentes.add(0, usuario);

		return influyentes;
	}
	
	/**Usuario más influyente*/
	public Usuario masInfluyente() {
		Usuario influyente = vertices.firstEntry().getValue().getElement();
		for (Vertex<Usuario> v : vertices.values()) {
			if (this.cantidadAmigos(influyente) < this.cantidadAmigos(v.getElement()))
				influyente = v.getElement();
		}
		return influyente;
	}
	
	/**Los usuarios que más interactuan entre si */
	public TreeMap <String,Usuario> usuariosQueMasInteractúanEntreSi(){
		TreeMap <String,Usuario> usuariosMasConectados = new TreeMap<String,Usuario>();
		int interacciones = 0;
		Relacion relacionMasDensa = null;
		for (Edge<Relacion> relaciones : redSocial.edges()) {
			 
			if (interacciones < relaciones.getElement().getInteraccion())
				relacionMasDensa = relaciones.getElement();
				interacciones = relaciones.getElement().getInteraccion();
		}
		Usuario usuario1 = relacionMasDensa.getUsuario1();
		Usuario usuario2 = relacionMasDensa.getUsuario2();
		usuariosMasConectados.put(usuario1.getId(), usuario1);
		usuariosMasConectados.put(usuario2.getId(), usuario2);
		return usuariosMasConectados;
	}

	/**Usuario que más interactúa en las redes sociales*/
	public Usuario usuarioQueMasIteractuaEnRedes() {
		Usuario vicio = null;
		int maximoEnRedes = 0;
		for (Vertex<Usuario> verticeVicio : redSocial.vertices()) {
			int tiempoEnRedes = 0;
			for (Edge<Relacion> relacionesDeVicio : redSocial.incomingEdges(verticeVicio)) {
				tiempoEnRedes = relacionesDeVicio.getElement().getInteraccion() + tiempoEnRedes;
			}
			if (maximoEnRedes < tiempoEnRedes) {
				maximoEnRedes = tiempoEnRedes;
				vicio = verticeVicio.getElement();
			}
		}
		return vicio;
	}
	
	/**Camino más nuevo entre dos usuarios*/
	public List<Usuario> caminoMasNuevo(String id, String id1)  {
		Graph<Usuario, Integer> g = new AdjacencyMapGraph<>(false);
		TreeMap<String, Vertex<Usuario>> verticesC=new TreeMap<>();

		for (Entry<String, Vertex<Usuario>> usr : vertices.entrySet())
			verticesC.put(usr.getKey(), g.insertVertex(usr.getValue().getElement()));

		for (Relacion rel : arcos)
			g.insertEdge(verticesC.get(rel.getUsuario1().getId()), verticesC.get(rel.getUsuario2().getId()),
					rel.getTiempoAmistad());

		PositionalList<Vertex<Usuario>> c = GraphAlgorithms.shortestPathList(g, verticesC.get(id), verticesC.get(id1));

		List<Usuario> camino = new ArrayList<>(c.size());
		for (Vertex<Usuario> v : c)
			camino.add(v.getElement());

		return camino;

	}
	
	private List<Vertex<Usuario>> noSonAmigosDe (Usuario usuario){
		List<Vertex<Usuario>> sugeridos = new ArrayList<Vertex<Usuario>>();
		for (Vertex<Usuario> v : redSocial.vertices()) {
			if (!v.getElement().equals(usuario)) {
				if (!amigosDe(usuario).contains(v.getElement())) {
					sugeridos.add(v);
				}
			}
		}
		return sugeridos;
	}
	
	/**Sugerencia de nueva amistad*/
	public Usuario sugerenciaNuevaAmistad(Usuario usuario) {
		Usuario sugerido = null;
		Vertex<Usuario> verticeUsuario = vertices.get(usuario.getId());
		List<Vertex<Usuario>> sugeridos = new ArrayList<Vertex<Usuario>>();
		int amigosNecesarios = 0;
		sugeridos = noSonAmigosDe(usuario);
		for (Vertex<Usuario> verticeSugerido : sugeridos) {
			int amigosEnComun = 0;
			for (Edge<Relacion> relacionesDeSugeridos : redSocial.incomingEdges(verticeSugerido)) {
				for (Edge<Relacion> relacionesDeUsuario : redSocial.incomingEdges(verticeUsuario)) {
					Vertex<Usuario> v1 = redSocial.endVertices(relacionesDeSugeridos)[0];
					Vertex<Usuario> v2 = redSocial.endVertices(relacionesDeSugeridos)[1];
					Vertex<Usuario> v3 = redSocial.endVertices(relacionesDeUsuario)[0];
					Vertex<Usuario> v4 = redSocial.endVertices(relacionesDeUsuario)[1];
					if ((v3.equals(v1)) || (v3.equals(v2)) || (v4.equals(v1)) || (v4.equals(v2))) {
						amigosEnComun++;
					}
				}
			}
			if (amigosEnComun >= amigosNecesarios)
				sugerido = verticeSugerido.getElement();
		}
		return sugerido;
	}
	
	/** Devuelve el grado promedio */
	public double gradoPromedio() {
		if (redSocial.numEdges() == 0)
			return redSocial.numEdges();

		double a = redSocial.numEdges() * 2;
		double v = redSocial.numVertices();
		return a / v;
	}

	/**Cantidad de amigos de un usuario*/
	public int cantidadAmigos(Usuario u) {
		Vertex<Usuario> v = vertices.get(u.getId());
		return redSocial.inDegree(v);
	}

	/**agrega usuario como vertice del grafo*/
	public void agregarUsuario(Usuario usuario) {
		vertices.put(usuario.getId(), redSocial.insertVertex(usuario));
	}

	/**agrega relacion como arco del grafo*/
	public void agregarRelacion(Relacion relacion) {
		redSocial.insertEdge(vertices.get(relacion.getUsuario1().getId()), vertices.get(relacion.getUsuario2().getId()),
				relacion);
		arcos.add(relacion);
	}

	/**retorna un usuario a partir del id de usuario*/
	public Usuario buscarVertice(String id) {
		if (vertices.get(id)== null)
			return null;
		return vertices.get(id).getElement();
	}

	/**retorna una lista con todos los usuarios*/
	public List<Usuario> getUsuarios() {
		List<Usuario> usuarios = new ArrayList<>();
		for (Entry<String, Vertex<Usuario>> vert : vertices.entrySet())
			usuarios.add(vert.getValue().getElement());

		return usuarios;
	}

	/**retorna una lista con todos los arcos*/
	public List<Relacion> getArcos() {
         return arcos;
	}

	/**retorna el grafo*/
	public Graph<Usuario, Relacion> getRedSocial() {
		return redSocial;
	}
}
