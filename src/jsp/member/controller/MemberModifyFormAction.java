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
 *  회원정보 수정화면에 현재 회원정보를 출력하는 Action 클래스
 */
public class MemberModifyFormAction implements Action
{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		
		// 세션이 가지고있는 로그인한 ID 정보를 가져온다
		HttpSession session = request.getSession();
		String id = session.getAttribute("sessionID").toString();
		
		// 수정할 회원정보를 가져온다.
		MemberService dao = MemberService.getInstance();
		MemberBean member = dao.getUserInfo(id);
		
		// ModifyFrom.jsp에 회원정보를 전달하기 위해 request에 MemberBean을 세팅한다.
		request.setAttribute("memberInfo", member);
		
		forward.setRedirect(false);
		forward.setNextPath("ModifyFrom.do");
		
		return forward;
	}
}
