package rs.test;

import java.util.ArrayList;
import java.util.List;

import net.datastructures.TreeMap;
import rs.logica.Calculo;
import rs.modelo.Relacion;
import rs.modelo.Usuario;

public class TestMasInfluyentes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testMasInfluyentes();

	}

	public static void testMasInfluyentes() {
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
		

		Calculo calculo = new Calculo(usuarios, r);
		List<Usuario> l = calculo.masInfluyentes();
		
		if (l.get(0).getNombre() != "Juan")
			System.out.println("Error lista de mas influyentes");
        if (l.get(1).getNombre() != "Lucas")
			System.out.println("Error lista de mas influyentes");
		if (l.get(2).getNombre() != "Marcos")
			System.out.println("Error lista de mas influyentes");
		if (l.get(3).getNombre() != "Maria")
			System.out.println("Error lista de mas influyentes");

	}

}
