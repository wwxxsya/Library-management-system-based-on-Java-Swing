package JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import dao.BookDao;
import dao.BorrowDetailDao;
import dao.UserDao;
import model.Book;
import model.BorrowDetail;
import model.User;
import utils.DbUtil;
import utils.toolUtil;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

public class AdminBorrowInfo extends JFrame {
	private JFrame jf;
	private JTextField textField;
	private JTable table;
	private DefaultTableModel model;
	private JTable BookTable;
	private DefaultTableModel BookModel;
	private JButton btnBackBook;
	DbUtil dbUtil = new DbUtil();
	BorrowDetailDao bdetailDao = new BorrowDetailDao();
	BookDao bookDao = new BookDao();
	UserDao userDao=new UserDao();
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JButton button;
	
	private JPanel panel_2;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_5;
	private JTextField textField_6;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JTable table_2;
	private DefaultTableModel model_2;

	public AdminBorrowInfo() {
		jf = new JFrame("管理员界面");
		jf.setBounds(400, 50, 600, 672);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.getContentPane().setLayout(null);// 设置布局管理器，null表示不使用

		JMenuBar menuBar = new JMenuBar();// 创建菜单栏组件
		jf.setJMenuBar(menuBar);// 向jf窗口容器中加入菜单栏组件menuBar

		JMenu mnNewMenu_2 = new JMenu("书籍管理");// 创建菜单组件
		menuBar.add(mnNewMenu_2);// 将菜单组件mnNewMenu_2加入到菜单栏组件menuBar中

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("书籍添加");// 创建菜单项组件
		/*
		 * 编写菜单项组件mntmNewMenuItem_2的鼠标事件监视器，来监听菜单项单击事件。
		 * MouseAdapter:用于接收鼠标事件的抽象适配器类,此抽象类定义的方法都是空的，只需要针对所关心的事件重写方法。
		 */
		mntmNewMenuItem_2.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {// 鼠标按下事件
				jf.dispose();// 撤销当前窗口，并释放当前窗口所使用的资源
				new AdminBookAdd();// 创建管理员图书添加窗口
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_2);// 将菜单项组件mntmNewMenuItem_2加入到菜单组件mnNewMenu_2中

		JMenuItem mntmNewMenuItem_3 = new JMenuItem("书籍修改");// 创建菜单项组件
		// 编写菜单项组件mntmNewMenuItem_3的鼠标事件监视器，来监听菜单项单击事件。
		mntmNewMenuItem_3.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {// 鼠标按下事件
				jf.dispose();// 撤销当前窗口，并释放当前窗口所使用的资源
				new AdminBookEdit();// 创建管理员图书修改窗口
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_3);// 将菜单项组件mntmNewMenuItem_3加入到菜单组件mnNewMenu_2中

		JMenu menu1 = new JMenu("用户管理");// 创建菜单组件
		menuBar.add(menu1);// 将菜单组件menu1加入到菜单栏组件menuBar中

		JMenuItem mntmNewMenuItem_4 = new JMenuItem("用户信息");// 创建菜单项组件
		// 编写菜单项组件mntmNewMenuItem_4的鼠标事件监视器，来监听菜单项单击事件。
		mntmNewMenuItem_4.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {// 鼠标按下事件
				jf.dispose();// 撤销当前窗口，并释放当前窗口所使用的资源
				new AdminUserInfo();// 创建管理员用户信息窗口
			}
		});
		menu1.add(mntmNewMenuItem_4);// 将菜单项组件mntmNewMenuItem_4加入到菜单组件menu1中

		JMenuItem mntmNewMenuItem_5 = new JMenuItem("借阅信息");// 创建菜单项组件
		// 编写菜单项组件mntmNewMenuItem_5的鼠标事件监视器，来监听菜单项单击事件。
		mntmNewMenuItem_5.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {// 鼠标按下事件
				jf.dispose();// 撤销当前窗口，并释放当前窗口所使用的资源
				new AdminBorrowInfo();// 创建管理员借阅信息窗口
			}
		});
		menu1.add(mntmNewMenuItem_5);// 将菜单项组件mntmNewMenuItem_5加入到菜单组件menu1中

		JMenu mnNewMenu_1 = new JMenu("退出系统");// 创建菜单组件
		// 编写菜单组件mnNewMenu_1的鼠标事件监视器，来监听菜单单击事件。
		mnNewMenu_1.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {// 鼠标按下事件
				// 弹出一个显示"欢迎再次使用"，并带有“确定”按钮的模态对话框
				JOptionPane.showMessageDialog(null, "欢迎再次使用");
				jf.dispose();// 撤销当前窗口，并释放当前窗口所使用的资源
			}
		});
		menuBar.add(mnNewMenu_1);// 将菜单组件mnNewMenu_1加入到菜单栏组件menuBar中

		jf.setVisible(true);// 设置管理员界面窗口可见
		jf.setResizable(true);// 设置管理员界面窗口可调整大小
		
		JPanel panel_1 = new JPanel();// 创建一个面板组件
		panel_1.setLayout(null);// 设置布局管理器，null表示不使用
		// 设置面板组件的边框
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "借阅信息", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(255, 0, 0)));
		panel_1.setBounds(20, 10, 541, 275);// 设置面板组件的坐标位置，以及宽、高

		/* 1、做一个表头栏数据 一维数组 */
		String[] title = { "借书人", "书名", "状态", "结束时间", "还书时间" };
		/* 具体的各栏行记录 先用空的二位数组占位 */
		String[][] dates = {};

		/* 2、然后实例化 上面2个控件对象 */
		// DefaultTableModel是 TableModel的一个实现，它使用一个 Vector来存储单元格的值对象，该 Vector由多个
		// Vector组成。
		model = new DefaultTableModel(dates, title);// 构建一个DefaultTableModel，并通过将 datas和 title传递到 setDataVector方法来初始化该表。
		table = new JTable(model);// 创建默认的表格模型，将此表的数据模型设置为 model，并将其注册为新数据模型的侦听器通知。

		/* 3、调用putDates方法，将从数据库获取到的图书信息数据放置到table中 */
		putDates_check(new BorrowDetail());// 获取数据库数据放置table中

		panel_1.setLayout(null);// 设置面板组件的布局管理器，null表示不使用
		JScrollPane jscrollpane = new JScrollPane();// 创建一个带有滚动条的面板
		jscrollpane.setBounds(20, 22, 496, 234);// 设置滚动面板组件的坐标位置，以及宽、高
		jscrollpane.setViewportView(table);// 将table放置到滚动面板中显示
		panel_1.add(jscrollpane);// 将滚动面板组件jscrollpane添加到面板组件panel_1中
		jf.getContentPane().add(panel_1);// 将面板组件panel_1添加到窗体面板中
	
	}
	
	
	
		// 从数据库中查询该登录用户的借阅信息列表
	private void putDates_check(BorrowDetail borrowDetail) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();// 返回 DefaultTableModel数据模型
		model.setRowCount(0);// 设置模型中的行数
		//Integer userId = LoginFrm.currentUser.getUserId();// 获取LoginFrm类变量currentUser中的用户id
		
		Connection con = null;
		try {
			con = dbUtil.getConnection();// 获取连接
			//borrowDetail.setUserId(userId);// 设置borrowDetail对象的用户id为userId
			// 调用bdetailDao的list方法，从数据库中查询该登录用户的借阅信息
			
			ResultSet list = bdetailDao.list(con, borrowDetail);
			while (list.next()) {
				// 集合类，继承自 AbstractList，实现了 List 接口 ，底层是数组结构，元素可重复，有序（存放顺序），支持下标索引访问，允许null元素
				Vector rowData = new Vector();// 创建Vector集合			
				rowData.add(list.getInt("userId"));
				rowData.add(list.getString("bookName"));// 将书名存到Vector集合中
				int status = list.getInt("status");// 获取借阅状态
				if (status == 1) {
					rowData.add("在借");// 将借阅状态存到Vector集合中
				}
				if (status == 2) {
					rowData.add("已还");// 将借阅状态存到Vector集合中
				}
				// 调用“工具类”将借书时间进行格式化，并存到Vector集合中
				rowData.add(toolUtil.getDateByTime(list.getLong("borrowTime")));
				// 调用“工具类”将还书时间进行格式化，并存到Vector集合中
				if (status == 2) {
					rowData.add(toolUtil.getDateByTime(list.getLong("returnTime")));
				}
				model.addRow(rowData);// 添加一行到模型的结尾
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeCon(con);// 关闭连接
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
