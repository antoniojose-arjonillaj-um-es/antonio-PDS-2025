package vistas;

import javax.swing.*;
import java.awt.Component;

import modelo.Relleno;

public class VistaPreguntaRelleno implements VistaPregunta {
	// Atributos
	private Relleno pregunta;
	private JTextField txtActual;

	// Constructor
	public VistaPreguntaRelleno(Relleno pregunta) {
		this.pregunta = pregunta;
	}
	
	// Métodos de clase
	@Override
	public void mostrar(Box contenedor) {
		
		JLabel enunciado1 = new JLabel(pregunta.getPregunta());
		enunciado1.setAlignmentX(Component.CENTER_ALIGNMENT);

		txtActual = new JTextField(20);
		txtActual.setMaximumSize(txtActual.getPreferredSize());
		txtActual.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel enunciado2 = new JLabel(pregunta.getSegundaPreg());
		enunciado2.setAlignmentX(Component.CENTER_ALIGNMENT);

		contenedor.add(enunciado1);
		contenedor.add(Box.createVerticalStrut(20));
		contenedor.add(txtActual);
		contenedor.add(Box.createVerticalStrut(20));
		contenedor.add(enunciado2);
	}

	@Override
	public String obtenerRespuesta() {
		return txtActual.getText().trim();
	}
}
