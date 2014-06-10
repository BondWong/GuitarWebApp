<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%@ include file="parts/head.jsp"%>

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
		<div class="content_container">
			<a href="show.jsp"><div class="img_container">
					<img src="images/i1.jpg" />
				</div></a>
			<div class="content_info">
				<div class="conten_head">Joke of the Day</div>
				<div class="content_count">268,123 members</div>
				<div class="content_join" id="activityJoin" style="cursor: pointer">
					<a><input id="joinID" type='hidden' value="1" />Join</a>
				</div>
			</div>
		</div>
		<div class="content_container">
			<a href="show.jsp"><div class="img_container">
					<img src="images/i2.jpg" />
				</div></a>
			<div class="content_info">
				<div class="conten_head">Joke of the Day</div>
				<div class="content_count">268,123 members</div>
				<a><div class="content_join">Join</div></a>
			</div>
		</div>
		<div class="content_container">
			<a href="show.jsp"><div class="img_container">
					<img src="images/i3.jpg" />
				</div></a>
			<div class="content_info">
				<div class="conten_head">Joke of the Day</div>
				<div class="content_count">268,123 members</div>
				<a><div class="content_join">Join</div></a>
			</div>
		</div>
		<div class="content_container">
			<a href="show.jsp"><div class="img_container">
					<img src="images/i2.jpg" />
				</div></a>
			<div class="content_info">
				<div class="conten_head">Joke of the Day</div>
				<div class="content_count">268,123 members</div>
				<a><div class="content_join">Join</div></a>
			</div>
		</div>
		<div class="content_container">
			<a href="show.jsp"><div class="img_container">
					<img src="images/i1.jpg" />
				</div></a>
			<div class="content_info">
				<div class="conten_head">Joke of the Day</div>
				<div class="content_count">268,123 members</div>
				<a><div class="content_join">Join</div></a>
			</div>
		</div>
		<div class="content_container">
			<a href="show.jsp"><div class="img_container">
					<img src="images/i2.jpg" />
				</div></a>
			<div class="content_info">
				<div class="conten_head">Joke of the Day</div>
				<div class="content_count">268,123 members</div>
				<a><div class="content_join">Join</div></a>
			</div>
		</div>
		<div class="content_container">
			<a href="show.jsp"><div class="img_container">
					<img src="images/i3.jpg" />
				</div></a>
			<div class="content_info">
				<div class="conten_head">Joke of the Day</div>
				<div class="content_count">268,123 members</div>
				<a><div class="content_join">Join</div></a>
			</div>
		</div>
	</div>
	<%@ include file="parts/securityCode.jsp"%>
	<script src="js/jquery-1.10.2.js"></script>
	<script src="js/jquery.json.min.js"></script>
	<script src="styles/bootstrap-3.0.3-dist/dist/js/bootstrap.min.js"></script>
	<script src="js/masonry.pkgd.min.js"></script>
	<script src="js/imagesloaded.pkgd.min.js"></script>
	<script src="js/function.js"></script>
	<script src="js/EventHandle.js"></script>
	<script type="text/javascript">
		Msnry('.container_custom', '.content_container', 265);
	</script>
</body>
</html>
