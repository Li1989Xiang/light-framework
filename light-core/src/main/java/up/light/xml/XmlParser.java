package up.light.xml;

import java.net.URL;

import javax.xml.XMLConstants;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import up.light.io.FileSystemResource;
import up.light.xml.bean.XmlLight;

public class XmlParser {
	private URL xsd;
	
	public XmlParser() {
		xsd = this.getClass().getResource("/resources/light.xsd");
	}
	
	public XmlLight parse(FileSystemResource file) {
		SAXParserFactory factory = SAXParserFactory.newInstance();  
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		XmlLight light = null;
		
        try {
			Schema schema = schemaFactory.newSchema(xsd);
			factory.setSchema(schema);
			factory.setNamespaceAware(true);
			SAXParser parser = factory.newSAXParser();
			LightHandler handler = new LightHandler();
			parser.parse(file.getInputStream(), handler);
			light = handler.getXmlLight();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
        
		return light;
	}
}
