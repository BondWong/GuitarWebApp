// JavaScript Document

$(document).ready(function() {  
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
});
