package service.sse;

import java.util.Map;

import utils.SSEType;

public class CancelCollectServerSentEvent extends ServerSentEvent {
	public CancelCollectServerSentEvent(Map<String, Object> params){
		super(params);
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return SSEType.CANCELCOLLECT.name();
	}

	@Override
	public String getData() {
		// TODO Auto-generated method stub
		return getTwoParamsJsonObject();
	}

}
