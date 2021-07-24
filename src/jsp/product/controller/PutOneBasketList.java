package jsp.product.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsp.common.controller.Action;
import jsp.common.controller.ActionForward;
import jsp.product.model.Basket;
import jsp.product.model.BasketCart;
import jsp.product.model.Product;
import jsp.product.model.ProductService;

;

public class PutOneBasketList implements Action{
   
	@Override
    public ActionForward execute(HttpServletRequest request,HttpServletResponse response)
    throws Exception {
		       
   /**
     * 제품명을 parameter로 입력 받아 그 제품의 장바구니 정보를 가져 온다.
     * @param String productName
     * @return Basket basket
     */        
    	ActionForward forward = new ActionForward();
		
    	ProductService productservice = new ProductService();
        
    	String productId=request.getParameter("productId");
        Product product = (Product)productservice.selectProduct(productId);
        
        String quantity = request.getParameter("quantity");
        System.out.println("quantity : " + quantity);
        Basket basket = new Basket();
        basket.setCompany(product.getCompany());
        basket.setMallId(product.getMallId());
        //basketOrderServlet에 다시 세팅함
        basket.setOrderNum("1");
        basket.setPrice(product.getPrice2());
        basket.setProductId(product.getProductId());
        basket.setProductName(product.getProductName());
        basket.setQuantity(Integer.parseInt(quantity));
        //basket.setQtyPrice(basket.getQuantity() * basket.getPrice());
        
        request.setAttribute("basket", basket);
        
        forward.setRedirect(true);
		forward.setNextPath("putOneBasket.java");
        
        return forward;
    	}
	}
    