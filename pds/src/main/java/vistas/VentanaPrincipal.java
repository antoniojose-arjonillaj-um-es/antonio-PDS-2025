package vistas;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.net.URI;
import java.net.URL;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import copialingo.Controlador;
import modelo.Curso;
import modelo.ModoCurso;
import modelo.Usuario;

public class VentanaPrincipal {

	// Constante
	private static final double PROPORCION_IMG = 0.27;
	private static final int ICONO = 30;
	private static final double FRAME_SIZE = 0.5;

	// Atributos
	private Usuario usuario;
	private Controlador controlador;

	private JLabel tiempoUs;
	private JLabel numeroTckt;

	private JFrame frame;

	public VentanaPrincipal(Controlador controlador, Usuario usuario) {
		this.controlador = controlador;
		this.usuario = usuario;

		// Crear el marco principal
		frame = new JFrame("Copialingo - Principal");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize((int) (screenSize.width * FRAME_SIZE), (int) (screenSize.height * FRAME_SIZE));
		frame.setMinimumSize(new Dimension(550, 300));
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (controlador.logout(usuario))
					frame.dispose();
				else {
					JOptionPane.showConfirmDialog(null,
							"ERROR: CURSOS ABIERTOS\nCierra el/los cursos abiertos antes de hacer logout", "-_-",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		// Establecemos el panel contenedor
		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setBorder(new EmptyBorder(2, 2, 2, 2));
		frame.setContentPane(contentPane);

		// Componentes de ventana
		JLabel imagenUs = new JLabel("");
		imagenUs.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					String nuevaUrl = JOptionPane.showInputDialog(frame, "Introduce la URL de la imagen:");
					if (nuevaUrl != null) {
						controlador.cambiarImagen(usuario, nuevaUrl);
						cargarImagen(imagenUs);
					}
				}
			}
		});
		cargarImagen(imagenUs);

		JLabel nombreUs = new JLabel(usuario.getNombreUs());
		tiempoUs = new JLabel("Tiempo uso total: " + Integer.toString(usuario.getTiempoUso()) + " horas");

		JLabel imagenTckt = new JLabel("");
		ImageIcon originalIcon = new ImageIcon(getClass().getResource("/recursos/entradas.png"));
		Image scaledImage = originalIcon.getImage().getScaledInstance(ICONO, ICONO, Image.SCALE_SMOOTH);
		imagenTckt.setIcon(new ImageIcon(scaledImage));

		numeroTckt = new JLabel(Integer.toString(usuario.getTickets()));
		JLabel rachaAct = new JLabel("Racha actual: " + Integer.toString(usuario.getRachaActual()));
		JLabel rachaMax = new JLabel("Mejor racha: " + Integer.toString(usuario.getMejorRacha()));

		JButton btnImportar = new JButton("Importar curso");
		JButton btnAbrir = new JButton("Abrir curso");
		JButton btnCompartir = new JButton("Compartir curso");

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

		// Panel para organizar info + rachas
		JPanel panelInfo = new JPanel();
		panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.Y_AXIS));
		panelInfo.add(panelTckt);
		panelTckt.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelInfo.add(Box.createVerticalStrut(5));
		panelInfo.add(rachaAct);
		rachaAct.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelInfo.add(Box.createVerticalStrut(5));
		panelInfo.add(rachaMax);
		rachaMax.setAlignmentX(Component.CENTER_ALIGNMENT);

		// Creamos panel superior con info usuario
		JPanel panelSup = new JPanel();
		panelSup.setLayout(new BoxLayout(panelSup, BoxLayout.X_AXIS));
		panelSup.add(imagenUs);
		panelSup.add(Box.createHorizontalStrut(10));
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
		panelBtn.add(btnCompartir);
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
				try {
					JFileChooser chooser = new JFileChooser();
					int resultado = chooser.showOpenDialog(null);
					if (resultado == JFileChooser.APPROVE_OPTION) {
						File file = chooser.getSelectedFile();
						if (controlador.importarCurso(usuario, file))
							JOptionPane.showConfirmDialog(null, "Curso importado correctamente", ":D",
									JOptionPane.PLAIN_MESSAGE);
						else
							JOptionPane.showConfirmDialog(null, "Error al importar el curso", "<]87",
									JOptionPane.PLAIN_MESSAGE);
					}
				} catch (Exception error) {
					error.printStackTrace();
				}
			}
		});

		// Evento para abrir curso
		btnAbrir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (usuario.getTickets() > 0) {
					mostrarDialogoOpciones();
					actualizarVentana();
				} else
					JOptionPane.showConfirmDialog(null, "No tienes suficientes vidas", ":(", JOptionPane.PLAIN_MESSAGE);
			}
		});

		// Evento para compartir curso
		btnCompartir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				compartirCurso();
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
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(frame, "No se ha podido cargar la imagen de usuario", "Error",
						JOptionPane.ERROR_MESSAGE);
				originalIcon = new ImageIcon(getClass().getResource("/recursos/user_default.png"));
			}
		} else {
			originalIcon = new ImageIcon(getClass().getResource("/recursos/user_default.png"));
		}
		int width = (int) (frame.getWidth() * PROPORCION_IMG);
		int height = (int) (frame.getHeight() * PROPORCION_IMG);
		Image scaledImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		etiqueta.setIcon(new ImageIcon(scaledImage));
	}

	private void mostrarDialogoOpciones() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(new JLabel("Elige curso a realizar:"));
		panel.add(Box.createVerticalStrut(3));

		panel.add(new JLabel("Curso:"));
		JComboBox<Curso> comboCursos = new JComboBox<>(usuario.getCursos().toArray(new Curso[0]));
		panel.add(comboCursos);
		panel.add(Box.createVerticalStrut(3));

		panel.add(new JLabel("Modo:"));
		JComboBox<ModoCurso> comboModo = new JComboBox<>(controlador.obtenerModos().toArray(new ModoCurso[0]));
		panel.add(comboModo);

		int resultado = JOptionPane.showConfirmDialog(null, panel, "Seleccionar Curso", JOptionPane.YES_NO_OPTION,
				JOptionPane.PLAIN_MESSAGE);

		if (resultado == JOptionPane.YES_OPTION) {
			Curso cursoSelec = (Curso) comboCursos.getSelectedItem();
			ModoCurso modoSelec = (ModoCurso) comboModo.getSelectedItem();
			if (cursoSelec != null && modoSelec != null) {
				switch (cursoSelec.getEstado()) {
				case EN_PROCESO:
					JOptionPane.showMessageDialog(null, "Curso ya en ejecucion", "Error", JOptionPane.WARNING_MESSAGE);
					break;
				case PAUSADO:
					int option = JOptionPane.showConfirmDialog(null, "Curso pausado con " + cursoSelec.getContestadas()
							+ " pregunta respondida de " + cursoSelec.getNumPreguntas()
							+ "\n¿Quieres continuar desde donde lo dejaste?\nSI - Continuar desde donde lo dejaste\nNO - Comenzar de cero",
							"Confirmación", JOptionPane.YES_NO_CANCEL_OPTION);
					if (option == JOptionPane.YES_OPTION) {
						controlador.continuarCurso(cursoSelec);
						new VentanaTest(controlador, cursoSelec, modoSelec);
					}
					if (option == JOptionPane.NO_OPTION) {
						controlador.reiniciarCurso(usuario, cursoSelec);
						new VentanaTest(controlador, cursoSelec, modoSelec);
					}
					break;
				case FINALIZADO:
					int opcion = JOptionPane
							.showConfirmDialog(null,
									"Curso ya completado con\n" + cursoSelec.getResultados()
											+ "\n¿Seguro que quiere continuar?",
									"Confirmación", JOptionPane.YES_NO_OPTION);
					if (opcion == JOptionPane.YES_OPTION) {
						controlador.reiniciarCurso(usuario, cursoSelec);
						new VentanaTest(controlador, cursoSelec, modoSelec);
					}
					break;
				default: // Sin iniciar
					controlador.iniciarCurso(usuario, cursoSelec);
					new VentanaTest(controlador, cursoSelec, modoSelec);
					break;
				}
			} else {
				JOptionPane.showMessageDialog(null, "ERROR: No hay curso o modo seleccionado\nPor favor,selecciona uno",
						"Error", JOptionPane.WARNING_MESSAGE);
			}
		}
	}

	private void compartirCurso() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(new JLabel("Compartiendo curso"));
		panel.add(Box.createVerticalStrut(3));

		panel.add(new JLabel("Introduce teléfono/usuario destinantario"));
		JTextField txtId = new JTextField();
		panel.add(txtId);
		panel.add(Box.createVerticalStrut(3));

		panel.add(new JLabel("Elige curso que quieres enviar:"));
		JComboBox<Curso> comboCursos = new JComboBox<>(usuario.getCursos().toArray(new Curso[0]));
		panel.add(comboCursos);

		int resultado = JOptionPane.showConfirmDialog(null, panel, "(⁄ ⁄•⁄ω⁄•⁄ ⁄)", JOptionPane.YES_NO_OPTION,
				JOptionPane.PLAIN_MESSAGE);

		if (resultado == JOptionPane.YES_OPTION) {
			Curso cursoSelec = (Curso) comboCursos.getSelectedItem();
			String userSelec = txtId.getText().trim();

			if (cursoSelec != null && (userSelec != null && !userSelec.isEmpty())) {
				switch (controlador.compartirCurso(userSelec, cursoSelec)) {
				case Controlador.ACIERTO:
					JOptionPane.showMessageDialog(null, "Curso compartido correctamente.", "\\(＾▽＾)/ ",
							JOptionPane.PLAIN_MESSAGE);
					break;
				case Controlador.ERROR_NAME:
					JOptionPane.showMessageDialog(null, "ERROR: Usuario no encontrado en el sistema.", "((((( ;°Д°) ",
							JOptionPane.ERROR_MESSAGE);
					break;
				default:
					JOptionPane.showMessageDialog(null, "ERROR: Error desconocido.", "(?_?) ",
							JOptionPane.ERROR_MESSAGE);
					break;
				}
			} else {
				JOptionPane.showMessageDialog(null, "ERROR: Usuario y curso no pueden estar vacíos", "(\\\\｀ﾛ´)\\\\ ",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public void actualizarVentana() {
		tiempoUs.setText("Tiempo uso total: " + Integer.toString(usuario.getTiempoUso()) + " horas");
		numeroTckt.setText(Integer.toString(usuario.getTickets()));

		frame.revalidate();
		frame.repaint();
	}
}
