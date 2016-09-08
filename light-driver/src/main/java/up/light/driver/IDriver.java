package up.light.driver;

import java.util.Set;

import org.openqa.selenium.WebDriver;

public interface IDriver {
	/**
	 * 获取真实的Driver对象，如SelendroidDriver、IOSDriver、AndroidDriver等
	 * @return 真实的Driver对象
	 */
	<T extends WebDriver> T getRealDriver();

	/**
	 * 获取当前上下文名称
	 * @return 当前上下文名称
	 */
	String getCurrentContext();
	
	/**
	 * 获取所有上下文名称
	 * @return 包含所有上下文名称的集合
	 */
	Set<String> getContexts();

	/**
	 * 切换到指定上下文
	 * @param context 上下文名称
	 */
	void context(String context);

	/**
	 * 屏幕点击坐标
	 * @param x
	 * @param y
	 * @param duration
	 */
	void tap(int x, int y, int duration);
	
	/**
	 * 屏幕滑动
	 * @param startx
	 * @param starty
	 * @param endx
	 * @param endy
	 * @param duration
	 */
	void swipe(int startx, int starty, int endx, int endy, int duration);
}
