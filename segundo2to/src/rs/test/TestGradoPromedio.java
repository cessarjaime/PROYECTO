package rs.test;

import java.util.ArrayList;
import java.util.List;

import net.datastructures.TreeMap;
import rs.modelo.Usuario;
import rs.logica.Calculo;
import rs.modelo.Relacion;

public class TestGradoPromedio {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testGradoPromedio();
		testGradoPromedioSinArcos();

	}
	public static void testGradoPromedio() {
		List<Usuario> u = new ArrayList<>();
		u.add(new Usuario("R3234", "Juan", 25, "hombre", "Puerto Madryn"));
		u.add(new Usuario("B3434", "Marcos", 34, "hombre", "trelew"));
		u.add(new Usuario("C5238", "Lucas", 20, "hombre", "Comodoro Rivadavia"));
		TreeMap<String, Usuario> usuarios = new TreeMap<String, Usuario>();
		for(Usuario usu: u)
			usuarios.put(usu.getId(), usu);
			
		List<Relacion> r = new ArrayList<>();
		r.add(new Relacion(u.get(0),u.get(1),1,1,1));
		r.add(new Relacion(u.get(0),u.get(2),2,2,2));
		r.add(new Relacion(u.get(1),u.get(2),3,3,3));
		Calculo calculo = new Calculo(usuarios, r);
		
		double gradopromedio = calculo.gradoPromedio();
		System.out.println(gradopromedio);
		if(gradopromedio!=2.0)
			System.out.println("Error grado promedio");
	}
	public static void testGradoPromedioSinArcos() {
		List<Usuario> u = new ArrayList<>();
		u.add(new Usuario("R3234", "Juan", 25, "hombre", "Puerto Madryn"));
		u.add(new Usuario("B3434", "Marcos", 34, "hombre", "trelew"));
		u.add(new Usuario("C5238", "Lucas", 20, "hombre", "Comodoro Rivadavia"));
		TreeMap<String, Usuario> usuarios = new TreeMap<String, Usuario>();
		for(Usuario usu: u)
			usuarios.put(usu.getId(), usu);
			
		List<Relacion> r = new ArrayList<>();
		Calculo calculo = new Calculo(usuarios, r);
		
		double gradopromedio = calculo.gradoPromedio();
		System.out.println(gradopromedio);
		if(gradopromedio!=0.0)
			System.out.println("Error grado promedio");
	}

}
