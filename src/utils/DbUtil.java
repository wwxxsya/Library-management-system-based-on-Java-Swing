package utils;

import java.sql.*;

public class DbUtil {
	private String dbDriver="com.mysql.cj.jdbc.Driver";
	private String dbUrl=
			"jdbc:mysql://localhost:3306/bookmanager?"
			+ "useSSL=true&serverTimezone=GMT%2B8&characterEncoding = utf-8";
	//CST
	private String dbUserName="root";
	private String dbPassword="81783714";
	//用于获取数据库连接
	public Connection getConnection()throws Exception{
		Class.forName(dbDriver);
		Connection con=(Connection)
				DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
		return con;
	}
	public void closeCon(Connection con)throws Exception{
		if(con!=null) {
			con.close();
		}
	}
}
