package rs.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import rs.aplicacion.Coordinador;

public class DesktopFrame extends JFrame {

	private Coordinador coordinador;
	private JPanel contentPane;
	JMenuItem mntmNewMenuItem_1;
	JMenuItem mntmNewMenuItem_2;
	JMenuItem mntmNewMenuItem1;
	JMenuItem mntmNewMenuItem2;

	public DesktopFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 600);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu_1 = new JMenu("Insertar");
		menuBar.add(mnNewMenu_1);

		Handler handler = new Handler();

		mntmNewMenuItem_1 = new JMenuItem("Usuarios");
		mntmNewMenuItem_1.addActionListener(handler);
		/*
		 * mntmNewMenuItem_1.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent e) { coordinador.mostrarInsertarUsuario(); } });
		 */
		mnNewMenu_1.add(mntmNewMenuItem_1);

		mntmNewMenuItem_2 = new JMenuItem("Relaciones");
		mntmNewMenuItem_2.addActionListener(handler);
		/*
		 * mntmNewMenuItem_1.addActionListener(new ActionListener() { /* public void
		 * actionPerformed(ActionEvent e) { coordinador.mostrarInsertarRelacion(); } });
		 */
		mnNewMenu_1.add(mntmNewMenuItem_2);

		JMenu mnNewMenu = new JMenu("Ver");
		menuBar.add(mnNewMenu);

		mntmNewMenuItem1 = new JMenuItem("Usuarios");
		mntmNewMenuItem1.addActionListener(handler);
		/*
		 * mntmNewMenuItem1.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent arg0) { coordinador.verUsuarios(); } });
		 */
		mnNewMenu.add(mntmNewMenuItem1);

		mntmNewMenuItem2 = new JMenuItem("Relaciones");
		mntmNewMenuItem2.addActionListener(handler);
		/*
		 * mntmNewMenuItem2.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent arg0) { coordinador.verRelaciones(); } });
		 */
		mnNewMenu.add(mntmNewMenuItem2);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		setSize(600, 480);
		setTitle("Empresa: MVC");
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);

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

	public void setCoordinador(Coordinador coordinador) {
		this.coordinador = coordinador;
	}

}
