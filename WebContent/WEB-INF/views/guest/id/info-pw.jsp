<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport"
	content="width=device-width, innitial-scale=1.0, maximum-scale=1.0,user-scalable=0" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link href="../../css/reset.css" type="text/css" rel="stylesheet" />
<link href="../../css/headfoot.css" type="text/css" rel="stylesheet" />
<link href="../../css/idFind.css" type="text/css" rel="stylesheet" />
<title>비밀번호 찾기 정보 : Secret D</title>
</head>
<body>

	<!-- header 부분 -->
	<jsp:include page="../../inc/header.jsp" />
	<!-- 	<div class="space"></div>
	main 부분
	<main class="body">
		<div id="id-find-pw-find">
			<a href="../id/find">아이디 찾기</a> <a href="../id/find-pw">비밀번호
				찾기</a>
		</div>
	<div class="content-container"> -->
	<main class="main">

	<div class="title">
		<img src="../../images/bg-cscenter.jpg" />
	</div>
	
	<div class="nt-container">
		<div class="form">
			<h2>비밀번호 찾기</h2>
			<h3>고객님의 정보와 일치하는 비밀번호입니다.</h3>


			<form method="post" class="pw-find-info clearfix">
				<div class="id-info">${param.pwd}</div>
			</form>
			<div class="find-next-info">
				<a href="../../login"><input type="button"
					class="btn btn-default btn-pad" value="로그인 하기" /></a> <a
					href="find-pw"><input type="button"
					class="btn btn-default btn-pad" value="비밀번호 찾기" /></a>
			</div>
		</div>
	</div>
	<!-- <div class="nt-container">
		<div class="form">
			<h2>비밀번호 찾기</h2>
			<h3>비밀번호 찾는 방법을 선택해 주세요.</h3>
			<div class="find">
			<form method="post" class="id-find-info clearfix">
				<div class="email-text">
					<div class="id-find-email-label">
						<label>현재 비번</label><p> <label>새 비번</label><p> <label>새
							비번확인 </label>
					</div>
					<div class="id-find-email-input">
						<input type="text" name="pwd" /> <input type="text" name="newPwd" />
						<input type="text" name="newPwd" />
					</div>
				</div>
			</form>
			<div class="find-next-info2">
				<input type="submit" name="btn" class="btn" value="확인" />
			</div>
			</div>
		</div>
	</div> -->
	</main>
	<!-- footer 부분 -->
	<jsp:include page="../../inc/footer.jsp" />
</body>
</html>