package up.light.xml.bean;

import java.util.List;

public class XmlWorks {
	private XmlDataSource dataSource;
	private XmlWork globalRecovery;
	private List<XmlWork> works;

	public XmlDataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(XmlDataSource dataSource) {
		this.dataSource = dataSource;
	}

	public XmlWork getGlobalRecovery() {
		return globalRecovery;
	}

	public void setGlobalRecovery(XmlWork globalRecovery) {
		this.globalRecovery = globalRecovery;
	}

	public List<XmlWork> getWorks() {
		return works;
	}

	public void setWorks(List<XmlWork> works) {
		this.works = works;
	}

}
