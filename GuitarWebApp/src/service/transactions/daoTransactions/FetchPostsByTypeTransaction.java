package service.transactions.daoTransactions;

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
		Integer startIndex = (Integer) params.get("startIndex");
		Integer pageSize = (Integer) params.get("pageSize");
		
		DAO<Post> pdao = new DAO<Post>(Post.class, em);
		
		List<Post> posts = new ArrayList<Post>();
		posts = pdao.collectionRead("Post.fetchByType", startIndex, pageSize, Post.class, type);
		List<Post.RepresentationShortCut> representationShortCuts = new ArrayList<Post.RepresentationShortCut>();
		for(Post p : posts){
			representationShortCuts.add(p.getRepresentationShortCut());
		}
		return representationShortCuts;
	}

}
