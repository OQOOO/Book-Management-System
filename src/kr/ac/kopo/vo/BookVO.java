package kr.ac.kopo.vo;

public class BookVO {

	private String isbn;
	private String bookName;
	private String writer;
	private String publisher;
	private String rentUserId;
	private int rentalDate;
	private int returnDate;
	
	public BookVO() {
		
	}
	
	// 도서 정보
	public BookVO(String isbn, String bookName, String writer, String publisher, String rentUserId) {
		super();
		this.isbn = isbn;
		this.bookName = bookName;
		this.writer = writer;
		this.publisher = publisher;
		this.rentUserId = rentUserId;
	}

	// 대여 정보
	public BookVO(String isbn, String rentUserId, String bookName, int rentalDate, int returnDate) {
		super();
		this.isbn = isbn;
		this.rentUserId = rentUserId;
		this.bookName = bookName;
		this.rentalDate = rentalDate;
		this.returnDate = returnDate;
	}

	public String getIsbn() {
		return isbn;
	}
	
	public String getBookName() {
		return bookName;
	}
	
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public String getWriter() {
		return writer;
	}
	
	public void setWriter(String writer) {
		this.writer = writer;
	}
	
	public String getPublisher() {
		return publisher;
	}
	
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getRentUserId() {
		return rentUserId;
	}

	public void setRentUserId(String rentUserId) {
		this.rentUserId = rentUserId;
	}

	public int getRentalDate() {
		return rentalDate;
	}

	public void setRentalDate(int rentalDate) {
		this.rentalDate = rentalDate;
	}

	public int getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(int returnDate) {
		this.returnDate = returnDate;
	}

	@Override
	public String toString() {
		return "BookVO [isbn=" + isbn + ", bookName=" + bookName + ", writer=" + writer + ", publisher=" + publisher
				+ ", rentUserId=" + rentUserId + ", rentalDate=" + rentalDate + ", returnDate=" + returnDate + "]";
	}

	
	
	
}
