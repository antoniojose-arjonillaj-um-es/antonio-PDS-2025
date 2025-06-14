package modelo;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Estado {
    EN_PROCESO,
    SIN_EMPEZAR,
    FINALIZADO,
    PAUSADO;

    @JsonCreator
    public static Estado fromString(String value) {
        switch (value.toLowerCase()) {
            case "en_proceso":
            	return EN_PROCESO;
            case "sin_empezar":
            	return SIN_EMPEZAR;
            case "finalizado":
            	return FINALIZADO;
            case "pausado":
            	return PAUSADO;
            default:
            	throw new IllegalArgumentException("Estado desconocido: " + value);
        }
    }
}
