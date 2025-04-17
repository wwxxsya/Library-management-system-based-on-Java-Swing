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
	private JFrame jf;//����
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
	
	DbUtil dbUtil=new DbUtil();//���������
	UserDao userDao=new UserDao();//userDao
	private JLabel lblNewLabel_1;
	public RegFrm() {
		jf=new JFrame("�û�ע��");//��������Ϊ"�û�ע��"�� ����
		jf.getContentPane().setFont(new Font("��Բ", Font.BOLD, 16));//���ô˴�����������������
		jf.setBounds(600, 250,510, 410);//���ô��ڵĳ�ʼλ�ã�����Ļ����600�����أ�����Ļ�Ϸ�250�����أ����ڵĿ���510������410
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//�����������ڵ�Ӧ�ó���
		jf.getContentPane().setLayout(null);//���ò��ֹ�������null��ʾ��ʹ��		
		
		
		//��һ�����
		lblNewLabel_1 = new JLabel("�û�ע��");//��ǩ
		lblNewLabel_1.setFont(new Font("��Բ", Font.BOLD, 22));//���ñ�ǩ������
		lblNewLabel_1.setBounds(184, 10, 122, 51);//���ñ�ǩ������λ�ã��Լ�����
		jf.getContentPane().add(lblNewLabel_1);//����ǩ���lblNewLabel_1��ӵ����������
		
		//�ڶ������
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
		
		//���������
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
		
		//���������		
		label_2 = new JLabel("�ֻ��ţ�");//��ǩ
		label_2.setForeground(Color.BLACK);//�����������ɫ
		label_2.setFont(new Font("��Բ", Font.BOLD, 16));//���ñ�ǩ������
		label_2.setBounds(110, 150, 75, 40);//���ñ�ǩ������λ�ã��Լ�����
		jf.getContentPane().add(label_2);//����ǩ���label_2��ӵ����������
		
		textField_2 = new JTextField();//�ı���
		textField_2.setFont(new Font("��Բ", Font.BOLD, 14));//�����ı��������
		textField_2.setColumns(10);//�����ı���ĳ���
		textField_2.setBounds(198, 156, 164, 30);//�����ı��������λ�ã��Լ�����
		jf.getContentPane().add(textField_2);//���ı������textField_2��ӵ����������
		/*
		 * ��169~145�б�д�ֻ����ı���ļ��������������ı���Ķ�����
		 * ��������˽�����������������������뽹��ʱ�����ָ���Ľ���������ԴӸ�������ս����¼���
		 * FocusListener()��һ���ӿڣ������Ǻͽӿ��йص������࣬��д�˸ýӿ��е�ȫ������
		 */
		textField_2.addFocusListener(new FocusListener() {
			//��focusLost()�����б�д���ֻ����ı���ʧȥ��꽹��ʱ�����ı��������ݵ�У���߼���
			@Override
			public void focusLost(FocusEvent e) {
				String phone=textField_2.getText();//��ȡ�ı����е�����
				//���á������ࡱ�ж��ֻ����Ƿ�Ϊ�գ����ֻ���Ϊ��ʱ��ʾ�����벻��Ϊ�ա��������������ɫΪ��ɫ
				if(toolUtil.isEmpty(phone)){
					phoneMes.setText("�ֻ��Ų���Ϊ��");
					phoneMes.setForeground(Color.RED);
				}else{
					//ʹ��������ʽ�����ֻ��ŵĸ�ʽ
					boolean flag=phone.matches("^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$");
					if(flag){
						phoneMes.setText("��");
						phoneMes.setForeground(Color.GREEN);
					}else{
						JOptionPane.showMessageDialog(null, "��������ȷ���ֻ��Ÿ�ʽ");
						phoneMes.setText("");
					}	
				}			
			}			
			@Override
			public void focusGained(FocusEvent e) {//��ý���							
			}
		});
		
		phoneMes = new JLabel("");//��ʾ��ǩ
		phoneMes.setFont(new Font("��Բ", Font.BOLD, 15));//���ñ�ǩ������
		phoneMes.setBounds(372, 142, 122, 30);//������ʾ��ǩ������λ�ã��Լ�����
		jf.getContentPane().add(phoneMes);//����ǩ���phoneMes��ӵ����������
		
		//���������		
		label_3 = new JLabel("�Ա�");//��ǩ
		label_3.setForeground(Color.BLACK);//�����������ɫ
		label_3.setFont(new Font("��Բ", Font.BOLD, 16));//���ñ�ǩ������
		label_3.setBounds(123, 184, 65, 40);//���ñ�ǩ������λ�ã��Լ�����
		jf.getContentPane().add(label_3);//����ǩ���label_3��ӵ����������
		
		rdbtnNewRadioButton = new JRadioButton("��");//��ѡ��ť
		rdbtnNewRadioButton.setFont(new Font("��Բ", Font.BOLD, 16));//���õ�ѡ��ť������
		rdbtnNewRadioButton.setBounds(198, 192, 58, 23);//���õ�ѡ��ť������λ�ã��Լ�����
		jf.getContentPane().add(rdbtnNewRadioButton);//����ѡ��ť���rdbtnNewRadioButton��ӵ����������
		
		rdbtnNewRadioButton_1 = new JRadioButton("Ů");//��ѡ��ť
		rdbtnNewRadioButton_1.setFont(new Font("��Բ", Font.BOLD, 16));//���õ�ѡ��ť������
		rdbtnNewRadioButton_1.setBounds(287, 192, 65, 23);//���õ�ѡ��ť������λ�ã��Լ�����
		rdbtnNewRadioButton_1.setSelected(true);//����Ĭ��Ϊѡ��״̬
		jf.getContentPane().add(rdbtnNewRadioButton_1);//����ѡ��ť���rdbtnNewRadioButton_1��ӵ����������
		ButtonGroup bg=new ButtonGroup();//����һ��ButtonGroup��ť���
		bg.add(rdbtnNewRadioButton_1);//��rdbtnNewRadioButton_1��ѡ��ť������뵽ͬһ��ButtonGroup����
		bg.add(rdbtnNewRadioButton);//��rdbtnNewRadioButton��ѡ��ť������뵽ͬһ��ButtonGroup����
		
		//���������						
		button = new JButton("ע��");//��ť
		button.setFont(new Font("��Բ", Font.BOLD, 15));//���ð�ť������
		button.setBounds(120, 299, 75, 30);//���ð�ť������λ�ã��Լ�����
		jf.getContentPane().add(button);//����ť���button��ӵ����������
		/*
		 * ��230~234�б�дע�ᰴť�ļ�������������ע�ᰴť�ĵ����¼���
		 * ����������¼������������¼�����ʱ��������actionPerformed�������Է������¼����д���
		 * ActionListener()��һ���ӿڣ������Ǻͽӿ��йص������࣬��д�˸ýӿ��е�ȫ������
		 */
		// ��дע�ᰴť�����¼���������
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegCheck(e);//����RegCheck����������ע����Ϣ����ȷ�ԡ������Խ����ж�
			}
		});
						
		button_1 = new JButton("ǰ����¼ҳ��");//��ť
		button_1.setFont(new Font("��Բ", Font.BOLD, 15));//���ð�ť������
		button_1.setBounds(245, 299, 132, 30);//���ð�ť������λ�ã��Լ�����
		jf.getContentPane().add(button_1);//����ť���button_1��ӵ����������
		/*
		 * ��245~250�б�д��¼��ť�ļ�������������ע�ᰴť�ĵ����¼���
		 * ����������¼������������¼�����ʱ��������actionPerformed�������Է������¼����д���
		 * ActionListener()��һ���ӿڣ������Ǻͽӿ��йص������࣬��д�˸ýӿ��е�ȫ������
		 */
		// ��дǰ����¼ҳ�水ť�����¼���������
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jf.setVisible(false);//�����û�ע�ᴰ��Ϊ���ɼ�
				new LoginFrm();//�����û���¼����
			}
		});
		
		
		jf.setVisible(true);//�����û�ע�ᴰ�ڿɼ�
		jf.setResizable(true);//�����û�ע�ᴰ�ڿɵ�����С
	}
	//������ע����Ϣ����ȷ�ԡ������Խ����ж�
	protected void RegCheck(ActionEvent e) {
		String username=textField.getText();//��ȡ�û���
		String password=textField_1.getText();//��ȡ����
		String phone=textField_2.getText();//��ȡ�ֻ���
		String sex="";
		if(rdbtnNewRadioButton.isSelected()){//������С���ѡ��ť��ѡ�У�����sex=��
			sex=rdbtnNewRadioButton.getText();
		}else{
			sex=rdbtnNewRadioButton_1.getText();//�����Ů����ѡ��ť��ѡ�У�����sex=Ů
		}
		//���á������ࡱ�ж��û��������롢�ֻ����Ƿ�Ϊ�գ���Ϊ���򵯳�һ����ʾ"�����������Ϣ"�������С�ȷ������ť��ģ̬�Ի���
		if (toolUtil.isEmpty(username) || toolUtil.isEmpty(password)||toolUtil.isEmpty(phone)) {
			JOptionPane.showMessageDialog(null, "�����������Ϣ");
			return;
		}
		User user = new User();//����user����
		user.setUserName(username);//�����û���
		user.setPassword(password);//��������
		user.setSex(sex);//�����Ա�
		user.setPhone(phone);//�����ֻ���
		user.setRole(0);//���ý�ɫ��Ĭ����ͨ�û�
		Connection con = null;
		try {
			con = dbUtil.getConnection();//��ȡ����
			int i = userDao.addUser(con, user);//����userDao��addUser�����������û���Ϣ��ӵ����ݿ����
			if (i == 2) {//����û������ڣ��򵯳�һ����ʾ"���û����Ѵ���,������ע��"�������С�ȷ������ť��ģ̬�Ի���
				JOptionPane.showMessageDialog(null, "���û����Ѵ���,������ע��");
			} else if (i == 0) {//ע��ʧ�ܣ��򵯳�һ����ʾ"ע��ʧ��"�������С�ȷ������ť��ģ̬�Ի���
				JOptionPane.showMessageDialog(null, "ע��ʧ��");
			} else {//ע��ɹ����򵯳�һ����ʾ"ע��ɹ�"�������С�ȷ������ť��ģ̬�Ի���
				JOptionPane.showMessageDialog(null, "ע��ɹ�");
				jf.dispose();//������ǰ�û�ע�ᴰ�ڣ����ͷŵ�ǰ������ʹ�õ���Դ
				new LoginFrm();//�����û���¼����
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
		RegFrm regFrm=new RegFrm();
	}
}