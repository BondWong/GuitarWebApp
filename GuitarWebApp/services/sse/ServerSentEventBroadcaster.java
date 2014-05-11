package sse;

import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.sse.EventOutput;
import org.glassfish.jersey.media.sse.OutboundEvent;
import org.glassfish.jersey.media.sse.SseBroadcaster;

public class ServerSentEventBroadcaster {
	private static ServerSentEventBroadcaster instance;
	private SseBroadcaster sseBroadcaster;
	private OutboundEvent.Builder builder;
	
	private ServerSentEventBroadcaster(){
		sseBroadcaster = new SseBroadcaster();
		builder = new OutboundEvent.Builder();
	}
	
	public static ServerSentEventBroadcaster getInstance(){
		if(instance == null){
			synchronized(ServerSentEventBroadcaster.class){
				if(instance == null)
					instance = new ServerSentEventBroadcaster();
			}
		}
		
		return instance;
	}
	
	public void subscribe(EventOutput eventOutput){
		this.sseBroadcaster.add(eventOutput);
		System.out.println(sseBroadcaster);
		System.out.println(eventOutput);
	}
	
	public void broadcast(ServerSentEvent serverSentEvent){
		OutboundEvent event = builder.name(serverSentEvent.getName())
				.mediaType(MediaType.TEXT_HTML_TYPE)
				.data(String.class, serverSentEvent.getData())
				.build();
		
		System.out.println(event);
		this.sseBroadcaster.broadcast(event);
	}
}
