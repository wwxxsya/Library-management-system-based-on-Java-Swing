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
		jf = new JFrame();// ����һ���µĴ���
		jf.setTitle("�û�ҳ��");// ���ñ���Ϊ"�û�ҳ��"
		jf.setBounds(250, 100, 700, 702);// ���ô��ڵĳ�ʼλ�ã�����Ļ����250�����أ�����Ļ�Ϸ�100�����أ����ڵĿ���700������702
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// �����������ڵ�Ӧ�ó���
		jf.getContentPane().setLayout(null);// ���ò��ֹ�������null��ʾ��ʹ��
		jf.setVisible(true);// �����û�ҳ�洰�ڿɼ�
		jf.setResizable(true);// �����û�ҳ�洰�ڿɵ�����С

		// ��һ�������
		lblNewLabel_2 = new JLabel("��ӭ��,");// ��ǩ
		lblNewLabel_2.setFont(new Font("��Բ", Font.BOLD, 18));// ���ñ�ǩ������
		lblNewLabel_2.setBounds(254, 11, 258, 28);// ���ñ�ǩ������λ�ã��Լ�����
		jf.getContentPane().add(lblNewLabel_2);// ����ǩ���lblNewLabel_2��ӵ����������

		lblNewLabel_1 = new JLabel("New label");// ��ǩ
		lblNewLabel_1.setForeground(Color.RED);// ���ñ�ǩ�������ɫ
		lblNewLabel_1.setFont(new Font("��Բ", Font.BOLD, 18));// ���ñ�ǩ������
		lblNewLabel_1.setBounds(315, 10, 197, 28);// ���ñ�ǩ������λ�ã��Լ�����
		jf.getContentPane().add(lblNewLabel_1);// ����ǩ���lblNewLabel_1��ӵ����������
		lblNewLabel_1.setText(LoginFrm.currentUser.getUserName());// ��ȡLoginFrm�����currentUser�е��û���

		// �ڶ����ֽ�����Ϣ���
		JPanel panel_1 = new JPanel();// ����һ��������
		// �����������ı߿�
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "������Ϣ", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(255, 0, 0)));
		panel_1.setBounds(23, 48, 651, 139);// ����������������λ�ã��Լ�����

		/* 1����һ����ͷ������ һά���� */
		String[] title = { "���", "����", "״̬", "����ʱ��", "����ʱ��" };
		/* ����ĸ����м�¼ ���ÿյĶ�λ����ռλ */
		String[][] dates = {};

		/* 2��Ȼ��ʵ���� ����2���ؼ����� */
		// DefaultTableModel�� TableModel��һ��ʵ�֣���ʹ��һ�� Vector���洢��Ԫ���ֵ���󣬸� Vector�ɶ��
		// Vector��ɡ�
		model = new DefaultTableModel(dates, title);// ����һ��DefaultTableModel����ͨ���� datas�� title���ݵ� setDataVector��������ʼ���ñ�
		table = new JTable();// ����Ĭ�ϵı��ģ��
		table.setModel(model);// ���˱������ģ������Ϊ model��������ע��Ϊ������ģ�͵�������֪ͨ��

		/* 3������putDates�������������ݿ��ȡ���ı��˵Ľ�����Ϣ���ݷ��õ�table�� */
		putDates(new BorrowDetail());

		panel_1.setLayout(null);// �����������Ĳ��ֹ�������null��ʾ��ʹ��
		JScrollPane jscrollpane = new JScrollPane();// ����һ�����й����������
		jscrollpane.setBounds(20, 22, 607, 98);// ���ù���������������λ�ã��Լ�����
		jscrollpane.setViewportView(table);// ��table���õ������������ʾ
		panel_1.add(jscrollpane);// ������������jscrollpane��ӵ�������panel_1��
		jf.getContentPane().add(panel_1);// ��������panel_1��ӵ����������

		// �������ֻ������
		JPanel panel = new JPanel();// ����һ��������
		// �����������ı߿�
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "����", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(255, 0, 0)));
		panel.setBounds(23, 194, 651, 70);// ����������������λ�ã��Լ�����
		jf.getContentPane().add(panel);// ��������panel��ӵ����������
		panel.setLayout(null);// �����������Ĳ��ֹ�������null��ʾ��ʹ��

		JLabel lblNewLabel = new JLabel("��ţ�");// ��ǩ
		lblNewLabel.setBounds(90, 15, 51, 27);// ���ñ�ǩ������λ�ã��Լ�����
		lblNewLabel.setFont(new Font("��Բ", Font.BOLD, 16));// ���ñ�ǩ������
		panel.add(lblNewLabel);// ����ǩ���lblNewLabel��ӵ�������panel��

		textField = new JTextField();// �ı���
		textField.setBounds(145, 18, 116, 24);// �����ı��������λ�ã��Լ�����
		textField.setColumns(10);// �����ı���ĳ���
		panel.add(textField);// ���ı������textField��ӵ�������panel��

		btnBackBook = new JButton("����");// ��ť
		btnBackBook.setFont(new Font("��Բ", Font.BOLD, 15));// ���ð�ť������
		btnBackBook.setBounds(299, 15, 85, 31);// ���ð�ť������λ�ã��Լ�����
		panel.add(btnBackBook);// ����ť���btnBackBook��ӵ�������panel��
		btnBackBook.setVisible(false);// ���á����顱��ť���ɼ�
		/*
		 * ��д�����顱��ť�ļ������������������顱��ť�ĵ����¼���
		 * ����������¼������������¼�����ʱ��������actionPerformed�������Է������¼����д���
		 * ActionListener()��һ���ӿڣ������Ǻͽӿ��йص������࣬��д�˸ýӿ��е�ȫ������
		 */
		btnBackBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String BorrowStr = textField.getText();// ��ȡ"���"�ı����е�����
				// ���á������ࡱ�жϱ���Ƿ�Ϊ�գ���Ϊ���򵯳�һ����ʾ"��ѡ��δ�����鼮"�������С�ȷ������ť��ģ̬�Ի���
				if (toolUtil.isEmpty(BorrowStr)) {
					JOptionPane.showMessageDialog(null, "��ѡ��δ�����鼮");
					return;
				}
				BorrowDetail detail = new BorrowDetail();// ���������������
				detail.setBorrowId(Integer.parseInt(BorrowStr));// ���ñ��
				detail.setStatus(2);// ����״̬Ϊ���ѻ���
				// ���á������ࡱ��ȡ��ǰʱ�䣬�����õ�����ʱ��
				detail.setReturnTime(toolUtil.getTime());
				Connection con = null;
				try {
					con = dbUtil.getConnection();// ��ȡ����
					// ����bdetailDao��returnBook�������������ݿ�Ļ�����Ϣ
					int i = bdetailDao.returnBook(con, detail);
					if (i == 1) {// ���³ɹ��򷵻ء�����ɹ���ģ̬�Ի���
						JOptionPane.showMessageDialog(null, "����ɹ�");
					} else {// ����ʧ���򷵻ء�����ʧ�ܡ�ģ̬�Ի���
						JOptionPane.showMessageDialog(null, "����ʧ��");
					}
				} catch (Exception e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "�����쳣");
				} finally {
					try {
						dbUtil.closeCon(con);// �ر�����
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				putDates(new BorrowDetail());// ����putDates���������ݿ��в�ѯ�õ�¼�û��Ľ�����Ϣ�б�
			}
		});

		button = new JButton("�˳�ϵͳ");// ��ť
		button.setFont(new Font("��Բ", Font.BOLD, 15));// ���ð�ť������
		button.setBounds(407, 15, 103, 31);// ���ð�ť������λ�ã��Լ�����
		panel.add(button);// ����ť���button��ӵ�������panel��
		/*
		 * ��д���˳�ϵͳ����ť�ļ����������������˳�ϵͳ����ť�ĵ����¼���
		 * ����������¼������������¼�����ʱ��������actionPerformed�������Է������¼����д���
		 * ActionListener()��һ���ӿڣ������Ǻͽӿ��йص������࣬��д�˸ýӿ��е�ȫ������
		 */
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "��ӭ�ٴ�ʹ��");
				jf.dispose();
			}
		});

		// ���Ĳ����鼮��Ϣ���

		JPanel panel_4 = new JPanel();// ����һ��������
		// �����������ı߿�
		panel_4.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "�鼮��Ϣ", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(255, 0, 0)));
		panel_4.setBounds(23, 277, 651, 248);// ����������������λ�ã��Լ�����

		/* 1����һ����ͷ������ һά���� */
		String[] title2 = { "���", "����", "����", "����" };
		/* ����ĸ����м�¼ ���ÿյĶ�λ����ռλ */
		String[][] dates2 = {};

		/* 2��Ȼ��ʵ���� ����2���ؼ����� */
		// DefaultTableModel�� TableModel��һ��ʵ�֣���ʹ��һ�� Vector���洢��Ԫ���ֵ���󣬸� Vector�ɶ��
		// Vector��ɡ�
		model_2 = new DefaultTableModel(dates2, title2);// ����һ��DefaultTableModel����ͨ���� datas�� title���ݵ�
														// setDataVector��������ʼ���ñ�
		table_2 = new JTable();// ����Ĭ�ϵı��ģ��
		table_2.setModel(model_2);// ���˱������ģ������Ϊ model��������ע��Ϊ������ģ�͵�������֪ͨ��

		/* 3������putDates�������������ݿ��ȡ���ı��˵Ľ�����Ϣ���ݷ��õ�table�� */
		putDates_2(new Book());

		panel_4.setLayout(null);// �����������Ĳ��ֹ�������null��ʾ��ʹ��
		JScrollPane jscrollpane2 = new JScrollPane();// ����һ�����й����������
		jscrollpane2.setBounds(20, 22, 607, 200);// ���ù���������������λ�ã��Լ�����
		jscrollpane2.setViewportView(table_2);// ��table���õ������������ʾ
		panel_4.add(jscrollpane2);// ������������jscrollpane��ӵ�������panel_1��
		jf.getContentPane().add(panel_4);// ��������panel_1��ӵ����������

		// ���岿�ֻ������
		JPanel panel_5 = new JPanel();// ����һ��������
		// �����������ı߿�
		panel_5.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "����", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(255, 0, 0)));
		panel_5.setBounds(23, 540, 651, 70);// ����������������λ�ã��Լ�����
		jf.getContentPane().add(panel_5);// ��������panel��ӵ����������
		panel_5.setLayout(null);// �����������Ĳ��ֹ�������null��ʾ��ʹ��

		lblNewLabel_5 = new JLabel("��ţ�");// ��ǩ
		lblNewLabel_5.setBounds(42, 25, 51, 27);// ���ñ�ǩ������λ�ã��Լ�����
		lblNewLabel_5.setFont(new Font("��Բ", Font.BOLD, 16));// ���ñ�ǩ������
		panel_5.add(lblNewLabel_5);// ����ǩ���lblNewLabel��ӵ�������panel��

		textField_5 = new JTextField();// �ı���
		textField_5.setEditable(false);// ���ò��ɱ༭
		textField_5.setBounds(88, 25, 116, 24);// �����ı��������λ�ã��Լ�����
		textField_5.setColumns(10);// �����ı���ĳ���
		panel_5.add(textField_5);// ���ı������textField��ӵ�������panel��

		lblNewLabel_6 = new JLabel("������");// ��ǩ
		lblNewLabel_6.setBounds(248, 25, 51, 27);// ���ñ�ǩ������λ�ã��Լ�����
		lblNewLabel_6.setFont(new Font("��Բ", Font.BOLD, 16));// ���ñ�ǩ������
		panel_5.add(lblNewLabel_6);// ����ǩ���lblNewLabel��ӵ�������panel��

		textField_6 = new JTextField();// �ı���
		textField_6.setEditable(false);// ���ò��ɱ༭
		textField_6.setBounds(300, 25, 116, 24);// �����ı��������λ�ã��Լ�����
		textField_6.setColumns(10);// �����ı���ĳ���
		panel_5.add(textField_6);// ���ı������textField��ӵ�������panel��

		JButton btnBackBook_2 = new JButton("����");// ��ť
		btnBackBook_2.setFont(new Font("��Բ", Font.BOLD, 15));// ���ð�ť������
		btnBackBook_2.setBounds(490, 25, 85, 31);// ���ð�ť������λ�ã��Լ�����
		panel_5.add(btnBackBook_2);// ����ť���btnBackBook��ӵ�������panel��
		btnBackBook_2.setVisible(true);// ���á����顱��ť�ɼ�
		/*
		 * ��д�����顱��ť�ļ������������������顱��ť�ĵ����¼���
		 * ����������¼������������¼�����ʱ��������actionPerformed�������Է������¼����д���
		 * ActionListener()��һ���ӿڣ������Ǻͽӿ��йص������࣬��д�˸ýӿ��е�ȫ������
		 */
		btnBackBook_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String BorrowStr = textField_5.getText();// ��ȡ"���"�ı����е�����
				// ���á������ࡱ�жϱ���Ƿ�Ϊ�գ���Ϊ���򵯳�һ����ʾ"��ѡ��δ�����鼮"�������С�ȷ������ť��ģ̬�Ի���

				if (toolUtil.isEmpty(BorrowStr)) {
					JOptionPane.showMessageDialog(null, "��ѡ���鼮");
					return;
				}

				BorrowDetail detail_2 = new BorrowDetail();// ���������������
				detail_2.setUserId(LoginFrm.currentUser.getUserId());

				Connection con = null;
				int bookid = Integer.parseInt(BorrowStr);
				int flag = 0;// �Ƿ����н����¼

				try {
					con = dbUtil.getConnection();// ��ȡ����
					ResultSet list = bdetailDao.list(con, detail_2);
					while (list.next()) {
						if (list.getInt("bookId") == bookid) {					
							int status = list.getInt("status");
							if (status == 1) {// �����ѱ���
								flag = 1;// ���н����¼
								JOptionPane.showMessageDialog(null, "�������ڽ裬���Ȼ��ٽ�");
								break;
							} 							
						}
					}

					if (flag == 0) {// δ�н����¼ֱ�ӽ���					
						lendbook(detail_2);
					}

				} catch (Exception e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "�����쳣");
				} finally {
					try {
						dbUtil.closeCon(con);// �ر�����
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				putDates_2(new Book());// ����putDates���������ݿ��в�ѯ�õ�¼�û��Ľ�����Ϣ�б�
				putDates(new BorrowDetail());
			}
		});

	}

	// ���麯��
	private void lendbook(BorrowDetail borrowDetail) {
		String BorrowStr = textField_5.getText();
		BorrowDetail detail_2 = new BorrowDetail();// ���������������
		detail_2.setUserId(LoginFrm.currentUser.getUserId());
		detail_2.setBookId(Integer.parseInt(BorrowStr));
		detail_2.setStatus(1);// ����״̬Ϊ���ѽ衱
		// ���á������ࡱ��ȡ��ǰʱ�䣬�����õ�����ʱ��
		detail_2.setBorrowTime(toolUtil.getTime());

		Connection con = null;
		try {
			con = dbUtil.getConnection();
			int i = bdetailDao.add(con, detail_2);
			if (i == 0) {
				// ���³ɹ��򷵻ء�����ɹ���ģ̬�Ի���
				JOptionPane.showMessageDialog(null, "����ɹ�");
			} else {// ����ʧ���򷵻ء�����ʧ�ܡ�ģ̬�Ի���
				JOptionPane.showMessageDialog(null, "����ʧ��");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // ��ȡ����

	}

	// �����ݿ��в�ѯ�õ�¼�û��Ľ�����Ϣ�б�
	private void putDates(BorrowDetail borrowDetail) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();// ���� DefaultTableModel����ģ��
		model.setRowCount(0);// ����ģ���е�����
		Integer userId = LoginFrm.currentUser.getUserId();// ��ȡLoginFrm�����currentUser�е��û�id
		Connection con = null;
		try {
			con = dbUtil.getConnection();// ��ȡ����
			borrowDetail.setUserId(userId);// ����borrowDetail������û�idΪuserId
			// ����bdetailDao��list�����������ݿ��в�ѯ�õ�¼�û��Ľ�����Ϣ
			ResultSet list = bdetailDao.list(con, borrowDetail);
			while (list.next()) {
				// �����࣬�̳��� AbstractList��ʵ���� List �ӿ� ���ײ�������ṹ��Ԫ�ؿ��ظ������򣨴��˳�򣩣�֧���±��������ʣ�����nullԪ��
				Vector rowData = new Vector();// ����Vector����
				rowData.add(list.getInt("borrowId"));// ��bookId�浽Vector������
				rowData.add(list.getString("bookName"));// �������浽Vector������
				int status = list.getInt("status");// ��ȡ����״̬
				if (status == 1) {
					rowData.add("�ڽ�");// ������״̬�浽Vector������
				}
				if (status == 2) {
					rowData.add("�ѻ�");// ������״̬�浽Vector������
				}
				// ���á������ࡱ������ʱ����и�ʽ�������浽Vector������
				rowData.add(toolUtil.getDateByTime(list.getLong("borrowTime")));
				// ���á������ࡱ������ʱ����и�ʽ�������浽Vector������
				if (status == 2) {
					rowData.add(toolUtil.getDateByTime(list.getLong("returnTime")));
				}
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
			public void mousePressed(MouseEvent me) {// ��갴���¼�
				putBack(me);// ����putBack����
			}
		});
	}

	// ������ʾ����ͼ���ͼ���ŵ��ı���ͻ��鰴ť
	protected void putBack(MouseEvent me) {
		int row = table.getSelectedRow();// ��ñ�ѡ���е�����
		// �ӱ��ģ���л��ָ����Ԫ��(��row�У���0��)��ֵ
		Integer borrowId = (Integer) table.getValueAt(row, 0);
		// �ӱ��ģ���л��ָ����Ԫ��(��row�У���2��)��ֵ
		String status = (String) table.getValueAt(row, 2);
		textField.setText(borrowId.toString());// ������ͼ��ı�Ÿ�ֵ��textFieldͼ�����ı���
		if (status.equals("�ڽ�")) {
			this.btnBackBook.setVisible(true);// ���ͼ���״̬Ϊ�ڽ裬����ʾ"����"��ť
		} else {
			this.btnBackBook.setVisible(false);// ���ͼ���״̬Ϊ�ѻ�������ʾ"����"��ť
		}
	}

	// ��ʾ���пɽ��鼮��Ϣ�б�
	// �����ݿ��в�ѯͼ����Ϣ�б�
	private void putDates_2(Book book) {
		DefaultTableModel model_2 = (DefaultTableModel) table_2.getModel();// ���� DefaultTableModel����ģ��
		model_2.setRowCount(0);// ����ģ���е�����
		Connection con = null;
		try {
			con = dbUtil.getConnection();// ��ȡ����
			// ����bookDao��list�����������ݿ��в�ѯ���е�ͼ����Ϣ
			ResultSet resultSet = bookDao.listCan(con, book);
			while (resultSet.next()) {
				// �����࣬�̳��� AbstractList��ʵ���� List �ӿ� ���ײ�������ṹ��Ԫ�ؿ��ظ������򣨴��˳�򣩣�֧���±��������ʣ�����nullԪ��

				if (resultSet.getInt("status") == 1) {
					// ״̬Ϊ1�ϼ�,�¼ܲ���ʾ
					Vector rowData = new Vector();// ����Vector����
					rowData.add(resultSet.getInt("bookId"));// ��bookId�浽Vector������
					rowData.add(resultSet.getString("bookName"));// �������浽Vector������
					rowData.add(resultSet.getString("author"));// �����ߴ浽Vector������
					rowData.add(resultSet.getString("remark"));
					model_2.addRow(rowData);// ���һ�е�ģ�͵Ľ�β
				}

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
		table_2.addMouseListener(new MouseAdapter() {
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
		int row = table_2.getSelectedRow();// ��ñ�ѡ���е�����
		// �ӱ��ģ���л��ָ����Ԫ��(��row�У���0��)��ֵ
		Integer bookId = (Integer) table_2.getValueAt(row, 0);
		Book book = new Book();// ����book����
		book.setBookId(bookId);// ����book�����bookId

		Connection con = null;

		try {
			con = dbUtil.getConnection();// ��ȡ����
			// ����bookDao��list�����������ݿ��в�ѯ��bookId��Ӧ��ͼ����Ϣ
			ResultSet list = bookDao.list(con, book);
			if (list.next()) {
				textField_5.setText(list.getString("bookId"));// ����ͼ��ĳ��渳ֵ��textField_5ͼ������ı���
				textField_6.setText(list.getString("bookName"));// ����ͼ��Ŀ�渳ֵ��textField_6ͼ�����ı���
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