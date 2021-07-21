package jsp.product.controller;


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
public class MainServlet extends HttpServlet{
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
		//String form = "MainForm.jsp?contentPage=product/";
		
		// 커맨드에 해당하는 액션을 실행한다.
		try {
			// 화면전환
			if(command.equals("ProductListForm.pr")) // 상품목록보기
			{
				forward=new ActionForward();
				forward.setRedirect(false);
				forward.setNextPath("selectAllProduct.jsp");
			}
			else if(command.equals("ProductDetailForm.pr")) // 상품자세히 보기
			{
				forward=new ActionForward();
				forward.setRedirect(false);
				forward.setNextPath("selectProduct.jsp");
			}									
			else if(command.equals("userError.pr")) // 에러
			{
				forward=new ActionForward();
				forward.setRedirect(false);
				forward.setNextPath("userError.jsp");
			}
			else if(command.equals("putOnebasketForm.pr")) // 장바구니로 이동
			{
				forward=new ActionForward();
				forward.setRedirect(false);
				forward.setNextPath("basketProduct.jsp");
			}
			else if(command.equals("selectmemberPurchaserForm.pr")) // 
			{
				forward=new ActionForward();
				forward.setRedirect(false);
				forward.setNextPath("orderProduct.jsp");
			}
			
			
			
			
			
									
			// 각종 처리 액션
			else if(command.equals("selectAllproduct.pr")) // 상품목록처리
			{
				action = new SelectAllProduct();
				forward = action.execute(request, response);
			}
			else if(command.equals("selectproduct.pr")) // 
			{
				action = new SelectProduct();
				forward = action.execute(request, response);
			}
			else if(command.equals("putOnebasket.pr")) 
			{
				action = new PutOneBasket();
				forward = action.execute(request, response);
			}
			else if(command.equals("selectmemberPurchaser.pr"))  //구매정보 등록하기전 회원정보 조회 처리
			{
				action = new SelectMember();
				forward = action.execute(request, response);
			}
			else if(command.equals("emptyAllbasket.pr")) //장바구니 전체비우기 처리 
			{
				action = new EmptyAllBasket();
				forward = action.execute(request, response);
			}
			else if(command.equals("emptyOnebasket.pr")) //장바구니 선택비우기 처리 
			{
				action = new EmptyOneBasket();
				forward = action.execute(request, response);
			}
			else if(command.equals("insertbasketPurchaser.pr")) // 구매정보 등록 처리
			{
				action = new InsertBasketPurchaser();
				forward = action.execute(request, response);
			}
			else if(command.equals("deletebasket.pr")) // 구매완료후 장바구니 삭제
			{
				action = new DeleteBasket();
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
