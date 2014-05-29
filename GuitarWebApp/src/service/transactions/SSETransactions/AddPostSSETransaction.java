package service.transactions.SSETransactions;

import java.util.List;
import java.util.Map;

import model.Post;
import service.sse.AddPostServerSentEvent;
import service.sse.ServerSentEvent;
import service.transactions.daoTransactions.DAOTransaction;
import service.transactions.daoTransactions.FetchPostsByUserIDTransaction;

public class AddPostSSETransaction extends SSETransaction{

	@SuppressWarnings("unchecked")
	@Override
	public ServerSentEvent initEvent(Map<String, Object> params) throws Exception {
		// TODO Auto-generated method stub
		DAOTransaction transaction = new FetchPostsByUserIDTransaction();
		List<Post.RepresentationShortCut> representationShortCuts = (List<Post.RepresentationShortCut>) transaction.execute(params);
		params.put("postRepresentationShortCut", representationShortCuts.get(0));
		
		return new AddPostServerSentEvent(params);
	}

}
