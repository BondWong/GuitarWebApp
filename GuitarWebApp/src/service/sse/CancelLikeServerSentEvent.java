package service.sse;

import java.util.Map;

import utils.SSEType;

public class CancelLikeServerSentEvent extends ServerSentEvent {
	public CancelLikeServerSentEvent(Map<String, Object> params){
		super(params);
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return SSEType.CANCELLIKE.name();
	}

	@Override
	public String getData() {
		// TODO Auto-generated method stub
		return getTwoParamsJsonObject();
	}
	
}
