package service.sse;

import java.util.Map;

import utils.SSEType;

public class FollowServerSentEvent extends ServerSentEvent {

	public FollowServerSentEvent(Map<String, Object> params) {
		super(params);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return SSEType.FOLLOW.name();
	}

	@Override
	public String getData() {
		// TODO Auto-generated method stub
		return getUidUidJsonObject();
	}

}
