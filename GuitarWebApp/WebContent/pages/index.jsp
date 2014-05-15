<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	hello
	<input type="hidden" value="<%= session.getAttribute("userID")%>" />
	<form action="/GuitarWebApp/app/fileUploader" method="post" enctype="multipart/form-data">
		<input type="text" name="userID"/>
		<input type="file" name="image1"/>
		<input type="file" name="image2"/>
		<input type="file" name="image3"/>
		<input type="submit"/>
	</form>
</body>
</html>