package rs.test;

import java.util.ArrayList;
import java.util.List;

import net.datastructures.TreeMap;
import rs.logica.Calculo;
import rs.modelo.Relacion;
import rs.modelo.Usuario;

public class TestCaminoMasNuevo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testCaminoMasNuevo();
		testCaminoMasNuevoError();
	}
	public static void testCaminoMasNuevo() {
		List<Usuario> u = new ArrayList<>();
		u.add(new Usuario("R3234", "Juan", 25, "hombre", "Puerto Madryn"));
		u.add(new Usuario("B3434", "Marcos", 34, "hombre", "trelew"));
		u.add(new Usuario("C5238", "Lucas", 20, "hombre", "Comodoro Rivadavia"));
		u.add(new Usuario("K5338", "Maria", 26, "Mujer", "Puerto Madryn"));
		TreeMap<String, Usuario> usuarios = new TreeMap<String, Usuario>();
		for (Usuario usu : u)
			usuarios.put(usu.getId(), usu);

		List<Relacion> r = new ArrayList<>();
		r.add(new Relacion(u.get(0), u.get(1), 1, 1, 1));
		r.add(new Relacion(u.get(0), u.get(2), 2, 2, 2));
		r.add(new Relacion(u.get(0), u.get(3), 4, 4, 4));
		r.add(new Relacion(u.get(1), u.get(2), 3, 3, 3));
		r.add(new Relacion(u.get(2), u.get(3), 5, 5, 5));
		
     
		Calculo calculo = new Calculo(usuarios, r);
		List<Usuario> camino = calculo.caminoMasNuevo("B3434", "K5338");
		if(camino.get(1).getNombre()!="Juan")
			System.out.println("Error camino mas nuevo");
	}
	public static void testCaminoMasNuevoError() {
		List<Usuario> u = new ArrayList<>();
		u.add(new Usuario("R3234", "Juan", 25, "hombre", "Puerto Madryn"));
		u.add(new Usuario("B3434", "Marcos", 34, "hombre", "trelew"));
		u.add(new Usuario("C5238", "Lucas", 20, "hombre", "Comodoro Rivadavia"));
		u.add(new Usuario("K5338", "Maria", 26, "Mujer", "Puerto Madryn"));
		TreeMap<String, Usuario> usuarios = new TreeMap<String, Usuario>();
		for (Usuario usu : u)
			usuarios.put(usu.getId(), usu);

		List<Relacion> r = new ArrayList<>();
		r.add(new Relacion(u.get(0), u.get(1), 1, 1, 1));
		r.add(new Relacion(u.get(0), u.get(2), 2, 2, 2));
		r.add(new Relacion(u.get(0), u.get(3), 4, 4, 4));
		r.add(new Relacion(u.get(1), u.get(2), 3, 3, 3));
		r.add(new Relacion(u.get(2), u.get(3), 5, 5, 5));
		
     
		Calculo calculo = new Calculo(usuarios, r);		
		try {
			List<Usuario> camino = calculo.caminoMasNuevo("b3434", "K5338");

		} catch (IllegalArgumentException e) {
			System.out.print("Error al cargar los nombres");
		}
		
	}

}
