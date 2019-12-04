import java.util.Collections;
import java.util.Vector;

class timeTableMain{
	static Vector<timeTable> timeTables = new Vector<timeTable>();
	static Vector<Vector<lecture>> lectures = new Vector<Vector<lecture>>();
	static Vector<nonLecture> nonLectures = new Vector<nonLecture>();
	static Vector<Vector<lecture>> essential = new Vector<Vector<lecture>>();
	static Vector<Vector<lecture>> onlyMajor=new Vector<Vector<lecture>>();
	static Vector<Integer> creditList = new Vector<Integer>();
	static int mCredit, MCredit, essentialFlag = 0;
	static float startTime, endTime;
	
	public static void pushMajor(Vector<lecture> tempLectures, int credit, int pivot) {
		if(tempLectures.size()==essentialFlag) {
			onlyMajor.add(tempLectures);
			creditList.add(credit);
			return;
		}
		for(int i=pivot;i<essentialFlag;i++) {
			for(int j=0;j<essential.elementAt(i).size();j++) {
				boolean canPush = true;
				for(int k=0;k<nonLectures.size();k++) {
					if(nonLectures.elementAt(k).isIntersected(essential.elementAt(i).elementAt(j).getTime())) {
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
						Vector<lecture> t = (Vector<lecture>)tempLectures.clone();
						t.add(essential.elementAt(i).elementAt(j));
						pushMajor(t, credit+essential.elementAt(i).elementAt(j).getCredit(), i+1);
					}
				}
			}
		}
	}
	
	public static void pushTimetable(Vector<lecture> tempLectures, int credit, int pivot) {
		if(credit>=mCredit&&credit<=MCredit) {
			timeTables.add(new timeTable(startTime, endTime, credit, tempLectures, nonLectures));
		}
		if(credit>=MCredit) {
			return;
		}
		for(int i=pivot;i<lectures.size();i++) {
			for(int j=0;j<lectures.elementAt(i).size();j++) {
				boolean canPush = true;
				for(int k=0;k<nonLectures.size();k++) {
					if(nonLectures.elementAt(k).isIntersected(lectures.elementAt(i).elementAt(j).getTime())) {
						canPush=false;
						break;
					}
					
				}
				for(int k=0;k<tempLectures.size();k++) {
					if(tempLectures.elementAt(k).isIntersected(lectures.elementAt(i).elementAt(j).getTime())) {
						canPush=false;
						break;
					}
				}
				if(canPush) {
					if(lectures.elementAt(i).elementAt(j).isInStartToEnd(startTime, endTime)) {
						@SuppressWarnings("unchecked")
						Vector<lecture> t = (Vector<lecture>)tempLectures.clone();
						t.add(lectures.elementAt(i).elementAt(j));
						pushTimetable(t, credit+lectures.elementAt(i).elementAt(j).getCredit(), i+1);
					}
				}
			}
		}
		return;
	}
	
	public static void main(String[] args) {
		parsingLecture tool = new parsingLecture();
		lectures = tool.getLecture("C:\\Users\\solji\\eclipse-workspace\\OOP_Final_Project\\src\\TEST.xls");//.xls 파일 경로
		System.out.println(lectures.size());
		nonLectureWindow w = new nonLectureWindow();
		w.callWindow(new Vector<String>());
		boolean isBreak = false;
		while(!isBreak) {
			isBreak = w.isClosed();
			System.out.print(".");
		}
		
		nonLectures = w.getNonLecture();
		etcSettingWindow etc = new etcSettingWindow();
		etc.callWindow();
		isBreak = false;
		while(!isBreak) {
			isBreak=etc.isClosed();
			System.out.print(".");
		}
		mCredit = etc.getmC();
		MCredit = etc.getMC();
		startTime = (float)etc.getsM()/6+etc.getsH();
		endTime = etc.geteH()+(float)etc.geteM()/6;
		int grade = etc.getGrade();
		
		sortLectures sorter = new sortLectures(grade);
		Collections.sort(lectures, sorter);
		
		for(int i=0;i<lectures.size();i++) {
			if(lectures.elementAt(i).elementAt(0).isMajor()&&lectures.elementAt(i).elementAt(0).getLecture_type()==0) {
				essentialFlag++;
				essential.add(lectures.elementAt(i));
			}
			else {
				break;
			}
		}
		
		pushMajor(new Vector<lecture>(), 0, 0);
		
		for(int i=0;i<onlyMajor.size();i++) {
			pushTimetable(onlyMajor.elementAt(i), creditList.elementAt(i), essentialFlag);
		}
		
		for(int i=0;i<10;i++) {
			System.out.println("---Timetable No."+(i+1)+"---");
			for(int j=0;j<timeTables.elementAt(i).getLectures().size();j++) {
				timeTables.elementAt(i).getLectures().elementAt(j).showInformation();
			}
			System.out.println(); 
		}
	}

}
