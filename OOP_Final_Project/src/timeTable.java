import java.util.Vector;

class timeTable {
	private float allStartTime, allEndTime;//When all schedule start and end
	private int minCredit, maxCredit;//Minimal credit, Maximum credit
	private Vector<lecture> lectures;//All lectures
	private Vector<nonLecture> nonLectures;//All non lectures


	public timeTable(float allStartTime, float allEndTime, int minCredit, int maxCredit, Vector<lecture> lectures,
			Vector<nonLecture> nonLectures) {
		super();
		this.allStartTime = allStartTime;
		this.allEndTime = allEndTime;
		this.minCredit = minCredit;
		this.maxCredit = maxCredit;
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


	public int getMinCredit() {
		return minCredit;
	}

	public void setMinCredit(int minCredit) {
		this.minCredit = minCredit;
	}

	public int getMaxCredit() {
		return maxCredit;
	}

	public void setMaxCredit(int maxCredit) {
		this.maxCredit = maxCredit;
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

}
