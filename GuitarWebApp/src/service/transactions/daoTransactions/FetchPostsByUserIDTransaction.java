package service.transactions.daoTransactions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import persistence.DAO;
import model.Post;

public class FetchPostsByUserIDTransaction extends DAOTransaction{

	@Override
	protected Object process(EntityManager em, Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		String userID = (String) params.get("userID");
		Integer startIndex = (Integer) params.get("startIndex");
		Integer pageSize = (Integer) params.get("pageSize");
		
		DAO<Post> pdao = new DAO<Post>(Post.class, em);
		List<Post> posts = new ArrayList<Post>();
		posts = pdao.collectionRead("Post.fetchByUserID", startIndex, pageSize, Post.class, userID);
		List<Post.RepresentationShortCut> representationShortCuts = new ArrayList<Post.RepresentationShortCut>();
		for(Post post : posts){
			representationShortCuts.add(post.getRepresentationShortCut());
		}
		return representationShortCuts;
	}

}
