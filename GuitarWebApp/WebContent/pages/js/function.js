
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
		
});

