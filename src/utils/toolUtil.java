package utils;

import java.sql.*;
import java.text.SimpleDateFormat;


public class toolUtil {
	//判断字符串是否为空
	public static boolean isEmpty(String str) {
		if(str!=null && !"".equals(str.trim())) {
			return false;
		}
		return true;
	}
	//用于获取当前时间
	public static Long getTime() {
		long time=System.currentTimeMillis();
		return time;
	}
	//用于对时间进行格式化
	public static String getDateByTime(Long time) {
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String string=format.format(new Date(time));
		return string;
	}
}
