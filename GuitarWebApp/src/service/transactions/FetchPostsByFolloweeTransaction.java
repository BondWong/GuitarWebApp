package service.transactions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import persistence.DAO;
import model.Post;

public class FetchPostsByFolloweeTransaction extends DAOTransaction{

	@Override
	protected Object process(EntityManager em, Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		String userID = (String) params.get("userID");
		
		DAO<Post> pdao = new DAO<Post>(Post.class, em);
		List<Post> posts = new ArrayList<Post>();
		List<Post.ShortCut> shortCuts = new ArrayList<Post.ShortCut>();
		
		posts = pdao.collectionRead("Post.fetchByFollowee", Post.class, userID);
		for(Post p : posts){
			shortCuts.add(p.getShortCut());
		}
		
		return shortCuts;
	}

}
