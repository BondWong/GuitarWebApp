// JavaScript Document
//eventSource Creat

$(document).ready(function() {  
		$("#btn_share").click(function() {
		//get the image and return a URL	
		/*var selected_file = document.getElementById('file').files[0];;
		$.post('../../GuitarWebApp/app/fileUploader',selected_file,function(data){
			alert(data);
		});
		*/
		var urls;
		var formData = new FormData($('form')[0]);
		$.ajax({
			type:'POST',
			url:'../../GuitarWebApp/app/fileUploader',
	        //Ajax events
	        success:function(data){
	        	urls = data;
	        	alert(urls);
				},
	        // Form data
	        data: formData,
	        //Options to tell jQuery not to process data or worry about content-type.
	        cache: false,
	        contentType: false,
	        processData: false
		});
		
		//put URL and data into a json
		var jtopic = document.getElementById("topic");
		var jcontent = document.getElementById("content");
		var jtype = document.getElementById("type");
		var jpublishDate = document.getElementById("publishDate");
		var jstartDate = document.getElementById("startDate");
		var jsonString = {urls:data,jtopic:topic,jcontent:content,jtype:type,jpublishDate:publishDate,jstartDate:startDate};
		var jsonData = $.toJson(jsonString);
		//put json to program and return a event
		$.post("../../GuitarWebApp/post/add/2011052407",jsonData);			
	});		
});
