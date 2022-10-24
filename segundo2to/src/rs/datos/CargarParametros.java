package datos;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CargarParametros {

	private static String archivoUsuario;
	private static String archivoRelacion;
	private static String archivoRelacionTest;
	private static String archivoUsuarioTest;

	public static void parametros() throws IOException {

		Properties prop = new Properties();
		InputStream input = new FileInputStream("config.properties");
		// load a properties file
		prop.load(input);
		// get the property value
		archivoUsuario = prop.getProperty("usuario");
		archivoRelacion = prop.getProperty("relaciones");
		archivoUsuarioTest = prop.getProperty("usuarioTest");
		archivoRelacionTest = prop.getProperty("relacionesTest");

	}

	public static String getArchivoUsuario() {
		return archivoUsuario;
	}

	public static String getArchivoRelacion() {
		return archivoRelacion;
	}

	public static String getArchivoUsuarioTest() {
		return archivoUsuarioTest;
	}

	public static String getArchivoRelacionTest() {
		return archivoRelacionTest;
	}

}
