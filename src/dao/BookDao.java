package dao;

import java.sql.*;
import model.Book;
import utils.toolUtil;

public class BookDao {
	
	// 图书添加
	public int add(Connection con,Book book)throws Exception{
		 
		String sql="insert into book(bookName,author,publish,price,number,status,remark) values(?,?,?,?,?,?,?)";
		PreparedStatement pstmt=(PreparedStatement) con.prepareStatement(sql);
		pstmt.setString(1, book.getBookName());		
		pstmt.setString(2, book.getAuthor());
		pstmt.setString(3, book.getPublish());
		pstmt.setDouble(4, book.getPrice());
		pstmt.setInt(5, book.getNumber());
		pstmt.setInt(6,1);//状态1，则将上架状态存到Vector集合中
		pstmt.setString(7, book.getRemark());
		
		return pstmt.executeUpdate();		
	}
	
	// 图书信息查询
	public ResultSet list(Connection con,Book book)throws Exception{
		StringBuffer sb=new StringBuffer("select * from book");
		if(book.getBookId() != null){
			sb.append(" where bookId="+book.getBookId());
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		return pstmt.executeQuery();				
	}
	
	// 图书信息查询(学生)
	public ResultSet listCan(Connection con,Book book)throws Exception{
		StringBuffer sb=new StringBuffer("select * from book");
		if(book.getBookId() != null){
			sb.append(" where bookId="+book.getBookId());
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		return pstmt.executeQuery();
				
	}

	//图书信息修改
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
