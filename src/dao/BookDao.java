package dao;

import java.sql.*;
import model.Book;
import utils.toolUtil;

public class BookDao {
	
	// ͼ�����
	public int add(Connection con,Book book)throws Exception{
		 
		String sql="insert into book(bookName,author,publish,price,number,status,remark) values(?,?,?,?,?,?,?)";
		PreparedStatement pstmt=(PreparedStatement) con.prepareStatement(sql);
		pstmt.setString(1, book.getBookName());		
		pstmt.setString(2, book.getAuthor());
		pstmt.setString(3, book.getPublish());
		pstmt.setDouble(4, book.getPrice());
		pstmt.setInt(5, book.getNumber());
		pstmt.setInt(6,1);//״̬1�����ϼ�״̬�浽Vector������
		pstmt.setString(7, book.getRemark());
		
		return pstmt.executeUpdate();		
	}
	
	// ͼ����Ϣ��ѯ
	public ResultSet list(Connection con,Book book)throws Exception{
		StringBuffer sb=new StringBuffer("select * from book");
		if(book.getBookId() != null){
			sb.append(" where bookId="+book.getBookId());
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		return pstmt.executeQuery();				
	}
	
	// ͼ����Ϣ��ѯ(ѧ��)
	public ResultSet listCan(Connection con,Book book)throws Exception{
		StringBuffer sb=new StringBuffer("select * from book");
		if(book.getBookId() != null){
			sb.append(" where bookId="+book.getBookId());
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		return pstmt.executeQuery();
				
	}

	//ͼ����Ϣ�޸�
	public int update(Connection con,Book book)throws Exception{
		String sql="update book set bookName=?,author=?,publish=?,price=?,number=?,status=?,remark=? where bookId=?";
		PreparedStatement pstmt=(PreparedStatement) con.prepareStatement(sql);
		pstmt.setString(1, book.getBookName());		
		pstmt.setString(2, book.getAuthor());
		pstmt.setString(3, book.getPublish());
		pstmt.setDouble(4, book.getPrice());
		pstmt.setInt(5, book.getNumber());
		pstmt.setInt(6, book.getStatus());
		pstmt.setString(7, book.getRemark());
		pstmt.setInt(8, book.getBookId());
		return pstmt.executeUpdate();
	}
	
}
