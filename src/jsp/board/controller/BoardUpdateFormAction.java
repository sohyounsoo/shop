package jsp.board.controller;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import jsp.board.model.BoardBean;
import jsp.board.model.BoardDAO;
import jsp.board.model.BoardService;
import jsp.common.controller.Action;
import jsp.common.controller.ActionForward;
 
public class BoardUpdateFormAction implements Action
{	
	
    @Override
    public ActionForward execute(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ActionForward forward = new ActionForward();
        
        // 페이지 번호와 글 번호를 가져온다.
        String pageNum = request.getParameter("page");
        String num = request.getParameter("num");
        int boardNum = Integer.parseInt(num);
 
        BoardService bs = BoardService.getInstance();
        BoardBean board = bs.getDetail(boardNum);
        
        request.setAttribute("board", board);
        request.setAttribute("pageNum", pageNum);
        
        forward.setRedirect(false); // 단순한 조회이므로
        forward.setNextPath("BoardUpdateForm.bo");
        
        return forward;
    }
}
