package api.common;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/")
public class BaseApplication extends Application{

	private Set<Class<?>> classes = new HashSet<>();

	public BaseApplication(){
		classes.add(ApiResource.class);
		classes.add(FilterApplication.class);
	}

	@Override
	public Set<Class<?>> getClasses() {
		return classes;
	}

}
