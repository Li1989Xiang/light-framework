package up.light.internal;

public enum BuildInItems {
	JAVA_VERSION("java.runtime.version"),
	VM_VERSION("java.vm.version"),
	OS_ARCH("os.arch"),
	OS_NAME("os.name"),
	OS_VERSION("os.version");
	
	private String name;
	
	BuildInItems(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
