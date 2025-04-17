package JFrame;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import dao.UserDao;
import model.User;
import utils.DbUtil;
import utils.toolUtil;

import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginFrm extends JFrame {
	public static User currentUser;
	private JFrame jf;// 窗口
	private JTextField textField;
	private JTextField textField_1;
	private JLabel label_2;
	private JTextField textField_2;
	private JLabel label_3;
	private JRadioButton rdbtnNewRadioButton_1;
	private JLabel usernameMes;
	private JLabel passwordMes;
	private JLabel panelMes;
	private JLabel phoneMes;
	private JButton button;
	private JButton button_1;
	private JRadioButton rdbtnNewRadioButton;

	DbUtil dbUtil = new DbUtil();// 工具类对象
	UserDao userDao = new UserDao();// userDao
	private JLabel lblNewLabel_1;
	int flag=-1;

	public LoginFrm() {
		jf = new JFrame("图书管理");// 创建标题为"图书管理"的 窗口
		jf.getContentPane().setFont(new Font("幼圆", Font.BOLD, 16));// 设置此窗体的容器对象的字体
		jf.setBounds(600, 250, 510, 410);// 设置窗口的初始位置，距屏幕左面600个像素，距屏幕上方250个像素，窗口的宽是510，高是410
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 结束窗口所在的应用程序
		jf.getContentPane().setLayout(null);// 设置布局管理器，null表示不使用
		

		// 第1行组件
		JLabel label = new JLabel("用户名：");//标签
		label.setForeground(Color.BLACK);//设置字体的颜色
		label.setFont(new Font("幼圆", Font.BOLD, 16));//设置标签的字体
		label.setBounds(110, 65, 75, 40);//设置标签的坐标位置，以及宽、高
		jf.getContentPane().add(label);//将标签组件label添加到窗体面板中
		
		textField = new JTextField();//文本框
		textField.setFont(new Font("幼圆", Font.BOLD, 14));//设置文本框的字体
		textField.setForeground(Color.BLACK);//设置字体的颜色
		textField.setColumns(10);//设置文本框的长度
		textField.setBounds(198, 71, 164, 30);//设置文本框的坐标位置，以及宽、高
		jf.getContentPane().add(textField);//将文本框组件textField添加到窗体面板中
		/*
		 * 第78~95行编写用户名文本框的监视器，来监听文本框的动作。
		 * 这里添加了焦点监视器，当该组件获得输入焦点时，添加指定的焦点监视器以从该组件接收焦点事件。
		 * FocusListener()是一个接口，以下是和接口有关的匿名类，重写了该接口中的全部方法
		 */
		textField.addFocusListener(new FocusListener() {		
			@Override
			public void focusGained(FocusEvent e) {//获得焦点
			}
			//在focusLost()函数中编写了用户文本框失去鼠标焦点时，对文本框中内容的校验逻辑。
			@Override
			public void focusLost(FocusEvent e) {//失去焦点				
				String text = textField.getText();//获取文本框中的内容
				//调用“工具类”判断用户名是否为空，当用户名为空时提示“用户名不能为空”，设置字体的颜色为红色
				if(toolUtil.isEmpty(text)){
					usernameMes.setText("用户名不能为空");
					usernameMes.setForeground(Color.RED);
				}else{    //当用户名不为空时提示“√”，设置字体的颜色为绿色
					usernameMes.setText("√");
					usernameMes.setForeground(Color.GREEN);
				}
			}
		});
		
		usernameMes = new JLabel("");//提示标签
		usernameMes.setFont(new Font("幼圆", Font.BOLD, 15));//设置标签的字体
		usernameMes.setBounds(372, 71, 122, 27);//设置提示标签的坐标位置，以及宽、高
		jf.getContentPane().add(usernameMes);//将标签组件usernameMes添加到窗体面板中
		
		// 第2行组件
		JLabel label_1 = new JLabel("密码：");//标签
		label_1.setForeground(Color.BLACK);//设置字体的颜色
		label_1.setFont(new Font("幼圆", Font.BOLD, 16));//设置标签的字体
		label_1.setBounds(120, 108, 65, 40);//设置标签的坐标位置，以及宽、高
		jf.getContentPane().add(label_1);//将标签组件label_1添加到窗体面板中
		
		textField_1 = new JTextField();//文本框
		textField_1.setFont(new Font("幼圆", Font.BOLD, 14));//设置文本框的字体
		textField_1.setToolTipText("密码不能为空");//设置鼠标移到控件上时的弹出提示信息
		textField_1.setColumns(10);//设置文本框的长度
		textField_1.setBounds(198, 114, 164, 30);//设置文本框的坐标位置，以及宽、高
		jf.getContentPane().add(textField_1);//将文本框组件textField_1添加到窗体面板中
		/*
		 * 第120~145行编写密码文本框的监视器，来监听文本框的动作。
		 * 这里添加了焦点监视器，当该组件获得输入焦点时，添加指定的焦点监视器以从该组件接收焦点事件。
		 * FocusListener()是一个接口，以下是和接口有关的匿名类，重写了该接口中的全部方法
		 */
		textField_1.addFocusListener(new FocusListener() {
			//在focusLost()函数中编写了密码文本框失去鼠标焦点时，对文本框中内容的校验逻辑。
			@Override
			public void focusLost(FocusEvent e) {	
				String pwd=textField_1.getText();//获取文本框中的内容
				//调用“工具类”判断密码是否为空，当密码为空时提示“密码不能为空”，设置字体的颜色为红色
				if(toolUtil.isEmpty(pwd)){
					passwordMes.setText("密码不能为空");
					passwordMes.setForeground(Color.RED);
				}else{
					//使用正则表达式定义密码的格式必须为6~16位的字母和数字的组合
					boolean flag=pwd.matches("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$");
					if(flag){
						passwordMes.setText("√");
						passwordMes.setForeground(Color.GREEN);
					}else{
						//若输入的密码不符合规范，进行提示
						JOptionPane.showMessageDialog(null, "密码需为6-16位数字和字母的组合");
						passwordMes.setText("");//清空密码文本框
					}	
				}				
			}
			@Override
			public void focusGained(FocusEvent e) {	//获得焦点
			}
		});
		
		passwordMes = new JLabel("");//提示标签
		passwordMes.setFont(new Font("幼圆", Font.BOLD, 15));//设置标签的字体
		passwordMes.setBounds(372, 114, 122, 27);//设置提示标签的坐标位置，以及宽、高
		jf.getContentPane().add(passwordMes);//将标签组件passwordMes添加到窗体面板中
		
		// 第4行组件
		//下拉框组件
		JLabel label_2 = new JLabel("权限：");// 标签
		label_2.setForeground(Color.BLACK);// 设置字体的颜色
		label_2.setFont(new Font("幼圆", Font.BOLD, 16));// 设置标签的字体
		label_2.setBounds(120, 150, 65, 40);// 设置标签的坐标位置，以及宽、高
		jf.getContentPane().add(label_2);// 将标签组件label_2添加到窗体面板中

		JPanel panel = new JPanel();// 下拉框
		JComboBox<String> comboBox = new JComboBox<>();// 下拉框组件
		comboBox.setBounds(200,150,164,35);
		comboBox.addItem("请选择用户类型");
		comboBox.addItem("学生");
		comboBox.addItem("管理员");
		
		
		 
		JTextField textField=new JTextField(20);		 
		comboBox.addActionListener(e->{
			String item=(String)comboBox.getSelectedItem();
			if(item.equals("学生")){
				flag=0;
			}else if(item.equals("管理员")) {
				flag=1;
			}
			else{    //当用户名不为空时提示“√”，设置字体的颜色为绿色
				panelMes.setText("用户不能为空");
				panelMes.setForeground(Color.RED);
			}
			
		});
	
		// 将JComboBox组件和JTextField组件加入面板组件中
		panel.add(comboBox);
		panel.add(textField);
		jf.getContentPane().add(comboBox);// 向jf窗口容器中加入页头的JPanel面板组件
		

		// 第5行组件
		button = new JButton("登录");// 按钮
		button.setFont(new Font("幼圆", Font.BOLD, 15));// 设置按钮的字体
		button.setBounds(120, 280, 70, 30);// 设置按钮的坐标位置，以及宽、高
		jf.getContentPane().add(button);// 将按钮组件button添加到窗体面板中
		/*
		 * 第230~234行编写注册按钮的监视器，来监听注册按钮的单击事件。
		 * 这里添加了事件监视器，当事件发生时，将调用actionPerformed方法，对发生的事件进行处理。
		 * ActionListener()是一个接口，以下是和接口有关的匿名类，重写了该接口中的全部方法
		 */
		// 重写登录按钮动作事件监听方法
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegCheck(e);// 调用RegCheck方法对所填注册信息的正确性、完整性进行判断
			}
		});

		button_1 = new JButton("注册");// 按钮
		button_1.setFont(new Font("幼圆", Font.BOLD, 15));// 设置按钮的字体
		button_1.setBounds(245, 280, 70, 30);// 设置按钮的坐标位置，以及宽、高
		jf.getContentPane().add(button_1);// 将按钮组件button_1添加到窗体面板中
		/*
		 * 第245~250行编写登录按钮的监视器，来监听注册按钮的单击事件。
		 * 这里添加了事件监视器，当事件发生时，将调用actionPerformed方法，对发生的事件进行处理。
		 * ActionListener()是一个接口，以下是和接口有关的匿名类，重写了该接口中的全部方法
		 */
		// 重写注册按钮动作事件监听方法
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jf.setVisible(false);// 设置用户登录窗口为不可见
				new RegFrm();// 创建用户注册窗口
			}
		});
		
		jf.setVisible(true);// 设置用户注册窗口可见
		jf.setResizable(true);// 设置用户注册窗口可调整大小
	}
			
		//对所填注册信息的正确性、完整性进行判断
	protected void RegCheck(ActionEvent e) {
		String username = textField.getText();// 获取用户名
		String password = textField_1.getText();// 获取密码
		
		//调用“工具类”判断用户名、密码、手机号是否为空，若为空则弹出一个显示"请输入相关信息"，并带有“确定”按钮的模态对话框
		if (toolUtil.isEmpty(username) || toolUtil.isEmpty(password)) {
			JOptionPane.showMessageDialog(null, "请输入相关信息");
			return;
		}
		
		User user = new User();//创建user对象
		user.setUserName(username);//设置输入的用户名
		user.setPassword(password);//设置输入的密码
		user.setRole(flag);//设置输入的身份
		
	
		Connection con = null;
		
		try {
			con = dbUtil.getConnection();//获取连接
			currentUser=userDao.login(con, user);//获取当前用户名
			
			
			if (currentUser!=null 
					&&(currentUser.getPassword().equals(password))
					&&(currentUser.getRole().equals(flag))) {
				//如果用户名存在。密码正确，身份验证正确
				
				int index=currentUser.getRole();//获取当前用户名的身份
			
				if(index==0) {
					//学生
					jf.dispose();//隐藏当前窗口
					new UserMenuFrm();//打开普通学生用户窗口
				}else if(index==1) {
					//管理员
					jf.dispose();
					new AdminMenuFrm();//打开管理员窗口
				}
			 
			} else {
				//登录失败，则弹出一个显示"用户名或密码,或身份类型错误"，并带有“确定”按钮的模态对话框
				JOptionPane.showMessageDialog(null, "用户名或密码,或身份类型错误");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			try {
				dbUtil.closeCon(con);//关闭连接
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
	}

	public static void main(String[] args) {
		LoginFrm LoginFrm = new LoginFrm();
		// SwingUtilities.invokeLater(createAndShowGUI);
	}
}
