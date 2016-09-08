package up.light.xml.bean;

import java.util.List;

public class XmlStep {
	private int index;
	private String keyword;
	private String call;
	private boolean enable;
	private int retry;
	private List<XmlParameter> parameters;

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getCall() {
		return call;
	}

	public void setCall(String call) {
		this.call = call;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public int getRetry() {
		return retry;
	}

	public void setRetry(int retry) {
		this.retry = retry;
	}

	public List<XmlParameter> getParameters() {
		return parameters;
	}

	public void setParameters(List<XmlParameter> parameters) {
		this.parameters = parameters;
	}

}
