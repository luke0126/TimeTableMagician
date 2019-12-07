import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

class timeTable extends JFrame{
	private int allStartTime, allEndTime;//When all schedule start and end
	private int credit;//Credit
	private Vector<lecture> lectures = new Vector<lecture>();//All lectures
	private Vector<nonLecture> nonLectures = new Vector<nonLecture>();//All non lectures
	
	private int prevOrNext = 0;
	private boolean isReturn = false; //Is window is closed
	private Color yourColor = new Color(240, 232, 232);
	private String[] day = {"월요일", "화요일", "수요일", "목요일", "금요일", "토요일", "일요일"};
	 private String[] timeInfo = {"0시", "1시", "2시", "3시", "4시", "5시", "6시", "7시",
			 "<html> 8시<br />0교시</html>", "<html> 9시<br />1교시</html>", "<html> 10시<br />2교시</html>", "<html> 11시<br />3교시</html>", 
			 "<html> 12시<br />4교시</html>", "<html> 13시<br />5교시</html>", "<html> 14시<br />6교시</html>", "<html> 15시<br />7교시</html>", 
			 "<html> 16시<br />8교시</html>", "<html> 17시<br />9교시</html>", "<html> 18시<br />10교시</html>", "<html> 19시<br />11교시</html>", 
			 "20시", "21시", "22시", "23시"};
	public void callWindow(){ //Open timetable
		prevOrNext=0;
		isReturn = false;
		int rowNum = allEndTime - allStartTime + 1;
		int width = 1560, height = 900;
		getContentPane().removeAll();
		this.refreshGUI();
		setTitle("Timetable Magician");
		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(yourColor);
		
		JButton[] dayBtn = new JButton[7]; //Show day on top
		for(int i=0;i<7;i++) {
			dayBtn[i]=new JButton(day[i]);
			dayBtn[i].setBounds(20 + 195*(i+1), 20, 195, height/rowNum);
			dayBtn[i].setBackground(new Color(255, 255, 255));
			c.add(dayBtn[i]);
		}
		
		JButton[] timeBtn = new JButton[24]; //Show time on left
		for(int i=allStartTime;i<allEndTime;i++) {
			timeBtn[i]=new JButton(timeInfo[i]);
			timeBtn[i].setBounds(20, 20+(height/rowNum)*(i+1 - allStartTime), 195, height/rowNum);
			timeBtn[i].setBackground(new Color(255, 255, 255));
			
			c.add(timeBtn[i]);
		}

		JButton nextBtn = new JButton("다음");
		nextBtn.setBounds(1620, 20, 160, 120);
		nextBtn.setBackground(new Color(255, 255, 255));
		c.add(nextBtn);
		
		JButton prevBtn = new JButton("이전");
		prevBtn.setBounds(1620, 800, 160, 120);
		prevBtn.setBackground(new Color(255, 255, 255));
		c.add(prevBtn);
		
		Vector<JButton> sBtn = new Vector<JButton>(); //Show schedule on that time
		for(int i=0;i<lectures.size();i++) {
			Color lc = new Color(192+(int)(Math.random()*63), 192+(int)(Math.random()*63), 192+(int)(Math.random()*63));
			for(int j=0;j<lectures.elementAt(i).getTime().size();j++) {
				sBtn.add(new JButton(lectures.elementAt(i).getName()));
				timeBlock tB = lectures.elementAt(i).getTime().elementAt(j);
				sBtn.lastElement().setBounds(20 + 195*(tB.getDay()+1), 20+(int)((height/rowNum)*(tB.getStartTime()+1-allStartTime)),
						195, (int)((height/rowNum)*(tB.getEndTime()-tB.getStartTime())));
				sBtn.lastElement().setBackground(lc);
				c.add(sBtn.lastElement());
			}
		}
		int lectureSize = sBtn.size();
		int index=0;
		for(int i=0;i<lectures.size();i++) {
			for(int j=0;j<lectures.elementAt(i).getTime().size();j++) {
				lecture l = lectures.elementAt(i);
				timeBlock t = l.getTime().elementAt(j);
				sBtn.elementAt(index).addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JOptionPane.showMessageDialog(c, l.getName()+"\n"+
					(l.isMajor()?"전공":"교양")+(l.isMajor()?(l.getLecture_type()==0?"필수":l.getLecture_type()==1?"기초":""):"")+
					"(교수: "+l.getProfessor()+")\n강의시간: "+t.getStartTime()+"~"+t.getEndTime());	
					}
				});
				index++;
			}
		}
		
		
		for(int i=0;i<nonLectures.size();i++) {
			Color lc = new Color(192+(int)(Math.random()*63), 192+(int)(Math.random()*63), 192+(int)(Math.random()*63));
			for(int j=0;j<nonLectures.elementAt(i).getTime().size();j++) {
				sBtn.add(new JButton(nonLectures.elementAt(i).getName()));
				timeBlock tB = nonLectures.elementAt(i).getTime().elementAt(j);
				sBtn.lastElement().setBounds(20 + 195*(tB.getDay()+1)
						, 20+(int)((height/rowNum)*(tB.getStartTime()+1-allStartTime)), 195, (int)((height/rowNum)*(tB.getEndTime()-tB.getStartTime())));
				sBtn.lastElement().setBackground(lc);
				c.add(sBtn.lastElement());
			}
		}

		
		for(int i=0;i<nonLectures.size();i++) {
			for(int j=0;j<nonLectures.elementAt(i).getTime().size();j++) {
				nonLecture l = nonLectures.elementAt(i);
				timeBlock t = l.getTime().elementAt(j);
				sBtn.elementAt(index).addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JOptionPane.showMessageDialog(c, l.getName()+"\n"+day[t.getDay()]+t.getStartTime()+"~"+t.getEndTime()+"\n이동시간: "+(int)(l.getFrontDelay()*60)+"분");	
					}
				});
				index++;
			}
		}
		
		
		
		
		JTable tt = new JTable(rowNum, 8);
		tt.setBounds(20, 20, width, height);
		tt.setBackground(new Color(227, 227, 227));
		tt.setGridColor(new Color(200, 200, 200));
		tt.setRowHeight(900/rowNum);
		c.add(tt);
		
		setSize(1820, 1000);
		setVisible(true);
		
		prevBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isReturn = true;
				prevOrNext=-1;
				dispose();
			}
		});
		nextBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isReturn = true;
				prevOrNext=1;
				dispose();
			}
		});
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	} 
	
	public timeTable(int credit, Vector<lecture> lectures, Vector<nonLecture> nonLectures) {
		super();
		this.credit = credit;
		this.lectures = lectures;
		this.nonLectures = nonLectures;
	}


	public float getAllStartTime() {
		return allStartTime;
	}


	public void setAllStartTime(int allStartTime) {
		this.allStartTime = allStartTime;
	}


	public float getAllEndTime() {
		return allEndTime;
	}


	public void setAllEndTime(int allEndTime) {
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
	
	public boolean isClosed() {
		return isReturn;
	}
	public void computeStarttime() { //Compute real start time
		float min=24;
		for(int i=0;i<lectures.size();i++) {
			for(int j=0;j<lectures.elementAt(i).getTime().size();j++) {
				min=lectures.elementAt(i).getTime().elementAt(j).getStartTime()<min?
						lectures.elementAt(i).getTime().elementAt(j).getStartTime():min;
			}
		}
		for(int i=0;i<nonLectures.size();i++) {
			for(int j=0;j<nonLectures.elementAt(i).getTime().size();j++) {
				nonLecture nl = nonLectures.elementAt(i);
				min=nl.getTime().elementAt(j).getStartTime()<min?
						nl.getTime().elementAt(j).getStartTime():min;
			}
		}
		allStartTime=(int)(min - 1);
		if(allStartTime<0) {
			allStartTime=0;
		}
	}
	public void computeEndtime() { //Compute real end time
		float max=0;
		for(int i=0;i<lectures.size();i++) {
			for(int j=0;j<lectures.elementAt(i).getTime().size();j++) {
				max=lectures.elementAt(i).getTime().elementAt(j).getEndTime()>max?
						lectures.elementAt(i).getTime().elementAt(j).getEndTime():max;
			}
		}
		for(int i=0;i<nonLectures.size();i++) {
			for(int j=0;j<nonLectures.elementAt(i).getTime().size();j++) {
				nonLecture nl = nonLectures.elementAt(i);
				max=nl.getTime().elementAt(j).getEndTime()>max?
						nl.getTime().elementAt(j).getEndTime():max;
			}
		}
		allEndTime=(int)(max + 1);
		if(allEndTime>24) {
			allEndTime=24;
		}
	}
	public int selectWay() {
		return prevOrNext;
	}
}
