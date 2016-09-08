package up.light;

import java.lang.reflect.Method;

public interface IStep extends IConfigurable {
	IKeyword getKeyword();

	Method getCall();
}
