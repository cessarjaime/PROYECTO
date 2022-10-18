package rs.gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.DefaultCellEditor;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import rs.controlador.Constantes;
import rs.controlador.Coordinador;
import rs.modelo.Usuario;

public class UsuariosList extends JDialog {

	private Coordinador coordinador;
	
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable tableUsuarios;
	

	/**
	 * Create the frame.
	 */
	public UsuariosList() {
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 756, 366);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(38, 25, 673, 244);
		contentPane.add(scrollPane);

		tableUsuarios = new JTable();
		tableUsuarios.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Nombre", "Genero", "Ciudad", "fechaDeN.", "EstadoCiv.", "NivelAcad." }) {
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});


		scrollPane.setViewportView(tableUsuarios);
		setModal(true);
		
	}


	public void loadTable() {
		((DefaultTableModel) tableUsuarios.getModel()).setRowCount(0);
		for (Usuario u : coordinador.listaUsuarios())
			addRow(u);
	}

	public void addRow(Usuario usu) {
		Object[] row = new Object[tableUsuarios.getModel().getColumnCount()];
		
		row[0] = usu.getId();
		row[1] = usu.getNombre();
		row[2] = usu.getGenero();
		row[3] = usu.getCiudad();
		row[4] = usu.getFechaNacimiento();
		row[5] = usu.getEstadoCivil();
		row[6] = usu.getNivelAcademico();
		((DefaultTableModel) tableUsuarios.getModel()).addRow(row);
	}

	

	public void setCoordinador(Coordinador coordinador) {
		this.coordinador = coordinador;
	}


}
