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

import rs.aplicacion.Constantes;
import rs.aplicacion.Coordinador;
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
	private JButton btnModificar;
	private JButton btnBorrar;
	private JButton btnCancelar;

	/**
	 * Create the frame.
	 */
	public UsuariosForm() {
		setBounds(450, 150, 662, 400); 
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblId = new JLabel("Id:");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblId.setBounds(42, 24, 107, 14);
		contentPane.add(lblId);

		jtfId = new JTextField();
		jtfId.setBounds(159, 24, 86, 20);
		contentPane.add(jtfId);
		jtfId.setColumns(10);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNombre.setBounds(42, 55, 107, 14);
		contentPane.add(lblNombre);

		jtfNombre = new JTextField();
		jtfNombre.setBounds(159, 55, 86, 20);
		contentPane.add(jtfNombre);
		jtfNombre.setColumns(10);
		
		JLabel lblGenero = new JLabel("Genero:");
		lblGenero.setBounds(42, 89, 107, 14);
		contentPane.add(lblGenero);

		jtfGenero = new JTextField();
		jtfGenero.setColumns(10);
		jtfGenero.setBounds(159, 86, 86, 20);
		contentPane.add(jtfGenero);
		
		JLabel lblCiudad = new JLabel("Ciudad:");
		lblCiudad.setBounds(42, 120, 107, 14);
		contentPane.add(lblCiudad);

		jtfCiudad = new JTextField();
		jtfCiudad.setColumns(10);
		jtfCiudad.setBounds(159, 117, 86, 20);
		contentPane.add(jtfCiudad);

		JLabel lblEdad = new JLabel("Fecha de Nacimiento:");
		lblEdad.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEdad.setBounds(42, 151, 107, 14);
		contentPane.add(lblEdad);
		
		jtfEdad = new JTextField();
		jtfEdad.setBounds(159, 148, 86, 20);
		contentPane.add(jtfEdad);
		jtfEdad.setColumns(10);
		
		JLabel lblEstadocivil = new JLabel("Estado civil:");
		lblEstadocivil.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEstadocivil.setBounds(42, 182, 107, 14);
		contentPane.add(lblEstadocivil);
		
		jtfEstadocivil = new JTextField();
		jtfEstadocivil.setBounds(159, 179, 86, 20);
		contentPane.add(jtfEstadocivil);
		jtfEstadocivil.setColumns(10);
		
		JLabel lblNivelAcademico = new JLabel("Nivel Academico:");
		lblNivelAcademico.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNivelAcademico.setBounds(42, 213, 107, 14);
		contentPane.add(lblNivelAcademico);
		
		jtfNivelAcademico = new JTextField();
		jtfNivelAcademico.setBounds(159, 210, 86, 20);
		contentPane.add(jtfNivelAcademico);
		jtfNivelAcademico.setColumns(10);	
		

		lblErrorId = new JLabel("");
		lblErrorId.setForeground(Color.RED);
		lblErrorId.setBounds(255, 24, 300, 14);
		contentPane.add(lblErrorId);

		lblErrorNombre = new JLabel("");
		lblErrorNombre.setForeground(Color.RED);
		lblErrorNombre.setBounds(255, 55, 300, 14);
		contentPane.add(lblErrorNombre);
		
		lblErrorGenero = new JLabel("");
		lblErrorGenero.setForeground(Color.RED);
		lblErrorGenero.setBounds(255, 89, 300, 14);
		contentPane.add(lblErrorGenero);
		
		lblErrorCiudad = new JLabel("");
		lblErrorCiudad.setForeground(Color.RED);
		lblErrorCiudad.setBounds(255, 120, 300, 14);
		contentPane.add(lblErrorCiudad);

		lblErrorEdad = new JLabel("");
		lblErrorEdad.setForeground(Color.RED);
		lblErrorEdad.setBounds(255, 151, 300, 14);
		contentPane.add(lblErrorEdad);
		
		lblErrorEstadocivil = new JLabel("");
		lblErrorEstadocivil.setForeground(Color.RED);
		lblErrorEstadocivil.setBounds(255, 182, 300, 14);
		contentPane.add(lblErrorEstadocivil);
		
		lblErrorNivelAcademico = new JLabel("");
		lblErrorNivelAcademico.setForeground(Color.RED);
		lblErrorNivelAcademico.setBounds(255, 213, 300, 14);
		contentPane.add(lblErrorNivelAcademico);

        

		Handler handler = new Handler();

		btnInsertar = new JButton("Insertar");
		btnInsertar.setBounds(85, 260, 114, 32); //202
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
		btnCancelar.setBounds(225, 260, 114, 32);
		contentPane.add(btnCancelar);
		btnCancelar.addActionListener(handler);

		setModal(true);
	}

	public void accion(int accion) {
		//btnInsertar.setVisible(false);
		/*
		 * btnModificar.setVisible(false); 
		 * btnBorrar.setVisible(false);
		 */
		jtfNombre.setEditable(true);
		jtfId.setEditable(true);
		jtfGenero.setEditable(true);
		jtfCiudad.setEditable(true);
		jtfEdad.setEditable(true);
        jtfEstadocivil.setVisible(true);
        jtfNivelAcademico.setVisible(true);

		// if (accion == Constantes.INSERTAR) {
		btnInsertar.setVisible(true);
		limpiar();
		// }

		/*
		 * if (accion == Constantes.MODIFICAR) { btnModificar.setVisible(true);
		 * jtfEdad.setEditable(false); mostrar(empleadoAsalariado); }
		 * 
		 * if (accion == Constantes.BORRAR) { btnBorrar.setVisible(true);
		 * jtfNombre.setEditable(false); jtfId.setEditable(false);
		 * jtfEdad.setEditable(false); jtfGenero.setEditable(false);
		 * jtfCiudad.setEditable(false); mostrar(empleadoAsalariado); }
		 */
	}

	/*
	 * private void mostrar(EmpleadoAsalariado empleadoAsalariado) {
	 * SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	 * jtfNombre.setText(empleadoAsalariado.getNombre());
	 * jtfId.setText(empleadoAsalariado.getApellido());
	 * jtfEdad.setText(empleadoAsalariado.getDocumento());
	 * jtfGenero.setText(sdf.format(empleadoAsalariado.getFechaNacimiento()));
	 * jtfCiudad.setText(empleadoAsalariado.getSalario() + ""); }
	 */

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
			/*
			 * if (event.getSource() == btnBorrar) { int resp =
			 * JOptionPane.showConfirmDialog(null, "Est� seguro que borra este registro?",
			 * "Confirmar", JOptionPane.YES_NO_OPTION); if (JOptionPane.OK_OPTION == resp)
			 * coordinador.borrarEmpleadoAsalariado( (new EmpleadoAsalariado(null, null,
			 * jtfEdad.getText(), null, 0.0))); return; }
			 */

			boolean valido = true;

			// validar Id
			String id = jtfId.getText().trim();
			if (id.isEmpty()) {
				lblErrorId.setText("Campo obligatorio");
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
			if (event.getSource() == btnInsertar)
				coordinador.insertarUsuario(usuario);

			/*
			 * if (event.getSource() == btnModificar)
			 * coordinador.modificarEmpleadoAsalariado(empleadoAsalariado);
			 */
		}

	}

	public void setCoordinador(Coordinador coordinador) {
		this.coordinador = coordinador;
	}

}
