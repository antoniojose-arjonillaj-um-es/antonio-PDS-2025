package modelo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.*;

@Entity
public class Test extends Pregunta {

	// Atributo
	@ElementCollection
	private List<Integer> respuestas;

	@ElementCollection
	private List<String> opciones;

	// Constructor
	public Test(String preg, List<String> opc, List<Integer> resp) {
		super(preg);
		this.opciones = opc;
		this.respuestas = resp;
	}

	// Getters
	public List<Integer> getRespuesta() {
		return respuestas;
	}

	public List<String> getOpciones() {
		return opciones;
	}

	// Setters
	public void setRespuesta(List<Integer> res) {
		this.respuestas = res;
	}

	public void setOpciones(List<String> opciones) {
		this.opciones = opciones;
	}

	// Métodos de clase
	@Override
	public boolean corregir(String dato) {
		if (dato.isEmpty() || dato == null)
			return false;
		List<Integer> numeros = Arrays.stream(dato.split(",")).map(Integer::parseInt).collect(Collectors.toList());
		if (numeros.size() != respuestas.size())
			return false;
		for (Integer respuesta : numeros) {
			if (!respuestas.contains(respuesta))
				return false;
		}
		return true;
	}

}
