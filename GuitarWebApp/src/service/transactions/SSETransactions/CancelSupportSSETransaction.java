package service.transactions.SSETransactions;

import java.util.Map;

import service.sse.CancelSupportServerSentEvent;
import service.sse.ServerSentEvent;

public class CancelSupportSSETransaction extends SSETransaction{

	@Override
	public ServerSentEvent initEvent(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		return new CancelSupportServerSentEvent(params);
		
	}

}
