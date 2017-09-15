<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
		<h2 class="hidden">공지사항</h2>

		<div class="search-form2">
			<c:set var="num1" value="외과" />
			<c:set var="num2" value="비뇨기과" />
			<c:set var="num3" value="산부인과" />
			<c:set var="num4" value="" />

			<c:set var="addr1" value="서울" />
			<c:set var="addr2" value="부산" />
			<c:set var="addr3" value="대구" />
			<c:set var="addr4" value="인천" />
			<c:set var="addr5" value="광주" />
			<c:set var="addr6" value="대전" />
			<c:set var="addr7" value="울산" />
			<c:set var="addr8" value="세종" />
			<c:set var="addr9" value="경기도" />
			<c:set var="addr10" value="강원도" />
			<c:set var="addr11" value="충청북도" />
			<c:set var="addr12" value="충청남도" />
			<c:set var="addr13" value="전라북도" />
			<c:set var="addr14" value="전라남도" />
			<c:set var="addr15" value="경상북도" />
			<c:set var="addr16" value="경상남도" />
			<c:set var="addr17" value="제주" />

			<div>
				<ul>
					<li><a href="?sub=${num4}">전체</a></li>
					<li><a href="?sub=${num1 }">항외과</a></li>
					<li><a href="?sub=${num2}">비뇨기과</a></li>
					<li><a href="?sub=${num3 }">산부인과</a></li>
				</ul>
			</div>

			<c:set var="tt" value="${param.name }" />
			<c:set var="s" value="${param.sub }" />
			<form
				action="?sub=${sh.value }&name=${param.name}&addr=${addr.value}">
				<select name="addr" id="addr">
					<option value="">시도 선택 </option>
					<option value="${addr1 }">서울특별시 </option>
					<option value="${addr2 }">부산광역시 </option>
					<option value="${addr3 }">대구광역시 </option>
					<option value="${addr4 }">인천광역시 </option>
					<option value="${addr5 }">광주광역시 </option>
					<option value="${addr6 }">대전광역시 </option>
					<option value="${addr7 }">울산광역시 </option>
					<option value="${addr8 }">세종특별자치시</option>
					<option value="${addr9 }">경기도 </option>
					<option value="${addr10 }">강원도</option>
					<option value="${addr11}">충청북도</option>
					<option value="${addr12 }">충청남도</option>
					<option value="${addr13 }"> 전라북도</option>
					<option value="${addr14 }">전라남도</option>
					<option value="${addr15 }">경상북도</option>
					<option value="${addr16 }">경상남도 </option>
					<option value="${addr17 }">제주특별자치도 </option>

				</select> <select name="sub" id="sh">
					<option value="${num4 }">진료 과목 선택</option>
					<option value="${num1 }">항외과</option>
					<option value="${num2 }">비뇨기과</option>
					<option value="${num3 }">산부인과</option>
				</select> <input type="text" class="input-text" name="name"
					placeholder="병원명을 입력하세요" /> <input type="submit" class="btn" />
			</form>

		</div>
		<form method="post">
			<table class="list-table">
				<tr>
					<th>진료과목</th>
					<th>병원명</th>
					<th>주소</th>
					<th>전화번호</th>
					<th><input type="submit" class="btn" name=del value="삭제"></th>

				</tr>
				<c:forEach var="h" items="${list}">
					<tr>
						<td>${ h.subject }</td>
						<td class="text-left"><a href="../hospital/edit?h_id=${h.id}">${h.name}</a></td>
						<td>${h.address}</td>
						<td>${ h.phone_number }</td>
						<td><input type="checkbox" name="check" value=${h.id}>
					</tr>
				</c:forEach>
			</table>
		</form>
		<div class="num">
			<c:set var="page" value="${param.p}" />
			<c:set var="startNum" value="${page-(page-1)%5}" />
			<c:set var="lastNum"
				value="${fn:substringBefore((count/10)==0? count/10: count/10+1,'.')}" />
			<div>
				<a href="?p=1">이전</a>
			</div>
			<ul>
				<c:forEach var="i" begin="0" end="4">
					<c:set var="strong" />
					<c:if test="${page==(startNum+i)}">
						<c:set var="strong" value="text-strong" />
					</c:if>
					<c:if test="${startNum+i<=lastNum}">
						<li><a class="${strong}"
							href="?p=${startNum+i}&sub=${param.sub}&name=${param.name}&addr=${param.addr}">${startNum+i}</a></li>
					</c:if>
					<c:if test="${startNum+i>lastNum}">
						<li>${startNum+i}</li>
					</c:if>
				</c:forEach>
			</ul>
			<div>
				<c:if test="${lastNum>= startNum+5}">
					<a href="?p=${startNum+5}">다음</a>
				</c:if>
			</div>
		</div>
	</main>
	<!-- footer부분 -->
	<jsp:include page="../../inc/footer.jsp" />
</body>
</html>