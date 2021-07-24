<%@ page contentType="text/html;charset=euc-kr" %> 
<%@ page language="java" import="jsp.product.model.Product" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<html>
<TITLE>selectProduct</TITLE>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">

<body>
<TABLE BORDER=0 CELLSPACING=0 CELLPADDING=0 WIDTH=800>

<TD WIDTH='480' ALIGN='left'>
    
    <P><FONT color=#0000ff face=굴림 size=3><CENTER><STRONG>[상품 상세정보]</STRONG><CENTER></FONT></P> 
    <jsp:useBean id='product' class='jsp.product.model.Product' scope='request' />
    <DIV STYLE='margin-top: 0.1in; margin-left: 0.1in; margin-bottom: 0.1in; margin-right: 0.1in'>
    <table width=450>
    <tr>
        <td rowspan=5 align=center><img  border="0" src="images/<jsp:getProperty name='product' property='photoDir'/>" width="250" height=200"></td>
        <td width=210 colspan=2 align=center><font color="red"><jsp:getProperty name='product' property='productName'/></font></td>
    </tr>
    <tr>
        <td><font size=3>제조원/수입원</font></td>
        <td><font size=3><jsp:getProperty name='product' property='company'/></font></td>
    </tr>
    <tr>
        <td><font size=3>일반가격</font></td>
        <td><font size=3><strike><jsp:getProperty name='product' property='price1S'/>원</strike></font></td>
    </tr>
    <tr>
        <td><font size=2>판매가격</font></td>
        <td><font color='red' face=arial>⇒&nbsp;<jsp:getProperty name='product' property='price2S'/>원</font></td>
    </tr>
    <tr>
        <td><font size=2>할부여부</font></td>
        <td>
            <%
            if(product.getInstall().trim().equals("1")) {
                out.println("가능");
            } else {
                out.println("불가능");
            }
            %>
        </td>
    </tr>
    <tr>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td colspan=3 align=center><div align=center><center><table width=70>
            <tr>
                <td><font size=3>제품설명</font></td>
            </tr>
            <tr>
                <td><textarea rows=4 name=detail cols=40><jsp:getProperty name='product' property='detail'/></textarea></td>
            </tr>
            <tr>
                <td align=center><font size=4><br>
                	<c:if test="${sessionScope.sessionID != null}">
                    <font color="red">구매하시려면 구매개수를 입력하시고 <br>
                    	장바구니 버튼을 눌러주세요</font><br><br>
                    </c:if>
                    <c:if test="${sessionScope.sessionID == null}">
						 <font color="red">구매하시려면 로그인을 해주세요 <br></font>
					</c:if>	
                    <form method="post" action="putOnebasket.pr">
                        구매갯수 &nbsp;&nbsp;<input type='text' name='quantity' size=2 value='1'>                                        
                        <input type='hidden' name='productId' value='<jsp:getProperty name='product' property='productId'/>'>
                        <br><br>
                        <c:if test="${sessionScope.sessionID != null}">
                        	<input type=submit value="장바구니넣기" onclick="logincheck()" >
                        </c:if>
                        &nbsp;<input type="button" value="상품목록보기" onclick="history.go(-1)">                     
                    </form>								<!--selectAllproduct.pr?page=${pageNum}}-->
                </td>
            </font></td>
        </tr>
    </table>
    </center></div></td>
</tr>
</table>		
<br>

</DIV>
</TD>
<!-- END of main conten t-->
</TR>
<TR>
    <TD WIDTH='160'>
    	 <jsp:include page="MainForm.jsp" />
    </TD>
  
</TR>	
</TABLE>
</body>
</html>