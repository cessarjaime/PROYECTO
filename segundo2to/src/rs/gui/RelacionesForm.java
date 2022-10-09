package rs.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import rs.aplicacion.Constantes;
import rs.aplicacion.Coordinador;
import rs.modelo.Relacion;
import rs.modelo.Usuario;
import rs.util.Validation;

public class RelacionesForm extends JDialog {

	private Coordinador coordinador;

	private JPanel contentPane;
	private JTextField jtfId1;
	private JTextField jtfId2;
	private JTextField jtfInteraccion;
	private JTextField jtfLikes;
	private JTextField jtfTiempo;

	private JLabel lblErrorId1;
	private JLabel lblErrorId2;
	private JLabel lblErrorInteraccion;
	private JLabel lblErrorLikes;
	private JLabel lblErrorTiempo;

	private JButton btnInsertar;
	private JButton btnModificar;
	private JButton btnBorrar;
	private JButton btnCancelar;
	private JButton btnConsultar;

	/**
	 * Create the frame.
	 */
	public RelacionesForm() {
		setBounds(100, 100, 662, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblId1 = new JLabel("Id1:");
		lblId1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblId1.setBounds(42, 24, 107, 14);
		contentPane.add(lblId1);

		jtfId1 = new JTextField();
		jtfId1.setBounds(159, 24, 86, 20);
		contentPane.add(jtfId1);
		jtfId1.setColumns(10);

		JLabel lblId2 = new JLabel("Id2:");
		lblId2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblId2.setBounds(42, 55, 107, 14);
		contentPane.add(lblId2);

		jtfId2 = new JTextField();
		jtfId2.setBounds(159, 55, 86, 20);
		contentPane.add(jtfId2);
		jtfId2.setColumns(10);

		JLabel lblInterracion = new JLabel("Interracion diaria:");
		lblInterracion.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblInterracion.setBounds(42, 89, 107, 14);
		contentPane.add(lblInterracion);

		jtfInteraccion = new JTextField();
		jtfInteraccion.setBounds(159, 86, 86, 20);
		contentPane.add(jtfInteraccion);
		jtfInteraccion.setColumns(10);

		JLabel lblLikes = new JLabel("Cantidad de Likes:");
		lblLikes.setBounds(42, 120, 107, 14);
		contentPane.add(lblLikes);

		jtfLikes = new JTextField();
		jtfLikes.setColumns(10);
		jtfLikes.setBounds(159, 117, 86, 20);
		contentPane.add(jtfLikes);

		JLabel lblTiempo = new JLabel("Fecha de amistad:");
		lblTiempo.setBounds(42, 151, 107, 14);
		contentPane.add(lblTiempo);

		jtfTiempo = new JTextField();
		jtfTiempo.setColumns(10);
		jtfTiempo.setBounds(159, 148, 86, 20);
		contentPane.add(jtfTiempo);

		lblErrorId1 = new JLabel("");
		lblErrorId1.setForeground(Color.RED);
		lblErrorId1.setBounds(255, 24, 300, 14);
		contentPane.add(lblErrorId1);

		lblErrorId2 = new JLabel("");
		lblErrorId2.setForeground(Color.RED);
		lblErrorId2.setBounds(255, 55, 300, 14);
		contentPane.add(lblErrorId2);

		lblErrorInteraccion = new JLabel("");
		lblErrorInteraccion.setForeground(Color.RED);
		lblErrorInteraccion.setBounds(255, 89, 300, 14);
		contentPane.add(lblErrorInteraccion);

		lblErrorLikes = new JLabel("");
		lblErrorLikes.setForeground(Color.RED);
		lblErrorLikes.setBounds(255, 120, 300, 14);
		contentPane.add(lblErrorLikes);

		lblErrorTiempo = new JLabel("");
		lblErrorTiempo.setForeground(Color.RED);
		lblErrorTiempo.setBounds(255, 151, 300, 14);
		contentPane.add(lblErrorTiempo);

		Handler handler = new Handler();

		btnInsertar = new JButton("Insertar");
		btnInsertar.setBounds(85, 202, 114, 32);
		contentPane.add(btnInsertar);
		btnInsertar.addActionListener(handler);

		/*
		 * btnModificar = new JButton("Modificar"); btnModificar.setBounds(85, 202, 114,
		 * 32); contentPane.add(btnModificar); btnModificar.addActionListener(handler);
		 * 
		 * btnBorrar = new JButton("Borrar"); btnBorrar.setBounds(85, 202, 114, 32);
		 * contentPane.add(btnBorrar); btnBorrar.addActionListener(handler);
		 */

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(225, 202, 114, 32);
		contentPane.add(btnCancelar);
		btnCancelar.addActionListener(handler);

		btnConsultar = new JButton("Consultar");
		btnConsultar.setBounds(365, 202, 114, 32);
		contentPane.add(btnConsultar);
		btnConsultar.addActionListener(handler);

		setModal(true);
	}

	public void accion(int accion) {
		btnInsertar.setVisible(false);
		/*
		 * btnModificar.setVisible(false); btnBorrar.setVisible(false);
		 */
		jtfId1.setEditable(true);
		jtfId2.setEditable(true);
		jtfInteraccion.setEditable(true);
		jtfLikes.setEditable(true);
		jtfTiempo.setEditable(true);

		if (accion == Constantes.INSERTAR) {
			btnInsertar.setVisible(true);
			limpiar();
		}

		/*
		 * if (accion == Constantes.MODIFICAR) { btnModificar.setVisible(true);
		 * jtfInteraccion.setEditable(false); mostrar(empleadoAsalariado); }
		 * 
		 * if (accion == Constantes.BORRAR) { btnBorrar.setVisible(true);
		 * jtfId1.setEditable(false); jtfId2.setEditable(false);
		 * jtfInteraccion.setEditable(false); jtfLikes.setEditable(false);
		 * jtfTiempo.setEditable(false); mostrar(empleadoAsalariado); }
		 */
	}
	/*
	 * private void mostrar(EmpleadoAsalariado empleadoAsalariado) {
	 * SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	 * jtfId1.setText(empleadoAsalariado.getNombre());
	 * jtfId2.setText(empleadoAsalariado.getApellido());
	 * jtfInteraccion.setText(empleadoAsalariado.getDocumento());
	 * jtfLikes.setText(sdf.format(empleadoAsalariado.getFechaNacimiento()));
	 * jtfTiempo.setText(empleadoAsalariado.getSalario() + ""); }
	 */

	private void limpiar() {
		jtfId1.setText("");
		jtfId2.setText("");
		jtfInteraccion.setText("");
		jtfLikes.setText("");
		jtfTiempo.setText("");
		
		lblErrorId1.setText("");
		lblErrorId2.setText("");
		lblErrorInteraccion.setText("");
		lblErrorLikes.setText("");
		lblErrorTiempo.setText("");
	}

	private class Handler implements ActionListener {
		public void actionPerformed(ActionEvent event) {

			if (event.getSource() == btnCancelar) {
				coordinador.cancelarRelacion();
				return;
			}

			if (event.getSource() == btnConsultar) {
				coordinador.verUsuarios();
			    return;
			}
			/*
			 * if (event.getSource() == btnBorrar) { int resp =
			 * JOptionPane.showConfirmDialog(null, "Est� seguro que borra este registro?",
			 * "Confirmar", JOptionPane.YES_NO_OPTION); if (JOptionPane.OK_OPTION == resp)
			 * coordinador.borrarEmpleadoAsalariado( (new EmpleadoAsalariado(null, null,
			 * jtfInteraccion.getText(), null, 0.0))); return; }
			 */

			boolean valido = true;


			// validar id1
			Usuario u1 = coordinador.buscarUsuario(jtfId1.getText());

			if (u1 == null) {
				lblErrorId1.setText("Usuario no encontrado");
				valido = false;
			}
			// validar id2
			Usuario u2 = coordinador.buscarUsuario(jtfId2.getText());

			if (u2 == null) {
				lblErrorId2.setText("Usuario no encontrado");
				valido = false;
			}

			// validar interaccion
			int interaccion = Validation.isInteger(jtfInteraccion.getText().trim());
			if (interaccion == -1) {
				lblErrorInteraccion.setText("Interracion no valido");
				valido = false;
			}

			// validar likes
			int likes = Validation.isInteger(jtfLikes.getText());
			if (likes == -1) {
				lblErrorLikes.setText("Likes no valido");
				valido = false;
			}
            
			// validar fecha de Amistad
			LocalDate tiempo=Validation.isDate(jtfTiempo.getText());
			if (tiempo==null) {
				lblErrorTiempo.setText("Formato de fecha AAAA-MM-DD");
				valido = false;
			}

			if (!valido) 
				return;
			Relacion r = new Relacion(u1, u2, interaccion, likes, tiempo);
			if (event.getSource() == btnInsertar) {
				coordinador.insertarRelacion(r);
			}
		}

	}

	public void setCoordinador(Coordinador coordinador) {
		this.coordinador = coordinador;
	}

}
