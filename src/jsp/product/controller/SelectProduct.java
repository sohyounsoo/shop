package jsp.product.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.common.controller.Action;
import jsp.common.controller.ActionForward;
import jsp.product.model.Product;
import jsp.product.model.ProductService;


public class SelectProduct implements Action {
	
	/**
	*	구매한 상품의 상세정보를 조회한다.
	*	1.  listProduct.jsp로 request를 받는다.
	*   2.  request객체에서 "keyword" 인 값을 가져온다.
	*	3.  ProductService의 selectProduct(keyword) 를 호출한다.
	*   4.  return  Collection 객체를 request scope에 저장한다.
	*/
	@Override
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) 
	throws Exception {
		
		ActionForward forward = new ActionForward();
		
		System.out.println("SearchKeywordProduct execute called");
		ProductService productservice = new ProductService();	

        String productId=request.getParameter("productId");
		System.out.println("Select Product name " + productId);
        Product product = (Product)productservice.selectProduct(productId);
        
        request.setAttribute("product", product);
        forward.setRedirect(false);
        forward.setNextPath("ProductDetailForm.pr");

		return forward;
	}
}