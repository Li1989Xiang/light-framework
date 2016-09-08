package up.light.driver;

import up.light.Setting;
import up.light.exceptions.LightReflectionException;
import up.light.utils.ArgumentUtil;
import up.light.utils.ClassUtil;

public class DriverFactory {
	private static IDriver driver;

	public static IDriver getDriver() {
		if (driver == null) {
			String name = Setting.getProperty("driver.class");
			Class<?> clazz = null;
			Object o = null;

			try {
				clazz = ClassUtil.getClass(name);
				ArgumentUtil.isInstance(clazz, IDriver.class, "Class must be an implemention of up.light.driver.IDriver");
				o = ClassUtil.getInstance(clazz);
				driver = (IDriver) o;
			} catch (LightReflectionException e) {
				throw new RuntimeException("can't create driver: " + name, e.getCause());
			}
		}

		return driver;
	}
	
	public static void close() {
		if(driver != null) {
			driver.getRealDriver().quit();
		}
	}
}
