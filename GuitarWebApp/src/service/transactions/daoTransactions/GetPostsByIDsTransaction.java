package service.transactions.daoTransactions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import persistence.DAO;
import model.Post;

public class GetPostsByIDsTransaction extends DAOTransaction{
	
	@SuppressWarnings("unchecked")
	@Override
	protected Object process(EntityManager em, Map<String, Object> params)
			throws Exception {
		List<Long> postIDs = (List<Long>) params.get("postIDs");
		String query = "SELECT p FROM Post p WHERE p.ID IN ";
		query = query + "(";
		
		Iterator<Long> iter = postIDs.iterator();
		while(iter.hasNext()){
			query = query + iter.next();
			if(iter.hasNext())
				query = query + ",";
		}
		
		query = query + ")";
		
		System.out.println(query);
		
		List<Post> posts = new ArrayList<Post>();
		List<Post.RepresentationShortCut> RepresentationShortCuts = new ArrayList<Post.RepresentationShortCut>();
		DAO<Post> pdao = new DAO<Post>(Post.class, em);
		posts = pdao.collectionDynamicRead(query, 0, postIDs.size(), Post.class);
		for(Post post : posts){
			RepresentationShortCuts.add(post.getRepresentationShortCut());
		}
		
		return RepresentationShortCuts;
	}

}
