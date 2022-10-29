package rs.test;

import java.time.LocalDate;

import rs.modelo.Gender;
import rs.modelo.Relacion;
import rs.modelo.Usuario;
import rs.negocio.Calculo;
import rs.negocio.RedSocial;

public class CargaDatos {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RedSocial redSocial=new RedSocial();
        
		Usuario u1 = new Usuario("juan", "Juan", Gender.valueOf("H"), "Puerto Madryn", LocalDate.of(2000, 5, 5),
				"Soltero", "Secundario");
		Usuario u2 = new Usuario("marcos", "Marcos", Gender.valueOf("H"), "Trelew", LocalDate.of(1992, 2, 20),
				"Soltero", "Superior");
		Usuario u3 = new Usuario("lucas", "Lucas", Gender.valueOf("M"), "Comodoro Rivadavia", LocalDate.of(1998, 1, 24),
				"Soltero", "Secundario");
		Usuario u4 = new Usuario("maria", "Maria", Gender.valueOf("NB"), "Puerto Madryn", LocalDate.of(1994, 6, 6),
				"Soltero", "Secundario");
		Usuario u5 = new Usuario("ana", "Ana", Gender.valueOf("M"), "Rawson", LocalDate.of(1966, 5, 27), "Casado",
				"Superior");
		Usuario u6 = new Usuario("luis", "Luis", Gender.valueOf("M"), "Sarmiento", LocalDate.of(1962, 10, 26), "Casado",
				"Superior");
		Usuario u7 = new Usuario("micaela", "Micaela", Gender.valueOf("M"), "Trelew", LocalDate.of(1998, 11, 2),
				"Soltero", "Secundario");

		redSocial.agregarUsuario(u1);
		redSocial.agregarUsuario(u2);
		redSocial.agregarUsuario(u3);
		redSocial.agregarUsuario(u4);
		redSocial.agregarUsuario(u5);
		redSocial.agregarUsuario(u6);
		redSocial.agregarUsuario(u7); 

		Relacion r1 = new Relacion(u1, u2, 10, 123, LocalDate.of(2019, 10, 10));
		Relacion r2 = new Relacion(u1, u5, 2, 56, LocalDate.of(2021, 11, 12));
		Relacion r3 = new Relacion(u1, u6, 5, 32, LocalDate.of(2019, 5, 3));
		Relacion r4 = new Relacion(u2, u3, 5, 199, LocalDate.of(2021, 5, 6));
		Relacion r5 = new Relacion(u3, u4, 3, 32, LocalDate.of(2022, 2, 2));
		Relacion r6 = new Relacion(u3, u7, 1, 2, LocalDate.of(2016, 5, 4));
		Relacion r7 = new Relacion(u4, u5, 2, 33, LocalDate.of(2017, 7, 11));
		Relacion r8 = new Relacion(u4, u6, 3, 21, LocalDate.of(2019, 11, 13));
		Relacion r9 = new Relacion(u5, u6, 4, 44, LocalDate.of(2013, 2, 3));
		Relacion r10 = new Relacion(u6, u7, 4, 15, LocalDate.of(2017, 5, 7));

		redSocial.agregarRelacion(r1);
		redSocial.agregarRelacion(r2);
		redSocial.agregarRelacion(r3);
		redSocial.agregarRelacion(r4);
		redSocial.agregarRelacion(r5);
		redSocial.agregarRelacion(r6);
		redSocial.agregarRelacion(r7);
		redSocial.agregarRelacion(r8);
		redSocial.agregarRelacion(r9);
		redSocial.agregarRelacion(r10);  

		for (Usuario u : redSocial.getUsuarios())
			System.out.println(u.getNombre());
		
		for (Relacion r :redSocial.getRelaciones())
			System.out.println(r.getUsuario1().getNombre()+" "
					+r.getUsuario2().getNombre()); 

	}

}
