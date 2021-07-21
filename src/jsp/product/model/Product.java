package jsp.product.model;

/**
 * This domain object represents a Product.
 * The data attributes are all package-private to allow access to them
 * in the {@link ProductService} class.
 */
import java.sql.Date;
import java.text.NumberFormat;
import java.util.Locale;
public class Product {
    
    String productId;
    String mallId;
    String productName;
    String company;
    int price1;
    String price1S;
    int price2;
    String price2S;
    String install;
    String keyword;
    String  detail;
    //String productDate;
    Date productDate;
    String photoDir;
    int seq;
    
    private NumberFormat nf;
    /**
     * This is the constructor.  I tis package-private to prevent
     * misuse.  The {@link ProductService} method should be used to
     * create a new Product object.
     */
    public Product() {
            nf = NumberFormat.getInstance(Locale.KOREA);              
    }
    
    public Product(String ProductId) {
        this(ProductId, "", "", "",0, 0, "", "", "", null,"");
    }
    
    /**
     * This is the full constructor.
     */
    public Product(String productId, String mallId, String productName, String company,
            int price1,int price2,String install, String keyword,String detail,Date productDate,String photoDir) {
        this.productId = productId;
        this.mallId = mallId;
        this.productName = productName;
        this.company = company;
        this.price1 = price1;
        this.price2 = price2;
        this.install = install;
        this.keyword = keyword;
        this.detail = detail;
        this.productDate = productDate;
        this.photoDir = photoDir;
    }
    
    public void setProductId(String productId) {
        this.productId = productId;
    }
    public void setMallId(String mallId) {
        this.mallId = mallId;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public void setCompany(String company) {
        this.company = company;
    }
    public void setPrice1(int price1) {
        this.price1 = price1;
    }
    public void setPrice2(int price2) {
        this.price2 = price2;
    }
    public void setInstall(String install) {
        this.install = install;
    }
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
    public void setDetail(String detail) {
        this.detail = detail;
    }
    public void setProductDate(Date productDate) {
        this.productDate = productDate;
    }
    public void setPhotoDir(String photoDir) {
        this.photoDir = photoDir;
    }
    public void setSeq(int seq) {
        this.seq = seq;
    }
    
    public String getProductId() {
        return productId;
    }
    public String getMallId() {
        return mallId;
    }
    public String getProductName() {
        return productName;
    }
    public String getCompany() {
        return company;
    }
    public int getPrice1() {
        return price1;
    }
    public int getPrice2() {
        return price2;
    }
    public String getInstall() {
        return install;
    }
    public String getKeyword() {
        return keyword;
    }
    public String getDetail() {
        return detail;
    }
    public Date getProductDate() {
        return productDate;
    }
    public String getPhotoDir() {
        return photoDir;
    }
    public int getSeq() {
        return seq;
    }
    public String getPrice1S() {
        return nf.format(price1);
    }
    public String getPrice2S() {
        return nf.format(price2);
    }
    
}
