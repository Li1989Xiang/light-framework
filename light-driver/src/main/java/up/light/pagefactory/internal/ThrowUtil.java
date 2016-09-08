package up.light.pagefactory.internal;

import java.lang.reflect.InvocationTargetException;

public class ThrowUtil {

	static Throwable extractReadableException(Throwable e) {
		if (!RuntimeException.class.equals(e.getClass()) && !InvocationTargetException.class.equals(e.getClass())) {
			return e;
		}

		return extractReadableException(e.getCause());
	}
}
