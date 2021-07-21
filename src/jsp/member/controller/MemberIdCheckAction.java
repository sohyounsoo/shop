package jsp.member.controller;
 
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.common.controller.Action;
import jsp.common.controller.ActionForward;
import jsp.member.model.MemberDAO;
import jsp.member.model.MemberService;

 
public class MemberIdCheckAction implements Action
{
    @Override
    public ActionForward execute(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
 
        String id = request.getParameter("id");
        MemberService dao = MemberService.getInstance();
        
        boolean result = dao.duplicateIdCheck(id);
        
        response.setContentType("text/html;charset=euc-kr");
        PrintWriter out = response.getWriter();
 
        if(result)    out.println("0"); // 아이디 중복
        else        out.println("1");
        
        out.close();
        
        return null;
    }
}
