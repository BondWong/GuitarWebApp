package service.transactions;

import java.util.Map;

import service.sse.ServerSentEvent;
import utils.SSEType;

public class DeleteCommentSSETransaction extends SSETransaction{

	@Override
	public void initEvent(ServerSentEvent sse, Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		String userID = (String) params.get("userID");
		Long postID = (Long) params.get("postID");
		Long commentID = (Long) params.get("commentID");
		
		sse.setName(SSEType.DELETECOMMENT);
		sse.setData(userID + " " + postID + " " + commentID);
	}

}
