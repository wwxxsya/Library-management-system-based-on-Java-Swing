package dao;

import java.sql.*;

import JFrame.LoginFrm;
import model.BorrowDetail;
import utils.toolUtil;

public class BorrowDetailDao {
	//借阅信息查询
	public ResultSet list(Connection con,BorrowDetail borrowDetail)throws Exception{
		StringBuffer sb=new StringBuffer("SELECT bd.*,u.userName,b.bookName from borrowdetail bd,user u,book b where u.userId=bd.userId and b.bookId=bd.bookId");
		
		if(borrowDetail.getUserId() != null){
			sb.append(" and u.userId = ?");
		}
		if(borrowDetail.getStatus() != null){
			sb.append(" and bd.status = ?");
		}
		if(borrowDetail.getBookId() != null){
			sb.append(" and bd.bookId = ?");
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		if(borrowDetail.getUserId() != null){
			pstmt.setInt(1, borrowDetail.getUserId());
		}
		
		if(borrowDetail.getStatus() != null && borrowDetail.getBookId() != null){
			pstmt.setInt(2, borrowDetail.getStatus());
			pstmt.setInt(3, borrowDetail.getBookId());
		}
		
		return pstmt.executeQuery();
	}
	
    //借书
	public int add(Connection con, BorrowDetail borrowDetail) throws Exception {
		
		// 根据编号查询借阅详情，更新借阅状态为已借、更新借书时间
		String sql = "insert into borrowdetail values(0,?,?,?,?,0)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		try {
			pstmt = con.prepareStatement(sql);	
			pstmt.setInt(1,borrowDetail.getUserId());
			pstmt.setInt(2,borrowDetail.getBookId());
			pstmt.setInt(3,borrowDetail.getStatus());			
			pstmt.setLong(4,borrowDetail.getBorrowTime());
			
			pstmt.executeUpdate();
			return 0;
		} catch (SQLException e) {
			System.err.println("存储借书记录出错!");
			System.out.println(e);
			return -1;
		}

	}
    //还书
	public int returnBook(Connection con,BorrowDetail detail)throws Exception {
		//根据编号查询借阅详情，更新借阅状态为已还、更新归还时间
		String sql = "update borrowdetail set status = ? ,returnTime = ? where borrowId = ?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, detail.getStatus());
		pstmt.setLong(2, detail.getReturnTime());
		pstmt.setInt(3, detail.getBorrowId());
		return pstmt.executeUpdate();
	}
}
