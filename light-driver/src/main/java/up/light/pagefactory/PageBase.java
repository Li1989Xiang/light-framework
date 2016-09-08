package up.light.pagefactory;

import org.openqa.selenium.support.PageFactory;

import up.light.driver.DriverFactory;
import up.light.driver.IDriver;
import up.light.pagefactory.internal.LightFieldDecorator;

/**
 * <p>此类使用selenium的PageFactory方法，实现PageObject设计模式</p>
 * 继承此类可实现外部对象库、多重定位功能
 */
public class PageBase {
	protected IDriver driver;
	
	public PageBase() {
		driver = DriverFactory.getDriver();
		PageFactory.initElements(new LightFieldDecorator(driver), this);
	}
}
