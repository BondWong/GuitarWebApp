<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<input type="hidden" value="<%= session.getAttribute("userID")%>">
	<form method="post" action="/GuitarWebApp/security/UserLoginServlet">
		<input type="hidden" value="<%= session.getAttribute("hiddenCode") %>">
		<input type="text" name="userID"/>
		<input type="password" name="password"/>
		<input type="submit"/>
	</form>
	<form method="post" action="security/UserLogoutServlet">
		<input type="text" name="userID">
		<input type="submit"/>
	</form>
</body>
</html>