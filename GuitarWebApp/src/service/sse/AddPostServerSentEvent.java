package service.sse;

import java.util.Map;

import utils.SSEType;

public class AddPostServerSentEvent extends ServerSentEvent {
	public AddPostServerSentEvent(Map<String, Object> params){
		super(params);
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return SSEType.ADDPOST.name();
	}

	@Override
	public String getData() {
		// TODO Auto-generated method stub
		return getPostShortCutJsonObject();
	}
	
}
