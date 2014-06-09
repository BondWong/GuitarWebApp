<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:choose>
	<c:when test="${sessionScope.userID eq null }">
		<form class="navbar-form navbar-costom" role="form" method="post"
			action="../security/LoginServlet">
			<div class="form-group">
				<input type="text" placeholder="UesrID" class="form-control"
					name="userID" required>
			</div>
			<div class="form-group">
				<input type="password" placeholder="Password" class="form-control"
					name="password" required>
			</div>
			<button class="btn btn-success" type="submit">Sign in</button>
		</form>
	</c:when>

	<c:otherwise>
		<%@ include file="navRightProfile.jsp"%>
	</c:otherwise>
</c:choose>