package up.light.xml.bean;

import up.light.xml.bean.base.XmlItemList;
import up.light.xml.bean.base.XmlPropertySet;

public class XmlLight {
	private XmlPropertySet setting;
	private XmlItemList<XmlListener> listeners;
	private XmlItemList<XmlKeyword> keywords;
	private XmlItemList<XmlWriter> report;
	private XmlWorks works;

	public XmlPropertySet getSetting() {
		return setting;
	}

	public void setSetting(XmlPropertySet setting) {
		this.setting = setting;
	}

	public XmlItemList<XmlListener> getListeners() {
		return listeners;
	}

	public void setListeners(XmlItemList<XmlListener> listeners) {
		this.listeners = listeners;
	}

	public XmlItemList<XmlKeyword> getKeywords() {
		return keywords;
	}

	public void setKeywords(XmlItemList<XmlKeyword> keywords) {
		this.keywords = keywords;
	}

	public XmlItemList<XmlWriter> getReport() {
		return report;
	}

	public void setReport(XmlItemList<XmlWriter> report) {
		this.report = report;
	}

	public XmlWorks getWorks() {
		return works;
	}

	public void setWorks(XmlWorks works) {
		this.works = works;
	}

}
