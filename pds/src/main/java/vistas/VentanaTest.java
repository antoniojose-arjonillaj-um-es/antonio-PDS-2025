package vistas;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.Timer;

import modelo.Curso;
import modelo.Pregunta;
import modelo.Relleno;
import modelo.Test;
import modelo.Traduccion;
import modelo.Usuario;
import umu.pds.Controlador;

public class VentanaTest {
	// Atributos
	private Controlador controlador;
	private Usuario usuario;
	private Curso curso;
	private String modalidad;
	private int indiceActual;

	private JFrame frame;
	private JPanel panelCentro;
	private JButton btnSiguiente;

	private List<JCheckBox> checkBoxesActuales; // Para preguntas tipo test (multi)
	private JTextField campoTextoActual; // Para preguntas de traducción o rellenar
	private Pregunta preguntaActual; // Para saber qué tipo de pregunta es

	private List<Pregunta> preguntas; // Lista de preguntas real, según modalidad
	private Timer temporizador;
	
	// Constructor
	public VentanaTest(Controlador controlador, Usuario usuario, Curso curso, String modalidad) {

		this.controlador = controlador;
		this.usuario = usuario;
		this.curso = curso;
		this.modalidad = modalidad;
		indiceActual = 0;

		// Inicializamos frame principal
		frame = new JFrame();
		frame.setTitle("Test de curso de " + curso.getNombre());
		frame.setSize(500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // TODO: Funcion de cierre

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
			recogerRespuestaActual();
			if (indiceActual < curso.getPreguntas().size() - 1) {
				indiceActual++;
				mostrarPregunta();
			} else {
				JOptionPane.showMessageDialog(null, "Fin del test."); // TODO: Añadir correcciones
				btnSiguiente.setEnabled(false);
				frame.dispose();
			}
		});
		JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelBoton.add(btnSiguiente);
		contentPane.add(panelBoton, BorderLayout.SOUTH);
		
		// Preparamos curso según modalidad escogida
		switch (modalidad) {
	    case Controlador.ALEATORIO:
	        preguntas = new ArrayList<>(curso.getPreguntas());
	        Collections.shuffle(preguntas); // Desordenamos preguntas
	        break;

	    case Controlador.CONTRARRELOJ:
	        preguntas = curso.getPreguntas();
	        iniciarTemporizador();  // Lanzamos el temporizador
	        break;

	    default:  // defecto
	        preguntas = curso.getPreguntas();
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
		if (preguntaActual instanceof Test) {
			mostrarPreguntaTest((Test) preguntaActual, box);

		} else if (preguntaActual instanceof Relleno) {
			mostrarPreguntaRelleno((Relleno) preguntaActual, box);

		} else if (preguntaActual instanceof Traduccion) {
			mostrarPreguntaTraduccion((Traduccion) preguntaActual, box);

		} else {
			JOptionPane.showMessageDialog(null, "Tipo de pregunta no soportada", "Error", JOptionPane.WARNING_MESSAGE);

		}

		// Pegamos la caja vertical al centro verticalmente usando glue
		panelCentro.add(Box.createVerticalGlue());
		panelCentro.add(box);
		panelCentro.add(Box.createVerticalGlue());

		panelCentro.revalidate();
		panelCentro.repaint();
		
		if(modalidad.equals(Controlador.CONTRARRELOJ))
			reiniciarTemporizador();
	}

	private void mostrarPreguntaTest(Test pregunta, Box box) {

		JLabel enunciado = new JLabel(pregunta.getPregunta());
		enunciado.setAlignmentX(Component.CENTER_ALIGNMENT);
		box.add(enunciado);
		box.add(Box.createVerticalStrut(10));

		checkBoxesActuales = new ArrayList<>();
		for (String opcion : pregunta.getOpciones()) {
			JCheckBox check = new JCheckBox(opcion);
			check.setAlignmentX(Component.CENTER_ALIGNMENT);
			checkBoxesActuales.add(check);
			box.add(check);
		}
	}

	private void mostrarPreguntaRelleno(Relleno pregunta, Box box) {

		JLabel enunciado1 = new JLabel(pregunta.getPregunta());
		enunciado1.setAlignmentX(Component.CENTER_ALIGNMENT);

		campoTextoActual = new JTextField(20);
		campoTextoActual.setMaximumSize(campoTextoActual.getPreferredSize());
		campoTextoActual.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel enunciado2 = new JLabel(pregunta.getSegundaPreg());
		enunciado2.setAlignmentX(Component.CENTER_ALIGNMENT);

		box.add(enunciado1);
		box.add(Box.createVerticalStrut(20));
		box.add(campoTextoActual);
		box.add(Box.createVerticalStrut(20));
		box.add(enunciado2);
	}

	private void mostrarPreguntaTraduccion(Traduccion pregunta, Box box) {

		JLabel enunciado = new JLabel(pregunta.getPregunta());
		enunciado.setAlignmentX(Component.CENTER_ALIGNMENT);

		campoTextoActual = new JTextField(30);
		campoTextoActual.setMaximumSize(campoTextoActual.getPreferredSize());
		campoTextoActual.setAlignmentX(Component.CENTER_ALIGNMENT);

		box.add(enunciado);
		box.add(Box.createVerticalStrut(20));
		box.add(campoTextoActual);
	}
	
	private void iniciarTemporizador() {
	    temporizador = new Timer(10_000, e -> {avanzarAutomaticamente();});
	    temporizador.setRepeats(false);  // Solo una vez por pregunta
	    temporizador.start();
	}
	
	private void reiniciarTemporizador() {
	    if ("contrarreloj".equalsIgnoreCase(modalidad)) {
	        temporizador.restart();
	    }
	}
	
	private void avanzarAutomaticamente() {
	    recogerRespuestaActual();  // Obtener la respuesta antes de pasar
	    if (indiceActual < preguntas.size() - 1) {
	        indiceActual++;
	        mostrarPregunta();
	    } else {
	        JOptionPane.showMessageDialog(null, "Fin del test (Fin del tiempo).");
	        btnSiguiente.setEnabled(false);
	        frame.dispose();
	    }
	}

	private void recogerRespuestaActual() {
	    String respuestaUsuario = "";

	    if (preguntaActual instanceof Test && checkBoxesActuales != null) {
	        List<String> seleccionadas = new ArrayList<>();
	        for (JCheckBox check : checkBoxesActuales) {
	            if (check.isSelected()) {
	                seleccionadas.add(check.getText());
	            }
	        }
	        respuestaUsuario = String.join(", ", seleccionadas);
	    } else if ((preguntaActual instanceof Traduccion || preguntaActual instanceof Relleno)
	               && campoTextoActual != null) {
	        respuestaUsuario = campoTextoActual.getText().trim();
	    }

	    System.out.println("Respuesta del usuario: " + respuestaUsuario);
	}
}
