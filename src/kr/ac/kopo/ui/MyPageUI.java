package kr.ac.kopo.ui;
import kr.ac.kopo.service.MyPageService;

public class MyPageUI extends BaseUI {
	
	String id;
	public MyPageUI(String id) {
		this.id = id;
	}
	
	private int manu() {
		System.out.println("숫자를 입력하여 선택하세요.");
		System.out.println("1 : 개인정보 출력");
		System.out.println("2 : 대여목록 확인");
		System.out.println("3 : 개인정보 수정");
		System.out.println("4 : 뒤로");
		System.out.println("0 : 종료");
		System.out.println("-----------------------------------------------------------------------------------------------");
		return scanInt("숫자를 입력하세요 >> ");
	}
	@Override
	protected void execution() throws Exception {
		
		while (true) {
			System.out.println("===============================================================================================");
			int choice = manu();
			MyPageService sv = new MyPageService(id);
			switch (choice) {
			case 1:
				// 유저정보
				System.out.println("===============================================================================================");
				sv.printPersonalInfo();
				break;
			case 2:
				// 대여한 책 목록
				System.out.println("===============================================================================================");
				sv.printRentalBookList();
				break;
			case 3:
				// 정보변경 ... '=====' 출력 메소드 안쪽에 있음
				sv.userDataUpdate();
				break;
			case 4:
				// 뒤로가기
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
