package service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import service.sse.ServerSentEvent;
import service.sse.ServerSentEventBroadcaster;
import utils.SSEType;

@Path("/ssetest")
public class SSETest {
	private ServerSentEventBroadcaster broadcaster;
	
	@Path("message/{userID : \\d+}")
	@GET
	public void getMessage(@PathParam("userID") String userID){
		broadcaster = ServerSentEventBroadcaster.getInstance();
		ServerSentEvent sse = new ServerSentEvent();
		sse.setName(SSEType.TEST);
		sse.setData(userID + " say hi to you");
		System.out.println(sse);
		broadcaster.broadcast(sse);
	}

}
