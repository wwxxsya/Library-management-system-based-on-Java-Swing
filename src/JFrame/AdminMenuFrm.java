package JFrame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class AdminMenuFrm extends JFrame {
	private JFrame jf;

	public AdminMenuFrm(){
		jf=new JFrame("管理员界面");//创建标题为"管理员界面"的 窗口
		jf.setBounds(400, 100, 600, 429);//设置窗口的初始位置，距屏幕左面400个像素，距屏幕上方100个像素，窗口的宽是600，高是429
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//结束窗口所在的应用程序
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
				
		jf.setVisible(true);//设置管理员界面窗口可见
		jf.setResizable(true);//设置管理员界面窗口可调整大小
	}
}