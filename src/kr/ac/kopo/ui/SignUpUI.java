package kr.ac.kopo.ui;

import kr.ac.kopo.dao.UserDAO;
import kr.ac.kopo.vo.UserVO;

public class SignUpUI extends BaseUI{
	
	protected String id() {
		while (true) {
			String id = scanStr("아이디 : ");
			if (id == "") {
				System.out.println("아이디를 입력해주세요");
				continue;
			}
			
			boolean tf = new UserDAO().idChack(id);
			if (tf == true) {
				return id;
			} else {
				System.out.println("중복된 아이디입니다. 다른 아이디를 입력해주세요");
			}
		}
	}
	
	protected String password() {
		for (int i = 0; i < 5; ++i) {
			String password = null;
			password = scanStr("비밀번호 : ");
			if (password == "") {
				System.out.println("비밀번호를 입력해주세요. ");
				continue;
			}
			String check = scanStr("한번 더 입력해주세요 : ");
			
			if (password.equals(check)) {
				return password;
			}
			System.out.println("잘못 입력했습니다. 다시 입력해주세요.");
		}
		return null;
	}
	
	// 어드민 전용
	protected String passwordAdminInsert() {
		while (true) {
			String pw = scanStr("비밀번호 : ");
			if (pw == null) {
				System.out.println("비밀번호를 입력해주세요. ");
				continue;
			}
			return pw;
		}
	}
	
	protected String userName() {
		while (true) {
			String name = scanStr("이름 : ");
			if (name == "") {
				System.out.println("이름을 입력해주세요.");
				continue;
			}
			return name;
		}
	}
	
	protected int birthDate() {
		String birthDateIpt = null;
		for (int i = 0; i < 5; ++i) {
			birthDateIpt = scanStr("생년월일 : ");
			if (birthDateIpt == "") {
				break;
			}
			
			if (birthDateIpt.matches("^(18|19|20)\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])$")) {
				return Integer.parseInt(birthDateIpt);
				
			} else {
				System.out.println("잘못된 입력입니다.");
			}
		}
		return -1;
	}
	
	protected String email() {
		return scanStr("이메일 : ");
	}
	
	protected String phoneNum() {
		
		String phoneNum = null;
		for (int i = 0; i < 5; ++i) {
		
			phoneNum = scanStr("전화번호 11자리 입력 : ");
			if (phoneNum.matches("\\d{11}")) {
					return phoneNum;
			}
			System.out.println("잘못된 입력입니다.");
		}
		
		return null;
	}

	protected void execution() {
		UserVO vo = new UserVO();
		
		System.out.println("회원가입을 위해 다음 목록들을 입력해주세요.");
		System.out.println("-----------------------------------------------------------------------------------------------");
		
		String id = id();
		String password = password();
		if (password == null) {
			System.out.println("회원가입이 취소되었습니다. 기억할 수 있는 비밀번호를 사용해주세요.");
			return;
		}
		
		String userName = userName();
		int birthDate = birthDate();
		if (birthDate == -1) {
			System.out.println("회원가입이 취소되었습니다. 옳바른 생년월일을 입력해주세요.");
		}
		
		String email = email();
		String phoneNum = phoneNum();
		if (phoneNum == null) {
			System.out.println("회원가입이 취소되었습니다. 옳바른 전화번호를 입력해주세요.");
		}
		
		//
		vo.setId(id);
		vo.setPassword(password);
		vo.setName(userName);
		vo.setBirthDate(birthDate);
		vo.setEmail(email);
		vo.setPhoneNum(phoneNum);
		
		// 데이터베이스 입력
		new UserDAO().userDataInsert(vo);
		
		System.out.println("회원 가입이 완료되었습니다.");
	}
	
	public void adminAdd() {
		UserVO vo = new UserVO();
		
		System.out.println("회원 등록을 위해 다음 목록들을 입력해주세요.");
		System.out.println("===============================================================================================");
		String id = id();
		String password = passwordAdminInsert();
		String userName = userName();
		int birthDate = birthDate();
		String email = email();
		String phoneNum = phoneNum();
		
		//
		vo.setId(id);
		vo.setPassword(password);
		vo.setName(userName);
		vo.setBirthDate(birthDate);
		vo.setEmail(email);
		vo.setPhoneNum(phoneNum);
		
		// 데이터베이스 입력
		new UserDAO().userDataInsert(vo);
	}
}
