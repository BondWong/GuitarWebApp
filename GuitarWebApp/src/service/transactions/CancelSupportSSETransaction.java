package service.transactions;

import java.util.Map;

import service.sse.ServerSentEvent;
import utils.SSEType;

public class CancelSupportSSETransaction extends SSETransaction{

	@Override
	public void initEvent(ServerSentEvent sse, Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		String userID = (String) params.get("userID");
		Long commentID = (Long) params.get("commentID");
		
		sse.setName(SSEType.CANCELSUPPORT);
		sse.setData(userID + " " + commentID);
		
	}

}
