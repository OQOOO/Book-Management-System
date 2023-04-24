package kr.ac.kopo.ui;

public class LibrayUI extends BaseUI {
	
	private int manu() {
		System.out.println("===============================================================================================");
		System.out.println("도서 관리 시스템");
		System.out.println("-----------------------------------------------------------------------------------------------");
		System.out.println("숫자를 입력하여 선택하세요.");
		System.out.println("1 : 회원가입");
		System.out.println("2 : 로그인");
		System.out.println("0 : 종료");
		
		System.out.println("-----------------------------------------------------------------------------------------------");
		return scanInt("숫자를 입력하세요 >> ");
	}

	public void execution() throws Exception {
		while (true) {
			int choice = manu();
			System.out.println("===============================================================================================");
			
			BaseUI ui = null;
			switch (choice) {
			case 1:
				// 회원가입
				ui = new SignUpUI();
				break;
			case 2:
				// 로그인. 로그인 이후 로그인 이후의 별도 UI로 이동
				ui = new LogInUI();
				break;
			case 0:
				// 종료
				ui = new ExitUI();
				break;
			}
			ui.execution();
		}
	}
	
	/*
	 * [ 실행스택 ]
	 * ...					|						|
	 * MyPageService		|	...					|	...
	 * MyPageUI, ...		|	MemberManagementUI, 	AfterLoginLibrayUI
	 * AfterLoginLibrayUI,		AdminUI
	 * LogInUI
	 * LibrayUI
	 */
}
