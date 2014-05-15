package service.transactions.SSETransactions;

import java.util.Map;

import service.sse.ServerSentEvent;
import service.sse.SupportAnswerServerSentEvent;

public class SupportAnswerSSETransaction extends SSETransaction{

	@Override
	public ServerSentEvent initEvent(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		return new SupportAnswerServerSentEvent(params);
	}

}
