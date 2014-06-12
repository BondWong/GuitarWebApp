//function editProfileInfro
		$('body').on('click','.aEditbtn',function(){
			$("span[class='Anickname']").html("<input id='focusedInput' class='nicknameE' type='text' value='Winson_Lau' />");
			$("span[class='Alooking']").html("<input class='lookingforE' id='focusedInput' type='text' value='Make friends' />");
			$("span[class='Atelenum']").html("<input class='telenumE' id='focusedInput' type='text' value='13750046461' />");
			$("span[class='Arelationship']").html("<select class='relationshipnE'><option value='single'>single</option><option value='loving'>loving</option></select>");
			var campusC = $('.Acampus').html();
			if(campusC=="珠海校区"){campus="ZhuhaiCampus";}
			if(campusC=="华文校区"){campus="HuawenCampus";}
			if(campusC=="深圳校区"){campus="ShenzhenCampus";}
			if(campusC=="校本部"){campus="GuangzhouCampus";}
			
			$.ajax({
				url:'../../GuitarWebApp/app/dormInfo/getInfo/'+campus,
				type:'get',
				success:function(data){		
					var option="";
					$.each(data,function(index,dorm){
						 option=option+"<option value='"+dorm+"'>"+dorm+"</option>";
					});
					$("span[class='Aaddress']").html("<select class='addressE'>"+option+"</select>");
				}
			});
			
			$("span[class='Aemail']").html("<input class='emailE' id='focusedInput' type='text' value='306941426@qq.com' />");
			$(this).text("Save");
			$(this).attr("class","btn btn-primary aSavebtn");
		});
		//function saveProfileInfro
		$('body').on('click','.aSavebtn',function(){
			var nickName = $('.nicknameE').val();
			var telenum = $('.telenumE').val();
			var relationship = $('.relationshipnE').val();
			var lookingfor = $('.lookingforE').val();
			var email = $('.emailE').val();
			var dorm = $('.addressE').val();
			//var lookingfor = $('.nicknameE').val();
			$.ajax({
				type:'put',
				url:'../../GuitarWebApp/app/user/updateProfile/'+userID+'/'+nickName+'/'+lookingfor+'/'+relationship+'/'+telenum+'/'+email+'/'+dorm,
				success:function(){
					getUserRepresentation();
				}
			});
			$(this).text("Edit");
			$(this).attr("class","btn btn-primary aEditbtn");
		});
		//function avatarImgBtn
		$('body').on("click",".avatarImgBtn",function(){
			var formData = new FormData($('.avatarForm')[0]);
			$.ajax({
				type:'POST',
				url:'../../GuitarWebApp/app/fileUploader',
				success:function(data){
					$.ajax({
						type:'PUT',
						url:'../../GuitarWebApp/app/user/changeAvatar/'+userID+'?avatarLink='+data
					});
				},
				// Form data
		        data: formData,
		        //Options to tell jQuery not to process data or worry about content-type.
		        cache: false,
		        contentType: false,
		        processData: false
			});
			$('#myModal').modal('hide');
		});
		//function addPhoto
		$('body').on("click",".addPhoto",function(){
			var formData = new FormData($('.photoForm')[0]);
			$.ajax({
				type:'POST',
				url:'../../GuitarWebApp/app/fileUploader',
				success:function(data){
					var Urls = '../../GuitarWebApp/app/user/addImages/'+userID+'?';
					$.each(data,function(n,photoUrl){
						if(n != data.length-1){
							Urls = Urls +'imageLinks='+ photoUrl+'&';
						}
						else{
							Urls = Urls +'imageLinks='+ photoUrl;
							return true;
						}
					});
					$.ajax({
						type:'PUT',
						url: Urls,
						success: function(){
							showPhotos();
						}
					});
				},
				// Form data
		        data: formData,
		        //Options to tell jQuery not to process data or worry about content-type.
		        cache: false,
		        contentType: false,
		        processData: false
			});
			$('#myModal2').modal('hide');
		});