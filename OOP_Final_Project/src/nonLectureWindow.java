import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.util.Vector;


import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.*;

@SuppressWarnings("serial")
class nonLectureWindow extends JFrame{
	private String[] day = {"월요일", "화요일", "수요일", "목요일", "금요일", "토요일", "일요일"};
	private String[] h = {"0시", "1시", "2시", "3시", "4시", "5시", "6시", "7시", "8시", "9시", "10시", "11시", "12시"
			, "13시","14시", "15시", "16시", "17시", "18시", "19시","20시", "21시","22시", "23시"};
	private String[] m = {"0분", "10분", "20분", "30분", "40분", "50분"};
	private Vector<nonLecture> nonLectures = new Vector<nonLecture>();
	private String yourFont = "배달의민족 한나체 Pro";
	private Color yourColor = new Color(240, 232, 232);
	private int sH, sM, eH, eM, dM;
	private boolean isReturn = false;
	
	
	public void callWindow(Vector<String> sNameList, Vector<lecture> lectures){
		getContentPane().removeAll();
		this.refreshGUI();
		setTitle("Timetable Magician");
		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(yourColor);
		
		JLabel l1 = new JLabel("일정 이름을 입력하세요");
		l1.setBounds(20, 10, 500, 20);
		l1.setFont(new Font(yourFont, Font.PLAIN, 21));
		c.add(l1);
		
		JTextField sName = new JTextField(20);
		sName.setBounds(20, 50, 480, 50);
		sName.setFont(new Font(yourFont, Font.PLAIN, 21));
		sName.setHorizontalAlignment(JTextField.CENTER);
		c.add(sName);
		
		JLabel l2 = new JLabel("해당되는 요일에 체크해 주세요");
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
		
		
		
		JLabel l3 = new JLabel("시간을 설정해 주세요");
		l3.setBounds(20, 190, 500, 20);
		l3.setFont(new Font(yourFont, Font.PLAIN, 21));
		c.add(l3);
		
		JComboBox<String> sHour = new JComboBox<String>(h);
		sHour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unchecked")
				JComboBox<String> cb = (JComboBox<String>)e.getSource(); 
				sH = cb.getSelectedIndex();
			}
		});
		sHour.setBounds(20, 230, 70, 20);
		c.add(sHour);
		
		JComboBox<String> sMin = new JComboBox<String>(m);
		sMin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unchecked")
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
				@SuppressWarnings("unchecked")
				JComboBox<String> cb = (JComboBox<String>)e.getSource(); 
				eH = cb.getSelectedIndex();
			}
		});
		eHour.setBounds(240, 230, 70, 20);
		c.add(eHour);
		
		JComboBox<String> eMin = new JComboBox<String>(m);
		eMin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unchecked")
				JComboBox<String> cb = (JComboBox<String>)e.getSource(); 
				eM = cb.getSelectedIndex();
			}
		});
		eMin.setBounds(330, 230, 70, 20);
		c.add(eMin);
		
		JLabel l5 = new JLabel("이동시간");
		l5.setBounds(450, 190, 150, 20);
		l5.setFont(new Font(yourFont, Font.PLAIN, 21));
		c.add(l5);
		
		JComboBox<String> dMin = new JComboBox<String>(m);
		dMin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unchecked")
				JComboBox<String> cb = (JComboBox<String>)e.getSource(); 
				dM = cb.getSelectedIndex();
			}
		});
		dMin.setBounds(450, 230, 70, 20);
		c.add(dMin);
		
		JButton addS = new JButton("추가");
		addS.setBounds(20, 300, 80, 50);
		addS.setFont(new Font(yourFont, Font.PLAIN, 14));
		c.add(addS);
		
		
		JList<String> sList = new JList<String>(sNameList);
		sList.setBounds(650, 20, 800, 350);
		sList.setFont(new Font(yourFont, Font.PLAIN, 18));
		c.add(sList);

		
		JButton run = new JButton("다음");
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
				if(tempText.compareTo("")==0) { //If schedule is null
					JOptionPane.showMessageDialog(c, "이름이 없는 일정입니다!");
				}
				else {
					boolean isChecked = false;
					for(int i=0;i<7;i++) {
						if(dayBox[i].isSelected()) {
							isChecked = true;
							break;
						}
					}
					if(!isChecked) { //If schedule has no day
						JOptionPane.showMessageDialog(c, "요일을 확인해 주세요!");
					}
					else {
						String inform="* "+tempText;
						nonLecture n = new nonLecture(new Vector<timeBlock>(), tempText, (float)dM/6);
						inform+=" "+h[sH]+" "+m[sM]+"~"+h[eH]+" "+m[eM];
						for(int i=0;i<7;i++) {
							if(dayBox[i].isSelected()) {
								if(eH+eM/6<sH+sM/6) {
									timeBlock tB = new timeBlock(sH+sM/6, 24, i);
									n.pushTime(tB);
									tB=new timeBlock(0, eH+eM/6, (i+1)%7);
									n.pushTime(tB);
								}
								else {
									timeBlock tB = new timeBlock(sH+(float)sM/6, eH+(float)eM/6, i);
									n.pushTime(tB);
								}
								inform+=" "+day[i];
							}
						}
						boolean isBreak = false;
						for(int i=0;i<nonLectures.size();i++) { //If schedule is intersected with others
							if(nonLectures.elementAt(i).isIntersected(n.getTime())) {
								JOptionPane.showMessageDialog(c, "다른 스케줄과 시간이 겹칩니다!");
								isBreak = true;
								break;
							}
						}
						for(int i=0;i<lectures.size();i++) { //If schedule is intersected with lectures
							if(n.isIntersected(lectures.elementAt(i))) {
								JOptionPane.showMessageDialog(c, "다른 강의와 시간이 겹칩니다!");
								isBreak = true;
								break;
							}
						}
						if(!isBreak) {
							sNameList.add(inform);
							nonLectures.add(n);
							callWindow(sNameList, lectures);
							JOptionPane.showMessageDialog(c, "추가되었습니다!");	
						}
						
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
