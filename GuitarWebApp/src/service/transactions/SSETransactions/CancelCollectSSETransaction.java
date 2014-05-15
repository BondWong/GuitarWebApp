package service.transactions.SSETransactions;

import java.util.Map;

import service.sse.ServerSentEvent;
import utils.SSEType;

public class CancelCollectSSETransaction extends SSETransaction{

	@Override
	public void initEvent(ServerSentEvent sse, Map<String, Object> params) {
		// TODO Auto-generated method stub
		String userID = (String) params.get("userID");
		Long postID = (Long) params.get("postID");
		
		sse.setName(SSEType.CANCELCOLLECT);
		sse.setData(userID + " " + postID);
		
	}

}
