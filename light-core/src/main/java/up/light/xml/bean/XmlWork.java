package up.light.xml.bean;

public class XmlWork {
	private String name;
	private boolean enable;
	private String dataSelector;

	private XmlStepList before;
	private XmlStepList steps;
	private XmlStepList after;
	private XmlStepList recovery;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public String getDataSelector() {
		return dataSelector;
	}

	public void setDataSelector(String dataSelector) {
		this.dataSelector = dataSelector;
	}

	public XmlStepList getBefore() {
		return before;
	}

	public void setBefore(XmlStepList before) {
		this.before = before;
	}

	public XmlStepList getSteps() {
		return steps;
	}

	public void setSteps(XmlStepList steps) {
		this.steps = steps;
	}

	public XmlStepList getAfter() {
		return after;
	}

	public void setAfter(XmlStepList after) {
		this.after = after;
	}

	public XmlStepList getRecovery() {
		return recovery;
	}

	public void setRecovery(XmlStepList recovery) {
		this.recovery = recovery;
	}

}
