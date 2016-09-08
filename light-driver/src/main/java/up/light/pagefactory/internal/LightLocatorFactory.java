package up.light.pagefactory.internal;

import java.lang.reflect.Field;

import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import up.light.driver.IDriver;

public class LightLocatorFactory implements ElementLocatorFactory {
	private IDriver driver;
	private String findType;
	
	public LightLocatorFactory(IDriver driver, String findType) {
		this.driver = driver;
		this.findType = findType;
	}

	@Override
	public ElementLocator createLocator(Field field) {
		if("context".equalsIgnoreCase(findType)) {
			return new ContextElementLocator(driver, field);
		}
		
		if("url".equalsIgnoreCase(findType)) {
			return new UrlElementLocator(driver, field);
		}
		
		throw new RuntimeException("unsupport find type: " + findType);
	}

}
