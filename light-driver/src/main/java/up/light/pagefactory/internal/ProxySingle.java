package up.light.pagefactory.internal;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.WrapsDriver;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class ProxySingle implements MethodInterceptor {
	private ElementLocator locator;
	private WebDriver driver;
	
	public ProxySingle(WebDriver driver, ElementLocator locator) {
		this.locator = locator;
		this.driver = driver;
	}

	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		if (Object.class.equals(method.getDeclaringClass())) {
			return proxy.invokeSuper(obj, args);
		}

		if (WrapsDriver.class.isAssignableFrom(method.getDeclaringClass())
				&& method.getName().equals("getWrappedDriver")) {
			return driver;
		}

		WebElement realElement = locator.findElement();
		Object ret = null;
		
		try {
			ret = method.invoke(realElement, args);
		} catch (Throwable t) {
			ThrowUtil.extractReadableException(t);
		}
		
		return ret;
	}

}
