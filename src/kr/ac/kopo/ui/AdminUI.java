package kr.ac.kopo.ui;

public class AdminUI extends BaseUI {
	
	String id;
	
	protected AdminUI(String id) {
		this.id = id;
	}
	
	private int manu() {
		System.out.println("숫자를 입력하여 선택하세요.");
		System.out.println("1 : 회원관리");
		System.out.println("2 : 도서관리");
		System.out.println("3 : 로그아웃");
		System.out.println("0 : 종료");
		
		return scanInt("숫자를 입력하세요 >> ");
	}

	@Override
	protected void execution() throws Exception {
		
		while (true) {
			System.out.println("===============================================================================================");
			System.out.println("관리자 화면");
			System.out.println("-----------------------------------------------------------------------------------------------");
			int choice = manu();
			
			BaseUI ui = null;
			
			switch (choice) {
			case 1:
				// 회원관리
				ui = new MemberManagementUI();
				break;
			case 2:
				// 도서관리
				ui = new BooksManagementUI();
				break;
			case 3:
				// 로그아웃
				return;
			case 0:
				// 종료
				ui = new ExitUI();
				break;
			}

			if (ui == null) {
				System.out.println("잘못된 입력입니다.");
			} else {
				ui.execution();
			}
		}
	}
}
