package service.sse;

import java.util.Map;

import utils.SSEType;

public class DeletePostServerSentEvent extends ServerSentEvent {

	public DeletePostServerSentEvent(Map<String, Object> params) {
		super(params);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return SSEType.DELETEPOST.name();
	}

	@Override
	public String getData() {
		// TODO Auto-generated method stub
		return getTwoParamsJsonObject();
	}

}
