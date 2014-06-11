<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%@ include file="parts/head.jsp"%>
<!-- Generic page styles -->
<body>
	<div class="navbar navbar-fixed-top navbar-inverse" role="navigation">
		<div class="container">
			<%@ include file="parts/navLeft.jsp"%>
			<div class="collapse navbar-collapse">
				<%@ include file="parts/communityDropDown.jsp"%>
				
				<%@ include file="parts/navRight.jsp"%>
			</div>
			<!-- /.nav-collapse -->
		</div>
		<!-- /.container -->
	</div>
	<!-- /.navbar -->
	<div class="container container_custom">
		<div class="alert alert-success alertCust">New Post!</div>
		<div class="pro_body">
			<div class="share post">
				<form enctype="multipart/form-data">
					<input class="form-control share_txt" type="text" placeholder="share anything you what to share" />
					<div class="shareBtnGroup">
						<div role="button" class="Btnshare btnMotion" data-toggle='modal' data-target='#addPostModal'><div class="Iconshare" style="background-image:url(images/motion.png);"></div><div>Motion</div></div>
						<div role="button" class="Btnshare btnMotion" data-toggle='modal' data-target='#addPostModal'><div class="Iconshare" style="background-image:url(images/photo.png);"></div><div>Photos</div></div>
						<div role="button" class="Btnshare btnMotion" data-toggle='modal' data-target='#addPostModal'><div class="Iconshare" style="background-image:url(images/video.png);"></div><div>Videos</div></div>
						<div role="button" class="Btnshare btnMotion" data-toggle='modal' data-target='#addPostModal'><div class="Iconshare" style="background-image:url(images/share.png);"></div><div class="Fontshare">Share</div></div>
					
					</div>
				</form>
				<!-- Modal -->
				<div class="modal fade" id="addPostModal" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">&times;</button>
								<h4 class="modal-title" id="myModalLabel">Share Post</h4>
							</div>
							<form class="photoForm" enctype="multipart/form-data">
								<div class="modal-body">
									
									
									<form id="fileupload" action="//jquery-file-upload.appspot.com/"  enctype="multipart/form-data" data-ng-app="demo" data-ng-controller="DemoFileUploadController" data-file-upload="options" data-ng-class="{'fileupload-processing': processing() || loadingFiles}">
        <input class="form-control share_txt"  id="share_txt2" type="text" placeholder="share anything you what to share" />
        <!-- The fileupload-buttonbar contains buttons to add/delete files and start/cancel the upload -->
        <div class="row fileupload-buttonbar">
            <div class="col-lg-7">
                <!-- The fileinput-button span is used to style the file input field as button -->
                <span class="btn btn-success fileinput-button" ng-class="{disabled: disabled}">
                    <i class="glyphicon glyphicon-plus"></i>
                    <span>Add files...</span>
                    <input type="file" name="files[]" multiple ng-disabled="disabled">
                </span>
                <button type="button" class="btn btn-primary start" data-ng-click="submit()">
                    <i class="glyphicon glyphicon-upload"></i>
                    <span>Start upload</span>
                </button>     
                <!-- The global file processing state -->
                <span class="fileupload-process"></span>
            </div>
            <!-- The global progress state -->
            <div class="col-lg-5 fade" data-ng-class="{in: active()}">
                <!-- The global progress bar -->
                <div class="progress progress-striped active" data-file-upload-progress="progress()"><div class="progress-bar progress-bar-success" data-ng-style="{width: num + '%'}"></div></div>
                <!-- The extended global progress state -->
                <div class="progress-extended">&nbsp;</div>
            </div>
        </div>
        <!-- The table listing the files available for upload/download -->
        <table class="table table-striped files ng-cloak">
            <tr data-ng-repeat="file in queue" data-ng-class="{'processing': file.$processing()}">
                <td data-ng-switch data-on="!!file.thumbnailUrl">
                    <div class="preview" data-ng-switch-when="true">
                        <a data-ng-href="{{file.url}}" title="{{file.name}}" download="{{file.name}}" data-gallery><img data-ng-src="{{file.thumbnailUrl}}" alt=""></a>
                    </div>
                    <div class="preview" data-ng-switch-default data-file-upload-preview="file"></div>
                </td>
                <td>
                    <p class="name" data-ng-switch data-on="!!file.url">
                        <span data-ng-switch-when="true" data-ng-switch data-on="!!file.thumbnailUrl">
                            <a data-ng-switch-when="true" data-ng-href="{{file.url}}" title="{{file.name}}" download="{{file.name}}" data-gallery>{{file.name}}</a>
                            <a data-ng-switch-default data-ng-href="{{file.url}}" title="{{file.name}}" download="{{file.name}}">{{file.name}}</a>
                        </span>
                        <span data-ng-switch-default>{{file.name}}</span>
                    </p>
                    <strong data-ng-show="file.error" class="error text-danger">{{file.error}}</strong>
                </td>
                <td>
                    <p class="size">{{file.size | formatFileSize}}</p>
                    <div class="progress progress-striped active fade" data-ng-class="{pending: 'in'}[file.$state()]" data-file-upload-progress="file.$progress()"><div class="progress-bar progress-bar-success" data-ng-style="{width: num + '%'}"></div></div>
                </td>
                <td>
                    <button type="button" class="btn btn-primary start" data-ng-click="file.$submit()" data-ng-hide="!file.$submit || options.autoUpload" data-ng-disabled="file.$state() == 'pending' || file.$state() == 'rejected'">
                        <i class="glyphicon glyphicon-upload"></i>
                        <span>Start</span>
                    </button>
                    <button type="button" class="btn btn-warning cancel" data-ng-click="file.$cancel()" data-ng-hide="!file.$cancel">
                        <i class="glyphicon glyphicon-ban-circle"></i>
                        <span>Cancel</span>
                    </button>
                    <button data-ng-controller="FileDestroyController" type="button" class="btn btn-danger destroy" data-ng-click="file.$destroy()" data-ng-hide="!file.$destroy">
                        <i class="glyphicon glyphicon-trash"></i>
                        <span>Delete</span>
                    </button>
                </td>
            </tr>
        </table>
    </form>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">Close</button>
									<button type="button" class="btn btn-primary" id="btn_share" value="upload">Share</button>
								</div>
							</form>
						</div>
						<!-- /.modal-content -->
					</div>
					<!-- /.modal-dialog -->
				</div>
				<!-- /.modal -->
			</div>
			<div class="post">
				<div class="post_body">
					<div class="row">
						<div class="col-md-2">
							<div class="user_img">
								<img src="images/user_img.jpg" />
							</div>
						</div>
						<div class="col-md-6">
							<div class="user_name">
								<strong>Winson_Lau</strong>
							</div>
							<div class="user_info">Yesterday 21:23pm</div>
						</div>
					</div>
					<div class="post_info">
						This is not a new article in PNAS, but it details results from a
						unique study and I have wanted to write about it for a while.
						NOTE: this is a
						<div class="post_more">
							<a>read more...</a>
						</div>
					</div>
					<div class="post_img">
						<img src="images/9.jpg" />
					</div>
					<div class="row">
						<div class="col-md-1">
							<div class="like">
								<a><span class="glyphicon glyphicon-heart-empty"
									style="font-size: 20px"></span></a>
							</div>
						</div>
						<div class="col-md-1">
							<div class="post_share">
								<a><span class="glyphicon glyphicon-share-alt"
									style="font-size: 20px"></span></a>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="post">
				<div class="post_body">
					<div class="row">
						<div class="col-md-2">
							<div class="user_img">
								<img class="userImg" src="images/user_img.jpg" />
							</div>
						</div>
						<div class="col-md-6">
							<div class="user_name">
								<strong>Winson_Lau</strong>
							</div>
							<div class="user_info">Yesterday 21:23pm</div>
						</div>
					</div>
					<div class="post_info">
						This is not a new article in PNAS, but it details results from a
						unique study and I have wanted to write about it for a while.
						NOTE: this is a
						<div class="post_more">
							<a>read more...</a>
						</div>
					</div>
					<div class="post_img">
						<img src="images/12.png" />
					</div>
					<div class="row">
						<div class="col-md-1">
							<div class="post_like" style="cursor: pointer">
								<a><input id="likeID" type='hidden' value="1" /><span
									class="glyphicon glyphicon-heart-empty" style="font-size: 20px">0</span></a>
							</div>
						</div>
						<div class="col-md-1">
							<div class="post_collect" style="cursor: pointer">
								<a><input id="collectID" type='hidden' value="1" /><span
									class="glyphicon glyphicon-star-empty" style="font-size: 20px"></span></a>
							</div>
						</div>
						<div class="col-md-1">
							<div class="post_share" style="cursor: pointer">
								<a><span class="glyphicon glyphicon-share-alt"
									style="font-size: 20px"></span></a>
							</div>
						</div>
					</div>
					<div class="media_comm">
						<div class="row addCommentBtn">
							<div class="col-lg-8">

								<div class="form-group">
									<input type="text" placeholder="Add a comment"
										class="form-control" id="commentText12" />
								</div>
							</div>
							<div class="col-lg-4">

								<button type="button" class="btn btn-success" id="addComment"
									value="12">Submit</button>
							</div>
						</div>
						<div class="act_content">
							<div class="row">
								<div class="col-lg-1">
									<img src="images/user_img2.jpg" />
								</div>
								<div class="col-lg-11">
									<div class="ures_name">
										<strong>Winson_Lau</strong>
									</div>
									<div class="user_info">Yesterday 21:23pm</div>
								</div>
							</div>
							<div class="act_comment">There's an extension that whenever
								you choose something that has a shortcut it has a toast popup
								that tells you what the shortcut would have been. I used it
								briefly a long time ago. But it turned out to be really
								annoying. Still it was a great idea.﻿</div>
						</div>
						<div class="act_content">
							<div class="row">
								<div class="col-lg-1">
									<img src="images/user_img3.jpg" />
								</div>
								<div class="col-lg-11">
									<div class="ures_name">
										<strong>Thackoor Singh</strong>
									</div>
									<div class="user_info">Yesterday 21:23pm</div>
								</div>
							</div>
							<div class="act_comment">free!</div>
						</div>
					</div>
				</div>
			</div>
			<div class="post">
				<div class="post_body">
					<div class="row">
						<div class="col-md-2">
							<div class="user_img">
								<img src="images/user_img.jpg" />
							</div>
						</div>
						<div class="col-md-6">
							<div class="user_name">
								<strong>Winson_Lau</strong>
							</div>
							<div class="user_info">Yesterday 21:23pm</div>
						</div>
					</div>
					<div class="post_info">
						This is not a new article in PNAS, but it details results from a
						unique study and I have wanted to write about it for a while.
						NOTE: this is a
						<div class="post_more">
							<a>read more...</a>
						</div>
					</div>
					<div class="post_img">
						<img src="images/2.jpg" />
					</div>
					<div class="row">
						<div class="col-md-1">
							<div class="like">
								<a><span class="glyphicon glyphicon-heart-empty"
									style="font-size: 20px"></span></a>
							</div>
						</div>
						<div class="col-md-1">
							<div class="post_share">
								<a><span class="glyphicon glyphicon-share-alt"
									style="font-size: 20px"></span></a>
							</div>
						</div>
					</div>
				</div>

			</div>
			<div class="post">
				<div class="post_body">
					<div class="row">
						<div class="col-md-2">
							<div class="user_img">
								<img src="images/user_img.jpg" />
							</div>
						</div>
						<div class="col-md-6">
							<div class="user_name">
								<strong>Winson_Lau</strong>
							</div>
							<div class="user_info">Yesterday 21:23pm</div>
						</div>
					</div>
					<div class="post_info">
						This is not a new article in PNAS, but it details results from a
						unique study and I have wanted to write about it for a while.
						NOTE: this is a
						<div class="post_more">
							<a>read more...</a>
						</div>
					</div>
					<div class="post_img">
						<img src="images/4.jpg" />
					</div>
					<div class="row">
						<div class="col-md-1">
							<div class="like">
								<a><span class="glyphicon glyphicon-heart-empty"
									style="font-size: 20px"></span></a>
							</div>
						</div>
						<div class="col-md-1">
							<div class="post_share">
								<a><span class="glyphicon glyphicon-share-alt"
									style="font-size: 20px"></span></a>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="post">
				<div class="post_body">
					<div class="row">
						<div class="col-md-2">
							<div class="user_img">
								<img src="images/user_img.jpg" />
							</div>
						</div>
						<div class="col-md-6">
							<div class="user_name">
								<strong>Winson_Lau</strong>
							</div>
							<div class="user_info">Yesterday 21:23pm</div>
						</div>
					</div>
					<div class="post_info">
						This is not a new article in PNAS, but it details results from a
						unique study and I have wanted to write about it for a while.
						NOTE: this is a
						<div class="post_more">
							<a>read more...</a>
						</div>
					</div>
					<div class="post_img">
						<img src="images/13.png" />
					</div>
					<div class="row">
						<div class="col-md-1">
							<div class="like">
								<a><span class="glyphicon glyphicon-heart-empty"
									style="font-size: 20px"></span></a>
							</div>
						</div>
						<div class="col-md-1">
							<div class="post_share">
								<a><span class="glyphicon glyphicon-share-alt"
									style="font-size: 20px"></span></a>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="post">
				<img src="images/3.jpg" />
			</div>
			<div class="post">
				<img src="images/2.jpg" />
			</div>
		</div>
	</div>
	<%@ include file="parts/securityCode.jsp"%>
	<script src="js/jquery-1.10.2.js"></script>
	<script src="js/jquery.json.min.js"></script>
	<script src="styles/bootstrap-3.0.3-dist/dist/js/bootstrap.min.js"></script>
	<script src="js/masonry.pkgd.min.js"></script>
	<script src="js/imagesloaded.pkgd.min.js"></script>
	<script src="js/vendor/jquery.ui.widget.js"></script>
	
<!-- The Load Image plugin is included for the preview images and image resizing functionality -->
<script src="http://blueimp.github.io/JavaScript-Load-Image/js/load-image.min.js"></script>
<!-- The Canvas to Blob plugin is included for image resizing functionality -->
<script src="http://blueimp.github.io/JavaScript-Canvas-to-Blob/js/canvas-to-blob.min.js"></script>
<!-- blueimp Gallery script -->
<script src="http://blueimp.github.io/Gallery/js/jquery.blueimp-gallery.min.js"></script>
<!-- The Iframe Transport is required for browsers without support for XHR file uploads -->
<script src="js/jquery.iframe-transport.js"></script>
<!-- The basic File Upload plugin -->
<script src="js/jquery.fileupload.js"></script>
<!-- The File Upload processing plugin -->
<script src="js/jquery.fileupload-process.js"></script>
<!-- The File Upload image preview & resize plugin -->
<script src="js/jquery.fileupload-image.js"></script>
<!-- The File Upload video preview plugin -->
<script src="js/jquery.fileupload-video.js"></script>
<!-- The File Upload validation plugin -->
<script src="js/jquery.fileupload-validate.js"></script>
<!-- The File Upload Angular JS module -->
<script src="js/jquery.fileupload-angular.js"></script>
<!-- The main application script -->
<script src="js/app.js"></script>
	<script src="js/function.js"></script>
	<script src="js/EventHandle.js"></script>
	<script type="text/javascript">
		Msnry('.pro_body', '.post', 435);
		fecthPostsByType('DISCUSSION');
	</script>
</body>
</html>
