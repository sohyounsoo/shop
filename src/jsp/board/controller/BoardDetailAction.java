package jsp.board.controller;



import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.board.comment.model.CommentBean;
import jsp.board.comment.model.CommentDAO;
import jsp.board.model.BoardBean;
import jsp.board.model.BoardDAO;
import jsp.board.model.BoardService;
import jsp.common.controller.Action;
import jsp.common.controller.ActionForward;

public class BoardDetailAction implements Action
{
	BoardService bs;
	
	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		
		// 파라미터로 넘어온 글번호를 가져온다.
		String num = request.getParameter("num");
		System.out.println("글번호:"+num);
		int boardNum = Integer.parseInt(num);
		
		//페이저번호를 가져온다.
		String pageNum = request.getParameter("page");
		
		BoardService bs = BoardService.getInstance();
		BoardBean board = bs.getDetail(boardNum); // 글 자세히 보기
		boolean result = bs.updateCount(boardNum); //조회수증가		
		
		CommentDAO commentDAO = CommentDAO.getInstance();
		ArrayList<CommentBean> commentList = commentDAO.getCommentList(boardNum);
		
		// 댓글이 1개라도 있다면 request에 commentList를 세팅한다.
		if(commentList.size() > 0) {
			request.setAttribute("commentList", commentList);
		}
		
		request.setAttribute("board", board);
		request.setAttribute("pageNum", pageNum);
			
			
		if(result){
			forward.setRedirect(false); // 단순한 조회이므로
			forward.setNextPath("BoardDetailForm.bo");
		}
		
		return forward;
	}
}
