package dao;

import java.sql.*;


import utils.toolUtil;
import model.User;

public class UserDao {
	
	//登录
	public User login(Connection con,User user)throws Exception {
				
		// 查询用户名是否存在
		String sql = "select * from user where userName=? ";
		PreparedStatement pstmt = con.prepareStatement(sql);// 预处理对象
		pstmt.setString(1, user.getUserName());// 设置通配符代表的具体的值		
		ResultSet rs = pstmt.executeQuery();// 执行查询语句
		if (rs.next()) {
			user.setPassword(rs.getString("password"));//获取当前用户密码
			user.setUserId(rs.getInt("userId"));//获取当前用户id
			user.setRole(rs.getInt("role"));//获取当前用户类型
			return user;//返回登录用户信息
		}
		else {
			return null;
		}
	}
	
	//注册
	public int addUser(Connection con,User user) throws Exception{
		//查询注册用户名是否存在
		String sql = "select * from user where userName=? ";
	    PreparedStatement pstmt = con.prepareStatement(sql);//预处理对象
		pstmt.setString(1,user.getUserName());//设置通配符代表的具体的值
	    ResultSet rs = pstmt.executeQuery();//执行查询语句
	    if(rs.next()){
	    	return 2;//如果用户名存在，返回2
	    }
	    //如果用户名不存在，添加到用户表
	    sql="insert into user (userName,password,role,sex,phone) values (?,?,?,?,?)";
	    PreparedStatement pstmt2=con.prepareStatement(sql);//预处理对象
		pstmt2.setString(1, user.getUserName());//设置通配符代表的具体的值
		pstmt2.setString(2, user.getPassword());
		pstmt2.setInt(3, user.getRole());
		pstmt2.setString(4,user.getSex());
		pstmt2.setString(5,user.getPhone());
		return pstmt2.executeUpdate();//返回受影响的记录条数，这里是插入一条记录，因此返回值为1
	}
	
	//用户信息查询
	public ResultSet list(Connection con,User user)throws Exception{
		StringBuffer sb=new StringBuffer("select * from user");
		if(user.getUserId() != null){
			sb.append(" where userId="+user.getUserId());
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		return pstmt.executeQuery();		
	}
	//用户信息修改
	public int update(Connection con,User user)throws Exception{
		String sql="update user set userName=?,password=?,role=?,sex=?,phone=? where userId=?";
		PreparedStatement pstmt=(PreparedStatement) con.prepareStatement(sql);
		pstmt.setString(1, user.getUserName());		
		pstmt.setString(2, user.getPassword());
		pstmt.setInt(3, 1);
		pstmt.setString(4,user.getSex() );
		pstmt.setString(5, user.getPhone());
		pstmt.setInt(6, user.getUserId());
		return pstmt.executeUpdate();	
	}	
	
	
}

