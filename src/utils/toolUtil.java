package utils;

import java.sql.*;
import java.text.SimpleDateFormat;


public class toolUtil {
	//�ж��ַ����Ƿ�Ϊ��
	public static boolean isEmpty(String str) {
		if(str!=null && !"".equals(str.trim())) {
			return false;
		}
		return true;
	}
	//���ڻ�ȡ��ǰʱ��
	public static Long getTime() {
		long time=System.currentTimeMillis();
		return time;
	}
	//���ڶ�ʱ����и�ʽ��
	public static String getDateByTime(Long time) {
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String string=format.format(new Date(time));
		return string;
	}
}
