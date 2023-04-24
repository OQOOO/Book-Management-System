package kr.ac.kopo.service;

import kr.ac.kopo.dao.LibraryBooksDAO;
import kr.ac.kopo.ui.BaseUI;
import kr.ac.kopo.vo.BookVO;

public class BooksManagementService extends BaseUI {

	@Override
	protected void execution() throws Exception {
		
	}
	
	

	public void bookRegistration() {
		LibraryBooksDAO dao = new LibraryBooksDAO();
		
		System.out.println("도서 등록을 위해 다음의 항목을 입력해주세요");
		String isbn = "";
		
		// isbn 중복체크
		boolean chack = false;
		for(int i = 0; i < 5; ++i) {
			isbn = scanStr("ISBN : ");
			chack = dao.isbnChack(isbn);
			if (chack == true) {
				if(isbn.matches("^[0-9]{13}$")) {
					break;
				}
				System.out.println("잘못된 입력입니다.");
			} else if (i < 4) {
				System.out.println("이미 존재하는 ISBN입니다. 다시 입력해주세요.");
			}
		}
		if (chack == false) {
			System.out.println("이전 화면으로 돌아갑니다.");
			return;
		}
		
		String bookName = scanStr("제목 : ");
		String writer = scanStr("작가 : ");
		String publisher = scanStr("출판사 : ");
		
		BookVO vo = new BookVO(isbn, bookName, writer, publisher, null);
		dao.bookRegistration(vo);
		System.out.println("도서 등록이 완료되었습니다.");
	}

	public void bookRemove() {
		LibraryBooksDAO dao = new LibraryBooksDAO();
		System.out.println("삭제할 도서의 ISBN을 입력해주세요.");
		String isbn = scanStr("ISBN : ");

		if (dao.isbnChack(isbn) == true) {
			System.out.println("존재하지 않는 ISBN입니다.");
			return;
		}
		dao.bookRemove(isbn);
		System.out.println("해당 도서가 삭제되었습니다.");
	}

	public void RentBookChack() {
		LibraryBooksDAO dao = new LibraryBooksDAO();
		
		System.out.println("대여한 도서 목록");
		System.out.println("-----------------------------------------------------------------------------------------------");
		System.out.printf("%-20s\t%-20s\t%-20s\t%-15s%s\n", "아이디", "제목", "ISBN", "대여일", "반납일");
		System.out.println("-----------------------------------------------------------------------------------------------");
		
		for (BookVO book : dao.RentBookList()) {

			String rent = Integer.toString(book.getRentalDate());
			String newRent = rent.substring(0, 4)+"-"+rent.substring(4, 6)+"-"+rent.substring(6, 8);
			
			String ret = Integer.toString(book.getReturnDate());
			String newRet = ret.substring(0, 4)+"-"+ret.substring(4, 6)+"-"+ret.substring(6, 8);
			
			System.out.printf("%-20s\t%-20s\t%-20s\t%-15s\t%-20s\n", book.getRentUserId() , book.getBookName(), book.getIsbn(), newRent, newRet);
		}	
	}
}



















