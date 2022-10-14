package rs.gui;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import rs.aplicacion.Coordinador;
import rs.modelo.Usuario;

public class MetodosList extends JDialog {
	
	private Coordinador coordinador;
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable tableUsuariosMetodos;
	

	public MetodosList() {
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 756, 366);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(38, 25, 673, 244);
		contentPane.add(scrollPane);

		tableUsuariosMetodos = new JTable();
		tableUsuariosMetodos.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Nombre", "Genero", "Ciudad", "fechaDeN.", "EstadoCiv.", "NivelAcad." }) {
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});

		scrollPane.setViewportView(tableUsuariosMetodos);
		setModal(true);
		
	}

	public void loadTable(Usuario usu,String id1, String id2, int opcion) {
		((DefaultTableModel) tableUsuariosMetodos.getModel()).setRowCount(0);
		switch (opcion) {
		case 2:
			for (Usuario u : coordinador.listaInfluyentes())
				addRow(u);
			break;
        case 3:
			for (Usuario u : coordinador.listaCaminoMasNuevo(id1, id2))
				addRow(u);
			break;
		case 5:
			for (Usuario u : coordinador.listaDeAmigos(usu))
				addRow(u);
			break;
     	}

	}

	public void addRow(Usuario usu) {
		Object[] row = new Object[tableUsuariosMetodos.getModel().getColumnCount()];

		row[0] = usu.getId();
		row[1] = usu.getNombre();
		row[2] = usu.getGenero();
		row[3] = usu.getCiudad();
		row[4] = usu.getFechaNacimiento();
		row[5] = usu.getEstadoCivil();
		row[6] = usu.getNivelAcademico();
		((DefaultTableModel) tableUsuariosMetodos.getModel()).addRow(row);
	}

	public void setCoordinador(Coordinador coordinador) {
		this.coordinador = coordinador;
	}


}
