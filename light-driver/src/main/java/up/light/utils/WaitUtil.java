package up.light.utils;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import up.light.driver.IDriver;

/**
 * 等待操作工具类
 */
public abstract class WaitUtil {

	public static int WAIT_SHORT = 1;
	public static int WAIT_MEDIUM = 5;
	public static int WAIT_LONG = 10;

	/**
	 * 判断指定的元素是否存在（可见）
	 * 
	 * @param driver
	 *            IDriver对象
	 * @param e
	 *            要判断的元素
	 * @param seconds
	 *            等待时间
	 * @return 元素可见时返回{@code true}， 否则返回{@code false}
	 */
	public static boolean exists(final IDriver driver, final WebElement e, int seconds) {
		boolean ret = false;

		try {
			ret = new WebDriverWait(driver.getRealDriver(), seconds).until(new ExpectedCondition<Boolean>() {
				@Override
				public Boolean apply(WebDriver input) {
					return e.isDisplayed();
				}
			});
		} catch (TimeoutException ex) {
		}

		return ret;
	}

	/**
	 * 等待指定的元素出现（可见）
	 * 
	 * @param driver
	 *            IDriver对象
	 * @param e
	 *            要等待的元素
	 * @param seconds
	 *            等待时间
	 */
	public static void waitFor(final IDriver driver, final WebElement e, int seconds) {
		new WebDriverWait(driver.getRealDriver(), seconds).until(new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver input) {
				return e.isDisplayed();
			}

			@Override
			public String toString() {
				return "visibility of element " + e;
			}
		});
	}

	/**
	 * 等待指定的元素消失
	 * 
	 * @param driver
	 *            IDriver对象
	 * @param e
	 *            要等待的元素
	 * @param seconds
	 *            等待时间
	 */
	public static void untilGone(final IDriver driver, final WebElement e, int seconds) {
		new WebDriverWait(driver.getRealDriver(), seconds).until(new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver input) {
				Boolean ret = Boolean.FALSE;
				try {
					ret = (e.isDisplayed() == false);
				} catch (NoSuchElementException | StaleElementReferenceException e) {
					ret = Boolean.TRUE;
				}
				return ret;
			}

			@Override
			public String toString() {
				return "invisibility of element " + e;
			}
		});
	}

	/**
	 * 等待元素的属性值达到指定的条件
	 * 
	 * @param driver
	 *            IDriver对象
	 * @param e
	 *            要等待的元素
	 * @param seconds
	 *            等待时间
	 * @param attributeName
	 *            属性名
	 * @param expectValue
	 *            期望值，不需要时可传入{@code null}
	 * @param condition
	 *            要达到的条件
	 */
	public static void waitForAttribute(final IDriver driver, final WebElement e, int seconds, String attributeName,
			String expectValue, ICondition condition) {
		new WebDriverWait(driver.getRealDriver(), seconds).until(new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver input) {
				String actual = e.getAttribute(attributeName);
				return condition.isTrue(expectValue, actual);
			}

			@Override
			public String toString() {
				return "attribute meeting condition";
			}
		});
	}

	/**
	 * 等待元素的Text值达到指定的条件
	 * 
	 * @param driver
	 *            IDriver对象
	 * @param e
	 *            要等待的元素
	 * @param seconds
	 *            等待时间
	 * @param expectValue
	 *            期望值，不需要时可传入{@code null}
	 * @param condition
	 *            要达到的条件
	 */
	public static void waitForText(final IDriver driver, final WebElement e, int seconds, String expectValue,
			ICondition condition) {
		new WebDriverWait(driver.getRealDriver(), seconds).until(new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver input) {
				String actual = e.getText();
				return condition.isTrue(expectValue, actual);
			}

			@Override
			public String toString() {
				return "text meeting condition";
			}
		});
	}

	/**
	 * 等待指定时间
	 * 
	 * @param millis
	 *            等待毫秒数
	 */
	public static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

}
