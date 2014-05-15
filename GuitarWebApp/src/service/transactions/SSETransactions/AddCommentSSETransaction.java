package service.transactions.SSETransactions;

import java.util.List;
import java.util.Map;

import model.Comment;
import service.sse.ServerSentEvent;
import service.transactions.daoTransactions.DAOTransaction;
import service.transactions.daoTransactions.FetchCommentsByUserIDTransaction;
import utils.SSEType;

public class AddCommentSSETransaction extends SSETransaction{

	@SuppressWarnings("unchecked")
	@Override
	public void initEvent(ServerSentEvent sse, Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		String userID = (String) params.get("userID");
		Long postID = (Long) params.get("postID");
		
		DAOTransaction transaction = new FetchCommentsByUserIDTransaction();
		List<Comment> comments = (List<Comment>) transaction.execute(params);
		
		sse.setName(SSEType.ADDCOMMENT);
		sse.setData(userID + " " + postID + " "+ comments.iterator().next().getID());
		
	}

}
