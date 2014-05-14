package unitTest;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import security.validation.ActivityGroup;
import security.validation.CommentRep;
import security.validation.PostRep;
import security.validation.UserRep;

public class RepContraintTest {
	private static Validator validator;
	@BeforeClass
	public static void classSetUp(){
		validator = Validation.buildDefaultValidatorFactory().getValidator();
	}
	
	@Ignore
	@Test
	public void testUserRepConstraint(){
		UserRep uRep = new UserRep();
		uRep.setID("2011052407");
		uRep.setPassword("1901103390");
		
		assertEquals(0, validator.validate(uRep).size());
	}
	
	@Ignore
	@Test
	public void testPostRepConstraint(){
		PostRep pRep = new PostRep();
		pRep.setPublishDate(new Date());
		pRep.setStartDate(new Date());
		System.out.println(validator.validate(pRep,ActivityGroup.class));
		
	}
	
	@Test
	public void testCommentRepConstraint(){
		CommentRep cRep = new CommentRep();
		cRep.setCommentType("ANSWER");
		cRep.setContent("");
		
		System.out.println(validator.validate(cRep));
	}
}
