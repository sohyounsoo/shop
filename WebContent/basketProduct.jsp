<%@ page contentType="text/html;charset=euc-kr" %> 
<%@ page import="java.util.*,java.text.NumberFormat,java.util.Locale"%>
<%@ page import="jsp.product.model.*" %>
<%! static int basketSize; %>
<html>
    <TITLE>장바구니</TITLE>
    <meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
    <body>
        <TABLE BORDER=0 CELLSPACING=0 CELLPADDING=0 WIDTH=800>
            <TR HEIGHT='100'>		
                
            </TR>
            <TR VALIGN=\'top\'>
              
                <!-- START of main conten t-->
                <TD WIDTH='480' ALIGN='left'>
                    <DIV STYLE='margin-top: 0.1in; margin-left: 0.1in; margin-bottom: 0.1in; margin-right: 0.1in'>
                        
                        <table width=600 border='0'>
                            <tr>
                                <td width=460 align=center colspan=2>
                                <FONT color=#0000ff face=굴림 size=3><CENTER><STRONG>[장바구니 내용]</font></td>
                                <td width=100 align=center></td>
                                <td width=40 align=center></td>
                            </tr>
                            <tr>
                                <td width=200 align=center><font size=2 color="#29f0f0">상품명</font></td>
                                <td width=80 align=center><font size=2 color="#29f0f0">수량</font></td>
                                <td width=120 align=center><font size=2 color="#29f0f0">판매가</font></td>
                                <td width=140 align=center><font size=2 color="#29f0f0">합계(단위:원)</font></td>
                                <td width=60 align=center></td>
                            </tr>                            
                            <%
                            BasketCart basketCart =  (BasketCart)session.getAttribute("BasketCart");
                            int totalPrice = 0;
                            if(basketCart != null) {
                                Collection collection = basketCart.getItems();
                                //out.println("basketcart size()" + collection.size());
                                basketSize = collection.size();
                                Iterator iterator = collection.iterator();
                                while( iterator.hasNext() ) {
                                    Basket basket = (Basket)iterator.next();
                                    totalPrice = totalPrice + basket.getQtyPrice();
                            %>
                            <tr>
                                <td  align=center><font size=2 ><a href='mainController?choice=select-product&productId=<%=basket.getProductId()%>'><%=basket.getProductName()%></a></font></td>
                                <td  align=center><font size=2 ><%=basket.getQuantity()%></font></td>
                                <td  align=right ><font size=2 ><%=basket.getPrice_S()%></font></td>
                                <td  align=right ><font size=2 ><%=basket.getQtyPrice_S()%></font></td>
                                <td align=right><font size=2 ><a href='emptyOnebasket.pr?productId=<%=basket.getProductId()%>'>삭제</a></font></td>
                            </tr>                                
                            <%
                                }//while
                            %>
                            
                            <tr>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td  align=center><font size=2 color="#29f0f0">총 금액</font></td>
                                <td  align=right><font size=2 color="#29f0f0"><%=NumberFormat.getInstance(Locale.KOREA).format(totalPrice)%></font></td>
                                <td align=center><font size=2 ></td>
                            </tr>
                            <%
                              }//(basketCart != null)
                            %>
                            <tr>
                                <td>&nbsp;</td>
                            </tr>
                            <tr>
                                <td align=center colspan=4>
                                    <Form>
                                        <input type=button value="  쇼핑계속 " onclick="location='selectAllproduct.pr'">&nbsp; 
                                        <% //out.println("basketcart size()" + basketSize);
                                           if (basketSize >= 1 ) { %>
                                        <input type=button value="  구   매  " onclick="location='selectmemberPurchaser.pr'">&nbsp; 
                                        <input type=button value="장바구니비우기 " onclick="location='emptyAllbasket.pr'">&nbsp;
                                        <input type="hidden" name="token" value="<%=request.getAttribute("token")%>">
                                        <% } %>
                                    </Form>
                                </td>
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