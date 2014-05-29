package service.transactions.SSETransactions;

import java.util.Map;

import service.sse.CancelFollowServerSentEvent;
import service.sse.ServerSentEvent;
import service.transactions.SSETransactions.SSETransaction;

public class CancelFollowSSETransaction extends SSETransaction{

	@Override
	public ServerSentEvent initEvent(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		return new CancelFollowServerSentEvent(params);
	}

}
