package jsp.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsp.common.controller.Action;
import jsp.common.controller.ActionForward;
import jsp.member.model.MemberBean;
import jsp.member.model.MemberDAO;
import jsp.member.model.MemberService;

/**
 * ȸ������ �����۾��� ó���ϴ� Action Ŭ����
 *
 */
public class MemberModifyAction implements Action
{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("euc-kr"); // ���ڵ�
		
		ActionForward forward = new ActionForward();
		
		MemberService dao = MemberService.getInstance();
		
		// ������ �������ִ� �α����� ID ������ �����´�
		HttpSession session = request.getSession();
		String id = session.getAttribute("sessionID").toString();
		
		// ������ ������ �ڹٺ� �����Ѵ�.
		MemberBean member = new MemberBean();
		member.setId(id);
		member.setPassword(request.getParameter("password"));
		member.setMail1(request.getParameter("mail1"));
		member.setMail2(request.getParameterValues("mail2")[0]); 
		member.setPhone(request.getParameter("phone"));
		member.setAddress(request.getParameter("address"));
		
		dao.updateMember(member);

		forward.setRedirect(true);
   		forward.setNextPath("Result.do");
		
   		// ȸ������ ���� ���� �޽����� ���ǿ� ��´�.
   		session.setAttribute("msg", "0");
   		
		return forward;
	}

}