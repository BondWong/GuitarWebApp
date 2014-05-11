package transactions;

import java.util.Map;

import sse.ServerSentEvent;
import utils.SSEType;

public class JoinActivitySSETransaction extends SSETransaction{

	@Override
	public void initEvent(ServerSentEvent sse, Map<String, Object> params) {
		// TODO Auto-generated method stub
		String userID = (String) params.get("userID");
		Long postID = (Long) params.get("postID");
		
		sse.setName(SSEType.JOINACTIVITY);
		sse.setData(userID + " " + postID);
		
	}

}
