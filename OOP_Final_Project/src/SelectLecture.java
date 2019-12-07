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

public class SelectLecture extends JFrame {
	
	private String[] day = {"������", "ȭ����", "������", "�����", "�ݿ���", "�����", "�Ͽ���"};
	private String[] gradeList = {"����", "1�г�", "2�г�", "3�г�", "4�г�"};
	private String yourFont = "����ǹ��� �ѳ�ü Pro";
	private Color yourColor = new Color(240, 232, 232), selected = new Color(255, 248, 204), nonSelected = new Color(255, 255, 255);
	private boolean isReturn = false;
	private Vector<Lecture> selectedLecture = new Vector<Lecture>();
	private Vector<Vector<Lecture>> lectures;
	
	public SelectLecture(String filePath) {
		
		ParsingLecture tool = new ParsingLecture(filePath);
		lectures = tool.getLectures();
		
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
				Lecture l = lectures.elementAt(i).elementAt(j);
				String info = "<html>"+l.getName()+"<br />"+
						(l.isMajor()?"����":"����")+(l.isMajor()?(l.getLecture_type()==0?"�ʼ�":l.getLecture_type()==1?"����":""):"")+
						"(����: "+l.getProfessor()+")<br />------���ǽð�------<br />";
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
								JOptionPane.showMessageDialog(c, "�ð��� Ȯ���� �ּ���!");
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
		
		JButton run = new JButton("����");
		run.setBounds(1000, 80, 120, 50);
		c.add(run);
		run.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NonLectureWindow nonLectureWindow = new NonLectureWindow(selectedLecture, lectures);
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
		
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1200,1000);
		setVisible(true);
	}
	
	public Vector<Lecture> getLectures(){
		return selectedLecture;
	}
	
	public void refreshGUI() {
		revalidate();
		repaint();
	}
}
