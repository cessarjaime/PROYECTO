package rs.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.apache.log4j.Logger;

import rs.controlador.Coordinador;
import rs.modelo.Gender;
import rs.modelo.Usuario;
import rs.util.Validation;

public class UsuariosFormConsultas extends JDialog {
	
	final static Logger logger = Logger.getLogger(UsuariosFormConsultas.class);
	private Coordinador coordinador;
	private int opcion;
	private JPanel contentPane;
	private String jtfId;
	private String jtfId2;

	private JLabel lblErrorId;
	private JLabel lblErrorId2;

	private JButton btnInsertar;
	private JButton btnCancelar;

	private JLabel lblId;
	private JLabel lblId2;

	private String usuarios[];
	private JComboBox usuariosJComboBox;
	private JComboBox usuarios2JComboBox;

	public UsuariosFormConsultas() {
        logger.debug("Cargando panel de consultas de usuarios");
		setBounds(450, 150, 500, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblId = new JLabel("Id usuario 1:");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblId.setBounds(42, 30, 107, 14);
		contentPane.add(lblId);

		lblId2 = new JLabel("Id usuario 2:");
		lblId2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblId2.setBounds(42, 84, 107, 14);
		contentPane.add(lblId2);


		lblErrorId = new JLabel("");
		lblErrorId.setForeground(Color.RED);
		lblErrorId.setBounds(255, 24, 300, 14);
		contentPane.add(lblErrorId);

		lblErrorId2 = new JLabel("");
		lblErrorId2.setForeground(Color.RED);
		lblErrorId2.setBounds(255, 55, 300, 14);
		contentPane.add(lblErrorId2);

		Handler handler = new Handler();

		btnInsertar = new JButton("Buscar");
		btnInsertar.setBounds(85, 130, 114, 32); // 202
		contentPane.add(btnInsertar);
		btnInsertar.addActionListener(handler);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(225, 130, 114, 32);
		contentPane.add(btnCancelar);
		btnCancelar.addActionListener(handler);

		setModal(true);
		usuariosJComboBox = new JComboBox();
		usuarios2JComboBox = new JComboBox();
		add(usuariosJComboBox);
		add(usuarios2JComboBox);
	}

	public void accion(int opcion) {

		this.opcion = opcion;
		lblId2.setVisible(true);

		btnInsertar.setVisible(true);

		remove(usuariosJComboBox);
		remove(usuarios2JComboBox);
		cargarUsuarios();
		jtfId = usuarios[0];
		jtfId2 = usuarios[0];
		usuariosJComboBox = new JComboBox(usuarios); // set up JComboBox
		usuariosJComboBox.setMaximumRowCount(10);
		usuariosJComboBox.setBounds(150, 25, 100, 30);
		usuariosJComboBox.addItemListener(new ItemListener() // anonymous inner class
		{
			// handle JComboBox event
			public void itemStateChanged(ItemEvent event) {

				if (event.getStateChange() == ItemEvent.SELECTED) {
					jtfId = usuarios[usuariosJComboBox.getSelectedIndex()];
				}
			}
		});
		usuarios2JComboBox = new JComboBox(usuarios); // set up JComboBox
		usuarios2JComboBox.setMaximumRowCount(10);
		usuarios2JComboBox.setBounds(150, 75, 100, 30);
		usuarios2JComboBox.addItemListener(new ItemListener() // anonymous inner class
		{
			// handle JComboBox event
			public void itemStateChanged(ItemEvent event) {

				if (event.getStateChange() == ItemEvent.SELECTED) {
					jtfId2 = usuarios[usuarios2JComboBox.getSelectedIndex()];
				}
			}
		});

		if (opcion == 5 || opcion == 7) {
			lblId2.setVisible(false);
			usuarios2JComboBox.setVisible(false);
		}
		add(usuariosJComboBox);
		add(usuarios2JComboBox);

	}

	private void cargarUsuarios() {
		usuarios=new String[coordinador.listaUsuarios().size()];
		int i = 0;
		for (Usuario u : coordinador.listaUsuarios()) {
			usuarios[i] = u.getId();
			i++;
		}
	}

	private class Handler implements ActionListener {
		public void actionPerformed(ActionEvent event) {

			if (event.getSource() == btnCancelar) {
				coordinador.cancelarCaminoUsuarios();
				return;
			}

			Usuario u1 = coordinador.buscarUsuario(jtfId);

			Usuario u2 = coordinador.buscarUsuario(jtfId2);

			if (event.getSource() == btnInsertar)
				switch (opcion) {
				case 3:
					coordinador.losMetodosUsuarioList(null, jtfId, jtfId2, 3);
					break;
				case 4:
					if (coordinador.mostrarTiempoAmistad(u1, u2) == null)
						JOptionPane.showMessageDialog(null, u1.getNombre() + " y " + u2.getNombre() + " no son amigos");
					else {
						JOptionPane.showMessageDialog(null,
								"La relacion entre " + u1.getNombre() + " y " + u2.getNombre() + " es:\n"
										+ "Tiempo de Amistad="
										+ coordinador.mostrarTiempoAmistad(u1, u2).getTiempoAmistad() + "\nLikes="
										+ coordinador.mostrarTiempoAmistad(u1, u2).getLikes() + "\nInteraccion="
										+ coordinador.mostrarTiempoAmistad(u1, u2).getInteraccion());
					}
					break;
				case 5:
					coordinador.losMetodosUsuarioList(u1, null, null, 5);
					break;
				case 7:
					coordinador.losMetodosUsuarioList(u1, null, null, 7);
				}

		}

	}

	public void setCoordinador(Coordinador coordinador) {
		this.coordinador = coordinador;
	}

}
