package kr.ac.kopo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.ac.kopo.util.ConnectionFactory;
import kr.ac.kopo.vo.BookVO;
import kr.ac.kopo.vo.UserVO;

public class RentBooksDAO {

	public List<BookVO> getUserRentData(UserVO vo) {
		
		List<BookVO> bookList = new ArrayList<>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * ");
		sql.append("FROM USER_RENTED ");
		sql.append("WHERE ID = ? ");
		
		try (
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){
			pstmt.setString(1, vo.getId());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				/////////
				BookVO book = new BookVO();
				book.setIsbn(rs.getString(1));
				book.setRentUserId(rs.getString(2));
				book.setBookName(rs.getString(3));
				
				// 대여일 처리
				String rd = rs.getString(4);
				rd = rd.split(" ")[0];
				rd = rd.replace("-", "");
				book.setRentalDate(Integer.parseInt(rd));
				
				// 반납일 처리
				String re = rs.getString(5);
				re = re.split(" ")[0];
				re = re.replace("-", "");
				book.setReturnDate(Integer.parseInt(re));
				
				/////////
				
				bookList.add(book);	
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		return bookList;
	}
	
	public void returnBook(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE USER_RENTED ");
		sql.append("WHERE ID = ? ");
		
		try (
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
				) {
			pstmt.setString(1, id); //////////////////////asdfjoasifjlsa;hf
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
