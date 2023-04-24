package kr.ac.kopo.service;

import java.util.ArrayList;
import java.util.List;

import kr.ac.kopo.dao.RentBooksDAO;
import kr.ac.kopo.dao.UserDAO;
import kr.ac.kopo.ui.BaseUI;
import kr.ac.kopo.vo.BookVO;
import kr.ac.kopo.vo.UserVO;

public class MyPageService extends BaseUI {
	
	String id;
	public MyPageService(String id) {
		this.id = id;
	}
	
	protected void execution() {
		System.out.println();
	}
	
	public void printBookList(List<BookVO> bookList) {
		
		System.out.println("[ 대여한 도서 목록 ]");
		System.out.printf("%-20s\t%-20s\t%-15s\t%-20s\n", "제목", "ISBN", "대여일", "반납일");
		System.out.println("-----------------------------------------------------------------------------------------------");
		
		for (BookVO book : bookList) {

			String rent = Integer.toString(book.getRentalDate());
			String newRent = rent.substring(0, 4)+"-"+rent.substring(4, 6)+"-"+rent.substring(6, 8);
			
			String ret = Integer.toString(book.getReturnDate());
			String newRet = ret.substring(0, 4)+"-"+ret.substring(4, 6)+"-"+ret.substring(6, 8);
			System.out.printf("%-20s\t%-20s\t%-20s\t%-20s\n", book.getBookName(), book.getIsbn(), newRent, newRet);
		}	
		if (bookList.size() == 0) {
			System.out.println("대여한 도서가 없습니다");
			System.out.println();
		}
	}

	/**
	 * 개인정보 밑 대여중인 도서목록 출력
	 */
	public void printPersonalInfo() {
		UserDAO userDao = new UserDAO();
		UserVO userVo = new UserVO();
		
		RentBooksDAO booksDao = new RentBooksDAO();
		
		userVo = userDao.getUserData(id);
		System.out.println("[ 개인정보 출력 ]");
		System.out.println("-----------------------------------------------------------------------------------------------");
		System.out.println("아이디\t: " + userVo.getId());
		System.out.println("비밀번호\t: " + userVo.getPassword());
		System.out.println("이름\t: " + userVo.getName());
		System.out.println("생년월일\t: " + userVo.getBirthDate()); // yyyy년 mm월 dd일 형식으로
		System.out.println("이메일\t: " + userVo.getEmail());
		System.out.println("전화번호\t: " + userVo.getPhoneNum());
		
		List<BookVO> bookList = new ArrayList<>();
		bookList = booksDao.getUserRentData(userVo);
		System.out.println("-----------------------------------------------------------------------------------------------");
		printBookList(bookList);
	}
	
	/**
	 * 대여중인 도서목록 출력
	 */
	public void printRentalBookList() {
		UserVO userVo = new UserVO();
		RentBooksDAO booksDao = new RentBooksDAO();
		
		userVo.setId(id);
		List<BookVO> bookList = new ArrayList<>();
		
		bookList = booksDao.getUserRentData(userVo);
		printBookList(bookList);
	}
	
	/**
	 * 데이터 수정
	 */
	public void userDataUpdate() {
		UserDAO dao = new UserDAO();
		UserVO beforeInfo = dao.getUserData(id);
		
		System.out.println("===============================================================================================");
		System.out.println("개인정보를 변경하려면 자신의 비밀번호를 입력해주세요.");
		String pass = scanStr("비밀번호 :");
		if (!beforeInfo.getPassword().equals(pass)) {
			System.out.println("비밀번호가 다릅니다.");
			return;
		}
		
		System.out.println("-----------------------------------------------------------------------------------------------");
		System.out.println("입력한 정보로 개인정보가 수정됩니다. 입력하지 않을경우 기존 정보가 유지됩니다.");
		String password = scanStr("비밀번호 :");
		String name = scanStr("이름 : ");
		
		
		String birthDateIpt = null;
		int birthDate = 0;
		while (true) {
			birthDateIpt = scanStr("생년월일 : ");
			if (birthDateIpt == "") {
				break;
			}
			
			if (birthDateIpt.matches("^(10|20)\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])$")) {
				birthDate = Integer.parseInt(birthDateIpt);
				break;
			} else {
				System.out.println("잘못된 입력입니다.");
			}
		}
		
		String email = scanStr("이메일 : ");

		
		/////////////////////////////
		String phoneNum = "";
		while (true) {
		
			phoneNum = scanStr("전화번호 11자리 입력 : ");
			if (phoneNum == "") {
				break;
			}
			// 숫자이면서 11자릿수일때만 true 반환하는 정규표현식
			if (phoneNum.matches("\\d{11}")) {
				break;
			}
			System.out.println("잘못된 입력입니다.");
		}
		/////////////////////////////
		
		password = (password == "") ? beforeInfo.getPassword() : password;
		name = (name == "") ? beforeInfo.getName() : name;
		birthDate = (birthDateIpt == "") ? beforeInfo.getBirthDate() : birthDate;
		email = (email == "") ? beforeInfo.getEmail() : email;
		phoneNum = (phoneNum == "") ? beforeInfo.getPhoneNum() : phoneNum;

		
		UserVO vo = new UserVO(id, password, name, birthDate, email, phoneNum);
		dao.userDataUpdate(vo);
		System.out.println("-----------------------------------------------------------------------------------------------");
		System.out.println("변경된 정보");
		vo.setId(id);
		vo = dao.getUserData(id);
		System.out.println("-----------------------------------------------------------------------------------------------");
		System.out.println("아이디\t: " + vo.getId() + " (변경불가)");
		System.out.println("비밀번호\t: " + vo.getPassword());
		System.out.println("이름\t: " + vo.getName());
		System.out.println("생년월일\t: " + vo.getBirthDate()); // yyyy년 mm월 dd일 형식으로
		System.out.println("이메일\t: " + vo.getEmail());
		System.out.println("전화번호\t: " + vo.getPhoneNum());
		
	}
	
}











