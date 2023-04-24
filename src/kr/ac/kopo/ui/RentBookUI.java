package kr.ac.kopo.ui;

import kr.ac.kopo.dao.LibraryBooksDAO;
import kr.ac.kopo.service.MyPageService;
import kr.ac.kopo.vo.BookVO;

public class RentBookUI extends BaseUI{
	
	String id;
	
	protected RentBookUI(String id) {
		this.id = id;
	}

	@Override
	protected void execution() throws Exception {
		LibraryBooksDAO dao = new LibraryBooksDAO();

		System.out.println("===============================================================================================");
		System.out.println("대여하고 싶은 도서의 ISBN을 입력하세요.");
		String isbn = scanStr("ISBN : ");
		System.out.println("-----------------------------------------------------------------------------------------------");
		BookVO vo = dao.searchBookWithIsbn(isbn);
		if (vo.getIsbn() == null) {
			System.out.println("존재하지 않는 도서입니다.");
			return;
		}
		
		if (vo.getRentUserId() != null) {
			System.out.println("해당 도서는 이미 대여중입니다.");
			return;
		}
		
		// isbn과 id 전송
		vo.setRentUserId(id);
		dao.rentBook(vo);
		System.out.println("대여가 완료되었습니다.");
		new MyPageService(id).printRentalBookList();
	}
}
