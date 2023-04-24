package kr.ac.kopo.ui;

import kr.ac.kopo.service.MemberManagementService;

public class MemberManagementUI extends BaseUI {

	private int manu() {
		System.out.println("숫자를 입력하여 선택하세요.");
		System.out.println("1 : 회원등록");
		System.out.println("2 : 회원삭제");
		System.out.println("3 : 회원검색");
		System.out.println("4 : 관리자 승인");
		System.out.println("5 : 뒤로가기");
		System.out.println("0 : 종료");
		
		return scanInt("숫자를 입력하세요 >> ");
	}
	
	@Override
	protected void execution() throws Exception {
		
		while (true) {
			System.out.println("===============================================================================================");
			int choice = manu();
			
			MemberManagementService service = new MemberManagementService();
			
			switch (choice) {
			case 1:
				// 회원등록
				System.out.println("===============================================================================================");
				service.addMembers();
				break;
			case 2:
				// 회원삭제
				System.out.println("===============================================================================================");
				service.removeMember();
				break;
			case 3:
				// 회원검색
				System.out.println("===============================================================================================");
				service.searchMembers();
				break;
			case 4:
				// 관리자 승인
				System.out.println("===============================================================================================");
				service.givingAdminRights();
				break;
			case 5:
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
