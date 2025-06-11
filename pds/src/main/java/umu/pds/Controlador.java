package umu.pds;

import java.util.List;

import modelo.Usuario;
import vistas.VentanaLogin;

/**
 * Hello world!
 *
 */
public class Controlador 
{
    public static final int ACIERTO = 0;
	public static final int ERROR_TLF = 1;
	public static final int ERROR_NOREPE = 2;

	public Controlador() {
		new VentanaLogin(this);
	}
	
	public static void main( String[] args )
    {
        new Controlador();
    }

	public Usuario aceptarLogin(String nombreUs, String contraseña) {
		// TODO Auto-generated method stub
		return null;
	}

	public int registrarUsuario(List<String> datos) {
		// TODO Auto-generated method stub
		return 0;
	}
}
