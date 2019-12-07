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

class TimeTable extends JFrame{
	private int allStartTime, allEndTime;//When all schedule start and end
	private int credit;//Credit
	private Vector<Lecture> lectures = new Vector<Lecture>();//All lectures
	private Vector<NonLecture> nonLectures = new Vector<NonLecture>();//All non lectures
	
	private int prevOrNext = 0;
	private boolean isReturn = false; //Is window is closed
	private Color yourColor = new Color(240, 232, 232);
	private String[] day = {"������", "ȭ����", "������", "�����", "�ݿ���", "�����", "�Ͽ���"};
	 private String[] timeInfo = {"0��", "1��", "2��", "3��", "4��", "5��", "6��", "7��",
			 "<html> 8��<br />0����</html>", "<html> 9��<br />1����</html>", "<html> 10��<br />2����</html>", "<html> 11��<br />3����</html>", 
			 "<html> 12��<br />4����</html>", "<html> 13��<br />5����</html>", "<html> 14��<br />6����</html>", "<html> 15��<br />7����</html>", 
			 "<html> 16��<br />8����</html>", "<html> 17��<br />9����</html>", "<html> 18��<br />10����</html>", "<html> 19��<br />11����</html>", 
			 "20��", "21��", "22��", "23��"};
	public void callWindow(Vector<TimeTable> timeTables, int tableIndex){ //Open timetable
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

		JButton nextBtn = new JButton("����");
		nextBtn.setBounds(1620, 20, 160, 120);
		nextBtn.setBackground(new Color(255, 255, 255));
		c.add(nextBtn);
		
		JButton prevBtn = new JButton("����");
		prevBtn.setBounds(1620, 800, 160, 120);
		prevBtn.setBackground(new Color(255, 255, 255));
		c.add(prevBtn);
		
		Vector<JButton> sBtn = new Vector<JButton>(); //Show schedule on that time
		for(int i=0;i<lectures.size();i++) {
			Color lc = new Color(192+(int)(Math.random()*63), 192+(int)(Math.random()*63), 192+(int)(Math.random()*63));
			for(int j=0;j<lectures.elementAt(i).getTime().size();j++) {
				sBtn.add(new JButton(lectures.elementAt(i).getName()));
				TimeBlock tB = lectures.elementAt(i).getTime().elementAt(j);
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
				Lecture l = lectures.elementAt(i);
				TimeBlock t = l.getTime().elementAt(j);
				sBtn.elementAt(index).addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JOptionPane.showMessageDialog(c, l.getName()+"\n"+
					(l.isMajor()?"����":"����")+(l.isMajor()?(l.getLecture_type()==0?"�ʼ�":l.getLecture_type()==1?"����":""):"")+
					"(����: "+l.getProfessor()+")\n���ǽð�: "+t.getStartTime()+"~"+t.getEndTime());	
					}
				});
				index++;
			}
		}
		
		
		for(int i=0;i<nonLectures.size();i++) {
			Color lc = new Color(192+(int)(Math.random()*63), 192+(int)(Math.random()*63), 192+(int)(Math.random()*63));
			for(int j=0;j<nonLectures.elementAt(i).getTime().size();j++) {
				sBtn.add(new JButton(nonLectures.elementAt(i).getName()));
				TimeBlock tB = nonLectures.elementAt(i).getTime().elementAt(j);
				sBtn.lastElement().setBounds(20 + 195*(tB.getDay()+1)
						, 20+(int)((height/rowNum)*(tB.getStartTime()+1-allStartTime)), 195, (int)((height/rowNum)*(tB.getEndTime()-tB.getStartTime())));
				sBtn.lastElement().setBackground(lc);
				c.add(sBtn.lastElement());
			}
		}

		
		for(int i=0;i<nonLectures.size();i++) {
			for(int j=0;j<nonLectures.elementAt(i).getTime().size();j++) {
				NonLecture l = nonLectures.elementAt(i);
				TimeBlock t = l.getTime().elementAt(j);
				sBtn.elementAt(index).addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JOptionPane.showMessageDialog(c, l.getName()+"\n"+day[t.getDay()]+t.getStartTime()+"~"+t.getEndTime()+"\n�̵��ð�: "+(int)(l.getFrontDelay()*60)+"��");	
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
				timeTables.elementAt(tableIndex-1>=0?tableIndex-1:0).callWindow(timeTables, tableIndex-1>=0?tableIndex+1:0);
			}
		});
		nextBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isReturn = true;
				prevOrNext=1;
				dispose();
				timeTables.elementAt(tableIndex+1<=timeTables.size()-1?tableIndex+1:timeTables.size()-1).callWindow(timeTables
						, tableIndex+1<=timeTables.size()-1?tableIndex+1:timeTables.size()-1);
			}
		});
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	} 
	
	public TimeTable(int credit, Vector<Lecture> lectures, Vector<NonLecture> nonLectures) {
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

	public Vector<Lecture> getLectures() {
		return lectures;
	}


	public void setLectures(Vector<Lecture> lectures) {
		this.lectures = lectures;
	}


	public Vector<NonLecture> getNonLectures() {
		return nonLectures;
	}


	public void setNonLectures(Vector<NonLecture> nonLectures) {
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
				NonLecture nl = nonLectures.elementAt(i);
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
				NonLecture nl = nonLectures.elementAt(i);
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
