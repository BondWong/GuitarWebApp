package services;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import model.Post;
import persistence.DAO;
import utils.PostType;

public class FetchPostsByTypeTransaction extends Transaction{

	@Override
	protected Object process(EntityManager em, Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		PostType type = (PostType) params.get("type");
		
		DAO<Post> pdao = new DAO<Post>(Post.class, em);
		return (List<Post>) pdao.collectionRead("Post.fetchByType", Post.class, type);
	}

}
