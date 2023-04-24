package kr.ac.kopo;

import kr.ac.kopo.ui.LibrayUI;

public class LibrayMain {

	public static void main(String[] args) {
		
		try {
			new LibrayUI().execution();
		} catch (Exception e) {//도서관 관리 시스템
			e.printStackTrace();
		}
		
		// 유저데이터 delete 만들고있음 userDAO 가면 있음
	}
}
