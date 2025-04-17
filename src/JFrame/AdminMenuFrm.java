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
		jf=new JFrame("����Ա����");//��������Ϊ"����Ա����"�� ����
		jf.setBounds(400, 100, 600, 429);//���ô��ڵĳ�ʼλ�ã�����Ļ����400�����أ�����Ļ�Ϸ�100�����أ����ڵĿ���600������429
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//�����������ڵ�Ӧ�ó���
		jf.getContentPane().setLayout(null);//���ò��ֹ�������null��ʾ��ʹ��	
		
		JMenuBar menuBar = new JMenuBar();//�����˵������
		jf.setJMenuBar(menuBar);//��jf���������м���˵������menuBar
								
		JMenu mnNewMenu_2 = new JMenu("�鼮����");//�����˵����
		menuBar.add(mnNewMenu_2);//���˵����mnNewMenu_2���뵽�˵������menuBar��
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("�鼮���");//�����˵������
		/*��д�˵������mntmNewMenuItem_2������¼����������������˵�����¼���
		*MouseAdapter:���ڽ�������¼��ĳ�����������,�˳����ඨ��ķ������ǿյģ�ֻ��Ҫ��������ĵ��¼���д������*/
		mntmNewMenuItem_2.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {//��갴���¼�
				jf.dispose();//������ǰ���ڣ����ͷŵ�ǰ������ʹ�õ���Դ
				new AdminBookAdd();//��������Աͼ����Ӵ���
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_2);//���˵������mntmNewMenuItem_2���뵽�˵����mnNewMenu_2��
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("�鼮�޸�");//�����˵������
		//��д�˵������mntmNewMenuItem_3������¼����������������˵�����¼���
		mntmNewMenuItem_3.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {//��갴���¼�
				jf.dispose();//������ǰ���ڣ����ͷŵ�ǰ������ʹ�õ���Դ
				new AdminBookEdit();//��������Աͼ���޸Ĵ���
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_3);//���˵������mntmNewMenuItem_3���뵽�˵����mnNewMenu_2��
		
		JMenu menu1 = new JMenu("�û�����");//�����˵����
		menuBar.add(menu1);//���˵����menu1���뵽�˵������menuBar��
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("�û���Ϣ");//�����˵������
		//��д�˵������mntmNewMenuItem_4������¼����������������˵�����¼���
		mntmNewMenuItem_4.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {//��갴���¼�
				jf.dispose();//������ǰ���ڣ����ͷŵ�ǰ������ʹ�õ���Դ
				new AdminUserInfo();//��������Ա�û���Ϣ����
			}
		});
		menu1.add(mntmNewMenuItem_4);//���˵������mntmNewMenuItem_4���뵽�˵����menu1��
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("������Ϣ");//�����˵������
		//��д�˵������mntmNewMenuItem_5������¼����������������˵�����¼���
		mntmNewMenuItem_5.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {//��갴���¼�
				jf.dispose();//������ǰ���ڣ����ͷŵ�ǰ������ʹ�õ���Դ
				new AdminBorrowInfo();//��������Ա������Ϣ����
			}
		});
		menu1.add(mntmNewMenuItem_5);//���˵������mntmNewMenuItem_5���뵽�˵����menu1��
		
		JMenu mnNewMenu_1 = new JMenu("�˳�ϵͳ");//�����˵����
		//��д�˵����mnNewMenu_1������¼����������������˵������¼���
		mnNewMenu_1.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {//��갴���¼�
				//����һ����ʾ"��ӭ�ٴ�ʹ��"�������С�ȷ������ť��ģ̬�Ի���
				JOptionPane.showMessageDialog(null, "��ӭ�ٴ�ʹ��");
				jf.dispose();//������ǰ���ڣ����ͷŵ�ǰ������ʹ�õ���Դ
			}
		});
		menuBar.add(mnNewMenu_1);//���˵����mnNewMenu_1���뵽�˵������menuBar��		
				
		jf.setVisible(true);//���ù���Ա���洰�ڿɼ�
		jf.setResizable(true);//���ù���Ա���洰�ڿɵ�����С
	}
}