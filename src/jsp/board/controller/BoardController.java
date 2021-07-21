 package jsp.board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.common.controller.Action;
import jsp.common.controller.ActionForward;

/**
 * 게시판관련 Controller
 *
 */
public class BoardController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	/**
	 * GET 방식일 경우 doGet()
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
			doProcess(request,response);
	}  	
		
	/**
	 * POST 방식일 경우 doPost()
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
			doProcess(request,response);
	}   	  	      	   
		
	/**
	 * 명령어에 따른 해당 Action을 지정해 준다.
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void doProcess(HttpServletRequest request, HttpServletResponse response) 
		 	throws ServletException, IOException {
		
		// 넘어온 커맨드를 추출하는 과정
		String requestURI = request.getRequestURI();
		int cmdIdx = requestURI.lastIndexOf("/")+1;
		
		String command = requestURI.substring(cmdIdx);
		
		// URI, command 확인
		System.out.println("requestURI : "+requestURI);
		System.out.println("command : "+command);
		
		ActionForward forward = null;
		Action action = null;
		
		// 보여줄 화면 URL
		String form = "MainForm.jsp?contentPage=board/";
		
		// 커맨드에 해당하는 액션을 실행한다.
		try {
			// 화면전환
			if(command.equals("BoardWriteForm.bo")) // 글쓰기 화면 이동
			{
				forward=new ActionForward();
				forward.setRedirect(false);
				forward.setNextPath(form+"BoardWriteForm.jsp");
			}
			else if(command.equals("BoardListForm.bo"))	// 게시판목록 화면 이동
			{
				forward=new ActionForward();
				forward.setRedirect(false);
				forward.setNextPath(form+"BoardListForm.jsp");
			}
			else if(command.equals("BoardDetailForm.bo"))	// 게시판 글 상세보기 화면 이동
			{
				System.out.println(">>> success");
				forward=new ActionForward();
				forward.setRedirect(false);
				forward.setNextPath(form+"BoardDetailForm.jsp"); //BoardUpdateAction.bo
			}
			else if(command.equals("BoardReplyForm.bo"))	// 답변 글 작성 화면 이동
			{
				forward=new ActionForward();
				forward.setRedirect(false);
				forward.setNextPath(form+"BoardReplyForm.jsp"); 
			}			
			else if(command.equals("BoardUpdateForm.bo"))	//
			{
				forward=new ActionForward();
				forward.setRedirect(false);
				forward.setNextPath(form+"BoardUpdateForm.jsp"); 
			}
	
			
			
			// 각종 처리 액션
			else if(command.equals("BoardWriteAction.bo")) // 게시판글 쓰기 처리
			{
				action = new BoardWriteAction();
				forward = action.execute(request, response);
			}
			else if(command.equals("BoardListAction.bo")) // 게시판 목록 불러오기 처리
			{
				action = new BoardListAction();
				forward = action.execute(request, response);
			}
			else if(command.equals("BoardDetailAction.bo")) // 게시판 글 상세보기 처리
			{
				action = new BoardDetailAction();
				forward = action.execute(request, response);
			}
			else if(command.equals("FileDownloadAction.bo")) // 파일첨부 처리
			{
				action = new FileDownloadAction();
				forward = action.execute(request, response);
			}
			else if(command.equals("BoardReplyFormAction.bo")) // 답변 글 작성 화면으로 이동하는 Action이다
			{
				action = new BoardReplyFormAction();
				forward = action.execute(request, response);
			}
			else if(command.equals("BoardReplyAction.bo")) // 답변 글을 작성하는 Action이다
			{
				action = new BoardReplyAction();
				forward = action.execute(request, response);
			}
			else if(command.equals("BoardDeleteAction.bo")) //
			{
				action = new BoardDeleteAction();
				forward = action.execute(request, response);
			}
			else if(command.equals("BoardUpdateFormAction.bo")) // 
			{
				action = new BoardUpdateFormAction();
				forward = action.execute(request, response);
			}
			else if(command.equals("BoardUpdateAction.bo")) // 
			{
				action = new BoardUpdateAction();
				forward = action.execute(request, response);
			}
			
			// 화면이동 - isRedirext() 값에 따라 sendRedirect 또는 forward를 사용
			// sendRedirect : 새로운 페이지에서는 request와 response객체가 새롭게 생성된다.
			// forward : 현재 실행중인 페이지와 forwad에 의해 호출될 페이지는 request와 response 객체를 공유
			if(forward != null){
				if (forward.isRedirect()) {
					response.sendRedirect(forward.getNextPath());
				} else {
					RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getNextPath());
					dispatcher.forward(request, response);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	} // end doProcess
}
