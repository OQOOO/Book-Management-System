package kr.ac.kopo.ui;

import java.util.List;

import kr.ac.kopo.dao.LibraryBooksDAO;
import kr.ac.kopo.vo.BookVO;

public class SearchBooksUI extends BaseUI {
	
	int adminRight; // 대여자 정보 출력 여부를 위한 관리자 권한 확인
	
	public SearchBooksUI(int adminRight) {
		this.adminRight = adminRight;
	}
	
	@Override
	protected void execution() throws Exception {
		
		LibraryBooksDAO dao = new LibraryBooksDAO();
		List<BookVO> bookList;
		BookVO vo = new BookVO();
		
		System.out.println("도서 검색을 위해 다음을 입력해주세요(입력받은 조건이 없으면 모두 검색)");
		String bookName = scanStr("책 제목 : ");
		String writer = scanStr("작가 : ");
		String publisher = scanStr("출판사 : ");
		
		if (bookName == "" && writer == "" && publisher == "") {
			bookList = dao.searchAllBooks();
		} else {
			vo.setBookName(bookName);
			vo.setWriter(writer);
			vo.setPublisher(publisher);
			bookList = dao.searchBooks(vo);
		}
		
		System.out.println("-----------------------------------------------------------------------------------------------");
		if (bookList != null) {
			System.out.println("[ 탐색된 도서 목록 ]");
			System.out.println();
			System.out.printf("%-20s\t%-20s\t%-20s\t%-10s\t%-15s\n", "제목", "ISBN", "작가", "출판사", (adminRight == 1 ? "대여자" : "대여여부"));
			System.out.println("-----------------------------------------------------------------------------------------------");
			for (BookVO book : bookList) {
				System.out.printf("%-20s\t%-20s\t%-20s\t%-10s\t%-15s\n"
						, book.getBookName(), book.getIsbn(), book.getWriter(), book.getPublisher()
						, ((book.getRentUserId() != null) ? (adminRight == 1 ? book.getRentUserId() : "대여중") : ""));
			}
		} else {
			System.out.println("조건에 해당하는 책이 없습니다.");
			System.out.println("-----------------------------------------------------------------------------------------------");
		}
		
		
	}
}
