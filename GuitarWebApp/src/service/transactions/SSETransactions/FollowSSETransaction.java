package service.transactions.SSETransactions;

import java.util.Map;

import service.sse.FollowServerSentEvent;
import service.sse.ServerSentEvent;

public class FollowSSETransaction extends SSETransaction{

	@Override
	public ServerSentEvent initEvent(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		return new FollowServerSentEvent(params);
	}

}
