package rs.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import rs.controlador.Coordinador;
import rs.modelo.Gender;
import rs.modelo.Usuario;
import rs.util.Validation;

public class UsuariosMetodoForm extends JDialog {
	private Coordinador coordinador;
	private int opcion;
	private JPanel contentPane;
	private JTextField jtfId;
	private JTextField jtfId2;

	private JLabel lblErrorId;
	private JLabel lblErrorId2;

	private JButton btnInsertar;
	private JButton btnCancelar;

	public UsuariosMetodoForm() {

		setBounds(450, 150, 500, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblId = new JLabel("Id usuario 1:");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblId.setBounds(42, 24, 107, 14);
		contentPane.add(lblId);

		jtfId = new JTextField();
		jtfId.setBounds(159, 24, 86, 20);
		contentPane.add(jtfId);
		jtfId.setColumns(10);

		JLabel lblId2 = new JLabel("Id usuario 2:");
		lblId2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblId2.setBounds(42, 55, 107, 14);
		contentPane.add(lblId2);

		jtfId2 = new JTextField();
		jtfId2.setBounds(159, 55, 86, 20);
		contentPane.add(jtfId2);
		jtfId2.setColumns(10);

		lblErrorId = new JLabel("");
		lblErrorId.setForeground(Color.RED);
		lblErrorId.setBounds(255, 24, 300, 14);
		contentPane.add(lblErrorId);

		lblErrorId2 = new JLabel("");
		lblErrorId2.setForeground(Color.RED);
		lblErrorId2.setBounds(255, 55, 300, 14);
		contentPane.add(lblErrorId2);

		Handler handler = new Handler();

		btnInsertar = new JButton("Insertar");
		btnInsertar.setBounds(85, 120, 114, 32); // 202
		contentPane.add(btnInsertar);
		btnInsertar.addActionListener(handler);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(225, 120, 114, 32);
		contentPane.add(btnCancelar);
		btnCancelar.addActionListener(handler);

		setModal(true);
	}

	public void accion(int opcion) {

		jtfId.setEditable(true);
		jtfId2.setEditable(true);
		this.opcion = opcion;
		btnInsertar.setVisible(true);
		limpiar();

	}

	private void limpiar() {

		jtfId.setText("");
		jtfId2.setText("");

		lblErrorId.setText("");
		lblErrorId2.setText("");

	}

	private class Handler implements ActionListener {
		public void actionPerformed(ActionEvent event) {

			if (event.getSource() == btnCancelar) {
				coordinador.cancelarCaminoUsuarios();
				return;
			}

			boolean valido = true;

			// validar id1
			Usuario u1 = coordinador.buscarUsuario(jtfId.getText());

			if (u1 == null) {
				lblErrorId.setText("Usuario no encontrado");
				valido = false;
			}
			// validar id2
			Usuario u2 = coordinador.buscarUsuario(jtfId2.getText());

			if (u2 == null) {
				lblErrorId2.setText("Usuario no encontrado");
				valido = false;
			}

			if (!valido)
				return;

			if (event.getSource() == btnInsertar)
				if (opcion == 3)
					coordinador.losMetodosUsuarioList(null, u1.getId(), u2.getId(), 3);
				else {
					if(coordinador.mostrarTiempoAmistad(u1, u2)==0)
						JOptionPane.showMessageDialog(null,u1.getNombre() + " y "
								+ u2.getNombre() + " no son amigos");
					else {
					JOptionPane.showMessageDialog(null, "EL tiempo de amistad entre " + u1.getNombre() + " y "
							+ u2.getNombre() + " es:\n" + coordinador.mostrarTiempoAmistad(u1, u2));
					}
				}
		}

	}

	public void setCoordinador(Coordinador coordinador) {
		this.coordinador = coordinador;
	}

}
