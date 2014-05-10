package services;

public class ServerSentEvent {
	private String name;
	private String data;
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setData(String data){
		this.data = data;
	}
	
	public String getData(){
		return data;
	}
	
}
