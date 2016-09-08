package up.light.utils;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;

import up.light.Setting;

public class LogUtil {
	private static final String DEFAULT_LOG_PATTERN = "[%d{yyyy-MM-dd HH:mm:ss,SSS}][%p] %C{1}:%L - %m%n";
	private static final String DEFAULT_FILENAME_PATTERN = "'log.'yyyy.MM.dd-HH.mm.ss'.txt'";
	private static final Logger log;
	private static boolean inited;

	static {
		String path = Setting.getConfigPath();
		File xml = new File(path + "log4j.xml");
		File prop = new File(path + "log4j.properties");

		if (xml.exists()) {
			DOMConfigurator.configure(path + "log4j.xml");
			inited = true;
		} else if (prop.exists()) {
			PropertyConfigurator.configure(path + "log4j.properties");
			inited = true;
		}

		log = Logger.getLogger(LogUtil.class);

		if (!inited) {
			log.setLevel(Level.INFO);
			Layout layout = new PatternLayout(DEFAULT_LOG_PATTERN);

			try {
				log.addAppender(new IndependentFileAppender(layout, Setting.getLogPath(), DEFAULT_FILENAME_PATTERN));
			} catch (IOException e) {
				throw new RuntimeException("can't create log file");
			}
		}
	}

	public static void trace(Object message) {
		log.trace(message);
	}

	public static void trace(Object message, Throwable t) {
		log.trace(message, t);
	}

	public static void debug(Object message) {
		log.debug(message);
	}

	public static void debug(Object message, Throwable t) {
		log.debug(message, t);
	}

	public static void info(Object message) {
		log.info(message);
	}

	public static void info(Object message, Throwable t) {
		log.info(message, t);
	}

	public static void warn(Object message) {
		log.warn(message);
	}

	public static void warn(Object message, Throwable t) {
		log.warn(message, t);
	}

	public static void error(Object message) {
		log.error(message);
	}

	public static void error(Object message, Throwable t) {
		log.error(message, t);
	}

	public static void fatal(Object message) {
		log.fatal(message);
	}

	public static void fatal(Object message, Throwable t) {
		log.fatal(message, t);
	}
}
