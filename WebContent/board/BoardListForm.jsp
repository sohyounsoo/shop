<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>        
<html>
<head>
	<title>전체 게시글</title>
		
	<script type="text/javascript">
		function writeForm(){
			location.href="BoardWriteForm.bo";
		}
	</script>
	
</head>
<body>
<div id="wrap">
	
	<c:if test="${sessionScope.sessionID == null}">
			<script type="text/javascript">alert("로그인후 글작성이 가능합니다.")</script>
	</c:if>	
	
	
	<!-- 글목록 위 부분-->
	<div id="topForm">
		<c:if test="${sessionScope.sessionID!=null}">
			<input type="button" value="글쓰기" onclick="writeForm()">
		</c:if>	
	</div>
	<br>
	<!-- 게시글 목록 부분 -->
	<div id="board">
		<table id="bList" width="800" border="3" bordercolor="lightgray">
			<tr heigh="30">
				<td>글번호</td>
				<td>제목</td>
				<td>작성자</td>
				<td>작성일</td>
				<td>조회수</td>
			</tr>
		<c:forEach var="board" items="${requestScope.list}">
			<tr>
				<td>${board.board_num}</td>
				
				<td align="left">
					<c:if test="${board.board_re_lev > 1}">
						<c:forEach begin="1" end="${board.board_re_lev}">
							&nbsp; <!-- 답변글일경우 글 제목 앞에 공백을 준다. -->
						</c:forEach>
						RE:
					</c:if>
					<a href="BoardDetailAction.bo?num=${board.board_num}&page=${spage}">
					${board.board_subject}
					</a>
				</td>
				
				<td>${board.board_id}</td>
				<td>${board.board_date}</td>
				<td>${board.board_count}</td>
			</tr>
		</c:forEach>
		</table>
	</div>
	
	<!-- 페이지 넘버 부분 -->
	<br>
	<div id="pageForm">
	
		<c:if test="${startPage != 1}">
			<a href='BoardListAction.bo?page=${startPage-1}'>[이전]</a>
		</c:if>
		
		<c:forEach var="pageNum" begin="${startpage=1}" end="${endPage}"><!-- 페이지번호 -->
			<c:if test="${pageNum == spage}">
				${pageNum}&nbsp;
			</c:if>
			<c:if test="${pageNum != spage}">
				<a href='BoardListAction.bo?page=${pageNum}'>${pageNum}&nbsp;</a> <!-- 게시판 페이지이동 -->
			</c:if>
		</c:forEach>
		
		<c:if test="${endPage != maxPage }">
			<a href='BoardListAction.bo?page=${endPage+1 }'>[다음]</a>
		</c:if>
	</div>
	
	<!--  검색 부분 -->
	<br>
	<div id="searchForm">
		<form>
			<select name="opt">
				<option value="0">제목</option>
				<option value="1">내용</option>
				<option value="2">제목+내용</option>
				<option value="3">글쓴이</option>
			</select>
			<br>
			<input type="text" size="20" name="condition"/>&nbsp;
			<br>			
			<input type="submit" value="검색"/>
		</form>	
	</div>
</div>	

</body>
</html>