package vistas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.URL;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Usuario;
import umu.pds.Controlador;

public class VentanaPrincipal {

	// Constante
	private static final double PROPORCION_IMG = 0.09;
	
	// Atributos
	private Usuario usuario;
	private Controlador controlador;

	private JFrame frame;

	public VentanaPrincipal(Controlador controlador, Usuario usuario) {
		this.controlador = controlador;
		this.usuario = usuario;

		// Crear el marco principal
		frame = new JFrame("Copialingo - Principal");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // TODO: Editar en futuro
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize((int) (screenSize.width * 0.7), (int) (screenSize.height * 0.75));

		// Establecemos el panel contenedor
		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);

		// Componentes de ventana
		JLabel imagenUs = new JLabel("");
		cargarImagen(imagenUs);
		
		JLabel nombreUs = new JLabel(usuario.getNombreUs());
		JLabel tiempoUs = new JLabel("Tiempo uso total: " + Integer.toString(usuario.getTiempoUso()));

		JLabel imagenTckt = new JLabel("");
		ImageIcon originalIcon = new ImageIcon(getClass().getResource("/recursos/entradas.png"));
		Image scaledImage = originalIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		imagenTckt.setIcon(new ImageIcon(scaledImage));
		
		JLabel numeroTckt = new JLabel(Integer.toString(usuario.getTickets()));
		JLabel rachaAct = new JLabel("Racha actual: " + Integer.toString(usuario.getRachaActual()));
		JLabel rachaMax = new JLabel("Mejor racha: " + Integer.toString(usuario.getMejorRacha()));

		JButton btnImportar = new JButton("Importar curso");
		JButton btnAbrir = new JButton("Abrir curso");

		// Panel para info de usuario a la izquierda
		JPanel panelUs = new JPanel();
		panelUs.setLayout(new BoxLayout(panelUs, BoxLayout.Y_AXIS));
		panelUs.add(nombreUs);
		panelUs.add(Box.createVerticalStrut(5));
		panelUs.add(tiempoUs);

		// Panel para info tickets
		JPanel panelTckt = new JPanel();
		panelTckt.setLayout(new BoxLayout(panelTckt, BoxLayout.X_AXIS));
		panelTckt.add(numeroTckt);
		panelTckt.add(Box.createHorizontalStrut(5));
		panelTckt.add(imagenTckt);

		// Panel para info rachas
		JPanel panelRacha = new JPanel();
		panelRacha.setLayout(new BoxLayout(panelRacha, BoxLayout.X_AXIS));
		panelRacha.add(rachaAct);
		panelRacha.add(Box.createHorizontalStrut(5));
		panelRacha.add(rachaMax);

		// Panel para organizar info + rachas
		JPanel panelInfo = new JPanel();
		panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.Y_AXIS));
		panelInfo.add(panelTckt);
		panelInfo.add(Box.createVerticalStrut(5));
		panelInfo.add(panelRacha);

		// Creamos panel superior con info usuario
		JPanel panelSup = new JPanel();
		panelSup.setLayout(new BoxLayout(panelSup, BoxLayout.X_AXIS));
		panelSup.add(imagenUs);
		panelSup.add(Box.createHorizontalStrut(5));
		panelSup.add(panelUs);
		panelSup.add(Box.createHorizontalGlue());
		panelSup.add(panelInfo);
		contentPane.add(panelSup, BorderLayout.NORTH);

		// Creamos panel que contendra los botones de la aplicacion
		JPanel panelBtn = new JPanel();
		panelBtn.setLayout(new BoxLayout(panelBtn, BoxLayout.X_AXIS));
		panelBtn.add(Box.createHorizontalGlue());
		panelBtn.add(btnImportar);
		panelBtn.add(Box.createHorizontalStrut(10));
		panelBtn.add(btnAbrir);
		panelBtn.add(Box.createHorizontalGlue());

		// Creamos panel que contendrá panel de botones (lo centra)
		JPanel contenedorBtn = new JPanel();
		contenedorBtn.setLayout(new BoxLayout(contenedorBtn, BoxLayout.Y_AXIS));
		contenedorBtn.add(Box.createVerticalGlue());
		contenedorBtn.add(panelBtn);
		contenedorBtn.add(Box.createVerticalGlue());

		contentPane.add(contenedorBtn, BorderLayout.CENTER);

		// Comportamiento botones

		// Evento para importar curso
		btnImportar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Importar curso");
			}
		});
		
		// Evento para abrir curso
		btnAbrir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Abrir curso");
			}
		});

		// Mostrar la ventana
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	private void cargarImagen(JLabel etiqueta) {
		String imagen = this.usuario.getImagen();
		ImageIcon originalIcon;
		if (imagen != null && !imagen.isEmpty()) {
			try {
				// Cambiamos imagen de usuario
				URL urlImagen = URI.create(imagen).toURL();
				originalIcon = new ImageIcon(urlImagen);
			} 
			catch (Exception ex) {
				JOptionPane.showMessageDialog(frame, "No se ha podido cargar la imagen de usuario", "Error", JOptionPane.ERROR_MESSAGE);
				originalIcon = new ImageIcon(getClass().getResource("/recursos/user_default.png"));
			}
		}else {
			originalIcon = new ImageIcon(getClass().getResource("/recursos/user_default.png"));
		}
		int width = (int) (frame.getWidth() * PROPORCION_IMG);
		int height = (int) (frame.getHeight() * PROPORCION_IMG);
		Image scaledImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		etiqueta.setIcon(new ImageIcon(scaledImage));
	}
}
