
package jsp.product.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.ArrayList;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * 이 도메인 객체는 Basket(쇼핑카트) 테이블과
 * Purchaser(구매) 테이블의 데이타를 액세스 하는 객체이다.
 */
public class BasketPurchaserDAO {
    private DataSource ds;
    /**
     * 디폴트 생성자
     * 같은 domain package 내에서만 접근 가능하도록 Access Modifier를 default 로 하였다.
     * JNDI API를 이용하여 Naming Service에 등록된 DataSource를 Lookup 한다.
     */
    BasketPurchaserDAO() {
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
     * Basket객체와 MemberID를 parameter로 받아 Basket 테이블에 정보를 등록시킨다.
     * @param Basket basket,String MEMID
     */
    public void insertBasketPurchaser(Basket basket,Purchaser purchaser,String MEMID) {
        String INSERTBasket_STMT = "INSERT INTO basket (ORDERNUM, PRODUCTID, MEMID, QUANTITY, PRICE) VALUES (?, ?, ?, ?, ?)";
        System.out.println("BasketDAO insertBasket : "+INSERTBasket_STMT);
        
        String INSERTPurchaser_STMT
                = "INSERT INTO Purchaser "
                +"(ORDERNUM, PLACE, MEMID, NAME, ADDRESS, TEL, EMAIL, PAYTYPE, TCOUNT, AMOUNT, PAYSTATUS, PURCHASEDATE, "
                +"CARDTYPE, CARDNUMBER) "
                +"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        System.out.println("BasketDAO insertPurchaser : "+INSERTPurchaser_STMT);
        
        Connection connection = null;
        PreparedStatement stmt_b = null;
        PreparedStatement stmt_p = null;
        
        try {
            
            // Get a database connection
            connection = ds.getConnection();
            connection.setAutoCommit(false);
            
            stmt_b = connection.prepareStatement(INSERTBasket_STMT);
            stmt_b.setString(1, basket.getOrderNum().trim());
            stmt_b.setString(2, basket.getProductId().trim());
            stmt_b.setString(3, MEMID.trim());
            stmt_b.setInt(4, basket.getQuantity());
            stmt_b.setInt(5, basket.getPrice());
            stmt_b.executeUpdate();
            
            stmt_p = connection.prepareStatement(INSERTPurchaser_STMT);
            stmt_p.setString(1, purchaser.getOrderNum());
            stmt_p.setString(2, purchaser.getPlace());
            stmt_p.setString(3, purchaser.getMemID());
            stmt_p.setString(4, purchaser.getName());
            stmt_p.setString(5, purchaser.getAddress());
            stmt_p.setString(6, purchaser.getTel());
            stmt_p.setString(7, purchaser.getEmail());
            stmt_p.setString(8, purchaser.getPayType());
            stmt_p.setInt(9, purchaser.getTcount());
            stmt_p.setInt(10, purchaser.getAmount());
            stmt_p.setString(11, purchaser.getPayStatus());
            stmt_p.setDate(12, new Date(System.currentTimeMillis()));
            stmt_p.setString(13, purchaser.getCardType());
            stmt_p.setString(14, purchaser.getCardNumber());
            stmt_p.executeUpdate();
            
            connection.commit();
            
            
        } catch(SQLException se) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            throw new RuntimeException("A database error occured. " + se.getMessage());
        } finally {
            try { if(stmt_b != null) stmt_b.close(); } catch(SQLException _ex) { _ex.printStackTrace(System.err);}
            try { if(stmt_p != null) stmt_p.close(); } catch(SQLException _ex) { _ex.printStackTrace(System.err);}
            try { if(connection != null) connection.close(); } catch(SQLException e){ e.printStackTrace(System.err);}
        }
    }
     /**
     * 주문번호를 새로 추가하기 위해 주문번호중 가장 큰값을 조회하여 1 증가시킨 값을 반환한다.
     * @param
     * @return int
     */
    public int OrderMaxNo() {
        String query = "select max(ordernum) from purchaser";
        System.out.println("OrderMaxNo() : "+query);
        
        int maxno=0;
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            
            connection = ds.getConnection();            
            stmt=connection.prepareStatement(query);
            rs = stmt.executeQuery();
            
            if(rs.next()){
                maxno = rs.getInt(1)+1;
            }
        } catch(SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());
        } finally {
            try { if ( rs != null ) rs.close(); } catch (SQLException sex) {sex.printStackTrace(System.err);}
            try { if(stmt != null) stmt.close(); } catch(SQLException _ex) { _ex.printStackTrace();}
            try { if(connection != null) connection.close(); } catch(SQLException e){ e.printStackTrace(System.err);}
        }
        return maxno;
    }
    /**
     * 구매 완료후에 MemberID를 parameter로 받아 Basket 테이블에 정보를 삭제시킨다.
     * @param String MEMID
     */
    public void deleteBasket( String MEMID ) {
        String DELT_STMT = "DELETE FROM BASKET WHERE MEMID = ?";
        System.out.println("BasketPurchaserDAO deleteBasket : "+ DELT_STMT + " " + MEMID);
        
        Connection connection = null;
        PreparedStatement pstmt = null;
        
        try {
            
            // Get a database connection
            connection = ds.getConnection();
            pstmt = connection.prepareStatement(DELT_STMT);
            pstmt.setString(1, MEMID);
            int delCount = pstmt.executeUpdate();
            System.out.println("BasketPurchaserDAO deleteBasket delCount :" + delCount);
        } catch(SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());
        } finally {
            try { if(pstmt != null) pstmt.close(); } catch(SQLException _ex) { _ex.printStackTrace(System.err);}
            try { if(connection != null) connection.close(); } catch(SQLException e){ e.printStackTrace(System.err);}
        }
    }
    /**
     * MemeberID를 Parameter로 받아 고객의 주문 정보를 Purchaser , Basket, Product 테이블에서 읽어와서 Collection으로 리턴한다.
     * @param String memID
     * @return Collection
     */
    public Collection listBasketPurchaser(String memID) {
        String SELT_STMT =
                "select a.ordernum, c.PRODUCTNAME, b.PRICE, b.QUANTITY, a.ADDRESS, a.PAYTYPE, a.EMAIL, a.TEL "
                +"from purchaser a,basket b,product c "
                +"where a.MEMID= ? and a.PAYSTATUS='N' "
                +"and a.ORDERNUM = b.ORDERNUM and b.PRODUCTID=c.PRODUCTID order by ordernum";
        System.out.println("BasketPurchaserDAO listBasketPurchaser query : "+ SELT_STMT);
        
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList arrayList=null;
        TheOrder theOrder=null;
        
        try {
            // Get a database connection
            connection = ds.getConnection();
            stmt = connection.prepareStatement(SELT_STMT);
            stmt.setString(1, memID);
            rs = stmt.executeQuery();
            
            arrayList=new ArrayList();
            while(rs.next()) {
                theOrder=new TheOrder();
                
                theOrder.setOrderNum(rs.getString(1));
                theOrder.setProductName(rs.getString(2));
                theOrder.setPrice(rs.getInt(3));
                theOrder.setQuantity(rs.getInt(4));
                theOrder.setAddress(rs.getString(5));
                theOrder.setPaytype(rs.getString(6));
                theOrder.setEmail(rs.getString(7));
                theOrder.setTel(rs.getString(8));
                arrayList.add(theOrder);
            }
            
        } catch(SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());
        } finally {
            try { if(rs != null) rs.close(); } catch(SQLException _ex) { _ex.printStackTrace(System.err);}
            try { if(stmt != null) stmt.close(); } catch(SQLException _ex) { _ex.printStackTrace(System.err);}
            try { if(connection != null) connection.close(); } catch(SQLException e){ e.printStackTrace(System.err);}
        }
        return arrayList;
    }
    
}