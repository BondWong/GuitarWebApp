// JavaScript Document
//create event source
$(document).ready(function(){
window.onload = function(){
if (!!window.EventSource) {
		var source = new EventSource("../../GuitarWebApp/app/event/subscribe");
		source.addEventListener("ADDPOST",function(event){
		var jsondata = $.parseJSON(event.data);
		var jsonPostShortCut = jsondata.postRepresentationShortCut;
		postIdContainer.push(jsonPostShortCut.ID);
		$('.alertCust').css("display","block");
	
 });
		source.addEventListener('deletePostSSE',function(e){});
		source.addEventListener('LIKEPOST',function(event){
			var jsondata = $.parseJSON(event.data);
			var postID = jsondata.pcID;
			var inputID = $("input[value='"+postID+"'][id='likeID']");
			inputID.next().attr("class","glyphicon glyphicon-heart");
			var like = parseInt(inputID.next().text())+1;
			inputID.next().text(like); 
		});
		source.addEventListener('CANCELLIKE',function(event){
			var jsondata = $.parseJSON(event.data);
			var postID = jsondata.pcID;
			var inputID = $("input[value='"+postID+"'][id='likeID']");
			inputID.next().attr("class","glyphicon glyphicon-heart-empty");
			var like = parseInt(inputID.next().text())-1;
			inputID.next().text(like);
		});
		source.addEventListener('COLLECTPOST',function(event){
			var jsondata = $.parseJSON(event.data);
			var postID = jsondata.pcID;
			var inputID = $("input[value='"+postID+"'][id='collectID']");
			inputID.next().attr("class","glyphicon glyphicon-star");
		});
		source.addEventListener('CANCELCOLLECT',function(event){
			var jsondata = $.parseJSON(event.data);
			var postID = jsondata.pcID;
			var inputID = $("input[value='"+postID+"'][id='collectID']");
			inputID.next().attr("class","glyphicon glyphicon-star-empty");
		});
		source.addEventListener('JOINACTIVITY',function(e){
			alert("success!");
		});
		source.addEventListener('ADDCOMMENT',function(event){
			var jsondata = $.parseJSON(event.data);
			var jsonComment = jsondata.commentRepresentation;
			var boarddiv = "<div class='act_content'><div class='row'><div class='col-lg-1'><img src='images/user_img2.jpg' /></div><div class='col-lg-11'><div class='ures_name'><strong>"+jsonComment.ownerRepresentation.nickName+"</strong></div><div class='user_info'>Yesterday 21:23pm</div></div></div><div class='act_comment'>"+jsonComment.content+"﻿</div></div></div>";
			$("button[value='"+jsondata.postID+"'][id='addComment']").parent().parent().after(boarddiv); 
			Msnry('.pro_body','.post',435);
		});
		source.addEventListener('supportSSE',function(e){});
		source.addEventListener('cancelSupportSSE',function(e){});
		source.addEventListener('FOLLOW',function(event){
			var jsondata = $.parseJSON(event.data);
			var followBtn = $("button[id='followBtn']");
			followBtn.text("Following");
			
		});
		source.addEventListener('CANCELFOLLOW',function(event){
			var jsondata = $.parseJSON(event.data);
			var followBtn = $("button[id='followBtn']");
			followBtn.text("Follow");
			
		});
		
}
};
});