package JFrame;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;

import javax.swing.JTextField;

import dao.UserDao;
import model.User;
import utils.DbUtil;
import utils.toolUtil;

import javax.swing.JRadioButton;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class RegFrm extends JFrame {
	private JFrame jf;//窗口
	private JTextField textField;
	private JTextField textField_1;
	private JLabel label_2;
	private JTextField textField_2;
	private JLabel label_3;
	private JRadioButton rdbtnNewRadioButton_1;
	private JLabel usernameMes;
	private JLabel passwordMes;
	private JLabel phoneMes;
	private JButton button;
	private JButton button_1;
	private JRadioButton rdbtnNewRadioButton;
	
	DbUtil dbUtil=new DbUtil();//工具类对象
	UserDao userDao=new UserDao();//userDao
	private JLabel lblNewLabel_1;
	public RegFrm() {
		jf=new JFrame("用户注册");//创建标题为"用户注册"的 窗口
		jf.getContentPane().setFont(new Font("幼圆", Font.BOLD, 16));//设置此窗体的容器对象的字体
		jf.setBounds(600, 250,510, 410);//设置窗口的初始位置，距屏幕左面600个像素，距屏幕上方250个像素，窗口的宽是510，高是410
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//结束窗口所在的应用程序
		jf.getContentPane().setLayout(null);//设置布局管理器，null表示不使用		
		
		
		//第一行组件
		lblNewLabel_1 = new JLabel("用户注册");//标签
		lblNewLabel_1.setFont(new Font("幼圆", Font.BOLD, 22));//设置标签的字体
		lblNewLabel_1.setBounds(184, 10, 122, 51);//设置标签的坐标位置，以及宽、高
		jf.getContentPane().add(lblNewLabel_1);//将标签组件lblNewLabel_1添加到窗体面板中
		
		//第二行组件
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
		
		//第三行组件
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
		
		//第四行组件		
		label_2 = new JLabel("手机号：");//标签
		label_2.setForeground(Color.BLACK);//设置字体的颜色
		label_2.setFont(new Font("幼圆", Font.BOLD, 16));//设置标签的字体
		label_2.setBounds(110, 150, 75, 40);//设置标签的坐标位置，以及宽、高
		jf.getContentPane().add(label_2);//将标签组件label_2添加到窗体面板中
		
		textField_2 = new JTextField();//文本框
		textField_2.setFont(new Font("幼圆", Font.BOLD, 14));//设置文本框的字体
		textField_2.setColumns(10);//设置文本框的长度
		textField_2.setBounds(198, 156, 164, 30);//设置文本框的坐标位置，以及宽、高
		jf.getContentPane().add(textField_2);//将文本框组件textField_2添加到窗体面板中
		/*
		 * 第169~145行编写手机号文本框的监视器，来监听文本框的动作。
		 * 这里添加了焦点监视器，当该组件获得输入焦点时，添加指定的焦点监视器以从该组件接收焦点事件。
		 * FocusListener()是一个接口，以下是和接口有关的匿名类，重写了该接口中的全部方法
		 */
		textField_2.addFocusListener(new FocusListener() {
			//在focusLost()函数中编写了手机号文本框失去鼠标焦点时，对文本框中内容的校验逻辑。
			@Override
			public void focusLost(FocusEvent e) {
				String phone=textField_2.getText();//获取文本框中的内容
				//调用“工具类”判断手机号是否为空，当手机号为空时提示“密码不能为空”，设置字体的颜色为红色
				if(toolUtil.isEmpty(phone)){
					phoneMes.setText("手机号不能为空");
					phoneMes.setForeground(Color.RED);
				}else{
					//使用正则表达式定义手机号的格式
					boolean flag=phone.matches("^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$");
					if(flag){
						phoneMes.setText("√");
						phoneMes.setForeground(Color.GREEN);
					}else{
						JOptionPane.showMessageDialog(null, "请输入正确的手机号格式");
						phoneMes.setText("");
					}	
				}			
			}			
			@Override
			public void focusGained(FocusEvent e) {//获得焦点							
			}
		});
		
		phoneMes = new JLabel("");//提示标签
		phoneMes.setFont(new Font("幼圆", Font.BOLD, 15));//设置标签的字体
		phoneMes.setBounds(372, 142, 122, 30);//设置提示标签的坐标位置，以及宽、高
		jf.getContentPane().add(phoneMes);//将标签组件phoneMes添加到窗体面板中
		
		//第五行组件		
		label_3 = new JLabel("性别：");//标签
		label_3.setForeground(Color.BLACK);//设置字体的颜色
		label_3.setFont(new Font("幼圆", Font.BOLD, 16));//设置标签的字体
		label_3.setBounds(123, 184, 65, 40);//设置标签的坐标位置，以及宽、高
		jf.getContentPane().add(label_3);//将标签组件label_3添加到窗体面板中
		
		rdbtnNewRadioButton = new JRadioButton("男");//单选按钮
		rdbtnNewRadioButton.setFont(new Font("幼圆", Font.BOLD, 16));//设置单选按钮的字体
		rdbtnNewRadioButton.setBounds(198, 192, 58, 23);//设置单选按钮的坐标位置，以及宽、高
		jf.getContentPane().add(rdbtnNewRadioButton);//将单选按钮组件rdbtnNewRadioButton添加到窗体面板中
		
		rdbtnNewRadioButton_1 = new JRadioButton("女");//单选按钮
		rdbtnNewRadioButton_1.setFont(new Font("幼圆", Font.BOLD, 16));//设置单选按钮的字体
		rdbtnNewRadioButton_1.setBounds(287, 192, 65, 23);//设置单选按钮的坐标位置，以及宽、高
		rdbtnNewRadioButton_1.setSelected(true);//设置默认为选中状态
		jf.getContentPane().add(rdbtnNewRadioButton_1);//将单选按钮组件rdbtnNewRadioButton_1添加到窗体面板中
		ButtonGroup bg=new ButtonGroup();//创建一个ButtonGroup按钮组件
		bg.add(rdbtnNewRadioButton_1);//将rdbtnNewRadioButton_1单选按钮组件加入到同一个ButtonGroup组中
		bg.add(rdbtnNewRadioButton);//将rdbtnNewRadioButton单选按钮组件加入到同一个ButtonGroup组中
		
		//第六行组件						
		button = new JButton("注册");//按钮
		button.setFont(new Font("幼圆", Font.BOLD, 15));//设置按钮的字体
		button.setBounds(120, 299, 75, 30);//设置按钮的坐标位置，以及宽、高
		jf.getContentPane().add(button);//将按钮组件button添加到窗体面板中
		/*
		 * 第230~234行编写注册按钮的监视器，来监听注册按钮的单击事件。
		 * 这里添加了事件监视器，当事件发生时，将调用actionPerformed方法，对发生的事件进行处理。
		 * ActionListener()是一个接口，以下是和接口有关的匿名类，重写了该接口中的全部方法
		 */
		// 重写注册按钮动作事件监听方法
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegCheck(e);//调用RegCheck方法对所填注册信息的正确性、完整性进行判断
			}
		});
						
		button_1 = new JButton("前往登录页面");//按钮
		button_1.setFont(new Font("幼圆", Font.BOLD, 15));//设置按钮的字体
		button_1.setBounds(245, 299, 132, 30);//设置按钮的坐标位置，以及宽、高
		jf.getContentPane().add(button_1);//将按钮组件button_1添加到窗体面板中
		/*
		 * 第245~250行编写登录按钮的监视器，来监听注册按钮的单击事件。
		 * 这里添加了事件监视器，当事件发生时，将调用actionPerformed方法，对发生的事件进行处理。
		 * ActionListener()是一个接口，以下是和接口有关的匿名类，重写了该接口中的全部方法
		 */
		// 重写前往登录页面按钮动作事件监听方法
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jf.setVisible(false);//设置用户注册窗口为不可见
				new LoginFrm();//创建用户登录窗口
			}
		});
		
		
		jf.setVisible(true);//设置用户注册窗口可见
		jf.setResizable(true);//设置用户注册窗口可调整大小
	}
	//对所填注册信息的正确性、完整性进行判断
	protected void RegCheck(ActionEvent e) {
		String username=textField.getText();//获取用户名
		String password=textField_1.getText();//获取密码
		String phone=textField_2.getText();//获取手机号
		String sex="";
		if(rdbtnNewRadioButton.isSelected()){//如果“男”单选按钮被选中，设置sex=男
			sex=rdbtnNewRadioButton.getText();
		}else{
			sex=rdbtnNewRadioButton_1.getText();//如果“女”单选按钮被选中，设置sex=女
		}
		//调用“工具类”判断用户名、密码、手机号是否为空，若为空则弹出一个显示"请输入相关信息"，并带有“确定”按钮的模态对话框
		if (toolUtil.isEmpty(username) || toolUtil.isEmpty(password)||toolUtil.isEmpty(phone)) {
			JOptionPane.showMessageDialog(null, "请输入相关信息");
			return;
		}
		User user = new User();//创建user对象
		user.setUserName(username);//设置用户名
		user.setPassword(password);//设置密码
		user.setSex(sex);//设置性别
		user.setPhone(phone);//设置手机号
		user.setRole(0);//设置角色，默认普通用户
		Connection con = null;
		try {
			con = dbUtil.getConnection();//获取连接
			int i = userDao.addUser(con, user);//调用userDao的addUser方法，将新用户信息添加到数据库表中
			if (i == 2) {//如果用户名存在，则弹出一个显示"该用户名已存在,请重新注册"，并带有“确定”按钮的模态对话框
				JOptionPane.showMessageDialog(null, "该用户名已存在,请重新注册");
			} else if (i == 0) {//注册失败，则弹出一个显示"注册失败"，并带有“确定”按钮的模态对话框
				JOptionPane.showMessageDialog(null, "注册失败");
			} else {//注册成功，则弹出一个显示"注册成功"，并带有“确定”按钮的模态对话框
				JOptionPane.showMessageDialog(null, "注册成功");
				jf.dispose();//撤销当前用户注册窗口，并释放当前窗口所使用的资源
				new LoginFrm();//创建用户登录窗口
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
		RegFrm regFrm=new RegFrm();
	}
}