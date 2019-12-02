import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.util.Vector;


import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.*;

class nonLectureWindow extends JFrame{
	private String[] day = {"������", "ȭ����", "������", "�����", "�ݿ���", "�����", "�Ͽ���"};
	private String[] h = {"0��", "1��", "2��", "3��", "4��", "5��", "6��", "7��", "8��", "9��", "10��", "11��", "12��"
			, "13��","14��", "15��", "16��", "17��", "18��", "19��","20��", "21��","22��", "23��"};
	private String[] m = {"0��", "10��", "20��", "30��", "40��", "50��"};
	private Vector<nonLecture> nonLectures = new Vector<nonLecture>();
	private String yourFont = "����ǹ��� �ѳ�ü Pro";
	private Color yourColor = new Color(240, 232, 232);
	private int sH, sM, eH, eM, dM;
	private boolean isReturn = false;
	
	
	public void callWindow(Vector<String> sNameList){
		getContentPane().removeAll();
		this.refreshGUI();
		setTitle("Timetable Magician");
		Container c = getContentPane();
		c.setLayout(null);
		
		c.setBackground(yourColor);
		
		JLabel l1 = new JLabel("���� �̸��� �Է��ϼ���");
		l1.setBounds(20, 10, 500, 20);
		l1.setFont(new Font(yourFont, Font.PLAIN, 21));
		c.add(l1);
		
		JTextField sName = new JTextField(20);
		sName.setBounds(20, 50, 480, 50);
		sName.setFont(new Font(yourFont, Font.PLAIN, 21));
		sName.setHorizontalAlignment(JTextField.CENTER);
		c.add(sName);
		
		JLabel l2 = new JLabel("�ش�Ǵ� ���Ͽ� üũ�� �ּ���");
		l2.setBounds(20, 115, 500, 20);
		l2.setFont(new Font(yourFont, Font.PLAIN, 21));
		c.add(l2);
		
		
		JCheckBox[] dayBox = new JCheckBox[7];
		for(int i=0;i<7;i++) {
			dayBox[i]=new JCheckBox(day[i], false);
			dayBox[i].setBounds(90*i+10, 140, 70, 30);
			dayBox[i].setBackground(yourColor);
			c.add(dayBox[i]);
		}
		
		
		
		JLabel l3 = new JLabel("�ð��� ������ �ּ���");
		l3.setBounds(20, 190, 500, 20);
		l3.setFont(new Font(yourFont, Font.PLAIN, 21));
		c.add(l3);
		
		JComboBox<String> sHour = new JComboBox<String>(h);
		sHour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> cb = (JComboBox<String>)e.getSource(); 
				sH = cb.getSelectedIndex();
			}
		});
		sHour.setBounds(20, 230, 70, 20);
		c.add(sHour);
		
		JComboBox<String> sMin = new JComboBox<String>(m);
		sMin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> cb = (JComboBox<String>)e.getSource(); 
				sM = cb.getSelectedIndex();
			}
		});
		sMin.setBounds(110, 230, 70, 20);
		c.add(sMin);
		
		JLabel l4 = new JLabel("~");
		l4.setBounds(190, 230, 40, 20);
		l4.setFont(new Font(yourFont, Font.PLAIN, 21));
		l4.setHorizontalAlignment(JLabel.CENTER);
		c.add(l4);
		
		
		JComboBox<String> eHour = new JComboBox<String>(h);
		eHour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> cb = (JComboBox<String>)e.getSource(); 
				eH = cb.getSelectedIndex();
			}
		});
		eHour.setBounds(240, 230, 70, 20);
		c.add(eHour);
		
		JComboBox<String> eMin = new JComboBox<String>(m);
		eMin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> cb = (JComboBox<String>)e.getSource(); 
				eM = cb.getSelectedIndex();
			}
		});
		eMin.setBounds(330, 230, 70, 20);
		c.add(eMin);
		
		JLabel l5 = new JLabel("�̵��ð�");
		l5.setBounds(450, 190, 150, 20);
		l5.setFont(new Font(yourFont, Font.PLAIN, 21));
		c.add(l5);
		
		JComboBox<String> dMin = new JComboBox<String>(m);
		eMin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> cb = (JComboBox<String>)e.getSource(); 
				dM = cb.getSelectedIndex();
			}
		});
		dMin.setBounds(450, 230, 70, 20);
		c.add(dMin);
		
		JButton addS = new JButton("�߰�");
		addS.setBounds(20, 300, 80, 50);
		addS.setFont(new Font(yourFont, Font.PLAIN, 14));
		c.add(addS);
		
		
		JList<String> sList = new JList<String>(sNameList);
		sList.setBounds(650, 20, 800, 350);
		sList.setFont(new Font(yourFont, Font.PLAIN, 18));
		c.add(sList);

		
		JButton run = new JButton("����");
		run.setBounds(160, 300, 80, 50);
		run.setFont(new Font(yourFont, Font.PLAIN, 14));
		c.add(run);
		
		setSize(1700,450);
		setVisible(true);
		
		
		addS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tempText;
				tempText = sName.getText();
				tempText=tempText.trim();
				if(tempText.compareTo("")==0) {
					JOptionPane.showMessageDialog(c, "�̸��� ���� �����Դϴ�!");
				}
				else {
					boolean isChecked = false;
					for(int i=0;i<7;i++) {
						if(dayBox[i].isSelected()) {
							isChecked = true;
							break;
						}
					}
					if(!isChecked) {
						JOptionPane.showMessageDialog(c, "������ Ȯ���� �ּ���!");
					}
					else {
						String inform="*"+tempText;
						nonLecture n = new nonLecture(new Vector<timeBlock>(), tempText, dM);
						inform+=" "+h[sH]+" "+m[sM]+"~"+h[eH]+" "+m[eM];
						for(int i=0;i<7;i++) {
							if(dayBox[i].isSelected()) {
								timeBlock tB = new timeBlock(sH+sM/6, eH+eM/6, i);
								n.pushTime(tB);
								inform+=" "+day[i];
							}
						}
						sNameList.add(inform);
						nonLectures.add(n);
						callWindow(sNameList);
						JOptionPane.showMessageDialog(c, "�߰��Ǿ����ϴ�!");
					}
				}
			}
		});
		
		run.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isReturn = true;
				dispose();
			}
		});

		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	
	public Vector<nonLecture> getNonLecture(){
		return nonLectures;
	}
	
	public void refreshGUI() {
		revalidate();
		repaint();
	}
	public boolean isClosed() {
		return isReturn;
	}
}