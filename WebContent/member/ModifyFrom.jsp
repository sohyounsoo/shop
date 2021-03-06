<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="jsp.member.model.MemberDAO" %>    
<%@ page import="jsp.member.model.MemberBean" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  

<%--
	// MemberInfoAction에서 넘긴 회원정보를 추출한다.
	MemberBean member=(MemberBean)request.getAttribute("memberInfo");
	
	아래에서 변경
--%>

<html>
<head>
 
    <title>회원정보 수정화면</title>
    
    <style type="text/css">
        table{
            margin-left:auto; 
            margin-right:auto;
            border:3px solid skyblue;
        }
        
        td{
            border:1px solid skyblue
        }
        
        #title{
            background-color:skyblue
        }
    </style>
    
    <script type="text/javascript">
    
        function init(){
            setComboValue("${member.mail2}");
        }
 
        function setComboValue(val) 
        {
            var selectMail = document.getElementById('mail2'); // select 아이디를 가져온다.
            for (i = 0, j = selectMail.length; i < j; i++)  // select 하단 option 수만큼 반복문 돌린다.
            {
                if (selectMail.options[i].value == val)  // 입력된값과 option의 value가 같은지 비교
                {
                    selectMail.options[i].selected = true; // 같은경우라면 체크되도록 한다.
                    break;
                }
            }
        }
        
        // 비밀번호 입력여부 체크
        function checkValue() {
            if(!document.userInfo.password.value){
                alert("비밀번호를 입력하세요.");
                return false;
            }
            if(document.userInfo.password.value != document.userInfo.password2.value ){
                alert("비밀번호가 일치하지 않습니다.");
                return false;
            }
        }
        
    </script>
    
</head>
<body onload="init()">
 
        <br><br>
        <b><font size="6" color="gray">회원정보 수정</font></b>
        <br><br><br>
        <!-- 회원정보를 가져와 member 변수에 담는다. -->
        <c:set var="member" value="${requestScope.memberInfo}"/>
        
        <!-- 입력한 값을 전송하기 위해 form 태그를 사용한다 -->
        <!-- 값(파라미터) 전송은 POST 방식 -->
        <form method="post" action="MemberModifyAction.do" 
                name="userInfo" onsubmit="return checkValue()">
                
            <table>
                <tr>
                    <td id="title">아이디</td>
                    <td id="title">${member.id}</td>
                </tr>
                <tr>
                    <td id="title">비밀번호</td>
                    <td>
                        <input type="password" name="password" maxlength="50" 
                            value="${member.password}">
                    </td>
                 </tr>
                <tr>
                    <td id="title">비밀번호 확인</td>
                    <td>
                        <input type="password" name="password2" maxlength="50" >
                    </td>                   
                </tr>
            </table>    
            <br><br>    
            <table>
 
                <tr>
                    <td id="title">이름</td>
                    <td>${member.name}</td>
                </tr>
                    
                <tr>
                    <td id="title">성별</td>
                    <td>${member.gender}</td>
                </tr>
                    
                <tr>
                    <td id="title">생일</td>
                    <td>
                        ${member.birthyy}년 
                        ${member.birthmm}월 
                        ${member.birthdd}일
                    </td>
                </tr>
                    
                <tr>
                    <td id="title">이메일</td>
                    <td>
                        <input type="text" name="mail1" maxlength="50" 
                            value="${member.mail1}">
                        @
                        <select name="mail2" id="mail2">
                            <option value="naver.com">naver.com</option>
                            <option value="gmail.com">gmail.com</option>
                            <option value="daum.net" >daum.net</option>
                            <option value="nate.com">nate.com</option>                        
                        </select>
                    </td>
                </tr>
                    
                <tr>
                    <td id="title">휴대전화</td>
                    <td>
                        <input type="text" name="phone" value="${member.phone}"/>
                    </td>
                </tr>
                <tr>
                    <td id="title">주소</td>
                    <td>
                        <input type="text" size="50" name="address"
                            value="${member.address}"/>
                    </td>
                </tr>
            </table>
            <br><br>
            <input type="button" value="취소" onclick="javascript:window.location='MainForm.do'">
            <input type="submit" value="수정"/>  
        </form>
        
</body>
</html>
