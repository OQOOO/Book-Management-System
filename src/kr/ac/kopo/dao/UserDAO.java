package kr.ac.kopo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.ac.kopo.util.ConnectionFactory;
import kr.ac.kopo.vo.UserVO;

public class UserDAO {
	
	public boolean idChack(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ID, PASSWORD ");
		sql.append("FROM USER_INFO ");
		sql.append("WHERE ID = ? ");
		int chackCnt = 0;
		
		try (
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			// 라인이 세어지면 중복
			while (rs.next()) {
				++ chackCnt;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 기존에 없으면 true 있으면 false
		return chackCnt == 0 ? true : false;
	}

	public void userDataInsert(UserVO vo) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO USER_INFO(ID, PASSWORD, NAME, ");
		sql.append("BIRTH_DATE, EMAIL, PHONE_NUM)");
		sql.append("VALUES(?, ?, ? ");
		sql.append(			", to_date(?, 'YYYYMMDD'), ?, ?)");
		
		try (
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getName());
			pstmt.setInt(4, vo.getBirthDate()); // 날자값으로 바꿔야함
			pstmt.setString(5, vo.getEmail());
			pstmt.setString(6, vo.getPhoneNum());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public UserVO userLogIn(UserVO vo) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ID, PASSWORD, ADMIN_RIGHT ");
		sql.append("FROM USER_INFO ");
		sql.append("WHERE ID = ? AND PASSWORD = ? ");
		int chackCnt = 0;
		
		try (
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPassword());
			
			ResultSet rs = pstmt.executeQuery();
			
			// 비밀번호 확인
			while (rs.next()) {
				++ chackCnt;
				vo.setAdminRight(rs.getInt(3));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return chackCnt == 1 ? vo : null;
	}
	
	public UserVO getUserData(String id) {
		
		UserVO userVo = new UserVO();
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * ");
		sql.append("FROM USER_INFO ");
		sql.append("WHERE ID = ? ");
		int chackCnt = 0;
		
		try (
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			rs.next();
			userVo.setId(rs.getString("ID"));
			userVo.setPassword(rs.getString("PASSWORD"));
			userVo.setName(rs.getString("NAME"));
			
			// 생일 처리
			String sb = rs.getString(4);
			sb = sb.split(" ")[0];
			sb = sb.replace("-", "");
			userVo.setBirthDate(Integer.parseInt(sb));
			
			userVo.setEmail(rs.getString("EMAIL"));
			userVo.setPhoneNum(rs.getString("PHONE_NUM"));

			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		return userVo;
	}
	
	public void userDataUpdate(UserVO vo) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE USER_INFO ");
		sql.append("SET PASSWORD = ?, NAME = ?, BIRTH_DATE = to_date(?, 'YYYYMMDD'), EMAIL = ?, PHONE_NUM = ? ");
		sql.append("WHERE ID = ? ");
		
		try (
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){
			pstmt.setString(1, vo.getPassword());
			pstmt.setString(2, vo.getName());
			pstmt.setInt(3, vo.getBirthDate()); // 날자값으로 바꿔야함
			pstmt.setString(4, vo.getEmail());
			pstmt.setString(5, vo.getPhoneNum());
			pstmt.setString(6, vo.getId());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 데이터 삭제
	public void userDataRemove(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM USER_INFO ");
		sql.append("WHERE ID = ? ");
		
		try (
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<UserVO> adminSearchMembers(UserVO vo) {
		
		List<UserVO> userList = new ArrayList<>();
		
		String id = vo.getId();
		String name = vo.getName();
		String email = vo.getEmail();
		
		String andOr = (id == "" || name == "") ? "OR" : "AND";
		String andOr2 = (name == "" || email == "") ? "OR" : "AND";
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * ");
		sql.append("FROM USER_INFO ");
		sql.append("WHERE ID = ? " + andOr + " NAME = ? " + andOr2 + " EMAIL = ? ");
		
		try (
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, email);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				UserVO userVo = new UserVO();
				
				userVo.setId(rs.getString(1));
				userVo.setPassword(rs.getString(2));
				userVo.setName(rs.getString(3));
				
				// 전화번호 처리
				String temp = rs.getString(4).split(" ")[0].replace("-", "");
				userVo.setBirthDate(Integer.parseInt(temp));
				
				userVo.setEmail(rs.getString(5));
				userVo.setPhoneNum(rs.getString(6));
				userVo.setAdminRight(rs.getInt(7));
	
				userList.add(userVo);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userList;
	}
	
	public List<UserVO> adminSearchAllMembers() {
		
		List<UserVO> userList = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * ");
		sql.append("FROM USER_INFO ");
		
		try (
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		) {
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				UserVO userVo = new UserVO();
				
				userVo.setId(rs.getString(1));
				userVo.setPassword(rs.getString(2));
				userVo.setName(rs.getString(3));
				
				// 전화번호 처리
				String temp = rs.getString(4).split(" ")[0].replace("-", "");
				userVo.setBirthDate(Integer.parseInt(temp));
				
				userVo.setEmail(rs.getString(5));
				userVo.setPhoneNum(rs.getString(6));
				userVo.setAdminRight(rs.getInt(7));
	
				userList.add(userVo);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userList;
	}
	
	
	
	public void giveAdminRights(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE USER_INFO ");
		sql.append("SET ADMIN_RIGHT = 1 ");
		sql.append("WHERE ID = ? ");
		
		try (
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){
			pstmt.setString(1, id);
			pstmt.executeQuery();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
