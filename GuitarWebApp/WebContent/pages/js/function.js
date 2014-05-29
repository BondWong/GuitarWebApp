//function Msnry
	function Msnry(selectContain,item,width){
		var container = document.querySelector(selectContain);
		var msnry;
		imagesLoaded( container, function() {
		msnry = new Masonry( container,{
			columnWidth: width,
			itemSelector: item,
			gutter:20
			});
		});
	}
	
$(document).ready(function(){
//function userTip
	(function($){  
		$.fn.userTips = function () {

			var tipFrame = '<div class="popTip"><div class="content"><div class="urserBgShort"><img src="images/urseBgShort.jpg" /></div><div class="urserInfShort"><img src="images/user_img4.jpg" /><p><h1>Bond</h1></p><p> a good guy</p><button>follow</button></div></div></div>';
			
			// Speed of the animations in milliseconds - 1000 = 1 second.
			var animSpeed = 300;
							
			// Global tinyTip variables;
			var tinyTip;				
			// When we hover over the element that we want the tooltip applied to
			$(this).hover(function() {		
				$('body').append(tipFrame);
				var divTip = 'div.popTip';
				tinyTip = $(divTip);
				tinyTip.hide();
				
				// Offsets so that the tooltip is centered over the element it is being applied to but
				// raise it up above the element so it isn't covering it.
				var yOffset = tinyTip.height();
				var xOffset = (tinyTip.width() / 2) - ($(this).width() / 2);
				
				// Grab the coordinates for the element with the tooltip and make a new copy
				// so that we can keep the original un-touched.
				var pos = $(this).offset();
				var nPos = pos;
				
				// Add the offsets to the tooltip position
				nPos.top = pos.top - yOffset;
				nPos.left = pos.left - xOffset;
				
				// Make sure that the tooltip has absolute positioning and a high z-index, 
				// then place it at the correct spot and fade it in.
				tinyTip.css('position', 'absolute').css('z-index', '1000');
				tinyTip.css(nPos).fadeIn(animSpeed);
				
			}, function() {
				// Fade the tooltip out once the mouse moves away and then remove it from the DOM.
				tinyTip.fadeOut(animSpeed, function() {
					$(this).remove();
					//$('div.popTip').remove();
				});
			});				
		};
	})(jQuery);
	$('img.userImg').userTips();
//function addPost
		$("#btn_share").click(function() {
		//get urls
		var formData = new FormData($('form')[0]);
		$.ajax({
			type:'POST',
			url:'../../GuitarWebApp/app/fileUploader',
	        //Ajax events
	        success:function(data){
	        	var d = new Date();
	        	var publishDateN = d.getFullYear() + "/" +(d.getMonth()+1) + "/" + d.getDate();
	        	var date = new Date(publishDateN);
	        	var jtopic = $("#topic").val();
	    		var jcontent = $("#share_txt").val();
	    		var jtype = $("#type").val();
	    		var jpublishDate = $("#publishDate").val();
	    		var jstartDate = $("#startDate").val();
	    		var jsonString = {mediaLocation:data,topic:jtopic,content:jcontent,postType:"ACTIVITY",publishDate:date,startDate:date};
	    		var jsonData = $.toJSON(jsonString);
	    		$.ajax({
	    			type:"POST",
	    			url:'../../GuitarWebApp/app/post/add/2011052407',
	    			data:jsonData,
	    			contentType: "application/json"
	    		});
				},
	        // Form data
	        data: formData,
	        //Options to tell jQuery not to process data or worry about content-type.
	        cache: false,
	        contentType: false,
	        processData: false
		});
					
	});		

//function collectPost and cancelCollcet
		$('body').on('click','.post_collect',function() {
			if(this.lastChild.lastChild.getAttribute("class") == "glyphicon glyphicon-star-empty"){
				var id = this.firstChild.firstChild.getAttribute("value");
				$.ajax({
					url:'../../GuitarWebApp/app/post/collect/2011052407/'+id,//'../../GuitarWebApp/app/post/collect/2011052407/'+id
					type:'put'
				});
			}
			if(this.lastChild.lastChild.getAttribute("class") == "glyphicon glyphicon-star"){
				var id = this.firstChild.firstChild.getAttribute("value");
				$.ajax({
					url:'../../GuitarWebApp/app/post/cancelCollect/2011052407/'+id,//'../../GuitarWebApp/app/post/collect/2011052407/'+id
					type:'put'
				});
			}
		});
//function likePost and cancelLike
		$('body').on('click','.post_like',function(){
			if(this.lastChild.lastChild.getAttribute("class") == "glyphicon glyphicon-heart-empty"){
				var id = this.firstChild.firstChild.getAttribute("value");
				$.ajax({
					url:'../../GuitarWebApp/app/post/like/2011052407/'+id,//'../../GuitarWebApp/app/post/collect/2011052407/'+id
					type:'put'
				});
			}
			if(this.lastChild.lastChild.getAttribute("class") == "glyphicon glyphicon-heart"){
				var id = this.firstChild.firstChild.getAttribute("value");
				$.ajax({
					url:'../../GuitarWebApp/app/post/cancelLike/2011052407/'+id,//'../../GuitarWebApp/app/post/collect/2011052407/'+id
					type:'put'
				});
			}
		});
//function likePost and cancelLike
		$('body').on('click','#activityJoin',function(){
			var id = this.firstChild.firstChild.getAttribute("value");
			$.ajax({
				url:'../../GuitarWebApp/app/post/join/2011052407/1',//'../../GuitarWebApp/app/post/collect/2011052407/'+id
				type:'put'
			});
		});
		
//function addComment
		$('body').on('click','#addComment',function(){
			var d = new Date();
        	var publishDateN = d.getFullYear() + "/" +(d.getMonth()+1) + "/" + d.getDate();
        	var date = new Date(publishDateN);
			var jcontent = $(".form-control").val();
			var jCommentType = "COMMENT";
			var jpublishDate = $("#publishDate").val();
			var jsonString = {content:jcontent,commentType:jCommentType};
			var jsonData = $.toJSON(jsonString);
			var id = this.firstChild.getAttribute("value");
			$.ajax({
    			type:"POST",
    			url:'../../GuitarWebApp/app/comment/add/2011052407/'+id,//'../../GuitarWebApp/app/comment/add/2011052407/'+id
    			data:jsonData,
    			contentType: "application/json"
    		});
		});
//function follow
		$('body').on('click','#followBtn',function(){
			//get post owner
			if($(this).text()=="follow"){
				$.ajax({
					url:'../../GuitarWebApp/app/user/follow/2011052407/2011052406',//'../../GuitarWebApp/app/user/follow/2011052407/'+id
					type:'put'
				});
			}
		});
		
});

