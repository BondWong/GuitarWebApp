package services;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import model.Post;
import persistence.DAO;

public class FetchPostsByUserIDTransaction extends Transaction{

	@Override
	protected Object process(EntityManager em, Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		String userID = (String) params.get("userID");
		
		DAO<Post> pdao = new DAO<Post>(Post.class, em);
		List<Post> posts = pdao.collectionRead("Post.fetchByUserID", Post.class, userID);
		return posts;
	}

}
