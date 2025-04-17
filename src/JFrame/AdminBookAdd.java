package JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import dao.BookDao;
import model.Book;
import utils.DbUtil;
import utils.toolUtil;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

public class AdminBookAdd extends JFrame {
	private JFrame jf;
	private JTable table;
	private DefaultTableModel model;
	DbUtil dbUtil = new DbUtil();
	BookDao bookDao = new BookDao();

	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JComboBox comboBox_2;

	public AdminBookAdd() {
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

		JPanel panel_2 = new JPanel();// 创建一个面板组件
		panel_2.setLayout(null);// 设置布局管理器，null表示不使用
		// 设置面板组件的边框
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "书籍添加", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(255, 0, 0)));
		panel_2.setBounds(20, 20, 541, 366);// 设置面板组件的坐标位置，以及宽、高
		jf.getContentPane().add(panel_2);// 将面板组件panel_2添加到窗体面板中

		JLabel label = new JLabel("书名：");// 标签
		label.setFont(new Font("幼圆", Font.BOLD, 14));// 设置字体
		label.setBounds(58, 30, 45, 27);// 设置标签的坐标位置，以及宽、高
		panel_2.add(label);// 将标签组件label添加到面板panel_2中

		textField_1 = new JTextField();// 文本框
		textField_1.setColumns(10);// 设置文本框的长度
		textField_1.setBounds(101, 30, 129, 27);// 设置文本框的坐标位置，以及宽、高
		panel_2.add(textField_1);// 将文本框组件textField_1添加到面板panel_2中

		JLabel label_1 = new JLabel("作者：");// 标签
		label_1.setFont(new Font("幼圆", Font.BOLD, 14));// 设置字体
		label_1.setBounds(294, 30, 45, 27);// 设置标签的坐标位置，以及宽、高
		panel_2.add(label_1);// 将标签组件label_1添加到面板panel_2中

		textField_2 = new JTextField();// 文本框
		textField_2.setColumns(10);// 设置文本框的长度
		textField_2.setBounds(338, 30, 128, 27);// 设置文本框的坐标位置，以及宽、高
		panel_2.add(textField_2);// 将文本框组件textField_2添加到面板panel_2中

		JLabel label_2 = new JLabel("出版社：");// 标签
		label_2.setFont(new Font("幼圆", Font.BOLD, 14));// 设置字体
		label_2.setBounds(44, 78, 59, 27);// 设置标签的坐标位置，以及宽、高
		panel_2.add(label_2);// 将标签组件label_2添加到面板panel_2中

		textField_3 = new JTextField();// 文本框
		textField_3.setColumns(10);// 设置文本框的长度
		textField_3.setBounds(101, 78, 129, 27);// 设置文本框的坐标位置，以及宽、高
		panel_2.add(textField_3);// 将文本框组件textField_2添加到面板panel_2中

		JLabel label_3 = new JLabel("价格：");// 标签
		label_3.setFont(new Font("幼圆", Font.BOLD, 14));// 设置字体
		label_3.setBounds(294, 78, 45, 27);// 设置标签的坐标位置，以及宽、高
		panel_2.add(label_3);// 将标签组件label_3添加到面板panel_2中

		textField_4 = new JTextField();// 文本框
		textField_4.setColumns(10);// 设置文本框的长度
		textField_4.setBounds(338, 78, 129, 27);// 设置文本框的坐标位置，以及宽、高
		panel_2.add(textField_4);// 将文本框组件textField_4添加到面板panel_2中

		JLabel label_4 = new JLabel("库存：");// 标签
		label_4.setFont(new Font("幼圆", Font.BOLD, 14));// 设置字体
		label_4.setBounds(58, 124, 45, 27);// 设置标签的坐标位置，以及宽、高
		panel_2.add(label_4);// 将标签组件label_4添加到面板panel_2中

		textField_5 = new JTextField();// 文本框
		textField_5.setColumns(10);// 设置文本框的长度
		textField_5.setBounds(101, 124, 129, 27);// 设置文本框的坐标位置，以及宽、高
		panel_2.add(textField_5);// 将文本框组件textField_5添加到面板panel_2中

		JLabel label_6 = new JLabel("描述：");// 标签
		label_6.setFont(new Font("幼圆", Font.BOLD, 14));// 设置字体
		label_6.setBounds(58, 170, 45, 27);// 设置标签的坐标位置，以及宽、高
		panel_2.add(label_6);// 将标签组件label_6添加到面板panel_2中

		textField_6 = new JTextField();// 文本框
		textField_6.setColumns(10);// 设置文本框的长度
		textField_6.setBounds(101, 170, 350, 27);// 设置文本框的坐标位置，以及宽、高
		panel_2.add(textField_6);// 将文本框组件textField_6添加到面板panel_2中

		
		
		
		
		JButton btnNewButton_1 = new JButton("添加");// 按钮
		btnNewButton_1.setFont(new Font("幼圆", Font.BOLD, 14));// 设置字体
		btnNewButton_1.setBounds(80, 240, 93, 35);// 设置标签的坐标位置，以及宽、高
		panel_2.add(btnNewButton_1);// 将下拉框组件btnNewButton_1添加到面板panel_2中
		/*
		 * 编写注册按钮的监视器，来监听注册按钮的单击事件。 这里添加了事件监视器，当事件发生时，将调用actionPerformed方法，对发生的事件进行处理。
		 * ActionListener()是一个接口，以下是和接口有关的匿名类，重写了该接口中的全部方法
		 */
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bookName = textField_1.getText();// 获取书名
				String author = textField_2.getText();// 获取作者
				String publish = textField_3.getText();// 获取出版
				String priceStr = textField_4.getText();// 获取价格
				String numberStr = textField_5.getText();// 获取库存
				String remark = textField_6.getText();// 获取描述

				// 调用“工具类”判断是否为空，若为空则弹出一个显示"请输入相关内容"，并带有“确定”按钮的模态对话框
				if (toolUtil.isEmpty(bookName) || toolUtil.isEmpty(author) || toolUtil.isEmpty(publish)
						|| toolUtil.isEmpty(priceStr) || toolUtil.isEmpty(numberStr) || toolUtil.isEmpty(remark)) {
					JOptionPane.showMessageDialog(null, "请输入相关内容");
					return;
				}
		
				Book book = new Book();// 创建book对象
				// book.setBookId(Integer.parseInt(bookId));//设置图书编号
				book.setBookName(bookName);// 设置书名
				book.setAuthor(author);// 设置作者
				book.setNumber(Integer.parseInt(numberStr));// 设置库存
				book.setPrice(Double.parseDouble(priceStr));// 设置价格
				book.setPublish(publish);// 设置出版
				book.setRemark(remark);// 设置描述

				Connection con = null;
				try {
					con = dbUtil.getConnection();// 获取连接
					// 调用bookDao的update方法，将新图书信息更新到数据库表中
					int i = bookDao.add(con, book);
					if (i == 1) {// 修改成功，则弹出一个显示"修改成功"，并带有“确定”按钮的模态对话框
						JOptionPane.showMessageDialog(null, "修改成功");
						clearField();// 清空界面录入信息
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

			}
		});

		JButton btnNewButton_2 = new JButton("重置");// 按钮
		btnNewButton_2.setFont(new Font("幼圆", Font.BOLD, 14));// 设置字体
		btnNewButton_2.setBounds(306, 240, 93, 35);// 设置标签的坐标位置，以及宽、高
		panel_2.add(btnNewButton_2);// 将下拉框组件btnNewButton_1添加到面板panel_2中
		/*
		 * 编写注册按钮的监视器，来监听注册按钮的单击事件。 这里添加了事件监视器，当事件发生时，将调用actionPerformed方法，对发生的事件进行处理。
		 * ActionListener()是一个接口，以下是和接口有关的匿名类，重写了该接口中的全部方法
		 */
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearField();
			}
		});

		jf.setVisible(true);// 设置管理员界面窗口可见
		jf.setResizable(true);// 设置管理员界面窗口可调整大小
	}

	public void clearField() {
		// 清空界面录入信息
		textField_1.setText("");
		textField_2.setText("");
		textField_3.setText("");
		textField_5.setText("");
		textField_4.setText("");
		textField_6.setText("");

	}
}
