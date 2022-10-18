package rs.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import rs.controlador.Constantes;
import rs.controlador.Coordinador;
import rs.modelo.Gender;
import rs.modelo.Usuario;
import rs.util.Validation;

public class UsuariosForm extends JDialog {

	private Coordinador coordinador;

	private JPanel contentPane;
	private JTextField jtfNombre;
	private JTextField jtfId;
	private JTextField jtfGenero;
	private JTextField jtfCiudad;
	private JTextField jtfEdad;
	private JTextField jtfEstadocivil;
	private JTextField jtfNivelAcademico;
	

	private JLabel lblErrorNombre;
	private JLabel lblErrorId;
	private JLabel lblErrorGenero;
	private JLabel lblErrorCiudad;
	private JLabel lblErrorEdad;
	private JLabel lblErrorEstadocivil;
	private JLabel lblErrorNivelAcademico;

	private JButton btnInsertar;
	private JButton btnCancelar;

	/**
	 * Create the frame.
	 */
	public UsuariosForm() {
		setBounds(450, 150, 550, 400); 
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblId = new JLabel("Id:");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblId.setBounds(42, 24, 107, 14);
		contentPane.add(lblId);

		jtfId = new JTextField();
		jtfId.setBounds(185, 24, 86, 20);
		contentPane.add(jtfId);
		jtfId.setColumns(10);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNombre.setBounds(42, 55, 107, 14);
		contentPane.add(lblNombre);

		jtfNombre = new JTextField();
		jtfNombre.setBounds(185, 55, 86, 20);
		contentPane.add(jtfNombre);
		jtfNombre.setColumns(10);
		
		JLabel lblGenero = new JLabel("Genero:");
		lblGenero.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblGenero.setBounds(42, 89, 107, 14);
		contentPane.add(lblGenero);

		jtfGenero = new JTextField();
		jtfGenero.setBounds(185, 86, 86, 20);
		contentPane.add(jtfGenero);
		jtfGenero.setColumns(10);
		
		JLabel lblCiudad = new JLabel("Ciudad:");
		lblCiudad.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCiudad.setBounds(42, 120, 107, 14);
		contentPane.add(lblCiudad);

		jtfCiudad = new JTextField();
		jtfCiudad.setColumns(10);
		jtfCiudad.setBounds(185, 117, 86, 20);
		contentPane.add(jtfCiudad);

		JLabel lblEdad = new JLabel("Fecha de Nacimiento:");
		lblEdad.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEdad.setBounds(42, 151, 130, 14);
		contentPane.add(lblEdad);
		
		jtfEdad = new JTextField();
		jtfEdad.setBounds(185, 148, 86, 20);
		contentPane.add(jtfEdad);
		jtfEdad.setColumns(10);
		
		JLabel lblEstadocivil = new JLabel("Estado civil:");
		lblEstadocivil.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEstadocivil.setBounds(42, 182, 107, 14);
		contentPane.add(lblEstadocivil);
		
		jtfEstadocivil = new JTextField();
		jtfEstadocivil.setBounds(185, 179, 86, 20);
		contentPane.add(jtfEstadocivil);
		jtfEstadocivil.setColumns(10);
		
		JLabel lblNivelAcademico = new JLabel("Nivel Academico:");
		lblNivelAcademico.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNivelAcademico.setBounds(42, 213, 107, 14);
		contentPane.add(lblNivelAcademico);
		
		jtfNivelAcademico = new JTextField();
		jtfNivelAcademico.setBounds(185, 210, 86, 20);
		contentPane.add(jtfNivelAcademico);
		jtfNivelAcademico.setColumns(10);	
		

		lblErrorId = new JLabel("");
		lblErrorId.setForeground(Color.RED);
		lblErrorId.setBounds(275, 24, 300, 14);
		contentPane.add(lblErrorId);

		lblErrorNombre = new JLabel("");
		lblErrorNombre.setForeground(Color.RED);
		lblErrorNombre.setBounds(275, 55, 300, 14);
		contentPane.add(lblErrorNombre);
		
		lblErrorGenero = new JLabel("");
		lblErrorGenero.setForeground(Color.RED);
		lblErrorGenero.setBounds(275, 89, 300, 14);
		contentPane.add(lblErrorGenero);
		
		lblErrorCiudad = new JLabel("");
		lblErrorCiudad.setForeground(Color.RED);
		lblErrorCiudad.setBounds(275, 120, 300, 14);
		contentPane.add(lblErrorCiudad);

		lblErrorEdad = new JLabel("");
		lblErrorEdad.setForeground(Color.RED);
		lblErrorEdad.setBounds(275, 151, 300, 14);
		contentPane.add(lblErrorEdad);
		
		lblErrorEstadocivil = new JLabel("");
		lblErrorEstadocivil.setForeground(Color.RED);
		lblErrorEstadocivil.setBounds(275, 182, 300, 14);
		contentPane.add(lblErrorEstadocivil);
		
		lblErrorNivelAcademico = new JLabel("");
		lblErrorNivelAcademico.setForeground(Color.RED);
		lblErrorNivelAcademico.setBounds(275, 213, 300, 14);
		contentPane.add(lblErrorNivelAcademico);

        

		Handler handler = new Handler();

		btnInsertar = new JButton("Insertar");
		btnInsertar.setBounds(85, 260, 114, 32); //202
		contentPane.add(btnInsertar);
		btnInsertar.addActionListener(handler);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(225, 260, 114, 32);
		contentPane.add(btnCancelar);
		btnCancelar.addActionListener(handler);

		setModal(true);
	}

	public void accion(int accion) {

		jtfNombre.setEditable(true);
		jtfId.setEditable(true);
		jtfGenero.setEditable(true);
		jtfCiudad.setEditable(true);
		jtfEdad.setEditable(true);
        jtfEstadocivil.setVisible(true);
        jtfNivelAcademico.setVisible(true);

		btnInsertar.setVisible(true);
		limpiar();
		


	}


	private void limpiar() {
		jtfNombre.setText("");
		jtfId.setText("");
		jtfGenero.setText("");
		jtfCiudad.setText("");
		jtfEdad.setText("");
		jtfEstadocivil.setText("");
		jtfNivelAcademico.setText("");
		
		lblErrorNombre.setText("");
		lblErrorId.setText("");
		lblErrorGenero.setText("");
		lblErrorCiudad.setText("");
		lblErrorEdad.setText("");
		lblErrorEstadocivil.setText("");
		lblErrorNivelAcademico.setText("");
	}

	private class Handler implements ActionListener {
		public void actionPerformed(ActionEvent event) {

			if (event.getSource() == btnCancelar) {
				coordinador.cancelarUsuario();
				return;
			}

			boolean valido = true;

			// validar Id
			String id = jtfId.getText().trim();
			if (id.isEmpty()) {
				lblErrorId.setText("Campo obligatorio");
				valido = false;
			} else if (coordinador.buscarUsuario(id) != null) {
				lblErrorId.setText("Este id ya existe");
				valido = false;
			}

			// validar nombre
			String nombre = jtfNombre.getText().trim();
			if (nombre.isEmpty()) {
				lblErrorNombre.setText("Campo obligatorio");
				valido = false;
			} else if (nombre.matches("[A-Z][a-zA-Z]*") != true) {
				lblErrorNombre.setText("Solo letras. Primera con may�scula");
				valido = false;
			}

			// validar genero
			Gender genero = Validation.isGenero(jtfGenero.getText());
			if (genero == null) {
				lblErrorGenero.setText("Genero no valido");
				valido = false;
			}

			// validar ciudad
			String ciudad = jtfCiudad.getText();
			if (ciudad.isEmpty()) {
				lblErrorCiudad.setText("Ciudad no v�lido");
				valido = false;
			}
			
			// validar fecha de nacimiento
			LocalDate fechaNacimiento= Validation.isDate(jtfEdad.getText());
			if (fechaNacimiento == null) {
				lblErrorEdad.setText("Formato de fecha AAAA-MM-DD");
				valido = false;
			}
            
			String estadoCivil =jtfEstadocivil.getText();
			if (estadoCivil.isEmpty()) {
				lblErrorEstadocivil.setText("Estado civil no v�lido");
				valido = false;
			}
			
			String nivelAcademico =jtfNivelAcademico.getText();
			if (nivelAcademico.isEmpty()) {
				lblErrorNivelAcademico.setText("Nivel academico no v�lido");
				valido = false;
			}
			
			if (!valido)
				return;

			Usuario usuario = new Usuario(id, nombre, genero, ciudad,fechaNacimiento,estadoCivil,nivelAcademico );
			if (event.getSource() == btnInsertar) {
				coordinador.insertarUsuario(usuario);
			 JOptionPane.showMessageDialog(null,"Usuario agregado");
			}

		}

	}

	public void setCoordinador(Coordinador coordinador) {
		this.coordinador = coordinador;
	}

}
