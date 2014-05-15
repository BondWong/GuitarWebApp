package service.transactions.SSETransactions;

import java.util.Map;

import service.sse.CancelCollectServerSentEvent;
import service.sse.ServerSentEvent;

public class CancelCollectSSETransaction extends SSETransaction{

	@Override
	public ServerSentEvent initEvent(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return new CancelCollectServerSentEvent(params);
		
	}

}
