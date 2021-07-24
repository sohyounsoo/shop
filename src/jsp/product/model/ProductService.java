package jsp.product.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;


/**
 * This object performs a variety of procut registration services.
 * I tacts a Facade into the business logic of registering a Product
 */
public class ProductService {
    
    /**
     * This constructor creates a Registration Service object.
     */
    ProductDAO productDataAccess;
    
    public ProductService() {
        productDataAccess = new ProductDAO();
    }
    
    public Product selectProduct(String productId) throws Exception{
        Product product = productDataAccess.selectProduct(productId);
        return product;
    }
    
    public ArrayList<Product> getBoardList(HashMap<String, Object> listOpt){
        return productDataAccess.getBoardList(listOpt);
    }

	public int getProductListCount(HashMap<String, Object> listOpt) {		
		return productDataAccess.getProductListCount(listOpt);
	}
    
}
