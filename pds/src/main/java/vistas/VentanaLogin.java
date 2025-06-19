package vistas;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import copialingo.Controlador;
import modelo.Usuario;

public class VentanaLogin {
	
	// Constantes de clase
	private static int DEFECTO = 0;
	private static int LOGO = 225;
	
	// Atributos
	private Controlador controlador;
	private static JTextField txtUsuario;
	private static JPasswordField txtContraseña;

	public VentanaLogin(Controlador control) {

		this.controlador = control;

		// Crear el marco principal
		JFrame frame = new JFrame("Copialingo - Login");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) (screenSize.width * 0.3);
		int height = (int) (screenSize.height * 0.5);
		frame.setSize(width, height);

		// Establecemos el panel contenedor
		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));

		frame.setContentPane(contentPane);

		// Crear el panel principal
		JPanel panelPrincipal = new JPanel();
		GridBagLayout gbl_panelPrincipal = new GridBagLayout();
		gbl_panelPrincipal.columnWeights = new double[] { 0.0, 0.0, 0.0 };
		gbl_panelPrincipal.columnWidths = new int[] { 0, 0, 0 };
		gbl_panelPrincipal.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0 };
		panelPrincipal.setLayout(gbl_panelPrincipal);
		panelPrincipal.setPreferredSize(new Dimension(350, 250)); // Fijar tamaño del panel
		frame.getContentPane().add(panelPrincipal, BorderLayout.CENTER);

		// Creamos y configuramos etiqueta con imagen appchat
		JLabel lblImagen = new JLabel("");
		GridBagConstraints gbc_lblImagen = new GridBagConstraints();
		gbc_lblImagen.fill = GridBagConstraints.VERTICAL;
		gbc_lblImagen.insets = new Insets(5, 5, 5, 0);
		gbc_lblImagen.gridx = 0;
		gbc_lblImagen.gridy = 0;
		gbc_lblImagen.gridwidth = 3;
		ImageIcon originalIcon = new ImageIcon(getClass().getResource("/recursos/Copialingo.png"));
		Image scaledImage = originalIcon.getImage().getScaledInstance(LOGO, LOGO, Image.SCALE_SMOOTH);
		lblImagen.setIcon(new ImageIcon(scaledImage));
		panelPrincipal.add(lblImagen, gbc_lblImagen);

		// Creamos y configuramos etiqueta Usuario
		JLabel lblUsuario = new JLabel("Usuario: ");
		GridBagConstraints gbc_lblUsuario = new GridBagConstraints();
		gbc_lblUsuario.gridwidth = 2;
		gbc_lblUsuario.insets = new Insets(5, 5, 5, 5);
		gbc_lblUsuario.gridx = 0;
		gbc_lblUsuario.gridy = 2;
		gbc_lblUsuario.anchor = GridBagConstraints.EAST;
		panelPrincipal.add(lblUsuario, gbc_lblUsuario);

		// Configuramos campo de texto Usuario
		txtUsuario = new JTextField(10);
		GridBagConstraints gbc_txtUsuario = new GridBagConstraints();
		gbc_txtUsuario.insets = new Insets(5, 5, 5, 0);
		gbc_txtUsuario.gridx = 2;
		gbc_txtUsuario.gridy = 2;
		gbc_txtUsuario.fill = GridBagConstraints.HORIZONTAL;
		panelPrincipal.add(txtUsuario, gbc_txtUsuario);

		// Creamos y configuramos etiqueta Contraseña
		JLabel lblContraseña = new JLabel("Contraseña:");
		GridBagConstraints gbc_lblContraseña = new GridBagConstraints();
		gbc_lblContraseña.gridwidth = 2;
		gbc_lblContraseña.fill = GridBagConstraints.VERTICAL;
		gbc_lblContraseña.insets = new Insets(5, 5, 0, 5);
		gbc_lblContraseña.gridx = 0;
		gbc_lblContraseña.gridy = 3;
		gbc_lblContraseña.anchor = GridBagConstraints.EAST;
		panelPrincipal.add(lblContraseña, gbc_lblContraseña);

		// Configuramos campo de texto Contraseña
		txtContraseña = new JPasswordField(15);
		GridBagConstraints gbc_txtContraseña = new GridBagConstraints();
		gbc_txtContraseña.insets = new Insets(5, 5, 0, 0);
		gbc_txtContraseña.gridx = 2;
		gbc_txtContraseña.gridy = 3;
		gbc_txtContraseña.fill = GridBagConstraints.BOTH;
		panelPrincipal.add(txtContraseña, gbc_txtContraseña);

		// Creamos panel que contendrá los botones
		JPanel panelBotones = new JPanel();
		frame.getContentPane().add(panelBotones, BorderLayout.SOUTH);

		// Creamos y configuramos botón Registrar
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new VentanaRegistro(controlador);
				frame.dispose();
			}
		});

		// Creamos y configuramos botón Cancelar
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(DEFECTO);
			}
		});

		// Creamos y configuramos botón Aceptar
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Usuario usuario;
				
				String nombreUs = new String(txtUsuario.getText());
				String contraseña = new String(txtContraseña.getPassword());
				
				if ((usuario = controlador.login(nombreUs, contraseña)) != null) {
					new VentanaPrincipal(controlador, usuario);
					frame.dispose();
				} else {
					txtContraseña.setText("");
					JOptionPane.showMessageDialog(frame, "Usuario y/o contraseña incorrectos", "Error",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.X_AXIS));

		// Añadimos elementos del panel botones en el orden que queremos que se muestren
		panelBotones.add(btnRegistrar);
		Component horizontalGlue = Box.createHorizontalGlue();
		panelBotones.add(horizontalGlue);
		panelBotones.add(btnCancelar);
		Component horizontalStrut = Box.createHorizontalStrut(10);
		panelBotones.add(horizontalStrut);
		panelBotones.add(btnLogin);

		// Mostrar la ventana
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
