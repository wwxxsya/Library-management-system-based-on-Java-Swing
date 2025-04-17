package dao;

import java.sql.*;

import JFrame.LoginFrm;
import model.BorrowDetail;
import utils.toolUtil;

public class BorrowDetailDao {
	//������Ϣ��ѯ
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
	
    //����
	public int add(Connection con, BorrowDetail borrowDetail) throws Exception {
		
		// ���ݱ�Ų�ѯ�������飬���½���״̬Ϊ�ѽ衢���½���ʱ��
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
			System.err.println("�洢�����¼����!");
			System.out.println(e);
			return -1;
		}

	}
    //����
	public int returnBook(Connection con,BorrowDetail detail)throws Exception {
		//���ݱ�Ų�ѯ�������飬���½���״̬Ϊ�ѻ������¹黹ʱ��
		String sql = "update borrowdetail set status = ? ,returnTime = ? where borrowId = ?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, detail.getStatus());
		pstmt.setLong(2, detail.getReturnTime());
		pstmt.setInt(3, detail.getBorrowId());
		return pstmt.executeUpdate();
	}
}
