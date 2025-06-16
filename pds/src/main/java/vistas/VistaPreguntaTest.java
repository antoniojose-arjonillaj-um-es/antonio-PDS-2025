package vistas;

import javax.swing.*;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import modelo.Test;

public class VistaPreguntaTest implements VistaPregunta {
	// Atributos
	private Test pregunta;
	private List<JCheckBox> checkBoxes;

	// Constructor
	public VistaPreguntaTest(Test pregunta) {
		this.pregunta = pregunta;
		this.checkBoxes = new ArrayList<>();
	}

	// Métodos de clase
	@Override
	public void mostrar(Box contenedor) {
		JLabel enunciado = new JLabel(pregunta.getPregunta());
		enunciado.setAlignmentX(Component.CENTER_ALIGNMENT);
		contenedor.add(enunciado);
		contenedor.add(Box.createVerticalStrut(10));

		List<String> opciones = pregunta.getOpciones();
		
		for (int i = 0; i < opciones.size(); i++) {
			JCheckBox check = new JCheckBox(opciones.get(i));
			check.setAlignmentX(Component.CENTER_ALIGNMENT);
			check.setActionCommand(String.valueOf(i));
			checkBoxes.add(check);
			contenedor.add(check);
		}
	}

	@Override
	public String obtenerRespuesta() {
		List<String> seleccionadas = new ArrayList<>();
		for (JCheckBox check : checkBoxes) {
			if (check.isSelected()) {
				seleccionadas.add(check.getActionCommand());
			}
		}
		return String.join(",", seleccionadas);
	}
}
