package utils;

public enum SSEType {
	TEST, ADDPOST, DELETEPOST, LIKEPOST, CANCELLIKE, COLLECTPOST, CANCELCOLLECT, JOINACTIVITY
	, ADDCOMMENT, DELETECOMMENT, SUPPORTANSWER, CANCELSUPPORT;
	
	public String toString(){
		return this.name();
	}
}
