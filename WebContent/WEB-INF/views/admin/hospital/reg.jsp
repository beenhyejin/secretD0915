<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 글등록</title>
<link href="../../css/reset.css" type="text/css" rel="stylesheet" />
<link href="../../css/headfoot.css" type="text/css" rel="stylesheet" />
<link href="../../css/main/hospital.css" type="text/css"
	rel="stylesheet" />
</head>
<body>
	<!-- header 부분 -->
	<jsp:include page="../../inc/header.jsp" />

	<main class="main">
	<div class="title">
		<img src="../../images/bg-search.png" />
	</div>
	<div class="sr-container">
		<form method="post" class="edit-form">
			<table class="list-table">
				<tr>
					<th>진료과목</th>
					<th>병원명</th>
					<th>주소</th>
					<th>전화번호</th>
				</tr>
				<tr>
					<td><input type="text" name="subject"></td>
					<td class="text-left"><input type="text" name="name"></td>
					<td><input type="text" name="address"></td>
					<td><input type="text" name="phone_number"></td>
				</tr>
			</table>
			<div class="btns">
				<input type="submit" class="btn pad" value="등록" /> <input
					type="button" class="btn pad" value="취소"
					onclick="location.href='list'" />
			</div>
		</form>
	</div>
	</main>
	<jsp:include page="../../inc/footer.jsp" />
</body>
</html>