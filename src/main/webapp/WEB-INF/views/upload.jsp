<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>upload</title>

</head>
<body>
	<h1>파일 업로드</h1>
	<form id="excelForm" action="excelUpload" enctype="multipart/form-data"
		method="post">
		<input type="file" id="file" name="file" value="엑셀업로드" accept=".xlsx">
		<input type="submit" value="업로드">
	</form>
</body>
</html>