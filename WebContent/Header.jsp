<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<html>
<head>
    <title>상단 영역</title>   
    <link rel="stylesheet" href="css/bootstrap.min.css"> 
    <style type="text/css">
    	@import url(css/button.css);
    
        #wrap{
            text-align: center;
            width: 650px;
            height: 100px;
        }      
        .container{
         text-align: center;
        }
        
    </style>
    
    <script type="text/javascript">
        
        function changeView(value){
            
            if(value == "0") // HOME 버튼 클릭시 첫화면으로 이동
            {
                location.href="MainForm.do";
            }
            else if(value == "1") // 로그인 버튼 클릭시 로그인 화면으로 이동
            {
                location.href="LoginForm.do";
            }
            else if(value == "2") // 회원가입 버튼 클릭시 회원가입 화면으로 이동
            {
                location.href="member/JoinForm.jsp";
            }
            else if(value == "3") // 로그아웃 버튼 클릭시 로그아웃 처리
            {
                location.href="MemberLogoutAction.do";
            }
            else if(value == "4") // 내정보 버튼 클릭시 회원정보 보여주는 화면으로 이동
            {
                location.href="MemberInfoAction.do";
            }
            else if(value == "5")
            {
                location.href="MemberListAction.do";
            }
            else if(value == "6")
            {
                location.href="BoardListAction.bo";
            }
            else if(value == "7")
            {
                location.href="selectAllproduct.pr";
            }
            else if(value == "8")
            {
                location.href="basketProduct.jsp";
            }
            
        }
    </script>
    
</head>
<body>
    <div class="container">
    <%
		if(session.getAttribute("sessionID") == null) // 로그인이 안되었을 때
		{ 
			
		}else{ // 로그인 했을 경우	
	%>
			<br><br>						
			<font size=5 color="skyblue"><%=session.getAttribute("sessionID") %></font>
			<font size=5>님 환영합니다.</font>
	<%	} %>
		<p>       
    </div>
</body>
</html>