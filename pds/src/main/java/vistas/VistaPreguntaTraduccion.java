package vistas;

import javax.swing.*;
import java.awt.Component;

import modelo.Traduccion;

public class VistaPreguntaTraduccion implements VistaPregunta {
	// Métodos de clase
	private Traduccion pregunta;
	private JTextField txtActual;

	// Constructor
	public VistaPreguntaTraduccion(Traduccion pregunta) {
		this.pregunta = pregunta;
	}

	// Métodos de clase
	@Override
	public void mostrar(Box box) {

		JLabel enunciado = new JLabel(pregunta.getPregunta());
		enunciado.setAlignmentX(Component.CENTER_ALIGNMENT);

		txtActual = new JTextField(30);
		txtActual.setMaximumSize(txtActual.getPreferredSize());
		txtActual.setAlignmentX(Component.CENTER_ALIGNMENT);

		box.add(enunciado);
		box.add(Box.createVerticalStrut(20));
		box.add(txtActual);
	}

	@Override
	public String obtenerRespuesta() {
		return txtActual.getText().trim();
	}
}
