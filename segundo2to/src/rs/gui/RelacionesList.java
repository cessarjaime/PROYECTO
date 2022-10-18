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

import rs.controlador.Coordinador;
import rs.modelo.Relacion;

public class RelacionesList extends JDialog {

	private Coordinador coordinador;
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable tableRelaciones;

	/**
	 * Create the frame.
	 */
	public RelacionesList() {
		
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 756, 366);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);


		scrollPane = new JScrollPane();
		scrollPane.setBounds(38, 25, 673, 244);
		contentPane.add(scrollPane);

		tableRelaciones = new JTable();
		tableRelaciones.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Nombre", "Id1", "Nombre", "Id2", "Interaccion", "Likes", "FechaDeA." }) {
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false};

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});

		scrollPane.setViewportView(tableRelaciones);
		setModal(true);	
		
	}

	public void loadTable() {
		((DefaultTableModel) tableRelaciones.getModel()).setRowCount(0);
		for(Relacion r:coordinador.listaRelacion())
			addRow(r);
	} 

	public void addRow(Relacion relacion) {
		Object[] row = new Object[tableRelaciones.getModel().getColumnCount()];
	
		row[0] = relacion.getUsuario1().getNombre();
		row[1] = relacion.getUsuario1().getId();
		row[2] = relacion.getUsuario2().getNombre();
		row[3] = relacion.getUsuario2().getId();
		row[4] = relacion.getInteraccion();
		row[5] = relacion.getLikes();
		row[6] = relacion.getFechaAmistad();
		((DefaultTableModel) tableRelaciones.getModel()).addRow(row);
	}

	

	public void setCoordinador(Coordinador coordinador) {
		this.coordinador = coordinador;
	}


		
}
