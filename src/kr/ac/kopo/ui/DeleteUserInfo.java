package kr.ac.kopo.ui;

import java.util.ArrayList;
import java.util.List;

import kr.ac.kopo.dao.RentBooksDAO;
import kr.ac.kopo.dao.UserDAO;
import kr.ac.kopo.vo.BookVO;
import kr.ac.kopo.vo.UserVO;

public class DeleteUserInfo extends BaseUI {
	
	String id;
	
	public DeleteUserInfo(String id) {
		this.id = id;
	}
	
	@Override
	protected void execution() throws Exception {}
	
	protected boolean execution1() throws Exception {
		System.out.println("===============================================================================================");
		System.out.println("정말로 탈퇴하려면 자신의 비밀번호를 입력해주세요");
		System.out.println("-----------------------------------------------------------------------------------------------");
		//System.out.println("withDrawal");
		String pw = scanStr("비밀번호 입력 : ");
		System.out.println("-----------------------------------------------------------------------------------------------");
//		if (!out.equals("withDrawal")) {
//			System.out.println("제시된 문장과 다릅니다.");
//			return false;
//		}
		
		UserDAO dao = new UserDAO();
		UserVO vo = new UserVO();
		
		vo.setId(id);
		vo.setPassword(pw);
		
		vo = dao.userLogIn(vo);
		if (vo == null) {
			System.out.println("비밀번호가 다릅니다.");
			return false;
		}
		
		RentBooksDAO bookDao = new RentBooksDAO();
		
		List<BookVO> bookLi = new ArrayList<>();
		
		// 유저 정보 vo
		vo.setId(id);

		// 대여한 책 목록
		bookLi = bookDao.getUserRentData(vo);
		
		// 만약 빌려간 책이 있다면
		if(0 < bookLi.size()) {
			System.out.println("다음과 같은 책을 대여중이므로 탈퇴가 불가능합니다.");
			System.out.println("대여한 도서 목록");
			System.out.println("-----------------------------------------------------------------------------------------------");
			System.out.printf("%-30s%-20s%-20s%-20s\n", "제목", "ISBN", "대여일", "반납일");
			System.out.println("-----------------------------------------------------------------------------------------------");
			
			for (BookVO book : bookLi) {

				String rent = Integer.toString(book.getRentalDate());
				String newRent = rent.substring(0, 4)+"-"+rent.substring(4, 6)+"-"+rent.substring(6, 8);
				
				String ret = Integer.toString(book.getReturnDate());
				String newRet = ret.substring(0, 4)+"-"+ret.substring(4, 6)+"-"+ret.substring(6, 8);
				System.out.printf("%-30s%-20s%-20s%-20s\n", book.getBookName(), book.getIsbn(), newRent, newRet);
			}	
			return false;
		} else {
			dao.userDataRemove(id);
			System.out.println("회원탈퇴가 완료되었습니다.");
			return true;
		}
	}
}
