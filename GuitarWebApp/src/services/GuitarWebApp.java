package services;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;



@ApplicationPath("/root")
public class GuitarWebApp extends Application{
	public Set<Class<?>> getClasses(){
		Set<Class<?>> classes = new LinkedHashSet<Class<?>>();
		classes.add(PostService.class);
		classes.add(CommentService.class);
		
		return classes;
	}
	
	public Set<Object> getSingletons(){
		Set<Object> singletons = new LinkedHashSet<Object>();
		
		return singletons;
	}
	
}
