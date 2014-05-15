package service.transactions.SSETransactions;

import java.util.List;
import java.util.Map;

import model.Comment;
import service.sse.AddCommentServerSentEvent;
import service.sse.ServerSentEvent;
import service.transactions.daoTransactions.DAOTransaction;
import service.transactions.daoTransactions.FetchCommentsByUserIDTransaction;

public class AddCommentSSETransaction extends SSETransaction{

	@SuppressWarnings("unchecked")
	@Override
	public ServerSentEvent initEvent(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		DAOTransaction transaction = new FetchCommentsByUserIDTransaction();
		List<Comment> comments = (List<Comment>) transaction.execute(params);
		
		params.put("comment", comments.get(0));
		
		return new AddCommentServerSentEvent(params);
	}

}
