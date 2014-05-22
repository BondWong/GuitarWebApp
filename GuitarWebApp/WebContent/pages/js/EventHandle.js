// JavaScript Document
		var source=new EventSource("http://localhost:8080/GuitarWebApp/app/event/subscribe");
		es.addEventListener('addPostSSE',function(e){
		$.each(e.postShortCut,function(addPostJson){
		var boarddiv = "<div class='post'><div class='post_body'><div class='row'><div class='col-md-2'><div class='user_img'><img src='images/user_img.jpg' /></div></div><div class='col-md-6'><div class='user_name'><strong>"+addPostJson.ownerNickName+"</strong></div><div class='user_info'>"+addPostJson.publishDate+"</div></div></div><div class='post_info'>"+addPostJson.content+"<div class='post_more'><a>read more...</a></div></div><div class='post_img'><img src="+addPostJson.mediaLocation[0]+" /></div><div class='row'><div class='col-md-1'><div class='like'><a><span class='glyphicon glyphicon-heart-empty' style='font-size:20px'>"+addPostJson.likeNum+"</span></a></div></div><div class='col-md-1'><div class='post_share'><a><span class='glyphicon glyphicon-share-alt' style='font-size:20px'></span></a></div></div></div></div></div>";
		});
	//append div
		$(".pro_body").append(boarddiv); 
 });
		es.addEventListener('addPostSSE',function(e){});
		es.addEventListener('deletePostSSE',function(e){});
		es.addEventListener('likePostSSE',function(e){});
		es.addEventListener('collectPostSSE',function(e){});
		es.addEventListener('cancelPostSSE',function(e){});
		es.addEventListener('joinActivitySSE',function(e){});
		es.addEventListener('addCommentSSE',function(e){});
		es.addEventListener('supportSSE',function(e){});
		es.addEventListener('cancelSupportSSE',function(e){});
		es.addEventListener('followSSE',function(e){});