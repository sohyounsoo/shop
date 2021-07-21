package jsp.product.model;

import java.text.NumberFormat;
import java.util.*;

/**
 * 이 도메인 객체는 상품 한개의 정보를 담는 Basket 객체를 여러개 담을 수 있는 객체이다.
 */
public class BasketCart {
    HashMap items = null;
    int numberOfItems = 0;
    int quantity = 0;
    private int totalPrice = 0;
    private NumberFormat nf;
    /**
     * 디폴트 생성자
     */
    public BasketCart() {
        nf = NumberFormat.getInstance(Locale.KOREA);
        items = new HashMap();
    }
    
    /**
     * BasketCart의 HashMap에 basket객체를 더해 준다.
     * ProductID를 키로 하여 존재 유무를 확인하고
     * 존재하면 quantity의 수를 증가 시킨다.
     * 존재하지 않으면 basket객체를 저장한다.
     *
     * @param String productID,Basket basket
     */
    public void add(String productID,Basket basket) {
        if(items.containsKey(productID)) {
            //존재하면 수량을 증가시킨다
            basket = (Basket) items.get(productID);
            basket.incrementQuantity();
        } else {
            //존재하지 않으면 basket객체를 저장한다.
            items.put(productID, basket);
        }
        numberOfItems++;
        //basket.setTotPrice(basket.getTotPrice() + basket.getPrice());
        
    }
    
    /**
     * BasketCart의 HashMap에 basket객체를 더해 준다.
     * ProductID를 키로 하여 존재를 유무를 확인하고
     * 존재 할때 quantity의 수를 parameter로 받아 증가 시킨다.
     *
     * @param String productID,Basket basket,int quantity
     */
    public void add(String productID,Basket basket,int quantity) {
        
        if(items.containsKey(productID)) {
            basket = (Basket) items.get(productID);
            basket.incrementQuantity(quantity);
            basket.setQtyPrice(basket.getQuantity() * basket.getPrice());
            setTotalPrice( getTotalPrice()  +  ( basket.getQuantity() * basket.getPrice() ) );
        } else {
            items.put(productID, basket);
            basket.setQtyPrice(quantity * basket.getPrice());
            setTotalPrice(getTotalPrice() + (quantity * basket.getPrice()) );
        }

        numberOfItems++;
        
        System.out.println("<<>>productID, quantity " + productID + " " + quantity + " " + basket.getQuantity());
    }
    
    
    /**
     * BasketCart의 HashMap에 basket객체를 삭제 해준다.
     * ProductID를 키로 하여 존재 유무를 확인하고
     * 존재 하면 HashMap에서 모두 삭제한다.
     *
     * @param String productID
     */
    public void removeAll(String productID) {
        if(items.containsKey(productID)) {
            Basket basket = (Basket) items.get(productID);
            
            totalPrice = 0;
            items.remove(productID);
            numberOfItems--;
        }
    }
    
    /**
     * BasketCart의 HashMap에 basket객체를 삭제 해준다.
     * ProductID를 키로 하여 존재 유무를 확인하고
     * 존재 할때 quantity의 수를 감소 시킨다.
     *
     * @param String productID
     */
    public void remove(String productID) {
        if(items.containsKey(productID)) {
            Basket basket = (Basket) items.get(productID);
            System.out.println("삭제전 :" + basket.getQuantity() + " " + getTotalPrice());
            
            basket.decrementQuantity();
            basket.setQtyPrice(basket.getQtyPrice() - basket.getPrice());
            setTotalPrice( getTotalPrice() - basket.getPrice() );

            System.out.println("삭제 후 : " + getTotalPrice());
            
            if(basket.getQuantity() <= 0)
                items.remove(productID);
            
            numberOfItems--;
        }
    }
    
    
    
    /**
     * HashMap의 모든 Values를 반환한다
     */
    public Collection getItems() {
        return items.values();
    }
    
    protected void finalize() {
        items.clear();
    }
    
    /**
     * HashMap의 값의 수를 리턴한다.
     */
    public int getNumberOfItems() {
        return numberOfItems;
    }
    
    /**
     * HashMap의 모든 내용을 삭제한다.
     */
    public void clear() {
        totalPrice = 0;
        items.clear();
        numberOfItems = 0;
    }
    
    /**
     * HashMap의 ProductID key값을 반환한다.
     * @return String[] keySet
     */
    public String[] keySet() {
        Collection col = items.keySet();
        int SIZE = col.size();
        String[] keySet = new String[SIZE];
        Iterator iter = col.iterator();
        
        int i = 0;
        
        while(iter.hasNext()) {
            keySet[i] = (String)iter.next();
            i++;
        }
        
        return keySet;
    }
    
    public int getTotalPrice() {
        return totalPrice;
    }
    
    public String getTotalPrice_S() {
        return nf.format(totalPrice);
    }
    
    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
