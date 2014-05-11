package transactions;

import java.util.List;
import java.util.Map;

import model.Post;
import sse.ServerSentEvent;
import utils.SSEType;

public class AddPostSSETransaction extends SSETransaction{

	@SuppressWarnings("unchecked")
	@Override
	public void initEvent(ServerSentEvent sse, Map<String, Object> params) throws Exception {
		// TODO Auto-generated method stub
		String userID = (String) params.get("userID");
		
		DAOTransaction transaction = new FetchPostsByUserIDTransaction();
		List<Post.ShortCut> shortCuts = (List<Post.ShortCut>) transaction.execute(params);
		Long postID = shortCuts.get(0).getID();
		
		sse.setName(SSEType.ADDPOST);
		sse.setData(userID + " " + postID);
		
	}

}
