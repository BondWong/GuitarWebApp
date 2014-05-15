package service.transactions.SSETransactions;

import java.util.Map;

import service.sse.CollectPostServerSentEvent;
import service.sse.ServerSentEvent;

public class CollectPostSSETransaction extends SSETransaction{

	@Override
	public ServerSentEvent initEvent(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return new CollectPostServerSentEvent(params);
		
	}

}
