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


public class AdminBookEdit extends JFrame {
	private JFrame jf;
	private JTable table;
	private DefaultTableModel model;
	DbUtil dbUtil=new DbUtil();
	BookDao bookDao=new BookDao();
	
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JComboBox comboBox_2;

	public AdminBookEdit(){
		jf=new JFrame("管理员界面");
		jf.setBounds(400, 50, 600, 672);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.getContentPane().setLayout(null);//设置布局管理器，null表示不使用
		
		JMenuBar menuBar = new JMenuBar();//创建菜单栏组件
		jf.setJMenuBar(menuBar);//向jf窗口容器中加入菜单栏组件menuBar
								
		JMenu mnNewMenu_2 = new JMenu("书籍管理");//创建菜单组件
		menuBar.add(mnNewMenu_2);//将菜单组件mnNewMenu_2加入到菜单栏组件menuBar中
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("书籍添加");//创建菜单项组件
		/*编写菜单项组件mntmNewMenuItem_2的鼠标事件监视器，来监听菜单项单击事件。
		*MouseAdapter:用于接收鼠标事件的抽象适配器类,此抽象类定义的方法都是空的，只需要针对所关心的事件重写方法。*/
		mntmNewMenuItem_2.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {//鼠标按下事件
				jf.dispose();//撤销当前窗口，并释放当前窗口所使用的资源
				new AdminBookAdd();//创建管理员图书添加窗口
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_2);//将菜单项组件mntmNewMenuItem_2加入到菜单组件mnNewMenu_2中
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("书籍修改");//创建菜单项组件
		//编写菜单项组件mntmNewMenuItem_3的鼠标事件监视器，来监听菜单项单击事件。
		mntmNewMenuItem_3.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {//鼠标按下事件
				jf.dispose();//撤销当前窗口，并释放当前窗口所使用的资源
				new AdminBookEdit();//创建管理员图书修改窗口
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_3);//将菜单项组件mntmNewMenuItem_3加入到菜单组件mnNewMenu_2中
		
		JMenu menu1 = new JMenu("用户管理");//创建菜单组件
		menuBar.add(menu1);//将菜单组件menu1加入到菜单栏组件menuBar中
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("用户信息");//创建菜单项组件
		//编写菜单项组件mntmNewMenuItem_4的鼠标事件监视器，来监听菜单项单击事件。
		mntmNewMenuItem_4.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {//鼠标按下事件
				jf.dispose();//撤销当前窗口，并释放当前窗口所使用的资源
				new AdminUserInfo();//创建管理员用户信息窗口
			}
		});
		menu1.add(mntmNewMenuItem_4);//将菜单项组件mntmNewMenuItem_4加入到菜单组件menu1中
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("借阅信息");//创建菜单项组件
		//编写菜单项组件mntmNewMenuItem_5的鼠标事件监视器，来监听菜单项单击事件。
		mntmNewMenuItem_5.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {//鼠标按下事件
				jf.dispose();//撤销当前窗口，并释放当前窗口所使用的资源
				new AdminBorrowInfo();//创建管理员借阅信息窗口
			}
		});
		menu1.add(mntmNewMenuItem_5);//将菜单项组件mntmNewMenuItem_5加入到菜单组件menu1中
		
		JMenu mnNewMenu_1 = new JMenu("退出系统");//创建菜单组件
		//编写菜单组件mnNewMenu_1的鼠标事件监视器，来监听菜单单击事件。
		mnNewMenu_1.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {//鼠标按下事件
				//弹出一个显示"欢迎再次使用"，并带有“确定”按钮的模态对话框
				JOptionPane.showMessageDialog(null, "欢迎再次使用");
				jf.dispose();//撤销当前窗口，并释放当前窗口所使用的资源
			}
		});
		menuBar.add(mnNewMenu_1);//将菜单组件mnNewMenu_1加入到菜单栏组件menuBar中		
				
						
		
		JPanel panel_1 = new JPanel();//创建一个面板组件
		panel_1.setLayout(null);//设置布局管理器，null表示不使用
		//设置面板组件的边框
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "书籍信息", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 0)));
		panel_1.setBounds(20, 10, 541, 275);//设置面板组件的坐标位置，以及宽、高
		
		 /*1、做一个表头栏数据  一维数组*/
		 String[] title={"编号", "书名", "作者", "价格", "库存", "状态" };
		/*具体的各栏行记录 先用空的二位数组占位*/
		 String[][] dates={};
		 
		 /*2、然后实例化 上面2个控件对象*/
		//DefaultTableModel是 TableModel的一个实现，它使用一个 Vector来存储单元格的值对象，该 Vector由多个 Vector组成。
		 model=new DefaultTableModel(dates,title);//构建一个DefaultTableModel，并通过将 datas和 title传递到 setDataVector方法来初始化该表。
		 table=new JTable(model);//创建默认的表格模型，将此表的数据模型设置为 model，并将其注册为新数据模型的侦听器通知。
		 
		 /*3、调用putDates方法，将从数据库获取到的图书信息数据放置到table中*/
		 putDates(new Book());//获取数据库数据放置table中	
		 
		 panel_1.setLayout(null);//设置面板组件的布局管理器，null表示不使用
		 JScrollPane jscrollpane = new JScrollPane();//创建一个带有滚动条的面板
		 jscrollpane.setBounds(20, 22, 496, 234);//设置滚动面板组件的坐标位置，以及宽、高
	     jscrollpane.setViewportView(table);//将table放置到滚动面板中显示
		 panel_1.add(jscrollpane);//将滚动面板组件jscrollpane添加到面板组件panel_1中
		 jf.getContentPane().add(panel_1);//将面板组件panel_1添加到窗体面板中
		
		//第二部分组件
		JPanel panel_2 = new JPanel();//创建一个面板组件
		panel_2.setBounds(20, 310, 541, 292);//设置面板组件的坐标位置，以及宽、高
		jf.getContentPane().add(panel_2);//将面板组件panel_2添加到窗体面板中
		panel_2.setLayout(null);//设置布局管理器，null表示不使用
		
		JLabel label = new JLabel("编号：");//标签
		label.setFont(new Font("幼圆", Font.BOLD, 14));//设置字体
		label.setBounds(58, 10, 45, 27);//设置标签的坐标位置，以及宽、高
		panel_2.add(label);//将标签组件label添加到面板panel_2中
		
		textField_1 = new JTextField();//文本框
		textField_1.setEditable(false);//设置不可编辑
		textField_1.setColumns(10);//设置文本框的长度
		textField_1.setBounds(101, 10, 129, 27);//设置文本框的坐标位置，以及宽、高
		panel_2.add(textField_1);//将文本框组件textField_1添加到面板panel_2中
		
		JLabel label_1 = new JLabel("书名：");//标签
		label_1.setFont(new Font("幼圆", Font.BOLD, 14));//设置字体
		label_1.setBounds(294, 10, 45, 27);//设置标签的坐标位置，以及宽、高
		panel_2.add(label_1);//将标签组件label_1添加到面板panel_2中
		
		textField_2 = new JTextField();//文本框
		textField_2.setColumns(10);//设置文本框的长度
		textField_2.setBounds(338, 10, 128, 27);//设置文本框的坐标位置，以及宽、高
		panel_2.add(textField_2);//将文本框组件textField_2添加到面板panel_2中
		
		JLabel label_2 = new JLabel("作者：");//标签
		label_2.setFont(new Font("幼圆", Font.BOLD, 14));//设置字体
		label_2.setBounds(58, 58, 45, 27);//设置标签的坐标位置，以及宽、高
		panel_2.add(label_2);//将标签组件label_2添加到面板panel_2中
		
		textField_3 = new JTextField();//文本框
		textField_3.setColumns(10);//设置文本框的长度
		textField_3.setBounds(101, 58, 129, 27);//设置文本框的坐标位置，以及宽、高
		panel_2.add(textField_3);//将文本框组件textField_2添加到面板panel_2中
		
		JLabel label_3 = new JLabel("价格：");//标签
		label_3.setFont(new Font("幼圆", Font.BOLD, 14));//设置字体
		label_3.setBounds(58, 104, 45, 27);//设置标签的坐标位置，以及宽、高
		panel_2.add(label_3);//将标签组件label_3添加到面板panel_2中
		
		textField_4 = new JTextField();//文本框
		textField_4.setColumns(10);//设置文本框的长度
		textField_4.setBounds(101, 104, 129, 27);//设置文本框的坐标位置，以及宽、高
		panel_2.add(textField_4);//将文本框组件textField_4添加到面板panel_2中
		
		JLabel label_4 = new JLabel("出版：");//标签
		label_4.setFont(new Font("幼圆", Font.BOLD, 14));//设置字体
		label_4.setBounds(294, 58, 45, 27);//设置标签的坐标位置，以及宽、高
		panel_2.add(label_4);//将标签组件label_4添加到面板panel_2中
		
		textField_5 = new JTextField();//文本框
		textField_5.setColumns(10);//设置文本框的长度
		textField_5.setBounds(337, 58, 129, 27);//设置文本框的坐标位置，以及宽、高
		panel_2.add(textField_5);//将文本框组件textField_5添加到面板panel_2中
						
		JLabel label_6 = new JLabel("库存：");//标签
		label_6.setFont(new Font("幼圆", Font.BOLD, 14));//设置字体
		label_6.setBounds(294, 104, 45, 27);//设置标签的坐标位置，以及宽、高
		panel_2.add(label_6);//将标签组件label_6添加到面板panel_2中
		
		textField_6 = new JTextField();//文本框
		textField_6.setColumns(10);//设置文本框的长度
		textField_6.setBounds(337, 104, 129, 27);//设置文本框的坐标位置，以及宽、高
		panel_2.add(textField_6);//将文本框组件textField_6添加到面板panel_2中
		
		JLabel label_7 = new JLabel("描述：");//标签
		label_7.setFont(new Font("幼圆", Font.BOLD, 14));//设置字体
		label_7.setBounds(58, 152, 45, 27);//设置标签的坐标位置，以及宽、高
		panel_2.add(label_7);//将标签组件label_7添加到面板panel_2中
		
		textField_7 = new JTextField();//文本框
		textField_7.setColumns(10);//设置文本框的长度
		textField_7.setBounds(101, 152, 365, 27);//设置文本框的坐标位置，以及宽、高
		panel_2.add(textField_7);//将文本框组件textField_7添加到面板panel_2中
		
		JLabel label_8 = new JLabel("状态：");//标签
		label_8.setFont(new Font("幼圆", Font.BOLD, 14));//设置字体
		label_8.setBounds(294, 190, 45, 27);//设置标签的坐标位置，以及宽、高
		panel_2.add(label_8);//将标签组件label_8添加到面板panel_2中						
		
		comboBox_2 = new JComboBox();//创建下拉框组件
		comboBox_2.setBounds(338, 191, 128, 26);//设置下拉框组件的坐标位置，以及宽、高
		comboBox_2.addItem("上架");//为下拉框添加选项
		comboBox_2.addItem("下架");//为下拉框添加选项
		panel_2.add(comboBox_2);//将下拉框组件comboBox_2添加到面板panel_2中
		
		JButton btnNewButton_1 = new JButton("修改");//按钮
		btnNewButton_1.setFont(new Font("幼圆", Font.BOLD, 14));//设置字体
		btnNewButton_1.setBounds(304, 235, 93, 35);//设置标签的坐标位置，以及宽、高
		panel_2.add(btnNewButton_1);//将下拉框组件btnNewButton_1添加到面板panel_2中
		/*
		 * 编写注册按钮的监视器，来监听注册按钮的单击事件。
		 * 这里添加了事件监视器，当事件发生时，将调用actionPerformed方法，对发生的事件进行处理。
		 * ActionListener()是一个接口，以下是和接口有关的匿名类，重写了该接口中的全部方法
		 */
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bookName = textField_2.getText();//获取书名
				String author = textField_3.getText();//获取作者
				String publish = textField_5.getText();//获取出版
				String priceStr = textField_4.getText();//获取价格
				String numberStr = textField_6.getText();//获取库存
				String remark = textField_7.getText();//获取描述
				String bookId = textField_1.getText();//获取图书编号
				//调用“工具类”判断是否为空，若为空则弹出一个显示"请输入相关内容"，并带有“确定”按钮的模态对话框
				if (toolUtil.isEmpty(bookId) || toolUtil.isEmpty(bookName)
						|| toolUtil.isEmpty(author) || toolUtil.isEmpty(publish)
						|| toolUtil.isEmpty(priceStr)
						|| toolUtil.isEmpty(numberStr) || toolUtil.isEmpty(remark)) {
					JOptionPane.showMessageDialog(null, "请输入相关内容");
					return;
				}
				int index = comboBox_2.getSelectedIndex();//返回当前所选项的索引
				
				Book book = new Book();//创建book对象
				book.setBookId(Integer.parseInt(bookId));//设置图书编号
				book.setBookName(bookName);//设置书名
				book.setAuthor(author);//设置作者
				book.setNumber(Integer.parseInt(numberStr));//设置库存
				book.setPrice(Double.parseDouble(priceStr));//设置价格
				book.setPublish(publish);//设置出版
				book.setRemark(remark);//设置描述
				if (index == 0) {
					book.setStatus(1);//如果所选索引项为0，则设置图书状态为1上架
				} else if (index == 1) {
					book.setStatus(2);//如果所选索引项为1，则设置图书状态为2下架
				}
				Connection con = null;
				try {
					con = dbUtil.getConnection();//获取连接
					//调用bookDao的update方法，将新图书信息更新到数据库表中
					int i = bookDao.update(con, book);
					if (i == 1) {//修改成功，则弹出一个显示"修改成功"，并带有“确定”按钮的模态对话框
						JOptionPane.showMessageDialog(null, "修改成功");
					} else {//修改失败，则弹出一个显示"修改失败"，并带有“确定”按钮的模态对话框
						JOptionPane.showMessageDialog(null, "修改失败");
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "修改异常");
				}finally{
					try {
						dbUtil.closeCon(con);//关闭连接
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				 /*调用putDates方法，将从数据库获取到的图书信息数据放置到table中*/
				putDates(new Book());
			}
		});	
		jf.setVisible(true);//设置管理员界面窗口可见
		jf.setResizable(true);//设置管理员界面窗口可调整大小
	}
	
	//从数据库中查询图书信息列表
	private void putDates(Book book) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();//返回 DefaultTableModel数据模型 
		model.setRowCount(0);//设置模型中的行数
		Connection con = null;
		try {
			con = dbUtil.getConnection();//获取连接
			//调用bookDao的list方法，从数据库中查询所有的图书信息
			ResultSet resultSet = bookDao.list(con, book);
			while (resultSet.next()) {
				//集合类，继承自 AbstractList，实现了 List 接口 ，底层是数组结构，元素可重复，有序（存放顺序），支持下标索引访问，允许null元素
				Vector rowData = new Vector();//创建Vector集合
				rowData.add(resultSet.getInt("bookId"));//将bookId存到Vector集合中
				rowData.add(resultSet.getString("bookName"));//将书名存到Vector集合中
				rowData.add(resultSet.getString("author"));//将作者存到Vector集合中
				rowData.add(resultSet.getDouble("price"));//将价格存到Vector集合中
				rowData.add(resultSet.getInt("number"));//将库存存到Vector集合中
				if (resultSet.getInt("status") == 1) {//获取图书状态
					rowData.add("上架");//若是状态1，则将上架状态存到Vector集合中
				} else {
					rowData.add("下架");//若是状态2，则将下架状态存到Vector集合中
				}
				model.addRow(rowData);//添加一行到模型的结尾
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeCon(con);//关闭连接
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//编写table模型的鼠标事件监视器，来监听表格单击事件。
		//MouseAdapter:用于接收鼠标事件的抽象适配器类,此抽象类定义的方法都是空的，只需要针对所关心的事件重写方法。
		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {//鼠标按下事件
			  tableMousePressed(evt);//调用tableMousePressed方法
			}
		});
	}
	//点击表格获取数据
		/**
		 * @param evt
		 */
	protected void tableMousePressed(MouseEvent evt) {
		int row = table.getSelectedRow();//获得被选中行的索引
		//从表格模型中获得指定单元格(第row行，第0列)的值
		Integer bookId = (Integer) table.getValueAt(row, 0);
		Book book = new Book();//创建book对象
		book.setBookId(bookId);//设置book对象的bookId
			
        Connection con = null;
		try {
			con = dbUtil.getConnection();//获取连接
			//调用bookDao的list方法，从数据库中查询该bookId对应的图书信息
			ResultSet list = bookDao.list(con, book);
			if (list.next()) {
				textField_1.setText(list.getString("bookId"));//将该图书的编号赋值到textField_1图书编号文本框
				textField_2.setText(list.getString("bookName"));//将该图书的书名赋值到textField_2图书书名文本框
				textField_3.setText(list.getString("author"));//将该图书的作者赋值到textField_3图书作者文本框
				textField_5.setText(list.getString("publish"));//将该图书的出版赋值到textField_5图书出版文本框
				textField_4.setText(list.getString("price"));//将该图书的价格赋值到textField_4图书价格文本框
				textField_6.setText(list.getString("number"));//将该图书的库存赋值到textField_6图书库存文本框
				textField_7.setText(list.getString("remark"));//将该图书的描述赋值到textField_7图书描述文本框
				int status = list.getInt("status");//获取图书的状态
				if (status == 1) {
					comboBox_2.setSelectedIndex(0);//如果状态为上架，则让comboBox_2的0项选中
				} else {
					comboBox_2.setSelectedIndex(1);//如果状态为下架，则让comboBox_2的1项选中
				}		
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);//关闭连接
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
	}	
}
