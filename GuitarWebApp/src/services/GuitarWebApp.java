package services;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

public class GuitarWebApp extends Application{
	public Set<Class<?>> getClasses(){
		Set<Class<?>> classes = new LinkedHashSet<Class<?>>();
		classes.add(PostService.class);
		classes.add(CommentService.class);
		
		return classes;
	}
	
	public Set<Object> getSingletons(){
		return Collections.emptySet();
	}
	
}
