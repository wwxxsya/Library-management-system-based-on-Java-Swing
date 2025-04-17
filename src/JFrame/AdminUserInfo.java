package JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import dao.BookDao;
import dao.UserDao;
import model.Book;
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

public class AdminUserInfo extends JFrame {
	private JFrame jf;
	private JTable table;
	private DefaultTableModel model;
	DbUtil dbUtil = new DbUtil();
	UserDao userDao = new UserDao();

	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JComboBox comboBox_2;

	public AdminUserInfo() {
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
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "用户信息", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(255, 0, 0)));
		panel_1.setBounds(20, 10, 541, 275);// 设置面板组件的坐标位置，以及宽、高

		/* 1、做一个表头栏数据 一维数组 */
		String[] title = { "编号", "用户名", "密码", "性别", "电话" };
		/* 具体的各栏行记录 先用空的二位数组占位 */
		String[][] dates = {};

		/* 2、然后实例化 上面2个控件对象 */
		// DefaultTableModel是 TableModel的一个实现，它使用一个 Vector来存储单元格的值对象，该 Vector由多个
		// Vector组成。
		model = new DefaultTableModel(dates, title);// 构建一个DefaultTableModel，并通过将 datas和 title传递到 setDataVector方法来初始化该表。
		table = new JTable(model);// 创建默认的表格模型，将此表的数据模型设置为 model，并将其注册为新数据模型的侦听器通知。

		/* 3、调用putDates方法，将从数据库获取到的图书信息数据放置到table中 */
		putDates(new User());// 获取数据库数据放置table中

		panel_1.setLayout(null);// 设置面板组件的布局管理器，null表示不使用
		JScrollPane jscrollpane = new JScrollPane();// 创建一个带有滚动条的面板
		jscrollpane.setBounds(20, 22, 496, 234);// 设置滚动面板组件的坐标位置，以及宽、高
		jscrollpane.setViewportView(table);// 将table放置到滚动面板中显示
		panel_1.add(jscrollpane);// 将滚动面板组件jscrollpane添加到面板组件panel_1中
		jf.getContentPane().add(panel_1);// 将面板组件panel_1添加到窗体面板中

		// 第二部分组件
		JPanel panel_2 = new JPanel();// 创建一个面板组件
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "用户编辑", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(255, 0, 0)));
		panel_2.setBounds(20, 310, 541, 292);// 设置面板组件的坐标位置，以及宽、高
		jf.getContentPane().add(panel_2);// 将面板组件panel_2添加到窗体面板中
		panel_2.setLayout(null);// 设置布局管理器，null表示不使用

		JLabel label = new JLabel("编号：");// 标签
		label.setFont(new Font("幼圆", Font.BOLD, 14));// 设置字体
		label.setBounds(30, 30, 45, 27);// 设置标签的坐标位置，以及宽、高
		panel_2.add(label);// 将标签组件label添加到面板panel_2中

		textField_1 = new JTextField();// 文本框
		textField_1.setEditable(false);// 设置不可编辑
		textField_1.setColumns(10);// 设置文本框的长度
		textField_1.setBounds(70, 30, 100, 27);// 设置文本框的坐标位置，以及宽、高
		panel_2.add(textField_1);// 将文本框组件textField_1添加到面板panel_2中

		JLabel label_1 = new JLabel("用户名：");// 标签
		label_1.setFont(new Font("幼圆", Font.BOLD, 14));// 设置字体
		label_1.setBounds(180, 30, 60, 27);// 设置标签的坐标位置，以及宽、高
		panel_2.add(label_1);// 将标签组件label_1添加到面板panel_2中

		textField_2 = new JTextField();// 文本框
		textField_2.setColumns(10);// 设置文本框的长度
		textField_2.setBounds(242, 30, 100, 27);// 设置文本框的坐标位置，以及宽、高
		panel_2.add(textField_2);// 将文本框组件textField_2添加到面板panel_2中

		JLabel label_2 = new JLabel("密码：");// 标签
		label_2.setFont(new Font("幼圆", Font.BOLD, 14));// 设置字体
		label_2.setBounds(378, 30, 45, 27);// 设置标签的坐标位置，以及宽、高
		panel_2.add(label_2);// 将标签组件label_2添加到面板panel_2中

		textField_3 = new JTextField();// 文本框
		textField_3.setColumns(10);// 设置文本框的长度
		textField_3.setBounds(418, 30, 100, 27);// 设置文本框的坐标位置，以及宽、高
		panel_2.add(textField_3);// 将文本框组件textField_2添加到面板panel_2中

		JLabel label_3 = new JLabel("性别：");// 标签
		label_3.setFont(new Font("幼圆", Font.BOLD, 14));// 设置字体
		label_3.setBounds(30, 80, 45, 27);// 设置标签的坐标位置，以及宽、高
		panel_2.add(label_3);// 将标签组件label_3添加到面板panel_2中

		textField_4 = new JTextField();// 文本框
		textField_4.setColumns(10);// 设置文本框的长度
		textField_4.setBounds(70, 80, 100, 27);// 设置文本框的坐标位置，以及宽、高
		panel_2.add(textField_4);// 将文本框组件textField_4添加到面板panel_2中

		JLabel label_4 = new JLabel("手机号：");// 标签
		label_4.setFont(new Font("幼圆", Font.BOLD, 14));// 设置字体
		label_4.setBounds(180, 80, 60, 27);// 设置标签的坐标位置，以及宽、高
		panel_2.add(label_4);// 将标签组件label_4添加到面板panel_2中

		textField_5 = new JTextField();// 文本框
		textField_5.setColumns(10);// 设置文本框的长度
		textField_5.setBounds(242, 80, 100, 27);// 设置文本框的坐标位置，以及宽、高
		panel_2.add(textField_5);// 将文本框组件textField_5添加到面板panel_2中

		JButton btnNewButton_1 = new JButton("修改");// 按钮
		btnNewButton_1.setFont(new Font("幼圆", Font.BOLD, 14));// 设置字体
		btnNewButton_1.setBounds(394, 100, 93, 35);// 设置标签的坐标位置，以及宽、高
		panel_2.add(btnNewButton_1);// 将下拉框组件btnNewButton_1添加到面板panel_2中
		/*
		 * 编写修改按钮的监视器，来监听修改按钮的单击事件。
		 * 这里添加了事件监视器，当事件发生时，将调用actionPerformed方法，对发生的事件进行处理。
		 * ActionListener()是一个接口，以下是和接口有关的匿名类，重写了该接口中的全部方法
		 */
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userName = textField_2.getText();// 获取用户名
				String password = textField_3.getText();// 获取密码
				String sex = textField_4.getText();// 获取
				String phone = textField_5.getText();// 获取
				String userId = textField_1.getText();// 获取编号

				// 调用“工具类”判断是否为空，若为空则弹出一个显示"请输入相关内容"，并带有“确定”按钮的模态对话框
				if (toolUtil.isEmpty(userId) || toolUtil.isEmpty(userName) || toolUtil.isEmpty(password)
						|| toolUtil.isEmpty(sex) || toolUtil.isEmpty(phone)) {
					JOptionPane.showMessageDialog(null, "请输入相关内容");
					return;
				}

				User user = new User();//创建用户对象
				user.setUserId(Integer.parseInt(userId));
				user.setUserName(userName);
				user.setPassword(password);
				user.setSex(sex);
				user.setPhone(phone);

				Connection con = null;
				try {
					con = dbUtil.getConnection();// 获取连接
					// 调用bookDao的update方法，将新图书信息更新到数据库表中
					int i = userDao.update(con, user);
					if (i == 1) {// 修改成功，则弹出一个显示"修改成功"，并带有“确定”按钮的模态对话框
						JOptionPane.showMessageDialog(null, "修改成功");
					} else {// 修改失败，则弹出一个显示"修改失败"，并带有“确定”按钮的模态对话框
						JOptionPane.showMessageDialog(null, "修改失败");
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "修改异常");
				} finally {
					try {
						dbUtil.closeCon(con);// 关闭连接
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				/* 调用putDates方法，将从数据库获取到的图书信息数据放置到table中 */
				putDates(new User());
			}
		});
	}

	// 从数据库中查询图书信息列表
	private void putDates(User user) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();// 返回 DefaultTableModel数据模型
		model.setRowCount(0);// 设置模型中的行数
		Connection con = null;
		try {
			con = dbUtil.getConnection();// 获取连接
			// 调用userDao的list方法，从数据库中查询所有的图书信息
			ResultSet resultSet = userDao.list(con, user);
			while (resultSet.next()) {
				// 集合类，继承自 AbstractList，实现了 List 接口 ，底层是数组结构，元素可重复，有序（存放顺序），支持下标索引访问，允许null元素
				
				Vector rowData = new Vector();// 创建Vector集合
				rowData.add(resultSet.getInt("userId"));// 将userId存到Vector集合中
				rowData.add(resultSet.getString("userName"));// 将用户名存到Vector集合中
				rowData.add(resultSet.getString("password"));// 将密码存到Vector集合中
				rowData.add(resultSet.getString("sex"));// 将性别存到Vector集合中
				rowData.add(resultSet.getString("phone"));// 将电话存到Vector集合中

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
		// 编写table模型的鼠标事件监视器，来监听表格单击事件。
		// MouseAdapter:用于接收鼠标事件的抽象适配器类,此抽象类定义的方法都是空的，只需要针对所关心的事件重写方法。
		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {// 鼠标按下事件
				tableMousePressed(evt);// 调用tableMousePressed方法
			}
		});
	}

	// 点击表格获取数据
	/**
	 * @param evt
	 */
	protected void tableMousePressed(MouseEvent evt) {
		int row = table.getSelectedRow();// 获得被选中行的索引
		// 从表格模型中获得指定单元格(第row行，第0列)的值
		Integer userId = (Integer) table.getValueAt(row, 0);
		User user = new User();// 创建user对象
		user.setUserId(userId);

		Connection con = null;
		try {
			con = dbUtil.getConnection();// 获取连接
			// 调用bookDao的list方法，从数据库中查询该bookId对应的图书信息
			ResultSet list = userDao.list(con, user);
			if (list.next()) {
				textField_1.setText(list.getString("userId"));// 将编号赋值到textField_1图书编号文本框
				textField_2.setText(list.getString("userName"));// 将赋值到textField_2图书书名文本框
				textField_3.setText(list.getString("password"));// 赋值到textField_3图书作者文本框
				textField_4.setText(list.getString("sex"));// 赋值到textField_5图书出版文本框
				textField_5.setText(list.getString("phone"));// 赋值到textField_4图书价格文本框

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
