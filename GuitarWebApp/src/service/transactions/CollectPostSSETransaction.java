package service.transactions;

import java.util.Map;

import service.sse.ServerSentEvent;
import utils.SSEType;

public class CollectPostSSETransaction extends SSETransaction{

	@Override
	public void initEvent(ServerSentEvent sse, Map<String, Object> params) {
		// TODO Auto-generated method stub
		String userID = (String) params.get("userID");
		Long postID =(Long) params.get("postID");
		
		sse.setName(SSEType.COLLECTPOST);
		sse.setData(userID + " " + postID);
		
	}

}