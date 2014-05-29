package service.sse;

import java.util.Map;

import utils.SSEType;

public class CancelFollowServerSentEvent extends ServerSentEvent{

	public CancelFollowServerSentEvent(Map<String, Object> params) {
		super(params);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return SSEType.CANCELFOLLOW.name();
	}

	@Override
	public String getData() {
		// TODO Auto-generated method stub
		return getUidUidJsonObject();
	}

}
