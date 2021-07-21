package jsp.product.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsp.common.controller.Action;
import jsp.common.controller.ActionForward;
import jsp.product.model.BasketCart;




public class EmptyAllBasket implements Action {
	
	/**
	*	장바구니 전체를 삭제한다.
	*	1.  basketProduct.jsp로 request를 받는다.
	*	3.  Session 객체에서 BasketCart객체의 레퍼런스을 얻어온다
	*   4.  BasketCart의 clear() 을 호출한다.
	*/
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) 
	throws Exception {
		
		ActionForward forward = new ActionForward();
		
		System.out.println("EmptyBasket execute called");

		HttpSession session = request.getSession(false);

		//BasketCart bc = null;
		if(session != null) {
			BasketCart bc = (BasketCart)session.getAttribute("BasketCart");
			bc.clear();
		}

		forward.setRedirect(false);
		forward.setNextPath("putOnebasketForm.pr");
		
		return forward;
	}

}