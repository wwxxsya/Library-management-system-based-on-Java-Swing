package model;

public class BorrowDetail {
	private Integer borrowId;
	private Integer userId;
	private Integer bookId;
	private Integer status;
	private long borrowTime;
	private long returnTime;
	public Integer getBorrowId() {
		return borrowId;
	}
	public void setBorrowId(Integer borrowId) {
		this.borrowId = borrowId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public long getBorrowTime() {
		return borrowTime;
	}
	public void setBorrowTime(long borrowTime) {
		this.borrowTime = borrowTime;
	}
	public long getReturnTime() {
		return returnTime;
	}
	public void setReturnTime(long returnTime) {
		this.returnTime = returnTime;
	}
	
}
