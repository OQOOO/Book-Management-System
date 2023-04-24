package kr.ac.kopo.ui;

import kr.ac.kopo.dao.UserDAO;
import kr.ac.kopo.vo.UserVO;

public class LogInUI extends BaseUI {
	@Override
	protected void execution() throws Exception {
		UserDAO dao = new UserDAO();
		
		for(int i = 0; i < 5; ++i) {
			UserVO vo = new UserVO();
			// 아이디 입력
			System.out.println("로그인을 위해 아이디와 비밀번호를 입력하세요.");
			String id = scanStr("아이디 : ");
			String pw = scanStr("비밀번호 : ");
			
			//
			vo.setId(id);
			vo.setPassword(pw);
			
			vo = dao.userLogIn(vo);
			
			if (vo != null) {
				if (vo.getAdminRight() == 1) {
					new AdminUI(vo.getId()).execution();
					break;
				}
				new AfterLoginLibrayUI(vo.getId()).execution();
				break;
			} else if (i < 4){
				System.out.println("해당 유저 정보가 없습니다. 다시 입력하세요.");
			}
		}
		
		// 로그아웃
		System.out.println("로그아웃 되었습니다.");
		System.out.println("");
		scanStr("(엔터를 입력하면 다음으로 진행합니다.)");
	}
}
