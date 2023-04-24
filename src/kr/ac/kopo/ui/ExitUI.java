package kr.ac.kopo.ui;

public class ExitUI extends BaseUI {
	
	@Override
	protected void execution() {
		System.out.println("도서 관리 프로그램을 종료합니다.");
		System.exit(0);
	}

}
