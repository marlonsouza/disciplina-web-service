package api.common;

import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Application;

@ApplicationPath("/")
public class BaseApplication extends Application{

	private Set<Class<?>> classes = new HashSet<>();

	public BaseApplication(){
		classes.add(ApiResource.class);
	}

	@Override
	public Set<Class<?>> getClasses() {
		return classes;
	}

}
