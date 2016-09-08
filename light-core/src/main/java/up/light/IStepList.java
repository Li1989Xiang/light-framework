package up.light;

import java.util.List;

public interface IStepList extends IConfigurable {
	String getDataTag();
	
	List<IStep> getSteps();
}
