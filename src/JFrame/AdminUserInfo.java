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
		jf = new JFrame("����Ա����");
		jf.setBounds(400, 50, 600, 672);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.getContentPane().setLayout(null);// ���ò��ֹ�������null��ʾ��ʹ��

		JMenuBar menuBar = new JMenuBar();// �����˵������
		jf.setJMenuBar(menuBar);// ��jf���������м���˵������menuBar

		JMenu mnNewMenu_2 = new JMenu("�鼮����");// �����˵����
		menuBar.add(mnNewMenu_2);// ���˵����mnNewMenu_2���뵽�˵������menuBar��

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("�鼮���");// �����˵������
		/*
		 * ��д�˵������mntmNewMenuItem_2������¼����������������˵�����¼���
		 * MouseAdapter:���ڽ�������¼��ĳ�����������,�˳����ඨ��ķ������ǿյģ�ֻ��Ҫ��������ĵ��¼���д������
		 */
		mntmNewMenuItem_2.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {// ��갴���¼�
				jf.dispose();// ������ǰ���ڣ����ͷŵ�ǰ������ʹ�õ���Դ
				new AdminBookAdd();// ��������Աͼ����Ӵ���
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_2);// ���˵������mntmNewMenuItem_2���뵽�˵����mnNewMenu_2��

		JMenuItem mntmNewMenuItem_3 = new JMenuItem("�鼮�޸�");// �����˵������
		// ��д�˵������mntmNewMenuItem_3������¼����������������˵�����¼���
		mntmNewMenuItem_3.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {// ��갴���¼�
				jf.dispose();// ������ǰ���ڣ����ͷŵ�ǰ������ʹ�õ���Դ
				new AdminBookEdit();// ��������Աͼ���޸Ĵ���
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_3);// ���˵������mntmNewMenuItem_3���뵽�˵����mnNewMenu_2��

		JMenu menu1 = new JMenu("�û�����");// �����˵����
		menuBar.add(menu1);// ���˵����menu1���뵽�˵������menuBar��

		JMenuItem mntmNewMenuItem_4 = new JMenuItem("�û���Ϣ");// �����˵������
		// ��д�˵������mntmNewMenuItem_4������¼����������������˵�����¼���
		mntmNewMenuItem_4.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {// ��갴���¼�
				jf.dispose();// ������ǰ���ڣ����ͷŵ�ǰ������ʹ�õ���Դ
				new AdminUserInfo();// ��������Ա�û���Ϣ����
			}
		});
		menu1.add(mntmNewMenuItem_4);// ���˵������mntmNewMenuItem_4���뵽�˵����menu1��

		JMenuItem mntmNewMenuItem_5 = new JMenuItem("������Ϣ");// �����˵������
		// ��д�˵������mntmNewMenuItem_5������¼����������������˵�����¼���
		mntmNewMenuItem_5.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {// ��갴���¼�
				jf.dispose();// ������ǰ���ڣ����ͷŵ�ǰ������ʹ�õ���Դ
				new AdminBorrowInfo();// ��������Ա������Ϣ����
			}
		});
		menu1.add(mntmNewMenuItem_5);// ���˵������mntmNewMenuItem_5���뵽�˵����menu1��

		JMenu mnNewMenu_1 = new JMenu("�˳�ϵͳ");// �����˵����
		// ��д�˵����mnNewMenu_1������¼����������������˵������¼���
		mnNewMenu_1.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {// ��갴���¼�
				// ����һ����ʾ"��ӭ�ٴ�ʹ��"�������С�ȷ������ť��ģ̬�Ի���
				JOptionPane.showMessageDialog(null, "��ӭ�ٴ�ʹ��");
				jf.dispose();// ������ǰ���ڣ����ͷŵ�ǰ������ʹ�õ���Դ
			}
		});
		menuBar.add(mnNewMenu_1);// ���˵����mnNewMenu_1���뵽�˵������menuBar��

		jf.setVisible(true);// ���ù���Ա���洰�ڿɼ�
		jf.setResizable(true);// ���ù���Ա���洰�ڿɵ�����С

		JPanel panel_1 = new JPanel();// ����һ��������
		panel_1.setLayout(null);// ���ò��ֹ�������null��ʾ��ʹ��
		// �����������ı߿�
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "�û���Ϣ", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(255, 0, 0)));
		panel_1.setBounds(20, 10, 541, 275);// ����������������λ�ã��Լ�����

		/* 1����һ����ͷ������ һά���� */
		String[] title = { "���", "�û���", "����", "�Ա�", "�绰" };
		/* ����ĸ����м�¼ ���ÿյĶ�λ����ռλ */
		String[][] dates = {};

		/* 2��Ȼ��ʵ���� ����2���ؼ����� */
		// DefaultTableModel�� TableModel��һ��ʵ�֣���ʹ��һ�� Vector���洢��Ԫ���ֵ���󣬸� Vector�ɶ��
		// Vector��ɡ�
		model = new DefaultTableModel(dates, title);// ����һ��DefaultTableModel����ͨ���� datas�� title���ݵ� setDataVector��������ʼ���ñ�
		table = new JTable(model);// ����Ĭ�ϵı��ģ�ͣ����˱������ģ������Ϊ model��������ע��Ϊ������ģ�͵�������֪ͨ��

		/* 3������putDates�������������ݿ��ȡ����ͼ����Ϣ���ݷ��õ�table�� */
		putDates(new User());// ��ȡ���ݿ����ݷ���table��

		panel_1.setLayout(null);// �����������Ĳ��ֹ�������null��ʾ��ʹ��
		JScrollPane jscrollpane = new JScrollPane();// ����һ�����й����������
		jscrollpane.setBounds(20, 22, 496, 234);// ���ù���������������λ�ã��Լ�����
		jscrollpane.setViewportView(table);// ��table���õ������������ʾ
		panel_1.add(jscrollpane);// ������������jscrollpane��ӵ�������panel_1��
		jf.getContentPane().add(panel_1);// ��������panel_1��ӵ����������

		// �ڶ��������
		JPanel panel_2 = new JPanel();// ����һ��������
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "�û��༭", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(255, 0, 0)));
		panel_2.setBounds(20, 310, 541, 292);// ����������������λ�ã��Լ�����
		jf.getContentPane().add(panel_2);// ��������panel_2��ӵ����������
		panel_2.setLayout(null);// ���ò��ֹ�������null��ʾ��ʹ��

		JLabel label = new JLabel("��ţ�");// ��ǩ
		label.setFont(new Font("��Բ", Font.BOLD, 14));// ��������
		label.setBounds(30, 30, 45, 27);// ���ñ�ǩ������λ�ã��Լ�����
		panel_2.add(label);// ����ǩ���label��ӵ����panel_2��

		textField_1 = new JTextField();// �ı���
		textField_1.setEditable(false);// ���ò��ɱ༭
		textField_1.setColumns(10);// �����ı���ĳ���
		textField_1.setBounds(70, 30, 100, 27);// �����ı��������λ�ã��Լ�����
		panel_2.add(textField_1);// ���ı������textField_1��ӵ����panel_2��

		JLabel label_1 = new JLabel("�û�����");// ��ǩ
		label_1.setFont(new Font("��Բ", Font.BOLD, 14));// ��������
		label_1.setBounds(180, 30, 60, 27);// ���ñ�ǩ������λ�ã��Լ�����
		panel_2.add(label_1);// ����ǩ���label_1��ӵ����panel_2��

		textField_2 = new JTextField();// �ı���
		textField_2.setColumns(10);// �����ı���ĳ���
		textField_2.setBounds(242, 30, 100, 27);// �����ı��������λ�ã��Լ�����
		panel_2.add(textField_2);// ���ı������textField_2��ӵ����panel_2��

		JLabel label_2 = new JLabel("���룺");// ��ǩ
		label_2.setFont(new Font("��Բ", Font.BOLD, 14));// ��������
		label_2.setBounds(378, 30, 45, 27);// ���ñ�ǩ������λ�ã��Լ�����
		panel_2.add(label_2);// ����ǩ���label_2��ӵ����panel_2��

		textField_3 = new JTextField();// �ı���
		textField_3.setColumns(10);// �����ı���ĳ���
		textField_3.setBounds(418, 30, 100, 27);// �����ı��������λ�ã��Լ�����
		panel_2.add(textField_3);// ���ı������textField_2��ӵ����panel_2��

		JLabel label_3 = new JLabel("�Ա�");// ��ǩ
		label_3.setFont(new Font("��Բ", Font.BOLD, 14));// ��������
		label_3.setBounds(30, 80, 45, 27);// ���ñ�ǩ������λ�ã��Լ�����
		panel_2.add(label_3);// ����ǩ���label_3��ӵ����panel_2��

		textField_4 = new JTextField();// �ı���
		textField_4.setColumns(10);// �����ı���ĳ���
		textField_4.setBounds(70, 80, 100, 27);// �����ı��������λ�ã��Լ�����
		panel_2.add(textField_4);// ���ı������textField_4��ӵ����panel_2��

		JLabel label_4 = new JLabel("�ֻ��ţ�");// ��ǩ
		label_4.setFont(new Font("��Բ", Font.BOLD, 14));// ��������
		label_4.setBounds(180, 80, 60, 27);// ���ñ�ǩ������λ�ã��Լ�����
		panel_2.add(label_4);// ����ǩ���label_4��ӵ����panel_2��

		textField_5 = new JTextField();// �ı���
		textField_5.setColumns(10);// �����ı���ĳ���
		textField_5.setBounds(242, 80, 100, 27);// �����ı��������λ�ã��Լ�����
		panel_2.add(textField_5);// ���ı������textField_5��ӵ����panel_2��

		JButton btnNewButton_1 = new JButton("�޸�");// ��ť
		btnNewButton_1.setFont(new Font("��Բ", Font.BOLD, 14));// ��������
		btnNewButton_1.setBounds(394, 100, 93, 35);// ���ñ�ǩ������λ�ã��Լ�����
		panel_2.add(btnNewButton_1);// �����������btnNewButton_1��ӵ����panel_2��
		/*
		 * ��д�޸İ�ť�ļ��������������޸İ�ť�ĵ����¼���
		 * ����������¼������������¼�����ʱ��������actionPerformed�������Է������¼����д���
		 * ActionListener()��һ���ӿڣ������Ǻͽӿ��йص������࣬��д�˸ýӿ��е�ȫ������
		 */
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userName = textField_2.getText();// ��ȡ�û���
				String password = textField_3.getText();// ��ȡ����
				String sex = textField_4.getText();// ��ȡ
				String phone = textField_5.getText();// ��ȡ
				String userId = textField_1.getText();// ��ȡ���

				// ���á������ࡱ�ж��Ƿ�Ϊ�գ���Ϊ���򵯳�һ����ʾ"�������������"�������С�ȷ������ť��ģ̬�Ի���
				if (toolUtil.isEmpty(userId) || toolUtil.isEmpty(userName) || toolUtil.isEmpty(password)
						|| toolUtil.isEmpty(sex) || toolUtil.isEmpty(phone)) {
					JOptionPane.showMessageDialog(null, "�������������");
					return;
				}

				User user = new User();//�����û�����
				user.setUserId(Integer.parseInt(userId));
				user.setUserName(userName);
				user.setPassword(password);
				user.setSex(sex);
				user.setPhone(phone);

				Connection con = null;
				try {
					con = dbUtil.getConnection();// ��ȡ����
					// ����bookDao��update����������ͼ����Ϣ���µ����ݿ����
					int i = userDao.update(con, user);
					if (i == 1) {// �޸ĳɹ����򵯳�һ����ʾ"�޸ĳɹ�"�������С�ȷ������ť��ģ̬�Ի���
						JOptionPane.showMessageDialog(null, "�޸ĳɹ�");
					} else {// �޸�ʧ�ܣ��򵯳�һ����ʾ"�޸�ʧ��"�������С�ȷ������ť��ģ̬�Ի���
						JOptionPane.showMessageDialog(null, "�޸�ʧ��");
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "�޸��쳣");
				} finally {
					try {
						dbUtil.closeCon(con);// �ر�����
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				/* ����putDates�������������ݿ��ȡ����ͼ����Ϣ���ݷ��õ�table�� */
				putDates(new User());
			}
		});
	}

	// �����ݿ��в�ѯͼ����Ϣ�б�
	private void putDates(User user) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();// ���� DefaultTableModel����ģ��
		model.setRowCount(0);// ����ģ���е�����
		Connection con = null;
		try {
			con = dbUtil.getConnection();// ��ȡ����
			// ����userDao��list�����������ݿ��в�ѯ���е�ͼ����Ϣ
			ResultSet resultSet = userDao.list(con, user);
			while (resultSet.next()) {
				// �����࣬�̳��� AbstractList��ʵ���� List �ӿ� ���ײ�������ṹ��Ԫ�ؿ��ظ������򣨴��˳�򣩣�֧���±��������ʣ�����nullԪ��
				
				Vector rowData = new Vector();// ����Vector����
				rowData.add(resultSet.getInt("userId"));// ��userId�浽Vector������
				rowData.add(resultSet.getString("userName"));// ���û����浽Vector������
				rowData.add(resultSet.getString("password"));// ������浽Vector������
				rowData.add(resultSet.getString("sex"));// ���Ա�浽Vector������
				rowData.add(resultSet.getString("phone"));// ���绰�浽Vector������

				model.addRow(rowData);// ���һ�е�ģ�͵Ľ�β
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeCon(con);// �ر�����
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// ��дtableģ�͵�����¼�����������������񵥻��¼���
		// MouseAdapter:���ڽ�������¼��ĳ�����������,�˳����ඨ��ķ������ǿյģ�ֻ��Ҫ��������ĵ��¼���д������
		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {// ��갴���¼�
				tableMousePressed(evt);// ����tableMousePressed����
			}
		});
	}

	// �������ȡ����
	/**
	 * @param evt
	 */
	protected void tableMousePressed(MouseEvent evt) {
		int row = table.getSelectedRow();// ��ñ�ѡ���е�����
		// �ӱ��ģ���л��ָ����Ԫ��(��row�У���0��)��ֵ
		Integer userId = (Integer) table.getValueAt(row, 0);
		User user = new User();// ����user����
		user.setUserId(userId);

		Connection con = null;
		try {
			con = dbUtil.getConnection();// ��ȡ����
			// ����bookDao��list�����������ݿ��в�ѯ��bookId��Ӧ��ͼ����Ϣ
			ResultSet list = userDao.list(con, user);
			if (list.next()) {
				textField_1.setText(list.getString("userId"));// ����Ÿ�ֵ��textField_1ͼ�����ı���
				textField_2.setText(list.getString("userName"));// ����ֵ��textField_2ͼ�������ı���
				textField_3.setText(list.getString("password"));// ��ֵ��textField_3ͼ�������ı���
				textField_4.setText(list.getString("sex"));// ��ֵ��textField_5ͼ������ı���
				textField_5.setText(list.getString("phone"));// ��ֵ��textField_4ͼ��۸��ı���

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeCon(con);// �ر�����
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
