package jsp.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.board.model.BoardBean;
import jsp.board.model.BoardDAO;
import jsp.board.model.BoardService;
import jsp.common.controller.Action;
import jsp.common.controller.ActionForward;

public class BoardReplyFormAction implements Action
{	
	BoardService bs;
	
	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		
		BoardService bs = BoardService.getInstance();
		int num = Integer.parseInt(request.getParameter("num"));
		// 답글 작성 후 원래 페이지로 돌아가기 위해 페이지 번호가 필요하다.
		String pageNum = request.getParameter("page");
		
		BoardBean board = bs.getDetail(num);
		request.setAttribute("board", board); // 부모 글정보 페이지번호를 request에 세팅한다
		request.setAttribute("pageNum", pageNum);
	
		
		forward.setRedirect(false); // 단순한 조회이므로
		forward.setNextPath("BoardReplyForm.bo");
		
		return forward;
	}
}
