package jsp.product.model;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * This domain object represents a Basket.
 * The data attributes are all package-private to allow access to them
 * in the {@link PurchaseService} class.
 */
public class TheOrder {
    String              orderNum;
    String		mallId;
    String		productId;
    String		productName;
    String		memId;
    int			quantity;
    int			price;
    String              deliveryStatus;
    String		name;
    String		address;
    String		tel;
    
    //theOrder 테이블의 칼럼에 있는 내용은 아니지만
    //주문정보(orderProduct.jsp)에서 사용되어져서  paytype,email 이 추가된것임.
    String		paytype;
    String		email;
   
    /**
     * This is the constructor.  I tis package-private to prevent
     * misuse.  The {@link PurchaserService} method should be used to
     * create a new Basket object.
     */
    
    TheOrder(){       
    };
    /**
     * This is the full constructor.
     */
    TheOrder(String orderNum, String mallId, String productName,int quantity,int price,String memId,String name,String address,String tel) {
        this.orderNum = orderNum;
        this.mallId = mallId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.memId = memId;
        this.name = name;
        this.address = address;
        this.tel = tel;
    }
    /**
     * 주문번호
     * @param String orderNum
     */
    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }
    /**
     * 몰아이디
     * @param String mallId
     */
    public void setMallId(String mallId) {
        this.mallId = mallId;
    }
    /**
     * 상품명
     * @param String productName
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }
    /**
     * 상품명
     * @param String productName
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }
    /**
     * 주문수량
     * @param int quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    /**
     * 주문물품가격
     * @param int price
     */
    public void setPrice(int price) {
        this.price = price;
    }
    /**
     * 주문자아이디
     * @param String memId
     */
    public void setMemId(String memId) {
        this.memId = memId;
    }
    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }
    
    /**
     * 주문자이름
     * @param String name
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * 주문자주소
     * @param String address
     */
    public void setAddress(String address) {
        this.address = address;
    }
    /**
     * 주문자연락처
     * @param String tel
     */
    public void setTel(String tel) {
        this.tel = tel;
    }
    
    
    /**
     * 결재타입(온라인입금/카드)
     * @param String paytype
     */
    public void setPaytype(String paytype) {
        this.paytype = paytype;
    }
    /**
     * 이메일주소
     * @param String email
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    
    
    public String getOrderNum() {return orderNum;}
    public String getMallId() {return mallId;}
    public String getProductId() {return productId;}
    public String getProductName() {return productName;}
    public int getQuantity() {return quantity;}
    public int getPrice() {return price;}
    public String getPrice_S() {
        return NumberFormat.getInstance(Locale.KOREA).format(getPrice());
    }
    public String getMemId() {return memId;}
    public String getDeliveryStatus() {return deliveryStatus;}
    public String getName() {return name;}
    public String getAddress() {return address;}
    public String getTel() {return tel;}  
    public String getPaytype() { return paytype; }
    public String getEmail() { return email; }
    
    
}//
