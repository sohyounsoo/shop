package jsp.board.controller;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import jsp.board.model.BoardBean;
import jsp.board.model.BoardDAO;
import jsp.board.model.BoardService;
import jsp.common.controller.Action;
import jsp.common.controller.ActionForward;
 
public class BoardReplyAction implements Action//답글 작성을 처리하는 action
{	
	BoardService bs;
	
    @Override
    public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception {
        
        request.setCharacterEncoding("euc-kr");
        ActionForward forward = new ActionForward();
        
        BoardService bs = BoardService.getInstance();
        BoardBean border = new BoardBean();
               
        // 답글 작성 후 원래 페이지로 돌아가기 위해 페이지 번호가 필요하다.
        String pageNum = request.getParameter("page");
        
        // 파리미터 값을 가져온다.
        int num = Integer.parseInt(request.getParameter("board_num"));
        String id = request.getParameter("board_id");
        String subject = request.getParameter("board_subject");
        String content = request.getParameter("board_content");
        int ref = Integer.parseInt(request.getParameter("board_re_ref"));
        
        //bean에답글저장
        border.setBoard_num(bs.getSeq());
        border.setBoard_id(id);
        border.setBoard_subject(subject);
        border.setBoard_content(content);
        border.setBoard_re_ref(ref);
        border.setBoard_parent(num); //부모글의 글번호를 저장
                
        boolean result = bs.boardInsert(border);
        
        if(result){
            forward.setRedirect(false); 
            // 원래있던 페이지로 돌아가기 위해 페이지번호를 전달한다.
            forward.setNextPath("BoardListAction.bo?page="+pageNum); 
        }
        return forward;
    }
}