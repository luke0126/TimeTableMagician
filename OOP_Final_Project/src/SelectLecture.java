import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class selectLecture extends JFrame{
	private String[] day = {"월요일", "화요일", "수요일", "목요일", "금요일", "토요일", "일요일"};
	private String[] gradeList = {"공통", "1학년", "2학년", "3학년", "4학년"};
	private String yourFont = "배달의민족 한나체 Pro";
	private Color yourColor = new Color(240, 232, 232), selected = new Color(255, 248, 204), nonSelected = new Color(255, 255, 255);
	private boolean isReturn = false;
	private Vector<lecture> selectedLecture = new Vector<lecture>();
	public void callWindow(Vector<Vector<lecture>> lectures) {
		getContentPane().removeAll();
		this.refreshGUI();
		setTitle("Timetable Magician");
		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(yourColor);
		int[] gradeNum= {0, 0, 0, 0, 0};
		
		for(int i=0;i<lectures.size();i++) {
			for(int j=0;j<lectures.elementAt(i).size();j++) {
				gradeNum[lectures.elementAt(i).elementAt(j).getLevel()]++;
			}
		}
		
		JPanel[] lectureList = new JPanel[5];
		for(int i=0;i<5;i++) {
			lectureList[i]=new JPanel();
			lectureList[i].setLayout(new GridLayout(gradeNum[i], 1));
		}
		JLabel[] gradeLabel = new JLabel[5];
		for(int i=0;i<5;i++) {
			gradeLabel[i] = new JLabel(gradeList[i]);
			gradeLabel[i].setBounds(20+190*i, 0, 170, 80);
			gradeLabel[i].setFont(new Font(yourFont, Font.PLAIN, 21));
			gradeLabel[i].setHorizontalAlignment(JLabel.CENTER);
			c.add(gradeLabel[i]);
		}
		for(int i=0;i<lectures.size();i++) {
			for(int j=0;j<lectures.elementAt(i).size();j++) {
				lecture l = lectures.elementAt(i).elementAt(j);
				String info = "<html>"+l.getName()+"<br />"+
						(l.isMajor()?"전공":"교양")+(l.isMajor()?(l.getLecture_type()==0?"필수":l.getLecture_type()==1?"기초":""):"")+
						"(교수: "+l.getProfessor()+")<br />------강의시간------<br />";
				for(int k=0;k<l.getTime().size();k++) {
					info+=day[l.getTime().elementAt(k).getDay()]+" "+l.getTime().elementAt(k).getStartTime()+"~"+l.getTime().elementAt(k).getEndTime()+"<br />";
				}
				info+="</html>";
				JButton btn = new JButton(info);
				btn.setHorizontalAlignment(JButton.LEFT);
				btn.setBackground(nonSelected);
				btn.setPreferredSize(new Dimension(160, 120));
				lectureList[lectures.elementAt(i).elementAt(j).getLevel()].add(btn);
				btn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(btn.getBackground()==nonSelected) {
							boolean isBreak=false;
							for(int i=0;i<selectedLecture.size();i++) {
								if(selectedLecture.elementAt(i).isIntersected(l.getTime())) {
									isBreak=true;
									break;
								}
							}
							if(isBreak) {
								JOptionPane.showMessageDialog(c, "시간을 확인해 주세요!");
							}
							else {
								btn.setBackground(selected);
								selectedLecture.add(l);
							}							
						}
						else {
							btn.setBackground(nonSelected);
							for(int i=0;i<selectedLecture.size();i++) {
								if(selectedLecture.elementAt(i)==l) {
									selectedLecture.remove(i);
								}
							}
						}						
					}
				});
			}
		}
		
		JButton run = new JButton("다음");
		run.setBounds(1000, 80, 120, 50);
		c.add(run);
		run.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isReturn = true;
				dispose();
			}
		});
		
		JScrollPane[] scroll = new JScrollPane[5];
		for(int i=0;i<5;i++) {
			scroll[i]=new JScrollPane(lectureList[i]);
			scroll[i].setBounds(20+190*i, 70, 170, 850);
	        scroll[i].setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			c.add(scroll[i]);
		}
		
		
		
		setSize(1200,1000);
		setVisible(true);
	}

	public Vector<lecture> getLectures(){
		return selectedLecture;
	}
	
	public void refreshGUI() {
		revalidate();
		repaint();
	}
	public boolean isClosed() {
		return isReturn;
	}

}
