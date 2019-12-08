import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


@SuppressWarnings("serial")
class EtcSettingWindow extends JFrame {
	private String yourFont = "배달의민족 한나체 Pro";
	private Color yourColor = new Color(240, 232, 232);
	private String[] h = {"0시", "1시", "2시", "3시", "4시", "5시", "6시", "7시", "8시", "9시", "10시", "11시", "12시"
			, "13시","14시", "15시", "16시", "17시", "18시", "19시","20시", "21시","22시", "23시"};
	private String[] m = {"0분", "10분", "20분", "30분", "40분", "50분"};
	private String[] credit = {"8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25"};
	private String[] gradeList = {"1학년", "2학년", "3학년", "4학년"};
	private int sH = 0, sM = 0, eH =0, eM = 0, mC = 8, MC = 8, grade = 1;
	private float startTime, endTime;
	private boolean isReturn;
	private Vector<Vector<Lecture>> essential = new Vector<Vector<Lecture>>();
	private Vector<Vector<Lecture>> onlyMajor=new Vector<Vector<Lecture>>();
	private Vector<NonLecture> nonLectures = new Vector<NonLecture>();
	private Vector<Integer> creditList = new Vector<Integer>();
	private Vector<TimeTable> timeTables = new Vector<TimeTable>();
	private Vector<Vector<TimeTable>> timeTablesCases = new Vector<Vector<TimeTable>>();
	private Vector<Vector<Lecture>> lectures;

	
	public EtcSettingWindow(Vector<Vector<Lecture>> lectures) {
		
		this.lectures = lectures;
		getContentPane().removeAll();
		this.refreshGUI();
		setTitle("Timetable Magician");
		Container c = getContentPane();
		c.setLayout(null);
		
		c.setBackground(yourColor);
		
		
		JLabel l1 = new JLabel("희망 강의 시작 시간");
		l1.setBounds(20, 10, 220, 20);
		l1.setFont(new Font(yourFont, Font.PLAIN, 21));
		c.add(l1);
		
		JLabel l2 = new JLabel("희망 강의 종료 시간");
		l2.setBounds(240, 10, 220, 20);
		l2.setFont(new Font(yourFont, Font.PLAIN, 21));
		c.add(l2);
		
		JComboBox<String> sHour = new JComboBox<String>(h); //Set start time
		sHour.setBackground(Color.white);
		sHour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unchecked")
				JComboBox<String> cb = (JComboBox<String>)e.getSource(); 
				sH = cb.getSelectedIndex();
			}
		});
		sHour.setBounds(20, 40, 70, 20);
		c.add(sHour);
		
		JComboBox<String> sMin = new JComboBox<String>(m);
		sMin.setBackground(Color.white);
		sMin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unchecked")
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
		
		
		JComboBox<String> eHour = new JComboBox<String>(h); //Set end time
		eHour.setBackground(Color.white);
		eHour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unchecked")
				JComboBox<String> cb = (JComboBox<String>)e.getSource(); 
				eH = cb.getSelectedIndex();
			}
		});
		eHour.setBounds(240, 40, 70, 20);
		c.add(eHour);
		
		JComboBox<String> eMin = new JComboBox<String>(m);
		eMin.setBackground(Color.white);
		eMin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unchecked")
				JComboBox<String> cb = (JComboBox<String>)e.getSource(); 
				eM = cb.getSelectedIndex();
			}
		});
		eMin.setBounds(330, 40, 70, 20);
		c.add(eMin);
		
		JLabel l3 = new JLabel("최소 수강 학점");
		l3.setBounds(20, 80, 140, 20);
		l3.setFont(new Font(yourFont, Font.PLAIN, 21));
		c.add(l3);
		
		JLabel l4 = new JLabel("최대 수강 학점");
		l4.setBounds(160, 80, 140, 20);
		l4.setFont(new Font(yourFont, Font.PLAIN, 21));
		c.add(l4);
		
		JLabel l5 = new JLabel("현재 학년");
		l5.setBounds(320, 80, 130, 20);
		l5.setFont(new Font(yourFont, Font.PLAIN, 21));
		c.add(l5);
		
		JComboBox<String> mCredit = new JComboBox<String>(credit); //Set credit
		mCredit.setBackground(Color.white);
		mCredit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unchecked")
				JComboBox<String> cb = (JComboBox<String>)e.getSource();
				mC=cb.getSelectedIndex()+8;
			}
		});
		mCredit.setBounds(40, 110, 70, 20);
		c.add(mCredit);
		
		JComboBox<String> MCredit = new JComboBox<String>(credit);
		MCredit.setBackground(Color.white);
		MCredit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unchecked")
				JComboBox<String> cb = (JComboBox<String>)e.getSource();
				MC=cb.getSelectedIndex()+8;
			}
		});
		MCredit.setBounds(180, 110, 70, 20);
		c.add(MCredit);
		
		JComboBox<String> gradeComboBox = new JComboBox<String>(gradeList); //Set grade(level)
		gradeComboBox.setBackground(Color.white);
		gradeComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unchecked")
				JComboBox<String> cb = (JComboBox<String>)e.getSource();
				grade=cb.getSelectedIndex()+1;
			}
		});
		gradeComboBox.setBounds(320, 110, 70, 20);
		c.add(gradeComboBox);
		
		JButton run = new JButton("실행하기");
		run.setBounds(100, 200, 200, 50);
		run.setFont(new Font(yourFont, Font.PLAIN, 14));
		run.setBackground(new Color(96, 125, 240));
		run.setBorderPainted(false);
		run.setForeground(Color.white);
		c.add(run);
		
		run.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mC>MC) { //Error on credit setting
					JOptionPane.showMessageDialog(c, "학점을 확인해 주세요!");
				}
				else if(sH*6+sM>=eH*6+eM) { //Error on time setting
					JOptionPane.showMessageDialog(c, "시간을 확인해 주세요!");
				}
				else {
					run.setText("제작중...");
					arrangeLecture();
					dispose();
					timeTables.elementAt(0).callWindow(timeTables, 0);
				}
				
			}
		});
			
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(450,350);
		setVisible(true);
	}

	
	
	public void refreshGUI() {
		revalidate();
		repaint();
	}

	private void arrangeLecture() {
		
		startTime = (float)sM/6+sH;
		endTime = eH+(float)eM/6;
		
		SortLectures sorter = new SortLectures(grade); 
		Collections.sort(lectures, sorter);
		
		for(int i=0;i<lectures.size();i++) { //Check how many essential lecture vector has
			if(lectures.elementAt(i).elementAt(0).isMajor()&&(lectures.elementAt(i).elementAt(0).getLecture_type()==0||
					lectures.elementAt(i).elementAt(0).getLecture_type()==1)) {//Essential means basic course or essential course
				boolean isBreak=false;
				for(int j=0;j<TimeTableMain.sL.getLectures().size();j++) {
					if(TimeTableMain.sL.getLectures().elementAt(j).isSameLecture(lectures.elementAt(i).firstElement())) {
						isBreak=true;
					}
				}
				if(!isBreak) {
					essential.add(lectures.elementAt(i));
				}
			}
			else {
				break;
			}
		}
		
		int selectedCredit=0;
		
		for(int i=0;i<TimeTableMain.sL.getLectures().size();i++) {
			selectedCredit+=TimeTableMain.sL.getLectures().elementAt(i).getCredit();
		}
		
		pushMajor(TimeTableMain.sL.getLectures(), selectedCredit, 0); //Push essential lectures
		if(onlyMajor.size()==0) {
			onlyMajor.add(TimeTableMain.sL.getLectures());
		}
		for(int i=0;i<onlyMajor.size();i++) {
			timeTablesCases.add(new Vector<TimeTable>());
			pushTimetable(onlyMajor.elementAt(i), creditList.elementAt(i), essential.size()); //Make timetable by backtracking
		}
		
		int maxLength=0;
		for(int i=0;i<timeTablesCases.size();i++) {
			maxLength=maxLength>timeTablesCases.elementAt(i).size()?maxLength:timeTablesCases.elementAt(i).size();
		}
		
		for(int i=0;i<maxLength;i++) {
			for(int j=0;j<timeTablesCases.size();j++) {
				if(timeTablesCases.elementAt(j).size()>i) {
					timeTables.add(timeTablesCases.elementAt(j).elementAt(i));
				}
			}
		}
	}
	
	private void pushMajor(Vector<Lecture> tempLectures, int credit, int pivot) { //Push essential major
		
		for(int i=pivot;i<essential.size();i++) {
			for(int j=0;j<essential.elementAt(i).size();j++) {
				boolean canPush = true;
				for(int k=0;k<nonLectures.size();k++) {
					if(nonLectures.elementAt(k).isIntersected(essential.elementAt(i).elementAt(j))) {
						canPush=false;
						break;
					}
				}
				for(int k=0;k<tempLectures.size();k++) {
					if(tempLectures.elementAt(k).isIntersected(essential.elementAt(i).elementAt(j).getTime())) {
						canPush=false;
						break;
					}
				}
				if(canPush) {
					if(essential.elementAt(i).elementAt(j).isInStartToEnd(startTime, endTime)) {
						@SuppressWarnings("unchecked")
						Vector<Lecture> t = (Vector<Lecture>)tempLectures.clone();
						t.add(essential.elementAt(i).elementAt(j));
						pushMajor(t, credit+essential.elementAt(i).elementAt(j).getCredit(), i+1);
					}
				}
			}
		}
		if(tempLectures.size()>=essential.size()+TimeTableMain.sL.getLectures().size() - 1) {
			onlyMajor.add(tempLectures);
			creditList.add(credit);
		}
		return;
	}
	
	private void pushTimetable(Vector<Lecture> tempLectures, int credit, int pivot) { //Push lectures
		if(credit>=mC&&credit<=MC) {
			timeTablesCases.lastElement().add(new TimeTable(credit, tempLectures, nonLectures));
			timeTablesCases.lastElement().lastElement().computeEndtime();
			timeTablesCases.lastElement().lastElement().computeStarttime();
		}
		if(credit>=MC) {
			return;
		}
		for(int i=pivot;i<lectures.size();i++) {
			for(int j=0;j<lectures.elementAt(i).size();j++) {
				boolean canPush = true;
				for(int k=0;k<nonLectures.size();k++) {
					if(nonLectures.elementAt(k).isIntersected(lectures.elementAt(i).elementAt(j))) {
						canPush=false;
						break;
					}
					
				}
				for(int k=0;k<tempLectures.size();k++) {
					if(tempLectures.elementAt(k).isIntersected(lectures.elementAt(i).elementAt(j).getTime())||
							tempLectures.elementAt(k).isSameLecture(lectures.elementAt(i).firstElement())) {
						canPush=false;
						break;
					}
				}
				if(canPush) {
					if(lectures.elementAt(i).elementAt(j).isInStartToEnd(startTime, endTime)) {
						@SuppressWarnings("unchecked")
						Vector<Lecture> t = (Vector<Lecture>)tempLectures.clone();
						t.add(lectures.elementAt(i).elementAt(j));
						pushTimetable(t, credit+lectures.elementAt(i).elementAt(j).getCredit(), i+1);
					}
				}
			}
		}
		return;
	}
	

	
}
