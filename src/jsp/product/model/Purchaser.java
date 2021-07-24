package jsp.product.model;
import java.sql.Date;
/**
 * This domain object represents a Product.
 * The data attributes are all package-private to allow access to them
 * in the {@link ProductService} class.
 */
public class Purchaser {
	String      orderNum;
	String      place;
	String      memID;
	String      name;
	String      address;
	String      tel;
	String      email;
	String      payType;
	String      payStatus;
	String		purchaseDate;
	//Date		purchaseDate;
	String		cardtype;
	String		cardnumber;
	String		samount;
	int			seq;
	int			tcount;
	int			amount;
		  /**
		   * This is the constructor.  I tis package-private to prevent
		   * misuse.  The {@link PurchaserService} method should be used to
		   * create a new Purchaser object.
		   */
		  public Purchaser() {}

		  Purchaser(String orderNum) {
			this(orderNum, "", "", "", "", "", "", "",0, 0, "", "");
		  }

		  /**
		   * This is the full constructor.
		   */
		  Purchaser(String orderNum, String place, String memID, String name,
				 String address,String tel,String email, String payType,int tcount,int amount,String payStatus,String purchaseDate) {
			this.orderNum = orderNum;
			this.place = place;
			this.memID = memID;
			this.name = name;
			this.address = address;
			this.tel = tel;
			this.email = email;
			this.payType = payType;
			this.tcount = tcount;
			this.amount = amount;
			this.payStatus = payStatus;
			this.purchaseDate = purchaseDate;
		  }

		  public void setOrderNum(String orderNum) {
			this.orderNum = orderNum;
		  }
		  public void setPlace(String place) {
			this.place = place;
		  }
		  public void setMemID(String memID) {
			this.memID = memID;
		  }
		  public void setName(String name) {
			this.name = name;
		  }
		  public void setAddress(String address) {
			this.address = address;
		  }
		  public void setTel(String tel) {
			this.tel = tel;
		  }
		  public void setEmail(String email) {
			this.email = email;
		  }
		  public void setPayType(String payType) {
			this.payType = payType;
		  }
		  public void setTcount(int tcount) {
			this.tcount = tcount;
		  }
		  public void setAmount(int amount) {
			this.amount = amount;
		  }
		  public void setSamount(String samount) {
			this.samount = samount;
		  }
		  public void setPayStatus(String payStatus) {
			this.payStatus = payStatus;
		  }
		  public void setPurchaseDate(String purchaseDate) {
			this.purchaseDate = purchaseDate;
		  }
		//public void setPurchaseDate(Date purchasedate) {
		//	this.purchasedate = purchasedate;
		//}
		  public void setCardType(String cardtype) {
				this.cardtype = cardtype;
			}
		  public void setCardNumber(String cardnumber) {
				this.cardnumber = cardnumber;
			}
		  public void setSeq(int seq) {
			this.seq = seq;
		  }

		  public String getOrderNum() {
			return orderNum;
		  }
		  public String getPlace() {
			return place;
		  }
		  public String getMemID() {
			return memID;
		  }
		  public String getName() {
			return name;
		  }
		  public String getAddress() {
			return address;
		  }
		  public String getTel() {
			return tel;
		  }
		  public String getEmail() {
			return email;
		  }
		  public String getPayType() {
			return payType;
		  }
		  public int getTcount() {
			return tcount;
		  }
		  public int getAmount() {
			return amount;
		  }
		  public String getSamount() {
			return samount;
		  }
		  public String getPayStatus() {
			return payStatus;
		  }
		  public String getPurchaseDate() {
			return purchaseDate;		
		  }
		  //public Date getPurchaseDate() {return purchasedate;}
		  public int getSeq() {
			return seq;
		  }
		public String getCardType() {return cardtype;}
		public String getCardNumber() {return cardnumber;}

}//
