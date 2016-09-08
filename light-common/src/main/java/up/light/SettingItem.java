package up.light;

import java.io.File;

enum SettingItem {
	PATH_ROOT("path.root") {
		@Override
		public String defaultValut() {
			return System.getProperty("user.dir") + File.separator;
		}
	},
	PATH_CONFIG("path.config") {
		@Override
		public String defaultValut() {
			return "config" + File.separator;
		}
	},
	PATH_LOG("path.log") {
		@Override
		public String defaultValut() {
			return "log" + File.separator;
		}
	},
	PATH_REPO("path.repo") {
		@Override
		public String defaultValut() {
			return "repository" + File.separator;
		}
	},
	TYPE_REPO("type.repo") {
		@Override
		public String defaultValut() {
			return "json";
		}

		@Override
		public String handle(String str) {
			return str;
		}
	};

	private String name;

	SettingItem(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public String handle(String str) {
		str = str.replace("/", File.separator);
		
		if (!str.endsWith(File.separator)) {
			str = str + File.separator;
		}

		return str;
	}

	public abstract String defaultValut();
}
