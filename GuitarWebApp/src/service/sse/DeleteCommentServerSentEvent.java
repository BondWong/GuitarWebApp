package service.sse;

import java.util.Map;

import utils.SSEType;

public class DeleteCommentServerSentEvent extends ServerSentEvent {

	public DeleteCommentServerSentEvent(Map<String, Object> params) {
		super(params);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return SSEType.DELETECOMMENT.name();
	}

	@Override
	public String getData() {
		// TODO Auto-generated method stub
		return getThreeParamsJsonObject();
	}

}
