package jsp.board.model;
 

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

 
 
public class BoardDAO 
{
	static DataSource ds;
	/**
	 * 커넥션 풀로 부터 커넥션 객체를 가져와 리턴한다.
	 * @return Connection
	 * @throws SQLException
	 * @throws NamingException
	 * @throws ClassNotFoundException
	 */
	public static Connection getConnection() throws SQLException, NamingException, 
	ClassNotFoundException{
		
			Context initCtx = new InitialContext();
			
			ds=(DataSource)initCtx.lookup("java:comp/env/jdbc/orcl");
			
			//getConnection메서드를 이용해서 커넥션 풀로 부터 커넥션 객체를 얻어내어 conn변수에 저장
			Connection conn = ds.getConnection();
			return conn;
	}
	
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;
    
    
    // 시퀀스를 가져온다.
    public int getSeq()
    {
        int result = 1;
        
        try {
            conn = BoardDAO.getConnection();
            
            // 시퀀스 값을 가져온다. (DUAL : 시퀀스 값을 가져오기위한 임시 테이블)
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT BOARD_NUM.NEXTVAL FROM DUAL"); // 시퀸스 값은 자동적으로 증가 그러나 여기서는 별도로 가져오는메서드
            
            pstmt = conn.prepareStatement(sql.toString());
            // 쿼리 실행
            rs = pstmt.executeQuery();
            
            if(rs.next()) {
            	result = rs.getInt(1);
            }
 
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        
        close();
        return result;    
    } // end getSeq
    
    // 글 삽입
    public boolean boardInsert(BoardBean board)
    {
        boolean result = false;
        
        try {
            conn = BoardDAO.getConnection();
            
            // 자동 커밋을 false로 한다.
            conn.setAutoCommit(false);
            
            StringBuffer sql = new StringBuffer();
            sql.append("INSERT INTO MEMBER_BOARD");
            sql.append("(BOARD_NUM, BOARD_ID, BOARD_SUBJECT, BOARD_CONTENT, BOARD_FILE");
            sql.append(", BOARD_RE_REF, BOARD_COUNT, BOARD_DATE, BOARD_PARENT)");
            sql.append(" VALUES(?,?,?,?,?,?,?,sysdate,?)");
            
            int num = board.getBoard_num();            // 글번호(시퀀스 값)
            int ref = board.getBoard_re_ref();         // 그룹번호
            int parent = board.getBoard_parent();     // 부모글번호
            
            // 부모글일 경우 그룹번호와 글번호 동일
            if(parent == 0) {
            	ref = num; //답변글 그룹번호는 부모 글의 글 번호가 들어가야 한다.
            }
            
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setInt(1, num);
            pstmt.setString(2, board.getBoard_id());
            pstmt.setString(3, board.getBoard_subject());
            pstmt.setString(4, board.getBoard_content());
            pstmt.setString(5, board.getBoard_file());
            pstmt.setInt(6, ref);
            pstmt.setInt(7, board.getBoard_count());
            pstmt.setInt(8, parent);
 
            int flag = pstmt.executeUpdate();
            if(flag > 0){
                result = true;
                conn.commit(); // 완료시 커밋
            }
            
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            } 
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        
        close();
        return result;    
    }

// end boardInsert();
    
    
    // 글목록 가져오기                                                            //검색조건과 내용이 담긴 Map
    public ArrayList<BoardBean> getBoardList(HashMap<String, Object> listOpt)
    {
        ArrayList<BoardBean> list = new ArrayList<BoardBean>();
        
        String opt = (String)listOpt.get("opt");
        String condition = (String)listOpt.get("condition");
        int start = (Integer)listOpt.get("start");
        
        try {
            conn = ds.getConnection();
            StringBuffer sql = new StringBuffer();
            
            // 글목록 전체를 보여줄 때
            if(opt == null)
            	
            	 /*
                 * SELECT * FROM
                 *            (SELECT  ROWNUM AS rnum,
                 *                   data.*
                 *             FROM
                 *                   (SELECT LEVEL,
                 *                              BOARD_NUM,
                 *                             BOARD_ID,
                 *                            BOARD_SUBJECT,
                 *                             BOARD_CONTENT,
                 *                             BOARD_FILE,
                 *                             BOARD_COUNT,
                 *                             BOARD_RE_REF,
                 *                             BOARD_PARENT,
                 *                             BOARD_DATE
                 *                    FROM MEMBER_BOARD
                 *                   START WITH BOARD_PARENT = 0
                 *                   CONNECT BY PRIOR BOARD_NUM = BOARD_PARENT
                 *                   ORDER SIBLINGS BY BOARD_RE_REF desc) 
                 *             data)
                  * WHERE rnum>=? and rnum<=? ;
                 */
            {
                // BOARD_RE_REF(그룹번호)의 내림차순 정렬 후 동일한 그룹번호일 때는
                // BOARD_NUM(글번호)의 오름차순으로 정렬 한 후에
                // start번 째 부터 start+9까지(10개의 글을 한 목록에 보여주기 위해)의 
                // 데이터를 검색해주는 sql
                // desc : 내림차순, asc : 오름차순 ( 생략 가능 )
            	sql.append("SELECT * FROM");
                sql.append(" (SELECT  ROWNUM AS rnum, data.* FROM ");
                sql.append("    (SELECT LEVEL, BOARD_NUM, BOARD_ID,    BOARD_SUBJECT,");
                sql.append("            BOARD_CONTENT, BOARD_FILE,BOARD_COUNT,");
                sql.append("            BOARD_RE_REF, BOARD_PARENT, BOARD_DATE");
                sql.append("    FROM MEMBER_BOARD");
                sql.append("    START WITH BOARD_PARENT = 0");// 0인 곳을 기준으로 계층 쿼리를 만든다
                sql.append("    CONNECT BY PRIOR BOARD_NUM = BOARD_PARENT");//Connect by Prior는 상위-하위 계층의 관계를 정하는 곳이다.
                sql.append("    ORDER SIBLINGS BY BOARD_RE_REF desc)");//. ORDER BY를 사용하면 정렬시 계층구조가 깨져버린다.            
                sql.append(" data) ");
                sql.append("WHERE rnum>=? and rnum<=?");
           //글에 순번을 부여하는 rownum이 먼저 실행되고 이후 order by 절이 실행되면서 원하는 결과가 나오지 않게 된다. 
           //그래서 rownum과 order by를 같이 사용할 경우 서브쿼리로 order by 를 수행하여 정렬 시킨 후 rownum을 사용해야 한다.
                
                
                pstmt = conn.prepareStatement(sql.toString());
                pstmt.setInt(1, start);
                pstmt.setInt(2, start+9);
                              
                // StringBuffer를 비운다.
                sql.delete(0, sql.toString().length());
            }
            else if(opt.equals("0")) // 제목으로 검색
            {
            	sql.append("SELECT * FROM");
                sql.append(" (SELECT  ROWNUM AS rnum, data.* FROM ");//ROWNUM 조회 순번 매기기
                sql.append("    (SELECT LEVEL, BOARD_NUM, BOARD_ID,    BOARD_SUBJECT,");
                sql.append("            BOARD_CONTENT, BOARD_FILE,BOARD_COUNT,");
                sql.append("            BOARD_RE_REF, BOARD_PARENT, BOARD_DATE");
                sql.append("    FROM MEMBER_BOARD");
                sql.append("    WHERE BOARD_SUBJECT like ?");
                sql.append("	START WITH BOARD_PARENT = 0");
                sql.append("    CONNECT BY PRIOR BOARD_NUM = BOARD_PARENT");
                sql.append("    ORDER SIBLINGS BY BOARD_RE_REF desc)");              
                sql.append(" data) ");
                sql.append("WHERE rnum>=? and rnum<=?");
                
                pstmt = conn.prepareStatement(sql.toString());
                pstmt.setString(1, "%"+condition+"%");
                pstmt.setInt(2, start);
                pstmt.setInt(3, start+9);
                
                sql.delete(0, sql.toString().length());
            }
            else if(opt.equals("1")) // 내용으로 검색
            {
            	sql.append("SELECT * FROM");
                sql.append(" (SELECT  ROWNUM AS rnum, data.* FROM ");
                sql.append("    (SELECT LEVEL, BOARD_NUM, BOARD_ID,    BOARD_SUBJECT,");
                sql.append("            BOARD_CONTENT, BOARD_FILE,BOARD_COUNT,");
                sql.append("            BOARD_RE_REF, BOARD_PARENT, BOARD_DATE");
                sql.append("    FROM MEMBER_BOARD");
                sql.append("    WHERE BOARD_CONTENT like ?");
                sql.append("    START WITH BOARD_PARENT = 0");
                sql.append("    CONNECT BY PRIOR BOARD_NUM = BOARD_PARENT");
                sql.append("    ORDER SIBLINGS BY BOARD_RE_REF desc)");              
                sql.append(" data) ");
                sql.append("WHERE rnum>=? and rnum<=?");
                
                pstmt = conn.prepareStatement(sql.toString());
                pstmt.setString(1, "%"+condition+"%");
                pstmt.setInt(2, start);
                pstmt.setInt(3, start+9);
                
                sql.delete(0, sql.toString().length());
            }
            else if(opt.equals("2")) // 제목+내용으로 검색
            {
            	sql.append("SELECT * FROM");
                sql.append(" (SELECT  ROWNUM AS rnum, data.* FROM ");
                sql.append("    (SELECT LEVEL, BOARD_NUM, BOARD_ID,    BOARD_SUBJECT,");
                sql.append("            BOARD_CONTENT, BOARD_FILE,BOARD_COUNT,");
                sql.append("            BOARD_RE_REF, BOARD_PARENT, BOARD_DATE");
                sql.append("    FROM MEMBER_BOARD");
                sql.append("    WHERE BOARD_SUBJECT like ?");
                sql.append("    OR BOARD_CONTENT like ?");
                sql.append("    START WITH BOARD_PARENT = 0");
                sql.append("    CONNECT BY PRIOR BOARD_NUM = BOARD_PARENT");
                sql.append("    ORDER SIBLINGS BY BOARD_RE_REF desc)");              
                sql.append(" data) ");
                sql.append("WHERE rnum>=? and rnum<=?");
                
                pstmt = conn.prepareStatement(sql.toString());
                pstmt.setString(1, "%"+condition+"%");
                pstmt.setString(2, "%"+condition+"%");
                pstmt.setInt(3, start);
                pstmt.setInt(4, start+9);
                
                sql.delete(0, sql.toString().length());
            }
            else if(opt.equals("3")) // 글쓴이로 검색
            {
            	sql.append("SELECT * FROM");
                sql.append(" (SELECT  ROWNUM AS rnum, data.* FROM ");
                sql.append("    (SELECT LEVEL, BOARD_NUM, BOARD_ID,    BOARD_SUBJECT,");
                sql.append("            BOARD_CONTENT, BOARD_FILE,BOARD_COUNT,");
                sql.append("            BOARD_RE_REF, BOARD_PARENT, BOARD_DATE");
                sql.append("    FROM MEMBER_BOARD");
                sql.append("    WHERE BOARD_ID like ?");
                sql.append("    START WITH BOARD_PARENT = 0");
                sql.append("    CONNECT BY PRIOR BOARD_NUM = BOARD_PARENT");
                sql.append("    ORDER SIBLINGS BY BOARD_RE_REF desc)");              
                sql.append(" data) ");
                sql.append("WHERE rnum>=? and rnum<=?");
                
                pstmt = conn.prepareStatement(sql.toString());
                pstmt.setString(1, "%"+condition+"%");
                pstmt.setInt(2, start);
                pstmt.setInt(3, start+9);
                
                sql.delete(0, sql.toString().length());
            }
            
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                BoardBean board = new BoardBean();
                board.setBoard_re_lev(rs.getInt("LEVEL"));
                board.setBoard_num(rs.getInt("BOARD_NUM"));
                board.setBoard_id(rs.getString("BOARD_ID"));
                board.setBoard_subject(rs.getString("BOARD_SUBJECT"));
                board.setBoard_content(rs.getString("BOARD_CONTENT"));
                board.setBoard_file(rs.getString("BOARD_FILE"));
                board.setBoard_count(rs.getInt("BOARD_COUNT"));
                board.setBoard_re_ref(rs.getInt("BOARD_RE_REF"));
                board.setBoard_parent(rs.getInt("BOARD_PARENT"));
                board.setBoard_date(rs.getDate("BOARD_DATE"));
                list.add(board);
            }
            
        } catch (Exception e) {
        	e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        
        close();
        return list;
    } // end getBoardList
    
    // 글의 개수를 가져오는 메서드                            //검색조건과 내용이 담긴 Map
    public int getBoardListCount(HashMap<String, Object> listOpt)
    {
        int result = 0;
        String opt = (String)listOpt.get("opt");
        String condition = (String)listOpt.get("condition");
        
        try {
            conn = BoardDAO.getConnection();
            StringBuffer sql = new StringBuffer();
            
            if(opt == null)    // 전체글의 개수
            {
                sql.append("select count(*) from MEMBER_BOARD");
                pstmt = conn.prepareStatement(sql.toString());
                
                // StringBuffer를 비운다.
                sql.delete(0, sql.toString().length());
            }
            else if(opt.equals("0")) // 제목으로 검색한 글의 개수
            {
                sql.append("select count(*) from MEMBER_BOARD where BOARD_SUBJECT like ?");
                pstmt = conn.prepareStatement(sql.toString());
                pstmt.setString(1, '%'+condition+'%');
                
                sql.delete(0, sql.toString().length());
            }
            else if(opt.equals("1")) // 내용으로 검색한 글의 개수
            {
                sql.append("select count(*) from MEMBER_BOARD where BOARD_CONTENT like ?");
                pstmt = conn.prepareStatement(sql.toString());
                pstmt.setString(1, '%'+condition+'%');
                
                sql.delete(0, sql.toString().length());
            }
            else if(opt.equals("2")) // 제목+내용으로 검색한 글의 개수
            {
                sql.append("select count(*) from MEMBER_BOARD ");
                sql.append("where BOARD_SUBJECT like ? or BOARD_CONTENT like ?");
                pstmt = conn.prepareStatement(sql.toString());
                pstmt.setString(1, '%'+condition+'%');
                pstmt.setString(2, '%'+condition+'%');
                
                sql.delete(0, sql.toString().length());
            }
            else if(opt.equals("3")) // 글쓴이로 검색한 글의 개수
            {
                sql.append("select count(*) from MEMBER_BOARD where BOARD_ID like ?");
                pstmt = conn.prepareStatement(sql.toString());
                pstmt.setString(1, '%'+condition+'%');
                
                sql.delete(0, sql.toString().length());
            }
            
            rs = pstmt.executeQuery();
            if(rs.next())    result = rs.getInt(1);
            
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        
        close();
        return result;
    } // end getBoardListCount
    
    
    // 상세보기
    public BoardBean getDetail(int boardNum)
    {    
        BoardBean board = null;
        
        try {
            conn = BoardDAO.getConnection();
            
            StringBuffer sql = new StringBuffer();
            sql.append("select * from MEMBER_BOARD where BOARD_NUM = ?");
            
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setInt(1, boardNum);
            
            rs = pstmt.executeQuery();
            if(rs.next())
            {
                board = new BoardBean();
                board.setBoard_num(boardNum);
                board.setBoard_id(rs.getString("BOARD_ID"));
                board.setBoard_subject(rs.getString("BOARD_SUBJECT"));
                board.setBoard_content(rs.getString("BOARD_CONTENT"));
                board.setBoard_file(rs.getString("BOARD_FILE"));
                board.setBoard_count(rs.getInt("BOARD_COUNT"));
                board.setBoard_re_ref(rs.getInt("BOARD_RE_REF"));
                board.setBoard_re_lev(rs.getInt("BOARD_RE_LEV"));
                board.setBoard_re_seq(rs.getInt("BOARD_RE_SEQ"));
                board.setBoard_date(rs.getDate("BOARD_DATE"));
            }
            
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        
        close();
        return board;
    } // end getDetail()
    
    // 조회수 증가
    public boolean updateCount(int boardNum)
    {
        boolean result = false;
        
        try {
            conn = BoardDAO.getConnection();
            
            // 자동 커밋을 false로 한다.
            conn.setAutoCommit(false);
            
            StringBuffer sql = new StringBuffer();
            sql.append("update MEMBER_BOARD set BOARD_COUNT = BOARD_COUNT+1 ");
            sql.append("where BOARD_NUM = ?");
            
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setInt(1, boardNum);
            
            int flag = pstmt.executeUpdate();
            if(flag > 0){
                result = true;
                conn.commit(); // 완료시 커밋
            }    
        } catch (Exception e) {
            try {
                conn.rollback(); // 오류시 롤백
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
            throw new RuntimeException(e.getMessage());
        }
        
        close();
        return result;
    } // end updateCount
     
    
    //파일을 가져오는 메서드 (삭제하기 위해서)
    public String getFileName(int boardNum) {
    	
    	String fileName = null;
    	
    	try {
    		conn = BoardDAO.getConnection();
    		
    		StringBuffer sql = new StringBuffer();
    		sql.append("SELECT BOARD_FILE from MEMBER_BOARD where BOARD_NUM=?");
    		
    		pstmt = conn.prepareStatement(sql.toString());
    		pstmt.setInt(1, boardNum);
    		
    		rs = pstmt.executeQuery();
    		if(rs.next()) {
    			fileName = rs.getString("BOARD_FILE");
    		}
    		
    	}catch(Exception e) {
    		throw new RuntimeException(e.getMessage());
    	}
    	
    	close();
    	return fileName;
    }
    
    
    
    
    //게시글 삭제 ,답글도 같이 삭제
    public boolean deleteBoard(int boardNum) {
    	
    	boolean result = false;
    	
    	try {
    		conn = BoardDAO.getConnection();
    		conn.setAutoCommit(false);
    		
    		StringBuffer sql = new StringBuffer();
    		sql.append("DELETE FROM MEMBER_BOARD");
    		sql.append(" WHERE BOARD_NUM IN"); //In연산자는 or연산자랑 비슷
    		sql.append(" (SELECT BOARD_NUM");
    		sql.append(" FROM MEMBER_BOARD");
    		sql.append(" START WITH BOARD_NUM = ?");// 굴번호 계층을 이루는 글을 모두 검색한다.
    		sql.append(" CONNECT BY PRIOR BOARD_NUM = BOARD_PARENT)");
    	 		
    		pstmt = conn.prepareStatement(sql.toString());
    		pstmt.setInt(1, boardNum);
    		
    		int flag = pstmt.executeUpdate();
    		if(flag > 0) {
    			result = true;
    			conn.commit();
    		}
    		   		
    	}catch(Exception e) {  		
    		try {
    			conn.rollback();
    		}catch(SQLException sqle) {
    			sqle.printStackTrace();
    		}
    		throw new RuntimeException(e.getMessage());
    	}
    	
    	close();
    	return result;
    }
    
    public boolean updateBoard(BoardBean border) {
    	boolean result = false;
    	
    	try {
    		conn = BoardDAO.getConnection();
    		conn.setAutoCommit(false);
    		
    		StringBuffer sql = new StringBuffer();
			sql.append("UPDATE MEMBER_BOARD SET");
			sql.append(" BOARD_SUBJECT=?");
			sql.append(" ,BOARD_CONTENT=?");
			sql.append(" ,BOARD_FILE=?");
			sql.append(" ,BOARD_DATE=SYSDATE ");
			sql.append("WHERE BOARD_NUM=?");
    		
    		pstmt = conn.prepareStatement(sql.toString());
    		pstmt.setString(1, border.getBoard_subject());
    		pstmt.setString(2, border.getBoard_content());
    		pstmt.setString(3, border.getBoard_file());
    		pstmt.setInt(4, border.getBoard_num());
    		
    		int flag = pstmt.executeUpdate();
    		if(flag> 0) {
    			result = true;
    			conn.commit();
    			
    		}
    		 
    	}catch(Exception e) {
    		try {
    			conn.rollback();
    		}catch(SQLException sqle) {
    			sqle.printStackTrace();
    		}
    		throw new RuntimeException(e.getMessage());
    	}
    	
    	close();
    	return result;
    }
    
   
    // DB 자원해제
    private void close()
    {
        try {
            if ( pstmt != null ){ pstmt.close(); pstmt=null; }
            if ( conn != null ){ conn.close(); conn=null;    }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    } // end close()
}
