package up.light;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

public class Setting {
	private static Map<String, String> mProperties = new HashMap<>();

	private static final String RUN_PLATFORM = "run.platform";
	private static final String PATH_PLATFORM = "path.platform";
	private static final String PATH_ROOT = "path.root";
	private static final String PATH_CONFIG = "path.config";
	private static final String PATH_LOG = "path.log";
	private static final String PATH_REPO = "path.repo";
	private static final String TYPE_REPO = "type.repo";

	static {
		System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");
	}

	public static Map<String, String> getProperties() {
		return mProperties;
	}

	public static void setProperties(Map<String, String> properties) {
		handleMap(properties);
		mProperties.putAll(properties);
	}

	public static String getProperty(String name) {
		return mProperties.get(name);
	}

	public static String getRootPath() {
		return mProperties.get(PATH_ROOT);
	}

	public static String getPlatformPath() {
		return mProperties.get(PATH_PLATFORM);
	}

	public static String getConfigPath() {
		return getPlatformPath() + mProperties.get(PATH_CONFIG);
	}

	public static String getRepoPath() {
		return getPlatformPath() + mProperties.get(PATH_REPO);
	}

	public static String getRepoType() {
		return mProperties.get(TYPE_REPO);
	}

	public static String getLogPath() {
		return getPlatformPath() + mProperties.get(PATH_LOG);
	}

	public static String getRunPlatform() {
		String p = mProperties.get(RUN_PLATFORM);

		if (p == null) {
			throw new RuntimeException(RUN_PLATFORM + " was not setted");
		}

		return p;
	}

	private static void handleMap(Map<String, String> map) {
		// platform must be setted
		String platform = map.get(RUN_PLATFORM);

		if (StringUtils.isBlank(platform)) {
			throw new RuntimeException(RUN_PLATFORM + " was not setted");
		}

		for (SettingItem i : SettingItem.values()) {
			String key = i.getName();
			String s = map.get(key);

			if (StringUtils.isBlank(s)) {
				map.put(key, i.defaultValut());
			} else {
				map.put(key, i.handle(s));
			}
		}

		// ignore the path of platform, it will be setted programmically
		String path = map.get(PATH_ROOT);
		map.put(PATH_PLATFORM, path + platform + File.separator);

		// add configuration in ${platform} folder
		Properties p = new Properties();
		path = map.get(PATH_PLATFORM) + map.get(PATH_CONFIG);
		
		try (FileInputStream fis = new FileInputStream(path + "config.properties")) {
			p.load(fis);
		} catch (IOException e) {
			System.err.println("WARN: config.properties don't exist, default values will be used.");
		}
		
		for(Map.Entry<Object, Object> e : p.entrySet()) {
			String key = null;
			String value = (String)e.getValue();
			
			if(!StringUtils.isBlank(value)) {
				key = (String)e.getKey();
				map.put(key, value);
			}
		}
	}
}
