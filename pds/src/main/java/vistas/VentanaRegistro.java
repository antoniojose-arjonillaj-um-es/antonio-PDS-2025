package vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import umu.pds.Controlador;

public class VentanaRegistro {

	// Atributos
	private Controlador controlador;
	private static String imgActual = ""; // Inicialmente, imagen por defecto

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

		JLabel lblUrlImagen = new JLabel("Url Imagen:");
		JTextField txtUrlImagen = new JTextField();

		JLabel lblImagen = new JLabel("Imagen", SwingConstants.CENTER);
		lblImagen.setPreferredSize(new Dimension(120, 120));
		lblImagen.setMinimumSize(new Dimension(120, 120));
		lblImagen.setMaximumSize(new Dimension(120, 120));
		lblImagen.setBorder(BorderFactory.createLineBorder(Color.BLACK));

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
		contentPanel.add(lblUrlImagen, gbc);

		gbc = new GridBagConstraints();
		gbc.insets = new Insets(8, 8, 8, 8);
		gbc.gridx = 1;
		gbc.gridy = 4;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		contentPanel.add(txtUrlImagen, gbc);

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
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(8, 20, 8, 8);
		contentPanel.add(lblImagen, gbc);

		// Panel inferior para botones Aceptar y Cancelar
		JPanel panelBotones = new JPanel(new BorderLayout());
		panelBotones.add(btnCancelar, BorderLayout.WEST);
		panelBotones.add(btnAceptar, BorderLayout.EAST);

		// Agregar paneles al principal
		panelPrincipal.add(contentPanel, BorderLayout.CENTER);
		panelPrincipal.add(panelBotones, BorderLayout.SOUTH);
		frame.setContentPane(panelPrincipal);

		// Mostramos el panel
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
