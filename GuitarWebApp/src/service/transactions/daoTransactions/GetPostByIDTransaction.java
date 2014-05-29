package service.transactions.daoTransactions;

import java.util.Map;

import javax.persistence.EntityManager;

import persistence.DAO;
import model.Post;

public class GetPostByIDTransaction extends DAOTransaction{

	@Override
	protected Object process(EntityManager em, Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		Long postID = (Long) params.get("postID");
		
		DAO<Post> pdao = new DAO<Post>(Post.class, em);
		Post p = pdao.singleResultRead(postID, Post.class);
		return p.getRepresentation();
	}

}
