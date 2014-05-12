package service.transactions;

import java.util.Map;

import service.sse.ServerSentEvent;
import service.sse.ServerSentEventBroadcaster;

public abstract class SSETransaction implements Transaction{
	private ServerSentEventBroadcaster broadcaster = ServerSentEventBroadcaster.getInstance();
	private ServerSentEvent sse;
	
	@Override
	public Object execute(Map<String, Object> params) throws Exception {
		sse = new ServerSentEvent();
		initEvent(sse, params);
		System.out.println("sse" + sse);
		broadcaster.broadcast(sse);
		return null;
	}
	
	public abstract void initEvent(ServerSentEvent sse, Map<String, Object> params) throws Exception;

}
