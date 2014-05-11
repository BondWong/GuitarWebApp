package utils;

public enum SSEType {
	TEST, ADDPOST, DELETEPOST, LIKEPOST, CANCELLIKE, COLLECTPOST, CANCELCOLLECT, JOINACTIVITY;
	
	public String toString(){
		return this.name();
	}
}
