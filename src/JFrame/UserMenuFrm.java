package JFrame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import dao.BorrowDetailDao;
import dao.UserDao;
import dao.BookDao;
import model.Book;
import model.BorrowDetail;
import model.User;
import utils.DbUtil;
import utils.toolUtil;

public class UserMenuFrm extends JFrame {
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
	UserDao userDao = new UserDao();
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

	public UserMenuFrm() {
		jf = new JFrame();// 创建一个新的窗口
		jf.setTitle("用户页面");// 设置标题为"用户页面"
		jf.setBounds(250, 100, 700, 702);// 设置窗口的初始位置，距屏幕左面250个像素，距屏幕上方100个像素，窗口的宽是700，高是702
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 结束窗口所在的应用程序
		jf.getContentPane().setLayout(null);// 设置布局管理器，null表示不使用
		jf.setVisible(true);// 设置用户页面窗口可见
		jf.setResizable(true);// 设置用户页面窗口可调整大小

		// 第一部分组件
		lblNewLabel_2 = new JLabel("欢迎您,");// 标签
		lblNewLabel_2.setFont(new Font("幼圆", Font.BOLD, 18));// 设置标签的字体
		lblNewLabel_2.setBounds(254, 11, 258, 28);// 设置标签的坐标位置，以及宽、高
		jf.getContentPane().add(lblNewLabel_2);// 将标签组件lblNewLabel_2添加到窗体面板中

		lblNewLabel_1 = new JLabel("New label");// 标签
		lblNewLabel_1.setForeground(Color.RED);// 设置标签字体的颜色
		lblNewLabel_1.setFont(new Font("幼圆", Font.BOLD, 18));// 设置标签的字体
		lblNewLabel_1.setBounds(315, 10, 197, 28);// 设置标签的坐标位置，以及宽、高
		jf.getContentPane().add(lblNewLabel_1);// 将标签组件lblNewLabel_1添加到窗体面板中
		lblNewLabel_1.setText(LoginFrm.currentUser.getUserName());// 获取LoginFrm类变量currentUser中的用户名

		// 第二部分借阅信息组件
		JPanel panel_1 = new JPanel();// 创建一个面板组件
		// 设置面板组件的边框
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "借阅信息", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(255, 0, 0)));
		panel_1.setBounds(23, 48, 651, 139);// 设置面板组件的坐标位置，以及宽、高

		/* 1、做一个表头栏数据 一维数组 */
		String[] title = { "编号", "书名", "状态", "借书时间", "还书时间" };
		/* 具体的各栏行记录 先用空的二位数组占位 */
		String[][] dates = {};

		/* 2、然后实例化 上面2个控件对象 */
		// DefaultTableModel是 TableModel的一个实现，它使用一个 Vector来存储单元格的值对象，该 Vector由多个
		// Vector组成。
		model = new DefaultTableModel(dates, title);// 构建一个DefaultTableModel，并通过将 datas和 title传递到 setDataVector方法来初始化该表。
		table = new JTable();// 创建默认的表格模型
		table.setModel(model);// 将此表的数据模型设置为 model，并将其注册为新数据模型的侦听器通知。

		/* 3、调用putDates方法，将从数据库获取到的本人的借阅信息数据放置到table中 */
		putDates(new BorrowDetail());

		panel_1.setLayout(null);// 设置面板组件的布局管理器，null表示不使用
		JScrollPane jscrollpane = new JScrollPane();// 创建一个带有滚动条的面板
		jscrollpane.setBounds(20, 22, 607, 98);// 设置滚动面板组件的坐标位置，以及宽、高
		jscrollpane.setViewportView(table);// 将table放置到滚动面板中显示
		panel_1.add(jscrollpane);// 将滚动面板组件jscrollpane添加到面板组件panel_1中
		jf.getContentPane().add(panel_1);// 将面板组件panel_1添加到窗体面板中

		// 第三部分还书组件
		JPanel panel = new JPanel();// 创建一个面板组件
		// 设置面板组件的边框
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "还书", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(255, 0, 0)));
		panel.setBounds(23, 194, 651, 70);// 设置面板组件的坐标位置，以及宽、高
		jf.getContentPane().add(panel);// 将面板组件panel添加到窗体面板中
		panel.setLayout(null);// 设置面板组件的布局管理器，null表示不使用

		JLabel lblNewLabel = new JLabel("编号：");// 标签
		lblNewLabel.setBounds(90, 15, 51, 27);// 设置标签的坐标位置，以及宽、高
		lblNewLabel.setFont(new Font("幼圆", Font.BOLD, 16));// 设置标签的字体
		panel.add(lblNewLabel);// 将标签组件lblNewLabel添加到面板组件panel中

		textField = new JTextField();// 文本框
		textField.setBounds(145, 18, 116, 24);// 设置文本框的坐标位置，以及宽、高
		textField.setColumns(10);// 设置文本框的长度
		panel.add(textField);// 将文本框组件textField添加到面板组件panel中

		btnBackBook = new JButton("还书");// 按钮
		btnBackBook.setFont(new Font("幼圆", Font.BOLD, 15));// 设置按钮的字体
		btnBackBook.setBounds(299, 15, 85, 31);// 设置按钮的坐标位置，以及宽、高
		panel.add(btnBackBook);// 将按钮组件btnBackBook添加到面板组件panel中
		btnBackBook.setVisible(false);// 设置“还书”按钮不可见
		/*
		 * 编写“还书”按钮的监视器，来监听“还书”按钮的单击事件。
		 * 这里添加了事件监视器，当事件发生时，将调用actionPerformed方法，对发生的事件进行处理。
		 * ActionListener()是一个接口，以下是和接口有关的匿名类，重写了该接口中的全部方法
		 */
		btnBackBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String BorrowStr = textField.getText();// 获取"编号"文本框中的内容
				// 调用“工具类”判断编号是否为空，若为空则弹出一个显示"请选择未还的书籍"，并带有“确定”按钮的模态对话框
				if (toolUtil.isEmpty(BorrowStr)) {
					JOptionPane.showMessageDialog(null, "请选择未还的书籍");
					return;
				}
				BorrowDetail detail = new BorrowDetail();// 创建借书详情对象
				detail.setBorrowId(Integer.parseInt(BorrowStr));// 设置编号
				detail.setStatus(2);// 设置状态为“已还”
				// 调用“工具类”获取当前时间，并设置到还书时间
				detail.setReturnTime(toolUtil.getTime());
				Connection con = null;
				try {
					con = dbUtil.getConnection();// 获取连接
					// 调用bdetailDao的returnBook方法，更新数据库的还书信息
					int i = bdetailDao.returnBook(con, detail);
					if (i == 1) {// 更新成功则返回“还书成功”模态对话框
						JOptionPane.showMessageDialog(null, "还书成功");
					} else {// 更新失败则返回“还书失败”模态对话框
						JOptionPane.showMessageDialog(null, "还书失败");
					}
				} catch (Exception e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "还书异常");
				} finally {
					try {
						dbUtil.closeCon(con);// 关闭连接
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				putDates(new BorrowDetail());// 调用putDates方法从数据库中查询该登录用户的借阅信息列表
			}
		});

		button = new JButton("退出系统");// 按钮
		button.setFont(new Font("幼圆", Font.BOLD, 15));// 设置按钮的字体
		button.setBounds(407, 15, 103, 31);// 设置按钮的坐标位置，以及宽、高
		panel.add(button);// 将按钮组件button添加到面板组件panel中
		/*
		 * 编写“退出系统”按钮的监视器，来监听“退出系统”按钮的单击事件。
		 * 这里添加了事件监视器，当事件发生时，将调用actionPerformed方法，对发生的事件进行处理。
		 * ActionListener()是一个接口，以下是和接口有关的匿名类，重写了该接口中的全部方法
		 */
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "欢迎再次使用");
				jf.dispose();
			}
		});

		// 第四部分书籍信息组件

		JPanel panel_4 = new JPanel();// 创建一个面板组件
		// 设置面板组件的边框
		panel_4.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "书籍信息", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(255, 0, 0)));
		panel_4.setBounds(23, 277, 651, 248);// 设置面板组件的坐标位置，以及宽、高

		/* 1、做一个表头栏数据 一维数组 */
		String[] title2 = { "编号", "书名", "作者", "描述" };
		/* 具体的各栏行记录 先用空的二位数组占位 */
		String[][] dates2 = {};

		/* 2、然后实例化 上面2个控件对象 */
		// DefaultTableModel是 TableModel的一个实现，它使用一个 Vector来存储单元格的值对象，该 Vector由多个
		// Vector组成。
		model_2 = new DefaultTableModel(dates2, title2);// 构建一个DefaultTableModel，并通过将 datas和 title传递到
														// setDataVector方法来初始化该表。
		table_2 = new JTable();// 创建默认的表格模型
		table_2.setModel(model_2);// 将此表的数据模型设置为 model，并将其注册为新数据模型的侦听器通知。

		/* 3、调用putDates方法，将从数据库获取到的本人的借阅信息数据放置到table中 */
		putDates_2(new Book());

		panel_4.setLayout(null);// 设置面板组件的布局管理器，null表示不使用
		JScrollPane jscrollpane2 = new JScrollPane();// 创建一个带有滚动条的面板
		jscrollpane2.setBounds(20, 22, 607, 200);// 设置滚动面板组件的坐标位置，以及宽、高
		jscrollpane2.setViewportView(table_2);// 将table放置到滚动面板中显示
		panel_4.add(jscrollpane2);// 将滚动面板组件jscrollpane添加到面板组件panel_1中
		jf.getContentPane().add(panel_4);// 将面板组件panel_1添加到窗体面板中

		// 第五部分还书组件
		JPanel panel_5 = new JPanel();// 创建一个面板组件
		// 设置面板组件的边框
		panel_5.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "借书", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(255, 0, 0)));
		panel_5.setBounds(23, 540, 651, 70);// 设置面板组件的坐标位置，以及宽、高
		jf.getContentPane().add(panel_5);// 将面板组件panel添加到窗体面板中
		panel_5.setLayout(null);// 设置面板组件的布局管理器，null表示不使用

		lblNewLabel_5 = new JLabel("编号：");// 标签
		lblNewLabel_5.setBounds(42, 25, 51, 27);// 设置标签的坐标位置，以及宽、高
		lblNewLabel_5.setFont(new Font("幼圆", Font.BOLD, 16));// 设置标签的字体
		panel_5.add(lblNewLabel_5);// 将标签组件lblNewLabel添加到面板组件panel中

		textField_5 = new JTextField();// 文本框
		textField_5.setEditable(false);// 设置不可编辑
		textField_5.setBounds(88, 25, 116, 24);// 设置文本框的坐标位置，以及宽、高
		textField_5.setColumns(10);// 设置文本框的长度
		panel_5.add(textField_5);// 将文本框组件textField添加到面板组件panel中

		lblNewLabel_6 = new JLabel("书名：");// 标签
		lblNewLabel_6.setBounds(248, 25, 51, 27);// 设置标签的坐标位置，以及宽、高
		lblNewLabel_6.setFont(new Font("幼圆", Font.BOLD, 16));// 设置标签的字体
		panel_5.add(lblNewLabel_6);// 将标签组件lblNewLabel添加到面板组件panel中

		textField_6 = new JTextField();// 文本框
		textField_6.setEditable(false);// 设置不可编辑
		textField_6.setBounds(300, 25, 116, 24);// 设置文本框的坐标位置，以及宽、高
		textField_6.setColumns(10);// 设置文本框的长度
		panel_5.add(textField_6);// 将文本框组件textField添加到面板组件panel中

		JButton btnBackBook_2 = new JButton("借书");// 按钮
		btnBackBook_2.setFont(new Font("幼圆", Font.BOLD, 15));// 设置按钮的字体
		btnBackBook_2.setBounds(490, 25, 85, 31);// 设置按钮的坐标位置，以及宽、高
		panel_5.add(btnBackBook_2);// 将按钮组件btnBackBook添加到面板组件panel中
		btnBackBook_2.setVisible(true);// 设置“借书”按钮可见
		/*
		 * 编写“借书”按钮的监视器，来监听“借书”按钮的单击事件。
		 * 这里添加了事件监视器，当事件发生时，将调用actionPerformed方法，对发生的事件进行处理。
		 * ActionListener()是一个接口，以下是和接口有关的匿名类，重写了该接口中的全部方法
		 */
		btnBackBook_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String BorrowStr = textField_5.getText();// 获取"编号"文本框中的内容
				// 调用“工具类”判断编号是否为空，若为空则弹出一个显示"请选择未还的书籍"，并带有“确定”按钮的模态对话框

				if (toolUtil.isEmpty(BorrowStr)) {
					JOptionPane.showMessageDialog(null, "请选择书籍");
					return;
				}

				BorrowDetail detail_2 = new BorrowDetail();// 创建借书详情对象
				detail_2.setUserId(LoginFrm.currentUser.getUserId());

				Connection con = null;
				int bookid = Integer.parseInt(BorrowStr);
				int flag = 0;// 是否已有借书记录

				try {
					con = dbUtil.getConnection();// 获取连接
					ResultSet list = bdetailDao.list(con, detail_2);
					while (list.next()) {
						if (list.getInt("bookId") == bookid) {					
							int status = list.getInt("status");
							if (status == 1) {// 该书已被借
								flag = 1;// 已有借书记录
								JOptionPane.showMessageDialog(null, "该书已在借，请先还再借");
								break;
							} 							
						}
					}

					if (flag == 0) {// 未有借书记录直接借书					
						lendbook(detail_2);
					}

				} catch (Exception e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "借书异常");
				} finally {
					try {
						dbUtil.closeCon(con);// 关闭连接
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				putDates_2(new Book());// 调用putDates方法从数据库中查询该登录用户的借阅信息列表
				putDates(new BorrowDetail());
			}
		});

	}

	// 借书函数
	private void lendbook(BorrowDetail borrowDetail) {
		String BorrowStr = textField_5.getText();
		BorrowDetail detail_2 = new BorrowDetail();// 创建借书详情对象
		detail_2.setUserId(LoginFrm.currentUser.getUserId());
		detail_2.setBookId(Integer.parseInt(BorrowStr));
		detail_2.setStatus(1);// 设置状态为“已借”
		// 调用“工具类”获取当前时间，并设置到借书时间
		detail_2.setBorrowTime(toolUtil.getTime());

		Connection con = null;
		try {
			con = dbUtil.getConnection();
			int i = bdetailDao.add(con, detail_2);
			if (i == 0) {
				// 更新成功则返回“借书成功”模态对话框
				JOptionPane.showMessageDialog(null, "借书成功");
			} else {// 更新失败则返回“借书失败”模态对话框
				JOptionPane.showMessageDialog(null, "借书失败");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 获取连接

	}

	// 从数据库中查询该登录用户的借阅信息列表
	private void putDates(BorrowDetail borrowDetail) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();// 返回 DefaultTableModel数据模型
		model.setRowCount(0);// 设置模型中的行数
		Integer userId = LoginFrm.currentUser.getUserId();// 获取LoginFrm类变量currentUser中的用户id
		Connection con = null;
		try {
			con = dbUtil.getConnection();// 获取连接
			borrowDetail.setUserId(userId);// 设置borrowDetail对象的用户id为userId
			// 调用bdetailDao的list方法，从数据库中查询该登录用户的借阅信息
			ResultSet list = bdetailDao.list(con, borrowDetail);
			while (list.next()) {
				// 集合类，继承自 AbstractList，实现了 List 接口 ，底层是数组结构，元素可重复，有序（存放顺序），支持下标索引访问，允许null元素
				Vector rowData = new Vector();// 创建Vector集合
				rowData.add(list.getInt("borrowId"));// 将bookId存到Vector集合中
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

		// 编写table模型的鼠标事件监视器，来监听表格单击事件。
		// MouseAdapter:用于接收鼠标事件的抽象适配器类,此抽象类定义的方法都是空的，只需要针对所关心的事件重写方法。
		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {// 鼠标按下事件
				putBack(me);// 调用putBack方法
			}
		});
	}

	// 用于显示待还图书的图书编号的文本框和还书按钮
	protected void putBack(MouseEvent me) {
		int row = table.getSelectedRow();// 获得被选中行的索引
		// 从表格模型中获得指定单元格(第row行，第0列)的值
		Integer borrowId = (Integer) table.getValueAt(row, 0);
		// 从表格模型中获得指定单元格(第row行，第2列)的值
		String status = (String) table.getValueAt(row, 2);
		textField.setText(borrowId.toString());// 将待还图书的编号赋值到textField图书编号文本框
		if (status.equals("在借")) {
			this.btnBackBook.setVisible(true);// 如果图书的状态为在借，则显示"还书"按钮
		} else {
			this.btnBackBook.setVisible(false);// 如果图书的状态为已还，则不显示"还书"按钮
		}
	}

	// 显示所有可借书籍信息列表
	// 从数据库中查询图书信息列表
	private void putDates_2(Book book) {
		DefaultTableModel model_2 = (DefaultTableModel) table_2.getModel();// 返回 DefaultTableModel数据模型
		model_2.setRowCount(0);// 设置模型中的行数
		Connection con = null;
		try {
			con = dbUtil.getConnection();// 获取连接
			// 调用bookDao的list方法，从数据库中查询所有的图书信息
			ResultSet resultSet = bookDao.listCan(con, book);
			while (resultSet.next()) {
				// 集合类，继承自 AbstractList，实现了 List 接口 ，底层是数组结构，元素可重复，有序（存放顺序），支持下标索引访问，允许null元素

				if (resultSet.getInt("status") == 1) {
					// 状态为1上架,下架不显示
					Vector rowData = new Vector();// 创建Vector集合
					rowData.add(resultSet.getInt("bookId"));// 将bookId存到Vector集合中
					rowData.add(resultSet.getString("bookName"));// 将书名存到Vector集合中
					rowData.add(resultSet.getString("author"));// 将作者存到Vector集合中
					rowData.add(resultSet.getString("remark"));
					model_2.addRow(rowData);// 添加一行到模型的结尾
				}

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
		table_2.addMouseListener(new MouseAdapter() {
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
		int row = table_2.getSelectedRow();// 获得被选中行的索引
		// 从表格模型中获得指定单元格(第row行，第0列)的值
		Integer bookId = (Integer) table_2.getValueAt(row, 0);
		Book book = new Book();// 创建book对象
		book.setBookId(bookId);// 设置book对象的bookId

		Connection con = null;

		try {
			con = dbUtil.getConnection();// 获取连接
			// 调用bookDao的list方法，从数据库中查询该bookId对应的图书信息
			ResultSet list = bookDao.list(con, book);
			if (list.next()) {
				textField_5.setText(list.getString("bookId"));// 将该图书的出版赋值到textField_5图书出版文本框
				textField_6.setText(list.getString("bookName"));// 将该图书的库存赋值到textField_6图书库存文本框
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