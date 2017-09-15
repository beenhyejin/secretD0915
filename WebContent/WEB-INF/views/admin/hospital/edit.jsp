<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
					<td><input type="text" name="subject" value="${detail.subject }"></td>
					<td class="text-left"><input type="text" name="name" value=${detail.name}></td>
					<td><input type="text" name="address" value="${detail.address}"></td>
					<td><input type="text" name="phone-number" value="${detail.phone_number}"></td>
				</tr>
			</table>

			<div class="btns">
				<input type="hidden" name="id" value=${ detail.id }>
				<!-- 어떠한 값을 업데이트 할지 -->
				<input type="submit" class="btn pad" value="저장" />
				<!-- input에 있는 값, key가 있는 값만 전달 -->
				<input type="button" class="btn pad" value="취소" onclick="location.href='list'"/>

			</div>
		</form>
	</div>
	</main>
	<!-- footer부분 -->
	<jsp:include page="../../inc/footer.jsp" />
</body>
</html>