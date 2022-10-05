package rs.test;

import java.util.ArrayList;
import java.util.List;

import rs.modelo.Relacion;
import rs.modelo.Usuario;

public class TestRelacion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
          test1();
          test2();
          test3();
	}
	public static void test1() {
		List<Usuario> u = new ArrayList<>();
		u.add(new Usuario("R3234", "Juan", 25, "hombre", "Puerto Madryn"));
		u.add(new Usuario("B3434", "Marcos", 34, "hombre", "trelew"));
		u.add(new Usuario("C5238", "Lucas", 20, "hombre", "Comodoro Rivadavia"));
		
		List<Relacion> r = new ArrayList<>();
		r.add(new Relacion(u.get(0), u.get(1), 1, 1, 1));
		r.add(new Relacion(u.get(0), u.get(1), 2, 2, 2));
		
		if(r.get(0).equals(r.get(1)))
		   System.out.println("son iguales test1");
		
	}
	public static void test2() {
		List<Usuario> u = new ArrayList<>();
		u.add(new Usuario("R3234", "Juan", 25, "hombre", "Puerto Madryn"));
		u.add(new Usuario("B3437", "Marcos", 34, "hombre", "trelew"));
		u.add(new Usuario("C5238", "Lucas", 20, "hombre", "Comodoro Rivadavia"));

		List<Relacion> r = new ArrayList<>();
		r.add(new Relacion(u.get(0), u.get(1), 1, 1, 1));
		r.add(new Relacion(u.get(0), u.get(2), 2, 2, 2));
		
		if(r.get(0).equals(r.get(1)))
		   System.out.println("son iguales test2");
		
	}
	public static void test3() {
		List<Usuario> u = new ArrayList<>();
		u.add(new Usuario("R3234", "Juan", 25, "hombre", "Puerto Madryn"));
		u.add(new Usuario("B3437", "Marcos", 34, "hombre", "trelew"));	

		List<Relacion> r = new ArrayList<>();
		r.add(new Relacion(u.get(0), u.get(1), 1, 1, 1));
		r.add(new Relacion(u.get(1), u.get(0), 2, 2, 2));

		if(r.get(0).equals(r.get(1)))
		   System.out.println("son iguales test3");
		
	}
}
