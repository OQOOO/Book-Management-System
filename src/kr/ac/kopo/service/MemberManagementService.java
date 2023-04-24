package kr.ac.kopo.service;

import java.util.List;

import kr.ac.kopo.dao.LibraryBooksDAO;
import kr.ac.kopo.dao.RentBooksDAO;
import kr.ac.kopo.dao.UserDAO;
import kr.ac.kopo.ui.BaseUI;
import kr.ac.kopo.ui.SignUpUI;
import kr.ac.kopo.vo.UserVO;

public class MemberManagementService extends BaseUI{

	@Override
	protected void execution() throws Exception {
		
	}
	
	public void addMembers() {
		
		SignUpUI ui = new SignUpUI();
		int addCnt = 0;
		while (true) {
			ui.adminAdd();
			++ addCnt;
			System.out.println("-----------------------------------------------------------------------------------------------");
			String stop = scanStr("1 입력시 중단. 그 외 계속 : ");
			if (stop.equals("1")) {
				System.out.println("-----------------------------------------------------------------------------------------------");
				System.out.println(addCnt + "명의 회원이 추가되었습니다.");
				return;
			}
		}
		
	}

	public void removeMember() {
		
		UserDAO dao = new UserDAO();
		System.out.println("삭제할 회원의 아이디를 입력해주세요");
		String id = scanStr("아이디 : ");
		
		if (dao.idChack(id) == true) {
			System.out.println("해당 아이디가 존재하지 않습니다.");
			return;
		} else {
			dao.userDataRemove(id);
			new RentBooksDAO().returnBook(id);
			new LibraryBooksDAO().returnBookWithId(id);
			// 빌려간 책 정보도 삭제
			System.out.println("삭제가 완료되었습니다.");
		}
	}

	public void searchMembers() {
		
		// 책검색처럼 변경
		List<UserVO> userList;
		UserDAO dao = new UserDAO();
		UserVO vo = new UserVO();
		
		System.out.println("해당하는 유저의 정보를 입력하세요. (입력 없을시 모두 탐색)");
		String id = scanStr("아이디 : ");
		String name = scanStr("이름 : ");
		String email = scanStr("이메일 : ");
		
		if (id == "" && name == "" && email == "") {
			userList = dao.adminSearchAllMembers();
		} else {
			vo.setId(id);
			vo.setName(name);
			vo.setEmail(email);
			userList = dao.adminSearchMembers(vo);
		}

		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("[ 해당하는 유저 목록 ]");
		System.out.printf("%-20s\t%-20s\t%-20s\t%-20s\t%-20s\t%-20s\t%-20s\n", "아이디", "비밀번호", "이름", "생년월일", "전화번호", "이메일", "관리자권한");
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");
		for(UserVO u : userList) {
			System.out.printf("%-20s\t%-20s\t%-20s\t%-20s\t%-20s\t%-20s\t%-20s\n"
					, u.getId(), u.getPassword(), u.getName(), u.getBirthDate(), u.getPhoneNum(), u.getEmail()
					, (u.getAdminRight() == 1 ? "[ 관리자 ]" : "일반 사용자"));
		}
		
	}

	public void givingAdminRights() {
		UserDAO dao = new UserDAO();
		System.out.println("관리자 권한을 부여할 사용자의 아이디를 입력하세요");
		String id = scanStr("아이디 : ");
		boolean chack = dao.idChack(id);
		if (chack == true) {
			System.out.println("존재하지 않는 아이디입니다.");
			return;
		}
		dao.giveAdminRights(id);
		System.out.println("관리자 권한 부여가 완료되었습니다.");
		
	}
	
}
