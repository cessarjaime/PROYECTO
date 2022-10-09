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

import rs.aplicacion.Coordinador;
import rs.modelo.Relacion;

public class RelacionesList extends JDialog {

	private Coordinador coordinador;
	private int accion;
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable tableEmpleadoAsalariado;
	private JButton btnInsertar;

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

	/*	btnInsertar = new JButton("Insertar");
		btnInsertar.setBounds(38, 280, 114, 32);
		contentPane.add(btnInsertar); */

		scrollPane = new JScrollPane();
		scrollPane.setBounds(38, 25, 673, 244);
		contentPane.add(scrollPane);

		tableEmpleadoAsalariado = new JTable();
		tableEmpleadoAsalariado.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Nombre", "Id1", "Nombre", "Id2", "Interaccion", "Likes", "FechaDeA." }) {
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false};

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		/*
		tableEmpleadoAsalariado.getColumn("Modificar").setCellRenderer(new ButtonRenderer());
		tableEmpleadoAsalariado.getColumn("Modificar").setCellEditor(new ButtonEditor(new JCheckBox()));
		tableEmpleadoAsalariado.getColumn("Borrar").setCellRenderer(new ButtonRenderer());
		tableEmpleadoAsalariado.getColumn("Borrar").setCellEditor(new ButtonEditor(new JCheckBox()));
		scrollPane.setViewportView(tableEmpleadoAsalariado);

		Handler handler = new Handler();
		btnInsertar.addActionListener(handler); */
		scrollPane.setViewportView(tableEmpleadoAsalariado);
		setModal(true);	
		//((DefaultTableModel) tableEmpleadoAsalariado.getModel()).setRowCount(0);
	}

	/*private class Handler implements ActionListener {
		public void actionPerformed(ActionEvent event) {

			EmpleadoAsalariadoForm empleadoAsalariadoForm = null;
			if (event.getSource() == btnInsertar)
				coordinador.insertarEmpleadoAsalariadoForm();
		}
	} 
*/
	public void loadTable() {
		((DefaultTableModel) tableEmpleadoAsalariado.getModel()).setRowCount(0);
		for(Relacion r:coordinador.listaRelacion())
			addRow(r);
	} 

	public void addRow(Relacion relacion) {
		Object[] row = new Object[tableEmpleadoAsalariado.getModel().getColumnCount()];
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		row[0] = relacion.getUsuario1().getNombre();
		row[1] = relacion.getUsuario1().getId();
		row[2] = relacion.getUsuario2().getNombre();
		row[3] = relacion.getUsuario2().getId();
		row[4] = relacion.getInteraccion();
		row[5] = relacion.getLikes();
		row[6] = relacion.getFechaAmistad();
		((DefaultTableModel) tableEmpleadoAsalariado.getModel()).addRow(row);
	}

	/*private void updateRow(int row) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		tableEmpleadoAsalariado.setValueAt(empleadoAsalariado.getNombre(), row, 0);
		tableEmpleadoAsalariado.setValueAt(empleadoAsalariado.getApellido(), row, 1);
		tableEmpleadoAsalariado.setValueAt(empleadoAsalariado.getDocumento(), row, 2);
		tableEmpleadoAsalariado.setValueAt(sdf.format(empleadoAsalariado.getFechaNacimiento()), row, 3);
		tableEmpleadoAsalariado.setValueAt(empleadoAsalariado.getSalario(), row, 4);
	}

	class ButtonRenderer extends JButton implements TableCellRenderer {

		public ButtonRenderer() {
			setOpaque(true);
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			if (isSelected) {
				setForeground(table.getSelectionForeground());
				setBackground(table.getSelectionBackground());
			} else {
				setForeground(table.getForeground());
				setBackground(UIManager.getColor("Button.background"));
			}
			// setText((value == null) ? "" : value.toString());
			Icon icon = null;
			if (value.toString().equals("edit"))
				icon = new ImageIcon(getClass().getResource("/imagen/b_edit.png"));
			if (value.toString().equals("drop"))
				icon = new ImageIcon(getClass().getResource("/imagen/b_drop.png"));
			setIcon(icon);
			return this;
		}
	}

	class ButtonEditor extends DefaultCellEditor {

		protected JButton button;
		private String label;
		private boolean isPushed;
		private JTable table;
		private boolean isDeleteRow = false;
		private boolean isUpdateRow = false;

		public ButtonEditor(JCheckBox checkBox) {
			super(checkBox);
			button = new JButton();
			button.setOpaque(true);
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					fireEditingStopped();
				}
			});
		}

		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) {

			if (isSelected) {
				button.setForeground(table.getSelectionForeground());
				button.setBackground(table.getSelectionBackground());
			} else {
				button.setForeground(table.getForeground());
				button.setBackground(table.getBackground());
			}

			label = (value == null) ? "" : value.toString();
			// button.setText(label);
			Icon icon = null;
			if (value.toString().equals("edit"))
				icon = new ImageIcon(getClass().getResource("/imagen/b_edit.png"));
			if (value.toString().equals("drop"))
				icon = new ImageIcon(getClass().getResource("/imagen/b_drop.png"));
			button.setIcon(icon);
			isPushed = true;
			this.table = table;
			isDeleteRow = false;
			isUpdateRow = false;
			return button;
		}

		@Override
		public Object getCellEditorValue() {
			if (isPushed) {				
				String id = tableEmpleadoAsalariado.getValueAt(tableEmpleadoAsalariado.getSelectedRow(), 2).toString();
				EmpleadoAsalariado emp = (EmpleadoAsalariado) coordinador
						.buscarEmpleado(new EmpleadoAsalariado(null, null, id, null, 0.0));
				if (label.equals("edit"))
					coordinador.modificarEmpleadoAsalariadoForm(emp);					
				else
					coordinador.borrarEmpleadoAsalariadoForm(emp);						
				}
			if (accion == Constantes.BORRAR)
				isDeleteRow = true;
			if (accion == Constantes.MODIFICAR)
				isUpdateRow = true;
			isPushed = false;
			return new String(label);
		}

		@Override
		public boolean stopCellEditing() {
			isPushed = false;
			return super.stopCellEditing();
		}

		protected void fireEditingStopped() {
			super.fireEditingStopped();

			DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
			if (isDeleteRow)
				tableModel.removeRow(table.getSelectedRow());

			if (isUpdateRow) {
				updateRow(table.getSelectedRow());
			}

		}
	}  */

	public void setCoordinador(Coordinador coordinador) {
		this.coordinador = coordinador;
	}

	public void setAccion(int accion) {
		this.accion = accion;
	}

		
}
