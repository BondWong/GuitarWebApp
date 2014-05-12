package service.transactions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import persistence.DAO;
import model.Post;
import utils.PostType;

public class FetchPostsByTypeTransaction extends DAOTransaction{

	@Override
	protected Object process(EntityManager em, Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		PostType type = (PostType) params.get("type");
		
		DAO<Post> pdao = new DAO<Post>(Post.class, em);
		
		List<Post> posts = new ArrayList<Post>();
		posts = pdao.collectionRead("Post.fetchByType", Post.class, type);
		List<Post.ShortCut> shortCuts = new ArrayList<Post.ShortCut>();
		for(Post p : posts){
			shortCuts.add(p.getShortCut());
		}
		return shortCuts;
	}

}
