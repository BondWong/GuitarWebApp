package service;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.glassfish.jersey.media.sse.EventOutput;
import org.glassfish.jersey.media.sse.SseFeature;

import service.sse.ServerSentEventBroadcaster;

@Singleton
@Path("/event")
public class ServerSentEventService {
	
	private ServerSentEventBroadcaster broadcaster = ServerSentEventBroadcaster.getInstance();
	
	@GET
	@Path("subscribe")
	@Produces(SseFeature.SERVER_SENT_EVENTS)
	public EventOutput subscribe(){
		final EventOutput eventoutput = new EventOutput();
		this.broadcaster.subscribe(eventoutput);
		return eventoutput;
	}
}
