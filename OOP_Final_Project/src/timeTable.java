import java.awt.Color;
import java.awt.Container;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;

class timeTable extends JFrame{
	private float allStartTime, allEndTime;//When all schedule start and end
	private int credit;//Credit
	private Vector<lecture> lectures = new Vector<lecture>();//All lectures
	private Vector<nonLecture> nonLectures = new Vector<nonLecture>();//All non lectures
	
	private String yourFont = "배달의민족 한나체 Pro";
	private Color yourColor = new Color(240, 232, 232);
	private String[] day = {"월요일", "화요일", "수요일", "목요일", "금요일", "토요일", "일요일"};
	
	public void callWindow(){
		getContentPane().removeAll();
		this.refreshGUI();
		setTitle("Timetable Magician");
		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(yourColor);

		JButton btn = new JButton("확인");
		btn.setBounds(20, 20, 195, 36);
		btn.setBackground(new Color(239, 249, 204));
		c.add(btn);
		
		JTable tt = new JTable(25, 8);
		tt.setBounds(20, 20, 1560, 900);
		tt.setBackground(new Color(227, 227, 227));
		tt.setGridColor(new Color(200, 200, 200));
		tt.setRowHeight(36);
		c.add(tt);
		
		
		
		
		
		
		
		
		
		setSize(1620, 1000);
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	} 
	
	public timeTable(float allStartTime, float allEndTime, int credit, Vector<lecture> lectures,
			Vector<nonLecture> nonLectures) {
		super();
		this.allStartTime = allStartTime;
		this.allEndTime = allEndTime;
		this.credit = credit;
		this.lectures = lectures;
		this.nonLectures = nonLectures;
	}


	public float getAllStartTime() {
		return allStartTime;
	}


	public void setAllStartTime(float allStartTime) {
		this.allStartTime = allStartTime;
	}


	public float getAllEndTime() {
		return allEndTime;
	}


	public void setAllEndTime(float allEndTime) {
		this.allEndTime = allEndTime;
	}


	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public Vector<lecture> getLectures() {
		return lectures;
	}


	public void setLectures(Vector<lecture> lectures) {
		this.lectures = lectures;
	}


	public Vector<nonLecture> getNonLectures() {
		return nonLectures;
	}


	public void setNonLectures(Vector<nonLecture> nonLectures) {
		this.nonLectures = nonLectures;
	}

	
	public void refreshGUI() {
		revalidate();
		repaint();
	}
}
