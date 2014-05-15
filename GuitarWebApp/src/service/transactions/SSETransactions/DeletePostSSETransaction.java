package service.transactions.SSETransactions;

import java.util.Map;

import service.sse.DeletePostServerSentEvent;
import service.sse.ServerSentEvent;

public class DeletePostSSETransaction extends SSETransaction{

	@Override
	public ServerSentEvent initEvent(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return new DeletePostServerSentEvent(params);
		
	}

}
