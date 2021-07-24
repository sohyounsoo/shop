package jsp.product.controller;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.board.model.BoardBean;
import jsp.board.model.BoardService;
import jsp.common.controller.Action;
import jsp.common.controller.ActionForward;
import jsp.product.model.Product;
import jsp.product.model.ProductService;

public class SelectAllProduct implements Action {
	
	/**
	*	상품의 리스트를 조회한다.
	*   1.  상품보기를 선택하는 request를 받는다.
	*   2.  return  Collection 객체를 request scope에 저장한다.
	*/
	@Override
	public ActionForward execute(HttpServletRequest request,
	           HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		
		// 현재 페이지 번호 만들기
		int spage = 1;
		String page = request.getParameter("page");

		if(page != null && !page.equals("")) {
					spage = Integer.parseInt(page);
		}
				
		// 검색조건과 검색내용을 가져온다.
		String opt = request.getParameter("opt");
		String condition = request.getParameter("condition");
		
		// 검색조건과 내용을 Map에 담는다.
		HashMap<String, Object> listOpt = new HashMap<String, Object>();
		listOpt.put("opt", opt);
		listOpt.put("condition", condition);
		
		System.out.println("opt"+opt);
		System.out.println("condition"+condition);
		
		ProductService productservice = new ProductService();
		int listCount = productservice.getProductListCount(listOpt);// 글의 개수를 가져온다
		
		// 한 화면에 10개의 게시글을 보여지게함
		// 페이지 번호는 총 5개, 이후로는 [다음]으로 표시
				
		// 전체 페이지 수
		int maxPage = (int)(listCount/10.0 + 0.9); 

		// 만약 사용자가 주소창에서 페이지 번호를 maxPage 보다 높은 값을 입력시
		// maxPage에 해당하는 목록을 보여준다.
		if(spage > maxPage) spage = maxPage;
		listOpt.put("start", spage*10-9);
					
		ArrayList<Product> list = productservice.getBoardList(listOpt);
		
		//시작 페이지 번호
		int startPage = (int)(spage/5.0 + 0.9) * 5 - 4; // 최대 5페이지까지 한번에 표시
		//마지막 페이지 번호
		int endPage = startPage + 4;
		if(endPage > maxPage)	endPage = maxPage;
		
		// 4개 페이지번호 저장
		request.setAttribute("spage", spage);// 현재 페이지 번호 
		request.setAttribute("maxPage", maxPage);//한페이지에 최대 번호
		request.setAttribute("startPage", startPage);//시작페이지번호
		request.setAttribute("endPage", endPage);//마지막 페이지번호
		
		request.setAttribute("list", list);
		
		forward.setRedirect(false);
	    forward.setNextPath("ProductListForm.pr");

		return forward;
	}
}