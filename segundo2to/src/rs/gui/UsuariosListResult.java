package rs.gui;

import java.awt.Font;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;

import rs.controlador.Coordinador;
import rs.modelo.Usuario;

public class UsuariosListResult extends JDialog {
	
	final static Logger logger = Logger.getLogger(UsuariosListResult.class);
	private Coordinador coordinador;
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable tableUsuariosMetodos;
	private JLabel lblUsuario;

	public UsuariosListResult() {
		logger.debug("Cargando lista de resultados de consultas");
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 756, 366);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblUsuario = new JLabel("");
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblUsuario.setBounds(38, 20, 402, 14);
		contentPane.add(lblUsuario);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(38, 45, 673, 600);
		contentPane.add(scrollPane);

		tableUsuariosMetodos = new JTable();

		scrollPane.setViewportView(tableUsuariosMetodos);
		setModal(true);

	}

	public void loadTable(Usuario usu, String id1, String id2, int opcion) {
		((DefaultTableModel) tableUsuariosMetodos.getModel()).setRowCount(0);
		switch (opcion) {
		case 2:
			modeloDeTabla(2);
			lblUsuario.setText("Los mas influyentes de mayor a menor");

			for (Usuario u : coordinador.listaInfluyentes())
				addRow(u, Integer.toString(coordinador.mostrarCantidadAmigos(u)));

			break;
		case 3:
			modeloDeTabla(3);
			lblUsuario.setText("El camino mas nuevo entre " + coordinador.buscarUsuario(id1).getNombre() + " y "
					+ coordinador.buscarUsuario(id2).getNombre() + " es ");
			List<Usuario> u1 = coordinador.listaCaminoMasNuevo(id1, id2);
			addRow(u1.get(0), "  ");

			for (int i = 1; i < u1.size(); i++)
				addRow(u1.get(i),
						Integer.toString(coordinador.mostrarTiempoAmistad(u1.get(i), u1.get(i - 1)).getTiempoAmistad())
								+ " años");
			break;
		case 5:
			modeloDeTabla(5);
			lblUsuario.setText(
					"Cantidad de amigos de " + usu.getNombre() + " son " + coordinador.listaDeAmigos(usu).size());

			for (Usuario u : coordinador.listaDeAmigos(usu))
				addRow(u, null);

			break;
		case 6:
			modeloDeTabla(6);
			lblUsuario.setText("Usuarios con mas tiempo de interaccion de mayor a menor");

			for (Usuario u : coordinador.usuarioMasdensos())
				addRow(u, Integer.toString(coordinador.mostrarInteraccionTotal(u)));

			break;
		case 7:
			modeloDeTabla(7);
			lblUsuario.setText("Sugerencias de amistad de " + usu.getNombre());

			for (Usuario u : coordinador.mostrarSugerenciasDeAmitad(usu))
				addRow(u, null);

			break;
		}

	}


	public void addRow(Usuario usu, String t) {
		Object[] row = new Object[tableUsuariosMetodos.getModel().getColumnCount()];

		row[0] = usu.getId();
		row[1] = usu.getNombre();
		row[2] = usu.getGenero();
		row[3] = usu.getCiudad();
		row[4] = usu.getFechaNacimiento();
		row[5] = usu.getEstadoCivil();
		row[6] = usu.getNivelAcademico();
		if (row.length >= 8)
			row[7] = t;

		((DefaultTableModel) tableUsuariosMetodos.getModel()).addRow(row);
	}

	public void setCoordinador(Coordinador coordinador) {
		this.coordinador = coordinador;
	}
	
	private void modeloDeTabla(int opcion) {
		switch (opcion) {
		case 2:
			tableUsuariosMetodos.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "ID", "Nombre",
					"Genero", "Ciudad", "fechaDeN.", "EstadoCiv.", "NivelAcad.", "Cant.Amigos" }));
			break;
		case 3:
			tableUsuariosMetodos.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "ID", "Nombre",
					"Genero", "Ciudad", "fechaDeN.", "EstadoCiv.", "NivelAcad.", "T.Amistad" }));

			break;
		case 5, 7:
			tableUsuariosMetodos.setModel(new DefaultTableModel(new Object[][] {},
					new String[] { "ID", "Nombre", "Genero", "Ciudad", "fechaDeN.", "EstadoCiv.", "NivelAcad." }));
			break;
		case 6:
			tableUsuariosMetodos.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "ID", "Nombre",
					"Genero", "Ciudad", "fechaDeN.", "EstadoCiv.", "NivelAcad.", "T.Interaccion" }));
			break;
		}

	}

}
