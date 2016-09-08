package up.light.pagefactory.internal;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.FieldDecorator;

import up.light.Setting;
import up.light.driver.IDriver;
import up.light.exceptions.LightReflectionException;
import up.light.utils.ArgumentUtil;
import up.light.utils.ClassUtil;

public class LightFieldDecorator implements FieldDecorator {
	private static final String TAG_PROXY = "proxy.target";
	private static final String TAG_FIND = "type.find";

	private ElementLocatorFactory factory;
	private WebDriver originalDriver;

	public LightFieldDecorator(IDriver driver) {
		this.originalDriver = driver.getRealDriver();
		this.factory = new LightLocatorFactory(driver, getFindType());
	}

	@Override
	public Object decorate(ClassLoader loader, Field field) {
		if (!(WebElement.class.isAssignableFrom(field.getType()) || isDecoratableList(field))) {
			return null;
		}

		ElementLocator locator = factory.createLocator(field);
		if (locator == null) {
			return null;
		}

		if (WebElement.class.isAssignableFrom(field.getType())) {
			return proxyForLocator(loader, locator);
		} else if (List.class.isAssignableFrom(field.getType())) {
			return proxyForListLocator(loader, locator);
		} else {
			return null;
		}
	}

	private boolean isDecoratableList(Field field) {
		if (!List.class.isAssignableFrom(field.getType())) {
			return false;
		}

		Type genericType = field.getGenericType();
		if (!(genericType instanceof ParameterizedType)) {
			return false;
		}

		Type listType = ((ParameterizedType) genericType).getActualTypeArguments()[0];

		if (listType instanceof Class) {
			Class<?> c = (Class<?>) listType;

			if (WebElement.class.isAssignableFrom(c)) {
				return true;
			}
		}

		return false;
	}

	@SuppressWarnings("unchecked")
	private List<WebElement> proxyForListLocator(ClassLoader loader, ElementLocator locator) {
		ProxyList proxy = new ProxyList(locator);
		return ProxyFactory.getEnhancedProxy(ArrayList.class, proxy);
	}

	private Object proxyForLocator(ClassLoader loader, ElementLocator locator) {
		ProxySingle proxy = new ProxySingle(originalDriver, locator);
		return (WebElement) ProxyFactory.getEnhancedProxy(getTypeForProxy(), proxy);
	}

	private Class<?> getTypeForProxy() {
		String name = Setting.getProperty(TAG_PROXY);

		if (StringUtils.isBlank(name)) {
			return RemoteWebElement.class;
		}

		Class<?> clazz = null;

		try {
			clazz = ClassUtil.getClass(name);
			ArgumentUtil.isInstance(clazz, WebElement.class, "Class must be an implemention of WebElement");
		} catch (LightReflectionException e) {
			throw new RuntimeException("can't get class of " + name, e.getCause());
		}

		return clazz;
	}

	private String getFindType() {
		String type = Setting.getProperty(TAG_FIND);

		if (StringUtils.isBlank(type)) {
			type = "context";
		}

		return type;
	}
}
