package up.light.pagefactory.internal;

import java.lang.reflect.Field;

import up.light.driver.IDriver;

class ContextElementLocator extends AbstractElementLocator {

	public ContextElementLocator(IDriver driver, Field field) {
		super(driver, field);
	}

	@Override
	protected void handleContext(String param) {
		if("".equals(param)) {
			return;
		}
		
		driver.context("NATIVE_APP");
		
		if("NATIVE_APP".equals(param)) {
			return;
		}
		
		driver.context(param);
	}

}
