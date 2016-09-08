package up.light.parser;

import up.light.io.IResource;

public interface IParser<T> {
	T parse(IResource res);
}
