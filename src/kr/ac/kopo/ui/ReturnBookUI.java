package kr.ac.kopo.ui;

import kr.ac.kopo.dao.LibraryBooksDAO;
import kr.ac.kopo.service.MyPageService;
import kr.ac.kopo.vo.BookVO;

public class ReturnBookUI extends BaseUI {
	
	String id;
	
	protected ReturnBookUI(String id) {
		this.id = id;
	}
	
	@Override
	protected void execution() throws Exception {
		
		LibraryBooksDAO dao = new LibraryBooksDAO();
		System.out.println("===============================================================================================");
		System.out.println("반납하려는 책의 ISBN을 입력하세요");
		String isbn = scanStr("ISBN : ");
		System.out.println("-----------------------------------------------------------------------------------------------");
		
		BookVO vo = dao.searchBookWithIsbn(isbn);
		

		if (vo.getRentUserId() == null || !vo.getRentUserId().equals(id)) {
			System.out.println("대여하지 않은 도서입니다.");
			return;
		}
		
		dao.returnBook(isbn);
		System.out.println("반납이 완료되었습니다.");
		new MyPageService(id).printRentalBookList();
	}
}
