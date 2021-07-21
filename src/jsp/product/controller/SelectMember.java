package jsp.product.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsp.common.controller.Action;
import jsp.common.controller.ActionForward;
import jsp.member.model.MemberBean;
import jsp.member.model.MemberService;




public class SelectMember implements Action {

  	/**
	*	회원(Member)정보를 조회한다(주문시 변경사항).
	*	1.  로그인상태에서 Session객체로 부터 session.getAttribute("Member.id")  로 memId 를 읽어온다.
	*	2.  MemberService의 select()를 호출한다. memId를 argument 로 전달한다. 
	*		select()의 return value로 Member 객체를 받는다.
	*  3.  request Scope에 Member객체를 저장한다.
	*	
	*/
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
			request.setAttribute("member", member);
			
			forward.setRedirect(false);
			forward.setNextPath("purchaseProduct.jsp");
			
		return forward;
	}//execute
}