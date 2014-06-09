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
//function addDiv
	function addPost(ownerNickName,publishDate,content,ID,likeNum){
		 var boarddiv = "<div class='post'><div class='post_body'><div class='row'><div class='col-md-2'><div class='user_img'><img class='userImg' src='images/user_img.jpg' /></div></div><div class='col-md-6'><div class='user_name'><strong>"+ownerNickName+"</strong></div><div class='user_info'>"+publishDate+"</div></div> </div><div class='post_info'>"+content+"<div class='post_more'><a>read more...</a></div></div><div class='post_img'><img src='images/9.jpg' /></div><div class='row'><div class='col-md-1'><div class='post_like' style='cursor:pointer'><a><input id='likeID' type='hidden' value="+ID+"><span class='glyphicon glyphicon-heart-empty' style='font-size:20px'>"+likeNum+"</span></a></div></div><div class='col-md-1'><div class='post_collect' style='cursor:pointer'><a><input id='collectID' type='hidden' value="+ID+"><span class='glyphicon glyphicon-star-empty' style='font-size:20px'></span></a></div></div><div class='col-md-1'><div class='post_share' style='cursor:pointer'><a><span class='glyphicon glyphicon-share-alt' style='font-size:20px'></span></a></div></div></div><div class='media_comm'><div class='row addCommentBtn'><div class='col-lg-8'><div class='form-group'><input type='text' placeholder='Add a comment' class='form-control' id='commentText"+ID+"'></div></div><div class='col-lg-4'><button type='submit' class='btn btn-success' id='addComment' value="+ID+">Summit</button></div></div><div class='act_content'><div class='row'><div class='col-lg-1'><img src='images/user_img2.jpg' /></div><div class='col-lg-11'><div class='ures_name'><strong>Winson_Lau</strong></div><div class='user_info'>Yesterday 21:23pm</div></div></div><div class='act_comment'>There's an extension that whenever you choose something that has a shortcut it has a toast popup that tells you what the shortcut would have been. I used it briefly a long time ago. But it turned out to be really annoying. Still it was a great idea.﻿</div></div><div class='act_content'><div class='row'><div class='col-lg-1'><img src='images/user_img3.jpg' /></div><div class='col-lg-11'><div class='ures_name'><strong>Thackoor Singh</strong></div><div class='user_info'>Yesterday 21:23pm</div></div></div><div class='act_comment'>free!</div></div></div></div></div>";
			$(".share").after(boarddiv); 
			Msnry('.pro_body','.post',435);
			$('img.userImg').userTips();
	}
//function fecthPostsBytype
	function fecthPostsByType(type){
		$.ajax({
			url:'../../GuitarWebApp/app/post/fetchByType/'+type+'/0/5',
			type:'get',
			success:function(data){
				$.each(data,function(index,jsonPostShortCut){
					if(index==0){
						return true;
					}
					addPost(jsonPostShortCut.ownerNickName,jsonPostShortCut.publishDate,jsonPostShortCut.content,jsonPostShortCut.id,jsonPostShortCut.likeNum);
				});
			}
		});
	};
	
//function fetchPostsByUserID
	
	function fetchPostsByUserID(){
		$.ajax({
			url:'../../GuitarWebApp/app/post/fetchByUserID/2011052405/0/5',// /post/fetchByUserID/'+id
			type:'get',
			success:function(data){
				//var jsondata = $.parseJSON(data);
				$.each(data,function(index,jsonPostShortCut){
					if(index==0){
						return true;
					}
					addPost(jsonPostShortCut.ownerNickName,jsonPostShortCut.publishDate,jsonPostShortCut.content,jsonPostShortCut.id,jsonPostShortCut.likeNum);
				});
			}
		});
	};
//function fetchPostsByFollowee
	function fetchPostByFollowee(){
		$.ajax({
			url:'../../GuitarWebApp/app/post/fetchByFollowee/2011052405/0/5',// /post/fetchByUserID/'+id
			type:'get',
			success:function(data){
				//var jsondata = $.parseJSON(data);
				$.each(data,function(index,jsonPostShortCut){
					addPost(jsonPostShortCut.ownerNickName,jsonPostShortCut.publishDate,jsonPostShortCut.content,jsonPostShortCut.id,jsonPostShortCut.likeNum);
					if(index==data.length-1){
						return true;
					}
				});
			}
		});
	}
	//function fetchPostsByIDs
	var postIdContainer = [];
	function fetchPostByIDs(){
		var Urls = '../../GuitarWebApp/app/post/getByIDs?';
		$.each(postIdContainer,function(n,value){
			if(n != postIdContainer.length-1){
				Urls = Urls +'postIDs='+ value+'&';
			}
			else{
				Urls = Urls +'postIDs='+ value;
				return true;
			}
		});
		$.ajax({
			url: Urls,// 
			type:'get',
			success:function(data){
				//var jsondata = $.parseJSON(data);
				$.each(data,function(index,jsonPostShortCut){
					addPost(jsonPostShortCut.ownerNickName,jsonPostShortCut.publishDate,jsonPostShortCut.content,jsonPostShortCut.id,jsonPostShortCut.likeNum);
					if(index==data.length-1){
						return true;
					}
				});
			}
		});
	}
	//function getUserRepresentation
	function getUserRepresentation(){
		$.ajax({
			url:'../../GuitarWebApp/app/user/getRepresentation/2011052405',
			type:'get',
			success:function(data){
				$('.Anickname').html(data.nickName);
				$('.Agender').html(data.gender);
				$('.Abirth').html(data.birthday);
				$('.Arelationship').html(data.relationship);
				$('.Acampus').html(data.campus);
				$('.Aaddress').html("#"+data.dorm+" "+data.dormNum);
				$('.Alooking').html(data.lookingFor);
			}
		});
	}
	/*function getUserRepresentation(){
		$.ajax({
			url:'../../GuitarWebApp/app/user/getRepresentation/2011052405',
			type:'get',
			success:function(data){
				UserID = data.ID;
				AuthType = data.authType;
				ImageLinks = data.imageLinks;
				FolloweesID = data.followeesID;
				Nickname = data.nickName;
				Gender = data.gender;
				Birth = data.birthday;
				Relationship = data.relationship;
				Campus = data.campus;
				Dorm = data.dorm;
				DormNum = data.dormNum;
				LookingFor = data.lookingFor;	
				alert(Nickname);
			}
		});
	}
	//funtion aboutUpdata
	function aboutUpdata(){
		$('.Anickname').html(Nickname);
		$('.Agender').html(Gender);
		$('.Abirth').html(Birth);
		$('.Arelationship').html(Relationship);
		$('.Acampus').html(Campus);
		$('.Aaddress').html("#"+Dorm+" "+DormNum);
		$('.Alooking').html(LookingFor);
	}*/
//function profileBg
	$('.profile_img').hover(function(){
		var changeBtn = "<div class='changeBtnGroup'><form><button class='btn btn-success profileImgBtn'>Change BlackgroundImg</button><input type='file' name='file' class='btn_file' style='display:none'/></form></div>";//<button class='btn btn-success avatarImgBtn'>Change Avatar</button>
		$('.profile_img').append(changeBtn);
		$('.changeBtnGroup').hide();
		$('.changeBtnGroup').fadeIn(300);
	},function(){
		$('.changeBtnGroup').fadeOut(300, function(){
			$(this).remove();
		});	
	});
//function profileImg 
	$('.profile_user_img').hover(function(){
		var changeBtn = "<button class='btn btn-success profileImg' data-toggle='modal' data-target='#myModal'>Change</button>";
		$(this).append(changeBtn);
		$('.profileImg').hide();
		$('.profileImg').fadeIn(300);
	},function(){
		$('.profileImg').fadeOut(300, function(){
			$(this).remove();
		});	
	});
//show followees
	function showFollowees(){
		$.ajax({
			url:'../../GuitarWebApp/app/user/getRepresentation/2011052405',
			type:'get',
			success: function(data){
				var followeesIDs = data.followeesID;
				$.each(followeesIDs,function(index,followeesID){
					$.ajax({
						url:'../../GuitarWebApp/app/user/getRepresentationShortCut/'+followeesID,
						type:'get',
						success: function(followeesShortCut){
							var followee="<img src='"+followeesShortCut.avatarLink+"'></img>";
							$('.followeeShow').append(followee);
						}
					});
				});
			}
		});
	}
//show photos
	function showPhotos(){
		$.ajax({
			url:'../../GuitarWebApp/app/user/getRepresentation/2011052405',
			type:'get',
			success: function(data){
				$.each(data.imageLinks,function(index,imageLink){
					var photoContainer="<div class='photo'><img src='"+imageLink+"' /></div>";
					$('.photoAddBtn').after(photoContainer);
				});
			}
		});
	}
$(document).ready(function(){
//function userTip
	(function($){  
		$.fn.userTips = function () {
			// Speed of the animations in milliseconds - 1000 = 1 second.
			var animSpeed = 300;			
			var tinyTip;		
			// When we hover over the element that we want the tooltip applied to	
			$(this).hover(function() {	
				var pos = $(this).offset();
				var nPos = pos;
				nPos.top = pos.top + 20;
				nPos.left = pos.left + 40;
				
				$.ajax({
					url:'../../GuitarWebApp/app/user/getRepresentationShortCut/2011052405',
					type:'get',
					success:function(data){
						var tipFrame = '<div class="popTip"><div class="content"><div class="urserBgShort"><img src="images/urseBgShort.jpg" /></div><div class="urserInfShort"><img src="images/user_img4.jpg" /><p><h1>Bond</h1></p><p> a good guy</p><button id="followBtn">Follow</button></div></div></div>';
						//var tipFrame = '<div class="popTip"><div class="content"><div class="urserBgShort"><img src="images/urseBgShort.jpg" /></div><div class="urserInfShort"><img src='+data.avatarLink+' /><p><h1>'+data.nickName+'</h1></p><p>'+data.lookingFor+'</p><button id="followBtn">Follow</button></div></div></div>';
						$('body').append(tipFrame);
						var divTip = 'div.popTip';
						tinyTip = $(divTip);
						tinyTip.hide();
						
						// Make sure that the tooltip has absolute positioning and a high z-index, 
						// then place it at the correct spot and fade it in.
						tinyTip.css('position', 'absolute').css('z-index', '1000');
						tinyTip.css(nPos).fadeIn(animSpeed);
						tinyTip.hover(function(){
							clearTimeout(window.timer);
						},function(){
							tinyTip.fadeOut(animSpeed, function() {
								$(this).remove();
							});
						});
					},
				});
			}, function() {
				// Fade the tooltip out once the mouse moves away and then remove it from the DOM.	
				window.timer = setTimeout(function(){
					tinyTip.fadeOut(animSpeed, function() {
					$(this).remove();
				});
					}, 200);
								
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
	    		var jsonString = {mediaLocation:data,topic:jtopic,content:jcontent,postType:"DISCUSSION",publishDate:date,startDate:date};
	    		var jsonData = $.toJSON(jsonString);
	    		$.ajax({
	    			type:"POST",
	    			url:'../../GuitarWebApp/app/post/add/2011052405',
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
					url:'../../GuitarWebApp/app/post/collect/2011052405/'+id,//'../../GuitarWebApp/app/post/collect/2011052405/'+id
					type:'put'
				});
			}
			if(this.lastChild.lastChild.getAttribute("class") == "glyphicon glyphicon-star"){
				var id = this.firstChild.firstChild.getAttribute("value");
				$.ajax({
					url:'../../GuitarWebApp/app/post/cancelCollect/2011052405/'+id,//'../../GuitarWebApp/app/post/collect/2011052405/'+id
					type:'put'
				});
			}
		});
//function likePost and cancelLike
		$('body').on('click','.post_like',function(){
			if(this.lastChild.lastChild.getAttribute("class") == "glyphicon glyphicon-heart-empty"){
				var id = this.firstChild.firstChild.getAttribute("value");
				$.ajax({
					url:'../../GuitarWebApp/app/post/like/2011052405/'+id,//'../../GuitarWebApp/app/post/collect/2011052405/'+id
					type:'put'
				});
			}
			if(this.lastChild.lastChild.getAttribute("class") == "glyphicon glyphicon-heart"){
				var id = this.firstChild.firstChild.getAttribute("value");
				$.ajax({
					url:'../../GuitarWebApp/app/post/cancelLike/2011052405/'+id,//'../../GuitarWebApp/app/post/collect/2011052405/'+id
					type:'put'
				});
			}
		});
//function likePost and cancelLike
		$('body').on('click','#activityJoin',function(){
			var id = this.firstChild.firstChild.getAttribute("value");
			$.ajax({
				url:'../../GuitarWebApp/app/post/join/2011052405/1',//'../../GuitarWebApp/app/post/collect/2011052405/'+id
				type:'put'
			});
		});
		
//function addComment
		$('body').on('click','#addComment',function(){
			var d = new Date();
        	var publishDateN = d.getFullYear() + "/" +(d.getMonth()+1) + "/" + d.getDate();
        	var date = new Date(publishDateN);
        	var id = this.getAttribute("value");
        	var inputID = "commentText"+id;
			var jcontent = $("input[id='"+inputID+"']").val();
			var jCommentType = "COMMENT";
			var jpublishDate = $("#publishDate").val();
			var jsonString = {content:jcontent,commentType:jCommentType};
			var jsonData = $.toJSON(jsonString);	
			
			$.ajax({
    			type:"POST",
    			url:'../../GuitarWebApp/app/comment/add/2011052405/'+id,//'../../GuitarWebApp/app/comment/add/2011052405/'+id
    			data:jsonData,
    			contentType: "application/json"
    		});
		});
//function follow
		$('body').on('click','#followBtn',function(){
			//get post owner
			if($(this).text()=="Follow"){
				$.ajax({
					url:'../../GuitarWebApp/app/user/follow/2011052405/2011052406',//'../../GuitarWebApp/app/user/follow/2011052405/'+id
					type:'put'
				});
			}
			if($(this).text()=="Following"){
				$.ajax({
					url:'../../GuitarWebApp/app/user/cancelFollow/2011052405/2011052406',//'../../GuitarWebApp/app/user/follow/2011052405/'+id
					type:'put'
				});
			}
		});
		//function editProfileInfro
		$('body').on('click','.aEditbtn',function(){
			$("span[class='Anickname']").html("<input id='focusedInput' class='nicknameE' type='text' value='Winson_Lau' />");
			$("span[class='Alooking']").html("<input class='lookingforE' id='focusedInput' type='text' value='Make friends' />");
			$("span[class='Agender']").html("<select class='genderE'><option value='male'>Male</option><option value='female'>Female</option></select>");
			$("span[class='Arelationship']").html("<select class='relationshipnE'><option value='single'>single</option><option value='loving'>loving</option></select>");
			$("span[class='Aaddress']").html("<select class='addressE'><option value='1'>#1</option><option value='2'>#2</option></select><input class='dormNumE' id='focusedInput' type='text' value='1509'></input>");
			$("span[class='Acampus']").html("<select class='campusE'><option value='JNU'>JNU</option><option value='MNU'>MNU</option></select>");
			$(this).text("Save");
			$(this).attr("class","btn btn-primary aSavebtn");
		});
		//function saveProfileInfro
		$('body').on('click','.aSavebtn',function(){
			var nickName = $('.nicknameE').val();
			var gender = $('.genderE').val();
			var relationship = $('.relationshipnE').val();
			var lookingfor = $('.lookingforE').val();
			var campus = $('.campusE').val();
			var dorm = $('.addressE').val();
			var dormNum = $('.dormNumE').val();
			//var lookingfor = $('.nicknameE').val();
			var d = new Date();
        	var publishDateN = d.getFullYear() + "/" +(d.getMonth()+1) + "/" + d.getDate();
        	var date = new Date(publishDateN);
			$.ajax({
				type:'put',
				url:'../../GuitarWebApp/app/user/updateProfile/2011052405/'+nickName+'/'+gender+'/'+lookingfor+'/'+relationship+'/'+'2011/09/07'+'/'+campus+'/'+dorm+'/'+dormNum,
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
						url:'../../GuitarWebApp/app/user/changeAvatar/2011052405?avatarLink='+data
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
					var Urls = '../../GuitarWebApp/app/user/addImages/2011052405?';
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
		//show reghtml
		$('body').on("click",".signIn",function(){
			$('.containerReg').fadeOut(300);
			$('.containerReg').attr("style","display:none");
			$('.containerSign').fadeIn(300);
		});
		$('body').on("click",".signUp",function(){
			$('.containerSign').fadeOut(300);
			$('.containerSign').attr("style","display:none");
			$('.containerReg').fadeIn(300);
		});
});

