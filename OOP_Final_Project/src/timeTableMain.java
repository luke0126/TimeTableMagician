import java.util.Collections;
import java.util.Vector;

class timeTableMain{
	static Vector<timeTable> timeTables = new Vector<timeTable>();
	static Vector<Vector<lecture>> lectures = new Vector<Vector<lecture>>();
	static Vector<nonLecture> nonLectures = new Vector<nonLecture>();
	

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
		int mCredit = etc.getmC();
		int MCredit = etc.getMC();
		float startTime = (float)etc.getsM()/6+etc.getsH();
		float endTime = etc.geteH()+(float)etc.geteM()/6;
		int grade = etc.getGrade();
		
		System.out.println(mCredit);
		System.out.println(MCredit);
		System.out.println(startTime);
		System.out.println(endTime);
		System.out.println(grade);
		sortLectures sorter = new sortLectures(grade);
		Collections.sort(lectures, sorter);
		for(int i=0;i<lectures.size();i++) {
			lectures.elementAt(i).elementAt(0).showInformation();
		}
		
	}

}
