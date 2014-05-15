package service.transactions.SSETransactions;

import java.util.Map;

import service.sse.CancelLikeServerSentEvent;
import service.sse.ServerSentEvent;

public class CancelLikeSSETransaction extends SSETransaction{

	@Override
	public ServerSentEvent initEvent(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return new CancelLikeServerSentEvent(params);
		
	}

}
