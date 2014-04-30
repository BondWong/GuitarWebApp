package test;

import static org.junit.Assert.*;
import model.Post;

import org.junit.Test;

public class ClassTypeEqualTest {
	@Test
	public void testClassTypeEqual(){
		assertEquals(true, initClass(Post.class).equals(Post.class));
	}
	
	public <T> Class<T> initClass(Class<T> type){
		return type;
	}
}
