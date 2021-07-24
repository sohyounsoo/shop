<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=euc-kr" %> 
<html>
    <TITLE>수취인정보</TITLE>
    <meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
    <body>
        <TABLE BORDER=0 CELLSPACING=0 CELLPADDING=0 WIDTH=800>
            <TR HEIGHT='100'>		
                
            </TR>
            <TR VALIGN=\'top\'>
                
                <!-- START of main conten t-->
                <TD WIDTH='480' ALIGN='left'>
                    <DIV STYLE='margin-top: 0.1in; margin-left: 0.1in; margin-bottom: 0.1in; margin-right: 0.1in'>
                        <FORM ACTION='insertbasketPurchaser.pr' METHOD='POST'>
                            <jsp:useBean id="member" class="jsp.member.model.MemberBean" scope="request"  />
                            
                            <TABLE BORDER='0' CELLSPACING='0' CELLPADDING='5' WIDTH='100%' width=400>
                                <TR>
                                    <TD colspan=2><font size='1' color="red">수취인정보(상품을 받으실 주소와 연락처를 기재해주세요)</font></TD>
                                </TR>
                                <TR>
                                    <TD colspan=2 align=center><font size='2' color="#0000FF">
                                            <P><FONT color=#0000ff face=굴림 size=3><CENTER><STRONG>[수취인정보]</STRONG><CENTER></FONT></P> 
                                    </font></td>
                                </TR>
                                <TR>
                                    <TD ALIGN='right'><font size=1>수취인 정보구분:</font> </TD>
                                    <TD><font size=1>자택<INPUT TYPE='radio' NAME='place' value='1' selected checked>
                                         	직장<INPUT TYPE='radio' NAME='place' value='2' ></font>
                                    </TD>
                                </TR>
                                <TR>
                                    <TD ALIGN='right'><font size=1>회원ID:</font></TD>
                                    <TD><%= (String)session.getAttribute("sessionID") %></TD>
                                    <input type=hidden name=sessionID value=<%= (String)session.getAttribute("sessionID") %>>
                                    </TR>
                                <TR>
                                    <TD ALIGN='right'><font size=1>수취인명:</font></TD>
                                    <TD><font size=2><INPUT TYPE='text' NAME='name' SIZE='20' value='<jsp:getProperty name="member" property="name"/>'></font></TD>
                                </TR>                                                           
                                <TR>
                                    <TD ALIGN='right'><font size=1>전화번호:</font></TD>
                                    <TD><INPUT TYPE='text' NAME='tel' SIZE='20' value='<jsp:getProperty name="member" property="phone"/>'></TD>
                                </TR>
                                <TR>
                                    <TD ALIGN='right'><font size=1>이메일:</font></TD>
                                    <TD><INPUT TYPE='text' NAME='mail' SIZE='20' value='<jsp:getProperty name="member" property="mail1"/>@<jsp:getProperty name="member" property="mail2"/>'></TD>
                                </TR>
                                <TR>
                                    <TD ALIGN='right'><font size=1>주소:</font></TD>
                                    <TD><INPUT TYPE='text' NAME='address' SIZE='40' value='<jsp:getProperty name="member" property="address"/>'></TD>
                                </TR>
                                <TR>
                                    <TD ALIGN='right'><font size=1>결재방법:</font></TD>
                                    <TD><font size=1>온라인 입금<input type='radio' name='paytype' value='remit' checked >
                                         	카드결제<input type='radio' name='paytype' value='card'></font>
                                    </TD>
                                </TR>
                                <TR>
                                    <TD ALIGN='right'><font size=1>카드 회사:</font></TD>
                                    <TD><INPUT TYPE='text' NAME='cardtype' SIZE='40'></TD>
                                </TR>
                                <TR>
                                    <TD ALIGN='right'><font size=1>카드 번호:</font></TD>
                                    <TD><INPUT TYPE='text' NAME='cardnumber' SIZE='40'></TD>
                                </TR>
                                
                                <TR>
                                    <TD colspan=2 align=center>
                                        <!--<input type=hidden name="choice" value="do-Purchase">-->
                                        <input type="button" value="쇼핑계속" onClick="location='mainController?choice=selectAll-product'">&nbsp;
                                        <input type="submit" value="구매하기"> &nbsp; 
                                        <input type="button" value="장바구니 보기" onClick="location='basketProduct.jsp'">
                                    </TD>
                                </TR>
                            </TABLE>
                        </FORM>
                    </DIV>
                </TD>
                <!-- END of main conten t-->
         	<TD WIDTH='160'>
    			 <jsp:include page="MainForm.jsp" />
    		</TD>
            </TR>
           	
        </TABLE>
    </body>
</html>