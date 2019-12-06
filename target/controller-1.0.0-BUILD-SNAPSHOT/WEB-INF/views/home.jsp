<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	파일 업로드
</h1>
	<form id="upLoadForm" name="upLoadForm" method="post"	action="upLoad.do">
		<input type="submit" value="엑셀 파일 업로드" />
	</form>
</body>
</html>
