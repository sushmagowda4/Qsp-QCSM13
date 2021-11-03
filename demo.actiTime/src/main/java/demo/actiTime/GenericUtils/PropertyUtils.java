package demo.actiTime.GenericUtils;

import java.io.FileInputStream;
import java.util.Properties;
/**
 * This utility returns the property file data as per the key provided
 * @author SUSHMA GOWDA
 *
 */
public class PropertyUtils {
	public String getPropertyData(String filepath, String key) {
		FileInputStream file;
		try {
			file = new FileInputStream(filepath);
			Properties properties = new Properties();
			properties.load(file);
			return properties.getProperty(key);			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
}
