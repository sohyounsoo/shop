package jsp.member.model;

import java.sql.SQLException;
import java.util.ArrayList;

import jsp.board.model.BoardDAO;

public class MemberService {
	
	private static MemberService instance;	
	// ΩÃ±€≈Ê ∆–≈œ
	public static MemberService getInstance(){
		if(instance==null)
			instance=new MemberService();
		return instance;
	}
	
	private static MemberDAO md;
	
	public MemberService() {	
		md = new MemberDAO();
	}
	

	public static  int loginCheck(String id, String password) {		
		return md.loginCheck(id, password);
	}

	public void updateMember(MemberBean member) throws SQLException {
		md.updateMember(member);
		
	}

	public MemberBean getUserInfo(String id) {
		return md.getUserInfo(id);
	}

	public ArrayList<MemberBean> getMemberList() {
		return md.getMemberList();
	}
	
	public void insertMember(MemberBean member) throws SQLException {
		md.insertMember(member);
		
	}


	public boolean duplicateIdCheck(String id) {
		
		return md.duplicateIdCheck(id);
	}


	public int deleteMember(String id, String password) {
		return md.deleteMember(id, password);
	}	

}
