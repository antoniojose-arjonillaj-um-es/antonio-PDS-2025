package vistas;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

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
	private int indiceActual;

	private JFrame frame;
	private JPanel panelCentro;
	private JButton btnSiguiente;

	// Constructor
	public VentanaTest(Controlador controlador, Usuario usuario, Curso curso, String modalidad) {

		this.controlador = controlador;
		this.usuario = usuario;
		this.curso = curso;

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
		JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelBoton.add(btnSiguiente);
		contentPane.add(panelBoton, BorderLayout.SOUTH);

		mostrarPregunta();

		btnSiguiente.addActionListener(e -> {
			if (indiceActual < curso.getPreguntas().size() - 1) {
				indiceActual++;
				mostrarPregunta();
			} else {
				JOptionPane.showMessageDialog(null, "Fin del test.");
				btnSiguiente.setEnabled(false);
				frame.dispose();
			}
		});

		// Finalmente mostramos la ventana
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	private void mostrarPregunta() {
		panelCentro.removeAll();

		Box boxVertical = Box.createVerticalBox();
	    boxVertical.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		Pregunta pregunta = curso.getPreguntas().get(indiceActual);
		if (pregunta instanceof Test) {
			mostrarPreguntaTest((Test) pregunta, boxVertical);
			
		} else if (pregunta instanceof Relleno) {
			mostrarPreguntaRelleno((Relleno) pregunta, boxVertical);
			
		} else if (pregunta instanceof Traduccion) {
			mostrarPreguntaTraduccion((Traduccion) pregunta, boxVertical);
			
		} else {
			JOptionPane.showMessageDialog(null, "Tipo de pregunta no soportada", "Error", JOptionPane.WARNING_MESSAGE);
		
		}

		// Pegamos la caja vertical al centro verticalmente usando glue
	    panelCentro.add(Box.createVerticalGlue());
	    panelCentro.add(boxVertical);
	    panelCentro.add(Box.createVerticalGlue());
	    
		panelCentro.revalidate();
		panelCentro.repaint();
	}

	private void mostrarPreguntaTest(Test pregunta, Box box) {
		
		JLabel enunciado = new JLabel(pregunta.getPregunta());
		enunciado.setAlignmentX(Component.CENTER_ALIGNMENT);
		box.add(enunciado);
		box.add(Box.createVerticalStrut(10));


		ButtonGroup grupo = new ButtonGroup();
		for (String opcion : pregunta.getOpciones()) {
			JRadioButton radio = new JRadioButton(opcion);
	        radio.setAlignmentX(Component.CENTER_ALIGNMENT);
	        grupo.add(radio);
	        box.add(radio);
		}
	}

	private void mostrarPreguntaRelleno(Relleno pregunta, Box box) {
		
		JLabel enunciado1 = new JLabel(pregunta.getPregunta());
		enunciado1.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JTextField texto = new JTextField(20);
		texto.setMaximumSize(texto.getPreferredSize());
		texto.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JLabel enunciado2 = new JLabel(pregunta.getSegundaPreg());
		enunciado2.setAlignmentX(Component.CENTER_ALIGNMENT);

		box.add(enunciado1);
		box.add(Box.createVerticalStrut(20));
		box.add(texto);
		box.add(Box.createVerticalStrut(20));
		box.add(enunciado2);
	}

	private void mostrarPreguntaTraduccion(Traduccion pregunta, Box box) {
		
		JLabel enunciado = new JLabel(pregunta.getPregunta());
		enunciado.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JTextField texto = new JTextField(30);
		texto.setMaximumSize(texto.getPreferredSize());
		texto.setAlignmentX(Component.CENTER_ALIGNMENT);

		box.add(enunciado);
		box.add(Box.createVerticalStrut(20));
		box.add(texto);
	}
}
