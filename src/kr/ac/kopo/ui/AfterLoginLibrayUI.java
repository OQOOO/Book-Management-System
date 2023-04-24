package kr.ac.kopo.ui;

public class AfterLoginLibrayUI extends BaseUI{
	
	private String id;
	
	protected AfterLoginLibrayUI(String id) {
		this.id = id;
	}
	
	private int manu() {
		System.out.println("숫자를 입력하여 선택하세요.");
		System.out.println("1 : 마이페이지");
		System.out.println("2 : 도서검색");
		System.out.println("3 : 도서대여");
		System.out.println("4 : 도서반납");
		System.out.println("5 : 로그아웃");
		System.out.println("6 : 회원탈퇴");
		System.out.println("0 : 종료");
		System.out.println("-----------------------------------------------------------------------------------------------");
		return scanInt("숫자를 입력하세요 >> ");
	}

	public void execution() throws Exception 
	{
		while (true) {
			System.out.println("===============================================================================================");
			int choice = manu();
			BaseUI ui = null;
			
			boolean myPageBack = false;
			boolean result = true; // 탈퇴확인
			
			
			switch (choice) {
			case 1:
				// 마이페이지
				ui = new MyPageUI(id);
				myPageBack = true;
				break;
			case 2:
				// 도서검색
				ui = new SearchBooksUI(0);
				break;
			case 3:
				// 도서대여
				ui = new RentBookUI(id);
				break;
			case 4:
				// 도서반납
				ui = new ReturnBookUI(id);
				break;
			case 5:
				// 로그아웃
				return;
			case 6:
				// 회원탈퇴
				result = new DeleteUserInfo(id).execution1();
				if (result) {
					return;
				}
				break;
			case 0:
				// 종료
				ui = new ExitUI();
				break;
			}
			if (ui != null) {
				ui.execution();
			} else if (result != false) {
				System.out.println("잘못된 입력입니다.");
			}
			System.out.println();
			if (myPageBack == false) {
				scanStr("(엔터를 입력하면 다음으로 진행합니다.)");
			}
		}
	}
}
