package sse;

import utils.SSEType;

public class ServerSentEvent {
	private SSEType name;
	private String data;
	
	
	public void setName(SSEType name){
		this.name = name;
	}
	
	public String getName(){
		return name.toString();
	}
	
	public void setData(String data){
		this.data = data;
	}
	
	public String getData(){
		return data;
	}
	
	public String toString(){
		return "name: " + getName() + "data: " + getData();
	}
	
}
