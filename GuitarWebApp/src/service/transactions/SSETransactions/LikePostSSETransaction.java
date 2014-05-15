package service.transactions.SSETransactions;

import java.util.Map;

import service.sse.LikePostServerSentEvent;
import service.sse.ServerSentEvent;

public class LikePostSSETransaction extends SSETransaction{

	@Override
	public ServerSentEvent initEvent(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return new LikePostServerSentEvent(params);
		
	}

}
