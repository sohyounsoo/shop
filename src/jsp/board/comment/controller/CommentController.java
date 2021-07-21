package jsp.board.comment.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.common.controller.Action;
import jsp.common.controller.ActionForward;
import jsp.member.controller.MemberLoginAction;


public class CommentController extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private HashMap<String,Action> commandMap;
	
	

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
	private void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		// 넘어온 커맨드를 추출하는 과정
		String requestURI = request.getRequestURI();
		int cmdIdx = requestURI.lastIndexOf("/") + 1;
		String command = requestURI.substring(cmdIdx);

		// URI, command 확인
		 System.out.println("requestURI : "+requestURI);
		//System.out.println("Board cmd : "+command);
		
		ActionForward forward = null;
		Action action = null;
		
		//String form = "MainForm.jsp?contentPage=member/";
		
		try {					 
			// 각종 처리 액션
			if(command.equals("CommentWriteAction.co")) 
			{
				action = new CommentWriteAction();
				forward = action.execute(request, response);
			}
						
			else if(command.equals("CommentReplyAction.co")) 
			{
				action = new CommentReplyAction();
				forward = action.execute(request, response);
			}
			else if(command.equals("CommentDeleteAction.co")) 
			{
				action = new CommentDeleteAction();
				forward = action.execute(request, response);
			}
			else if(command.equals("CommentUpdateAction.co")) 
			{
				action = new CommentUpdateAction();
				forward = action.execute(request, response);
			}
			else if(command.equals("CommentUpdateFormAction.co")) 
			{
				action = new CommentUpdateFormAction();
				forward = action.execute(request, response);
			}
			else if(command.equals("CommentReplyFormAction.co")) 
			{
				action = new CommentReplyFormAction();
				forward = action.execute(request, response);
			}
			
			/*
			 * 화면이동 - isRedirext() 값에 따라 sendRedirect 또는 forward를 사용
			 * sendRedirect : 새로운 페이지에서는 request와 response객체가 새롭게 생성된다.
			 * forward : 현재 실행중인 페이지와 forwad에 의해 호출될 페이지는 request와 response 객체를 공유
			 */
			if(forward != null){
				if (forward.isRedirect()) {
					response.sendRedirect(forward.getNextPath());
				} else {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher(forward.getNextPath());
					dispatcher.forward(request, response);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	} // end doProcess	  	      	
}
