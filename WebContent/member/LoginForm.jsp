<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 

<html>
<head>
	<%
		// ���ڵ� ó��
		request.setCharacterEncoding("euc-kr"); 
	%>
	<title>�α��� ȭ��</title>

	<style type="text/css">
		table{
			padding: 60px 0px;
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

		function checkValue()
		{
			inputForm = eval("document.loginInfo");
			if(!inputForm.id.value)
			{
				alert("���̵� �Է��ϼ���");	
				inputForm.id.focus();
				return false;
			}
			if(!inputForm.password.value)
			{
				alert("��й�ȣ�� �Է��ϼ���");	
				inputForm.password.focus();
				return false;
			}
		}
	
		// ��� ��ư Ŭ���� ùȭ������ �̵�
		function goFirstForm() {
			location.href="MainForm.do";
		}
	</script>

</head>
<body>
	<div id="wrap">
	
		<br><br>
		<b><font size="6" color="gray">�α���</font></b>
		<br><br><br>
	
		<form name="loginInfo" method="post" action="MemberLoginAction.do" 
				onsubmit="return checkValue()">

			<table>
				<tr>
					<td bgcolor="skyblue">���̵�</td>
					<td><input type="text" name="id" maxlength="50"></td>
				</tr>
				<tr>
					<td bgcolor="skyblue">��й�ȣ</td>
					<td><input type="password" name="password" maxlength="50"></td>
				</tr>
			</table>
			<br>
			<input type="submit" value="�α���"/>
			<input type="button" value="���" onclick="goFirstForm()">
		</form>
		
		<%-- 
			// ���̵�, ��й�ȣ�� Ʋ����� ȭ�鿡 �޽��� ǥ��
			String loginMsg = (String)request.getAttribute("fail");
				
			if(loginMsg!=null && loginMsg.equals("0")) 
			{
				out.println("<br>");
				out.println("<font color='red' size='5'>��й�ȣ�� Ȯ���� �ּ���.</font>");
			}
			else if(loginMsg!=null && loginMsg.equals("-1"))
			{	
				out.println("<br>");
				out.println("<font color='red' size='5'>���̵� Ȯ���� �ּ���.</font>");
			}
			�Ʒ����� ������ 
		--%>
		
		<c:set var="failMessage" value="${requestScope.fail}"/>
		<c:if test="${failMessage!=null}">
		<c:choose>
			<c:when test="${failMessage=='0'}">
				<br><font color='red' size='3'>��й�ȣ�� Ȯ���� �ּ���</font>
			</c:when>
			<c:otherwise>
				<br><font color='red' size='3'>���̵� Ȯ���� �ּ���</font>
			</c:otherwise>
		</c:choose>
		</c:if>
		
		
		
	</div>	
</body>
</html>