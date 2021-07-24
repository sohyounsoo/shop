package jsp.board.model;

import java.util.ArrayList;
import java.util.HashMap;

public class BoardService {	
	
	private BoardDAO bd;
	
	public static BoardService instance;
	 
	public static BoardService getInstance(){
		if(instance==null)
			instance=new BoardService();
	        return instance;
	 }
	
	public BoardService() {	
		bd = new BoardDAO();
	}
	
	//모듈화 테스트 해봄
	public int getSeq() {
		return bd.getSeq();
		
	}
	public boolean boardInsert(BoardBean board) {
		return bd.boardInsert(board);
	}

	public String getFileName(int boardNum) {		
		return bd.getFileName(boardNum);
	}

	public boolean deleteBoard(int boardNum) {
		return bd.deleteBoard(boardNum);
	}

	public BoardBean getDetail(int boardNum) {		
		return bd.getDetail(boardNum);
	}

	public boolean updateCount(int boardNum) {
		return bd.updateCount(boardNum);
	}

	public ArrayList<BoardBean> getBoardList(HashMap<String, Object> listOpt) {
		return bd.getBoardList(listOpt);
	}

	public int getBoardListCount(HashMap<String, Object> listOpt) {
		return bd.getBoardListCount(listOpt);
	}

	public boolean updateBoard(BoardBean border) {
		return bd.updateBoard(border);
	}

	
    
    
}
