import java.util.Vector;

class timeTableMain{
	static Vector<timeTable> timeTables = new Vector<timeTable>();
	static Vector<Vector<lecture>> lectures = new Vector<Vector<lecture>>();
	static Vector<nonLecture> nonLectures = new Vector<nonLecture>();
	

	public static void main(String[] args) {
		parsingLecture tool = new parsingLecture();
		lectures = tool.getLecture("C:\\Users\\solji\\eclipse-workspace\\OOP_Final_Project\\src\\TEST.xls");//.xls 파일 경로
		
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
		float startTime = etc.getsM()/6+etc.getsH();
		float endTime = etc.geteH()+etc.geteM()/6;
		
		
		
		
	}

}
