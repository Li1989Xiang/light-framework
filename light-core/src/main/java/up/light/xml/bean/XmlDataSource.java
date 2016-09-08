package up.light.xml.bean;

import up.light.xml.bean.base.XmlPropertySet;

public class XmlDataSource extends XmlPropertySet {
	private String type;
	private String src;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

}
