// JavaScript Document
//create event source
window.onload = function(){
if (!!window.EventSource) {
		var source = new EventSource("../../GuitarWebApp/app/event/subscribe");
		source.addEventListener("ADDPOST",function(event){
		var jsondata = $.parseJSON(event.data);
		var jsonPostShortCut = jsondata.postShortCut;
		var boarddiv = "<div class='post'><div class='post_body'><div class='row'><div class='col-md-2'><div class='user_img'><img src='images/user_img.jpg' /></div></div><div class='col-md-6'><div class='user_name'><strong>"+jsonPostShortCut.ownerNickName+"</strong></div><div class='user_info'>"+jsonPostShortCut.publishDate+"</div></div></div><div class='post_info'>"+jsonPostShortCut.content+"<div class='post_more'><a>read more...</a></div></div><div class='post_img'><img src='images/13.png' /></div><div class='row'><div class='col-md-1'><div class='like'><a><span class='glyphicon glyphicon-heart-empty' style='font-size:20px'>"+jsonPostShortCut.likeNum+"</span></a></div></div><div class='col-md-1'><div class='post_share'><a><span class='glyphicon glyphicon-share-alt' style='font-size:20px'></span></a></div></div></div></div></div>";
		//append div
		$(".share").after(boarddiv); 
		Msnry();
 });
		source.addEventListener('deletePostSSE',function(e){});
		source.addEventListener('likePostSSE',function(e){});
		source.addEventListener('collectPostSSE',function(e){});
		source.addEventListener('cancelPostSSE',function(e){});
		source.addEventListener('joinActivitySSE',function(e){});
		source.addEventListener('addCommentSSE',function(e){});
		source.addEventListener('supportSSE',function(e){});
		source.addEventListener('cancelSupportSSE',function(e){});
		source.addEventListener('followSSE',function(e){});
}
};