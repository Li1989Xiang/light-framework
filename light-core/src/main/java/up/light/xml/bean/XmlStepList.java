package up.light.xml.bean;

import up.light.xml.bean.base.XmlItemList;

public class XmlStepList extends XmlItemList<XmlStep> {
	private String dataTag;
	private boolean enable;
	private int retry;
	private String dataSelector;

	public String getDataTag() {
		return dataTag;
	}

	public void setDataTag(String dataTag) {
		this.dataTag = dataTag;
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

	public String getDataSelector() {
		return dataSelector;
	}

	public void setDataSelector(String dataSelector) {
		this.dataSelector = dataSelector;
	}

}
