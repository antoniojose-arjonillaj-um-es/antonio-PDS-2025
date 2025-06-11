package umu.pds;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

import modelo.Curso;
import modelo.Pregunta;
import modelo.Traduccion;
import modelo.Usuario;
import vistas.VentanaPrincipal;

public class Lanzador {
	public static void main(String[] args) {
		// Aseguramos que la interfaz gráfica se inicie en el hilo de eventos de Swing
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Controlador controlador = Controlador.getInstancia();
//				new VentanaLogin(controlador);
				placeholder(controlador);
			}
		});
	}
	
	public static void placeholder(Controlador c) {
		Usuario user = new Usuario("antonio", "",
				"https://www.eldiasegovia.es/media/IMG/2019/95AD3DCE-A543-813D-2367FD44E4DBBB0D.JPG", 0);
		
		Pregunta pregunta = new Traduccion("Hola", "Hola");
		List<Pregunta> lp = new ArrayList<Pregunta>();
		lp.add(pregunta);
		
		List<Curso> lista = new ArrayList<Curso>();
		Curso prim = new Curso("Ingles", lp);
		lista.add(prim);
		prim = new Curso("Frances", lp);
		lista.add(prim);
		prim = new Curso("Portugues", lp);
		lista.add(prim);
		user.setCursos(lista);

		new VentanaPrincipal(c, user);
	}
}
