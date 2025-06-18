package copialingo;

import javax.swing.SwingUtilities;

import vistas.VentanaLogin;

public class Lanzador {
	public static void main(String[] args) {
		// Aseguramos que la interfaz gráfica se inicie en el hilo de eventos de Swing
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Controlador controlador = Controlador.getInstancia();
				new VentanaLogin(controlador);
			}
		});
	}
}
