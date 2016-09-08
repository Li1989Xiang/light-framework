package up.light.pagefactory.internal;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class ProxyList implements MethodInterceptor {
	private ElementLocator locator;

	public ProxyList(ElementLocator locator) {
		this.locator = locator;
	}

	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		if (Object.class.equals(method.getDeclaringClass())) {
            return proxy.invokeSuper(obj, args);
        }

        ArrayList<WebElement> realElements = new ArrayList<WebElement>();
        realElements.addAll(locator.findElements());
        Object ret = null;
        
		try {
            ret = method.invoke(realElements, args);
        } catch (Throwable t) {
        	ThrowUtil.extractReadableException(t);
        }
		
		return ret;
	}

}
