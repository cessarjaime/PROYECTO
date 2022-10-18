package rs.gui;

import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import rs.controlador.Coordinador;
import rs.modelo.Relacion;
import rs.modelo.Usuario;

public class MetodosRelacionList extends JDialog {
	private Coordinador coordinador;
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable tableRelacionMetodos;
	private JLabel lblRelacion;
	private List<String> nombres;

	public Coordinador getCoordinador() {
		return coordinador;
	}

	public MetodosRelacionList() {
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 250, 756, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	    lblRelacion = new JLabel("Los usuarios mas densamente conectados son ");
		lblRelacion.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblRelacion.setBounds(38, 24, 402, 14);
		contentPane.add(lblRelacion);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(38, 50, 673, 100);
		contentPane.add(scrollPane);

		tableRelacionMetodos = new JTable();
		tableRelacionMetodos.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Nombre", "Genero", "Ciudad", "fechaDeN.", "EstadoCiv.", "NivelAcad." }));

		scrollPane.setViewportView(tableRelacionMetodos);
		setModal(true);

	}

	public void loadTable() {
	    nombres=new ArrayList<>(2);                                        
		((DefaultTableModel) tableRelacionMetodos.getModel()).setRowCount(0);
		for (Entry<String, Usuario> r : coordinador.relacionMasdensa().entrySet()) {
			nombres.add(r.getKey());
			addRow(r.getValue());
		}
		lblRelacion.setText(lblRelacion.getText()+nombres.get(0)+" y "+nombres.get(1));
	}

	public void addRow(Usuario usu) {
		Object[] row = new Object[tableRelacionMetodos.getModel().getColumnCount()];
		row[0] = usu.getId();
		row[1] = usu.getNombre();
		row[2] = usu.getGenero();
		row[3] = usu.getCiudad();
		row[4] = usu.getFechaNacimiento();
		row[5] = usu.getEstadoCivil();
		row[6] = usu.getNivelAcademico();
		((DefaultTableModel) tableRelacionMetodos.getModel()).addRow(row);
	}

	public void setCoordinador(Coordinador coordinador) {
		this.coordinador = coordinador;
	}
}
