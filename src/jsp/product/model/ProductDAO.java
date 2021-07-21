package jsp.product.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import jsp.board.model.BoardDAO;
import jsp.member.model.MemberBean;


/**
 * 이 도메인 객체는 Product(상품) 테이블의 데이타를 액세스 하는 객체이다.
 */
class ProductDAO {
    private DataSource ds;
    /**
     * 디폴트 생성자
     * 같은 domain package 내에서만 접근 가능하도록 Access Modifier를 default 로 하였다.
     * JNDI API를 이용하여 Naming Service에 등록된 DataSource를 Lookup 한다.
     */
    ProductDAO() {
        try {
            // Retrieve the DataSource from JNDI
            Context ctx = new InitialContext();
            if ( ctx == null ) {
                throw new RuntimeException("JNDI Context could not be found.");
            }
            ds = (DataSource)ctx.lookup("java:comp/env/jdbc/orcl");
            if ( ds == null ) {
                throw new RuntimeException("DataSource could not be found.");
            }
        } catch (NamingException ne) {
            throw new RuntimeException("A JNDI error occured. "+ ne.getMessage());
        }
    }
    
    
    /**
     * 상품ID을 parameter로 넘겨 받아  장바구니에 검색된 상품상세 정보를 반환합니다.
     * @param String productId
     * @return Product product
     */
    public Product selectProduct(String productId) throws Exception {
        if(  ! productIDExist( productId ) ) throw new Exception(productId+"는 존재하는 않는 ProductID 입니다.");
        
        String RETRIEVE_STMT = "Select ProductID,MallID,ProductName,Company,Price1,Price2,Install,keyword,Detail,ProductDate,PhotoDir "+
                "from product where productId= ?";
        System.out.println("ProductDAO selectProduct RETRIEVE_STMT : "+RETRIEVE_STMT);
        
        Product product=null;
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            
            // Get a database connection
            connection = ds.getConnection();
            stmt = connection.prepareStatement(RETRIEVE_STMT);
            stmt.setString(1, productId);
            rs = stmt.executeQuery();
            
            while(rs.next()) {
                product = new Product();
                product.setProductId(rs.getString(1));
                product.setMallId(rs.getString(2));
                product.setProductName(rs.getString(3));
                product.setCompany(rs.getString(4));
                product.setPrice1(rs.getInt(5));
                product.setPrice2(rs.getInt(6));
                product.setInstall(rs.getString(7));
                product.setKeyword(rs.getString(8));
                product.setDetail(rs.getString(9));
                product.setProductDate(rs.getDate(10));
                product.setPhotoDir(rs.getString(11));
            }
            
        } catch(SQLException se) {
            se.printStackTrace();
            throw new RuntimeException("A database error occured. " + se.getMessage());
        } finally {
            try { if(rs != null) rs.close(); } catch(SQLException _ex) { _ex.printStackTrace(System.err);}
            try { if(stmt != null) stmt.close(); } catch(SQLException _ex) {_ex.printStackTrace(System.err); }
            try { if(connection != null) connection.close(); } catch(Exception e){ e.printStackTrace(System.err);}
        }
        return product;
    }
    
    /**
     * 상품ID을 parameter로 넘겨 받아 검색된 상품상세 정보를 반환합니다.
     * @param String productId
     * @return Product product
     */
    public ArrayList<Product> getBoardList(HashMap<String, Object> listOpt){
    	
    	ArrayList<Product> productlist = new ArrayList<Product>();
    	
    	String opt = (String)listOpt.get("opt"); // 검색옵션(제목, 내용, 글쓴이 등..)
        String condition = (String)listOpt.get("condition"); // 검색내용
        int start = (Integer)listOpt.get("start"); // 현재 페이지번호

        Product product=null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {

        	conn = ds.getConnection();
        	StringBuffer sql = new StringBuffer();
        	
        	if(opt == null) {
        		sql.append("SELECT * FROM");
                sql.append(" (SELECT  ROWNUM AS rnum, data.* FROM ");
                sql.append("    (SELECT ProductID, MallID, ProductName, Company,");
                sql.append("            Price1, Price2,Install,");
                sql.append("            keyword, Detail, ProductDate,PHOTODIR");
                sql.append("    FROM Product)");             
                sql.append(" data) ");
                sql.append("WHERE rnum>=? and rnum<=?");
        	
        	
        	//상품 목록 전체를 보여줄 때
        	pstmt = conn.prepareStatement(sql.toString());
        	pstmt.setInt(1, start);
            pstmt.setInt(2, start+9);

        	sql.delete(0, sql.toString().length());
        	}
            else if(opt.equals("0")) {
            	sql.append("SELECT * FROM");
                sql.append(" (SELECT  ROWNUM AS rnum, data.* FROM ");
                sql.append("    (SELECT ProductID, MallID, ProductName, Company,");
                sql.append("            Price1, Price2,Install,");
                sql.append("            keyword, Detail, ProductDate,PHOTODIR");
                sql.append("    FROM Product");
                sql.append("    WHERE ProductName like ? )");
                sql.append(" data) ");
                sql.append("WHERE rnum>=? and rnum<=?");
                
                pstmt = conn.prepareStatement(sql.toString());
                pstmt.setString(1, "%"+condition+"%");
                pstmt.setInt(2, start);
                pstmt.setInt(3, start+9);
                
                sql.delete(0, sql.toString().length());
                
            }
            else if(opt.equals("1")) { //제조회사이름으로 검색
            	sql.append("SELECT * FROM");
                sql.append(" (SELECT  ROWNUM AS rnum, data.* FROM ");
                sql.append("    (SELECT ProductID, MallID, ProductName, Company,");
                sql.append("            Price1, Price2,Install,");
                sql.append("            keyword, Detail, ProductDate,PHOTODIR");
                sql.append("    FROM Product");
                sql.append("    WHERE Company like ?)");
                sql.append(" data) ");
                sql.append("WHERE rnum>=? and rnum<=?");
                
                pstmt = conn.prepareStatement(sql.toString());
                pstmt.setString(1, "%"+condition+"%");
                pstmt.setInt(2, start);
                pstmt.setInt(3, start+9);
                
                sql.delete(0, sql.toString().length());
            }
       	        	        	
        	rs = pstmt.executeQuery();
            for(int i=1;rs.next();i++) {
            	
                product = new Product();                
                product.setProductId(rs.getString("ProductID"));
                product.setMallId(rs.getString("MallID"));
                product.setProductName(rs.getString("ProductName"));
                product.setCompany(rs.getString("Company"));
                product.setPrice1(rs.getInt("Price1"));
                product.setPrice2(rs.getInt("Price2"));
                product.setInstall(rs.getString("Install"));
                product.setKeyword(rs.getString("keyword"));
                product.setDetail(rs.getString("Detail"));
                product.setProductDate(rs.getDate("ProductDate"));
                product.setPhotoDir(rs.getString("PHOTODIR"));
                product.setSeq(i);
                productlist.add(product);
            }
            
        } catch(SQLException se) {
            se.printStackTrace();
            throw new RuntimeException("A database error occured. " + se.getMessage());
        } finally {
            try { if(rs != null) rs.close(); } catch(SQLException _ex) { _ex.printStackTrace(System.err);}
            try { if(pstmt != null) pstmt.close(); } catch(SQLException _ex) {_ex.printStackTrace(System.err); }
            try { if(conn != null) conn.close(); } catch(Exception e){ e.printStackTrace(System.err);}
        }
        return productlist;
    }
    
    public int getProductListCount(HashMap<String, Object> listOpt) {
    	
    	int result = 0;
    	Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String opt = (String)listOpt.get("opt");
        String condition = (String)listOpt.get("condition");
        
        try {
        	conn = BoardDAO.getConnection();
        	StringBuffer sql = new StringBuffer();
        	
        	if(opt == null)    // 전체글의 개수
            {
                sql.append("select count(*) from Product");
                pstmt = conn.prepareStatement(sql.toString());
                
                // StringBuffer를 비운다.
                sql.delete(0, sql.toString().length());
            }
            else if(opt.equals("0")) //
            {
                sql.append("select count(*) from Product where ProductName like ?");
                pstmt = conn.prepareStatement(sql.toString());
                pstmt.setString(1, '%'+condition+'%');
                
                sql.delete(0, sql.toString().length());
            }
            else if(opt.equals("1")) // 
            {
                sql.append("select count(*) from Product where Company like ?");
                pstmt = conn.prepareStatement(sql.toString());
                pstmt.setString(1, '%'+condition+'%');
                
                sql.delete(0, sql.toString().length());
            }
        	
        	 rs = pstmt.executeQuery();
             if(rs.next())    result = rs.getInt(1);
        	
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }finally {
            try {if ( rs != null ) rs.close(); } catch (SQLException sex) {sex.printStackTrace(System.err);}
            try {if ( pstmt != null ) pstmt.close(); } catch (SQLException sex) {sex.printStackTrace(System.err);}
            try {if ( conn != null ) conn.close(); }catch (SQLException e) { e.printStackTrace(System.err); }
        }
        return result;
    }
    
    /**
     * 상품ID를 입력받아 상품ID의 유무를 판별합니다.
     * @param String memID
     * @return boolean
     */
    public boolean productIDExist(String productId) {
        String RETRIEVE_STMT = "Select productid from product where productId = ?";
        System.out.println("ProductDAO productIDExist RETRIEVE_STMT : " + RETRIEVE_STMT);
        
        MemberBean member = null;
        Connection connection=null;
        PreparedStatement stmt=null;
        ResultSet rs = null;
        boolean isExist = false;
        
        try {           
            // Get a database connection
            connection = ds.getConnection();
            stmt=connection.prepareStatement(RETRIEVE_STMT);
            stmt.setString(1, productId);
            rs=stmt.executeQuery();
            isExist = rs.next();
            
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("A database error occured. " + e.getMessage());
        } finally {
            try {if ( rs != null ) rs.close(); } catch (SQLException sex) {sex.printStackTrace(System.err);}
            try {if ( stmt != null ) stmt.close(); } catch (SQLException sex) {sex.printStackTrace(System.err);}
            try {if ( connection != null ) connection.close(); }catch (SQLException e) { e.printStackTrace(System.err); }
        }
        return isExist;
    }
}//ProductDAO

