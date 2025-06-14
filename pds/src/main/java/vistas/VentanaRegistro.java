package vistas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import umu.pds.Controlador;

public class VentanaRegistro {

	// Constante
	public static String DEFECTO = "/recursos/user_default.png";
	public static double IMG = 0.33; // Proporcion de imagen
	
	// Atributos
	private Controlador controlador;
	private static String imgActual = "";

	public VentanaRegistro(Controlador control) {

		this.controlador = control;

		// Crear el marco principal
		JFrame frame = new JFrame("Copialingo - Formulario de Registro");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) (screenSize.width * 0.5);
		int height = (int) (screenSize.height * 0.55);
		frame.setSize(width, height);

		// Establecemos el panel contenedor
		JPanel contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 1.0 };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0 };
		contentPanel.setLayout(gbl_contentPanel);

		// Panel principal con BorderLayout y márgenes
		JPanel panelPrincipal = new JPanel(new BorderLayout());
		panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		// Componentes
		JLabel lblNombre = new JLabel("Nombre:");
		JTextField txtNombre = new JTextField();

		JLabel lblTelefono = new JLabel("Teléfono:");
		JTextField txtTelefono = new JTextField();

		JLabel lblContrasena = new JLabel("Contraseña:");
		JPasswordField txtContrasena = new JPasswordField();

		JLabel lblRepetir = new JLabel("Repetir contraseña:");
		JPasswordField txtRepetir = new JPasswordField();

		JLabel lblImagen = new JLabel("Url Imagen:");
		JTextField txtImagen = new JTextField();

		JLabel imagen = new JLabel("", SwingConstants.CENTER);
		ImageIcon originalIcon = new ImageIcon(getClass().getResource(DEFECTO));
		Image scaledImage = originalIcon.getImage().getScaledInstance((int) (width * 0.25), (int) (height * 0.3),
				Image.SCALE_SMOOTH);
		imagen.setIcon(new ImageIcon(scaledImage));

		JButton btnActualizar = new JButton("Actualizar");
		JButton btnCancelar = new JButton("Cancelar");
		JButton btnAceptar = new JButton("Aceptar");

		GridBagConstraints gbc;

		// Fila 0 - Nombre
		gbc = new GridBagConstraints();
		gbc.insets = new Insets(8, 8, 8, 8);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		contentPanel.add(lblNombre, gbc);

		gbc = new GridBagConstraints();
		gbc.insets = new Insets(8, 8, 8, 8);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1.0;
		contentPanel.add(txtNombre, gbc);

		// Fila 1 - Teléfono
		gbc = new GridBagConstraints();
		gbc.insets = new Insets(8, 8, 8, 8);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.WEST;
		contentPanel.add(lblTelefono, gbc);

		gbc = new GridBagConstraints();
		gbc.insets = new Insets(8, 8, 8, 8);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		contentPanel.add(txtTelefono, gbc);

		// Fila 2 - Contraseña
		gbc = new GridBagConstraints();
		gbc.insets = new Insets(8, 8, 8, 8);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.WEST;
		contentPanel.add(lblContrasena, gbc);

		gbc = new GridBagConstraints();
		gbc.insets = new Insets(8, 8, 8, 8);
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		contentPanel.add(txtContrasena, gbc);

		// Fila 3 - Repetir contraseña
		gbc = new GridBagConstraints();
		gbc.insets = new Insets(8, 8, 8, 8);
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.anchor = GridBagConstraints.WEST;
		contentPanel.add(lblRepetir, gbc);

		gbc = new GridBagConstraints();
		gbc.insets = new Insets(8, 8, 8, 8);
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		contentPanel.add(txtRepetir, gbc);

		// Fila 4 - URL Imagen + botón
		gbc = new GridBagConstraints();
		gbc.insets = new Insets(8, 8, 8, 8);
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.anchor = GridBagConstraints.WEST;
		contentPanel.add(lblImagen, gbc);

		gbc = new GridBagConstraints();
		gbc.insets = new Insets(8, 8, 8, 8);
		gbc.gridx = 1;
		gbc.gridy = 4;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		contentPanel.add(txtImagen, gbc);

		gbc = new GridBagConstraints();
		gbc.gridwidth = 2;
		gbc.insets = new Insets(8, 20, 8, 8);
		gbc.gridx = 2;
		gbc.gridy = 4;
		gbc.anchor = GridBagConstraints.NORTH;
		contentPanel.add(btnActualizar, gbc);

		// Fila 6 - Imagen
		gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 1;
		gbc.gridy = 6;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(8, 20, 8, 8);
		contentPanel.add(imagen, gbc);

		// Panel inferior para botones Aceptar y Cancelar
		JPanel panelBotones = new JPanel(new BorderLayout());
		panelBotones.add(btnCancelar, BorderLayout.WEST);
		panelBotones.add(btnAceptar, BorderLayout.EAST);

		// Agregar paneles al principal
		panelPrincipal.add(contentPanel, BorderLayout.CENTER);
		panelPrincipal.add(panelBotones, BorderLayout.SOUTH);
		
		// Añadimos scroll panel en caso de que falte espacio
		JScrollPane scrollPanel = new JScrollPane(contentPanel);
		scrollPanel.setBorder(null);
		panelPrincipal.add(scrollPanel, BorderLayout.CENTER);
		frame.setContentPane(panelPrincipal);
		
		// Funcionalidades de los botones
		btnActualizar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				imgActual = txtImagen.getText().trim();
				if (!imgActual.isEmpty()) {
					try {
						URL urlImagen = URI.create(imgActual).toURL();
						ImageIcon originalIcon = new ImageIcon(urlImagen);
						Image scaledIcon = originalIcon.getImage().getScaledInstance((int) (frame.getWidth() * IMG),
								(int) (frame.getHeight() * IMG), Image.SCALE_SMOOTH);
						imagen.setIcon(new ImageIcon(scaledIcon));
					} catch (MalformedURLException ex) {
						JOptionPane.showMessageDialog(frame, "URL no válida", "Error", JOptionPane.ERROR_MESSAGE);
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(frame, "No se pudo cargar imagen", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
					txtImagen.setText("");
				} else {
					imgActual = "";
					JOptionPane.showMessageDialog(frame, "Introduzca una URL", "Error", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new VentanaLogin(controlador);
				frame.dispose();
			}
		});
		
		btnAceptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Comprobamos que los datos obligatorios están completos
				List<JTextField> campos = Arrays.asList(txtNombre, txtTelefono, txtContrasena, txtRepetir);
				boolean completos = campos.stream().noneMatch(campo -> campo.getText().trim().isEmpty());
				if (completos) {
					// Pasamos los datos a String y los pasamos a controlador
					List<String> datos = campos.stream().map(JTextField::getText).collect(Collectors.toList());
					datos.add(imgActual);
					switch (controlador.registrarUsuario(datos)) {
					case Controlador.ACIERTO:
						JOptionPane.showMessageDialog(frame, "Registro completado correctamente");
						new VentanaLogin(controlador);
						frame.dispose();
						break;
					case Controlador.ERROR_TLF:
						JOptionPane.showMessageDialog(frame, "Número de telefono ya registrado", "Error",JOptionPane.WARNING_MESSAGE);
						txtTelefono.setText("");
						break;
					case Controlador.ERROR_NOREPE:
						JOptionPane.showMessageDialog(frame, "Contraseñas no son iguales", "Error",JOptionPane.ERROR_MESSAGE);
						txtContrasena.setText("");
						txtRepetir.setText("");
						break;
					case Controlador.ERROR_NAME:
						JOptionPane.showMessageDialog(frame, "Nombre de usuario ya registrado", "Error",JOptionPane.WARNING_MESSAGE);
						txtNombre.setText("");
					default:
						// Error desconocido
						JOptionPane.showMessageDialog(frame, "Error desconocido", "Error", JOptionPane.ERROR_MESSAGE);
						break;
					}
				} else {
					JOptionPane.showMessageDialog(frame, "Debes rellenar todos los campos");
				}
			}
		});
		
		// Mostramos el panel
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
