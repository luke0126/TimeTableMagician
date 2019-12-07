import java.util.Vector;

class Lecture extends ScheduleBlock{
	private String professor;//Professor's name
	private int credit;//3, 2 or 1
	private boolean isMajor;//true = major, false = nonMajor
	private int lecture_type;//true->0 : 전공필수, true->1 : 전공기초, true->2 : 전공
	//false->0 : 필수교양, false->1, 2, 3, 4, 5 : 핵심교양 시리즈
	private int level; //0->공통, 1, 2, 3, 4->1학년 2학년 3학년 4학년
	private String code;
	public Lecture(Vector<TimeBlock> time, String name, String professor, int credit, boolean isMajor, int lecture_type,
			int level, String code) {
		super(time, name);
		this.professor = professor;
		this.credit = credit;
		this.isMajor = isMajor;
		this.lecture_type = lecture_type;
		this.level = level;
		this.code = code;
	}

	public void showInformation() { //Just for debugging
		System.out.println("강의 명: "+super.getName()+", 교수님: "+this.professor+", 학점: "+this.credit+", 전공: "+this.isMajor+", 강의유형: "+this.lecture_type+", 학년: "+this.level);
		System.out.println("--강의시간--");
		for(int i=0;i<super.getTime().size();i++) {
			switch (super.getTime().elementAt(i).getDay()) {
			case 0:
				System.out.print("월요일 ");
				break;
			case 1:
				System.out.print("화요일 ");
				break;
			case 2:
				System.out.print("수요일 ");
				break;
			case 3:
				System.out.print("목요일 ");
				break;
			case 4:
				System.out.print("금요일 ");
				break;
			case 5:
				System.out.print("토요일 ");
				break;
			case 6:
				System.out.print("일요일 ");
				break;
			default:
				break;
			}
			System.out.print(super.getTime().elementAt(i).getStartTime()+"~"+super.getTime().elementAt(i).getEndTime()+" ");
		}
		System.out.println();
	}
	
	public String getProfessor() {
		return professor;
	}


	public void setProfessor(String professor) {
		this.professor = professor;
	}
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}

	public boolean isMajor() {
		return isMajor;
	}

	public void setMajor(boolean isMajor) {
		this.isMajor = isMajor;
	}

	public int getLecture_type() {
		return lecture_type;
	}

	public void setLecture_type(int lecture_type) {
		this.lecture_type = lecture_type;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}	
	
}
