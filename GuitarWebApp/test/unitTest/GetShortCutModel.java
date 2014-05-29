package unitTest;

import static org.junit.Assert.*;
import model.Post;

import org.junit.Assert;
import org.junit.Test;

import service.factory.PostFactory;
import utils.ParamGenerator;
import utils.PostType;

public class GetShortCutModel {
	@Test
	public void testGetShortCutModel(){
		Post p = new PostFactory().create(ParamGenerator.generatePostParam(PostType.ACTIVITY));
		
		Post.RepresentationShortCut psc = p.getRepresentationShortCut();
		
		Assert.assertNotNull(psc);
		assertEquals(psc.getID(), p.getID());
		assertEquals(psc.getContent(), p.getContent());
		assertEquals(psc.getLikeNum(), p.getLikeNum());
		assertEquals(psc.getParticipentsNum(), p.getParticipantsNum());
		assertEquals(psc.getType(), p.getType());
		assertEquals(psc.getTopic(), p.getTopic());
	}
}
