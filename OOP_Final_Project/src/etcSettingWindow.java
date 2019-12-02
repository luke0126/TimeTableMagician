import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


class etcSettingWindow extends JFrame{
	private String yourFont = "����ǹ��� �ѳ�ü Pro";
	private Color yourColor = new Color(240, 232, 232);
	private String[] h = {"0��", "1��", "2��", "3��", "4��", "5��", "6��", "7��", "8��", "9��", "10��", "11��", "12��"
			, "13��","14��", "15��", "16��", "17��", "18��", "19��","20��", "21��","22��", "23��"};
	private String[] m = {"0��", "10��", "20��", "30��", "40��", "50��"};
	private String[] credit = {"8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25"};
	private int sH, sM, eH, eM, mC, MC;
	private boolean isReturn;
	public void callWindow() {
		getContentPane().removeAll();
		this.refreshGUI();
		setTitle("Timetable Magician");
		Container c = getContentPane();
		c.setLayout(null);
		
		c.setBackground(yourColor);
		
		
		JLabel l1 = new JLabel("��� ���� ���� �ð�");
		l1.setBounds(20, 10, 220, 20);
		l1.setFont(new Font(yourFont, Font.PLAIN, 21));
		c.add(l1);
		
		JLabel l2 = new JLabel("��� ���� ���� �ð�");
		l2.setBounds(240, 10, 220, 20);
		l2.setFont(new Font(yourFont, Font.PLAIN, 21));
		c.add(l2);
		
		JComboBox<String> sHour = new JComboBox<String>(h);
		sHour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> cb = (JComboBox<String>)e.getSource(); 
				sH = cb.getSelectedIndex();
			}
		});
		sHour.setBounds(20, 40, 70, 20);
		c.add(sHour);
		
		JComboBox<String> sMin = new JComboBox<String>(m);
		sMin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> cb = (JComboBox<String>)e.getSource(); 
				sM = cb.getSelectedIndex();
			}
		});
		sMin.setBounds(110, 40, 70, 20);
		c.add(sMin);
		
		JLabel l0 = new JLabel("~");
		l0.setBounds(190, 40, 40, 20);
		l0.setFont(new Font(yourFont, Font.PLAIN, 21));
		l0.setHorizontalAlignment(JLabel.CENTER);
		c.add(l0);
		
		
		JComboBox<String> eHour = new JComboBox<String>(h);
		eHour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> cb = (JComboBox<String>)e.getSource(); 
				eH = cb.getSelectedIndex();
			}
		});
		eHour.setBounds(240, 40, 70, 20);
		c.add(eHour);
		
		JComboBox<String> eMin = new JComboBox<String>(m);
		eMin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> cb = (JComboBox<String>)e.getSource(); 
				eM = cb.getSelectedIndex();
			}
		});
		eMin.setBounds(330, 40, 70, 20);
		c.add(eMin);
		
		JLabel l3 = new JLabel("�ּ� ���� ����");
		l3.setBounds(20, 80, 220, 20);
		l3.setFont(new Font(yourFont, Font.PLAIN, 21));
		c.add(l3);
		
		JLabel l4 = new JLabel("�ִ� ���� ����");
		l4.setBounds(240, 80, 220, 20);
		l4.setFont(new Font(yourFont, Font.PLAIN, 21));
		c.add(l4);
		
		JComboBox<String> mCredit = new JComboBox<String>(credit);
		mCredit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> cb = (JComboBox<String>)e.getSource();
				mC=cb.getSelectedIndex()+8;
			}
		});
		mCredit.setBounds(40, 110, 70, 20);
		c.add(mCredit);
		
		JComboBox<String> MCredit = new JComboBox<String>(credit);
		MCredit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> cb = (JComboBox<String>)e.getSource();
				MC=cb.getSelectedIndex()+8;
			}
		});
		MCredit.setBounds(260, 110, 70, 20);
		c.add(MCredit);
		
		JButton run = new JButton("�����ϱ�");
		run.setBounds(100, 200, 200, 50);
		run.setFont(new Font(yourFont, Font.PLAIN, 14));
		c.add(run);
		
		run.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mC>MC) {
					JOptionPane.showMessageDialog(c, "������ Ȯ���� �ּ���!");
				}
				else {
					isReturn = true;
					dispose();	
				}
				
			}
		});
		
		setSize(450,350);
		setVisible(true);
	}
	
	
	public void refreshGUI() {
		revalidate();
		repaint();
	}
	public boolean isClosed() {
		return isReturn;
	}


	public int getsH() {
		return sH;
	}


	public int getsM() {
		return sM;
	}


	public int geteH() {
		return eH;
	}


	public int geteM() {
		return eM;
	}


	public int getmC() {
		return mC;
	}


	public int getMC() {
		return MC;
	}
	
}