<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="jsp.member.model.MemberBean" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 


<%--
	String contentPage=request.getParameter("contentPage");
	if(contentPage==null)
		contentPage="";
		
		아래에서 변경함
--%>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="assets/css/main.css" />
		<noscript><link rel="stylesheet" href="assets/css/noscript.css" /></noscript>
	<title>가전제품사이트</title>	
	<style type="text/css">
		#wrap {
			width: 600px;
			margin: 0 auto 0 auto;
		}

		#topForm{
			text-align :right;
		}

		#board, #pageForm, #searchForm{
			text-align :center;
		}
		
		#bList{
			text-align :center;
		}
	</style>
</head>
<body class="is-preload">
<jsp:include page="Header.jsp" />	
		<!-- Wrapper -->
			<div id="wrapper">
				<!-- Header -->
					<header id="header" class="alt">
						<a onclick="changeView(0)" class="logo"><strong>종합프로젝트</strong> <span>드론사이트</span></a>
						<nav>
							<a href="#menu">Menu</a>
						</nav>
					</header>
		
				<!-- Menu -->
					<nav id="menu">
						<ul class="links">
							<li><button class="btn-4" onclick="changeView(0)">HOME</button></li>
							 <c:if test="${sessionScope.sessionID!=null}">
							<li><button id="logoutBtn" class="btn-2" onclick="changeView(3)">로그아웃</button></li>
							<li><button id="updateBtn" class="btn-2" onclick="changeView(4)">내정보</button></li>
							<li><button id="updateBtn" class="btn-2" onclick="changeView(8)">장바구니보기</button></li>
							</c:if>
						</ul>
						<ul class="actions stacked">
							<li><button id="" class="btn-3" onclick="changeView(7)">상품보기</button></li>
							<li><button id="joinBtn" class="btn-5" onclick="changeView(6)">게시판</button></li>
							<!-- // 로그인 안되었을 경우 - 로그인, 회원가입 버튼을 보여준다. -->
        					<c:if test="${sessionScope.sessionID==null}">
							<li><button id="loginBtn" class="btn-1" onclick="changeView(1)">로그인</button></li>
							<li><button id="joinBtn" class="btn-1" onclick="changeView(2)">회원가입</button></li>
							</c:if>
							 <c:if test="${sessionScope.sessionID !=null && sessionScope.sessionID=='admin'}">
							<li> <button id="memberViewBtn" class="btn-4" onclick="changeView(5)">회원보기</button></li>
							</c:if>
						</ul>
					</nav>

				<!-- Banner -->
					<section id="banner" class="major">
						<div class="inner">
							<c:set var="contentPage" value="${param.contentPage}"/>
								<c:if test="${contentPage==null}">	<br><br><br><br>
									<jsp:include page="slide.jsp" />
								</c:if>
									<jsp:include page="${contentPage}" />
						</div>
					</section>
		
				<!-- Footer -->
					<footer id="footer">
						<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
						<div class="inner">
							<ul class="icons">
								<li><a href="#" class="icon brands alt fa-twitter"><span class="label">Twitter</span></a></li>
								<li><a href="#" class="icon brands alt fa-facebook-f"><span class="label">Facebook</span></a></li>
								<li><a href="#" class="icon brands alt fa-instagram"><span class="label">Instagram</span></a></li>
								<li><a href="#" class="icon brands alt fa-github"><span class="label">GitHub</span></a></li>
								<li><a href="#" class="icon brands alt fa-linkedin-in"><span class="label">LinkedIn</span></a></li>
							</ul>
							<ul class="copyright">
								<li>&copy; Untitled</li><li>Design: <a href="https://html5up.net">HTML5 UP</a></li>
							</ul>
						</div>
					</footer>

			</div>
	
<!-- Scripts -->
			<script src="assets/js/jquery.min.js"></script>
			<script src="assets/js/jquery.scrolly.min.js"></script>
			<script src="assets/js/jquery.scrollex.min.js"></script>
			<script src="assets/js/browser.min.js"></script>
			<script src="assets/js/breakpoints.min.js"></script>
			<script src="assets/js/util.js"></script>
			<script src="assets/js/main.js"></script>
</body>
</html>