package vistas;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Collections;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import copialingo.Controlador;
import modelo.Curso;
import modelo.Pregunta;

public class VentanaTest {
	// Constantes de clase
	private static final double FRAME_SIZE = 0.35;

	// Atributos generales
	private Controlador controlador;
	private Curso curso;
	private String modalidad;

	// Atributos visibles
	private JFrame frame;
	private JPanel panelCentro;
	private JButton btnSiguiente;

	// Atributos para obtener respuestas
	private VistaPregunta vistaPreguntaActual;
	private Pregunta preguntaActual; // Para saber qué tipo de pregunta es

	// Atributos para modalidades
	private List<Pregunta> preguntas; // Lista de preguntas real, según modalidad
	private int indiceActual;
	// Atributos para contrarreloj
	private Timer temporizador;
	private JLabel lblTemporizador;
	private int segRestantes;

	// Constructor
	public VentanaTest(Controlador controlador, Curso curso, String modalidad) {

		this.controlador = controlador;
		this.curso = curso;
		this.modalidad = modalidad;

		indiceActual = 0;
		preguntas = curso.getPreguntasVacias();

		// Inicializamos frame principal
		frame = new JFrame();
		frame.setTitle("Test de curso de " + curso.getNombre());
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize((int) (screenSize.width * FRAME_SIZE), (int) (screenSize.height * FRAME_SIZE));
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (temporizador != null && temporizador.isRunning()) {
					temporizador.stop();
				}
				controlador.pausarCurso(curso);
				frame.dispose();
			}
		});

		// Establecemos el panel contenedor
		JPanel contentPane = new JPanel(new BorderLayout());
		contentPane.setBorder(new EmptyBorder(2, 2, 2, 2));
		frame.setContentPane(contentPane);

		// Inicializamos el panel centro (donde estarán nuestras opciones y tal)
		panelCentro = new JPanel();
		panelCentro.setLayout(new BoxLayout(panelCentro, BoxLayout.Y_AXIS));

		// Le ponemos scrollPanel en caso de que no haya espacio
		JScrollPane scrollPane = new JScrollPane(panelCentro);
		contentPane.add(scrollPane, BorderLayout.CENTER);

		// Creamos el componente botón y su panel para mantener su espacio
		btnSiguiente = new JButton("Siguiente");
		btnSiguiente.addActionListener(e -> {
			avanzarPregunta();
		});
		JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelBoton.add(btnSiguiente);
		contentPane.add(panelBoton, BorderLayout.SOUTH);

		// Etiqueta de tiempo
		lblTemporizador = new JLabel("Tiempo restante: 10 s");
		lblTemporizador.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblTemporizador, BorderLayout.NORTH);
		lblTemporizador.setVisible(false); // Por defecto oculto, sólo se muestra en contrarreloj

		// Preparamos curso según modalidad escogida
		// Aqui incluimos comportamiento general de futuros modos si es necesario
		switch (modalidad) {
		case Controlador.ALEATORIO:
			frame.setTitle(frame.getTitle() + "- Modalidad aleatoria");
			Collections.shuffle(preguntas); // Desordenamos preguntas
			break;
		case Controlador.CONTRARRELOJ:
			frame.setTitle(frame.getTitle() + "- Modalidad contrareloj");
			break;
		default: // defecto
			frame.setTitle(frame.getTitle() + "- Modalidad defecto");
			break;
		}

		mostrarPregunta();

		// Finalmente mostramos la ventana
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	private void mostrarPregunta() {
		panelCentro.removeAll();

		Box box = Box.createVerticalBox();
		box.setAlignmentX(Component.CENTER_ALIGNMENT);

		preguntaActual = preguntas.get(indiceActual);
		try {
			vistaPreguntaActual = VistaPreguntaFactory.crear(preguntaActual);
			vistaPreguntaActual.mostrar(box);
		} catch (IllegalArgumentException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
		}

		// Pegamos la caja vertical al centro verticalmente usando glue
		panelCentro.add(Box.createVerticalGlue());
		panelCentro.add(box);
		panelCentro.add(Box.createVerticalGlue());

		panelCentro.revalidate();
		panelCentro.repaint();

		if (modalidad.equals(Controlador.CONTRARRELOJ))
			reiniciarTemporizador();
	}

	private void reiniciarTemporizador() {
		if (temporizador != null && temporizador.isRunning()) {
			temporizador.stop();
		}
		segRestantes = 10;
		lblTemporizador.setVisible(true);
		actualizarEtiquetaTemporizador();

		if (temporizador != null && temporizador.isRunning()) {
			temporizador.stop();
		}

		temporizador = new Timer(1000, e -> {
			segRestantes--;
			actualizarEtiquetaTemporizador();

			if (segRestantes <= 0) {
				temporizador.stop();
				avanzarPregunta();
			}
		});
		temporizador.start();
	}

	private void actualizarEtiquetaTemporizador() {
		lblTemporizador.setText("Tiempo restante: " + segRestantes + " s");
	}

	private void avanzarPregunta() {
		recogerRespuestaActual(); // Obtener la respuesta antes de pasar
		if (indiceActual < preguntas.size() - 1) {
			indiceActual++;
			mostrarPregunta();
		} else {
			JOptionPane.showMessageDialog(null,
					"Fin del test - modalidad: " + modalidad + "\n" + controlador.terminarCurso(curso));
			btnSiguiente.setEnabled(false);
			frame.dispose();
		}
	}

	private void recogerRespuestaActual() {
		if (vistaPreguntaActual != null) {
			String respuesta = vistaPreguntaActual.obtenerRespuesta();
			controlador.corregirPregunta(curso, preguntaActual, respuesta);
		}
	}
}
