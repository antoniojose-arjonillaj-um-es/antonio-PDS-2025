package vistas;

import modelo.*;

public class VistaPreguntaFactory {
    public static VistaPregunta crear(Pregunta pregunta) {
        if (pregunta instanceof Test) {
            return new VistaPreguntaTest((Test) pregunta);
        } else if (pregunta instanceof Relleno) {
            return new VistaPreguntaRelleno((Relleno) pregunta);
        } else if (pregunta instanceof Traduccion) {
            return new VistaPreguntaTraduccion((Traduccion) pregunta);
        } else {
            throw new IllegalArgumentException("Tipo de pregunta no soportado");
        }
    }
}
