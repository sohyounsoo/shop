<%@ page contentType="text/html;charset=euc-kr" %> 
<%@ page import="java.util.*" %>
<html>
    <TITLE>orderProduct</TITLE>
    <meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
    <body>
        <TABLE BORDER=0 CELLSPACING=0 CELLPADDING=0 WIDTH=800>
            
           
                              
                <!--body start -->
                <td>
                    <DIV STYLE='margin-top: 0.1in; margin-left: 0.1in; margin-bottom: 0.1in; margin-right: 0.1in'>
                        <P>
                        <P align=center>
                            <FONT color=#0000ff face=굴림 size=3>
                        <STRONG>[주문 정보]</STRONG></FONT></P> 
                        <CENTER>
                        <FORM ACTION='deletebasket.pr' METHOD='POST'>
                                <TABLE border=0 width=640 cellpadding=4 cellspacing=4 style="font-size:10pt">
                                <TR>
                                    <TH width=5% bgcolor=#9999FF> <FONT color=white face="굴림"> <NOBR>주문번호</NOBR></FONT></TH>
                                    <TH width=30% bgcolor=#9999FF> <FONT color=white face="굴림"> <NOBR>제품명</NOBR></FONT></TH>
                                    <TH width=10% bgcolor=#9999FF> <FONT color=white face="굴림"> <NOBR>주문수량</NOBR></FONT></TH>
                                    <TH width=15% bgcolor=#9999FF> <FONT color=white face="굴림"> <NOBR>판매가격(단위:원)</NOBR></FONT></TH>
                                </TR>
                                <% 
                                ArrayList collection = (ArrayList) request.getAttribute("collection");
                                //out.println("collection :"+ collection.size());
                                Iterator iter = collection.iterator();
                                //out.println("iter :"+ iter);
                                  jsp.product.model.TheOrder theOrder = null;
                                while(iter.hasNext()) {
                                    theOrder = (  jsp.product.model.TheOrder)iter.next();
                                %>                    
                                <TR align=center>                
                                    <TD><%=theOrder.getOrderNum()%></TD>
                                    <TD><%=theOrder.getProductName()%></TD>
                                    <TD><%=theOrder.getQuantity()%></TD>
                                    <TD><%=theOrder.getPrice_S()%></TD>
                                </tr>
                                <% } %>
                            </table>
                            
                            <br>
                            <P align=center>
                                <FONT color=#0000ff face=굴림 size=3>
                            <STRONG>고객 정보</STRONG></FONT></P> 
                            <TABLE border=0 width=640 cellpadding=4 cellspacing=4 style="font-size:10pt">
                                <TR>
                                    <TH width=30% bgcolor=#9999FF> <FONT color=white face="굴림"> <NOBR>주소</NOBR></FONT></TH>
                                    <TH width=15% bgcolor=#9999FF> <FONT color=white face="굴림"> <NOBR>EMAIL</NOBR></FONT></TH>
                                    <TH width=10% bgcolor=#9999FF> <FONT color=white face="굴림"> <NOBR>전화번호</NOBR></FONT></TH>
                                    <TH width=10% bgcolor=#9999FF> <FONT color=white face="굴림"> <NOBR>지불형태</NOBR></FONT></TH>
                                </TR>
                                <% 
                                ArrayList collection1 = (ArrayList) request.getAttribute("collection");
                                    jsp.product.model.TheOrder theOrder1 = null;
                                //for(int i=0; i < collection1.size(); i++ ) {
                                int listSize = collection1.size();
                                //if(  listSize > 1 ) {
                                    theOrder1 = ( jsp.product.model.TheOrder)collection1.get( listSize - 1);
                                %>    
                                <TR align=center>
                                    <TD><%=theOrder1.getAddress()%></TD>
                                    <TD><%=theOrder1.getEmail()%></TD>
                                    <TD><%=theOrder1.getTel()%></TD>
                                    <TD><%=theOrder1.getPaytype()%></TD>
                                </TR>
                                <% //} %>
                            </table>        
                            <p>
                            <input type='hidden' name='memid' value='<%= (String)session.getAttribute("MemberBean.id")%>'>                            
                                <br>
                                <center><h1> 주문 완료되었습니다. </h1></center>      
                                <br>
                                <input type="submit" value="구매완료">
                                <br>
                            </p>
                        </FORM>
                    </DIV>
                </td>
                <!--body end -->
            </TR>
            <TR>
                <TD WIDTH='160'>
                	 <jsp:include page="MainForm.jsp" />
                </TD>
               
            </TR>	
        </TABLE>
    </body>
</html>