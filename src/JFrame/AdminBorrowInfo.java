package JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import dao.BookDao;
import dao.BorrowDetailDao;
import dao.UserDao;
import model.Book;
import model.BorrowDetail;
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

public class AdminBorrowInfo extends JFrame {
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
	UserDao userDao=new UserDao();
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

	public AdminBorrowInfo() {
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
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "������Ϣ", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(255, 0, 0)));
		panel_1.setBounds(20, 10, 541, 275);// ����������������λ�ã��Լ�����

		/* 1����һ����ͷ������ һά���� */
		String[] title = { "������", "����", "״̬", "����ʱ��", "����ʱ��" };
		/* ����ĸ����м�¼ ���ÿյĶ�λ����ռλ */
		String[][] dates = {};

		/* 2��Ȼ��ʵ���� ����2���ؼ����� */
		// DefaultTableModel�� TableModel��һ��ʵ�֣���ʹ��һ�� Vector���洢��Ԫ���ֵ���󣬸� Vector�ɶ��
		// Vector��ɡ�
		model = new DefaultTableModel(dates, title);// ����һ��DefaultTableModel����ͨ���� datas�� title���ݵ� setDataVector��������ʼ���ñ�
		table = new JTable(model);// ����Ĭ�ϵı��ģ�ͣ����˱������ģ������Ϊ model��������ע��Ϊ������ģ�͵�������֪ͨ��

		/* 3������putDates�������������ݿ��ȡ����ͼ����Ϣ���ݷ��õ�table�� */
		putDates_check(new BorrowDetail());// ��ȡ���ݿ����ݷ���table��

		panel_1.setLayout(null);// �����������Ĳ��ֹ�������null��ʾ��ʹ��
		JScrollPane jscrollpane = new JScrollPane();// ����һ�����й����������
		jscrollpane.setBounds(20, 22, 496, 234);// ���ù���������������λ�ã��Լ�����
		jscrollpane.setViewportView(table);// ��table���õ������������ʾ
		panel_1.add(jscrollpane);// ������������jscrollpane��ӵ�������panel_1��
		jf.getContentPane().add(panel_1);// ��������panel_1��ӵ����������
	
	}
	
	
	
		// �����ݿ��в�ѯ�õ�¼�û��Ľ�����Ϣ�б�
	private void putDates_check(BorrowDetail borrowDetail) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();// ���� DefaultTableModel����ģ��
		model.setRowCount(0);// ����ģ���е�����
		//Integer userId = LoginFrm.currentUser.getUserId();// ��ȡLoginFrm�����currentUser�е��û�id
		
		Connection con = null;
		try {
			con = dbUtil.getConnection();// ��ȡ����
			//borrowDetail.setUserId(userId);// ����borrowDetail������û�idΪuserId
			// ����bdetailDao��list�����������ݿ��в�ѯ�õ�¼�û��Ľ�����Ϣ
			
			ResultSet list = bdetailDao.list(con, borrowDetail);
			while (list.next()) {
				// �����࣬�̳��� AbstractList��ʵ���� List �ӿ� ���ײ�������ṹ��Ԫ�ؿ��ظ������򣨴��˳�򣩣�֧���±��������ʣ�����nullԪ��
				Vector rowData = new Vector();// ����Vector����			
				rowData.add(list.getInt("userId"));
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

	}
}
