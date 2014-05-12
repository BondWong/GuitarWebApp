package service.transactions;

import java.util.Map;

import service.sse.ServerSentEvent;
import utils.SSEType;

public class LikePostSSETransaction extends SSETransaction{

	@Override
	public void initEvent(ServerSentEvent sse, Map<String, Object> params) {
		// TODO Auto-generated method stub
		String userID = (String) params.get("userID");
		Long postID = (Long) params.get("postID");
		
		sse.setName(SSEType.LIKEPOST);
		sse.setData(userID + " " + postID);
		
	}

}
