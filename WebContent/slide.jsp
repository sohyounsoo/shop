<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<style type="text/css">
@import url(assets/css/FirstView.css);
</style>
</head>
<body>
<div class="section">
	<input type="radio" name="slide" id="slide01" checked>
	<input type="radio" name="slide" id="slide02">
	<input type="radio" name="slide" id="slide03">
	<div class="slidewrap">
		
		<ul class="slidelist">
			<!-- 슬라이드 영역 -->
			<li class="slideitem">
				<a>
					<div class="textbox">
						<h3>쇼핑몰 사이트</h3>
						<p></p>
					</div>
					<img src="./img/드론사진.jpeg">
				</a>
			</li>
			<li class="slideitem">
				<a>
					
					<div class="textbox">
						<h3>게시판 문의</h3>
						<p></p>
					</div>
					<img src="./img/게시판사진.png">
				</a>
			</li>
			<li class="slideitem">
				<a>
					
					<div class="textbox">
						<h3></h3>
						<p></p>
					</div>
					<img src="./img/드론사진.jpeg">
				</a>
			</li class="slideitem">

			<!-- 좌,우 슬라이드 버튼 -->
			<div class="slide-control">
				<div>
					<label for="slide03" class="left"></label>
					<label for="slide02" class="right"></label>
				</div>
				<div>
					<label for="slide01" class="left"></label>
					<label for="slide03" class="right"></label>
				</div>
				<div>
					<label for="slide02" class="left"></label>
					<label for="slide01" class="right"></label>
				</div>
			</div>

		</ul>
		<!-- 페이징 -->
		<ul class="slide-pagelist">
			<li><label for="slide01"></label></li>
			<li><label for="slide02"></label></li>
			<li><label for="slide03"></label></li>
		</ul>
	</div>

	
</div>
</body>
</html>