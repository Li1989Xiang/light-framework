package up.light;

import up.light.data.IRow;

public interface IResult {
	void setStatus(Status status);

	Status getStatus();

	void putText(String name, String value);

	String getText(String name);

	void putBinary(String name, byte[] value);

	byte[] getBinary(String name);

	boolean isBinary();

	IWork getWork();

	IStep getStep();
	
	Throwable getThrowable();
	
	IRow getData();
}
