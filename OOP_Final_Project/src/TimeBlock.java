
class TimeBlock {
	private float startTime, endTime;//24hours
	private int day;//0->Monday, 1->Tuesday, ..., 6->Sunday
	
	public TimeBlock(float startTime, float endTime, int day) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.day = day;
	}
	
	public float getStartTime() {
		return startTime;
	}
	public void setStartTime(float startTime) {
		this.startTime = startTime;
	}
	public float getEndTime() {
		return endTime;
	}
	public void setEndTime(float endTime) {
		this.endTime = endTime;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	
	
}
