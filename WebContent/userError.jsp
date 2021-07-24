<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=euc-kr" %>
<html>
    <TITLE>userError</TITLE>
    <meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
    <body>
        <TABLE BORDER=0 CELLSPACING=0 CELLPADDING=0 WIDTH=800>
            
            
                <!-- START of main conten t-->
                <TD WIDTH='480' ALIGN='left'>
                    <DIV STYLE='margin-top: 0.1in; margin-left: 0.1in;
                    margin-bottom: 0.1in; margin-right: 0.1in'>
                        
                        <%-- Report any errors (if any) --%>
                        <%
                        // Retrieve the errorMsgs from the request-scope
                        List errorMsgs = (List) request.getAttribute("errorMsgs");
                        if ( (errorMsgs != null) && !errorMsgs.isEmpty() ) {
                        %>
                        <p>
                            <font color='red'>Please correct the following errors:
                                <ul>
                                    <%
                                    Iterator items = errorMsgs.iterator();
                                    while ( items.hasNext() ) {
                                        String message = (String) items.next();
                                    %>
                                    <li><%= message %></li>
                                    <%
                                    } // END of while loop over errorMsgs
                                    %>
                                </ul>
                            </font>
                        </p>
                        <%
                        } // END of if errorMsgs is not empty
                        %>                        
                        
                        
                    </DIV>
                </TD>
                <!-- END of main conten t-->
            </TR>
            <TR>
                <TD WIDTH='160'>
                </TD>
                
            </TR>	
        </TABLE>
    </body>
</html>  
