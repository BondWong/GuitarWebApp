package service.sse;

import java.util.Map;

import utils.SSEType;

public class CancelSupportServerSentEvent extends ServerSentEvent {
	public CancelSupportServerSentEvent(Map<String, Object> params){
		super(params);
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return SSEType.CANCELSUPPORT.name();
	}

	@Override
	public String getData() {
		// TODO Auto-generated method stub
		return getTwoParamsJsonObject();
	}
	
}
