package services;

import java.util.Map;

import javax.persistence.EntityManager;

import model.Post;
import persistence.DAO;

public class GetPostByIDTransaction extends Transaction{

	@Override
	protected Object process(EntityManager em, Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		Long postID = (Long) params.get("postID");
		
		DAO<Post> pdao = new DAO<Post>(Post.class, em);
		Post p = pdao.singleResultRead(postID, Post.class);
		return p;
	}

}
