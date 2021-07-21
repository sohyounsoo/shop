
package jsp.product.model;

import java.util.Collection;
/**
 * 이 도메인 객체는 Controller의 요청을 받아서 DAO에게 넘겨주는 Facade 역할을 한다.
 * 
 */
public class BasketPurchaserService {
    private BasketPurchaserDAO basketPurchaserDao;
   /**
     * 디폴트 생성자
     *
     */    
    public BasketPurchaserService() {
        basketPurchaserDao = new BasketPurchaserDAO();
    }
        
    public void insertBasketPurchaser(Basket basket,Purchaser purchaser,String MEMBERID) {
        basketPurchaserDao.insertBasketPurchaser(basket, purchaser,MEMBERID);
    }
    
    public void deleteBasket(String MEMBERID) {
        basketPurchaserDao.deleteBasket(MEMBERID);
    }

    public Collection listBasketPurchaser(String memID) {
        Collection coll = basketPurchaserDao.listBasketPurchaser(memID);        
        return coll;
    }
      
    public int OrderMaxNo() {
        int maxno = basketPurchaserDao.OrderMaxNo();
        return maxno;
    }    
    
}
