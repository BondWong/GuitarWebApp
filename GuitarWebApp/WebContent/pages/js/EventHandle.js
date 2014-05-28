// JavaScript Document
//create event source
$(document).ready(function(){
window.onload = function(){
if (!!window.EventSource) {
		var source = new EventSource("../../GuitarWebApp/app/event/subscribe");
		source.addEventListener("ADDPOST",function(event){
		var jsondata = $.parseJSON(event.data);
		var jsonPostShortCut = jsondata.postShortCut;
		 var boarddiv = "<div class='post'><div class='post_body'><div class='row'><div class='col-md-2'><div class='user_img'><img src='images/user_img.jpg' /></div></div><div class='col-md-6'><div class='user_name'><strong>"+jsonPostShortCut.ownerNickName+"</strong></div><div class='user_info'>"+jsonPostShortCut.publishDate+"</div></div> </div><div class='post_info'>"+jsonPostShortCut.content+"<div class='post_more'><a>read more...</a></div></div><div class='post_img'><img src='images/9.jpg' /></div><div class='row'><div class='col-md-1'><div class='post_like' style='cursor:pointer'><a><input id='likeID' type='hidden' value="+jsonPostShortCut.ID+"><span class='glyphicon glyphicon-heart-empty' style='font-size:20px'>"+jsonPostShortCut.likeNum+"</span></a></div></div><div class='col-md-1'><div class='post_collect' style='cursor:pointer'><a><input id='collectID' type='hidden' value="+jsonPostShortCut.ID+"><span class='glyphicon glyphicon-star-empty' style='font-size:20px'></span></a></div></div><div class='col-md-1'><div class='post_share' style='cursor:pointer'><a><span class='glyphicon glyphicon-share-alt' style='font-size:20px'></span></a></div></div></div></div></div>";
		//append div
		$(".share").after(boarddiv); 
		Msnry('.pro_body','.post',435);
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
		source.addEventListener('addCommentSSE',function(e){});
		source.addEventListener('supportSSE',function(e){});
		source.addEventListener('cancelSupportSSE',function(e){});
		source.addEventListener('followSSE',function(e){});
}
};
});