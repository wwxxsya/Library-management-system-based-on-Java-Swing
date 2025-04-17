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
	private JFrame jf;// ����
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

	DbUtil dbUtil = new DbUtil();// ���������
	UserDao userDao = new UserDao();// userDao
	private JLabel lblNewLabel_1;
	int flag=-1;

	public LoginFrm() {
		jf = new JFrame("ͼ�����");// ��������Ϊ"ͼ�����"�� ����
		jf.getContentPane().setFont(new Font("��Բ", Font.BOLD, 16));// ���ô˴�����������������
		jf.setBounds(600, 250, 510, 410);// ���ô��ڵĳ�ʼλ�ã�����Ļ����600�����أ�����Ļ�Ϸ�250�����أ����ڵĿ���510������410
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// �����������ڵ�Ӧ�ó���
		jf.getContentPane().setLayout(null);// ���ò��ֹ�������null��ʾ��ʹ��
		

		// ��1�����
		JLabel label = new JLabel("�û�����");//��ǩ
		label.setForeground(Color.BLACK);//�����������ɫ
		label.setFont(new Font("��Բ", Font.BOLD, 16));//���ñ�ǩ������
		label.setBounds(110, 65, 75, 40);//���ñ�ǩ������λ�ã��Լ�����
		jf.getContentPane().add(label);//����ǩ���label��ӵ����������
		
		textField = new JTextField();//�ı���
		textField.setFont(new Font("��Բ", Font.BOLD, 14));//�����ı��������
		textField.setForeground(Color.BLACK);//�����������ɫ
		textField.setColumns(10);//�����ı���ĳ���
		textField.setBounds(198, 71, 164, 30);//�����ı��������λ�ã��Լ�����
		jf.getContentPane().add(textField);//���ı������textField��ӵ����������
		/*
		 * ��78~95�б�д�û����ı���ļ��������������ı���Ķ�����
		 * ��������˽�����������������������뽹��ʱ�����ָ���Ľ���������ԴӸ�������ս����¼���
		 * FocusListener()��һ���ӿڣ������Ǻͽӿ��йص������࣬��д�˸ýӿ��е�ȫ������
		 */
		textField.addFocusListener(new FocusListener() {		
			@Override
			public void focusGained(FocusEvent e) {//��ý���
			}
			//��focusLost()�����б�д���û��ı���ʧȥ��꽹��ʱ�����ı��������ݵ�У���߼���
			@Override
			public void focusLost(FocusEvent e) {//ʧȥ����				
				String text = textField.getText();//��ȡ�ı����е�����
				//���á������ࡱ�ж��û����Ƿ�Ϊ�գ����û���Ϊ��ʱ��ʾ���û�������Ϊ�ա��������������ɫΪ��ɫ
				if(toolUtil.isEmpty(text)){
					usernameMes.setText("�û�������Ϊ��");
					usernameMes.setForeground(Color.RED);
				}else{    //���û�����Ϊ��ʱ��ʾ���̡��������������ɫΪ��ɫ
					usernameMes.setText("��");
					usernameMes.setForeground(Color.GREEN);
				}
			}
		});
		
		usernameMes = new JLabel("");//��ʾ��ǩ
		usernameMes.setFont(new Font("��Բ", Font.BOLD, 15));//���ñ�ǩ������
		usernameMes.setBounds(372, 71, 122, 27);//������ʾ��ǩ������λ�ã��Լ�����
		jf.getContentPane().add(usernameMes);//����ǩ���usernameMes��ӵ����������
		
		// ��2�����
		JLabel label_1 = new JLabel("���룺");//��ǩ
		label_1.setForeground(Color.BLACK);//�����������ɫ
		label_1.setFont(new Font("��Բ", Font.BOLD, 16));//���ñ�ǩ������
		label_1.setBounds(120, 108, 65, 40);//���ñ�ǩ������λ�ã��Լ�����
		jf.getContentPane().add(label_1);//����ǩ���label_1��ӵ����������
		
		textField_1 = new JTextField();//�ı���
		textField_1.setFont(new Font("��Բ", Font.BOLD, 14));//�����ı��������
		textField_1.setToolTipText("���벻��Ϊ��");//��������Ƶ��ؼ���ʱ�ĵ�����ʾ��Ϣ
		textField_1.setColumns(10);//�����ı���ĳ���
		textField_1.setBounds(198, 114, 164, 30);//�����ı��������λ�ã��Լ�����
		jf.getContentPane().add(textField_1);//���ı������textField_1��ӵ����������
		/*
		 * ��120~145�б�д�����ı���ļ��������������ı���Ķ�����
		 * ��������˽�����������������������뽹��ʱ�����ָ���Ľ���������ԴӸ�������ս����¼���
		 * FocusListener()��һ���ӿڣ������Ǻͽӿ��йص������࣬��д�˸ýӿ��е�ȫ������
		 */
		textField_1.addFocusListener(new FocusListener() {
			//��focusLost()�����б�д�������ı���ʧȥ��꽹��ʱ�����ı��������ݵ�У���߼���
			@Override
			public void focusLost(FocusEvent e) {	
				String pwd=textField_1.getText();//��ȡ�ı����е�����
				//���á������ࡱ�ж������Ƿ�Ϊ�գ�������Ϊ��ʱ��ʾ�����벻��Ϊ�ա��������������ɫΪ��ɫ
				if(toolUtil.isEmpty(pwd)){
					passwordMes.setText("���벻��Ϊ��");
					passwordMes.setForeground(Color.RED);
				}else{
					//ʹ��������ʽ��������ĸ�ʽ����Ϊ6~16λ����ĸ�����ֵ����
					boolean flag=pwd.matches("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$");
					if(flag){
						passwordMes.setText("��");
						passwordMes.setForeground(Color.GREEN);
					}else{
						//����������벻���Ϲ淶��������ʾ
						JOptionPane.showMessageDialog(null, "������Ϊ6-16λ���ֺ���ĸ�����");
						passwordMes.setText("");//��������ı���
					}	
				}				
			}
			@Override
			public void focusGained(FocusEvent e) {	//��ý���
			}
		});
		
		passwordMes = new JLabel("");//��ʾ��ǩ
		passwordMes.setFont(new Font("��Բ", Font.BOLD, 15));//���ñ�ǩ������
		passwordMes.setBounds(372, 114, 122, 27);//������ʾ��ǩ������λ�ã��Լ�����
		jf.getContentPane().add(passwordMes);//����ǩ���passwordMes��ӵ����������
		
		// ��4�����
		//���������
		JLabel label_2 = new JLabel("Ȩ�ޣ�");// ��ǩ
		label_2.setForeground(Color.BLACK);// �����������ɫ
		label_2.setFont(new Font("��Բ", Font.BOLD, 16));// ���ñ�ǩ������
		label_2.setBounds(120, 150, 65, 40);// ���ñ�ǩ������λ�ã��Լ�����
		jf.getContentPane().add(label_2);// ����ǩ���label_2��ӵ����������

		JPanel panel = new JPanel();// ������
		JComboBox<String> comboBox = new JComboBox<>();// ���������
		comboBox.setBounds(200,150,164,35);
		comboBox.addItem("��ѡ���û�����");
		comboBox.addItem("ѧ��");
		comboBox.addItem("����Ա");
		
		
		 
		JTextField textField=new JTextField(20);		 
		comboBox.addActionListener(e->{
			String item=(String)comboBox.getSelectedItem();
			if(item.equals("ѧ��")){
				flag=0;
			}else if(item.equals("����Ա")) {
				flag=1;
			}
			else{    //���û�����Ϊ��ʱ��ʾ���̡��������������ɫΪ��ɫ
				panelMes.setText("�û�����Ϊ��");
				panelMes.setForeground(Color.RED);
			}
			
		});
	
		// ��JComboBox�����JTextField���������������
		panel.add(comboBox);
		panel.add(textField);
		jf.getContentPane().add(comboBox);// ��jf���������м���ҳͷ��JPanel������
		

		// ��5�����
		button = new JButton("��¼");// ��ť
		button.setFont(new Font("��Բ", Font.BOLD, 15));// ���ð�ť������
		button.setBounds(120, 280, 70, 30);// ���ð�ť������λ�ã��Լ�����
		jf.getContentPane().add(button);// ����ť���button��ӵ����������
		/*
		 * ��230~234�б�дע�ᰴť�ļ�������������ע�ᰴť�ĵ����¼���
		 * ����������¼������������¼�����ʱ��������actionPerformed�������Է������¼����д���
		 * ActionListener()��һ���ӿڣ������Ǻͽӿ��йص������࣬��д�˸ýӿ��е�ȫ������
		 */
		// ��д��¼��ť�����¼���������
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegCheck(e);// ����RegCheck����������ע����Ϣ����ȷ�ԡ������Խ����ж�
			}
		});

		button_1 = new JButton("ע��");// ��ť
		button_1.setFont(new Font("��Բ", Font.BOLD, 15));// ���ð�ť������
		button_1.setBounds(245, 280, 70, 30);// ���ð�ť������λ�ã��Լ�����
		jf.getContentPane().add(button_1);// ����ť���button_1��ӵ����������
		/*
		 * ��245~250�б�д��¼��ť�ļ�������������ע�ᰴť�ĵ����¼���
		 * ����������¼������������¼�����ʱ��������actionPerformed�������Է������¼����д���
		 * ActionListener()��һ���ӿڣ������Ǻͽӿ��йص������࣬��д�˸ýӿ��е�ȫ������
		 */
		// ��дע�ᰴť�����¼���������
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jf.setVisible(false);// �����û���¼����Ϊ���ɼ�
				new RegFrm();// �����û�ע�ᴰ��
			}
		});
		
		jf.setVisible(true);// �����û�ע�ᴰ�ڿɼ�
		jf.setResizable(true);// �����û�ע�ᴰ�ڿɵ�����С
	}
			
		//������ע����Ϣ����ȷ�ԡ������Խ����ж�
	protected void RegCheck(ActionEvent e) {
		String username = textField.getText();// ��ȡ�û���
		String password = textField_1.getText();// ��ȡ����
		
		//���á������ࡱ�ж��û��������롢�ֻ����Ƿ�Ϊ�գ���Ϊ���򵯳�һ����ʾ"�����������Ϣ"�������С�ȷ������ť��ģ̬�Ի���
		if (toolUtil.isEmpty(username) || toolUtil.isEmpty(password)) {
			JOptionPane.showMessageDialog(null, "�����������Ϣ");
			return;
		}
		
		User user = new User();//����user����
		user.setUserName(username);//����������û���
		user.setPassword(password);//�������������
		user.setRole(flag);//������������
		
	
		Connection con = null;
		
		try {
			con = dbUtil.getConnection();//��ȡ����
			currentUser=userDao.login(con, user);//��ȡ��ǰ�û���
			
			
			if (currentUser!=null 
					&&(currentUser.getPassword().equals(password))
					&&(currentUser.getRole().equals(flag))) {
				//����û������ڡ�������ȷ�������֤��ȷ
				
				int index=currentUser.getRole();//��ȡ��ǰ�û��������
			
				if(index==0) {
					//ѧ��
					jf.dispose();//���ص�ǰ����
					new UserMenuFrm();//����ͨѧ���û�����
				}else if(index==1) {
					//����Ա
					jf.dispose();
					new AdminMenuFrm();//�򿪹���Ա����
				}
			 
			} else {
				//��¼ʧ�ܣ��򵯳�һ����ʾ"�û���������,��������ʹ���"�������С�ȷ������ť��ģ̬�Ի���
				JOptionPane.showMessageDialog(null, "�û���������,��������ʹ���");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			try {
				dbUtil.closeCon(con);//�ر�����
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
