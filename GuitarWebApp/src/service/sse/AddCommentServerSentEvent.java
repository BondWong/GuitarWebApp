package service.sse;

import java.util.Map;

import utils.SSEType;

public class AddCommentServerSentEvent extends ServerSentEvent {
	public AddCommentServerSentEvent(Map<String, Object> params){
		super(params);
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return SSEType.ADDCOMMENT.name();
	}

	@Override
	public String getData() {
		// TODO Auto-generated method stub
		return getCommentShortCutJsonObject();
	}
	
}
