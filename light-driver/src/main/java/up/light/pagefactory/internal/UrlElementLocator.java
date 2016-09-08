package up.light.pagefactory.internal;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.NoSuchElementException;

import up.light.driver.IDriver;
import up.light.utils.ArgumentUtil;

class UrlElementLocator extends AbstractElementLocator {
	private static final String PREFIX = "kds519/view/";
	private static String currentUrlPattern;

	public UrlElementLocator(IDriver driver, Field field) {
		super(driver, field);
		currentUrlPattern = getUrl(null);
	}

	@Override
	protected void handleContext(String param) {
		if (currentUrlPattern.equals(param) || "".equals(param)) {
			return;
		}

		Set<String> cs = driver.getContexts();
		
		for(String c : sortDesc(cs)) {
			if(matchUrl(param, getUrl(c))) {
				currentUrlPattern = param;
				return;
			}
		}
		
		throw new NoSuchElementException("can't find context whose url matches " + param);
	}

	private String getUrl(String context) {
		String current = driver.getCurrentContext();
		
		if(context != null) {
			if(!current.equals(context)) {
				driver.context(context);
				current = context;
			}
		}

		if ("NATIVE_APP".equals(current)) {
			return "NATIVE_APP";
		}
		
		String url = driver.getRealDriver().getCurrentUrl();
		return url.substring(url.indexOf(PREFIX) + PREFIX.length());
	}
	
	private List<String> sortDesc(Set<String> set) {
		String[] arr = new String[set.size()];
		set.toArray(arr);
		
		Arrays.sort(arr, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				//字母在前，数字在后；数字按照其数值大小从小到大排序
				if(o1 == null || o2 == null) {
					throw new NullPointerException();
				}
				
				int i1 = o1.lastIndexOf("_");
				int i2 = o2.lastIndexOf("_");
				Float io1 = null, io2 = null;
				
				try {
					io1 = Float.valueOf(o1.substring(i1 + 1));
				} catch (NumberFormatException e) {
				}
				
				try {
					io2 = Float.valueOf(o2.substring(i2 + 1));
				} catch (NumberFormatException e) {
				}
				
				if(io1 != null) {
					if(io2 != null) {
						// io1是数字，io2是数字
						return Float.compare(io1, io2);
					} else {
						// io1是数字，io2是字母
						return 1;
					}
				} else {
					if(io2 != null) {
						// io1是字母，io2是数字
						return -1;
					} else {
						// io1是字母，io2是字母
						return o1.compareTo(o2);
					}
				}
			}
		});
		
		List<String> list = Arrays.asList(arr);
		//倒序
		Collections.reverse(list);
		return list;
	}
	
	private boolean matchUrl(String pattern, String url) {
		ArgumentUtil.notNull(pattern, "pattern must not be null");
		ArgumentUtil.notNull(url, "url must not be null");
		
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(url);
		return m.matches();
	}
}
