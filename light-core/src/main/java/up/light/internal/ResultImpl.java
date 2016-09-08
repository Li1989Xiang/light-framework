package up.light.internal;

import java.util.HashMap;
import java.util.Map;

import up.light.IResult;
import up.light.IStep;
import up.light.IWork;
import up.light.Status;
import up.light.data.IRow;

public class ResultImpl implements IResult {
	private Status status;
	private boolean isBinary;
	private IWork work;
	private IStep step;
	private Throwable throwable;
	private IRow row;

	private Map<String, String> textValues = new HashMap<>();
	private Map<String, byte[]> binaryValues = new HashMap<>();

	public ResultImpl(Status status, boolean isBinary, IWork work, IStep step, Throwable throwable, IRow row) {
		this.status = status;
		this.isBinary = isBinary;
		this.work = work;
		this.step = step;
		this.throwable = throwable;
		this.row = row;
	}

	@Override
	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public Status getStatus() {
		return status;
	}

	@Override
	public void putText(String name, String value) {
		textValues.put(name, value);
	}

	@Override
	public String getText(String name) {
		return textValues.get(name);
	}

	@Override
	public void putBinary(String name, byte[] value) {
		binaryValues.put(name, value);
	}

	@Override
	public byte[] getBinary(String name) {
		return binaryValues.get(name);
	}

	@Override
	public boolean isBinary() {
		return isBinary;
	}

	@Override
	public IWork getWork() {
		return work;
	}

	@Override
	public IStep getStep() {
		return step;
	}

	@Override
	public Throwable getThrowable() {
		return throwable;
	}

	@Override
	public IRow getData() {
		return row;
	}
}
