package rs.negocio;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import java.util.Set;
import java.util.TreeMap;

import net.datastructures.AdjacencyMapGraph;
import net.datastructures.Edge;
import net.datastructures.Graph;
import net.datastructures.GraphAlgorithms;
import net.datastructures.PositionalList;
import net.datastructures.Vertex;
import rs.conexion.AConnection;
import rs.modelo.Relacion;
import rs.modelo.Usuario;
import rs.servicio.RelacionService;
import rs.servicio.RelacionServiceImpl;
import rs.servicio.UsuarioService;
import rs.servicio.UsuarioServiceImpl;
import rs.util.Calendario;

public class Calculo {
	
	final static Logger logger = Logger.getLogger(Calculo.class);
	private Graph<Usuario, Relacion> redSocial;
	private TreeMap<String, Vertex<Usuario>> vertices;
	private List<Edge<Relacion>> arcos;
	private UsuarioService usuarioService;
	private RelacionService relacionService;

	public Calculo(List<Usuario> usuarios,List<Relacion> relaciones) {
      logger.debug("Cargando grafo");
		redSocial = new AdjacencyMapGraph<>(false);

		// Cargar usuarios
		vertices = new TreeMap<String, Vertex<Usuario>>();

		for (Usuario u : usuarios)
			vertices.put(u.getId(), redSocial.insertVertex(u));

		// Cargar relaciones
		arcos = new ArrayList<>();

		for (Relacion rel : relaciones)
			arcos.add(redSocial.insertEdge(vertices.get(rel.getUsuario1().getId()),
					vertices.get(rel.getUsuario2().getId()), rel));

	}

	/** agrega usuario como vertice del grafo */
	public void agregarUsuario(Usuario usuario) {
		vertices.put(usuario.getId(), redSocial.insertVertex(usuario));
        logger.info("Se agrego el usuario "+ usuario.getId()+" al  grafo");
	
	}

	/** agrega relacion como arco del grafo */
	public void agregarRelacion(Relacion relacion) {
		arcos.add(redSocial.insertEdge(vertices.get(relacion.getUsuario1().getId()),
				vertices.get(relacion.getUsuario2().getId()), relacion));
		logger.info("Se agrego la relacion "+ relacion.getUsuario1().getId() 
				+" y "+relacion.getUsuario2().getId()+" al grafo");
	}

	public void modificarVertice(Usuario u) {
		Usuario usuario = vertices.get(u.getId()).getElement();
		usuario.setNombre(u.getNombre());
		usuario.setGenero(u.getGenero());
		usuario.setCiudad(u.getCiudad());
		usuario.setFechaNacimiento(u.getFechaNacimiento());
		usuario.setEstadoCivil(u.getEstadoCivil());
		usuario.setNivelAcademico(u.getNivelAcademico());
		logger.info("Se modifico el usuario "+ usuario.getId()+" del  grafo");
	
	}

	public void modificarArco(Relacion r) {
		Relacion relacion = arcos.get(buscarArcoIndice(r)).getElement();
		relacion.setInteraccion(r.getInteraccion());
		relacion.setLikes(r.getLikes());
		relacion.setFechaAmistad(r.getFechaAmistad());
		
		logger.info("Se modifico la relacion "+ relacion.getUsuario1().getId() 
				+" y "+relacion.getUsuario2().getId()+" del grafo");

	}

	public List<Relacion> removerVertice(Usuario u) {
		List<Relacion> relacionesDelete=new ArrayList<>();
		for (Edge<Relacion> r : redSocial.incomingEdges(vertices.get(u.getId()))) {
	
			removerArco(r.getElement());
			relacionesDelete.add(r.getElement());

		}
		vertices.remove(u.getId());
		logger.info("Se borro el usuario "+ u.getId()+" del  grafo");
		volverCargarGrafo(getUsuarios(), getArcos());
		// redSocial.removeVertex(vertices.get(u.getId()));
		
		return relacionesDelete;
	}

	public void removerArco(Relacion r) {
		arcos.remove(buscarArcoIndice(r));
		logger.info("Se borro la relacion "+ r.getUsuario1().getId() 
				+" y "+r.getUsuario2().getId()+" del grafo");
		volverCargarGrafo(getUsuarios(), getArcos());
		// redSocial.removeEdge(arcos.remove(buscarArcoIndice(r)));
	}

	/** retorna una lista con todos los usuarios */
	public List<Usuario> getUsuarios() {
		List<Usuario> usuarios = new ArrayList<>();
		for (Entry<String, Vertex<Usuario>> vert : vertices.entrySet())
			usuarios.add(vert.getValue().getElement());

		return usuarios;
	}

	/** retorna una lista con todas las relaciones */
	public List<Relacion> getArcos() {
		List<Relacion> relaciones = new ArrayList<>();
		for (Edge<Relacion> r : arcos)
			relaciones.add(r.getElement());
		return relaciones;
	}

	/** retorna el grafo */
	public Graph<Usuario, Relacion> getRedSocial() {
		return redSocial;
	}

	/** retorna un usuario a partir del id de usuario */
	public Usuario buscarVertice(String id) {
		if (vertices.get(id) == null)
			return null;
		return vertices.get(id).getElement();
	}

	public Relacion bucarArco(Relacion relacion) {
		return arcos.get(buscarArcoIndice(relacion)).getElement();
	}

	// Consultas

	/** Muestra un listado de los amigos de un usuario */
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

	/** tiempo de amistad entre dos usuarios */
	public Relacion tiempoDeAmistad(Usuario u1, Usuario u2) {
		Edge<Relacion> arcoAmistad = redSocial.getEdge(vertices.get(u1.getId()), vertices.get(u2.getId()));
		if (arcoAmistad == null)
			return null;

		return arcoAmistad.getElement();
	}

	/** Listado de los usuarios en orden del más influyente al menos */
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

	/** Usuario más influyente */
	public Usuario masInfluyente() {
		Usuario influyente = vertices.firstEntry().getValue().getElement();
		for (Vertex<Usuario> v : vertices.values()) {
			if (this.cantidadAmigos(influyente) < this.cantidadAmigos(v.getElement()))
				influyente = v.getElement();
		}
		return influyente;
	}

	/** usuarios que más interactuan */
	public List<Usuario> usuariosQueMasInteractuan() {
		TreeMap<String, Integer> usuariosIdInteracciones = new TreeMap<String, Integer>();
		for (Vertex<Usuario> usersVertices : redSocial.vertices()) {
			usuariosIdInteracciones.put(usersVertices.getElement().getId(),
					totalInteraccionesUsuarios(usersVertices.getElement()));
		}

		return ordenarUsuariosQueMasInteractuanEntreSi(usuariosIdInteracciones);
	}

	/** total de interacciones de un usuario */
	public int totalInteraccionesUsuarios(Usuario u) {
		int interacciones = 0;
		for (Edge<Relacion> arcosRelaciones : redSocial.incomingEdges(vertices.get(u.getId()))) {
			interacciones = arcosRelaciones.getElement().getInteraccion() + interacciones;
		}
		return interacciones;
	}

	/** Usuario que más interactúa en las redes sociales */
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

	/** Camino más nuevo entre dos usuarios */
	public List<Usuario> caminoMasNuevo(String id, String id1) {
		Graph<Usuario, Integer> g = new AdjacencyMapGraph<>(false);
		TreeMap<String, Vertex<Usuario>> verticesC = new TreeMap<>();

		for (Entry<String, Vertex<Usuario>> usr : vertices.entrySet())
			verticesC.put(usr.getKey(), g.insertVertex(usr.getValue().getElement()));

		for (Relacion rel : getArcos())
			g.insertEdge(verticesC.get(rel.getUsuario1().getId()), verticesC.get(rel.getUsuario2().getId()),
					Calendario.getTiempo(rel.getFechaAmistad()));

		PositionalList<Vertex<Usuario>> c = GraphAlgorithms.shortestPathList(g, verticesC.get(id), verticesC.get(id1));

		List<Usuario> camino = new ArrayList<>(c.size());
		for (Vertex<Usuario> v : c)
			camino.add(v.getElement());

		return camino;

	}

	/** Sugerencias de nueva amistad */
	public List<Usuario> sugerenciaNuevaAmistad(Usuario usuario) {
		List<Usuario> sugerenciasNuevasAmistad = new ArrayList<Usuario>();
		Vertex<Usuario> verticeUsuario = vertices.get(usuario.getId());
		List<Vertex<Usuario>> sugeridos = new ArrayList<Vertex<Usuario>>();
		int amigosNecesarios = 2;
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
				sugerenciasNuevasAmistad.add(verticeSugerido.getElement());
		}
		return sugerenciasNuevasAmistad;
	}

	/** Devuelve el grado promedio */
	public double gradoPromedio() {
		if (redSocial.numEdges() == 0)
			return redSocial.numEdges();

		double a = redSocial.numEdges() * 2;
		double v = redSocial.numVertices();
		return a / v;
	}

	/** Cantidad de amigos de un usuario */
	public int cantidadAmigos(Usuario u) {
		Vertex<Usuario> v = vertices.get(u.getId());
		return redSocial.inDegree(v);
	}

	// metodos privados

	private List<Vertex<Usuario>> noSonAmigosDe(Usuario usuario) {
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

	private int buscarArcoIndice(Relacion r) {
		for (int i = 0; i < arcos.size(); i++)
			if (arcos.get(i).getElement().equals(r))
				return i;
		return -1;

	}

	/** ordena los usuarios por interacciones */
	private List<Usuario> ordenarUsuariosQueMasInteractuanEntreSi(TreeMap<String, Integer> arbolUsuarioInteracciones) {
		List<Usuario> usuariosOredenadosPorInteracciones = new ArrayList<Usuario>();
		Integer data[] = new Integer[arbolUsuarioInteracciones.size()];
		int l = 0;
		// carga valores de interacciones en un array
		for (Integer valores : arbolUsuarioInteracciones.values()) {
			data[l] = valores;
			l++;
		}
		// ordena el array de mayor a menor por lo tanto los valores de las
		// interacciones
		int aux;
		for (int i = 0; i < data.length; i++) {
			for (int j = i + 1; j < data.length; j++) {
				if (data[i] < data[j]) {
					aux = data[i];
					data[i] = data[j];
					data[j] = aux;
				}
			}
		}
		// utilizando el treeMap carga en una lista ordenada por cantidad de
		// interacciones
		Entry<String, Integer> entrada;
		Set<Entry<String, Integer>> entradas = arbolUsuarioInteracciones.entrySet();
		Iterator<Entry<String, Integer>> iterator = entradas.iterator();
		for (int k = 0; k < data.length; k++) {
			boolean listado = false;
			entradas = arbolUsuarioInteracciones.entrySet();
			iterator = entradas.iterator();
			while (iterator.hasNext() && !listado) {
				entrada = iterator.next();
				if (data[k].equals(entrada.getValue())) {
					usuariosOredenadosPorInteracciones.add(vertices.get(entrada.getKey()).getElement());
					listado = true;
					arbolUsuarioInteracciones.remove(entrada.getKey());
				}
			}
		}
		return usuariosOredenadosPorInteracciones;
	}

	private void volverCargarGrafo(List<Usuario> usuarios, List<Relacion> relaciones) {
		redSocial = new AdjacencyMapGraph<>(false);
		vertices.clear();
		for (Usuario u : usuarios)
			vertices.put(u.getId(), redSocial.insertVertex(u));

		arcos.clear();
		for (Relacion r : relaciones)
			arcos.add(redSocial.insertEdge(vertices.get(r.getUsuario1().getId()), vertices.get(r.getUsuario2().getId()),
					r));
	}

}
