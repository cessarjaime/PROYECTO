package rs.datos;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CargarParametros {

	private static String archivoUsuario;
	private static String archivoRelacion;
	

	public static void parametros() throws IOException {
		
		Properties prop = new Properties();		
			InputStream input = new FileInputStream("config.properties");
			// load a properties file
			prop.load(input);
			// get the property value
			archivoUsuario = prop.getProperty("usuario");		
			archivoRelacion = prop.getProperty("relaciones");
			
	}

	public static String getArchivoUsuario() {
		return archivoUsuario;
	}

	public static String getArchivoRelacion() {
		return archivoRelacion;
	}

	
}
