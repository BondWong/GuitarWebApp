package service.transactions.daoTransactions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import persistence.DAO;
import model.Comment;

public class FetchCommentsByUserIDTransaction extends DAOTransaction{

	@Override
	protected Object process(EntityManager em, Map<String, Object> params)
			throws Exception {
		String userID = (String) params.get("userID");
		
		List<Comment> comments = new ArrayList<Comment>();
		DAO<Comment> cdao = new DAO<Comment>(Comment.class, em);
		comments = cdao.collectionRead("Comment.fetchByUserID",
				0, 1, Comment.class, userID);
		
		return comments;
	}

}
