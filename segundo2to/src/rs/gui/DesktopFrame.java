package rs.gui;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.log4j.Logger;

import rs.conexion.AConnection;
import rs.controlador.Coordinador;

public class DesktopFrame extends JFrame {
	
	final static Logger logger = Logger.getLogger(DesktopFrame.class);
	
	private JComboBox metodosJComboBox;
	private String metodos[] = { "    ", "Grado Promedio", "Los mas influyentes", "El camino mas nuevo",
			"Tiempo de amistad", "Amigos de", "Densidad", "Sugerencia de Amistad", "El que mas interactúa" };
	private Coordinador coordinador;
	private JMenuItem mntmNewMenuItem_1;
	private JMenuItem mntmNewMenuItem_2;
	private JMenuItem mntmNewMenuItem1;
	private JMenuItem mntmNewMenuItem2;

	public DesktopFrame() {
		
     logger.debug("Cargando panel principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu_1 = new JMenu("Insertar");
		menuBar.add(mnNewMenu_1);

		Handler handler = new Handler();

		mntmNewMenuItem_1 = new JMenuItem("Usuarios");
		mntmNewMenuItem_1.addActionListener(handler);

		mnNewMenu_1.add(mntmNewMenuItem_1);

		mntmNewMenuItem_2 = new JMenuItem("Relaciones");
		mntmNewMenuItem_2.addActionListener(handler);

		mnNewMenu_1.add(mntmNewMenuItem_2);

		JMenu mnNewMenu = new JMenu("Ver");
		menuBar.add(mnNewMenu);

		mntmNewMenuItem1 = new JMenuItem("Usuarios");
		mntmNewMenuItem1.addActionListener(handler);

		mnNewMenu.add(mntmNewMenuItem1);

		mntmNewMenuItem2 = new JMenuItem("Relaciones");
		mntmNewMenuItem2.addActionListener(handler);

		mnNewMenu.add(mntmNewMenuItem2);

		Fondo fondo = new Fondo();
		setContentPane(fondo);

		setBounds(100, 100, 650, 600);
		setSize(600, 480);
		setTitle("Red Social");
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);

		JLabel lblFun = new JLabel("FUNCIONALIDADES");
		lblFun.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblFun.setBounds(175, 50, 200, 50);
		add(lblFun);

		metodosJComboBox = new JComboBox(metodos); // set up JComboBox
		metodosJComboBox.setMaximumRowCount(8); // display three rows
		metodosJComboBox.setBounds(185, 100, 175, 30);
		metodosJComboBox.addItemListener(new ItemListener() // anonymous inner class
		{
			// handle JComboBox event
			public void itemStateChanged(ItemEvent event) {

				if (event.getStateChange() == ItemEvent.SELECTED) {

					switch (metodosJComboBox.getSelectedIndex()) {
					case 1:
						JOptionPane.showMessageDialog(null,
								"El grado promedio es:\n" + coordinador.mostrarGradoPromedio());
						break;
					case 2:
						coordinador.losMetodosUsuarioList(null, null, null, 2);
						break;
					case 3:
						coordinador.mostrarCaminoUsuarios(3);

						break;
					case 4:
						coordinador.mostrarCaminoUsuarios(4);

						break;
					case 5:

						coordinador.mostrarCaminoUsuarios(5);

						break;

					case 6:
						coordinador.losMetodosUsuarioList(null, null, null, 6);
						break;
					case 7:
						coordinador.mostrarCaminoUsuarios(7);

						break;
					case 8:
						JOptionPane.showMessageDialog(null, "El usuario que mas interactua en la red es el\n"
								+ coordinador.mostrarElQueMasInteractua());
						break;
					}

					metodosJComboBox.setSelectedIndex(0);
				}
			}

		} // end anonymous inner class
		); // end call to addItemListener

		add(metodosJComboBox);

	}

	private class Handler implements ActionListener {
		public void actionPerformed(ActionEvent event) {

			if (event.getSource() == mntmNewMenuItem_1)
				coordinador.mostrarInsertarUsuario();

			if (event.getSource() == mntmNewMenuItem_2)
				coordinador.mostrarInsertarRelacion();

			if (event.getSource() == mntmNewMenuItem1)
				coordinador.verUsuarios();

			if (event.getSource() == mntmNewMenuItem2)
				coordinador.verRelaciones();

		}
	}

	private class Fondo extends JPanel {
		public void paint(Graphics g) {
			ImageIcon imagen = new ImageIcon(getClass().getResource("/rs/imagen/fondo.jpg"));
			g.drawImage(imagen.getImage(), 0, 0, getWidth(), getHeight(), null);
			setOpaque(false);
			super.paint(g);
		}
	}

	public void setCoordinador(Coordinador coordinador) {
		this.coordinador = coordinador;
	}

}
