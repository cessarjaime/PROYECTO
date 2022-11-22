package rs.conexion;

import java.util.Hashtable;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

public class Factory {
	final static Logger logger = Logger.getLogger(Factory.class);
	private static Hashtable<String, Object> instancias = new Hashtable<String, Object>();

	public static Object getInstancia(String objName) {
		try {
			// verifico si existe un objeto relacionado a objName
			// en la hashtable
			Object obj = instancias.get(objName);
			// si no existe entonces lo instancio y lo agrego
			if (obj == null) {
				ResourceBundle rb = ResourceBundle.getBundle("factory");
				String sClassname = rb.getString(objName);
				obj = Class.forName(sClassname).newInstance();
				// agrego el objeto a la hashtable
				logger.debug(obj);
			}
			return obj;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	}
}