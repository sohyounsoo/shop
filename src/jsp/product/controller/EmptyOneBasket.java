package jsp.product.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsp.common.controller.Action;
import jsp.common.controller.ActionForward;
import jsp.product.model.BasketCart;


public class EmptyOneBasket implements Action {
	
	/**
	*	장바구니의 상품을 삭제한다.
	*	1.  basketProduct.jsp로 request를 받는다.
	*   2.  request객체에서 "productName" 인 값을 가져온다.
	*	3.  Session 객체에서 BasketCart객체의 레퍼런스을 얻어온다
	*   4.  상품의 이름을 argument 로 주어 BasketCart의 removeAll(productName)을 호출한다.
	*/
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) 
	throws Exception {
		
		ActionForward forward = new ActionForward();
		
		System.out.println("DeleteBasket execute called");

		HttpSession session = request.getSession(false);

		//BasketCart bc = null;
		if(session != null) {
			BasketCart bc = (BasketCart)session.getAttribute("BasketCart");
                        String productId=request.getParameter("productId");
			bc.remove(productId);
		}
		
		forward.setRedirect(false);
		forward.setNextPath("putOnebasketForm.pr");
		
		return forward;
	}

}