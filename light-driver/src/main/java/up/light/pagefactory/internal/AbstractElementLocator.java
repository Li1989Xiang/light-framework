package up.light.pagefactory.internal;

import java.lang.reflect.Field;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import up.light.driver.IDriver;
import up.light.repository.LocatorBean;
import up.light.repository.Repository;
import up.light.utils.ArgumentUtil;

abstract class AbstractElementLocator implements ElementLocator {
	protected IDriver driver;
	private Field field;
	private LocatorBean bean;
	private boolean shouldCache;
	private WebElement cachedElement;

	public AbstractElementLocator(IDriver driver, Field field) {
		this.driver = driver;
		this.field = field;
		shouldCache = isLookupCached(field);
		bean = Repository.getLocatorBean(field);
		ArgumentUtil.notNull(bean, "can't get LocatorBean use name " + getDescription());
	}

	@Override
	public WebElement findElement() {
		if (cachedElement != null && shouldCache) {
			return cachedElement;
		}

		handleContext(bean.getIn());
		List<By> ls = bean.getBys();
		WebElement ele = null;

		for (By b : ls) {
			try {
				ele = driver.getRealDriver().findElement(b);

				if (shouldCache) {
					cachedElement = ele;
				}

				return ele;
			} catch (NoSuchElementException e) {
				// do nothing
			}
		}

		throw new NoSuchElementException(
				"can't find element use bys: " + ls + ", current context is " + driver.getCurrentContext());
	}

	@Override
	public List<WebElement> findElements() {
		// findElements don't support multiple locators;
		handleContext(bean.getIn());
		return driver.getRealDriver().findElements(bean.getBys().get(0));
	}

	private boolean isLookupCached(Field f) {
		if (f.getAnnotation(CacheLookup.class) != null) {
			return true;
		}
		
		return false;
	}

	private String getDescription() {
		return field.getName() + " in class " + field.getDeclaringClass().getName();
	}

	abstract protected void handleContext(String param);
}