<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>upload</title>
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
<script>
 $(function() {
  $('#AjaxStart').click(function() {
   var allData = "test1=1&test2=2";
   $.ajax({
    type : "POST",
    //dataType: "json",
    dataType : 'text',
    data : allData,
    url : "excelUploadAjax",
    success : function(data) {
     alert(data);
    }
   });
  });
 });
</script>
</head>
<body>
	<h1>파일 업로드</h1>
	<form id="excelUploadForm" name="excelUploadForm" enctype="multipart/form-data" method="post"
		action="excelUploadAjax">
		<div class="contents">
			<div>첨부파일은 한개만 등록 가능합니다.</div>

			<dl class="vm_name">
				<dt class="down w90">첨부 파일</dt>
				<dd>
					<input id="excelFile" type="file" name="excelFile" />
				</dd>
			</dl>
		</div>

		<div class="bottom">
			<button id="AjaxStart">Ajax Start</button>
		</div>
	</form>




</body>
</html>
