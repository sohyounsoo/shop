package jsp.product.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsp.common.controller.Action;
import jsp.common.controller.ActionForward;
import jsp.product.model.BasketPurchaserService;


public class DeleteBasket implements Action {
   
    /**
     *	장바구니에 상품을 담는다
     *	1.  detailProduct.jsp로 request를 받는다.
     *	2.  Session 객체에서 "Member.id" 에 해당하는 값이 가져와 check한다.
     *     즉 로그인여부를 체크하고 난후에 장바구니에 상품을 담아야 한다.
     *   3. 로그인 하지 않은 상태이면 "로그인을 먼저하세요!! "라는 메세지를 보여주고
     *      "userError" 를 return 한다.
     *	4.  Session 객체에서 BasketCart객체의 레퍼런스을 얻어온다
     *   5.  request객체에서 "productName" 인 값을 가져온다.
     *	6.  productName을 parameter로 입력 받아 그 제품의 상품정보를 가져오는 listProduct(request, response, productName) 만든다.
     *       ProductDAO의 listProduct(productName)를 호출한다.
     *		결과값을 Basket객체에 저장한다.
     *   7.  상품의 이름과 Basket객체를 argument 로 주어 BasketCart의 add(productName, basket)을 호출한다.
     */
    public ActionForward execute(HttpServletRequest request,HttpServletResponse response)
    throws Exception {
    	
    	ActionForward forward = new ActionForward();
    	
        System.out.println("DeleteBasket execute called");
        List errorMsgs = new ArrayList();
        // Store this set in the request scope, in case we need to
        // send the ErrorPage view.
        request.setAttribute("errorMsgs", errorMsgs);
        
        HttpSession session = request.getSession(false);
        String memID = null;
        if(session != null) {
            memID =  (String)session.getAttribute("sessionID");
            System.out.println("memID : " + memID);
            if( memID == null  || memID.equals("") ) {
                errorMsgs.add("로그인을 먼저하세요!!");
                //System.out.println("login ERROR");
            }
        }
        
        //쇼핑카트 clear
        //BasketCart basketCart = (BasketCart)session.getAttribute("BasketCart");
        //basketCart.clear();
        
        BasketPurchaserService basketPurchaserService = new BasketPurchaserService();
        
        basketPurchaserService.deleteBasket(memID);
        
        forward.setRedirect(true);
        forward.setNextPath("MainForm.do");
        
        
        return forward;
    }
    
    
}