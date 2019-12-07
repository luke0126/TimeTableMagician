import java.util.Vector;

class Lecture extends ScheduleBlock{
	private String professor;//Professor's name
	private int credit;//3, 2 or 1
	private boolean isMajor;//true = major, false = nonMajor
	private int lecture_type;//true->0 : �����ʼ�, true->1 : ��������, true->2 : ����
	//false->0 : �ʼ�����, false->1, 2, 3, 4, 5 : �ٽɱ��� �ø���
	private int level; //0->����, 1, 2, 3, 4->1�г� 2�г� 3�г� 4�г�
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
		System.out.println("���� ��: "+super.getName()+", ������: "+this.professor+", ����: "+this.credit+", ����: "+this.isMajor+", ��������: "+this.lecture_type+", �г�: "+this.level);
		System.out.println("--���ǽð�--");
		for(int i=0;i<super.getTime().size();i++) {
			switch (super.getTime().elementAt(i).getDay()) {
			case 0:
				System.out.print("������ ");
				break;
			case 1:
				System.out.print("ȭ���� ");
				break;
			case 2:
				System.out.print("������ ");
				break;
			case 3:
				System.out.print("����� ");
				break;
			case 4:
				System.out.print("�ݿ��� ");
				break;
			case 5:
				System.out.print("����� ");
				break;
			case 6:
				System.out.print("�Ͽ��� ");
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
