package kr.ac.kopo.ui;

import kr.ac.kopo.service.BooksManagementService;

public class BooksManagementUI extends BaseUI {

	private int manu() {
		System.out.println("숫자를 입력하여 선택하세요.");
		System.out.println("1 : 도서 목록 출력");
		System.out.println("2 : 도서 등록");
		System.out.println("3 : 도서 삭제");
		System.out.println("4 : 대출 현황 출력");
		System.out.println("5 : 뒤로가기");
		System.out.println("0 : 종료");
		
		return scanInt("숫자를 입력하세요 >> ");
	}
	@Override
	protected void execution() throws Exception {
		// TODO Auto-generated method stub
		while (true) {
			System.out.println("===============================================================================================");
			int choice = manu();
			
			BooksManagementService service = new BooksManagementService();
			
			switch (choice) {
			case 1:
				// 도서 목록 출력
				System.out.println("===============================================================================================");
				new SearchBooksUI(1).execution();
				break;
			case 2:
				// 도서등록
				System.out.println("===============================================================================================");
				service.bookRegistration();
				break;
			case 3:
				// 도서삭제
				System.out.println("===============================================================================================");
				service.bookRemove();
				break;
			case 4:
				// 대출현황
				System.out.println("===============================================================================================");
				service.RentBookChack();
				break;
			case 5:
				// 로그아웃
				return;
			case 0:
				// 종료
				new ExitUI().execution();
				break;
			}
			System.out.println();
			scanStr("(엔터를 입력하면 다음으로 진행합니다.)");

		}

	}

}
