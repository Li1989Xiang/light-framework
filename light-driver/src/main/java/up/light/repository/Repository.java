package up.light.repository;

import java.lang.ref.SoftReference;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import up.light.Setting;
import up.light.io.FileSystemResource;
import up.light.io.IResource;
import up.light.parser.IParser;
import up.light.parser.RepoParserFactory;
import up.light.utils.LogUtil;

public class Repository {
	private static Map<String, SoftReference<Map<String, LocatorBean>>> mRepo = new HashMap<>();
	private static IParser<Map<String, LocatorBean>> mParser;
	
	public static LocatorBean getLocatorBean(Field field) {
		String className = field.getDeclaringClass().getSimpleName();
		String filedName = field.getName();
		SoftReference<Map<String, LocatorBean>> ref = mRepo.get(className);
		
		if(ref == null) {
			// has not parsered
			LogUtil.debug("repository has not neen parsed, begin parse");
			parse(className);
			ref = mRepo.get(className);
		}
		
		Map<String, LocatorBean> m = ref.get();
		
		if(m == null) {
			// cleaned by gc
			LogUtil.debug("repository was cleaned, parse it again");
			parse(className);
			m = ref.get();
		}
		
		return m.get(filedName);
	}
	
	private static void parse(String className) {
		String type = Setting.getRepoType();
		
		if(mParser == null) {
			mParser = RepoParserFactory.getParser(type);
		}
		
		String file = className + "." + type;
		IResource res = new FileSystemResource(Setting.getRepoPath() + file);
		SoftReference<Map<String, LocatorBean>> ref = new SoftReference<>(mParser.parse(res));
		mRepo.put(className, ref);
	}
}
