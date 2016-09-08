package up.light;

import java.io.File;

import up.light.io.FileSystemResource;
import up.light.xml.XmlParser;
import up.light.xml.bean.XmlLight;

public class Light {

	public static void main(String[] args) {
		String file;
		
		if(args.length == 1 && args[0].endsWith("light.xml")) {
			file = args[0];
		} else {
			file = "target/classes/resources/light.xml";
		}
		
		Light light = new Light();
		light.run(file);
	}

	public void run(String file) {
		File f = new File(file);
		if(!f.exists()) {
			System.err.println("ERROR: " + file + " is not exists.");
			return;
		}
		
		XmlParser parser = new XmlParser();
		XmlLight light = parser.parse(new FileSystemResource(f));
		System.out.println(light);
	}
}
