package service.transactions.SSETransactions;

import java.util.Map;

import service.sse.DeleteCommentServerSentEvent;
import service.sse.ServerSentEvent;

public class DeleteCommentSSETransaction extends SSETransaction{

	@Override
	public ServerSentEvent initEvent(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		return new DeleteCommentServerSentEvent(params);
	}

}
