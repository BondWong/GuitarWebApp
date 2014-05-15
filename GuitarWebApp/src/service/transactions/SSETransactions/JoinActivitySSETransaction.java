package service.transactions.SSETransactions;

import java.util.Map;

import service.sse.JoinActivityServerSentEvent;
import service.sse.ServerSentEvent;

public class JoinActivitySSETransaction extends SSETransaction{

	@Override
	public ServerSentEvent initEvent(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return new JoinActivityServerSentEvent(params);
		
	}

}
