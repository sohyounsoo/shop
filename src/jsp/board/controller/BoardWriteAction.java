package jsp.board.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import jsp.board.model.BoardBean;
import jsp.board.model.BoardDAO;
import jsp.board.model.BoardService;
import jsp.common.controller.Action;
import jsp.common.controller.ActionForward;


public class BoardWriteAction implements Action
{	
	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
				
		// 업로드 파일 사이즈
		int fileSize= 100*1024*1024;
		// 업로드될 폴더 절대경로
		String uploadPath = request.getServletContext().getRealPath("/UploadFolder");

		try {
			
			// 파일업로드 
			MultipartRequest multi = new MultipartRequest
					(request, uploadPath, fileSize, "euc-kr", new DefaultFileRenamePolicy());

			// 파일이름 가져오기
			String fileName = "";
			Enumeration<String> names = multi.getFileNames();
			if(names.hasMoreElements())
			{
				String name = names.nextElement();
				fileName = multi.getFilesystemName(name); //파일이름을 추출한다.
			}
			
			BoardService bs = BoardService.getInstance();
			BoardBean border = new BoardBean();
			
			border.setBoard_num(bs.getSeq()); // 시퀀스값 가져와 세팅
			border.setBoard_id(multi.getParameter("board_id")); // 히든값
			border.setBoard_subject(multi.getParameter("board_subject"));
			border.setBoard_content(multi.getParameter("board_content"));
			border.setBoard_file(fileName);
		
			boolean result = bs.boardInsert(border);
			
			
			if(result){
				forward.setRedirect(true);
				forward.setNextPath("BoardListAction.bo");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("글 작성 오류 : " + e.getMessage());
		}
		return forward;
	}
}
